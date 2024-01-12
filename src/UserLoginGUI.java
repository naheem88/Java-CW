import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserLoginGUI extends JFrame {

    JLabel usernameLabel;
    JLabel passwordLabel;
    JDialog dialogBox;
    JLabel dialogBoxLabel;
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
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            User userInformation = new User(username, password);
            if (userInformation.getUserNameAndPasswordHashMap().containsKey(username)) {
                if (userInformation.getUserNameAndPasswordHashMap().get(username).equals(password)) {
                    createHomeFrame();
                } else {
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
        HomeGUI homeFrame = new HomeGUI(shoppingManager);
        // Settings for the frame
        homeFrame.setSize(900,600);
        homeFrame.setVisible(true);
        homeFrame.setDefaultCloseOperation(homeFrame.DISPOSE_ON_CLOSE);
        UserLoginGUI.this.dispose();
    }
}