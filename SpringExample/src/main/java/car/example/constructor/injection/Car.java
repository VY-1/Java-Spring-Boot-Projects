package car.example.constructor.injection;

public class Car {

    private Specification specification;

    //Constructor Dependency Injection based on specification. A Constructor is needed.
    public Car(Specification specification) {
        this.specification = specification;
    }

    public void displayDetails(){
        System.out.println("Car Details: " + specification.toString());
    }
}
