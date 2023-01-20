import model.*;
import repo.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleManager {

    private static final Scanner scanner = new Scanner(System.in);

    // all data access objects
    private static final ProductDAO productDAO = new ProductDAO();
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
        System.out.println("[7] Search cart by ID"); // 4. Search customer by ID
        System.out.println("[8] Search order by ID"); // 4. Search customer by ID

        // insert
        System.out.println("\n[9] Insert new product"); // 5. Add new product
        System.out.println("[10] Insert new customer"); // 6. Add new customer

        // update
        System.out.println("\n[11] Update product"); // 7. Amend product
        System.out.println("[12] Update customer"); // 8. Amend customer

        // order -> move to new menu
        System.out.println("\n[13] Order Products ->");

        // delete
        System.out.println("\n[14] Delete product"); // 9. Delete product
        System.out.println("[15] Delete customer"); // 10. Delete customer
        System.out.println("\n[16] Delete order");
        System.out.println("\n[17] Delete cart");

        System.out.println("\n[18] Exit"); // 11. exit
    }

    /**
     * printProductList
     */
    public void printProductsList() throws SQLException {
        System.out.println("\n------------------ List all products --------------------");
        ArrayList<Product> allProducts = productDAO.getAll();
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

    // search product by id
    public void searchProductByID() throws SQLException {
        System.out.print("\nSearch by product ID: ");
        int ID = Integer.parseInt(scanner.nextLine());

        Product product = productDAO.get(ID);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product not found by id=" + ID);
        }

        System.out.println();
    }

    // search customer by id
    public void searchCustomerByID() throws SQLException {
        System.out.print("\nSearch by customer ID: ");
        int cID = Integer.parseInt(scanner.nextLine());
        Customer customer = customerDAO.get(cID);
        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer not found by id=" + cID);
        }
        System.out.println();
    }

    // search cart by id
    public void searchCartByID() throws SQLException {
        System.out.print("\nSearch by cart ID: ");
        int cID = Integer.parseInt(scanner.nextLine());
        Cart cart = cartDAO.get(cID);
        if (cart != null) {
            System.out.println(cart);
        } else {
            System.out.println("Cart not found by id=" + cID);
        }
        System.out.println();
    }

    // search order by id
    public void searchOrderByID() throws SQLException {
        System.out.print("\nSearch by order ID: ");
        int orderID = Integer.parseInt(scanner.nextLine());
        Order order = orderDAO.get(orderID);
        if (order != null) {
            System.out.println(order);
        } else {
            System.out.println("Cart not found by id=" + orderID);
        }
        System.out.println();
    }

    // create new product
    public void createNewProduct() throws SQLException {
        System.out.println("\nCreate new product: ");

        String category;
        String artist;
        String album;
        String genre;
        String SKU;
        double price;
        int quantity;

        System.out.println("Please enter Category: ");
        category = scanner.nextLine();
        System.out.println("Please enter Artist: ");
        artist = scanner.nextLine();
        System.out.println("Please enter Album: ");
        album = scanner.nextLine();
        System.out.println("Please enter Genre: ");
        genre = scanner.nextLine();
        System.out.println("Please enter SKU: ");
        SKU = scanner.nextLine();
        System.out.println("Please enter Price: ");
        price = Double.parseDouble(scanner.nextLine());
        System.out.println("Please enter Quantity: ");
        quantity = Integer.parseInt(scanner.nextLine());

        Product products = new Product(category, artist, album, genre, SKU, price, quantity);

        int productID = productDAO.add(products);
        System.out.println("\nProduct added successfully, create product ID is (" + productID + ")");
    }

    // create new customer
    public void createNewCustomer() throws SQLException {
        System.out.println("\nCreate new customer: ");

        String customerForename;
        String customerSurname;
        Address customerAddress = new Address();
        String customerTelNo;

        // customer info part
        System.out.print("Please enter customer forename: ");
        customerForename = scanner.nextLine();
        System.out.print("Please enter customer surname: ");
        customerSurname = scanner.nextLine();
        System.out.print("Please enter telephone number: ");
        customerTelNo = scanner.nextLine();

        // address part
        System.out.println("Please enter address: ");
        System.out.print("\t Enter house no: ");
        customerAddress.setHouseNo(scanner.nextLine());
        System.out.print("\t Enter address line 1: ");
        customerAddress.setAddressLine1(scanner.nextLine());
        System.out.print("\t Enter address line 2: ");
        customerAddress.setAddressLine2(scanner.nextLine());
        System.out.print("\t Enter country: ");
        customerAddress.setCountry(scanner.nextLine());
        System.out.print("\t Enter postcode: ");
        customerAddress.setPostcode(scanner.nextLine());

        // create and print successful message
        Customer customerA = new Customer(customerForename,
                customerSurname, customerAddress, customerTelNo);
        int customerID = customerDAO.add(customerA);
        System.out.println("\nCustomer added successfully, create product ID is (" + customerID + ")");
    }
}
