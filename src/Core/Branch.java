package Core;

import java.util.*;

public class Branch {

    static private int branchCount;

    private String id;
    private String name;
    ArrayList<Customer> customers = new ArrayList<>();
    private String branchAddress;

    //Constructor
    public Branch(String name, String address){
        branchCount++;
        this.name = name;
        this.branchAddress = address;
        id = setId();
    }

    //Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return branchAddress;
    }

    public ArrayList<Customer> getCustomers(){
        return customers;
    }

    //helps us to get the Customer from the branch based on the name of the customer
    public Customer getCustomer(String name){
        Iterator customersItr = customers.iterator();
        while(customersItr.hasNext()){
            Customer customer = (Customer) customersItr.next();
            if(customer.getName().toLowerCase() == name.toLowerCase()) return customer;
        }
        return null;
    }

    public void getInfo(){
        System.out.println();
        System.out.println("Branch Information");
        System.out.println("----------------");
        System.out.println("Br_Name: "+name);
        System.out.println("Br_Id: "+id);
        System.out.println("Br_Address: "+branchAddress);
        System.out.println("No of Customer: "+customers.size());
    }

    //Setters
    private String setId(){
        String id = "BR"+branchCount;
        for(int i=1; i<=4; i++) id+=Math.round(Math.random()*9);
        return id;
    }

    //Specialized methods

    /* this method asks all the required information from the user and then
     creates a Customer object which will be added to customers list */
    public Customer addCustomer(){
        Scanner sc = new Scanner(System.in);
        boolean flag = false;

        //taking name input from the user
        System.out.print("Name: ");
        String name = "";
        if(sc.hasNextLine()){
            name = sc.nextLine();
        }

        //taking address input from the user
        System.out.print("Address: ");
        String address = "";
        if(sc.hasNextLine()){
            address = sc.nextLine();
        }

        //taking dob input from the user
        System.out.print("DOB (DD/MM/YYYY): ");
        Date dob = null;
        while(!flag){
            if(sc.hasNextLine()){
                try{
                    dob = new Date(sc.nextLine());
                    flag = true;
                } catch (IllegalArgumentException iae){
                    System.out.print("Wrong date format! Try again (DD/MM/YYYY): ");
                }
            }
        }

        //taking password input form the user
        System.out.print("Password: ");
        String password = "";
        if(sc.hasNext()){
            password = sc.next();
        }

        //creating a new customer based on the provided information from the user
        Customer customer = new Customer(name, address, dob, password);

        //adding the newly created Customer to the customers list
        customers.add(customer);

        return customer;
    }

    //removes Customer from the customers list based on the name
    public void removeCustomer(String name){
        if(customers.remove(getCustomer(name)));
    }
}
