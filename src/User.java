import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class User {
    private static String userName;
    private static String userPassword;

    private HashMap<String, String> regularUserHashMap;

    public User(String userName, String userPassword) {
        User.userName = userName.stripLeading().stripTrailing();
        User.userPassword = userPassword.stripLeading().stripTrailing();
    }

    public User() {
        regularUserHashMap = new HashMap<>();
        loadInfo();
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

    // Implement the file method
    public void saveToFile() {
        try {
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
            File fileChecker = new File("UserList.txt");
            if (fileChecker.exists()) {
                FileReader file = new FileReader("UserList.txt");
                Scanner textReader = new Scanner(file);
                while (textReader.hasNextLine()) {
                    String textLine = textReader.nextLine();
                    String[] fileData = textLine.split(",");
                    System.out.println(fileData[0]);
                    System.out.println(fileData[1]);
                    if (textLine.equals("")) {
                        break;
                    }
                    regularUserHashMap.put(fileData[0], fileData[1]);
                }
                file.close();
            }
        } catch (IOException exception) {
            System.out.println("\nAn error has occurred loading the product information\n");
        }
    }

    public HashMap<String, String> getRegularUserHashMap() {
        return regularUserHashMap;
    }

    public boolean newUser() {
        if (regularUserHashMap == null) {
            return true;
        }
        if (regularUserHashMap.containsKey(userName)) {
            return false;
        }
        return true;
    }
}