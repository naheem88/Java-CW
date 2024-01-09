import javax.swing.table.AbstractTableModel;
import java.util.*;

public class ShoppingTableModel extends AbstractTableModel {
    private final String[] colNames = {"Product", "Quantity", "Price (£)"};
    private final List<Product> shoppingCart;
    private Map<String,Integer> productQuantityHashMap;

    public ShoppingTableModel(List<Product> userShoppingCart, HashMap<String, Integer> productQuantityHashMap) {
        this.shoppingCart = userShoppingCart;
        this.productQuantityHashMap = productQuantityHashMap;
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
    public Object getValueAt(int rowNum, int colNum) {
        Object tableValue = null;
        String productId = this.shoppingCart.get(rowNum).getProductId();
        int productQuantity = this.productQuantityHashMap.get(productId);
        if (colNum == 0) {
            tableValue = productId + ", " + this.shoppingCart.get(rowNum).getProductInfo();
        } else if (colNum == 1) {
            tableValue = productQuantity;
        } else if (colNum == 2) {
            tableValue = ((this.shoppingCart.get(rowNum).getProductPrice()) * productQuantity) + " £";
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