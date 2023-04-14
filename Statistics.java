package pecunia;

import java.util.*;
import java.lang.*;

public class Statistics {
    public static ArrayList<Object[]> TransactionsData = MainController.TransactionsData;
    /**
     * pecunia.Statistics() is a menu that later on calls different functions, depending on the user's input.
     * It gives clear messages about current state of menu and what exactly user needs to do, giving exactly
     * 4 options to work with. Taking input and comparing it to the simple integers.
     *
    public static void Statistics(){
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------------\n");
        System.out.println("Select one of the following options:\n");
        System.out.println("1) Top transactions by expenses\n");
        System.out.println("2) Top categories by expenses\n");
        System.out.println("3) View all recurring transactions\n");
        System.out.println("4) View all transactions over certain amount\n");
        System.out.println("5) View transactions with the same amount\n");
        int input = sc.nextInt();
        if (input == 1){
            System.out.print("Your top transactions so far:\n");
            TopTransactions();
        }
        else if (input == 2){
            System.out.print("Your top categories by expenses are:\n");
            TopCategories();
        }
        else if (input == 3){
            System.out.print("All of your recurring transactions:\n");
            Recurring();
        }
        else if (input == 4){
            System.out.print("Enter an amount:\n");
            AboveAmount();
        }
        else if (input == 5){
            System.out.println("All of your transactions with the same amount:\n");
            SameAmount();
        }

    }

    /**
     * TopTransactions() ranks user's transactions from biggest to lowest comparing amounts.
     * It prints them out in an ordered list, when called.
     */
    public static ArrayList<Object[]> TopTransactions(){
        ArrayList<Object[]> TopTransactions = new ArrayList<>(TransactionsData);
        return Sorting(TopTransactions);

    }

    /**
     * TopCategories() shows how much does a user spend on three different categories, ranking them in an order of the
     * biggest amount to the smallest.
     */
    public static ArrayList<Object[]> TopCategories(){
        int n = 0;
        double FoodAmount = 0;
        double UtAmount = 0;
        double EntAmount = 0;
        String food = "Food";
        String util = "Utilities";
        String ent = "Entertainment";
        while (n < TransactionsData.size()){
            Object[] Transaction = TransactionsData.get(n);
            String Transactionstr = Transaction[1].toString();
            if (Transactionstr.equals(food)){
                String str = Transaction[0].toString();
                double transaction1 = Double.parseDouble(str);
                FoodAmount = FoodAmount + transaction1;
            }
            else if (Transactionstr.equals(util)){
                String str1 = Transaction[0].toString();
                double transaction2 = Double.parseDouble(str1);
                UtAmount = UtAmount + transaction2;
            }
            else if (Transactionstr.equals(ent)){
                String str2 = Transaction[0].toString();
                double transaction3 = Double.parseDouble(str2);
                EntAmount = EntAmount + transaction3;
            }
            n += 1;
        }
        ArrayList<Object[]> Categories = new ArrayList<>();
        Object[] Food = new Object[] {FoodAmount, "Food"};
        Object[] Utilities = new Object[] {UtAmount, "Utilities"};
        Object[] Entertainment = new Object[] {EntAmount, "Entertainment"};
        Categories.add(Food);
        Categories.add(Utilities);
        Categories.add(Entertainment);
        ArrayList<Object[]> SortedCat = Sorting(Categories);
        return  SortedCat;
    }

    /**
     * Recurring() prints out all of user's recurring transactions, if it is called.
     * It simply compares whether or not the position 2 inside of a transaction is equal to 1(YES),
     * If it does, it prints out. If it is not equal, it prints out nothing.
     */
    public static ArrayList<Object[]> Recurring(){
        ArrayList<Object[]> Transactions = new ArrayList<>(TransactionsData);
        ArrayList<Object[]> RecData = new ArrayList<>();
        int n = 0;
        while (n != Transactions.size()){
            Object[] Recurring = Transactions.get(n);
            String recur = Recurring[2].toString();
            if (Objects.equals(recur, "Recurring")) {
                RecData.add(Recurring);
            }
            else{
                "".isEmpty();
            }
            n += 1;
        }
        return RecData;
    }

    /**
     * AboveAmount() shows user all of their transactions which have an amount higher than a number in input.
     * Here, we compare the input to the existing amount that is already stored in all of the transactions,
     * and check if they are more or equal to the entered amount. If they are equal, we print them and update
     * the loop. If not, we print out emptiness.
     */
    public static ArrayList<Object[]> AboveAmount(String amountint){
        double amount  = Double.parseDouble(amountint);
        ArrayList<Object[]> temp = new ArrayList<>(TransactionsData);
        ArrayList<Object[]> TopTransactions = new ArrayList<>(Sorting(temp));
        ArrayList<Object[]> OverAmount = new ArrayList<>();
        int n = 0;
        while (n < TopTransactions.size()) {
            Object[] Transaction = TopTransactions.get(n);
            String str = Transaction[0].toString();
            double TransactionAmount = Double.parseDouble(str);
            if (TransactionAmount >= amount) {
                OverAmount.add(Transaction);
            }
            else {
                "".isEmpty();
            }
            n += 1;
        }
        return OverAmount;
    }
    /**
     * Sorting() algorithm that is used to print out different categories of data, like TopCategories, TopAmount and etc.
     * This is a bubble sorting algorithm, meaning it constantly and repeatedly compares parameters and switches them
     * if they are in the wrong order. It constantly appeals to the global ArrayList.
     * @param Transactions
     * @return
     */
    public static ArrayList<Object[]> Sorting(ArrayList<Object[]> Transactions){
        int n = Transactions.size();
        Object[] temp;
        Object[] temp2;
        for (int i = 0; i < n; i++){
            for (int j = 1; j < (n-i); j++){
                temp = Transactions.get(j-1);
                temp2 = Transactions.get(j);
                String str = temp2[0].toString();
                String str1 = temp[0].toString();
                double amount = Double.parseDouble(str);
                double amount1 = Double.parseDouble(str1);
                if (amount > amount1){
                    Collections.swap(Transactions, j-1, j);
                }
            }
        }
        return Transactions;
    }

    /**
     * This function shows all the transactions that have the same amount,
     * by first sorting the transactions by amount and then going through
     * them 1 by 1 and comparing the amounts of transactions to each other
     * if the amounts are equal, it prints both transactions
     */
    public static ArrayList<Object[]> SameAmount(){
        int n = 0;
        ArrayList<Object[]> temp = new ArrayList<>(TransactionsData);
        ArrayList<Object[]> SortedData = new ArrayList<>(Sorting(temp));
        ArrayList<Object[]> SameAmount = new ArrayList<>();
        while (SortedData.size() > n) {
            Object[] Transaction = SortedData.get(n);
            String str = Transaction[0].toString();
            int trHash = str.hashCode();
            if (n + 1 < SortedData.size()) {
                Object[] Transaction2 = SortedData.get(n + 1);
                String str2 = Transaction2[0].toString();
                int trHash2 = str2.hashCode();
                if (trHash == trHash2) {
                    SameAmount.add(Transaction);
                } else if (trHash != trHash2) {
                    "".isEmpty();
                }
                else if (n + 2 == SortedData.size()){
                    SameAmount.add(Transaction2);
                }
            }
            n += 1;
        }
        return SameAmount;
    }
}
