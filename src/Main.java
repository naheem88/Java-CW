import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Westminster Online Shopping System\n");
        String proceed = "Y";
        WestminsterShoppingManager shopManager = new WestminsterShoppingManager();
        shopManager.loadInfo();
        while (proceed.equals("Y")) {
            try {
                System.out.print("Are you a User (1) or Manager (2): ");
                Scanner input = new Scanner(System.in);
                int user = input.nextInt();
                input = new Scanner(System.in);
                if (user == 1) {
                    UserLoginGUI userLoginFrame = new UserLoginGUI(shopManager);
                    // Settings for the frame
                    userLoginFrame.setSize(480,280);
                    userLoginFrame.setVisible(true);
                    userLoginFrame.setDefaultCloseOperation(userLoginFrame.DISPOSE_ON_CLOSE);
                    System.out.println();
                    System.out.print("Do you want to access the shopping system again (Yes = 1 / No = 2): ");
                    int userProceed = input.nextInt();
                    if (userProceed == 2) {
                        proceed = "N";
                    }
                } else if (user == 2) {
                    System.out.print("""
                    Please select an option:
                    1) Add Product
                    2) Remove Product
                    3) Print list of products
                    4) Save to file
                    5) View GUI
                    0) Quit
                    """);
                    System.out.println();
                    System.out.print("Please enter the number of the action you would like to perform: ");
                    int userChoice = input.nextInt();
                    switch (userChoice) {
                        case 1:
                            shopManager.addProduct();
                            break;
                        case 2:
                            shopManager.removeProduct();
                            break;
                        case 3:
                            shopManager.sortAndPrintProductList();
                            break;
                        case 4:
                            shopManager.saveToFile();
                            break;
                        case 5:
                            UserLoginGUI userLoginFrame = new UserLoginGUI(shopManager);
                            // Settings for the frame
                            userLoginFrame.setSize(480,280);
                            userLoginFrame.setVisible(true);
                            userLoginFrame.setDefaultCloseOperation(userLoginFrame.DISPOSE_ON_CLOSE);
                            break;
                        case 0:
                            System.out.println("\nThank you for using this program");
                            proceed = "N";
                            break;
                        default:
                            System.out.println("\nInvalid Option\n");
                            break;
                    }
                } else {
                    System.out.println("\nInvalid Option\n");
                }
            } catch (InputMismatchException error) {
                System.out.println("\nInvalid Input\n");
            }
        }
    }
}