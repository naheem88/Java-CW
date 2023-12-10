public class Clothing extends Product{
    private String size;
    private String colour;

    public Clothing(String productId, String productName, int numOfAvailItem, double productPrice, String size, String colour) {
        super(productId, productName, numOfAvailItem, productPrice);
        this.size = size;
        this.colour = colour;
    }

    public String getSize() {
        return this.size;
    }

    public String getColour() {
        return this.colour;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String getProductType() {
        return "Clothing";
    }

    @Override
    public String getProductInfo() {
        return this.size + ", " + this.colour;
    }

    @Override
    public String printProductInfo() {
        return "Size: " + this.size + "\n" + "Colour: " + this.colour;
    }
}
