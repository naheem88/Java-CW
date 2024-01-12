import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class User {
    // Instance Variables
    private static String userName;
    private static String userPassword;
    private static HashMap<String, String> userNameAndPasswordHashMap;

    public User(String userName, String userPassword) {
        User.userName = userName.stripLeading().stripTrailing();
        User.userPassword = userPassword.stripLeading().stripTrailing();
        userNameAndPasswordHashMap = new HashMap<>();
        loadInfo();
    }

    public User() {
    }

    public HashMap<String, String> getUserNameAndPasswordHashMap() {
        return userNameAndPasswordHashMap;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserName(String userName) {
        User.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        User.userPassword = userPassword;
    }

    public void saveToFile() {
        // Checking if the user has entered a username and password
        if (userName.equals("") || userPassword.equals("")) {
            return;
        }
        try {
            // Saving the user information to a file
            BufferedWriter textWriter = new BufferedWriter(new FileWriter("UserList.txt", true));
            textWriter.write(userName + "," + userPassword);
            textWriter.newLine();
            textWriter.close();
        } catch (IOException exception) {
            System.out.println("\nAn error has occurred\n");
        }
    }

    public void loadInfo() {
        try {
            // Loading the user information from a file
            File fileChecker = new File("UserList.txt");
            // Checking if the file exists
            if (fileChecker.exists() && !(userName.equals("") || userPassword.equals(""))) {
                FileReader file = new FileReader("UserList.txt");
                Scanner textReader = new Scanner(file);
                while (textReader.hasNextLine()) {
                    // Reading the file line by line
                    String textLine = textReader.nextLine();
                    String[] fileData = textLine.split(",");
                    // Checking if the file is empty
                    if (fileData[0].equals("")) {
                        break;
                    }
                    userNameAndPasswordHashMap.put(fileData[0], fileData[1]);
                }
                file.close();
            }
        } catch (IOException exception) {
            System.out.println("\nAn error has occurred loading the product information\n");
        }
    }

    public boolean newUser() {
        // Checking if the user is a new user
        if (userNameAndPasswordHashMap.containsKey(userName)) {
            return false;
        }
        return true;
    }
}