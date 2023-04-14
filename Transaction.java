package pecunia;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Transaction {
    public static ArrayList<Object[]> TransactionsData = MainController.TransactionsData;

    public static Scanner sc = new Scanner(System.in);

    /**
     * Handles the input that we then send as an argument to AddTransaction()
     * and add the result to our ArrayList TransactionsData
     *
     */
    public static void TransactionInput(){
        System.out.print("Enter transaction amount: ");
        double amount = sc.nextDouble();
        System.out.print("Enter transaction category: \n");
        String category = "";
        System.out.print("1) Food\n");
        System.out.print("2) Utilities\n");
        System.out.print("3) Entertainment\n");
        int input = sc.nextInt();
        if (input == 1) {
            category = "Food";
        } else if (input == 2) {
            category = "Utilities";
        } else if (input == 3) {
            category = "Entertainment";
        } else {
            category = "No category";
        }
        System.out.print("Is the transaction recurring? (1 for Yes, 0 for No)\n");
        int recurring = sc.nextInt();
        if (recurring != 0 && recurring != 1) {
            System.out.println("Incorrect input");
            System.exit(0);

        }
        TransactionsData.add(AddTransaction(amount, category, String.valueOf(recurring)));

    }
    /**
     * AddTransaction() function takes in user input as long as the user chose the first option in the menu
     * We are asking for amount, category and whether or not the transaction is recurring, later adding
     * all of the info inside of a global ArrayList.
     * In the end, if done correctly, we inform user that the transaction has been added successfully.
     *
     * @param amount
     * @param category
     * @param recurring
     * @return
     */
    public static Object[] AddTransaction(double amount, String category, String recurring) {
        return new Object[]{amount, category, recurring};

    }




    /**
     * PrintAllTransactions() prints out everything we currently have stored now in the global ArrayList,
     * when called.
     */
    public static Object[] PrintAllTransactions() {
        Object[] Transaction;
        Object[] allTrans = new Object[0];
        int n = 0;
        while (n < TransactionsData.size()) {
            Transaction = TransactionsData.get(n);
            return Transaction;
        }
        if (TransactionsData.isEmpty()){
            allTrans = new Object[]{"Your wallet is empty!"};
        }
        return allTrans;
    }
}
