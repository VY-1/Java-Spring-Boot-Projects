package com.example.autowire.name;

public class Car {

    //autowire by name will be based on the variable name; ex: specification
    private Specification specification;

    //autowire by name will need a setter instead of constructor. setter will be corresponded to the name of the variable
    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public void displayDetails(){
        System.out.println("Car Details: " + specification.toString());
    }
}
