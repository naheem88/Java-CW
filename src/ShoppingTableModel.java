import javax.swing.table.AbstractTableModel;
import java.util.*;

public class ShoppingTableModel extends AbstractTableModel {
    private final String[] colNames = {"Product", "Quantity", "Price (£)"};
    private final List<Product> shoppingCart;
    private Map<String,Integer> productQuantityHashMap;
    public ShoppingTableModel(List<Product> userShoppingCart) {
        Set<Product> removeDuplicateProduct = new HashSet<>(userShoppingCart);
        shoppingCart = new ArrayList<>(removeDuplicateProduct);
        Collections.sort(shoppingCart);
        productQuantityHashMap = new HashMap<>();
        for (Product product: userShoppingCart) {
            int count = 0;
            for (Product product2: userShoppingCart) {
                if (product2.getProductId().equals(product.getProductId())) {
                    count++;
                }
            }
            productQuantityHashMap.put(product.getProductId(), count);
        }
    }

    @Override
    public int getRowCount() {
        return this.shoppingCart.size();
    }

    @Override
    public int getColumnCount() {
        return this.colNames.length;
    }

    @Override
    // Need to fix this
    public Object getValueAt(int rowNum, int colNum) {
        Object tableValue = null;
        String productId = this.shoppingCart.get(rowNum).getProductId();
        if (colNum == 0) {
            tableValue = productId + ", " + this.shoppingCart.get(rowNum).getProductInfo();
        } else if (colNum == 1) {
            tableValue = productQuantityHashMap.get(productId);
        } else if (colNum == 2) {
            tableValue = this.shoppingCart.get(rowNum).getProductPrice() + " £";
        }
        return tableValue;
    }

    @Override
    public String getColumnName(int colNum) {
        return this.colNames[colNum];
    }

    @Override
    public Class getColumnClass(int colNum) {
        if (colNum == 3) {
            return Double.class;
        } else {
            return String.class;
        }
    }
}