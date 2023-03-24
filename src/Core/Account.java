package Core;

import java.util.ArrayList;

public class Account {

    private static int accCount;

    private long accountNumber;
    private String ifsCode;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private double balance;
    private String accType;

    //Constructors
    public Account() {
        accountNumber = setAccNo();
        ifsCode = setIfscCode();
        balance = 0;
        accType = "Savings";
    }

    //Getters
    public long getAccountNumber() {
        return accountNumber;
    }

    public String getIfsCode() {
        return ifsCode;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccType() {
        return accType;
    }

    public void getInfo(){
        System.out.println();
        System.out.println("Account Information");
        System.out.println("----------------");
        System.out.println("Acc_No: "+accountNumber);
        System.out.println("Acc_IFSC: "+ifsCode);
        System.out.println("Acc_Type: "+accType);
        System.out.println("Acc_Balance: "+balance);
        System.out.println("No of transactions: "+ transactions.size());
    }

    //Setters
    private long setAccNo(){
        String id="";
        for(int i=1; i<=4; i++) id+=Math.round(Math.random()*9);
        return Long.valueOf(id)+accCount;
    }

    private String setIfscCode(){
        String ifsc= "abc"+accCount;
        for(int i=1; i<=4; i++) ifsc+=Math.round(Math.random()*9);
        return ifsc;
    }

    //Specialized methods
    public Transaction deposite(double amt){
        if(amt > 0){
            balance += amt;
            System.out.println("Transaction Successful: +"+amt);
        } else System.err.println("Transaction Failed!");

        Transaction transaction= new Transaction(amt, "deposite", this.accountNumber);
        transactions.add(transaction);
        return transaction;
    }

    public Transaction withdraw(double amt){
        if(amt <= balance && amt > 0){
            balance -= amt;
            System.out.println("Transaction Successful: -"+amt);
        } else {
            System.err.println("Transaction Failed!");
            return null;
        }

        Transaction transaction= new Transaction(amt, "withdrew", this.accountNumber);
        transactions.add(transaction);
        return transaction;
    }

    public Transaction transfer(double amt, Account acc){
        if(amt <= balance && amt > 0) {
            balance -= amt;
            acc.balance += amt;
            Transaction transaction= new Transaction(amt, "withdrew", this.accountNumber, acc.getAccountNumber());
            transactions.add(transaction);
            return transaction;
        } else {
            System.out.println("Transaction Failed (check your balance)");
        }
        return null;
    }
}
