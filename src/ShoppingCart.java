import java.util.*;

public class ShoppingCart {
    // Instance Variables
    private List<Product> productList;
    private List<Product> duplicateProductList;
    private double totalPrice;
    private HashMap<String, Integer> productQuantityHashMap;

    public ShoppingCart() {
        this.productList = new ArrayList<>();
        this.duplicateProductList = new ArrayList<>();
        this.productQuantityHashMap = new HashMap<>();
    }

    public List<Product> getProductList() {
        return this.productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(Product product) {
        this.productList.add(product);
        this.duplicateProductList.add(product);
        getProductQuantity();
        removeDuplicateProduct();
    }

    public void removeProduct(String productId) {
        for (int count = 0; count < this.productList.size(); count++) {
            if (productId.equals((this.productList.get(count)).getProductId())) {
                this.productList.remove(count);
                break;
            }
        }
    }

    public void removeDuplicateProduct() {
        // Removing duplicate products
        Set<Product> removeDuplicateProductSet = new HashSet<>(this.productList);
        this.productList = new ArrayList<>(removeDuplicateProductSet);
        // Sorting the product list
        Collections.sort(this.productList);

    }

    public void getProductQuantity() {
        for (Product product: this.duplicateProductList) {
            int count = 0;
            // Counting the quantity of a product 
            for (Product product2: this.duplicateProductList) {
                if (product2.getProductId().equals(product.getProductId())) {
                    count++;
                }
            }
            productQuantityHashMap.put(product.getProductId(), count);
        }
    }

    public double totPrice() {
        for (Product product : this.productList) {
            // Calculating the total price of the products in the shopping cart
            double totProductPrice = ((product.getProductPrice()) * (productQuantityHashMap.get(product.getProductId())));
            this.totalPrice += totProductPrice;
        }
        return this.totalPrice;
    }

    public double categoryDiscount(double totalPrice) {
        for (Product product : this.productList) {
            if (productQuantityHashMap.get(product.getProductId()) >= 3) {
                return Math.round((totalPrice * 0.2));
            }
        }
        return 0;
    }

    public HashMap<String, Integer> getProductQuantityHashMap() {
        return productQuantityHashMap;
    }

    public void setProductQuantityHashMap(HashMap<String, Integer> productQuantityHashMap) {
        this.productQuantityHashMap = productQuantityHashMap;
    }

}