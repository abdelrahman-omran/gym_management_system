package Gym;

import EQ_GYM.Equipment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Date;

public abstract class Admin {

    public static Date getUserDate(Scanner input){
        /*int day, month, year;
        System.out.println("Enter the Date you want to specify: (a single number for each value, 0 for any)");
        System.out.println("Day: ");
        day = input.nextInt();
        System.out.println("Month: ");
        month = input.nextInt();
        System.out.println("Year: ");
        year = input.nextInt();
        //
        return (new Date(day, month, year));*/
        return new Date();
    }

    private enum userType {
        COACH,
        CUSTOMER
    }
    
    private static Person existCheck(int ID, userType t){
        if(t == userType.COACH){
            for (Coach coach: Gym.listOfCoaches) {
                if (coach.getID() == ID) {
                    return coach;
                }
            }
        }
        else{
            for (Customer customer: Gym.listOfCustomers) {
                if (customer.getID() == ID) {
                    return customer;
                }
            }
        }

        //
        return null;
    }

    public static void addCoach(Scanner input) {
        System.out.println("Enter the Coach's Name:");
        String name = input.nextLine();

        System.out.println("Enter the Coach's ID:");
        int id = input.nextInt();

        System.out.println("Enter the Coach's Gender:");
        String gender = input.nextLine();

        System.out.println("Enter the Coach's Address:");
        String address = input.nextLine();

        System.out.println("Enter the Coach's Phone Number:");
        int phoneNumber = input.nextInt();

        System.out.println("Enter the Coach's Email:");
        String email = input.next();

        System.out.println("Enter the Coach's Working Hours:");
        int workingHours = input.nextInt();
        System.out.println("Enter the Coach's Top Secret Password: ");
        String password = input.next();

        //
        Gym.listOfCoaches.add(new Coach(name, id, gender, address, phoneNumber, email, workingHours, password));
        System.out.println("Coach added successfully!");
    }


