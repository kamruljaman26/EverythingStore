import java.sql.SQLException;
import java.util.Scanner;

import server.MyHttpServer;

import java.io.IOException;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ConsoleManager manager = new ConsoleManager();

    public static void main(String[] args) throws SQLException, IOException {

        // run http server
        MyHttpServer httpServer = new MyHttpServer();
        httpServer.run();

        // main menu
//        createMainMenu();

    }

    // Console based view's main menu
    private static void createMainMenu() throws SQLException {
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

                // delete product
                case "13" -> manager.deleteProduct();

                // delete customer
                case "14" -> manager.deleteCustomer();

                // delete cart
                case "15" -> manager.deleteCart();

                // delete order
                case "16" -> manager.deleteOrder();

                // order product
                case "17" -> orderMenu();

                // exit
                case "18" -> System.out.println("Exit");

                // default
                default -> System.out.println("Invalid option, please re-enter");
            }

        } while (!options.equals("18"));
    }

    // order menu
    private static void orderMenu() throws SQLException {
        String options;
        do {

            manager.printOrderMenu();
            System.out.print("\nInput: ");

            options = scanner.nextLine();

            // handle user selected options
            switch (options) {
//
                // View products in cart
                case "1" -> manager.printCart();

                // Add products in cart
                case "2" -> manager.addProductsInCart();

                // Delete all products in cart
                case "3" -> manager.deleteAllCartProducts();

                //  Crate Order (process cart products
                case "4" -> manager.createOrder();

                // View Orders
                case "5" -> manager.printOrdersList();

                // Exit
                case "6" -> System.out.println("Exit (order-section)");

                // default
                default -> System.out.println("Invalid option, please re-enter");
            }

        } while (!options.equals("6"));
    }


}

