package EQ_GYM;

import java.util.Scanner;

public abstract class Equipment {
    private String name;
    protected int quantity;
    private int code;
    Equipment(String name){
        this.name=name;
        this.quantity = 0;
    }

    Equipment(String name,int quantity,int code){
        this.name=name;
        this.quantity=quantity;
        this.code=code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    // Function to add equipment based on user input
    public static Equipment addEquipment(Scanner input) {
        System.out.println("Choose the type of equipment:");
        System.out.println("1. Bike");
        System.out.println("2. Dumbbells");
        System.out.println("3. Treadmill");
        System.out.println("4. Leg Press");
        System.out.println("5. Weight Bench");

        int choice = input.nextInt();
        input.nextLine(); // Consume the newline character

        System.out.println("Enter Equipment Name: ");
        String name = input.nextLine();
        System.out.println("Enter Equipment Quatity: ");
        int qnt = input.nextInt();
        System.out.println("Enter Equipment Code: ");
        int code = input.nextInt();

        switch (choice) {
            case 1:
                return new Bike(name, qnt, code);
            case 2:
                return new Dumbbells(name, qnt, code);
            case 3:
                return new Treadmill(name, qnt, code);
            case 4:
                return new Leg_Press(name, qnt, code);
            case 5:
                return new Weight_Bench(name, qnt, code);
            default:
                System.out.println("Invalid choice. Returning null.");
                return null;
        }
    }

    public abstract void display();

}