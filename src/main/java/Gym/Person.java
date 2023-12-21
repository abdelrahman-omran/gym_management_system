package Gym;

import java.util.Scanner;


public  abstract class Person {
    public String Name ;
    private int ID ;
    String Gender ;
    private String Address ;
    int Phone_number ;
    protected String E_mail;
    private String password;

    public Person (String Name, int ID, String Gender, String Address, int Phone_number, String E_mail, String password){
        this.Name =Name;
        this.ID =ID;
        this.Gender =Gender;
        this.Address=Address;
        this.Phone_number =Phone_number;
        this.E_mail=E_mail;
        this.password = password;
    }

    public Person (){ //defalt constractor
        this("",0,"","",0,"", "");
    }

    public int getID() {
        return ID;
    }

    public String getAddress() {
        return Address;
    }

    public String getName() {
        return Name;
    }

    public String getPassword(){
        return password;
    }

    public String getGender() {
        return Gender;
    }

    public int getPhone_number() {
        return Phone_number;
    }

    public String getE_mail() {
        return E_mail;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAdress(String Adress) {
        this.Address = Adress;
    }

    abstract void display ();



    public void  read_data(){
        Scanner read =new Scanner (System.in);
        System.out.println("\t\t\tHallo");
        System.out.println("\t\t\t-----");
        System.out.print("Please Enter your Name: ");
        Name = read.next() ;
        System.out.print("Please Enter your ID: ");
        ID = read.nextInt() ;
        System.out.print("Please Enter your Adress: ");
        Address = read.next() ;
        System.out.print("Please Enter your Phone Number: ");
        Phone_number = read.nextInt() ;
        System.out.println("Please Enter your  Gander\t\t\t(male / female)");
        while(true){
            Gender = read.next() ;
            String g_m ="male";
            String g_f ="female";
            if (Gender.equals(g_f) || Gender.equals(g_m)){
                break;
            }
            else {
                System.out.print("Please Enter your Gander\t\t\t(male / female)");
            }
        }

        System.out.print("Please Enter your E-mail: ");
        while(true){
            E_mail= read.next() ;
            String gmail ="@gmail.com";
            if ( E_mail.contains(gmail) ){
                break;
            }
            else {
                System.out.print("Please Enter your corect E-mail ");
            }
        }
        System.out.println("congratulations Now you have an accont ");
    }

    public void register(){

    }

    public void login(){

    }
}