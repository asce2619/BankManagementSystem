/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author janni
 */
public class userInterfaceController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Label welcomeLabel;
    @FXML
    private PasswordField passwordLogin;
    @FXML
    private MenuButton accountType;
    @FXML
    private TextField userLogin;
    @FXML
    private Button login;
    @FXML
    private AnchorPane loginPage;
    
    public static String role;
    
    public Customer c;
    
    
    @FXML
    private void loginButtonAction(ActionEvent event) throws Exception {
        String username = userLogin.getText();
        String password = passwordLogin.getText();
        int enter = 0;
        int customer = 0;
        
        /*Manger Login*/
        if(username.equals("admin") && password.equals("admin") && role.equals("Manager")){
            Stage stageLogin = (Stage) loginPage.getScene().getWindow();
            stageLogin.close();//close login scene
            
            /* Manager Page */
            Parent root = FXMLLoader.load(getClass().getResource("managerInterface.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        /* Customer login */
        else if(role.equals("Customer")){
            customer = 1;
            String currentDirectory = System.getProperty("user.dir"); 
            File dir = new File(currentDirectory);

            for(File file : dir.listFiles()){
                if(file.getName().endsWith((".txt"))){
                    try(Scanner readFile = new Scanner(file)){
                        String un = readFile.next();
                        String pw = readFile.next();
                        int ba = Integer.parseInt(readFile.next());
                        if(username.equals(un) && password.equals(pw)){
                            enter = 1;//meaning customer created
                            c = new Customer(un, pw, ba);//makes temp customer according to file
                            break;
                        }
                    }
                    catch(Exception e3){}   
                }
            }
        }
        /* invalid login window */
        else{
            Parent root = FXMLLoader.load(getClass().getResource("loginError.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        
        if(customer == 1){
            //if customer created
            if(enter == 1){
                Stage stageLogin = (Stage) loginPage.getScene().getWindow();
                stageLogin.close();//closes login page
                
                try{
                    /* Opens Customer page */
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("customerInterface.fxml"));
                    Parent root = (Parent)loader.load();
                    
                    CustomerInterfaceController controller = loader.getController();
                    controller.reciveData(c); //passes customer logging in to customer controller
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                }
                catch(IOException e){
                    System.out.println("Error");
                }
            }
            else{
                /*Login Error*/
                Parent root = FXMLLoader.load(getClass().getResource("loginError.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void managerLogin(ActionEvent event) {
        accountType.setText("Manager");
        role = "Manager";//account type is manager
    }

    @FXML
    private void customerLogin(ActionEvent event) {
        accountType.setText("Customer");
        role = "Customer";//account type is customer
    }
    
}
