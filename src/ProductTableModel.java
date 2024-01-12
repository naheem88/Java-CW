import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProductTableModel extends AbstractTableModel {
    // Instance Variables
    private final String[] colNames = {"Product ID", "Name", "Category", "Price (£)", "Info"};
    private List<Product> productTableList;

    public ProductTableModel(List<Product> productArrayList) {
        this.productTableList = productArrayList;
    }

    // Method to update the table
    public void updateTable(List<Product> newArrayList) {
        productTableList = newArrayList;
        // Notifies the table that the data has changed
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.productTableList.size();
    }

    @Override
    public int getColumnCount() {
        return this.colNames.length;
    }

    @Override
    public Object getValueAt(int rowNum, int colNum) {
        Object tableValue = null;
        if (colNum == 0) {
            tableValue = this.productTableList.get(rowNum).getProductId();
        } else if (colNum == 1) {
            tableValue = this.productTableList.get(rowNum).getProductName();
        } else if (colNum == 2) {
            tableValue = this.productTableList.get(rowNum).getProductType();
        } else if (colNum == 3) {
            tableValue = this.productTableList.get(rowNum).getProductPrice();
        } else if (colNum == 4){
            tableValue = this.productTableList.get(rowNum).getProductInfo();
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