    // Edit Coach Info
    public static void editCoach(int coachID, Scanner input) {
        Coach coachEdit = (Coach)existCheck(coachID, userType.COACH);
        if (coachEdit == null) {
            System.out.println("No Coach was found with the provided ID");
            return;
        }
        //
        System.out.println("Choose the information to edit:");
        System.out.println("1. Name");
        System.out.println("2. Gender");
        System.out.println("3. Address");
        System.out.println("4. Phone Number");
        System.out.println("5. Email");
        System.out.println("0. Cancel");

        int choice = input.nextInt();
        input.nextLine();
        //
        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                coachEdit.setName(input.nextLine());
                break;
            case 2:
                System.out.print("Enter new gender: ");
                coachEdit.setGender(input.nextLine());
                break;
            case 3:
                System.out.print("Enter new address: ");
                coachEdit.setAdress(input.nextLine());
                break;
            case 4:
                System.out.print("Enter new phone number: ");
                coachEdit.setPhone_number(input.nextInt());
                break;
            case 5:
                System.out.print("Enter new email: ");
                coachEdit.setE_mail(input.nextLine());
                break;
            case 0:
                System.out.println("Editing canceled.");
                return;
            default:
                System.out.println("Invalid choice. Editing canceled.");
                return;
        }
        System.out.println("Coach information updated successfully.");
    }

    
    public static void deleteCoach(int coachID){
        Coach coachCheck = (Coach)existCheck(coachID,userType.COACH);
        if(coachCheck==null){
            System.out.println("No Coach exists with this ID.");
            return;
        }
        //
        Gym.listOfCoaches.removeIf(coach -> coach.getID() == coachID);
        Gym.listOfCustomers.removeIf(customer -> customer.getCoachID() == coachID);
        Gym.listOfSubscriptions.removeIf(sub -> sub.getCoach_id() == coachID);
        //
        System.out.println("Coach deleted successfully");

    }

    public static void addCustomer(Scanner input) {
        System.out.println("Enter the Customer's Name:");
        String name = input.nextLine();

        System.out.println("Enter the Customer's ID:");
        int id = input.nextInt();

        System.out.println("Enter the Customer's Gender:");
        String gender = input.nextLine();
        //input.nextLine(); // Consume the newline character

        System.out.println("Enter the Customer's Address:");
        String address = input.nextLine();

        System.out.println("Enter the Customer's Phone Number:");
        int phoneNumber = input.nextInt();

        System.out.println("Enter the Customer's Email:");
        String email = input.next();

        System.out.println("Enter the ID of the Coach for the Customer:");
        int coachId = input.nextInt();

        System.out.println("Enter the Customer's Top Secret Password: ");
        String password = input.next();

        // Find the coach with the specified ID and add the customer to their list
        boolean coachIdCorrect=false;
        for (Coach coach : Gym.listOfCoaches) {
            if (coach.getID() == coachId) {
                // Add the new customer to the list of Gym customers

                // Add the new customer to the list of Coach customers
                if(coach.number_of_customers > 9){
                    System.out.println("Currently this Coach is training the Max number of customers (10)");
                    System.out.println("Customer not added.");
                    return;
                }
                // Add to all gym customers
                Gym.listOfCustomers.add(new Customer(name, id, gender, address, phoneNumber, email, coachId, password));
                // Add to Coach customers
                coach.List_of_customers.add(Gym.listOfCustomers.get(Gym.listOfCustomers.toArray().length-1));

                coach.number_of_customers++;
                coachIdCorrect=true;
                break;
            }
        }
        if(!coachIdCorrect){
            System.out.println("No Coach exists with the specified Coach ID");
            return;
        }

        System.out.println("Customer added successfully!");
    }

    // Edit Customer Info
    public static void editCustomer(int customerID, Scanner input){
        Customer customerEdit = (Customer)existCheck(customerID, userType.CUSTOMER);
        if(customerEdit==null){
            System.out.println("No Customer was found with the provided ID");
            return;
        }
        //
        System.out.println("Choose the information to edit:");
        System.out.println("1. Name");
        System.out.println("2. Gender");
        System.out.println("3. Address");
        System.out.println("4. Phone Number");
        System.out.println("5. Email");
        System.out.println("6. Coach ID");
        System.out.println("0. Cancel");

        int choice = input.nextInt();
        input.nextLine();
        //
        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                customerEdit.setName(input.nextLine());
                break;
            case 2:
                System.out.print("Enter new gender: ");
                customerEdit.setGender(input.nextLine());
                break;
            case 3:
                System.out.print("Enter new address: ");
                customerEdit.setAdress(input.nextLine());
                break;
            case 4:
                System.out.print("Enter new phone number: ");
                customerEdit.Phone_number = input.nextInt();
                break;
            case 5:
                System.out.print("Enter new email: ");
                customerEdit.E_mail = input.nextLine();
                break;
            case 6:
                System.out.print("Enter new coach ID: ");
                customerEdit.coachID = input.nextInt();
                break;
            case 0:
                System.out.println("Editing canceled.");
                return;
            default:
                System.out.println("Invalid choice. Editing canceled.");
                return;
        }
        System.out.println("Customer information updated successfully.");
    }
    
    public static void deleteCustomer(int customerID){
        Customer customerCheck = (Customer)existCheck(customerID,userType.CUSTOMER);
        if(customerCheck==null) {
            System.out.println("No Customer exists with this ID.");
            return;
        }
        for(Subscription sub: Gym.listOfSubscriptions){
            if(sub.getCostumer_id() == customerID){
                deleteSub(sub);
            }
        }
        //
        Coach coach = (Coach)existCheck(customerCheck.coachID, userType.COACH);
        if(coach != null) coach.List_of_customers.removeIf(customer -> customer.getID() == customerID);
        Gym.listOfCustomers.removeIf(customer -> customer.getID() == customerID);
        Gym.listOfSubscriptions.removeIf(sub -> sub.getCostumer_id() == customerID);
        System.out.println("Customer deleted successfully.");

    }

    public static void deleteSub(Subscription sub){
        Gym.listOfSubscriptions.remove(sub);
    }

    
    public static void addEquipment(Scanner input){
        // Add the equipment to the gym's list
        Gym.sportsEquipment.add(Equipment.addEquipment(input));
    }

    public static void editEquipment(int equipmentCode, Scanner input) {
        Equipment equipmentEdit = null;

        // Check if the equipment with the provided code exists
        for (Equipment equipment : Gym.sportsEquipment) {
            if (equipment.getCode() == equipmentCode) {
                equipmentEdit = equipment;
                break;
            }
        }

        if (equipmentEdit == null) {
            System.out.println("No equipment was found with the provided code");
            return;
        }

        System.out.println("Choose the information to edit:");
        System.out.println("1. Name");
        System.out.println("2. Quantity");
        System.out.println("0. Cancel");

        int choice = input.nextInt();
        input.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                equipmentEdit.setName(input.nextLine());
                break;
            case 2:
                System.out.print("Enter new quantity: ");
                equipmentEdit.setQuantity(input.nextInt());
                break;
            case 0:
                System.out.println("Editing canceled.");
                return;
            default:
                System.out.println("Invalid choice. Editing canceled.");
                return;
        }

        System.out.println("Equipment information updated successfully.");
    }

    
    public static void deleteEquipment(int equipmentCode){
        // search and add
        Gym.sportsEquipment.removeIf(equipment -> equipment.getCode() == equipmentCode);
    }


    public static void showSubscriptionHistory(int customerID){
        for(Subscription sub: Gym.listOfSubscriptions){
            if(sub.getCostumer_id() == customerID){
                sub.getMembershipPlan().display();
            }
        }
    }
    
    // Display all the customers that subscribed to the gym in a given month/day
    public static void displayCustomersInDate(Scanner input){
        System.out.println("Month(m) or Day(d)?");
        String c = input.nextLine();
        if(c.equals("m")){
            System.out.println("Enter a Month: 1-12");
            int month = input.nextInt();
            for(Customer customer: Gym.listOfCustomers){
                if(customer.subscription.getMembershipPlan().start_date.getMonth()+1 == month){
                    customer.display();
                    System.out.println("--------");
                }
           }
        }
        //
        else if(c.equals("d")) {
            System.out.println("Enter a Day: 1:31");
            int day = input.nextInt();
            for (Customer customer : Gym.listOfCustomers) {
                if (customer.subscription.getMembershipPlan().start_date.getDay()+1 == day) {
                    customer.display();
                    System.out.println("--------");
                }
            }
        }
        else{
            System.out.println("Invalid Choice, Out!!!");
        }
    }
    
    // Display all the customers of a specific coach
    public static void displayCoachCustomers(int coachID){
        for(Customer customer: Gym.listOfCustomers){
            if(customer.getCoachID() == coachID){
                customer.display();
                System.out.println("--------");
            }
        }
    }
    
    // Display the GYM income in a given month
    public static void displayGymIncome(Scanner input){
        double income = 0;
        int month, year;
        System.out.println("Enter year: (2000-2050)");
        year = input.nextInt();
        System.out.println("Enter month: (1-12)");
        month = input.nextInt();
        for(Subscription sub: Gym.listOfSubscriptions){
            MembershipPlan mem = sub.getMembershipPlan();
            if(mem.start_date.getMonth()+1 == month && mem.start_date.getYear()+1 == year){
                income+= mem.discount_price(mem.number_of_plan);
            }
        }
        //
        System.out.println("The income in month " + month + " of the year " + year + " Was: " + income);
    }
    
    // Display Gym Coaches, sorted descendingly according to their number of customers
    public static void displaySortedCoaches(){
        ArrayList<Coach> sortedCoaches = new ArrayList<>(Gym.listOfCoaches);

        // Sort the coaches based on the number of customers (descending order)
        Collections.sort(sortedCoaches);
        //
        for(Coach coach: sortedCoaches){
            coach.display();
            System.out.println("No. of customers: " + coach.number_of_customers);
            System.out.println("--------");
        }
    }

    public static void readScenario(Scanner input) {
        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Add/Edit/Delete Coaches, Equipment, and Customers");
            System.out.println("2. Show Subscription History for a Customer");
            System.out.println("3. Display Customers Subscribed in a Month/Day");
            System.out.println("4. Display Customers of a Specific Coach");
            System.out.println("5. Display Gym Income in a Given Month");
            System.out.println("6. Display Coaches Sorted by Assigned Customers");
            System.out.println("0. Exit");

            int choice = input.nextInt();
            input.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    manageObjects(input);
                    break;
                case 2:
                    System.out.println("Enter the Customer's ID: ");
                    int cuID = input.nextInt();
                    showSubscriptionHistory(cuID);
                    break;
                case 3:
                    displayCustomersInDate(input);
                    break;
                case 4:
                    System.out.println("Enter the Coach's ID: ");
                    int coID = input.nextInt();
                    displayCoachCustomers(coID);
                    break;
                case 5:
                    displayGymIncome(input);
                    break;
                case 6:
                    displaySortedCoaches();
                    break;
                case 0:
                    System.out.println("Exiting the admin panel.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void manageObjects(Scanner input) {
        System.out.println("Choose an action:");
        System.out.println("1. Add Coach");
        System.out.println("2. Edit Coach");
        System.out.println("3. Delete Coach");
        System.out.println("4. Add Customer");
        System.out.println("5. Edit Customer");
        System.out.println("6. Delete Customer");
        System.out.println("7. Add Equipment");
        System.out.println("8. Edit Equipment");
        System.out.println("9. Delete Equipment");
        System.out.println("0. Back");

        int choice = input.nextInt();
        input.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                addCoach(input);
                break;
            case 2:
                System.out.println("Enter the Coaches ID: ");
                editCoach(input.nextInt(), input);
                break;
            case 3:
                System.out.println("Enter the Coaches ID: ");
                deleteCoach(input.nextInt());
                break;
            case 4:
                addCustomer(input);
                break;
            case 5:
                System.out.println("Enter the Customer's ID: ");
                editCustomer(input.nextInt(), input);
                break;
            case 6:
                System.out.println("Enter the Customer's ID: ");
                deleteCustomer(input.nextInt());
                break;
            case 7:
                addEquipment(input);
                break;
            case 8:
                System.out.println("Enter the Equipment Code: ");
                editEquipment(input.nextInt(), input);
                break;
            case 9:
                System.out.println("Enter the Equipment's Code: ");
                deleteEquipment(input.nextInt());
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public static void registerUser(String role, Scanner input){
        switch(role){
            case "Customer":
                addCustomer(input);
            case "Coach":
                addCoach(input);
            default:
                System.out.println("Invalid Choice. Outtt");
        }
    }

}
