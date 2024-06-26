import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {
    private List<Product> productList;

    public WestminsterShoppingManager() {
        this.productList = new ArrayList<>();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProduct() {
        Scanner input = new Scanner(System.in);
        // Checking if the system has reached it's maximum capacity
        if (this.productList.size() >= 50) {
            System.out.println("\nThe system has reached it's maximum capacity");
            System.out.println("Can not add anymore products\n");
            return;
        }
        // Getting the product information
        System.out.println("\nProduct Information\n");
        System.out.print("Product ID: ");
        String productId = input.next().stripLeading().stripTrailing();
        // Checking if the product ID already exists
        for(Product product: this.productList) {
            if (productId.equals(product.getProductId())) {
                System.out.println("\nA product with this ID already exists\n");
                return;
            }
        }
        System.out.print("Product Name: ");
        String productName = input.next();
        System.out.print("Number of Available Items: ");
        int numOfAvailItem = input.nextInt();
        System.out.print("Price (£): ");
        double price = input.nextDouble();
        System.out.print("Type of Product(Electronics = 1 or Clothing = 2): ");
        int typeOfProduct = input.nextInt();
        // Checking the type of product
        if (typeOfProduct == 1) {
            System.out.print("Brand Name: ");
            String brandName = input.next();
            System.out.print("Warranty Period (Weeks): ");
            String warrantyPeriod = input.next() + " Weeks Warranty";
            Product electronicProduct = new Electronic(productId, productName, numOfAvailItem, price, brandName, warrantyPeriod);
            this.productList.add(electronicProduct);
        } else if (typeOfProduct == 2) {
            System.out.print("Size: ");
            String size = input.next();
            System.out.print("Colour: ");
            String colour = input.next();
            Product clothingProduct = new Clothing(productId, productName, numOfAvailItem, price, size, colour);
            this.productList.add(clothingProduct);
        } else {
            System.out.println("\nInvalid product type");
            return;
        }
        System.out.println("\nThe product has been added successfully\n");
    }

    public void removeProduct() {
        Scanner input = new Scanner(System.in);
        System.out.print("Product ID: ");
        String productId = input.next().stripLeading().stripTrailing();
        for (int count = 0; count < this.productList.size(); count++) {
            if (productId.equals((this.productList.get(count)).getProductId())) {
                // Removing the product
                printInfo(this.productList.get(count));
                this.productList.remove(count);
                System.out.println("\nNumber of products remaining: " + this.productList.size() + "\n");
                break;
                }
            if (count == (this.productList.size() - 1)) {
                System.out.println("\nThis product could not be found\n");
            }
        }
    }

    public void printInfo(Product product) {
        // Printing the product information
        System.out.println("\n----------------------------------------------------------------------------------------------------------\n");
        System.out.println("                                            -----------------------");
        System.out.println("                                            | Product Information |");
        System.out.println("                                            -----------------------\n");
        System.out.println(product.toString() + "\n" +
                "Type of Product: " + product.getProductType() + "\n" + "\n" +
                product.printProductInfo());
                System.out.println();
                System.out.println("----------------------------------------------------------------------------------------------------------");
    }

    public void saveToFile() {
        try {
            // Saving the product information into a text file
            FileWriter textWriter = new FileWriter("ProductInfo.txt");
            // Writing the product information into the text file
            for (Product product: this.getProductList()) {
                textWriter.write(product.getProductId() + "," +
                        product.getProductName() + "," +
                        product.getNumOfAvailItem() + "," +
                        product.getProductPrice() + "," +
                        product.getProductType() + "," +
                        product.getProductInfo());
                // Adding a new line after each product
                textWriter.write(System.lineSeparator());
            }
            textWriter.close();
            System.out.println("\nThe product information has been saved into the file successfully\n");
        } catch (IOException exception) {
            System.out.println("\nAn error has occurred while saving the product information into the text file\n");
        }
    }

    public void loadInfo() {
        try {
            // Loading the product information from the text file
            FileReader file = new FileReader("ProductInfo.txt");
            Scanner textReader = new Scanner(file);
            while (textReader.hasNextLine()) {
                String textLine = textReader.nextLine();
                String[] fileData = textLine.split(",");
                // Checking if the file is empty
                if (fileData[0].equals("")) {
                    return;
                }
                String productId = fileData[0];
                String productName = fileData[1];
                int numOfAvailItem = Integer.parseInt(fileData[2]);
                double price = Double.parseDouble(fileData[3]);
                String productType = fileData[4];
                if (productType.equals("Electronic")) {
                    String brandName = fileData[5];
                    String warrantyPeriod = fileData[6].stripLeading();
                    Product electronicProduct = new Electronic(productId, productName, numOfAvailItem, price, brandName, warrantyPeriod);
                    this.productList.add(electronicProduct);
                } else {
                    String size = fileData[5];
                    String colour = fileData[6].stripLeading();
                    Product clothingProduct = new Clothing(productId, productName, numOfAvailItem, price, size, colour);
                    this.productList.add(clothingProduct);
                }
            }
            file.close();
            System.out.println("Information retrieved from text file\n");
        } catch (IOException exception) {
            System.out.println("\nAn error has occurred loading the product information\n");
        }
    }

    public void sortAndPrintProductList() {
        // Sorting the product list
        Collections.sort(this.productList);
        for (Product product: this.productList) {
            this.printInfo(product);
        }
        System.out.println();
    }
}