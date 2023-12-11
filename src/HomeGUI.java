import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class HomeGUI extends JFrame {
    JPanel topPanel;
    JPanel menu;
    JComboBox productTypeList;
    JButton cartBtn;
    ProductTableModel tableModel;
    JPanel selectedProductPanel;
    JTable productTable;
    JScrollPane scrollPane;
    JTextArea textArea;

    private List<Product> productList;
    private List<Product> electronicList;
    private List<Product> clothingList;

    public HomeGUI(WestminsterShoppingManager shopManager) {
        this.setTitle("Westminster Online Shopping Centre");
        this.setLayout(new BorderLayout());

        topPanel = new JPanel();
        menu = new JPanel();

        menu.add(new JLabel("Select Product Category"));
        productTypeList = new JComboBox<>(new String[] {"All", "Electronics", "Clothing"});
        menu.add(productTypeList);
        cartBtn = new JButton("Shopping Cart");

        topPanel.add(menu);
        topPanel.add(cartBtn);

        productList = shopManager.getProductList();
        electronicList = new ArrayList<>();
        clothingList = new ArrayList<>();

        for (Product product: productList) {
            if (product instanceof Electronic) {
                electronicList.add(product);
            } else if(product instanceof Clothing) {
                clothingList.add(product);
            }
        }

        // Making table model
        tableModel = new ProductTableModel(productList);
        productTable = new JTable(tableModel);
        scrollPane = new JScrollPane(productTable);

        // Selected Product Panel
        selectedProductPanel = new JPanel();
        selectedProductPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        textArea = new JTextArea(7, 1);

        // Adding the components to the frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(selectedProductPanel, BorderLayout.SOUTH);

        cartBtn.addMouseListener(new shpCartBtnHandler());
        productTable.addMouseListener(new productSelectedHandler());
        productTypeList.addItemListener(new productTypeHandler());
    }

    private class shpCartBtnHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            ShoppingCartGUI shopCartFrame = new ShoppingCartGUI();
            // Settings for the frame
            shopCartFrame.setSize(800,600);
            shopCartFrame.setVisible(true);
            shopCartFrame.setDefaultCloseOperation(shopCartFrame.DISPOSE_ON_CLOSE);
        }
    }

    private class productSelectedHandler extends MouseAdapter  {
        public void mouseClicked(MouseEvent event) {
            selectedProductPanel.remove(textArea);
            int clickedRow = productTable.rowAtPoint(event.getPoint());
            String productSelectedInfo = "Selected Product - Details\n" +
                    productList.get(clickedRow).toString() + "\n" +
                    productList.get(clickedRow).printProductInfo();
            textArea.setText(productSelectedInfo);
            textArea.setBackground(new Color(0, 0, 0, 0));
            selectedProductPanel.add(textArea);
        }
    }

    private class productTypeHandler implements ItemListener   {
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                if (productTypeList.getSelectedItem() == "All") {
                    tableModel = new ProductTableModel(productList);
                    productTable = new JTable(tableModel);
                    scrollPane = new JScrollPane(productTable);
                    add(scrollPane, BorderLayout.CENTER);
                } else if (productTypeList.getSelectedItem() == "Electronics") {
                    tableModel = new ProductTableModel(electronicList);
                    productTable = new JTable(tableModel);
                    scrollPane = new JScrollPane(productTable);
                    add(scrollPane, BorderLayout.CENTER);
                } else if (productTypeList.getSelectedItem() == "Clothing") {
                    tableModel = new ProductTableModel(clothingList);
                    productTable = new JTable(tableModel);
                    scrollPane = new JScrollPane(productTable);
                    add(scrollPane, BorderLayout.CENTER);
                }
            }
        }
    }
}
