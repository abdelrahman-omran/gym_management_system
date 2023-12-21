package EQ_GYM;

public class Dumbbells extends Equipment{
    double weight;


Dumbbells(){
    super("Dummbbels");
}
    public Dumbbells(String name,int quantity,int code){
        super(name, quantity, code);
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void display(){
        System.out.println("Type: " + this.getName());
        System.out.println("Quantity: " + this.quantity);
        System.out.println("Code: " + this.getCode());
    }
}
