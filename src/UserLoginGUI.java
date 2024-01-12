import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserLoginGUI extends JFrame {

    // GUI Components
    JLabel usernameLabel;
    JLabel passwordLabel;
    JDialog dialogBox;
    JLabel dialogBoxLabel;

    // Instance Variables
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private WestminsterShoppingManager shoppingManager;

    public UserLoginGUI(WestminsterShoppingManager shoppingManager) {
        setTitle("User Login");
        setLayout(new GridLayout(3, 2));
        this.shoppingManager = shoppingManager;

        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(18);
        passwordField = new JPasswordField(18);

        loginBtn = new JButton("Login");
        loginBtn.addMouseListener(new loginBtnHandler());

        // Add components to the frame
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel());
        add(loginBtn);
    }

    private class loginBtnHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent event) {
            // Getting the username and password
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            User userInformation = new User(username, password);
            // Checking if the username and password are correct
            if (userInformation.getUserNameAndPasswordHashMap().containsKey(username)) {
                // Checking if the password is correct
                if (userInformation.getUserNameAndPasswordHashMap().get(username).equals(password)) {
                    // Creating the home frame
                    createHomeFrame();
                } else {
                    // Displaying a dialog box
                    dialogBox = new JDialog(UserLoginGUI.this, "Incorrect Password");
                    dialogBoxLabel = new JLabel("Please Enter the Correct Password");
                    dialogBox.add(dialogBoxLabel);
                    dialogBox.setSize(280, 100);
                    dialogBox.setVisible(true);
                    usernameField.setText("");
                    passwordField.setText("");
                }
            } else {
                createHomeFrame();
            }
        }
    }

    public void createHomeFrame() {
        // Creating the home frame
        HomeGUI homeFrame = new HomeGUI(shoppingManager);
        // Settings for the frame
        homeFrame.setSize(900,600);
        homeFrame.setVisible(true);
        homeFrame.setDefaultCloseOperation(homeFrame.DISPOSE_ON_CLOSE);
        // Closing the login frame
        UserLoginGUI.this.dispose();
    }
}