package org.ckbk.sre.services;

import org.ckbk.sre.exceptions.EmptyInputFieldException;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.ckbk.sre.exceptions.UsernameAlreadyExistsException;
import org.ckbk.sre.exceptions.InvalidCredentialsException;
import org.ckbk.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.ckbk.sre.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;
    private static String loggedInUser;

    public static void initDatabase(Nitrite database) {
        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String username, String password, String mail, String nrTel, String lastName, String firstName, User.ROLE role) throws UsernameAlreadyExistsException, EmptyInputFieldException {
        checkUserDoesNotAlreadyExist(username);
        checkInputFieldsAreFilled(username, password, mail, nrTel, lastName, firstName);
        userRepository.insert(new User(username, encodePassword(username, password), mail, nrTel, lastName, firstName, role, "@images/profile/row-1-column-1.png"));
    }
    public static void logInUser(String username, String password) throws InvalidCredentialsException, EmptyInputFieldException {
        if(username.isEmpty() || password.isEmpty()) throw new EmptyInputFieldException();
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                if(!Objects.equals(encodePassword(username, password), user.getPassword())) {
                    throw new InvalidCredentialsException();
                } else {
                    loggedInUser = username;
                    return;
                }
        }
        throw  new InvalidCredentialsException();
    }

    public static String getUsername(){
        return loggedInUser;
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

    private static String encodePassword(String salt, String password) {
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


}