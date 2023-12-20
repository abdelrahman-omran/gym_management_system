package Gym;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

import static Main.Main.inBodyList;

public class Coach extends Person implements Comparable<Coach> {
    int working_hours;
    int number_of_customers;
    public ArrayList<Customer> List_of_customers = new ArrayList<>();


    public Coach(String Name , int ID , String Gender ,String Adress ,int Phone_number, String E_mail, int W_H){
        super(Name, ID, Gender, Adress, Phone_number, E_mail);
        this.working_hours = W_H;
        this.number_of_customers = 0;
    }

    public Coach(){
        this("",0,"","",0,"",0);
    }
    
    public Coach(Coach coach){
        super(coach.getName(), coach.getID(), coach.getGender(), coach.getAdress(),
                coach.getPhone_number(), coach.getE_mail());
        this.working_hours = coach.working_hours;
        
    }
    public void addCustomerToCoach()
    {
        for(Customer cu: Gym.listOfCustomers)
        {
            if(cu.coachID == this.getID())
                this.List_of_customers.add(cu);
        }
    }

    void display (){
        System.out.println("Name: "+Name);
        System.out.println("ID:"+this.getID());
        System.out.println("Gender: " + Gender);
        System.out.println("Address: "+this.getAdress());
        System.out.println("Phone_number: "+Phone_number);
        System.out.println("E_mail: "+E_mail);
        System.out.println("Working Hours: "+working_hours);
    }

    public  void read (){
        this.read_data();
        Scanner input = new Scanner (System.in);
        // Read the number of working hours
        System.out.print("How many hours do you work per day?");
        while (true){
            this.working_hours =input.nextInt();
            if(this.working_hours > 10){
                System.out.println("please enter correct number ");
            }
            else {
                break;
            }

        }
        // Read number of customers
        System.out.print("How many customers are you training?");
        while (true){
            number_of_customers = input.nextInt();
            if(number_of_customers > 10){
                System.out.println("please enter correct number ");
            }
            else {
                break;
            }
         }
    }
    
    void show_customers (){
        for (Customer cu: Gym.listOfCustomers){
            int i =1 ;
            if(cu.coachID == this.getID())
                cu.display();
            System.out.println("-------");
            i++;
        }
     }
    
    void show_list_of_inbodies (Customer cus){
        cus.addInBody();
         for (InBody in : cus.List_of_inbodies){         
             in.Display();     
         }
     }
     
    void show_details_of_Customer (String name){
        System.out.println("Length: " + Gym.listOfCustomers.toArray().length);
         for (Customer cu: Gym.listOfCustomers){
             if(cu.getName().equals(name)) {
                 cu.display();
                 System.out.println("------------");
             }
        }
    }
    
    void show_details_of_Customer_gender (String gender){
        addCustomerToCoach();
        System.out.println(this.List_of_customers.toArray().length);
        for (Customer cu: this.List_of_customers){
            int i =1 ;
            if(gender.equals(cu.Gender) ){
               System.out.println("\t\tcustomer 1");  
                cu.display();
                i++;
            }
        }
    }
    
    //@override: Comparing descendingly
    public int compareTo(Coach compareCoach){
        int com = ((Coach)compareCoach).number_of_customers;
        
        return com-this.number_of_customers;
    }

    public int getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(int working_hours) {
        this.working_hours = working_hours;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setPhone_number(int Phone_number) {
        this.Phone_number = Phone_number;
    }

    public void setE_mail(String E_mail) {
        this.E_mail = E_mail;
    }

    public void readScenario() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCoach Functionalities: (Enter the corresponding number)");
            System.out.println("1. Show a list of all his customers.");
            System.out.println("2. Get the inbody history of any of his customers.");
            System.out.println("3. Get all the details of a customer by his name.");
            System.out.println("4. Show a list of all his female/male customers.");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nList of All Customers:");
                    if(Gym.listOfCustomers.isEmpty())
                        System.out.println("You have no Customers.");
                    else
                        this.show_customers();
                    break;
                case 2:
                    System.out.print("\nEnter customer name to get inbody history: ");
                    String customerName = scanner.nextLine();
                    System.out.println(List_of_customers.toArray().length);
                    for (Customer customer : Gym.listOfCustomers) {
                        if (customerName.equals(customer.getName())) {
                            System.out.println("\nInBody History of " + customerName + ":");
                            show_list_of_inbodies(customer);
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.print("\nEnter customer name to get details: ");
                    String customerDetailsName = scanner.nextLine();
                    System.out.println("\nDetails of Customer " + customerDetailsName + ":");
                    show_details_of_Customer(customerDetailsName);
                    break;
                case 4:
                    System.out.print("\nEnter 'male' or 'female' to show customers of that gender: ");
                    String genderChoice = scanner.nextLine();
                    System.out.println("\nList of " + genderChoice + " Customers:");
                    show_details_of_Customer_gender(genderChoice);
                    break;
                case 0:
                    System.out.println("Exiting Coach Functionalities.");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
    
    

}
