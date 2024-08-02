package com.example.autowire.constructor;

public class Car {

    //autowire by Constructor will be based on the Constructor;
    private Specification specification;

    //autowire by Constructor will need a constructor
    public Car(Specification specification) {
        this.specification = specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public void displayDetails(){
        System.out.println("Car Details: " + specification.toString());
    }
}
