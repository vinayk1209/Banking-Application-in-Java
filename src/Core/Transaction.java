package Core;

import java.util.Date;

public class Transaction {

    private static int tranCount;

    private String id;
    private String type;
    private double amount;
    private long date;
    private long from;
    private long to;

    //Constructors

    public Transaction(double amt, String type, long accNo){
        this.amount = amt;
        this.type = type;
        this.date = new Date().getTime();
        if(type == "withdrew")  from = accNo;
        else to = 0;
    }

    public Transaction(double amt, String type, long from, long to){
        this.amount = amt;
        this.type = type;
        this.date = new Date().getTime();
        this.from = from;
        this.to = to;
    }

    //Getters
    public String getId(){
        return id;
    }

    public String getType(){
        return type;
    }

    public void getInfo(){
        System.out.println();
        System.out.println("Transaction Information");
        System.out.println("----------------");
        System.out.println("Tr_No: "+id);
        System.out.println("Tr_Type: "+type);
        System.out.println("Tr_Amount: "+amount);
        System.out.println("Tr_Date: "+date);
        System.out.println("Tr_From: "+from);
        System.out.println("Tr_To: "+ to);
    }

    //Setters
    private String setTransactionNo(){
        String id="TR"+tranCount;
        for(int i=1; i<=4; i++) id+=Math.round(Math.random()*9);
        return id;
    }
}

