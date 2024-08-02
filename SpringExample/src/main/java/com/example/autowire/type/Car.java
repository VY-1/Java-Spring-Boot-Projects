package com.example.autowire.type;

public class Car {

    //autowire by Type will be based on the Class name; ex: Specification
    private Specification specification;

    //autowire by type will need a setter. setter will be corresponded to the name of the type
    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public void displayDetails(){
        System.out.println("Car Details: " + specification.toString());
    }
}
