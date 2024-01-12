import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;

public class ShoppingCartGUI extends JFrame{
    JPanel mainPanel;
    ShoppingTableModel shoppingCartTableModel;
    JTable shoppingCartTable;
    JScrollPane shoppingCartScrollPane;
    JLabel totPrice;
    JLabel categoryDiscount;
    JLabel newUserDiscount;
    JLabel finalTotal;
    JButton buyBtn;
    HomeGUI homeGUI;
    private List<Product> userCart;

    public ShoppingCartGUI(List<Product> userShoppingCart, HashMap<String, Integer> productQuantityHashMap, HomeGUI homeGUI) {
        this.homeGUI = homeGUI;
        this.setTitle("Shopping Cart");
        this.setLayout(new BorderLayout());
        ShoppingCart shoppingCart = new ShoppingCart();
        userCart = userShoppingCart;
    
        shoppingCartTableModel = new ShoppingTableModel(userCart, productQuantityHashMap);
        shoppingCartTable = new JTable(shoppingCartTableModel);
        shoppingCartScrollPane = new JScrollPane(shoppingCartTable);

        shoppingCart.setProductQuantityHashMap(productQuantityHashMap);
        shoppingCart.setProductList(userCart);

        double totalPrice = shoppingCart.totPrice();
        double categoryDiscountPrice = shoppingCart.categoryDiscount(totalPrice);
        double newUserDiscountPrice = 0;

        User userInfo = new User();
        if (userInfo.newUser()) {
            newUserDiscountPrice = Math.round((totalPrice * 0.1));
        } else {
            newUserDiscountPrice = 0;
        }

        double finalTotPrice = totalPrice - (newUserDiscountPrice + categoryDiscountPrice);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2));
        
        
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel("Total Price " + "                                                                         " + totalPrice + " £"));

        
        mainPanel.add(new JLabel()); 
        mainPanel.add(new JLabel("First Purchase Discount (10%)                                      " + "- " + newUserDiscountPrice + " £"));

        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel("Three Items in the same Category Discount (20%)    " + "- " + categoryDiscountPrice + " £"));
        
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel("Final Total" + "                                                                           " + finalTotPrice + " £"));

        buyBtn = new JButton("Buy");
        JPanel buyPanel = new JPanel();
        buyPanel.add(new JLabel(), BorderLayout.WEST);
        buyPanel.add(new JLabel(), BorderLayout.EAST);
        buyPanel.add(buyBtn, BorderLayout.CENTER);

        this.add(shoppingCartScrollPane, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buyPanel, BorderLayout.SOUTH);
        buyBtn.addMouseListener(new buyBtnHandler());
    }

    private class buyBtnHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            if (userCart.size() >= 1) {
                User userInfo = new User();
                if (userInfo.newUser()) {
                    userInfo.saveToFile();
                }
                ShoppingCartGUI.this.dispose();
                homeGUI.dispose();
            }
        }
    }
}