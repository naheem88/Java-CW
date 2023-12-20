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
    ProductTableModel mainTableModel;
    JTable productTable;
    JScrollPane mainScrollPane;
    JPanel selectedProductPanel;
    JTextArea textArea;
    JButton addToShoppingCartBtn;

    private List<Product> productList;
    private List<Product> electronicList;
    private List<Product> clothingList;
    private Product selectedProduct;
    private ShoppingCart userShoppingCart;

    public HomeGUI(WestminsterShoppingManager shopManager) {
        this.setTitle("Westminster Online Shopping Centre");
        userShoppingCart = new ShoppingCart();
        this.setLayout(new BorderLayout());

        topPanel = new JPanel();
        menu = new JPanel();

        menu.add(new JLabel("Select Product Category"));
        productTypeList = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        menu.add(productTypeList);
        cartBtn = new JButton("Shopping Cart");

        topPanel.add(menu);
        topPanel.add(cartBtn);

        productList = shopManager.getProductList();
        electronicList = new ArrayList<>();
        clothingList = new ArrayList<>();

        for (Product product : productList) {
            if (product instanceof Electronic) {
                electronicList.add(product);
            } else if (product instanceof Clothing) {
                clothingList.add(product);
            }
        }

        // Making table model
        createTable(productList);

        // Selected Product Panel
        selectedProductPanel = new JPanel();
        selectedProductPanel.setLayout(new FlowLayout());
        textArea = new JTextArea(7,1);
        textArea.setBackground(new Color(0, 0, 0, 0));
        textArea.setEditable(false);
        selectedProductPanel.add(textArea);
        addToShoppingCartBtn = new JButton("Add to Shopping Cart");

        // Adding the components to the frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(selectedProductPanel, BorderLayout.SOUTH);
        cartBtn.addMouseListener(new shpCartBtnHandler());
        productTypeList.addItemListener(new productTypeHandler());
        addToShoppingCartBtn.addMouseListener(new addToShoppingCartHandler());
    }

    private class shpCartBtnHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            ShoppingCartGUI shopCartFrame = new ShoppingCartGUI(userShoppingCart.getProductList(), userShoppingCart.getProductQuantityHashMap(), HomeGUI.this);
            // Settings for the frame
            shopCartFrame.setSize(800, 600);
            shopCartFrame.setVisible(true);
            shopCartFrame.setDefaultCloseOperation(shopCartFrame.DISPOSE_ON_CLOSE);
        }
    }

    private class productSelectedHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            int clickedRow = productTable.rowAtPoint(event.getPoint());
            if (clickedRow != -1) {
                Object productId = productTable.getModel().getValueAt(clickedRow, 0);
                for (Product product : productList) {
                    if (productId.equals(product.getProductId())) {
                        String productSelectedInfo = "Selected Product - Details\n" + product + "\n" + product.printProductInfo();
                        textArea.setText(productSelectedInfo);
                        selectedProductPanel.add(addToShoppingCartBtn);
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
            if (event.getStateChange() == ItemEvent.SELECTED) {
                HomeGUI.this.remove(mainScrollPane);
                textArea.setText("");
                selectedProductPanel.remove(addToShoppingCartBtn);
                HomeGUI.this.repaint();
                if (productTypeList.getSelectedItem() == "All") {
                    createTable(productList);
                } else if (productTypeList.getSelectedItem() == "Electronics") {
                    createTable(electronicList);
                } else if (productTypeList.getSelectedItem() == "Clothing") {
                    createTable(clothingList);
                }
            }
        }
    }

    private class addToShoppingCartHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            userShoppingCart.addProduct(selectedProduct);
        }
    }

    private void createTable(List<Product> productList) {
        mainTableModel = new ProductTableModel(productList);
        productTable = new JTable(mainTableModel);
        mainScrollPane = new JScrollPane(productTable);
        this.add(mainScrollPane, BorderLayout.CENTER);
        productTable.addMouseListener(new productSelectedHandler());
    }
}


