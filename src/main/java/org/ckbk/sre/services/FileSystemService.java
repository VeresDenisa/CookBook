package org.ckbk.sre.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemService {
    private static String APPLICATION_FOLDER = ".ckbkFiles";
    private static String USER_FOLDER = System.getProperty("user.home");
    public static Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER);

    public static void setApplicationFolder(String APP){
        APPLICATION_FOLDER = APP;
        APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    }

    public static Path getPathToFile(String... path) {
        return APPLICATION_HOME_PATH.resolve(Paths.get(".", path));
    }

    public static void setAppFolder(String s) {
        APPLICATION_FOLDER = s;
        APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    }

    public static void initDirectory() {
        Path applicationHomePath = getApplicationHomeFolder();
        if (!Files.exists(applicationHomePath)) {
            if (!applicationHomePath.toFile().mkdirs())
                throw new RuntimeException("Could not initialize directories");
        }
    }

    public static Path getApplicationHomeFolder() {
        return Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    }
}
