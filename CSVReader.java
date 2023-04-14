package pecunia;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class CSVReader {

    /**
     * CSV() assists in reading the .csv file. It opens the comma-separated file and outputs everything
     * inside of it, rearraging it into an arrayList that we can work with in our pecunia.Main.java class.
     */

    public static ArrayList<Object[]> CSV(String filepath, Scanner sc) throws FileNotFoundException {
        ArrayList<Object[]> Transactions = new ArrayList<>();
        File wallet = new File(filepath);
        if (wallet.exists()){
        sc.useDelimiter(",");
        Object[] Transaction = new Object[3];
        int count = 0;
        while (sc.hasNext()) {
            Transaction[count] = sc.next();
            count += 1;
            if (count >= 2) {
                Transaction[count] = sc.next();
                count = 0;
                Transactions.add(Transaction);
                Transaction = new Object[3];
            }
        }
        }
        else{
            "".isEmpty();
        }

    return Transactions;
    }
}
