package view;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.*;
import repo.CartDAO;
import repo.OrderDAO;
import util.Util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DeleteOrderHandler implements HttpHandler {
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

        OrderDAO orderDAO = new OrderDAO();

        try {
            // get the dvd details before we delete from the Database
            Order order = orderDAO.get(ID);
            // actually delete from database;
            orderDAO.delete(ID);

            // custom string
            StringBuilder cus = new StringBuilder();
            StringBuilder prod = new StringBuilder();

            // custom customer sting for simple view
            Customer customer = order.getCustomer();
            Address address = customer.getCustomerAddress();

            cus.append("Name: ");
            cus.append(customer.getCustomerForename()).append(" ").append(customer.getCustomerSurname()).append("<br>");
            cus.append("Tel No: ").append(customer.getCustomerTelNo()).append("<br>");
            cus.append("Address: ").append(address.getAddressLine1()).append(", ")
                    .append(address.getAddressLine2()).append(", ").append(address.getCountry()).append(", ")
                    .append(address.getPostcode());

            // custom products sting for simple view
            ArrayList<OrderProduct> products = order.getProducts();
            products.forEach(p -> {
                prod.append(p.getProduct()).append("<br>");
                prod.append("Quantity: ").append(p.getQuantity()).append("<br><br>");
            });

            out.write(
                    "<html>" +
                            "<head> <title>Customer Library</title> " +
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1> Order Deleted </h1>" +
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>Order ID:</th>" +
                            "    <th>Customer:</th>" +
                            "    <th>Products:</th>" +
                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");


            out.write(
                    "  <tr>" +
                            "    <td><b>" + order.getOrder_id() + "<b></td>" +
                            "    <td>" + cus + "</td>" +
                            "    <td>" + prod + "</td>" +
                            "  </tr>"
            );

            out.write(
                    "</tbody>" +
                            "</table>" +
                            "<a href=\"/orders\"> Back to Menu </a>" +
                            "</body>" +
                            "</html>");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        out.close();
    }
}
