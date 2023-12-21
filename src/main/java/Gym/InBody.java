package Gym;

import java.text.SimpleDateFormat;
import java.util.Date;


public class InBody {
    private double Price;
    private double Weight;
    private double Height;
    private double Calories;
    private String Name;
    private String Gender;
    private int Age;
    Date date = null;
    SimpleDateFormat st= new SimpleDateFormat("dd/MM/yyyy"+"\t"+"hh:mm");



    public InBody(double Weight, double Height, int Age, String Name, String Gender,Date date) {
        this.Weight = Weight;
        this.Height = Height;
        this.Age = Age;
        this.Name = Name;
        this.Gender = Gender;
        this.date = date;
    }

    public InBody(double Price){

    }

    private double BMR() {

        if (Gender.equals("male") || Gender.equals("Male")) {
            Calories = (((10 * Weight) + (6.25 * Height)) - (5 * Age) + 5);
        } else if (Gender.equals("female") || Gender.equals("Female")) {
            Calories = (((10 * Weight) + (6.25 * Height)) - (5 * Age) - 161);
        }
        return Calories;
    }

    private void Bulk() {
        System.out.println("To gain half a kilogram per week, you must eat \"" + (Calories + 500) + "\" calories per day");
        System.out.println("To gain a kilogram per week, you must eat \"" + (Calories + 1000) + "\" calories per day");
    }

    private void Cut() {
        System.out.println("To lose half a kilogram per week, you must eat \"" + (Calories - 500) + "\" calories per day");
        System.out.println("To lose a kilogram per week, you must eat \"" + (Calories - 1000) + "\" calories per day");
    }

    protected void howManyKilosToReduce(){
        if (Gender.equals("Male")||Gender.equals("male")){
            if(Weight ==(50+2.3*((Height/100*39.37)-60))){
                System.out.println("Your weight is ideal");
            }
            else if (Weight > (50+2.3*((Height/100*39.37)-60))){
                System.out.println("You must lose \""+(int) (Weight - (50+2.3*((Height/100*39.37)-60))) +"\" kilogram to reach the ideal weight");
            }
            else {
                System.out.println("You must gain \""+(int) ((50+2.3*((Height/100*39.37)-60)) - Weight) +"\" kilogram to reach the ideal weight");
            }
        }
        else if (Gender.equals("Female")||Gender.equals("female")){
            if(Weight ==(49+1.7*((Height/100*39.37)-60))){
                System.out.println("Your weight is ideal");
            }
            else if (Weight > (49+1.7*((Height/100*39.37)-60))){
                System.out.println("You must lose \""+(int)(Weight - (49+1.7*((Height/100*39.37)-60))) +"\" kilogram to reach the ideal weight");
            }
            else {
                System.out.println("You must gain \""+(int)((49+1.7*((Height/100*39.37)-60)) - Weight) +"\" kilogram to reach the ideal weight");
            }
        }
    }

    private void BodyNeeds(){
        System.out.println("Your protein needs : "+(Weight*1.9));
        System.out.println("Your healthy fat needs : "+((.3*Calories)/9));
        System.out.println("Your carb needs : "+((Calories-((.3*Calories)/9)+(Weight*1.9))/4));
    }

    private void MemberInfo(){
        System.out.println("Name\t\tGender\t\tAge\t\tHeight\t\tWeight\t\tTime of Measurement");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(Name+"\t\t"+Gender+"\t\t"+Age+"\t\t"+Height+"\t\t"+Weight+"\t\t"+st.format(date)+"\n\n");
    }
    public String getName()
    {
        return Name;
    }
    public String getGender(){
        return Gender;
    }
    public double getWeight(){
        return Weight;
    }
    public double getHeight()
    {
        return Height;
    }
    public int getAge()
    {
        return Age;
    }

    public Date getDate()
    {
        return date;
    }

    public void Display(){
        BMR();
        MemberInfo();
        System.out.println("The daily calories you need : "+Calories);
        System.out.println("------------------------------------");
        Bulk();
        System.out.println("------------------------------------------------------------------------");
        Cut();
        System.out.println("------------------------------------------------------------------------");
        BodyNeeds();
    }
}