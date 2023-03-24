package Core;

import java.util.ArrayList;
import java.util.Iterator;

public class Bank {

    static private int bankCount;

    private String id;
    private String name;
    private ArrayList<Branch> branches = new ArrayList<>();

    //Constructor
    public Bank(String name) {
        bankCount++;
        this.name = name;
        id = setId();
    }

    //Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // helps us to get a particular Branch from the branches list based on the name of the branch
    public Branch getBranch(String name){
        Iterator branchesItr = branches.iterator();
        while(branchesItr.hasNext()){
            Branch branch = (Branch) branchesItr.next();
            if(name.equalsIgnoreCase(branch.getName())) return branch;
        }
        return null;
    }

    public ArrayList<Branch> getBranches(){
        return branches;
    }

    public void getInfo(){
        System.out.println();
        System.out.println("Bank Information");
        System.out.println("----------------");
        System.out.println("B_Name: "+name);
        System.out.println("B_Id: "+id);
        System.out.println("No of branches: "+branches.size());
    }

    public void printAllBranchInfo(){
        int count = 0;
        System.out.println("All Branches of the bank");
        System.out.println("----------------");
        Iterator branchesItr = branches.iterator();
        while(branchesItr.hasNext()){
            Branch branch = (Branch) branchesItr.next();
            System.out.print(++count + ") Br_Name: "+ branch.getName() +"   |   ");
            System.out.print("Br_Id: "+branch.getId()+"   |   ");
            System.out.print("Br_Address: "+branch.getAddress()+"   |   ");
            System.out.println();
        }
    }

    //Setters
    private String setId(){
        String id = "BNK"+bankCount;
        for(int i=1; i<=4; i++) id+=Math.round(Math.random()*9);
        return id;
    }

    //Specialized methods
    public void addBranch(String name, String address){
        branches.add(new Branch(name, address));
    }

    public void removeBranch(String name){
        if(branches.remove(getBranch(name)));
    }

    //searches for the Account in the bank based on the account number and then returns the Account
    public Account getAcc(long accNo) {
        Iterator branchesItr = branches.iterator();
        while(branchesItr.hasNext()){
            Branch branchItm = (Branch) branchesItr.next();
            Iterator customersItr = branchItm.customers.iterator();
            while(customersItr.hasNext()){
                Customer customerItm = (Customer) customersItr.next();
                if(customerItm.getAccount().getAccountNumber() == accNo) return customerItm.getAccount();
            }
        }
        return null;
    }
}
