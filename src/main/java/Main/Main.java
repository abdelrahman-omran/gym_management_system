package Main;

import EQ_GYM.*;
import EQ_GYM.Dumbbells;
import EQ_GYM.Leg_Press;
import EQ_GYM.Treadmill;
import EQ_GYM.Weight_Bench;
import Gym.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.File;
import java.nio.file.Files;
import java.util.Date;

/*import static Gym.Gym.listOfCustomers;
import static Gym.Gym.listOfSubscriptions;
import static Gym.Gym.listOfCoaches;
import static Gym.Gym.sportsEquipment;*/

public class Main {
    static Map<String, Boolean> className = new HashMap<>();
    static ArrayList<Gym> gymObj = new ArrayList<>();
    static ArrayList<MembershipPlan> membershipPlans = new ArrayList<>();
    public static ArrayList<InBody> inBodyList = new ArrayList<>();
    public static void ReadFile(String file)
    {
        File newFile = new File(file);
        try (Scanner scanner = new Scanner(Paths.get(file)))
        {
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                if(line.equals("Gym"))
                {
                    while(true)
                    {
                        line = scanner.nextLine();
                        if(className.get(line)!= null)
                        {
                            break;
                        }
                        String[] attribute = line.split(",");
                        String name = attribute[0];
                        String address = attribute[1];
                        long phoneNumber = Long.valueOf(attribute[2]);
                        System.out.println(name);
                        gymObj.add(new Gym(name, address, phoneNumber));
                    }
                }
                if(line.equals("Equipment"))
                {
                    while(true)
                    {
                        line = scanner.nextLine();
                        if(className.get(line)!= null)
                        {
                            break;
                        }
                        String[] attribute = line.split(",");
                        String name = attribute[0];
                        int code = Integer.valueOf(attribute[1]);
                        int quantity = Integer.valueOf(attribute[2]);
                        switch (name) {
                            case "Dumbbells":
                                Gym.sportsEquipment.add(new Dumbbells(name, quantity, code));
                                break;
                            case "Treadmill":
                                Gym.sportsEquipment.add(new Treadmill(name, quantity, code));
                                break;
                            case "Bike":
                                Gym.sportsEquipment.add(new Bike(name, quantity, code));
                                break;
                            case "Leg_Press":
                                Gym.sportsEquipment.add(new Leg_Press(name, quantity, code));
                                break;
                            case "Weight_Bench":
                                Gym.sportsEquipment.add(new Weight_Bench(name, quantity, code));
                                break;
                        }
                    }
                }
                if(line.equals("MembershipPlan"))
                {
                    while(true)
                    {
                        line = scanner.nextLine();
                        if(className.get(line)!= null)
                        {
                            break;
                        }
                        String[] attribute = line.split(",");

                        String member_name = attribute[0];
                        String sDate = attribute[1];

                        Date start_date = null;
                        try{
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            start_date = dateFormat.parse(sDate);
                        }

                        catch(ParseException e){
                            e.printStackTrace();

                        }
                        int number_of_plan = Integer.valueOf(attribute[2]);

                        membershipPlans.add(new MembershipPlan(member_name,start_date, number_of_plan));
                    }
                }
                if(line.equals("Subscription"))
                {
                    int i = 0;
                    while(true)
                    {
                        line = scanner.nextLine();
                        if(className.get(line)!= null)
                        {
                            break;
                        }

                        String[] attribute = line.split(",");
                        int coach_id = Integer.valueOf(attribute[0]);
                        int costumer_id = Integer.valueOf(attribute[1]);
                        MembershipPlan membershipPlan = membershipPlans.get(i);
                        i++;

                        Gym.listOfSubscriptions.add(new Subscription(coach_id,costumer_id,membershipPlan));
                    }
                }
                if(line.equals("InBody"))
                {
                    while(true)
                    {
                        line = scanner.nextLine();
                        if(className.get(line)!= null)
                        {
                            break;
                        }
                        String[] attribute = line.split(",");
                        double Weight = Double.valueOf(attribute[0]);
                        double Height = Double.valueOf(attribute[1]);
                        int Age = Integer.valueOf(attribute[2]);
                        String Name = attribute[4];
                        String Gender = attribute[3];
                        inBodyList.add(new InBody(Weight,Height,Age,Name,Gender));
                    }
                }
                if(line.equals("Coach"))
                {
                    while(true)
                    {
                        line = scanner.nextLine();
                        if(className.get(line)!= null)
                        {
                            break;
                        }
                        String[] attribute = line.split(",");

                        String Name = attribute[0];
                        int ID = Integer.valueOf(attribute[1]);
                        String Gender = attribute[2];
                        String Adress = attribute[3];
                        int Phone_number =  Integer.valueOf(attribute[4]);
                        String E_mail = attribute[5];
                        int W_H =  Integer.valueOf(attribute[6]);

                        Gym.listOfCoaches.add(new Coach(Name, ID, Gender,Adress, Phone_number,E_mail, W_H));
                    }
                }
                if(line.equals("Customer"))
                {
                    int i = 0;
                    while(true)
                    {
                        line = scanner.nextLine();
                        if(className.get(line)!= null)
                        {
                            break;
                        }
                        String[] attribute = line.split(",");

                        String Name = attribute[0];
                        int ID = Integer.valueOf(attribute[1]);
                        String Gender = attribute[2];
                        String Adress = attribute[3];
                        int Phone_number =  Integer.valueOf(attribute[4]);
                        String E_mail = attribute[5];
                        int coachID =  Integer.valueOf(attribute[6]);

                        Subscription sub = Gym.listOfSubscriptions.get(i);
                        i++;

                        Gym.listOfCustomers.add(new Customer(Name, ID, Gender,Adress,
                                Phone_number, E_mail, coachID,sub));
                    }
                }

            }

        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

    }
    public static void WriteFile(String classOutput)
    {
        try
        {
            FileWriter outputFile = new FileWriter("output.csv");
            outputFile.write(classOutput);
            outputFile.close();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }



    public static void main(String[] args) {

        // Create Map of Classes
        className.put("Gym",true);
        className.put("Equipment", true);
        className.put("Customer", true);
        className.put("Coach", true);
        className.put("Subscription", true);
        className.put("MembershipPlan", true);
        className.put("InBody", true);
        className.put("Admin", true);
        className.put("Exit",true);
        Date date = null;
        String d = "19/12/2001";
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date  = dateFormat.parse(d);
        }

        catch(ParseException e){
            e.printStackTrace();

        }
        System.out.println(date);
        //Gym gym = new Gym("GYMO", "Addddd", 2343242);
        //ArrayList<MembershipPlan> membershipPlans = new ArrayList<>();
        ArrayList<InBody> InBodyList = new ArrayList<>();

        /*Gym.listOfCoaches.add(new Coach("CoacherM", 1, "male", "7 al Street", 07775000, "CoacherM@gmail.com", 8));
        Gym.listOfCoaches.add(new Coach("Coacher2F", 2, "female", "9 el Street", 07775000, "Coacher2F@gmail.com", 6));
        Gym.listOfCustomers.add(new Customer("Cu1", 101, "male", "17 Moon Street", 500090, "Customer1@hotmail.com", 2));
        Gym.listOfCustomers.add(new Customer("Cu2", 102, "female", "18 Sun Street", 500500, "Customer255@gmail.com", 1));
        Gym.listOfCoaches.get(0).List_of_customers.add(Gym.listOfCustomers.get(1));
        System.out.println("First Coach Cust No = " + Gym.listOfCoaches.get(0).List_of_customers.toArray().length); // Debugging
        System.out.println("All Gym Customer number = " + Gym.listOfCustomers.toArray().length); // Debugging*/
        //
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the file path");
        String file = input.nextLine();
        
        // Read File
        ReadFile(file);
        System.out.println("Coaches Length: " + Gym.listOfCoaches.toArray().length);
        System.out.println("Customer Length: " + Gym.listOfCustomers.toArray().length);
        System.out.println("Equipment Length: " + Gym.sportsEquipment.toArray().length);
        System.out.println("Subscription Length: " + Gym.listOfSubscriptions.toArray().length);
        System.out.println("Membership Length: " + membershipPlans.toArray().length);
        System.out.println("InBody Length: " + inBodyList.toArray().length);
        // Sign in & Choose Role
        
        boolean run = true;
        while(run) {
            boolean nextFunction = true;
            System.out.println("Who are you little guy ?(Admin, Coach, Customer) (type 'Stop' to run)");
            String role = input.nextLine();
            int i = 0;
            switch (role)
            {
            case "Customer":
                    //Scanner scan = new Scanner(System.in);
                    Customer customer =  null;
                    while(customer == null){
                        System.out.println("Enter you name:");
                        String cName = input.nextLine();
                        for(Customer co: Gym.listOfCustomers){
                            if(co.getName().equals(cName)){
                                customer = co;
                            }
                        }
                        //
                        if (customer == null){
                            System.out.println("No Customer exists with the provided name");
                            System.out.println("Exit (e) or Retry(r) ?");
                            String option = input.nextLine();
                            if(option.equals("e")){
                                break;
                            }
                        }

                    }
                    // Check password
                    if(customer != null) customer.readScenario();
                    break;

                case "Coach":
                    //Scanner scan = new Scanner(System.in);
                    Coach coach =  null;
                    while(coach == null){
                        System.out.println("Enter your name:");
                        String cName = input.nextLine();
                        //System.out.println(Gym.listOfCoaches.toArray().length);
                        for(Coach co: Gym.listOfCoaches){
                            //System.out.println(co.getName());
                            if(co.getName().equals(cName)){
                                coach = co;
                            }
                        }
                        //
                        if (coach == null){
                            System.out.println("No Coach exists with the provided name");
                            System.out.println("Exit (e) or Retry(r) ?");
                            String option = input.nextLine();
                            if(option.equals("e")){
                                break;
                            }
                        }
                    }
                    //
                    if(coach != null) coach.readScenario();
                    break;
                case "Admin":
                    // Check Username and Password
                    String userName, pass;
                    userName = pass = null;
                    while(!userName.equals("Admin") || !pass.equals("Admin")){
                        System.out.println("Enter");
                    }

                    break;
                case "Stop":
                    run = false;
                    break;
                default:
                    System.out.println("Get out of here !!!");

            }
        }
        // Write Output

    }
}