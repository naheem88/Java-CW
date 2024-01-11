public class Electronic extends Product {
    private String brandName;
    private String warrantyPeriod;

    public Electronic(String productId, String productName, int numOfAvailItem, double productPrice, String brandName, String warrantyPeriod) {
        super(productId, productName, numOfAvailItem, productPrice);
        this.brandName = brandName;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String getProductType() {
        return "Electronic";
    }

    @Override
    public String getProductInfo() {
        return this.brandName + ", " + this.warrantyPeriod;
    } 

    @Override
    public String printProductInfo() {
        return "Brand Name: " + this.brandName + "\n" + "\n" +  "Warranty Period: " + this.warrantyPeriod;
    }
}