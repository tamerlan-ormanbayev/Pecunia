package pecunia;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * NAMEs: Yurii Bezborodov (30114669), Tamerlan Ormanbayev (30141208)
 * TUTORIALs: T07, T03
 * DATE: 18th MARCH 2022

*/
class MainTest{


    @Test

    //Tests if a transaction creates correctly

    void AddTransactionTest() {
        double amount = 100;
        String category = "Food";
        String recurring = "Recurring";
        Object[] Transaction1 = {amount, category, recurring};
        assertArrayEquals(Transaction1, Transaction.AddTransaction(amount,category,recurring));
    }


    //Tests if the sorting algorithm correctly determines the wrong order and switches it when needed
    @Test
    void SortingTest(){
        double amount = 100;
        String category = "Food";
        int recurring = 1;
        Object[] Transaction = {amount, category, recurring};
        double amount1 = 1000;
        String category1 = "Food";
        String recurring1 = "Recurring";
        Object[] Transaction1 = {amount1, category1, recurring1};
        ArrayList<Object[]> TransactionsData = new ArrayList<>();
        TransactionsData.add(Transaction);
        TransactionsData.add(Transaction1);
        ArrayList<Object[]> TopTransactions = new ArrayList<>();
        TopTransactions.add(Transaction1);
        TopTransactions.add(Transaction);
        assertEquals(TopTransactions, Statistics.Sorting(TransactionsData));
    }
}
