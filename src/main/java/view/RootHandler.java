package view;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class RootHandler implements HttpHandler {

    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

        out.write(
                "<html>" +
                        "<head> <title>Library</title> " +
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<br><br>" +
                        "<div class=\"container\">" +

                        // display products, customer, cart, orders
                        "<a href=\"/products\">Display Products</a> " +
                        "<br>" +
                        "<a href=\"/customers\">Display Customers</a> " +
                        "<br>" +
                        "<a href=\"/carts\">Display Carts</a> " +
                        "<br>" +
                        "<a href=\"/orders\">Display Orders</a> " +

                        // add products, customer
                        "<br>" + "<br>" +
                        "<a href=\"/add-product\">Add Product</a> " +
                        "<br>" +
                        "<a href=\"/add-customer\">Add Customer</a> " +

                        "</div>" + "</body>" + "</html>"
        );
        out.close();
    }

}