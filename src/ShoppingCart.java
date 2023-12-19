import java.util.*;

public class ShoppingCart {
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
        Set<Product> removeDuplicateProductSet = new HashSet<>(this.productList);
        this.productList = new ArrayList<>(removeDuplicateProductSet);
        Collections.sort(this.productList);

    }

    public void getProductQuantity() {
        for (Product product: this.duplicateProductList) {
            int count = 0;
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
            this.totalPrice += product.getProductPrice();
        }
        return this.totalPrice;
    }

    public HashMap<String, Integer> getProductQuantityHashMap() {
        return productQuantityHashMap;
    }
}
