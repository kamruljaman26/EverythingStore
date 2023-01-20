package view;

import java.io.OutputStreamWriter;

import model.Address;
import model.Customer;
import repo.CustomerDAO;
import util.Util;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class ProcessAddCustomerHandler implements HttpHandler {
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
        CustomerDAO customers = new CustomerDAO();

        //System.out.println("about to get data");
        String customerForename = parms.get("customerforename");
        String customerSurname = parms.get("customersurname");
        String customerTelNo = parms.get("customertelno");

        String houseNo = parms.get("houseno");
        String addressLine1 = parms.get("line1");
        String addressLine2 = parms.get("line2");
        String country = parms.get("country");
        String postcode = parms.get("postcode");

        Address address = new Address(houseNo, addressLine1, addressLine2, country, postcode);
        Customer customer = new Customer(customerForename, customerSurname, address, customerTelNo);
        System.out.println("Customer to add" + customer);

        try {
            int customerID = customers.add(customer);// add to database
            customer = customers.get(customerID);

            out.write(
                    "<html>" +
                            "<head> <title>Customer List</title> " +
                            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                            "</head>" +
                            "<body>" +
                            "<h1> Customer Added </h1>" +
                            "<table class=\"table\">" +
                            "<thead>" +
                            "  <tr>" +
                            "    <th>Customer ID</th>" +
                            "    <th>customer Forename</th>" +
                            "    <th>Customer Surname</th>" +
                            "    <th>Customer Address</th>" +
                            "    <th>Customer Telephone Number</th>" +

                            "  </tr>" +
                            "</thead>" +
                            "<tbody>");


            out.write(
                    "  <tr>" +
                            "    <td>" + customer.getCustomerID() + "</td>" +
                            "    <td>" + customer.getCustomerForename() + "</td>" +
                            "    <td>" + customer.getCustomerSurname() + "</td>" +
                            "    <td>" + customer.getCustomerAddress() + "</td>" +
                            "    <td>" + customer.getCustomerTelNo() + "</td>" +
                            "  </tr>"
            );

            out.write(
                    "</tbody>" +
                            "</table>" +
                            "<a href=\"/\"> Back to List </a>" +
                            "</body>" +
                            "</html>");
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }

        out.close();

    }
}
