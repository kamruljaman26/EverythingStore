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
            HttpServer server =
                    HttpServer.create(new InetSocketAddress(PORT), 0);

            server.createContext("/", new RootHandler());
            server.createContext("/products", new DisplayAllProductHandler());
            // server.createContext("/searchProduct", new SearchProductHandler() );
            server.createContext("/delete", new DeleteProductHandler());
            server.createContext("/add", new AddProductHandler());
            server.createContext("/processAddProduct", new ProcessAddProductHandler());
            server.createContext("/customers", new DisplayAllCustomerHandler());
            // server.createContext("/searchCustomer", new SearchCustomerHandler() );
            server.createContext("/deleteCustomer", new DeleteCustomerHandler());
            server.createContext("/addCustomer", new AddCustomerHandler());
            server.createContext("/processAddCustomer", new ProcessAddCustomerHandler());

            // start
            server.setExecutor(null);
            server.start();

            System.out.println("The server is listening on port " + PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
