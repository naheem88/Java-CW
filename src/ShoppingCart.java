import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> productList;
    private double totalPrice;
    public ShoppingCart() {
        this.productList = new ArrayList<>();
    }

    public List<Product> getProductList() {
        return this.productList;
    }

    public void addProduct(Product product) {
        this.productList.add(product);
    }

    public void removeProduct(String productId) {
        for (int count = 0; count < this.productList.size(); count++) {
            if (productId.equals((this.productList.get(count)).getProductId())) {
                this.productList.remove(count);
                break;
            }
        }
    }

    public double totPrice() {
        for (Product product : this.productList) {
            this.totalPrice += product.getProductPrice();
        }
        return this.totalPrice;
    }
}
