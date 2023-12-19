import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class ShoppingCartGUI extends JFrame{
    ShoppingTableModel shoppingCartTableModel;
    JTable shoppingCartTable;
    JScrollPane shoppingCartScrollPane;

    public ShoppingCartGUI(List<Product> userShoppingCart, HashMap<String, Integer> productQuantityHashMap) {
        this.setTitle("Shopping Cart");
        this.setLayout(new BorderLayout());
        shoppingCartTableModel = new ShoppingTableModel(userShoppingCart, productQuantityHashMap);
        shoppingCartTable = new JTable(shoppingCartTableModel);
        shoppingCartScrollPane = new JScrollPane(shoppingCartTable);
        this.add(shoppingCartScrollPane, BorderLayout.CENTER);
    }
}
