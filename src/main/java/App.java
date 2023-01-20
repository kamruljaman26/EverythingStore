import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.*;
import repo.*;
import server.MyHttpServer;
import view.*;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductDAO product = new ProductDAO();
    private static final CustomerDAO customers = new CustomerDAO();
    private static ConsoleManager manager = new ConsoleManager();

    public static void main(String[] args) throws SQLException, IOException {

        // run http server
        MyHttpServer httpServer = new MyHttpServer();
//        httpServer.run();

        // Console based view
        String options;
        do {

            // print menu & get option from user
            manager.printMenu();
            System.out.print("\nInput: ");

            options = scanner.nextLine();

            // handle user selected options
            switch (options) {

                // print product list
                case "1" -> manager.printProductsList();

                // print customer list
                case "2" -> manager.printCustomerList();

                // print cart
                case "3" -> manager.printCart();

                // print orders
                case "4" -> manager.printOrdersList();

                // search product by id
                case "5" -> manager.searchProductByID();

                // search customer by id
                case "6" -> manager.searchCustomerByID();

                // search cart by id
                case "7" -> manager.searchCartByID();

                // search order by id
                case "8" -> manager.searchOrderByID();

                case "9" -> {
                    System.out.println("\nCreate new product: ");
                    Product products = createProduct();
                    product.add(products);
                    System.out.println("");
                    break;
                }
                case "10" -> {
                    System.out.println("\nCreate new customer: ");
                    Customer customerA = createCustomer();
                    customers.add(customerA);
                    System.out.println();
                    break;
                }
                case "11" -> {
                    System.out.println("\nUpdate product: ");
                    System.out.println("Enter product ID: ");
                    int xID = Integer.parseInt(scanner.nextLine());
                    System.out.println(product.get(xID));
                    Product updatedProduct = updateProduct(product.get(xID));
                    product.update(updatedProduct);
                    break;
                }
                case "12" -> {
                    System.out.println("\nUpdate customer: ");
                    System.out.println("Enter customer ID: ");
                    int cUID = Integer.parseInt(scanner.next());
                    System.out.println(customers.get(cUID));
                    Customer updatedCustomer = updateCustomer(customers.get(cUID));
                    customers.update(updatedCustomer);
                }
                case "13" -> System.out.println("Amend order");
                case "14" -> {
                    System.out.println("Delete product");
                    System.out.println("Enter product ID to be deleted: ");
                    int dID = Integer.parseInt(scanner.nextLine());
                    product.delete(dID);
                }
                case "15" -> {
                    System.out.println("Delete customer");
                    System.out.println("Enter customer ID to be deleted: ");
                    int cdID = Integer.parseInt(scanner.nextLine());
                    customers.delete(cdID);
                }
                case "16" -> System.out.println("Delete orders");
                case "17" -> System.out.println("Exit");
                default -> System.out.println("Invalid option, please re-enter");
            }

        } while (!options.equals("17"));
    }

    // create product
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

    // create customer
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

    // update product
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

    // update customer
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

