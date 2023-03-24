package Util;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import Controller.Controller;
import Core.*;

public class Utility {
    public static boolean createAccount(Bank bank){

        Scanner sc = new Scanner(System.in);

        //printing branches info
        System.out.println();
        bank.printAllBranchInfo();
        System.out.println();

        /* taking branchName as input from the user so that we can create an account in that
            particular user selected branch */
        System.out.print("Select a branch to create an account (Name): ");
        Branch branch = null;
        String branchName = "";
        while(branch==null){
            if(sc.hasNextLine()){
                branchName = sc.nextLine();
                //helpes to get the Branch based on the name.
                branch = bank.getBranch(branchName);
            }
            if(branch != null)break;
            else System.out.print("No branch found! Try again: ");
        }

        try{
            /* asks relevant information from the user for creation of account and then add that user
             to the user selected branch */
            branch.addCustomer();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean login(Bank bank){
        Scanner sc = new Scanner(System.in);


        System.out.println();
        System.out.println("Login");
        System.out.println("----------------");

        //taking name input from the user
        System.out.print("Name: ");
        String name = "";
        if(sc.hasNextLine()){
            boolean flag = false;
            while(!flag){
                try{
                    name = sc.nextLine();
                    flag = true;
                }catch (InputMismatchException ime){
                    System.out.print("Wrong Input! Try again: ");
                }
            }
        }

        //taking password input from the user.
        System.out.print("Password: ");
        String password = "";
        if(sc.hasNext()){
            boolean flag = false;
            while(!flag){
                try{
                    password = sc.next();
                    flag = true;
                }catch (InputMismatchException ime){
                    System.out.print("Wrong Input! Try again: ");
                }
            }
        }

        /* seraching for the user account based on the name and password given by the user
        if the account is found then the method will return true if not false */
        boolean flag = false;
        Iterator branchesItr = bank.getBranches().iterator();
        root: while(branchesItr.hasNext()){
            Branch branchItm = (Branch)branchesItr.next();
            Iterator customersItr = branchItm.getCustomers().iterator();
            while(customersItr.hasNext()){
                Customer customerItm = (Customer)customersItr.next();
                flag = customerItm.authorize(name, password);
                if(flag) {
                    Controller.setActiveCustomer(customerItm);
                    break root;
                }
            }
        }
        if(!flag) System.err.println("Wrong Username/Password!");
        return flag;
    }

    //this method helps us to get the input from the user within the specified range for selecting the options.
    public static int getInput(int beg, int end){
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.print("Input: ");

        int data = 0;
        boolean flag = false;
        while(!flag){
            try{
                data = sc.nextInt();
                if(data >= beg && data <= end)flag = true;
                else System.out.print("Please choose the correct option : ");
            }catch (InputMismatchException ime){
                System.out.print("Wrong Input! Try again: ");
                sc.nextLine();
            }
        }

        return data;
    }

    //helps us to get the amount from the user to transfer, deposite, withdraw
    public static double getAmt(String type){
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println(type);
        System.out.println("----------------");
        System.out.print("Amount: ");
        boolean flag = false;
        Double amount = 0d;
        while(!flag){
            if(sc.hasNextDouble()){
                try{
                    amount = sc.nextDouble();
                    flag = true;
                } catch (InputMismatchException ime){
                    System.out.println("Wrong Input! Try again: ");
                }
            }
        }
        return amount;
    }

    //asks recipient account number and then get the Account of the recipient based on the account number.
    public static Account getTransferAcc(Bank bank){
        Scanner sc = new Scanner(System.in);
        System.out.print("To (Acc No): ");
        boolean flag = false;
        long accNo = 0;
        while(!flag){
            if(sc.hasNextLong()){
                try{
                    accNo = sc.nextLong();
                    flag = true;
                } catch (InputMismatchException ime){
                    System.out.println("Wrong Input! Try again: ");
                }
            }
        }

        //
        return bank.getAcc(accNo);
    }

    //this method transfer the amount from the current logged in user account to another account
    public static void transfer(Bank bank, Account acc){
        double amt = getAmt("Transfer");
        Account toAcc = getTransferAcc(bank);
        if(toAcc == null) System.err.println("No account exists with this number");
        else {
            acc.transfer(amt, toAcc);
            System.out.println("Transaction Successful! " + acc.getAccountNumber() + " -> " + toAcc.getAccountNumber());
        }
    }

}
