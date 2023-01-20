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
    private static final ConsoleManager manager = new ConsoleManager();

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

                // create new product
                case "9" -> manager.createNewProduct();

                // create new customer
                case "10" -> manager.createNewCustomer();

                // update product
                case "11" -> manager.updateProduct();

                // update customer
                case "12" -> manager.updateCustomer();

                case "13" -> {
                    System.out.println("Delete product");
                    System.out.println("Enter product ID to be deleted: ");
                    int dID = Integer.parseInt(scanner.nextLine());
//                    product.delete(dID);
                }
                case "14" -> {
                    System.out.println("Delete customer");
                    System.out.println("Enter customer ID to be deleted: ");
                    int cdID = Integer.parseInt(scanner.nextLine());
//                    customers.delete(cdID);
                }
                case "15" -> System.out.println("Delete orders");
                case "16" -> System.out.println("Delete carts");

                case "17" -> System.out.println("Order Products -> -> -> ");
                case "18" -> System.out.println("Exit");
                default -> System.out.println("Invalid option, please re-enter");
            }

        } while (!options.equals("18"));
    }
}

