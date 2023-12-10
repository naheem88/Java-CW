import java.util.List;

public interface ShoppingManager {

    List<Product> getProductList();
    void addProduct();

    void removeProduct();

    void printInfo(Product product);

    void saveToFile();

    void loadInfo();

    void sortAndPrintProductList();

}
