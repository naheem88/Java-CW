import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShoppingCartGUI extends JFrame{
    ShoppingTableModel shoppingCartTableModel;
    JTable shoppingCartTable;
    JScrollPane shoppingCartScrollPane;
    List<Product> userShoppingCart;

    public ShoppingCartGUI(List<Product> userShoppingCart) {
        this.setTitle("Shopping Cart");
        this.setLayout(new BorderLayout());
        this.userShoppingCart = userShoppingCart;
        shoppingCartTableModel = new ShoppingTableModel(userShoppingCart);
        shoppingCartTable = new JTable(shoppingCartTableModel);
        shoppingCartScrollPane = new JScrollPane(shoppingCartTable);
        this.add(shoppingCartScrollPane, BorderLayout.CENTER);
    }
}
