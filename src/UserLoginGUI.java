import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class UserLoginGUI extends JFrame {

    JLabel usernameLabel;
    JLabel passwordLabel;
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
            HashMap<String, String> checkUserInfo;
            checkUserInfo = userInformation.getRegularUserHashMap();
            if ((username.equals("")) || (password.equals(""))) {
                newUserLoginFrame("Please fill all the information");
            } else {
                if (checkUserInfo.containsKey(username)) {
                    System.out.println(username);
                    System.out.println(checkUserInfo.get(username));
                    if (checkUserInfo.get(username).equals(password)) {
                        HomeGUI homeFrame = new HomeGUI(shoppingManager);
                        // Settings for the frame
                        homeFrame.setSize(900,600);
                        homeFrame.setVisible(true);
                        homeFrame.setDefaultCloseOperation(homeFrame.DISPOSE_ON_CLOSE);
                        UserLoginGUI.this.dispose();
                    } else {
                        newUserLoginFrame("Incorrect Password");
                    }
                }
                HomeGUI homeFrame = new HomeGUI(shoppingManager);
                // Settings for the frame
                homeFrame.setSize(900,600);
                homeFrame.setVisible(true);
                homeFrame.setDefaultCloseOperation(homeFrame.DISPOSE_ON_CLOSE);
                UserLoginGUI.this.dispose();

            }
        }
    }
    private void newUserLoginFrame(String message) {
        UserLoginGUI userLoginFrameNew = new UserLoginGUI(shoppingManager);
        JDialog dialog = new JDialog();
        dialog.add(userLoginFrameNew, message);
        // Settings for the frame
        userLoginFrameNew.setSize(480,280);
        userLoginFrameNew.setVisible(true);
        userLoginFrameNew.setDefaultCloseOperation(userLoginFrameNew.DISPOSE_ON_CLOSE);
        UserLoginGUI.this.dispose();
    }


}