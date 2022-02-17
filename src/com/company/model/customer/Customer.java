package com.company.model.customer;
import java.util.Objects;
import java.util.regex.Pattern;


public class Customer {
    private static final String emailRegex = "^(.+)@(.+).com$";
    String firstName;
    String lastName;
    String email;

    private final Pattern pattern = Pattern.compile(emailRegex);


    public Customer(String firstName, String lastName, String email) {
        isValidEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private void isValidEmail(String email){
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Error,Invalid Email");
        }
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer" + "\n" +
                "firstName=" + firstName + '\n' +
                 "lastName=" + lastName + '\n' +
                "email=" + email ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(email, customer.email) && Objects.equals(pattern, customer.pattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, pattern);
    }
}
