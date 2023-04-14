package pecunia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class TransactionController {
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

    /**
     * Initializes the scene
     * Adds choices to ChoiceBoxes
     * Prints all transactions in TransactionsData to ListView
     */
    public void initialize(){
        category.getItems().removeAll(category.getItems());
        String[] categorychoice = {"Food", "Entertainment", "Utilities"};
        category.getItems().addAll(categorychoice);
        recur.getItems().removeAll(category.getItems());
        String[] recurchoice = {"Recurring", "Non-Recurring"};
        recur.getItems().addAll(recurchoice);
        PrintToList();
    }

    /**
     * PrintToList() is needed to not only update the global ListView, we also need it to replace
     * unnecessary square brackets on the ends of every transaction.
     */
    public void PrintToList(){
        for (int n = 0; n < MainController.TransactionsData.size(); n++) {
            list.getItems().addAll(MainController.currency + Arrays.toString(MainController.TransactionsData.get(n)).replace("[", "").replace("]",""));
        }
    }

    /**
     * inputAmount() takes in user amount from amount TextField and parses it to Integer.
     * @return
     */
    public String inputAmount(){

        try {
            String amountstr = amount.getText();
            Double amount = Double.parseDouble(amountstr);
            return amountstr;
        } catch (NumberFormatException e){
            System.out.println("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot parse non-integer values");
            alert.setContentText("Please, only use numbers.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isEmpty()){
                System.out.println("");
            }
        }
        return null;
    }

    /**
     * inputCat() takes in user input from category ChoiceBox and returns it
     * as a String
     * @return
     */
    public String inputCat(){
        return category.getValue().toString();
    }

    /**
     * inputRec() takes in user input from category ChoiceBox and returns it
     * as a String
     * @return
     */
    public String inputRec(){
        return recur.getValue().toString();
    }

    /**
     * TransactionWithASwitch() is a function that immediately switches scenes from Transactions
     * back to Main Menu, as soon as the user decides to do so.
     *
     *
     * @param e
     * @throws IOException
     */
    public void TransactionWithASwitch(ActionEvent e) throws IOException {
        AddTransaction();
        switchtosceneMain(e);
    }

    /**
     * takes input from textfields and choiceboxes
     * Uses Transaction.AddTransaction to create Object[] and add it to Arraylist TransactionsData
     */
    public void AddTransaction(){
        if (inputAmount() != null) {
            Double amount = Double.parseDouble(inputAmount());
            String category = inputCat();
            String recur = inputRec();
            MainController.TransactionsData.add(Transaction.AddTransaction(amount, category, recur));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            fxmlLoader.getController();
        }
    }

    /**
     * switchtosceneMain() calls and loads our main fxml file whenever called by the user.
     * We are simply calling a new FXMLLoader and then, relying onto our ListView, we
     * are able to load the needed scene.
     *
     *
     * @throws IOException
     */
    @FXML
    public void switchtosceneMain(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
