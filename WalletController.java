package pecunia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class WalletController implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    Label WalletName;
    @FXML
    ChoiceBox currency;
    @FXML
    Label TrAmount;
    @FXML
    Label TrSum;
    @FXML
    Label WalletID;
    private TextInputDialog td = new TextInputDialog("");
    private Object currentChoice;
    public static File rename = new File("data/");

    /**
     * Initializes the scene, fills in data in its respective labels
     * Handles choice of currency
     * Sets the file name to its correct format just in case
     *
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] CurrencyChoice = {"$", "€", "£", "₴", "₸"};
        currency.getItems().addAll(CurrencyChoice);
        TrAmount.setText("Total amount of transactions: " + MainController.TransactionsData.size());
        TrSum.setText("Total sum of transactions: " + TotalAmount());
        WalletName.setText("Wallet name: " + WelcomeController.currentTransaction.replace(".csv", ""));
        WalletID.setText("Wallet ID: " + Integer.parseInt(MainController.id));
        currency.setValue(MainController.currency);
        currency.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            currentChoice = currency.getSelectionModel().getSelectedItem();
            MainController.currency = currentChoice;
            currency.setValue(MainController.currency);
        });
        if(!WelcomeController.NewFile.getPath().equals("$%$%$")) {
            rename = new File("data" + File.separator + WelcomeController.currentTransaction + "$" + MainController.id);
            WelcomeController.SelectedFile.renameTo(rename);
        }
    }

    /**
     * switchtosceneMain() is needed to load in the main fxml file as soon as the user has chosen a file to load
     * in. We are using the same stage, but we create a new scene out of the main.fxml file.
     *
     *
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
     * TotalAmount() simply sums up all the existing transactions in the wallet, outputting the final
     * number as another option to view statistic, as a double number. It interacts with the global
     * ArrayList TransactionsData.
     *
     *
     * @return
     */
    public double TotalAmount(){
        double sum = 0;
        int n = 0;
        while (n < MainController.TransactionsData.size()){
            Object[] Transaction = MainController.TransactionsData.get(n);
            sum += Double.parseDouble(Transaction[0].toString());
            n += 1;
        }
        return sum;
    }

    /**
     * AboutDisplay() displays out an informational alert about the program, as well as about its
     * creators.
     */
    public void AboutDisplay() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("About Pecunia app");
        alert.setContentText("Authors: Tamerlan Ormanbayev, Yurii Bezborodov" + "\n"
                + "Pecunia v1.0" + "\n"
                + "Inquiries: tamerlan.ormanbayev@ucalgary.ca; yurii.bezborodov@ucalgary.ca" + "\n");
        Optional<ButtonType> About = alert.showAndWait();
    }

    /**
     * ChangeName() creates a new TextInputDialog and takes text from the textField in the TextInputDialog
     * It waits until user presses 'OK', as well as it also bans all of the prohibited symbols. After that
     * it updates the file name, it also updates the name showing in the settings line.
     */
    public void ChangeName(){
        TilePane r = new TilePane();
        td.setHeaderText("Enter the name of the wallet");
        javafx.scene.control.Button button = new javafx.scene.control.Button("click");
        TextField textField = td.getEditor();
        td.showAndWait();
        MainController.name = textField.getText().replaceAll("[^a-zA-Z0-9]", "");
        WalletName.setText("Wallet name: " + MainController.name);
        WelcomeController.currentTransaction = MainController.name + "$" + MainController.id + ".csv";
        rename = new File("data" + File.separator + WelcomeController.currentTransaction);
        WelcomeController.SelectedFile.renameTo(rename);
    }

    /**
     * switchtosceneWelcome() simply loads in a different FXML that we need to switch back to the Welcome screen,
     * if the user decides to go back and try to load a different file. We are getting the same scene and
     * we load it in.
     *
     *
     * @param e
     * @throws IOException
     */
    public void switchtosceneWelcome(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("WecomeMenu.fxml")));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}

