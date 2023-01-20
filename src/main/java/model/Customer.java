package model;


import java.util.Objects;

public class Customer {

    private int customerID;
    private String customerForename;
    private String customerSurname;
    private Address customerAddress;
    private String customerTelNo;

    public Customer() {
    }

    public Customer(String customerForename, String customerSurname, Address customerAddress, String customerTelNo) {
        this.customerForename = customerForename;
        this.customerSurname = customerSurname;
        this.customerAddress = customerAddress;
        this.customerTelNo = customerTelNo;
    }

    public Customer(int customerID, String customerForename, String customerSurname,
                    Address customerAddress, String customerTelNo) {
        this.customerID = customerID;
        this.customerForename = customerForename;
        this.customerSurname = customerSurname;
        this.customerAddress = customerAddress;
        this.customerTelNo = customerTelNo;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerForename() {
        return customerForename;
    }

    public void setCustomerForename(String customerForename) {
        this.customerForename = customerForename;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public Address getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(Address customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerTelNo() {
        return customerTelNo;
    }

    public void setCustomerTelNo(String customerTelNo) {
        this.customerTelNo = customerTelNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerID == customer.customerID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID);
    }

    /*    @Override
    public String toString() {
        return "Customer: "
                + "\n Customer ID: " + customerID
                + "\n Customer Forename: " + customerForename
                + "\n Customer Surname: " + customerSurname
                + "\n Customer Address: " + customerAddress
                + "\n Telelphone Number: " + customerTelNo;
    }*/

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", customerForename='" + customerForename + '\'' +
                ", customerSurname='" + customerSurname + '\'' +
                ", customerAddress=" + customerAddress +
                ", customerTelNo='" + customerTelNo + '\'' +
                '}';
    }
}
