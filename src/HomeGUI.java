import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeGUI extends JFrame {
    JPanel topPanel;
    JPanel menu;
    JButton cartBtn;
    ProductTableModel tableModel;
    JPanel selectedProductPanel;
    JTable productTable;
    JScrollPane scrollPane;
    JTextField selectedProductInfo;
    public HomeGUI(WestminsterShoppingManager shopManager) {
        this.setTitle("Westminster Online Shopping Centre");
        this.setLayout(new BorderLayout());

        topPanel = new JPanel();
        menu = new JPanel();

        menu.add(new JLabel("Select Product Category"));
        menu.add(new JComboBox(new String[] {"All", "Electronics", "Clothing"}));
        cartBtn = new JButton("Shopping Cart");

        topPanel.add(menu);
        topPanel.add(cartBtn);

        // Making table model
        tableModel = new ProductTableModel(shopManager.getProductList());
        productTable = new JTable(tableModel);
        scrollPane = new JScrollPane(productTable);

        // Selected Product Panel
        selectedProductPanel = new JPanel();
        selectedProductPanel.add(new JLabel("Selected Product - Details"));
        selectedProductInfo = new JTextField("Product Id: " + productTable);

        // Adding the components to the frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(selectedProductPanel, BorderLayout.SOUTH);

        shpCartBtnHandler actionHandler = new shpCartBtnHandler();

        cartBtn.addActionListener(actionHandler);


        //selectedProductPanel.add(new JLabel())
    }
    public void getSelectedProductInfo() {}

    private class shpCartBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            ShoppingCartGUI shopCartFrame = new ShoppingCartGUI();
            // Settings for the frame
            shopCartFrame.setSize(800,600);
            shopCartFrame.setVisible(true);
            shopCartFrame.setDefaultCloseOperation(shopCartFrame.DISPOSE_ON_CLOSE);
        }
    }
}
