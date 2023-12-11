import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Westminster Online Shopping System\n");
        String proceed = "Y";
        WestminsterShoppingManager shopManager = new WestminsterShoppingManager();
        shopManager.loadInfo();
        HomeGUI homeFrame = new HomeGUI(shopManager);
        // Settings for the frame
        homeFrame.setSize(900,600);
        homeFrame.setVisible(true);
        homeFrame.setDefaultCloseOperation(homeFrame.EXIT_ON_CLOSE);
        while (proceed.equals("Y")) {
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
            try {
                System.out.print("Please enter the number of the action you would like to perform: ");
                Scanner input = new Scanner(System.in);
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
//                        HomeGUI homeFrame = new HomeGUI(shopManager);
//                        // Settings for the frame
//                        homeFrame.setSize(900,600);
//                        homeFrame.setVisible(true);
//                        homeFrame.setDefaultCloseOperation(homeFrame.EXIT_ON_CLOSE);
                        break;
                    case 0:
                        System.out.println("\nThank you for using this program");
                        proceed = "N";
                        break;
                    default:
                        System.out.println("\nInvalid Option\n");
                        break;
                }
            } catch (InputMismatchException error) {
                System.out.println("\nInvalid Input\n");
            }
        }
    }
}