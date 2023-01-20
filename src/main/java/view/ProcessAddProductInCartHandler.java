package view;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Cart;
import model.Product;
import repo.CartDAO;
import repo.ProductDAO;
import util.Util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

public class ProcessAddProductInCartHandler implements HttpHandler {
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

        CartDAO cartDAO = new CartDAO();
        ProductDAO productDAO = new ProductDAO();
        System.out.println("about to get data");
        String productID = parms.get("productid");
        int quantity = Integer.parseInt(parms.get("quantity"));

        try {
            Product product = productDAO.get(Integer.parseInt(productID));
            Cart cart = new Cart(product, quantity);
            int cartID = cartDAO.add(cart);
            cart.setCartID(cartID);
            cart = cartDAO.get(cartID);

            out.write(
                    "<html>" +
                            "<head> <title>Product Library</title> " +
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1> Product Added </h1>" +
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>Cart ID</th>" +
                            "    <th>Product</th>" +
                            "    <th>Quantity</th>" +
                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");


            out.write(
                    "  <tr>" +
                            "    <td>" + cart.getCartID() + "</td>" +
                            "    <td>" + cart.getProduct() + "</td>" +
                            "    <td>" + cart.getQuantity() + "</td>" +
                            "  </tr>"
            );

            out.write(
                    "</tbody>" +
                            "</table>" +
                            "<a href=\"/products\"> Back to List </a>" +
                            "</body>" +
                            "</html>");

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
