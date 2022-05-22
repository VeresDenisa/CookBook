package org.ckbk.sre;

import org.apache.commons.io.FileUtils;
import org.ckbk.sre.exceptions.*;
import org.ckbk.sre.services.FileSystemService;
import org.ckbk.sre.services.RecipeService;
import org.ckbk.sre.services.UserService;
import org.dizitart.no2.Nitrite;
import org.junit.jupiter.api.*;
import org.ckbk.sre.model.User;

import static org.ckbk.sre.services.FileSystemService.getPathToFile;
import static org.ckbk.sre.services.FileSystemService.initDirectory;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

class UserServiceTest {

    private static Nitrite database;

    public static final String USERNAME = "MIHA84";
    public static final String PASSWORD = "airport";
    public static final String ROLE = "Client";
    public static final String IMAGE = "images/profile/row-3-column-4.png";
    public static final String FIRSTNAME = "Mihaela";
    public static final String LASTNAME = "Smith";
    public static final String MAIL = "miha@gmail.com";
    public static final String TEL = "0762497356";

    @BeforeAll
    static void beforeAll() {
        System.out.println("UserService testing");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("UserService testing");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.setApplicationFolder(".testing_blog");
        initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        database = Nitrite.builder()
                .filePath(getPathToFile("testing.db").toFile())
                .openOrCreate("test", "test");
        UserService.initDatabase(database);
    }

    @AfterEach
    void tearDown() {
        database.close();
    }

    @Test
    @DisplayName("Database is initialized and there are no users")
    void testDatabaseIsInitializedAndNoUserIsPersisted() {
        assertThat(UserService.getUserRepositorySize()).isEqualTo(0);
    }

