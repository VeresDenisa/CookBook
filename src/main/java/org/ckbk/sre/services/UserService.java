package org.ckbk.sre.services;

import org.ckbk.sre.exceptions.*;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.ckbk.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

    private static ObjectRepository<User> userRepository;
    private static User loggedInUser;

    public static void initDatabase(Nitrite database) {
        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String username, String password, String mail, String nrTel, String lastName, String firstName, String role) throws UsernameAlreadyExistsException, EmptyInputFieldException, PhoneNumberIsNotValidException, EmailAddressIsNotValidException{
        checkUserDoesNotAlreadyExist(username);
        checkInputFieldsAreFilled(username, password, mail, nrTel, lastName, firstName);
        checkPhoneNumberIsValid(nrTel);
        checkEmailAddressIsValid(mail);
        userRepository.insert(new User(username, encodePassword(username, password), mail, nrTel, lastName, firstName, role, "images/profile/row-3-column-4.png"));
    }

    public static void editUser(String password, String lastName, String firstName, String mail, String nrTel, String passN, String passNC) throws PhoneNumberIsNotValidException, EmailAddressIsNotValidException, NewPasswordIsNotConfirmedException, InvalidCredentialsException {
        for(User user : userRepository.find())
            if(Objects.equals(user, loggedInUser))
                if (loggedInUser.getPassword().equals(encodePassword(loggedInUser.getUsername(), password))) {
                    if (!(firstName.isEmpty())) loggedInUser.setFirstName(firstName);
                    if (!(lastName.isEmpty())) loggedInUser.setLastName(lastName);
                    if (!(nrTel.isEmpty())) {
                        checkPhoneNumberIsValid(nrTel);
                        loggedInUser.setNrTel(nrTel);
                    }
                    if (!(mail.isEmpty())) {
                        checkEmailAddressIsValid(mail);
                        loggedInUser.setMail(mail);
                    }
                    if (!(passN.isEmpty()) && !(passNC.isEmpty())) {
                        checkNewPassword(passN, passNC);
                        loggedInUser.setPassword(encodePassword(loggedInUser.getUsername(), passN));
                    }
                    userRepository.update(loggedInUser);
                } else throw new InvalidCredentialsException();
    }

    public static void deleteMyAccount(String password) throws InvalidCredentialsException {
        for(User user : userRepository.find())
            if(Objects.equals(user, loggedInUser))
                if (loggedInUser.getPassword().equals(encodePassword(loggedInUser.getUsername(), password))) {
                    userRepository.remove(loggedInUser);
                    RecipeService.changeAnonymous(loggedInUser);
                } else throw new InvalidCredentialsException();
    }

    public static void deleteClientAccount(User user){
        for(User u : userRepository.find())
            if(Objects.equals(user, u)) {
                userRepository.remove(u);
                RecipeService.changeAnonymous(u);
            }
    }

    private static void checkNewPassword(String passN, String passNC) throws NewPasswordIsNotConfirmedException {
        if (!(passN.equals(passNC))) throw new NewPasswordIsNotConfirmedException();
    }

    private static void checkEmailAddressIsValid(String mail) throws EmailAddressIsNotValidException {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(mail);
        if(!mat.matches()) throw new EmailAddressIsNotValidException();
    }

    private static void checkPhoneNumberIsValid(String nrTel) throws PhoneNumberIsNotValidException{
        try {
            Integer.parseInt(nrTel);
        } catch (NumberFormatException nfe) {
            throw new PhoneNumberIsNotValidException();
        }
        if(nrTel.length() != 10) throw new PhoneNumberIsNotValidException();
    }

    public static void logInUser(String username, String password) throws InvalidCredentialsException, EmptyInputFieldException {
        if(username.isEmpty() || password.isEmpty()) throw new EmptyInputFieldException();
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                if(!Objects.equals(encodePassword(username, password), user.getPassword())) {
                    throw new InvalidCredentialsException();
                } else {
                    loggedInUser = user;
                    return;
                }
        }
        throw  new InvalidCredentialsException();
    }

    public static User getUser(){
        return loggedInUser;
    }

    public static long getUserRepositorySize(){ return userRepository.size();}

    public static User findUser(String username){
        for(User user : userRepository.find())
            if(Objects.equals(user.getUsername(), username))
                return user;
        return null;
    }

    public static void reportClient(String username){
        for(User user : userRepository.find())
            if(Objects.equals(user.getUsername(), username)){
                user.setReported(true);
                userRepository.update(user);
            }
    }

    public static void cancelReportClient(String username){
        for(User user : userRepository.find())
            if(Objects.equals(user.getUsername(), username)){
                user.setReported(false);
                userRepository.update(user);
            }
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static void checkInputFieldsAreFilled(String username, String password, String mail, String nrTel, String lastName, String firstName) throws EmptyInputFieldException {
        if(username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || nrTel.isEmpty() || mail.isEmpty()) throw new EmptyInputFieldException();
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


    public static ArrayList<String> getReportedUsers() {
        ArrayList<String> rep = new ArrayList<>();
        var l = userRepository.find().toList();
        for(User user : l){
            if(user.isReported()) {
                rep.add(user.getUsername());
            }
        }
        return rep;
    }

    public static ObjectRepository<User> getUserRepository() {
        return userRepository;
    }
}
