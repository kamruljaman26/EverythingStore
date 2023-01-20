import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.everythingstore.model.Address;
import com.everythingstore.model.Customer;
import com.everythingstore.model.Product;
import com.everythingstore.repo.CustomerDAO;
import com.everythingstore.repo.ProductDAO;
import com.everythingstore.view.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {
    private static final int PORT = 8080;
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductDAO product = new ProductDAO();
    private static final CustomerDAO customers = new CustomerDAO();

    public static void main(String[] args) throws SQLException, IOException {

        /*
         * HTTP Server & View Links
         */
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/", new RootHandler());
        server.createContext("/products", new DisplayAllProductHandler());
        //server.createContext("/searchProduct", new SearchProductHandler() );
        server.createContext("/delete", new DeleteProductHandler());
        server.createContext("/add", new AddProductHandler());
        server.createContext("/processAddProduct", new ProcessAddProductHandler());
        server.createContext("/customers", new DisplayAllCustomerHandler());
        // server.createContext("/searchCustomer", new SearchCustomerHandler() );
        server.createContext("/deleteCustomer", new DeleteCustomerHandler());
        server.createContext("/addCustomer", new AddCustomerHandler());
        server.createContext("/processAddCustomer", new ProcessAddCustomerHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("The server is listening on port " + PORT);

        // Console based view
        String options;
        do {
            System.out.println("\nThe Everything (Music) Store");

            System.out.println("\nChoose from the following options:");

            System.out.println("\n[1] List all products"); // 1. list all products
            System.out.println("[2] List all customers"); // 2. list all customers

            System.out.println("\n[3] Search product by ID"); // 3. search product by ID
            System.out.println("[4] Search customer by ID"); // 4. Search customer by ID

            System.out.println("\n[5] Insert new product"); // 5. Add new product
            System.out.println("[6] Insert new customer"); // 6. Add new customer

            System.out.println("\n[7] Update product"); // 7. Amend product
            System.out.println("[8] Update customer"); // 8. Amend customer

            System.out.println("\n[9] Amend order");

            System.out.println("\n[10] Delete product"); // 9. Delete product
            System.out.println("[11] Delete customer"); // 10. Delete customer
            System.out.println("\n[12] Delete order");

            System.out.println("\n[13] Exit"); // 11. exit

            options = scanner.nextLine();

            switch (options) {
                case "1" -> {
                    System.out.println("\nList all products");
                    ArrayList<Product> allProducts = product.getAll();
                    for (int i = 0; i < allProducts.size(); i++) {
                        System.out.println(allProducts.get(i));
                    }
                    System.out.println();
                    break;
                }
                case "2" -> {
                    System.out.println("\nList all customers");
                    ArrayList<Customer> allCustomers = customers.getAll();
                    for (int i = 0; i < allCustomers.size(); i++) {
                        System.out.println(allCustomers.get(i));
                    }
                    System.out.println();
                    break;
                }
                case "3" -> {
                    System.out.println("\nSearch by product ID: ");
                    int ID = Integer.parseInt(scanner.nextLine());
                    System.out.println(product.get(ID));
                    System.out.println();
                    break;
                    //case "3" -> System.out.println("List of all orders");
                }
                case "4" -> {
                    System.out.println("\nSearch by customer ID: ");
                    int cID = Integer.parseInt(scanner.nextLine());
                    System.out.println(customers.get(cID));
                    System.out.println();
                    break;
                }
                case "5" -> {
                    System.out.println("\nCreate new product: ");
                    Product products = createProduct();
                    product.add(products);
                    System.out.println("");
                    break;
                }
                case "6" -> {
                    System.out.println("\nCreate new customer: ");
                    Customer customerA = createCustomer();
                    customers.add(customerA);
                    System.out.println();
                    break;
                }
                case "7" -> {
                    System.out.println("\nUpdate product: ");
                    System.out.println("Enter product ID: ");
                    int xID = Integer.parseInt(scanner.nextLine());
                    System.out.println(product.get(xID));
                    Product updatedProduct = updateProduct(product.get(xID));
                    product.update(updatedProduct);
                    break;
                }
                case "8" -> {
                    System.out.println("\nUpdate customer: ");
                    System.out.println("Enter customer ID: ");
                    int cUID = Integer.parseInt(scanner.next());
                    System.out.println(customers.get(cUID));
                    Customer updatedCustomer = updateCustomer(customers.get(cUID));
                    customers.update(updatedCustomer);

                }
                case "9" -> System.out.println("Amend order");
                case "10" -> {
                    System.out.println("Delete product");
                    System.out.println("Enter product ID to be deleted: ");
                    int dID = Integer.parseInt(scanner.nextLine());
                    product.delete(dID);
                }
                case "11" -> {
                    System.out.println("Delete customer");
                    System.out.println("Enter customer ID to be deleted: ");
                    int cdID = Integer.parseInt(scanner.nextLine());
                    customers.delete(cdID);
                }
                case "12" -> System.out.println("Delete orders");
                case "13" -> System.out.println("Exit");
                default -> System.out.println("Invalid option, please re-enter");
            }

        } while (!options.equals("13"));
    }

    private static Product createProduct() {
        int ID;
        String category;
        String artist;
        String album;
        String genre;
        String SKU;
        double price;
        int quantity;

        Scanner scanner = new Scanner(System.in); //close

        System.out.println("Please enter ID: ");
        ID = Integer.parseInt(scanner.nextLine());
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

        return new Product(ID, category, artist, album, genre, SKU, price, quantity);
    }

    private static Customer createCustomer() {
        int customerID;
        String customerForename;
        String customerSurname;
        String customerAddress;
        String customerTelNo;

        Scanner scanner = new Scanner(System.in); //close

        System.out.println("Please enter ID: ");
        customerID = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter customer forename: ");
        customerForename = scanner.nextLine();
        System.out.println("Please enter customer surname: ");
        customerSurname = scanner.nextLine();
        System.out.println("Please enter address: ");
        customerAddress = scanner.nextLine();
        System.out.println("Please enter telephone number: ");
        customerTelNo = scanner.nextLine();

        // todo fixed address
        return new Customer(customerID, customerForename, customerSurname, new Address(), customerTelNo);
    }

    private static Product updateProduct(Product up) {
        int ID;
        String category;
        String artist;
        String album;
        String genre;
        String SKU;
        double price;
        int quantity;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Update product with product ID: " + up.getId());

        System.out.println("Update category: ");
        category = scanner.nextLine();
        if (category.equals(""))
            category = up.getCategory();

        System.out.println("Update artist: ");
        artist = scanner.nextLine();
        if (artist.equals(""))
            artist = up.getArtist();

        System.out.println("Update album title: ");
        album = scanner.nextLine();
        if (album.equals(""))
            album = up.getAlbum();

        System.out.println("Update genre: ");
        genre = scanner.nextLine();
        if (genre.equals(""))
            genre = up.getGenre();

        System.out.println("Update SKU: ");
        SKU = scanner.nextLine();
        if (SKU.equals(""))
            SKU = up.getSKU();

        System.out.println("Update price: ");
        String cvprice = scanner.nextLine();
        if (cvprice.equals(""))
            price = up.getPrice();
        else
            price = Double.parseDouble(cvprice);

        System.out.println("Update quantity: ");
        String cvquantity = scanner.nextLine();
        if (cvquantity.equals(""))
            quantity = up.getQuantity();
        else
            quantity = Integer.parseInt(cvquantity);

        return new Product(up.getId(), category, artist, album, genre, SKU, price, quantity);
    }

    private static Customer updateCustomer(Customer up) {
        int customerID;
        String customerForename;
        String customerSurname;
        Address customerAddress;
        String customerTelNo;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Update customer with customer ID: " + up.getCustomerID());

        System.out.println("Update Forename: ");
        customerForename = scanner.nextLine();
        if (customerForename.equals(""))
            customerForename = up.getCustomerForename();

        System.out.println("Update Surname: ");
        customerSurname = scanner.nextLine();
        if (customerSurname.equals(""))
            customerSurname = up.getCustomerSurname();

        System.out.println("Update Address: ");
//        customerAddress = scanner.nextLine();
//        if (customerAddress.equals(""))
            // todo: fix address type
//            customerAddress = up.getCustomerAddress();
            customerAddress = up.getCustomerAddress();

        System.out.println("Update Telephone Number: ");
        customerTelNo = scanner.nextLine();
        if (customerTelNo.equals(""))
            customerTelNo = up.getCustomerTelNo();

        // todo: fix address type
        return new Customer(up.getCustomerID(), customerForename, customerSurname, customerAddress, customerTelNo);
    }
}

