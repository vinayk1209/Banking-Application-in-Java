package Controller;

import Core.*;
import Util.Ui;
import Util.Utility;

public class Controller {

    private static Bank bank;
    private static Customer activeCustomer;
    private static boolean isInitWelcomeMsg = true;
    private static boolean isAccWelcomeMsg = true;

    static {
        System.out.println();
        bank = new Bank("STATE BANK OF INDIA");
        bank.addBranch("PLATINUM BRANCH HYDERABAD"
                ,"PLOT NO.1100,ROAD NO.36,GROUND FLOOR NEAR PEDDAMMA TEMPLE,JUBILEE HILLS HYDERABAD");
        bank.addBranch("EMPIRE HOUSE BRANCH FORT MUMBAI"
                , "Peninsula House, 235-237, Dr Dadabhai Naoroji Rd, Fort, Mumbai, Maharashtra 400001");
        bank.addBranch("Hosur Road Branch BENGALURU"
                , "Pmr Tower, 43, Service Rd, next to Suzuki Showroom, Chennakeshava Nagar, Lavakusha Nagar, Pragathi Nagar, Electronic City, Bengaluru, Karnataka 560100");
        bank.addBranch("bandra", "mumbai");
    }

    public static void main(String[] args) {
        init();
    }

    public static void init(){
        while(true){

            Ui.printInitialOptions(bank.getName(), isInitWelcomeMsg);

            //making isInitWelcomeMsg = false; so that the initial welcome message will not be repeated
            isInitWelcomeMsg = false;

            //taking input from the user
            int input = Utility.getInput(1,2);

            /*
            * if the user input is 1: Create an account
            *                         - then printInitialChoices() and the same loop will keep on repeating
            * if the user input is 2: Login to existing account
            *                         - then printUserChoices and execute userAccountFunctionality()
            * */
            switch(input){
                case 1: if(Utility.createAccount(bank)){
                    System.out.println();
                    System.out.println("Account successfully created");
                }
                        break;
                case 2: if(Utility.login(bank)){
                    userAccountFunctionality();
                }
                        break;
            }
        }
    }


    public static void userAccountFunctionality() {
        Account acc = activeCustomer.getAccount();
        root: while(true){
            Ui.printUserOptions(activeCustomer.getName(), isAccWelcomeMsg);

            //making isAccWelcomeMsg = false; so that the welcome message after successful login will not be repeated
            isAccWelcomeMsg = false;

            //taking input from the user
            int input = Utility.getInput(0,6);

            //based on the given input from the user relevant functions will be executed
            switch(input){
                case 0: System.exit(0);
                        break;
                case 1: Ui.printBalance(acc.getBalance());
                        break;
                case 2: acc.deposite(Utility.getAmt("Deposite"));
                        break;
                case 3: acc.withdraw(Utility.getAmt("Withdraw"));
                        break;
                case 4: Utility.transfer(bank, acc);
                        break;
                case 5: acc.getInfo();
                        break;
                case 6: {
                    //if the user input is 6 then the user will be logged out from the account and then shown printInitialChoices()
                    break root;
                }
            }
        }
    }

    public static void setActiveCustomer(Customer customer){
        activeCustomer = customer;
    }
}