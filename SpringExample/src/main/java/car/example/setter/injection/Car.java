package car.example.setter.injection;

public class Car {

    private Specification specification;

    //Setter Dependency Injection based on specification. A setter is needed.
    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public void displayDetails(){
        System.out.println("Car Details: " + specification.toString());
    }
}
