import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class HomeGUI extends JFrame {
    // GUI Components
    JPanel topPanel;
    JPanel menu;
    JComboBox<String> productTypeList;
    JButton cartBtn;
    ProductTableModel mainTableModel;
    JTable productTable;
    JScrollPane mainScrollPane;
    JPanel selectedProductPanel;
    JTextArea textArea;
    JButton addToShoppingCartBtn;
    JPanel productBtnPanel;

    // Instance Variables
    private List<Product> productList;
    private List<Product> electronicList;
    private List<Product> clothingList;
    private Product selectedProduct;
    private ShoppingCart userShoppingCart;

    public HomeGUI(WestminsterShoppingManager shopManager) {
        this.setTitle("Westminster Online Shopping Centre");
        userShoppingCart = new ShoppingCart();
        this.setLayout(new BorderLayout());

        // Top Panel
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 0, 70, 10);
        topPanel = new JPanel();
        topPanel.setBorder(emptyBorder);
        topPanel.setLayout(new BorderLayout());

        // Menu Panel
        menu = new JPanel();
        menu.add(new JLabel("Select Product Category"));
        // Creating the combo box
        productTypeList = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        menu.add(productTypeList);

        // Shopping Cart Button
        cartBtn = new JButton("Shopping Cart");

        // Adding the components to the top panel
        topPanel.add(cartBtn, BorderLayout.EAST);
        topPanel.add(menu, BorderLayout.CENTER);

        productList = shopManager.getProductList();
        electronicList = new ArrayList<>();
        clothingList = new ArrayList<>();

        // Creating lists for the electronic and clothing products
        for (Product product : productList) {
            if (product instanceof Electronic) {
                electronicList.add(product);
            } else if (product instanceof Clothing) {
                clothingList.add(product);
            }
        }

        // Making table model
        mainTableModel = new ProductTableModel(productList);
        productTable = new JTable(mainTableModel);
        mainScrollPane = new JScrollPane(productTable);
        this.add(mainScrollPane, BorderLayout.CENTER);

        // Selected Product Panel
        selectedProductPanel = new JPanel();
        selectedProductPanel.setLayout(new BorderLayout());
        textArea = new JTextArea(7,1);
        textArea.setBackground(new Color(0, 0, 0, 0));
        textArea.setEditable(false);

        selectedProductPanel.add(textArea, BorderLayout.WEST);
        addToShoppingCartBtn = new JButton("Add to Shopping Cart");
        
        // Product Button Panel
        productBtnPanel = new JPanel();
        productBtnPanel.add(new JLabel(), BorderLayout.WEST);
        productBtnPanel.add(new JLabel(), BorderLayout.EAST);
        
        // Border for the selected product panel
        Border emptyBorder2 = BorderFactory.createEmptyBorder(40, 20, 10, 0);
        selectedProductPanel.setBorder(emptyBorder2);

        // Adding the components to the frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(selectedProductPanel, BorderLayout.SOUTH);

        // Adding the event handlers
        cartBtn.addMouseListener(new shpCartBtnHandler());
        productTypeList.addItemListener(new productTypeHandler());
        productTable.addMouseListener(new productSelectedHandler());
        addToShoppingCartBtn.addMouseListener(new addToShoppingCartHandler());
    }
    
    private class shpCartBtnHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            // Creating the shopping cart frame
            ShoppingCartGUI shopCartFrame = new ShoppingCartGUI(userShoppingCart.getProductList(), userShoppingCart.getProductQuantityHashMap(), HomeGUI.this);
            // Settings for the frame
            shopCartFrame.setSize(800, 600);
            shopCartFrame.setVisible(true);
            shopCartFrame.setDefaultCloseOperation(shopCartFrame.DISPOSE_ON_CLOSE);
        }
    }

    private class productSelectedHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            // Getting the row that was clicked
            int clickedRow = productTable.rowAtPoint(event.getPoint());
            if (clickedRow != -1) {
                // Getting the product id of the product that was clicked
                Object productId = productTable.getModel().getValueAt(clickedRow, 0);
                for (Product product : productList) {
                    if (productId.equals(product.getProductId())) {
                        String productSelectedInfo = "Selected Product - Details\n" + "\n" + product + "\n" + product.printProductInfo();
                        textArea.setText(productSelectedInfo);
                        selectedProductPanel.add(productBtnPanel, BorderLayout.SOUTH);
                        productBtnPanel.add(addToShoppingCartBtn, BorderLayout.CENTER);
                        selectedProduct = product;
                        // Refreshes the JFrame
                        HomeGUI.this.repaint();
                        break;
                    }
                }
            }
        }
    }

    private class productTypeHandler implements ItemListener {
        public void itemStateChanged(ItemEvent event) {
            // If the user selects a different product type, the table is updated
            if (event.getStateChange() == ItemEvent.SELECTED) {
                textArea.setText("");
                selectedProductPanel.remove(productBtnPanel);
                HomeGUI.this.repaint();
                if (productTypeList.getSelectedItem().equals("All")) {
                    mainTableModel.updateTable(productList);
                } else if (productTypeList.getSelectedItem().equals("Electronics")) {
                    mainTableModel.updateTable(electronicList);
                } else if (productTypeList.getSelectedItem().equals("Clothing")) {
                    mainTableModel.updateTable(clothingList);
                }
                // Refreshes the JFrame
                HomeGUI.this.revalidate();
                HomeGUI.this.repaint();
            }
        }
    }

    private class addToShoppingCartHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            // Adds the selected product to the shopping cart
            userShoppingCart.addProduct(selectedProduct);
        }
    }
}