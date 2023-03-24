package Core;

import java.util.Date;

public class Customer {

    private static int cusCount;

    private String id;
    private String name;
    private Account account;
    private String address;
    private Date dob;
    private String password;

    //Constructors
    public Customer(String name, String address, Date dob, String password){
        cusCount++;
        account = new Account();
        this.name = name;
        id = setId();
        this.address = address;
        this.dob = dob;
        this.password = password;
    }

    //Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Date getDob() {
        return dob;
    }

    public Account getAccount(){
        return account;
    }

    public void getInfo(){
        System.out.println();
        System.out.println("Customer Information");
        System.out.println("----------------");
        System.out.println("Cus_Name: "+name);
        System.out.println("Cus_Id: "+id);
        System.out.println("Cus_Dob: "+ dob);
        System.out.println("Cus_Address: "+ address);
    }

    //Setters
    private String setId(){
        String id = "CUS"+cusCount;
        for(int i=1; i<=4; i++) id+=Math.round(Math.random()*9);
        return id;
    }

    //Specialized methods
    public boolean authorize(String name, String password){

        if(name.equalsIgnoreCase(this.name) && password.equals(this.password)) {
            System.out.println("success");
            return true;
        }
        else return false;
    }
}
