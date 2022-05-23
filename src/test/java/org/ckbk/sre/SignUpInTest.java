package org.ckbk.sre;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.ckbk.sre.services.FileSystemService;
import org.ckbk.sre.services.UserService;
import org.dizitart.no2.Nitrite;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.ckbk.sre.services.FileSystemService.getPathToFile;
import static org.ckbk.sre.services.FileSystemService.initDirectory;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class SignUpInTest {

//    private static Nitrite database;
//
//    public static final String USERNAME = "MIHA84";
//    public static final String PASSWORD = "airport";
//    public static final String ROLE = "Client";
//    public static final String IMAGE = "images/profile/row-3-column-4.png";
//    public static final String FIRSTNAME = "Mihaela";
//    public static final String LASTNAME = "Smith";
//    public static final String MAIL = "miha@gmail.com";
//    public static final String TEL = "0762497356";
//
//    @BeforeEach
//    void setUp() throws Exception {
//        FileSystemService.setApplicationFolder(".testing_blog");
//        initDirectory();
//        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
//        database = Nitrite.builder()
//                .filePath(getPathToFile("testing.db").toFile())
//                .openOrCreate("test", "test");
//        UserService.initDatabase(database);
//        UserService.addUser(USERNAME, PASSWORD, MAIL, TEL, LASTNAME, FIRSTNAME, ROLE);
//    }
//
//    @BeforeAll
//    static void beforeAll() {
//        System.out.println("SignUp&In testing");
//    }
//
//    @AfterAll
//    static void afterAll() {
//        System.out.println("SignUp&In testing");
//    }
//
//    @AfterEach
//    void tearDown(){
//        database.close();
//    }
//
//    @Start
//    void start(Stage primaryStageS) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("SignUpIn.fxml"));
//        primaryStageS.setTitle("SIGN IN / SIGN UP");
//        primaryStageS.setScene(new Scene(root, 900, 500));
//        primaryStageS.setResizable(false);
//        primaryStageS.show();
//    }
//
////    @Test
////    void testLoginFailedWrongCredentials(FxRobot robot) {
////        robot.clickOn("#usernameLogInField");
////        robot.write(USERNAME + 1);
////        robot.clickOn("#passwordLogInField");
////        robot.write(PASSWORD);
////        robot.clickOn("#login");
////        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Invalid credentials! Try again...");
////    }
}