    @Test
    @DisplayName("User is successfully persisted to Database")
    void testUserIsAddedToDatabase() throws UsernameAlreadyExistsException, EmailAddressIsNotValidException, EmptyInputFieldException, PhoneNumberIsNotValidException, EmailAddressIsNotValidException, EmptyInputFieldException, PhoneNumberIsNotValidException {
        assertThat(UserService.getUserRepositorySize()).isEqualTo(0);
        UserService.addUser(USERNAME, PASSWORD, MAIL, TEL, LASTNAME, FIRSTNAME, ROLE);
        assertThat(UserService.getUserRepositorySize()).isEqualTo(1);
        User user = UserService.findUser(USERNAME);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(USERNAME);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(USERNAME, PASSWORD));
        assertThat(String.valueOf(user.getRole())).isEqualTo(ROLE);
        assertThat(user.getNrTel()).isEqualTo(TEL);
        assertThat(user.getMail()).isEqualTo(MAIL);
        assertThat(user.getImage()).isEqualTo(IMAGE);
        assertThat(user.getFirstName()).isEqualTo(FIRSTNAME);
        assertThat(user.getLastName()).isEqualTo(LASTNAME);
        assertThat(user.isReported()).isEqualTo(false);
    }

    @Test
    @DisplayName("Password encryption works")
    void testPasswordIsEncrypted() {
        assertThat(UserService.encodePassword("Ana", "are")).isEqualTo(UserService.encodePassword("Ana", "are"));
        assertThat(UserService.encodePassword("Ana", "are")).isNotEqualTo(UserService.encodePassword("Ana", "mare"));
        assertThat(UserService.encodePassword("Ane", "are")).isNotEqualTo(UserService.encodePassword("Ana", "are"));
        assertThat(UserService.encodePassword("Ane", "mare")).isNotEqualTo(UserService.encodePassword("Ana", "are"));
    }

    @Test
    @DisplayName("Multiple users are successfully persisted to Database")
    void testUsersAreAddedToDatabase() throws UsernameAlreadyExistsException, EmailAddressIsNotValidException, EmptyInputFieldException, PhoneNumberIsNotValidException {
        for(int i = 0; i < 50; i++){
            String username = USERNAME + i;
            UserService.addUser(username ,PASSWORD, MAIL, TEL, LASTNAME, FIRSTNAME, ROLE);
        }
        assertThat(UserService.getUserRepository().find().toList()).isNotEmpty();
        assertThat(UserService.getUserRepositorySize()).isEqualTo(50);
        for(User user : UserService.getUserRepository().find().toList()){
            assertThat(user).isNotNull();
            assertThat(user.getUsername()).isEqualTo(USERNAME + UserService.getUserRepository().find().toList().indexOf(user));
            assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(USERNAME + UserService.getUserRepository().find().toList().indexOf(user), PASSWORD));
            assertThat(String.valueOf(user.getRole())).isEqualTo(ROLE);
            assertThat(user.getNrTel()).isEqualTo(TEL);
            assertThat(user.getMail()).isEqualTo(MAIL);
            assertThat(user.getImage()).isEqualTo(IMAGE);
            assertThat(user.getFirstName()).isEqualTo(FIRSTNAME);
            assertThat(user.getLastName()).isEqualTo(LASTNAME);
        }
    }

    @Test
    @DisplayName("User is successfully deleted from Database")
    void testUserIsDeletedFromDatabase() throws UsernameAlreadyExistsException, EmailAddressIsNotValidException, EmptyInputFieldException, PhoneNumberIsNotValidException, InvalidCredentialsException {
        assertThat(UserService.getUserRepositorySize()).isEqualTo(0);
        UserService.addUser(USERNAME, PASSWORD, MAIL, TEL, LASTNAME, FIRSTNAME, ROLE);
        UserService.addUser(USERNAME + 1, PASSWORD, MAIL, TEL, LASTNAME, FIRSTNAME, ROLE);
        assertThat(UserService.getUserRepositorySize()).isEqualTo(2);

        boolean ok = false;
        for(User user : UserService.getUserRepository().find())
            if (user.getUsername().equals(USERNAME)) {
                assertThat(user).isEqualTo(UserService.findUser(user.getUsername()));
                ok = true;
                break;
            }
        assertThat(ok).isEqualTo(true);
        UserService.logInUser(USERNAME, PASSWORD);
        RecipeService.initDatabase(database);

        assertThat(UserService.getUserRepositorySize()).isEqualTo(2);
        UserService.deleteClientAccount(UserService.findUser(USERNAME + 1));
        assertThat(UserService.getUserRepositorySize()).isEqualTo(1);
        assertThat(UserService.findUser(USERNAME + 1)).isNull();

        UserService.deleteMyAccount(PASSWORD);
        assertThat(UserService.getUserRepositorySize()).isEqualTo(0);
    }

    @Test
    @DisplayName("User is successfully reported")
    void testUserIsReported() throws UsernameAlreadyExistsException, EmailAddressIsNotValidException, EmptyInputFieldException, PhoneNumberIsNotValidException, InvalidCredentialsException {
        UserService.addUser(USERNAME, PASSWORD, MAIL, TEL, LASTNAME, FIRSTNAME, ROLE);

        assertThat(UserService.findUser(USERNAME).isReported()).isEqualTo(false);
        assertThat(UserService.getReportedUsers().size()).isEqualTo(0);
        UserService.reportClient(USERNAME);
        assertThat(UserService.findUser(USERNAME).isReported()).isEqualTo(true);
        assertThat(UserService.getReportedUsers().get(0)).isEqualTo(UserService.findUser(USERNAME).getUsername());
        assertThat(UserService.getReportedUsers().size()).isEqualTo(1);
        UserService.cancelReportClient(USERNAME);
        assertThat(UserService.findUser(USERNAME).isReported()).isEqualTo(false);
        assertThat(UserService.getReportedUsers().size()).isEqualTo(0);

        assertThat(UserService.getUserRepositorySize()).isEqualTo(1);
    }

    @Test
    @DisplayName("User is successfully logged in")
    void testUserIsLoggedIn() throws UsernameAlreadyExistsException, EmailAddressIsNotValidException, EmptyInputFieldException, PhoneNumberIsNotValidException, InvalidCredentialsException {
        UserService.addUser(USERNAME, PASSWORD, MAIL, TEL, LASTNAME, FIRSTNAME, ROLE);

        assertThrows(InvalidCredentialsException.class, () ->
                UserService.logInUser(USERNAME, PASSWORD + 1));
        assertThrows(InvalidCredentialsException.class, () ->
                UserService.logInUser(USERNAME + 1, PASSWORD));
        assertThrows(InvalidCredentialsException.class, () ->
                UserService.logInUser(USERNAME + 1, PASSWORD + 1));
        assertThrows(EmptyInputFieldException.class, () ->
                UserService.logInUser("", PASSWORD));
        assertThrows(EmptyInputFieldException.class, () ->
                UserService.logInUser(USERNAME, ""));
        assertThrows(EmptyInputFieldException.class, () ->
                UserService.logInUser("", ""));

        UserService.logInUser(USERNAME, PASSWORD);

        assertThat(UserService.getUserRepositorySize()).isEqualTo(1);
    }

    @Test
    @DisplayName("User information is successfully updated")
    void testEditUser() throws UsernameAlreadyExistsException, EmailAddressIsNotValidException, EmptyInputFieldException, PhoneNumberIsNotValidException, InvalidCredentialsException, NewPasswordIsNotConfirmedException {
        UserService.addUser(USERNAME, PASSWORD, MAIL, TEL, LASTNAME, FIRSTNAME, ROLE);
        assertThrows(UsernameAlreadyExistsException.class, () ->
                UserService.addUser(USERNAME ,PASSWORD, MAIL, TEL, LASTNAME, FIRSTNAME, ROLE) );
        assertThat(UserService.getUserRepositorySize()).isEqualTo(1);

        UserService.logInUser(USERNAME, PASSWORD);
        assertThrows(EmailAddressIsNotValidException.class, () ->
                UserService.editUser(PASSWORD, LASTNAME + "-John", FIRSTNAME + " Ioana", MAIL + "com and org", TEL, PASSWORD + 1, PASSWORD + 1));
        assertThrows(PhoneNumberIsNotValidException.class, () ->
                UserService.editUser(PASSWORD, LASTNAME + "-John", FIRSTNAME + " Ioana", MAIL, TEL + "1", PASSWORD + 1, PASSWORD + 1));
        assertThrows(InvalidCredentialsException.class, () ->
                UserService.editUser(PASSWORD + 1, LASTNAME + "-John", FIRSTNAME + " Ioana", MAIL, TEL, PASSWORD + 1, PASSWORD + 1));
        assertThrows(NewPasswordIsNotConfirmedException.class, () ->
                UserService.editUser(PASSWORD, LASTNAME + "-John", FIRSTNAME + " Ioana", MAIL, TEL, PASSWORD + 1, PASSWORD + 2));
        UserService.editUser(PASSWORD, LASTNAME + "-John", FIRSTNAME + " Ioana", MAIL, TEL, PASSWORD + 1, PASSWORD + 1);
        assertThat(UserService.getUserRepositorySize()).isEqualTo(1);
        assertThat(UserService.getUser().getNrTel()).isEqualTo(TEL);
        assertThat(UserService.getUser().getMail()).isEqualTo(MAIL);
        assertThat(UserService.getUser().getFirstName()).isEqualTo(FIRSTNAME + " Ioana");
        assertThat(UserService.getUser().getLastName()).isEqualTo(LASTNAME + "-John");
        assertThat(UserService.getUser().isReported()).isEqualTo(false);
        assertThat(UserService.getUser().getPassword()).isEqualTo(UserService.encodePassword(UserService.getUser().getUsername(), PASSWORD + 1));
    }
}
