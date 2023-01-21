package view;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Cart;
import repo.CartDAO;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class DisplayAllCartHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

        CartDAO cartDao = new CartDAO();

        try {
            ArrayList<Cart> allCarts = cartDao.getAll();
            AtomicInteger totalPrice = new AtomicInteger();
            allCarts.forEach(p -> {
                totalPrice.addAndGet((int) (p.getProduct().getPrice() * p.getQuantity()));
            });
            out.write(
                    "<html>" +
                            "<head> <title>Customer Library</title> " +
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1> Cart Products </h1>" +
                            "<h3> Total Price: " + totalPrice + "$ </h3>" +
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>Cart ID:</th>" +
                            "    <th>Product:</th>" +
                            "    <th>Quantity:</th>" +
                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");

            for (Cart c : allCarts) {
                out.write(
                        "  <tr>" +
                                "    <td><b>" + c.getCartID() + "<b></td>" +
                                "    <td>" + c.getProduct() + "</td>" +
                                "    <td>" + c.getQuantity() + "</td>" +

                                "<td><a href=\"/delete-cart?id=" + c.getCartID() + "\"> delete </a><td>" +
                                "  </tr>"
                );
            }
            out.write(
                    "</tbody>" +
                            "</table>" +
                            "<a href=\"/\"> Back to Menu </a><br><br>" +

                            "<h3> Order cart products </h3>" +
                            "<form method=\"get\" action=\"/process-order\">" +
                            "<div class=\"form-group\"> " +

                            "<label for=\"customerid\">Category</label> " +
                            "<input type=\"text\" class=\"form-control\" name=\"customerid\" id=\"customerid\"> " +

                            "<br>" + "<button type=\"submit\" class=\"btn btn-primary\">Process Order</button> " +
                            "</div>" +
                            "</form>" +

                            "</div>" +

                            "</body>" +
                            "</html>");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        out.close();
    }
}
