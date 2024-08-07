package com.example.componentscan.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//Can also specifiy the @Component name using @Component("employee") otherwise will use class name as default with lowerCase
@Component("employee1")
public class Employee {
    private int employeeId;

    //field can be intitlaize using @Value("StringHere")
    @Value("Dave")
    private String firstName;
    @Value("${java.home}")
    private String lastName;
    @Value("#{4*4}")
    private double salary;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
