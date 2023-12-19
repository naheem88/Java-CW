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

    public ShoppingCartGUI(List<Product> userShoppingCart, HashMap<String, Integer> productQuantityHashMap) {
        this.setTitle("Shopping Cart");
        this.setLayout(new BorderLayout());
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCartTableModel = new ShoppingTableModel(userShoppingCart, productQuantityHashMap);
        shoppingCartTable = new JTable(shoppingCartTableModel);
        shoppingCartScrollPane = new JScrollPane(shoppingCartTable);
        shoppingCart.setProductQuantityHashMap(productQuantityHashMap);
        shoppingCart.setProductList(userShoppingCart);
        totPrice = new JLabel("Total Price: " + shoppingCart.totPrice() + " £");
        this.add(shoppingCartScrollPane, BorderLayout.NORTH);
        this.add(totPrice, BorderLayout.CENTER);
    }
}
