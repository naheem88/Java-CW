public abstract class Product implements Comparable<Product>{
    protected String productId;
    protected String productName;
    protected int numOfAvailItem;
    protected double productPrice;

    public Product(String productId, String productName, int numOfAvailItem, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.numOfAvailItem = numOfAvailItem;
        this.productPrice = productPrice;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public int getNumOfAvailItem() {
        return this.numOfAvailItem;
    }

    public double getProductPrice() {
        return this.productPrice;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setNumOfAvailItem(int numOfAvailItem) {
        this.numOfAvailItem = numOfAvailItem;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public abstract String getProductType();

    public abstract String getProductInfo();

    public abstract String printProductInfo();

    @Override
    public String toString() {
        return "Product ID: " + this.productId + "\n" + "\n" +
                "Product Name: " + this.productName + "\n" + "\n" +
                "Number of Available items: " + this.numOfAvailItem + "\n" + "\n" +
                "Product Price: £" + this.productPrice + "\n";
    }

    @Override
    public int compareTo(Product obj) {
        return this.productId.compareTo(obj.productId);
    }
}