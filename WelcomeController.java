package pecunia;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;



public class WelcomeController {
    public static File NewFile = new File("$%$%$");

    private TextInputDialog td = new TextInputDialog("");
    private File folder = new File("data");
    private File[] listOfFiles = folder.listFiles();
    public static File SelectedFile;
    public static String currentTransaction;
    public static ArrayList<Integer> IDArray = new ArrayList<>();
    private int NewId;
    public static String args;
    @FXML
    ListView<String> Files;

    /**
     * initializes the scene
     * First adds all of the files in a "data" directory to Listview
     * Then handles input of if a file was clicked in a list, and launches CSVReader for a respective file
     * After file has been selected, read, and has been stored in MainController.TransactionsData switches to scene main.fxml
     *
     */
    public void initialize() {

        listOfFiles = folder.listFiles();
        for (File listOfFile : listOfFiles) {
            Files.getItems().addAll(listOfFile.getName()); //.substring(0, listOfFile.getName().indexOf(MainController.currency.toString()))); // adds a file name to listview by looping through all the files in listOfFiles
            IDArray.add(Integer.parseInt(listOfFile.getName().substring(listOfFile.getName().indexOf(MainController.currency.toString())+1).replace(MainController.currency.toString(), "").replace(".csv", "")));
        }
        Files.getItems().clear();
        for (File listOfFile : listOfFiles) {
            Files.getItems().addAll(listOfFile.getName().substring(0, listOfFile.getName().indexOf(MainController.currency.toString()))); // adds a file name to listview by looping through all the files in listOfFiles
        }
        Files.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> { //adds a listener that is keeping track of if something in a list was selected
            String wallet;
            currentTransaction = Files.getSelectionModel().getSelectedItem();

                for (int n = 0; n < listOfFiles.length; n++) { // as long as the chosen option is in the bounds of our list of file names
                    if (NewId == 0) {
                        if (listOfFiles[n].getPath().equals("data" + File.separator + currentTransaction + MainController.currency + IDArray.get(n) + ".csv")) {// if the chosen option is inside our list XOR new file name is in currentTransaction

                            MainController.id = IDArray.get(n).toString();
                            SelectedFile = listOfFiles[n];
                            wallet = "data" + File.separator + currentTransaction + MainController.currency + IDArray.get(n) + ".csv";
                            try {
                                Scanner filescanner = new Scanner(new FileInputStream(wallet));
                                MainController.TransactionsData = CSVReader.CSV(wallet, filescanner);
                                switchtosceneMain();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    } else {
                        if (WalletController.rename.getPath().equals("data" + File.separator
                                + currentTransaction + MainController.currency.toString() + NewId + ".csv")) {
                            MainController.id = String.valueOf(NewId);
                            SelectedFile = listOfFiles[n];
                            wallet = "data" + File.separator + currentTransaction + MainController.currency + NewId + ".csv";
                            try {
                                Scanner filescanner = new Scanner(new FileInputStream(wallet));
                                MainController.TransactionsData = CSVReader.CSV(wallet, filescanner);
                                switchtosceneMain();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                IDArray.clear();
        });
    }

    /**
     * switchtosceneMain() calls and loads our main fxml file whenever called by the user.
     * We are simply calling a new FXMLLoader and then, relying onto our ListView, we
     * are able to load the needed scene.
     *
     *
     * @throws IOException
     */
    public void switchtosceneMain() throws IOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
            Stage stage = (Stage) Files.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (NullPointerException e){
        }
    }

    /**
     * quit() allows user to safely quit as soon as they decide to do so. We set up a new alert window
     * of an INFORMATION type, and provide every bit of needed information, accordingly warning user about
     * a potential danger of their files being lost if not saved.
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
     * New() method sets up a new TextInputDialog that helps us take in user input as the name of the file.
     * We then take the data from that textField with .getText() and assign it as the name of
     * the new file that has been created. We also add the new file name to our global
     * ListView, updating it accordingly.
     *
     *
     * @throws IOException
     */
    public void New() throws IOException {
        TilePane r = new TilePane();
        td.setHeaderText("Enter the name of the wallet");
        javafx.scene.control.Button button = new javafx.scene.control.Button("click");
        TextField textField = td.getEditor();
        td.showAndWait();
        NewFile = new File("data" + File.separator + textField.getText().replaceAll("[^a-zA-Z0-9]", ""));
        NewFile.createNewFile();
        MainController.id = String.valueOf(Math.floor(Math.random()*(99999-10000+1)+10000)).replace(".0", "");
        WalletController.rename = new File(NewFile.getPath() + MainController.currency + MainController.id + ".csv");
        NewFile.renameTo(WalletController.rename);
        Files.getItems().add(WalletController.rename.getName().replace(MainController.id, "").replace(MainController.currency.toString(), "").replace(".csv",""));
        listOfFiles = folder.listFiles();
        WelcomeController.NewFile = new File("$%$%$");
        NewId = Integer.parseInt(WalletController.rename.getName().substring(WalletController.rename.getName().indexOf(MainController.currency.toString())+1).replace(MainController.currency.toString(), "").replace(".csv", ""));
    }
}
