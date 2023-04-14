package pecunia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    TextField amount;
    @FXML
    ChoiceBox category;
    @FXML
    ChoiceBox recur;
    @FXML
    ListView<String> list;
    @FXML
    Label Transactionlabel;

    /**
     * Switches to scene main.fxml which is our Main Menu
     * @param e
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
     * A loop that prints all our transactions in MainController.TransactionsData to ListView list,
     * also replaces "]" and "[" with "" when printing
     */
    public void PrintToList(){
        for (int n = 0; n < MainController.TransactionsData.size(); n++) {
            list.getItems().addAll(MainController.currency +
                    Arrays.toString(MainController.TransactionsData.get(n)).replace("[", "").replace("]",""));
        }

    }
    String currentTransaction;

    /**
     * Initializes the scene when it is shown,
     * Adds choices to ChoiceBoxes
     * Prints Transactions to ListView
     * handles the selection of transactions in a list
     *
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        category.getItems().removeAll(category.getItems());
        String[] categorychoice = {"Food", "Entertainment", "Utilities"};
        category.getItems().addAll(categorychoice);
        recur.getItems().removeAll(category.getItems());
        String[] recurchoice = {"Recurring", "Non-Recurring"};
        recur.getItems().addAll(recurchoice);
        PrintToList();
        list.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> { //adds a listener that is keeping track of if something in a list was selected
            currentTransaction = list.getSelectionModel().getSelectedItem(); // gets the value of a selected value
            Transactionlabel.setText(currentTransaction);
            for (int n = 0; n < MainController.TransactionsData.size(); n++) { //loops through TransactionsData and if there is an item there that is equal to currentTransaction and sets input fields to their respective values
                if ((MainController.currency +
                        Arrays.toString(MainController.TransactionsData.get(n)).replace("[","").replace("]", "")).equals(currentTransaction)) {
                    Object[] Transaction = MainController.TransactionsData.get(n);
                    amount.setText(Transaction[0].toString());
                    category.setValue(Transaction[1].toString());
                    recur.setValue(Transaction[2].toString());
                }
            }
        });
    }
    /**
     * EditTransaction() allows user to edit any part of already stored transaction, its category, amount
     * and if it is recurring. It appeals to the global ArrayList and edits the chosen part of the transaction
     * by constantly asking for input in both menu and new data that needs to be stored.
     */
    public void EditTransaction(){
        Object[] Transaction;
        for (int n = 0; n < MainController.TransactionsData.size(); n++){
            if ((MainController.currency +
                    Arrays.toString(MainController.TransactionsData.get(n)).replace("[", "").replace("]","")).equals(currentTransaction)){
                Transaction = MainController.TransactionsData.get(n);
                Transaction[0] = Double.parseDouble(amount.getText().replaceAll("[^0-9.]", ""));
                Transaction[1] = category.getValue();
                Transaction[2] = recur.getValue();
            }
            else {
                Transactionlabel.setText("Transaction not selected!");
            }
        }
    }

    /**
     * EditWithASwitch() is a simple method required to send user back to the main menu, as soon as they have
     * finished editing their transaction, they will press the button that immediately calls
     * switchtosceneMain(e)
     *
     *
     * @param e
     * @throws IOException
     */
    public void EditWithASwitch(ActionEvent e) throws IOException {
        EditTransaction();
        switchtosceneMain(e);
    }

    /**
     * DeleteTransaction() is a method that is called whenever the user needs to delete their transaction.
     * As long as the transaction is actually in the bounds of our ArrayList, we remove the position from that
     * list, accordingly as the user chose themselves. We switch back to the main menu as soon as the action
     * has been done.
     *
     *
     * @param e
     * @throws IOException
     */
    public void DeleteTransaction(ActionEvent e) throws IOException {
        for (int n = 0; n < MainController.TransactionsData.size(); n++) {
            if (Arrays.toString(MainController.TransactionsData.get(n)).replace("[", "").replace("]","").equals(currentTransaction)) {
                MainController.TransactionsData.remove(n);
                switchtosceneMain(e);
            }
            else {
                Transactionlabel.setText("Transaction not selected!");
            }
        }
    }
}
