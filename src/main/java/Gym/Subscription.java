package Gym;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Subscription {
   private int coach_id;
   private int costumer_id;
   private MembershipPlan membershipPlan;
    
    public Subscription(int coach_id,int costumer_id, MembershipPlan memberPlan)
    {
    this.coach_id=coach_id;
    this.costumer_id=costumer_id;
    this.membershipPlan = memberPlan;
    
    }

    public int getCoach_id() {
        return coach_id;
    }

    public void setCoach_id(int coach_id) {
        this.coach_id = coach_id;
    }

    public int getCostumer_id() {
        return costumer_id;
    }

    public void setCostumer_id(int costumer_id) {
        this.costumer_id = costumer_id;
    }

    public MembershipPlan getMembershipPlan() {
        return membershipPlan;
    }
}
