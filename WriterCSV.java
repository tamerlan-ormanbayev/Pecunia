package pecunia;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class WriterCSV {

    /**
     * CSVW() helps us with filling in the comma-separated file (.csv), by saving the user input
     * inside of it. It also helps us reset the wallet, if the user chose such option.
     */

    public static void CSVW(String currentFile) throws IOException {
        ArrayList<Object[]> data = Transaction.TransactionsData;
        FileWriter writer = new FileWriter("data/" + currentFile);

        for (Object[] trnsctn : data) {
            String str = Arrays.toString(trnsctn);
            str = str.replace("]", ",");
            str = str.replace("[", "");
            writer.write(str.replaceAll("\\s+", "").trim().concat(""));

        }
        writer.close();
    }
}

