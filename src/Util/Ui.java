package Util;

public class Ui {
    //displays a welcome message and some choices for creating an account or login to existing account
    public static void printInitialOptions(String bankName, boolean isWelcomeMsg){
        System.out.println();
        if(isWelcomeMsg){
            System.out.println("Welcome to " + bankName + " Bank");
            System.out.println("----------------");
            System.out.println("1)Create an account   2)Login");
        }
        else {
            System.out.println("1)Create an account   2)Login");
        }
    }

    //displays a welcome message and some choices to select for logged in users
    public static void printUserOptions(String customerName, boolean isWelcomeMsg){
        System.out.println();
        if(isWelcomeMsg){
            System.out.println("Welcome " + customerName);
            System.out.println("----------------");
            System.out.println("1)View Balance    2)Deposite    3)Withdraw    4)Transfer    5)Account Info    6)Logout    0)Exit");
        }
        else {
            System.out.println("1)View Balance    2)Deposite    3)Withdraw    4)Transfer    5)Account Info    6)Logout    0)Exit");
        }
    }

    //displays balance of the user
    public static void printBalance(double bal){
        System.out.println();
        System.out.println("Balance");
        System.out.println("----------------");
        System.out.println(bal);
    }

}
