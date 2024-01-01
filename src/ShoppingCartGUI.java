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

    public ShoppingCartGUI(List<Product> userShoppingCart, HashMap<String, Integer> productQuantityHashMap, HomeGUI homeGUI) {
        this.homeGUI = homeGUI;
        this.setTitle("Shopping Cart");
        this.setLayout(new BorderLayout());
        ShoppingCart shoppingCart = new ShoppingCart();
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 4));

        shoppingCartTableModel = new ShoppingTableModel(userShoppingCart, productQuantityHashMap);
        shoppingCartTable = new JTable(shoppingCartTableModel);
        shoppingCartScrollPane = new JScrollPane(shoppingCartTable);

        shoppingCart.setProductQuantityHashMap(productQuantityHashMap);
        shoppingCart.setProductList(userShoppingCart);

        double totalPrice = shoppingCart.totPrice();
        double categoryDiscountPrice = shoppingCart.categoryDiscount(totalPrice);
        double newUserDiscountPrice = 0;

        User userInfo = new User();
        if (userInfo.newUser()) {
            newUserDiscountPrice = Math.round((totalPrice * 0.1));
        } else {
            newUserDiscountPrice = 0;
        }
        newUserDiscount = new JLabel("First Purchase Discount (10%)");

        double finalTotPrice = totalPrice - (newUserDiscountPrice + categoryDiscountPrice);

        totPrice = new JLabel("Total Price");
        categoryDiscount = new JLabel("Three Items in the same Category Discount (20%)");
        finalTotal = new JLabel("Final Total");

        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(totPrice);
        mainPanel.add(new JLabel("  " + totalPrice + " £"));
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(newUserDiscount);
        mainPanel.add(new JLabel("- " + newUserDiscountPrice + " £"));
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(categoryDiscount);
        mainPanel.add(new JLabel("- " + categoryDiscountPrice + " £"));
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(finalTotal);
        mainPanel.add(new JLabel("  " + finalTotPrice + " £"));

        buyBtn = new JButton("Buy");

        this.add(shoppingCartScrollPane, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buyBtn, BorderLayout.SOUTH);
        buyBtn.addMouseListener(new buyBtnHandler());
    }

    private class buyBtnHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            User userInfo = new User();
            if (userInfo.newUser()) {
                userInfo.saveToFile();
            }
            // Add update number of available items
            ShoppingCartGUI.this.dispose();
            homeGUI.dispose();
        }
    }
}