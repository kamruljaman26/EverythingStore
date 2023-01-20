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

            // add products and customer
            server.createContext("/add-product", new AddProductHandler());
            server.createContext("/add-customer", new AddCustomerHandler());

            // search products and customer
            // server.createContext("/searchProduct", new SearchProductHandler() );
            // server.createContext("/searchCustomer", new SearchCustomerHandler() );

            // delete
            server.createContext("/delete-product", new DeleteProductHandler());
            server.createContext("/delete-customer", new DeleteCustomerHandler());

            // data process handler
            server.createContext("/process-add-customer", new ProcessAddCustomerHandler());
            server.createContext("/process-add-product", new ProcessAddProductHandler());


            // start
            server.setExecutor(null);
            server.start();

            System.out.println("The server is listening on port " + PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
