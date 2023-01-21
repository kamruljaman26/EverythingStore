package server;

import com.sun.net.httpserver.HttpServer;
import view.*;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Manage separate thread for web server
 */
public class MyHttpServer implements Runnable {
    private static final int PORT = 8080; // http port

    @Override
    public void run() {
        try {
            // HTTP Server & View Links
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

            // home
            server.createContext("/", new RootHandler());

            // view products and customer
            server.createContext("/products", new DisplayAllProductHandler());
            server.createContext("/customers", new DisplayAllCustomerHandler());
            server.createContext("/carts", new DisplayAllCartHandler());
            server.createContext("/orders", new DisplayAllOrderHandler());

            // add products and customer & handle process
            server.createContext("/add-product", new AddProductHandler());
            server.createContext("/process-add-product", new ProcessAddProductHandler());
            server.createContext("/add-customer", new AddCustomerHandler());
            server.createContext("/process-add-customer", new ProcessAddCustomerHandler());

            // search products and customer
            // server.createContext("/searchProduct", new SearchProductHandler() );
            // server.createContext("/searchCustomer", new SearchCustomerHandler() );

            // delete
            server.createContext("/delete-product", new DeleteProductHandler());
            server.createContext("/delete-customer", new DeleteCustomerHandler());
            server.createContext("/delete-cart", new DeleteCartHandler());
            server.createContext("/delete-order", new DeleteOrderHandler());

            // process order -> :D
            server.createContext("/process-order", new ProcessOrderHandler());

            // manage cart and order
            server.createContext("/process-add-product-in-cart", new ProcessAddProductInCartHandler());


            // start
            server.setExecutor(null);
            server.start();

            System.out.println("The server is listening on port " + PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
