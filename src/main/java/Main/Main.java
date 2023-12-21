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
import java.text.Format;
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

    static Scanner input = new Scanner(System.in);
    public static Date stringToDate(String sDate)
    {
        Date date = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            date = dateFormat.parse(sDate);
        }
        catch(ParseException e){
            e.printStackTrace();

        }
        return date;
    }
    public static String dateToString(Date date)
    {
            Format formatter = new SimpleDateFormat("dd/MM/yyyy");
            String sDate = formatter.format(date);
            return sDate;
    }


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
                        Date start_date = stringToDate(sDate);
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
                        String sDate = attribute[5];
                        Date date = stringToDate(sDate);
                        inBodyList.add(new InBody(Weight,Height,Age,Name,Gender,date));
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
                        String password = attribute[7];

                        Gym.listOfCoaches.add(new Coach(Name, ID, Gender,Adress, Phone_number,E_mail, W_H,password));
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
                        String password = attribute[7];

                        Subscription sub = Gym.listOfSubscriptions.get(i);
                        i++;

                        Gym.listOfCustomers.add(new Customer(Name, ID, Gender,Adress,
                                Phone_number, E_mail, coachID,sub,password));
                        // add to coaches lists
                        for(Coach coach: Gym.listOfCoaches){
                            if(coach.getID()==coachID){
                                coach.List_of_customers.add(Gym.listOfCustomers.get(Gym.listOfCustomers.toArray().length-1));
                            }
                        }
                    }
                }

            }

        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

    }
    public static void WriteFile()
    {
        try
        {
            String sDate;
            FileWriter outputFile = new FileWriter("input1.txt");
            outputFile.write("Gym\n");
            for(Gym gym : gymObj)
                outputFile.write(gym.getName()+','+gym.getAddress()+','+gym.getPhoneNumber()+"\n");
            outputFile.write("Equipment\n");
            for(Equipment eq : Gym.sportsEquipment)
                outputFile.write(eq.getName()+','+eq.getCode()+','+eq.getQuantity()+"\n");
            outputFile.write("MembershipPlan\n");
            for(MembershipPlan mem : membershipPlans) {
                sDate = dateToString(mem.getStart_date());
                outputFile.write(mem.getMember_name() + ',' +sDate+ ',' + mem.getNumberOfMonths() + ',' + mem.getNumber_of_plan() + "\n");
            }
            outputFile.write("Subscriptions\n");
            for(Subscription sub : Gym.listOfSubscriptions)
                outputFile.write(sub.getCoach_id()+','+ sub.getCostumer_id()+','+"\n");
            outputFile.write("InBody\n");
            for(InBody inbody : inBodyList) {
                sDate = dateToString(inbody.getDate());
                outputFile.write(inbody.getWeight() + ',' + inbody.getHeight() + ',' + inbody.getAge() + ',' + inbody.getGender() + ',' + inbody.getName() + ',' +sDate+ "\n");
            }
            outputFile.write("Coach\n");
            for(Coach co : Gym.listOfCoaches)
                outputFile.write(co.getName()+','+co.getID()+','+co.getGender()+','+co.getAddress()+','+co.getPhone_number()+','+co.getE_mail()+','+co.getWorking_hours()+','+co.getPassword()+"\n");
            outputFile.write("Customer\n");
            for(Customer cu : Gym.listOfCustomers)
                outputFile.write(cu.getName()+','+cu.getID()+','+cu.getGender()+','+cu.getAddress()+','+cu.getPhone_number()+','+cu.getE_mail()+','+cu.getCoachID()+','+cu.getPassword()+"\n");
            outputFile.write("Exit\n");

            outputFile.close();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    static Person log_in(String role) {
        if (role.equals("Customer")) {
            Customer customer = null;
            while (customer == null) {
                System.out.println("Enter you name:");
                String cName = String.valueOf(input.nextLine());
                System.out.println("Enter you password:");
                String cPassword = String.valueOf(input.nextLine());
                for (Customer co : Gym.listOfCustomers) {
                    if (co.getName().equals(cName) && co.getPassword().equals(cPassword)) {
                        customer = co;
                        System.out.println("Login successful!");
                        System.out.println("Welcome mr :" + co.getName());
                    }
                }
                //
                if (customer == null) {
                    System.out.println("Invalid username or password");
                    System.out.println("Exit (e) or Retry(r) ?");
                    String option = input.nextLine();
                    if (option.equals("e")) {
                        break;
                    }
                }
            }
            // Check password
            if (customer != null) return customer;
        }
        else if (role.equals("Coach")) {
            Coach coach = null;
            while (coach == null) {
                System.out.println("Enter you name:");
                String cName = String.valueOf(input.nextLine());
                System.out.println("Enter you password:");
                String cPassword = String.valueOf(input.nextLine());
                for (Coach co : Gym.listOfCoaches) {
                    if (co.getName().equals(cName) && co.getPassword().equals(cPassword)) {
                        coach = co;
                        System.out.println("Login successful!");
                        System.out.println("Welcome mr :" + co.getName());
                    }
                }
                //
                if (coach == null) {
                    System.out.println("Invalid username or password");
                    System.out.println("Exit (e) or Retry(r) ?");
                    String option = input.nextLine();
                    if (option.equals("e")) {
                        break;
                    }
                }
            }
            // Check password
            if (coach != null) return coach;
        }
        return null;
    }

    static void sign_up(Scanner input, String role) {
        while (true) {
            System.out.println("  SIGN UP  ");
            System.out.println("------------");
            //
            System.out.println("You have to call an Admin Boiii");
            String userName, pass, choice="r";
            //
            while(true){
                System.out.println("Enter the admin Username: ");
                userName = input.nextLine();
                System.out.println("Enter the admin Password: ");
                pass = input.nextLine();
                //
                if(!userName.equals("Admin") || !pass.equals("Admin")){
                    System.out.println("Invalid Credentials, Retry(r) or Exit(e)?");
                    choice = input.nextLine();
                    if(choice.equals("r"))
                        continue;
                    else
                        break;
                }
                break;
            }
            if(choice.equals("r")) {
                Admin.registerUser(role, input);
                System.out.println("You registered successfully.");
            }
            break;

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
        ArrayList<InBody> InBodyList = new ArrayList<>();
        String dat = "07/05/2022";
        Date date1 = stringToDate(dat);
        System.out.println(dat);
        String dates = dateToString(date1);
        System.out.println(dates);
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
        WriteFile();
        System.out.println("Coaches Length: " + Gym.listOfCoaches.toArray().length);
        System.out.println("Customer Length: " + Gym.listOfCustomers.toArray().length);
        System.out.println("Equipment Length: " + Gym.sportsEquipment.toArray().length);
        System.out.println("Subscription Length: " + Gym.listOfSubscriptions.toArray().length);
        System.out.println("Membership Length: " + membershipPlans.toArray().length);
        System.out.println("InBody Length: " + inBodyList.toArray().length);
        // Sign in & Choose Role

        boolean run = true;
        String start;
        while(run) {
            boolean nextFunction = true;
            System.out.println("Who are you little guy ?(Admin, Coach, Customer) (type 'Stop' to run)");
            String role = input.nextLine();
            int i = 0;
            //
            switch (role)
            {
                case "Customer":
                    System.out.println("Register(r) or Login(l) ?");
                    start = input.nextLine();
                    if(start.equals("r")){
                        sign_up(input, role);
                    }
                    else{
                        log_in(role);
                    }
                    Customer customer = (Customer)log_in(role);
                    // Check password
                    if(customer != null) customer.readScenario(input);
                    break;
                case "Coach":
                    System.out.println("Register(r) or Login(l) ?");
                    start = input.nextLine();
                    if(start.equals("r")){
                        sign_up(input, role);
                    }
                    else{
                        log_in(role);
                    }
                    Coach coach =  null;
                    coach = (Coach)log_in(role);
                    //
                    if(coach != null) coach.readScenario(input);
                    break;
                case "Admin":
                    // Check Username and Password
                    String userName, pass, choice="r";
                    userName = pass = " ";
                    while(true){
                        System.out.println("Enter the admin Username: ");
                        userName = input.nextLine();
                        System.out.println("Enter the admin Password: ");
                        pass = input.nextLine();
                        //
                        if(!userName.equals("Admin") || !pass.equals("Admin")){
                            System.out.println("Invalid Credentials, Retry(r) or Exit(e)?");
                            choice = input.nextLine();
                            if(choice.equals("r"))
                                continue;
                            else
                                break;
                        }
                        break;
                    }
                    if(choice.equals("r")){
                        Admin.readScenario(input);
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