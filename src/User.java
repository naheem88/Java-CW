import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private static String userName;
    private static String userPassword;
    private List<String> regularUserList;

    public User(String userName, String userPassword) {
        User.userName = userName.stripLeading().stripTrailing();
        User.userPassword = userPassword.stripLeading().stripTrailing();
    }

    public User() {
        regularUserList = new ArrayList<>();
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
            textWriter.write(userName);
            textWriter.newLine();
            textWriter.close();
        } catch (IOException exception) {
            System.out.println("\nAn error has occurred\n");
        }
    }

    public void loadInfo() {
        try {
            File fileChecker = new File("UserList.txt");
            if (fileChecker.exists() && !(userName.equals(""))) {
                FileReader file = new FileReader("UserList.txt");
                Scanner textReader = new Scanner(file);
                while (textReader.hasNextLine()) {
                    String fileData = textReader.nextLine();
                    if (fileData.equals("")) {
                        break;
                    }
                    regularUserList.add(fileData);
                }
                file.close();
            }
        } catch (IOException exception) {
            System.out.println("\nAn error has occurred loading the product information\n");
        }
    }

    public boolean newUser() {
        if (userName.equals("")) {
            return false;
        }
        for (String name: regularUserList) {
            if (name.equals(userName)) {
                return false;
            }
        }
        return true;
    }
}