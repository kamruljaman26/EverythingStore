package view;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Cart;
import model.Customer;
import repo.CartDAO;
import repo.CustomerDAO;
import util.Util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.Map;

public class DeleteCartHandler implements HttpHandler {
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

        // Get param from URL
        Map<String, String> parms = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(parms);

        //get ID number
        int ID = Integer.parseInt(parms.get("id"));

        CartDAO cartDAO = new CartDAO();

        try {
            // get the dvd details before we delete from the Database
            Cart cart = cartDAO.get(ID);
            // actually delete from database;
            cartDAO.delete(ID);

            out.write(
                    "<html>" +
                            "<head> <title>Customer Library</title> " +
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1> Customer Deleted </h1>" +
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>Cart ID:</th>" +
                            "    <th>Product:</th>" +
                            "    <th>Quantity:</th>" +
                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");


            out.write(
                    "  <tr>" +
                            "    <td><b>" + cart.getCartID() + "<b></td>" +
                            "    <td>" + cart.getProduct() + "</td>" +
                            "    <td>" + cart.getQuantity() + "</td>" +
                            "  </tr>"
            );

            out.write(
                    "</tbody>" +
                            "</table>" +
                            "<a href=\"/\"> Back to Menu </a>" +
                            "</body>" +
                            "</html>");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        out.close();
    }
}
