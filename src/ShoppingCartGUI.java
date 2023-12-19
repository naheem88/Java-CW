import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class ShoppingCartGUI extends JFrame{
    JPanel mainPanel;
    ShoppingTableModel shoppingCartTableModel;
    JTable shoppingCartTable;
    JScrollPane shoppingCartScrollPane;
    JLabel totPrice;
    JLabel categoryDiscount;

    public ShoppingCartGUI(List<Product> userShoppingCart, HashMap<String, Integer> productQuantityHashMap) {
        this.setTitle("Shopping Cart");
        this.setLayout(new BorderLayout());
        ShoppingCart shoppingCart = new ShoppingCart();
        mainPanel = new JPanel(new FlowLayout());
        shoppingCartTableModel = new ShoppingTableModel(userShoppingCart, productQuantityHashMap);
        shoppingCartTable = new JTable(shoppingCartTableModel);
        shoppingCartScrollPane = new JScrollPane(shoppingCartTable);
        shoppingCart.setProductQuantityHashMap(productQuantityHashMap);
        shoppingCart.setProductList(userShoppingCart);
        double totalPrice = shoppingCart.totPrice();
        double categoryDiscountPrice = shoppingCart.categoryDiscount(totalPrice);
        totPrice = new JLabel("Total Price: " + totalPrice + " £");
        categoryDiscount = new JLabel("Three Items in the same Category Discount: " + categoryDiscountPrice + " £");
        mainPanel.add(totPrice);
        mainPanel.add(categoryDiscount);
        this.add(shoppingCartScrollPane, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
    }
}
