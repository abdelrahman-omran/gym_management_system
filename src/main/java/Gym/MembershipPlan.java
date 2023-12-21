package Gym;

import java.util.Date;

public class MembershipPlan {
    String member_name;
    Date start_date = null ;
    int numberOfMonths;
    Date end_date = new Date();
    final float price_of_month = 300;
    int number_of_plan;
    //

    public MembershipPlan (String member_name,  Date start_date, int noOfMonths, int number_of_plan){
        this.member_name = member_name;
        this.start_date = start_date;
        this.numberOfMonths = noOfMonths;
        this.number_of_plan =number_of_plan;
        long durationInMillis = this.numberOfMonths * 30L * 24L * 60L * 60L * 1000L; // Convert months to milliseconds
        this.end_date = new Date(start_date.getTime() + durationInMillis);

    }
    public MembershipPlan (String member_name, Date start_date, int number_of_plan){
        this.member_name = member_name;
        this.start_date = start_date;
        this.number_of_plan =number_of_plan;

    }
    public MembershipPlan (String member_name,int number_of_plan){
        this.member_name = member_name;
        this.number_of_plan =number_of_plan ;

    }
    /*public MembershipPlan (){
            this(0);
        start_date =new Date();
        end_date = new Date();
    }*/
    //

    public boolean check_is_active (Date day_date){
        return !day_date.after(this.end_date);

    }

    public void update_member (boolean ch, Date day_date ){
        boolean check = false ;
        int year, month, day;
        year = this.end_date.getYear();
        month = this.end_date.getMonth();
        day = this.end_date.getDay();
        if(ch==false){
            switch (number_of_plan){
                case 1:
                    if (check == ch && end_date.getMonth()+1!= 12 ){
                        month = day_date.getMonth()+1;
                    }
                    else if (check == ch && end_date.getMonth()+1 == 12 ){
                        month = 1;
                        year++;
                    }
                    System.out.println("the next date is " + month +"\t" + day_date.getYear());
                    break;
                case 2:

                    if (check == ch && end_date.getMonth()+1<11){
                        month = day_date.getMonth()+2;
                    }
                    else if (check == ch && end_date.getMonth() == 12   ){
                        month =2;
                        year ++;
                    }
                    else if (check == ch && end_date.getMonth()+1 == 11  ){
                        month =1;
                        year++;
                    }
                    System.out.println("the next date is " + end_date.getMonth() +"\t" + day_date.getYear());
                    break;
                case 3:

                    if (check == ch && day_date.getMonth()<10){
                        month = day_date.getMonth()+3;
                    }
                    else if (check == ch && day_date.getMonth()+1 == 12){
                        month =3;
                        year++;
                    }
                    else if (check == ch && day_date.getMonth()+1 == 11  ){
                        month =2;
                        year++;
                    }
                    else if (check == ch && day_date.getMonth()+1 == 10){
                        month =1;
                        year++;
                    }
                    System.out.println("the next date is " + month +"\t" + year);
                    break;
                case 4 :
                    if (check == ch && month<=6){
                        month = day_date.getMonth()+6;
                        if(month>12){
                            month = month-12;
                            year++;
                        }
                    }
                    else if (check == ch && end_date.getMonth() > 6){
                        if(month==12){
                            month=6 ;
                            year++;
                        }
                        else {
                            month = month % 6 ;
                            year++;
                        }
                    }
                    System.out.println("the next date is " + month + "\t" +year );
                    break;
                case 5 :
                    year = day_date.getYear()+1 ;
                    System.out.println("the next date is " + month+"\t" + year);
                    break;
                default:
                    System.out.println("any way case ");
                    break;
            }
        }
        else {
            System.out.println("not need active");
        }
    }

    public float discount_price ( int number_of_plan ) {
        float price_member =0 ;
        switch (number_of_plan){
            case 1:
                price_member =price_of_month ; // 1month                        300
                break;
            case 2:
                price_member =price_of_month * 2 ; // 2 month                   600
                break;
            case 3:
                price_member =price_of_month * (8/3); // 3 month            800
                break;
            case 4 :
                price_member = price_of_month * (16/3) ; // 6 month         1600
                break;
            case 5 :
                price_member = price_of_month * 10 ; // 1 year              3000
                break;
            default:
                System.out.println("any way case ");
                break;
        }

        return price_member ;
    }

    public String getMember_name() {
        return member_name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public int getNumberOfMonths() {
        return numberOfMonths;
    }

    public int getNumber_of_plan() {
        return number_of_plan;
    }

    public  void display () {
        System.out.println("Name: " + member_name);
        System.out.println("Number of plan: " + number_of_plan);
        System.out.println("Day: " + Integer.valueOf(start_date.getDay()+1)+"\tmonth: " + Integer.valueOf(start_date.getMonth()+1)+"\tYear: " + Integer.valueOf(start_date.getYear()+1900));
    }
}