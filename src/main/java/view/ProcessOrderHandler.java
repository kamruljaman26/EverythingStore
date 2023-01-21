package view;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.*;
import repo.CartDAO;
import repo.CustomerDAO;
import repo.OrderDAO;
import util.Util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class ProcessOrderHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200, 0);

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

        // Get param from URL
        Map<String, String> parms = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(parms);

        try {
            // create order
            int customerID = Integer.parseInt(parms.get("customerid"));
            CartDAO cartDAO = new CartDAO();
            OrderDAO orderDAO = new OrderDAO();
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.get(customerID);

            // convert cart
            ArrayList<OrderProduct> orderProducts = new ArrayList<>();
            cartDAO.getAll().forEach(p -> {
                orderProducts.add(new OrderProduct(p.getProduct(), p.getQuantity()));
            });

            // remove cart products
            cartDAO.getAll().forEach(p -> {
                try {
                    cartDAO.delete(p.getCartID());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            // create order
            Order order = new Order(customer, orderProducts);
            int orderID = orderDAO.add(order);
            order = orderDAO.get(orderID);

            if (orderID != -1) {
                System.out.println(orderDAO.get(orderID));
                System.out.println("-- Order created successfully");
            } else {
                System.out.println("Unable to create order.");
            }

            out.write(
                    "<html>" +
                            "<head> <title>Product Library</title> " +
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1> Order Added </h1>" +
                            "<h3> Total Price: " + order.getTotalPrice() + "$ </h3>" +
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>Order ID:</th>" +
                            "    <th>Customer:</th>" +
                            "    <th>Products:</th>" +
                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");

            StringBuilder cus = new StringBuilder();
            StringBuilder prod = new StringBuilder();

            // custom customer sting for simple view
            Address address = customer.getCustomerAddress();
            cus.append("Name: ");
            cus.append(customer.getCustomerForename()).append(" ").append(customer.getCustomerSurname()).append("<br>");
            cus.append("Tel No: ").append(customer.getCustomerTelNo()).append("<br>");
            cus.append("Address: ").append(address.getAddressLine1()).append(", ")
                    .append(address.getAddressLine2()).append(", ").append(address.getCountry()).append(", ")
                    .append(address.getPostcode());

            // custom products sting for simple view
            ArrayList<OrderProduct> products = order.getProducts();
            products.forEach( p->{
                prod.append(p.getProduct()).append("<br>");
                prod.append("Quantity: ").append(p.getQuantity()).append("<br><br>");
            });

            out.write(
                    "  <tr>" +
                            "    <td><b>" + order.getOrder_id() + "<b></td>" +

                            // create short string for customer & order
                            "    <td>" + cus.toString() + "</td>" +
                            "    <td>" + prod.toString() + "</td>" +
                            "  </tr>"
            );

            out.write(
                    "</tbody>" +
                            "</table>" +
                            "<a href=\"/\"> Back to List </a>" +
                            "</body>" +
                            "</html>");

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
