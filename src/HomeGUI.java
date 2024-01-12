import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class HomeGUI extends JFrame {
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

    private List<Product> productList;
    private List<Product> electronicList;
    private List<Product> clothingList;
    private Product selectedProduct;
    private ShoppingCart userShoppingCart;

    public HomeGUI(WestminsterShoppingManager shopManager) {
        this.setTitle("Westminster Online Shopping Centre");
        userShoppingCart = new ShoppingCart();
        this.setLayout(new BorderLayout());

        Border emptyBorder = BorderFactory.createEmptyBorder(10, 0, 70, 10);

        topPanel = new JPanel();
        topPanel.setBorder(emptyBorder);
        menu = new JPanel();

        topPanel.setLayout(new BorderLayout());
        menu.add(new JLabel("Select Product Category"));
        productTypeList = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        menu.add(productTypeList);

        cartBtn = new JButton("Shopping Cart");

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
        
        productBtnPanel = new JPanel();

        productBtnPanel.add(new JLabel(), BorderLayout.WEST);
        productBtnPanel.add(new JLabel(), BorderLayout.EAST);
        

        Border emptyBorder2 = BorderFactory.createEmptyBorder(40, 20, 10, 0);

        selectedProductPanel.setBorder(emptyBorder2);

        // Adding the components to the frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(selectedProductPanel, BorderLayout.SOUTH);
        cartBtn.addMouseListener(new shpCartBtnHandler());
        productTypeList.addItemListener(new productTypeHandler());
        productTable.addMouseListener(new productSelectedHandler());
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
                HomeGUI.this.revalidate();
                HomeGUI.this.repaint();
            }
        }
    }

    private class addToShoppingCartHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            userShoppingCart.addProduct(selectedProduct);
        }
    }
}