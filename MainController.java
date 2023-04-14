package pecunia;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.*;
/**
 * NAMEs: Yurii Bezborodov (30114669), Tamerlan Ormanbayev (30141208)
 * TUTORIALs: T07, T03
 * DATE: 18th MARCH 2022

 */
public class MainController {
    public static String name = "MyPecunia";
    public static String id;
    public static Object currency = "$";
    private Stage stage;
    private Scene scene;
    private Parent root;


    /**
     * Here we declare a new ArrayList that we will soon use in every other class
     */
    public static ArrayList<Object[]> TransactionsData = new ArrayList<>();

    /**
     * switchtosceneTransaction() is a function that is needed to switch from the main GUI scene
     * to Transaction GUI scene, in other words we are loading a different fxml file.
     *
     *
     * @param e
     * @throws IOException
     */
    public void switchtosceneTransaction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Transaction.fxml")));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * switchtosceneStats() is a function that is needed to switch from the main GUI scene
     * to Statistics GUI scene, in other words we are loading a different fxml file.
     *
     *
     * @param e
     * @throws IOException
     */
    public void switchtosceneStats(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Stats.fxml")));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * showpopuptransaction() is a very simple method needed to call switchtosceneTransaction
     * We also bind it in Scene Builder.
     *
     *
     * @param e
     * @throws IOException
     */
    @FXML
    public void showpopuptransaction(ActionEvent e) throws IOException {
        switchtosceneTransaction(e);
    }

    /**
     * initialize() constantly updates our global list, and prints fresh data from an ArrayList
     */
    public void initialize(){
        list.getItems().clear();
        PrintToList(TransactionsData);

    }


    /**
     * quit() lets the user safely quit, by declaring a new alert. It contains a lot of useful info
     * warning the user about danger of losing any unsaved changes.
     */
    public void quit(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Any unsaved changes may be lost.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isEmpty()){
            System.out.println("Message closed.");
        }
        else if (result.get() == ButtonType.OK){
            System.exit(0);
        }
        else if (result.get() == ButtonType.NO){
            "".isEmpty();
        }
    }

    /**
     * here we declare our global ListView
     */
    @FXML
    ListView<String> list;

    /**
     * PrintToList() is needed to not only update the global ListView, we also need it to replace
     * unnecessary square brackets on the ends of every transaction.
     *
     *
     * @param Transactions
     */
    public void PrintToList(ArrayList<Object[]> Transactions){
        for (int n = 0; n < Transactions.size(); n++) {
            list.getItems().addAll(currency + Arrays.toString(Transactions.get(n)).replace("[", "").replace("]",""));
        }
    }

    /**
     * switchtosceneEdit() is a function that is needed to switch from the main GUI scene
     * to Edit GUI scene, in other words we are loading a different fxml file.
     *
     *
     * @param e
     * @throws IOException
     */
    public void switchtosceneEdit(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Edit.fxml")));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * switchtosceneStats() is a function that is needed to switch from the main GUI scene
     * to Wallet GUI scene, in other words we are loading a different fxml file.
     *
     *
     * @param e
     * @throws IOException
     */
    public void switchtosceneWallet(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Wallet.fxml")));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Save() simply calls our Writer class whenever user presses a needed button.
     *
     *
     * @throws IOException
     */
    public void Save() throws IOException {
        WriterCSV.CSVW(WelcomeController.currentTransaction + "$" + MainController.id + ".csv");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Saved");
        alert.setHeaderText("Changes saved successfully!");
        alert.setContentText("Press OK to close this message.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isEmpty()){
            System.out.println("");
        }
    }
}
