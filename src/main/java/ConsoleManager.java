import model.*;
import repo.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

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
        System.out.println("\n[5] Search product by ID"); // 5. search product by ID
        System.out.println("[6] Search customer by ID"); // 6. Search customer by ID
        System.out.println("[7] Search cart by ID"); // 7. Search customer by ID
        System.out.println("[8] Search order by ID"); // 8. Search customer by ID

        // insert
        System.out.println("\n[9] Insert new product"); // 9. Add new product
        System.out.println("[10] Insert new customer"); // 10. Add new customer

        // update
        System.out.println("\n[11] Update product"); // 11. Amend product
        System.out.println("[12] Update customer"); // 12. Amend customer

        // delete
        System.out.println("\n[13] Delete product"); // 13. Delete product
        System.out.println("[14] Delete customer"); // 14. Delete customer
        System.out.println("[15] Delete cart"); // 15. Delete order
        System.out.println("[16] Delete order"); // 16. Delete cart

        // order -> move to new menu
        System.out.println("\n[17] Order Products -> ->"); // 17. Order menu
        System.out.println("[18] Exit"); // 11. exit
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

        if (callCart.size() == 0) {
            System.out.println("The cart is empty");
            return;
        }

        // print total price
        AtomicInteger totalPrice = new AtomicInteger();
        callCart.forEach(p -> {
            totalPrice.addAndGet((int) (p.getProduct().getPrice() * p.getQuantity()));
        });
        System.out.println("Total Price: " + totalPrice + "$");

        for (Cart cart : callCart) {
            System.out.println(cart);
        }
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

    // update a product
    public void updateProduct() throws SQLException {
        System.out.println("\nUpdate product: ");
        System.out.println("Enter product ID: ");

        int xID = Integer.parseInt(scanner.nextLine());
        Product product = productDAO.get(xID);

        if (product == null) {
            System.out.println("Product not found by id=" + xID + "");
            return;
        }

        System.out.println(productDAO.get(xID));

        String category;
        String artist;
        String album;
        String genre;
        String SKU;
        double price;
        int quantity;

        System.out.println("Update product with product ID: " + product.getId());

        System.out.print("Update category: ");
        category = scanner.nextLine();
        if (category.equals(""))
            category = product.getCategory();

        System.out.print("Update artist: ");
        artist = scanner.nextLine();
        if (artist.equals(""))
            artist = product.getArtist();

        System.out.print("Update album title: ");
        album = scanner.nextLine();
        if (album.equals(""))
            album = product.getAlbum();

        System.out.print("Update genre: ");
        genre = scanner.nextLine();
        if (genre.equals(""))
            genre = product.getGenre();

        System.out.print("Update SKU: ");
        SKU = scanner.nextLine();
        if (SKU.equals(""))
            SKU = product.getSKU();

        System.out.print("Update price: ");
        String cvprice = scanner.nextLine();
        if (cvprice.equals(""))
            price = product.getPrice();
        else
            price = Double.parseDouble(cvprice);

        System.out.print("Update quantity: ");
        String cvquantity = scanner.nextLine();
        if (cvquantity.equals(""))
            quantity = product.getQuantity();
        else
            quantity = Integer.parseInt(cvquantity);

        // update product
        Product up = new Product(product.getId(), category, artist, album, genre, SKU, price, quantity);
        productDAO.update(up);

        System.out.println("Updated " + up);
    }

    // update customer
    public void updateCustomer() throws SQLException {

        System.out.println("\nUpdate customer: ");
        System.out.print("Enter customer ID: ");
        int cUID = Integer.parseInt(scanner.next());

        Customer customer = customerDAO.get(cUID);
        if (customer == null) {
            System.out.println("Customer not found by id=" + cUID);
            return;
        }
        System.out.println("\n" + customer);

        String customerForename;
        String customerSurname;
        String customerTelNo;

        System.out.println("Update customer with customer ID: " + customer.getCustomerID());

        System.out.print("Update Forename: ");
        scanner.nextLine();
        customerForename = scanner.nextLine();
        if (customerForename.equals(""))
            customerForename = customer.getCustomerForename();

        System.out.print("Update Surname: ");
        customerSurname = scanner.nextLine();
        if (customerSurname.equals(""))
            customerSurname = customer.getCustomerSurname();

        System.out.print("Update Telephone Number: ");
        customerTelNo = scanner.nextLine();
        if (customerTelNo.equals(""))
            customerTelNo = customer.getCustomerTelNo();

        String houseNo;
        String addressLine1;
        String addressLine2;
        String country;
        String postcode;

        // --------- address part
        System.out.print("\nUpdate Address Details: \n");
        Address address = customer.getCustomerAddress();

        System.out.print("\t Enter House No: ");
        houseNo = scanner.nextLine();
        if (houseNo.equals(""))
            houseNo = address.getHouseNo();

        System.out.print("\t Enter Address Line 1: ");
        addressLine1 = scanner.nextLine();
        if (addressLine1.equals(""))
            addressLine1 = address.getAddressLine1();

        System.out.print("\t Enter Address Line 2: ");
        addressLine2 = scanner.nextLine();
        if (addressLine2.equals(""))
            addressLine2 = address.getAddressLine2();

        System.out.print("\t Enter Country: ");
        country = scanner.nextLine();
        if (country.equals(""))
            country = address.getCountry();

        System.out.print("\t Enter Postcode: ");
        postcode = scanner.nextLine();
        if (postcode.equals(""))
            postcode = address.getCountry();

        address.setHouseNo(houseNo);
        address.setAddressLine1(addressLine1);
        address.setAddressLine2(addressLine2);
        address.setCountry(country);
        address.setPostcode(postcode);

        customer.setCustomerSurname(customerSurname);
        customer.setCustomerForename(customerForename);
        customer.setCustomerTelNo(customerTelNo);

        customer.setCustomerAddress(address);
        customerDAO.update(customer);

        System.out.println("Updated " + customer);
    }

    // delete product
    public void deleteProduct() throws SQLException {
        System.out.println("Delete product");
        System.out.print("Enter product ID to be deleted: ");

        int dID = Integer.parseInt(scanner.nextLine());
        Product product = productDAO.get(dID);
        if (product == null) {
            System.out.println("Product not found by id=" + dID);
            return;
        }

        productDAO.delete(dID);
        System.out.println("Product deleted Successfully");
        System.out.println("\t " + product);
    }

    // delete customer
    public void deleteCustomer() throws SQLException {
        System.out.println("Delete customer");
        System.out.print("Enter customer ID to be deleted: ");

        int cID = Integer.parseInt(scanner.nextLine());
        Customer customer = customerDAO.get(cID);
        if (customer == null) {
            System.out.println("Customer not found by id=" + cID);
            return;
        }

        customerDAO.delete(cID);
        System.out.println("Customer deleted Successfully");
        System.out.println("\t " + customer);
    }

    // delete cart
    public void deleteCart() throws SQLException {
        System.out.println("Delete cart");
        System.out.print("Enter cart ID to be deleted: ");

        int cID = Integer.parseInt(scanner.nextLine());
        Cart cart = cartDAO.get(cID);
        if (cart == null) {
            System.out.println("Cart not found by id=" + cID);
            return;
        }

        cartDAO.delete(cID);
        System.out.println("Cart deleted Successfully");
        System.out.println("\t " + cart);
    }

    // delete order
    public void deleteOrder() throws SQLException {
        System.out.println("Delete order");
        System.out.print("Enter order ID to be deleted: ");

        int cID = Integer.parseInt(scanner.nextLine());
        Order order = orderDAO.get(cID);
        if (order == null) {
            System.out.println("Order not found by id=" + cID);
            return;
        }

        orderDAO.delete(cID);
        System.out.println("Order deleted Successfully");
        System.out.println(order);
    }

    // print order menus
    public void printOrderMenu() {
        // intro
        System.out.println("\n\n------------------ Order Product from Everything Store ------------------------");
        System.out.println("\nChoose from the following options:");

        // prints list
        System.out.println("\n[1] View products in cart."); // 1. View products in cart
        System.out.println("[2] Add products in cart"); // 2. Add products in cart
        System.out.println("[3] Delete all products in cart"); // 3.Delete all products in cart
        System.out.println("[4] Crate Order (process cart products"); // 4. Crate Order (process cart products0
        System.out.println("[5] View Orders"); // 5. View Orders

        System.out.println("[6] Exit"); // 11. exit
    }

    // Add products in cart
    public void addProductsInCart() throws SQLException {
        System.out.println("Add products to carts");
        printProductsList();

        System.out.print("Enter Product ID: ");
        int productID = scanner.nextInt();
        Product product = productDAO.get(productID);
        if (product == null) {
            System.out.println("Invalid product selection");
            return;
        }

        System.out.print("Enter Product Quantity: ");
        int quantity = scanner.nextInt();

        Cart cart = new Cart(product, quantity);
        cartDAO.add(cart);

        System.out.println(product);
        System.out.println("Successfully added in cart.");
    }

    // Delete all products in cart
    public void deleteAllCartProducts() throws SQLException {
        ArrayList<Cart> all = cartDAO.getAll();
        all.forEach(p -> {
            try {
                cartDAO.delete(p.getCartID());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Successfully deleted all cart products.");
    }

    // Crate Order (process cart products
    public void createOrder() throws SQLException {

        /*
         * Create order steps
         * S1: Get customer id
         * S2: Create order products list
         * S3: Delete all cart products
         * S4: create order
         */

        // can't process empty cart
        if(cartDAO.getAll().isEmpty()){
            System.out.println("Sorry you can't create a order, because the cart is empty. Please add products\n" +
                    "in cart to order.");
            return;
        }

        // get customer
        System.out.println("Create Order");
        System.out.print("Enter Customer ID: ");
        int customerID = scanner.nextInt();
        Customer customer = customerDAO.get(customerID);
        if (customer == null) {
            System.out.println("Customer not found by id=" + customerID);
            return;
        }

        // convert cart
        ArrayList<OrderProduct> orderProducts = new ArrayList<>();
        cartDAO.getAll().forEach(p -> {
            orderProducts.add(new OrderProduct(p.getProduct(), p.getQuantity()));
        });

        // remove cart products
        cartDAO.getAll().forEach(p -> {
            try {
                cartDAO.delete(p.getCartID());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // create order
        Order order = new Order(customer, orderProducts);
        int orderID = orderDAO.add(order);
        if (orderID != -1) {
            System.out.println(orderDAO.get(orderID));
            System.out.println("-- Order created successfully");
        } else {
            System.out.println("Unable to create order.");
        }
    }
}
