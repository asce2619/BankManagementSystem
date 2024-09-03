/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bank;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author janni
 */
public class ManagerInterfaceController extends Manager implements Initializable{

    @FXML
    private TextField addUsername;
    @FXML
    private TextField addPassword;
    @FXML
    private TextField deleteUsername;
    @FXML
    private AnchorPane managerHomePage;
    @FXML
    private AnchorPane addCustomerPage;
    @FXML
    private AnchorPane deleteCustomerPage;
    @FXML
    private AnchorPane managerPage;

    @FXML
    private TableView<?> addTable;
    @FXML
    private TableColumn<?, ?> numberCol;
    @FXML
    private TableColumn<?, ?> usernameCol;
    @FXML
    private TableColumn<?, ?> passwordCol;
    @FXML
    private TableColumn<?, ?> balanceCol;
    @FXML
    private TableColumn<?, ?> levelCol;
    @FXML
    private TextField customerSearchAdd;
    @FXML
    private TextField customerSearchDelete;
    
    //private final ObservableList <Manager> list = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void managerHomeButton(ActionEvent event) {
        deleteCustomerPage.setVisible(false);
        addCustomerPage.setVisible(false);
        managerHomePage.setVisible(true);//home page visible only
    }

    @FXML
    private void addCustomersButton(ActionEvent event) {
        deleteCustomerPage.setVisible(false);
        managerHomePage.setVisible(false);
        addCustomerPage.setVisible(true);//customer add page visible only
        
        //printTableView();
    }

    @FXML
    private void deleteCutomersButton(ActionEvent event) {
        addCustomerPage.setVisible(false);
        managerHomePage.setVisible(false);
        deleteCustomerPage.setVisible(true);//customer delete page visible only
        
        //printTableView();
    }

    @FXML
    private void logoutButton(ActionEvent event) throws Exception {
        Stage stageLogin = (Stage) managerPage.getScene().getWindow();
        stageLogin.close();//close manager page
            
        /* open login page again */
        Parent root = FXMLLoader.load(getClass().getResource("userInterface.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML/* NOT WORKING (extra feature: Search for customer in add and delete) */
    private void searchForCustomer(ActionEvent event) throws Exception {
        Button sourceButton = (Button) event.getSource();
        String search = null;
        
        if(sourceButton.equals(customerSearchAdd)){
            search = customerSearchAdd.getText();
        }
        else if(sourceButton.equals(customerSearchDelete)){
            search = customerSearchDelete.getText();
        }
        
        int found = 0;
        String un = null;
        
        String currentDirectory = System.getProperty("user.dir"); 
        File dir = new File(currentDirectory);

        for(File file : dir.listFiles()){
            if(file.getName().endsWith((".txt"))){
                try(Scanner readFile = new Scanner(file)){
                    un = readFile.next();
                    String pw = readFile.next();
                    int ba = Integer.parseInt(readFile.next());
                    //if customer found
                    if(search.equals(un)){
                        found = 1;
                        break;
                    }
                }
                catch(Exception e3){}   
            }
        }
        
        if(found == 1 && sourceButton.equals(customerSearchAdd)){
            customerSearchAdd.clear();
            Parent root = FXMLLoader.load(getClass().getResource("customerFoundPage.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else if(found == 0 && sourceButton.equals(customerSearchDelete)){
            customerSearchDelete.clear();
            Parent root = FXMLLoader.load(getClass().getResource("customerFoundPage.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else{
            customerSearchAdd.clear();
            customerSearchDelete.clear();
            Parent root = FXMLLoader.load(getClass().getResource("customerNotFoundPage.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML/* DOES account for error (deleting customer that does not exist */
    private void deleteCustomer(ActionEvent event) throws IOException {
        String username = deleteUsername.getText();
        
        File fileName = new File(username + ".txt");
        //if file exists
        if(fileName.exists()){
            deleteCustomer(username);//delete customer from manager class
            deleteUsername.clear();
        }
        else{
            /* Deleting Customer Error */
            Parent root = FXMLLoader.load(getClass().getResource("deleteCustomerError.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        
        //printTableView();
    }

    @FXML/* DOES account for error (adding customer that already exists) */
    private void addButton(ActionEvent event) throws IOException{
        String username = addUsername.getText();
        String password = addPassword.getText();
        
        addCustomer(username, password);//adds customer from manager class
        
        addUsername.clear();
        addPassword.clear();
        
        //printTableView();
    }
    
    /* NOT WORKING (extra feature: table view to see list of customer)
    private void printTableView(){
        File[] allFiles = fileName.listFiles(); 
        BufferedReader reader;
        
        if (allFiles != null){
            for (File file : allFiles){
                try{
                    reader = new BufferedReader(new FileReader(file));
                    
                    Scanner scan = new Scanner(reader);
                    
                    while(scan.hasNext()){
                        
                        
                    }
                }
                catch(IOException e){
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } 
        }
    }*/

}
