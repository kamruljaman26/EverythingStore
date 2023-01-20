import model.Cart;
import model.Customer;
import model.Order;
import model.Product;
import repo.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleManager {

    private static final Scanner scanner = new Scanner(System.in);

    // all data access objects
    private static final ProductDAO product = new ProductDAO();
    private static final CartDAO cartDAO = new CartDAO();
    private static final CustomerDAO customerDAO = new CustomerDAO();
    private static final OrderDAO orderDAO = new OrderDAO();
    private static final AddressDAO addressDAO = new AddressDAO();

    /**
     * print menu in console
     */
    public void printMenu() {

        // intro
        System.out.println("\nThe Everything (Music) Store");
        System.out.println("\nChoose from the following options:");

        // prints list
        System.out.println("\n[1] List all products"); // 1. list all products
        System.out.println("[2] List all customers"); // 2. list all customers
        System.out.println("[3] List all cart products"); // 3. list all carts
        System.out.println("[4] List all orders and order's products"); // 4. list all orders

        // search
        System.out.println("\n[5] Search product by ID"); // 3. search product by ID
        System.out.println("[6] Search customer by ID"); // 4. Search customer by ID

        // insert
        System.out.println("\n[7] Insert new product"); // 5. Add new product
        System.out.println("[8] Insert new customer"); // 6. Add new customer

        // update
        System.out.println("\n[9] Update product"); // 7. Amend product
        System.out.println("[10] Update customer"); // 8. Amend customer

        // order
        System.out.println("\n[11] Amend order");

        // delete
        System.out.println("\n[12] Delete product"); // 9. Delete product
        System.out.println("[13] Delete customer"); // 10. Delete customer
        System.out.println("\n[14] Delete order");

        System.out.println("\n[15] Exit"); // 11. exit
    }

    /**
     * printProductList
     */
    public void printProductsList() throws SQLException {
        System.out.println("\n------------------ List all products --------------------");
        ArrayList<Product> allProducts = product.getAll();
        for (Product allProduct : allProducts) {
            System.out.println(allProduct);
        }
        System.out.println();
    }

    public void printCustomerList() throws SQLException {
        System.out.println("\n------------------- List all customers ------------------");
        ArrayList<Customer> allCustomers = customerDAO.getAll();
        for (Customer allCustomer : allCustomers) {
            System.out.println(allCustomer);
        }
        System.out.println();
    }

    // print all cart products
    public void printCart() throws SQLException {
        System.out.println("\n-------------------- Current Cart Products--------------------");
        ArrayList<Cart> callCart = cartDAO.getAll();
        for (Cart cart : callCart) {
            System.out.println(cart);
        }
        System.out.println();
    }

    // print all orders
    public void printOrdersList() throws SQLException {
        System.out.println("\n--------------------- Orders List --------------------------------");
        ArrayList<Order> orders = orderDAO.getAll();
        for (Order order : orders) {
            System.out.println(order);
        }
        System.out.println();
    }
}
