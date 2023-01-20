package view;

import java.io.OutputStreamWriter;

import repo.CustomerDAO;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.IOException;

public class AddCustomerHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {

        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));

        CustomerDAO customer = new CustomerDAO();
        //code to go here for DB
        out.write(
                "<html>" +
                        "<head> <title>Customer List</title> " +
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\"" +
                        " integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">" +
                        "<h1> Add Customer </h1>" +
                        "<form method=\"get\" action=\"/process-add-customer\">" +
                        "<div class=\"form-group\"> " +

                        // fore name
                        "<label for=\"customer forename\">Customer Forename</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"customerforename\" id=\"customerforename\"> " +

                        // sur name
                        "<label for=\"customer surname\">Customer Surname</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"customersurname\" id=\"customersurname\"> " +

                        // telephone number
                        "<label for=\"customer tel no\">Customer Telephone Number</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"customertelno\" id=\"customertelno\"> " +

                        // address section - house no
                        "<label for=\"houseno\">House No</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"houseno\" id=\"houseno\"> " +

                        // address section - line 1
                        "<label for=\"line1\">Address Line 1</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"line1\" id=\"line1\"> " +

                        // address section - line 2
                        "<label for=\"line2\">Address Line 2</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"line2\" id=\"line2\"> " +

                        // address section - country
                        "<label for=\"country\">Country</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"country\" id=\"country\"> " +

                        // address section - postcode
                        "<label for=\"postcode\">Postcode</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"postcode\" id=\"postcode\"> " +


                        "<br><button type=\"submit\" class=\"btn btn-primary\">Submit</button> " +
                        "</div>" +
                        "</form>" +
                        "<a href=\"/\"> Back to List </a>" +
                        "</div>" +
                        "</body>" +
                        "</html>");

        out.close();
    }
}
