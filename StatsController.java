package pecunia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class StatsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private TextInputDialog td = new TextInputDialog("");
    @FXML
    ListView<String> list;

    /**
     * Initializes the scene and prints TransactionsData to a listview
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PrintToList(MainController.TransactionsData);
    }

    /**
     * switchtosceneMain() calls and loads our main fxml file whenever called by the user.
     * We are simply calling a new FXMLLoader and then, relying onto our ListView, we
     * are able to load the needed scene.
     *
     *
     * @throws IOException
     */
    public void switchtosceneMain(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * PrintToList() is needed to not only update the global ListView, we also need it to replace
     * unnecessary square brackets on the ends of every transaction.
     *
     *
     * @param Transactions
     */
    public void PrintToList(ArrayList<Object[]> Transactions) {
        for (int n = 0; n < Transactions.size(); n++) {
            list.getItems().addAll(MainController.currency + Arrays.toString(Transactions.get(n)).replace("[", "").replace("]",""));
        }
    }

    /**
     * Stores the result of Statistics.TopTransactions in ArrayList Transactions
     * Clears ListView
     * Prints Transactions to ListView
     * Result shows Transactions in descending order by amount
     */
    public void TopTransactions(){
        ArrayList<Object[]> Transactions = Statistics.TopTransactions();
        list.getItems().clear();
        PrintToList(Transactions);
    }

    /**
     * TopCategories() stores the result of Statistics.TopCategories() in an ArrayList and prints
     * it into our global ListView, clearing it in advance to ensure correct data showing
     */
    public void TopCategories(){
        ArrayList<Object[]> Transactions = Statistics.TopCategories();
        list.getItems().clear();
        PrintToList(Transactions);
    }

    /**
     * SameAmount() stores the result of Statistics.SameAmount() in an ArrayList and prints
     * it into our global ListView, clearing it in advance to ensure correct data showing.
     * If no transactions with the same amount are found, it simply prints out the
     * statement.
     */
    public void SameAmount(){
        ArrayList<Object[]> Transactions = Statistics.SameAmount();
        list.getItems().clear();
        if (Transactions.isEmpty()){
            list.getItems().add("No transactions with the same amount");
        }
        else {PrintToList(Transactions);}
    }

    /**
     * Calls TextInputDialog
     * Sets the window
     * Gets the textfield from TextInputDialog
     * shows the window and waits until the window is closed
     * gets the text from a textfield
     * Calls OverAmount
     * @param e
     */
    public void startr(ActionEvent e) {
        TilePane r = new TilePane();
        td.setHeaderText("Enter the amount over which you want to view transactions");
        Button button = new Button("click");
        TextField textField = td.getEditor();
        td.showAndWait();
        if (textField.getText().isEmpty()){
            "".isEmpty();
        }
        else{
            OverAmount(textField.getText().replaceAll("[^0-9.]", ""));
        }
    }

    /**
     * OverAmount() stores the result of Statistics.SameAmount() in an ArrayList and prints
     * it into the ListView, clearing it in advance to ensure correct data showing.
     * If there are no transactions over the chosen amount, it prints out
     * a statement.
     *
     *
     * @param amountStr
     * @return
     */
    public ArrayList<Object[]> OverAmount(String amountStr){
        ArrayList<Object[]> Transactions = Statistics.AboveAmount(amountStr);
        list.getItems().clear();
        if (Transactions.isEmpty()){
            list.getItems().add("No transactions over that amount");
        }
        else { PrintToList(Transactions);}
        return Transactions;
    }

    /**
     * Recurrance() stores the result of Statistics.SameAmount() in an ArrayList and prints
     * it into the ListView, clearing it in advance to ensure correct data showing.
     * If there are no recurring transactions, it prints out the statement, ensuring
     * correct status displays.
     */
    public void Recurrance(){
        ArrayList<Object[]> Transactions = Statistics.Recurring();
        list.getItems().clear();
        if (Transactions.isEmpty()){
            list.getItems().add("No recurring transactions");
        }
        else {PrintToList(Transactions);}
    }
}
