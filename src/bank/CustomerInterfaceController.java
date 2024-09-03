/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author janni
 */
public class CustomerInterfaceController extends BankAccount implements Initializable{

    @FXML
    private AnchorPane customerPage;
    @FXML
    private TextField depositBalance;
    @FXML
    private TextField depositLevel;
    @FXML
    private TextField depositAmount;
    @FXML
    private AnchorPane homePage;
    @FXML
    private AnchorPane depositPage;
    @FXML
    private AnchorPane withdrawPage;
    @FXML
    private TextField withdrawBalance;
    @FXML
    private TextField withdrawLevel;
    @FXML
    private TextField withdrawAmount;
    @FXML
    private AnchorPane onlinePage;
    @FXML
    private TextField onlineLevel;
    @FXML
    private TextField purchaceAmount;
    @FXML
    private TextField levelTax;
    @FXML
    private TextField totalAmount;
    @FXML
    private TextField onlineBalance;
    @FXML
    private Button headphone;
    @FXML
    private Button pens;
    @FXML
    private Button paper;
    @FXML
    private Button laptop;
    @FXML
    private Button shirt;
    @FXML
    private Button shoes;

    //for online purchase
    private int amount = 0;
    private int totalCost = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public Customer c;
    //recives data from user interface after customer login
    public void reciveData(Customer c){
        this.c = c;
    }
    
    @FXML
    private void customerHomeButton(ActionEvent event) {
        depositPage.setVisible(false);
        withdrawPage.setVisible(false);
        onlinePage.setVisible(false);
        homePage.setVisible(true);//home page visable only
        
        /*resets online purchase*/
        amount = 0;
        totalCost = 0;
        purchaceAmount.setText(String.valueOf(amount));
        totalAmount.setText(String.valueOf(totalCost));
    }


    @FXML
    private void logoutButton(ActionEvent event) throws Exception {
        Stage stageLogin = (Stage) customerPage.getScene().getWindow();
        stageLogin.close();//close customer page
            
        /* open login page again */
        Parent root = FXMLLoader.load(getClass().getResource("userInterface.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void onlinePurchaceButton(ActionEvent event) {
        homePage.setVisible(false);
        depositPage.setVisible(false);
        withdrawPage.setVisible(false);
        onlinePage.setVisible(true);//online purchase page visable only
        
        /*resets online purchase*/
        amount = 0;
        totalCost = 0;
        
        onlineBalance.setText(String.valueOf(c.getBalance()));
        onlineLevel.setText(c.getAccountLevel(c));
        levelTax.setText(c.getTax(c));
        purchaceAmount.setText(String.valueOf(amount));
        totalAmount.setText(String.valueOf(totalCost));
    }

    @FXML
    private void customerDepositeButton(ActionEvent event) {
        withdrawPage.setVisible(false);
        onlinePage.setVisible(false);
        homePage.setVisible(false);
        depositPage.setVisible(true);//deposit page visable only
        
        /*resets online purchase*/
        amount = 0;
        totalCost = 0;
        purchaceAmount.setText(String.valueOf(amount));
        totalAmount.setText(String.valueOf(totalCost));
        
        depositBalance.setText(String.valueOf(c.getBalance()));
        depositLevel.setText(c.getAccountLevel(c));
    }

    @FXML
    private void customerWithdrawButton(ActionEvent event) {
        onlinePage.setVisible(false);
        homePage.setVisible(false);
        depositPage.setVisible(false);
        withdrawPage.setVisible(true);//withdraw page visable only
        
        /*resets online purchase*/
        amount = 0;
        totalCost = 0;
        purchaceAmount.setText(String.valueOf(amount));
        totalAmount.setText(String.valueOf(totalCost));
        
        withdrawBalance.setText(String.valueOf(c.getBalance()));
        withdrawLevel.setText(c.getAccountLevel(c));
    }
    
    @FXML
    private void depositButton(ActionEvent event) {
        int deposit = Integer.parseInt(depositAmount.getText());
        c.addAmount(deposit);//add deposit amount
        depositAmount.clear();
    }

    @FXML
    private void depositeUpdate(ActionEvent event) throws FileNotFoundException, IOException {
        c.setAccountLevel(c);//sets account level according to new balance
        
        /* Updates file with new balance */
        File fileName = new File(c.getUsername() + ".txt");
        Scanner readFile = new Scanner(fileName);
        String un = readFile.next();
        String pw = readFile.next();
        String bal = readFile.next();
        String content = un + "\n" + pw + "\n" + String.valueOf(c.getBalance());
        FileWriter writer = new FileWriter(c.getUsername() + ".txt");
        writer.append(content);
        writer.flush();
        
        //outputs new values
        depositBalance.setText(String.valueOf(c.getBalance()));
        depositLevel.setText(c.getAccountLevel(c));
    }

    @FXML
    private void withdrawUpdate(ActionEvent event) throws IOException {
        c.setAccountLevel(c);//sets account level according to new balance
        
        /* Updates file with new balance */
        File fileName = new File(c.getUsername() + ".txt");
        Scanner readFile = new Scanner(fileName);
        String un = readFile.next();
        String pw = readFile.next();
        String bal = readFile.next();
        String content = un + "\n" + pw + "\n" + String.valueOf(c.getBalance());
        FileWriter writer = new FileWriter(c.getUsername() + ".txt");
        writer.append(content);
        writer.flush();
        
        //outputs new values
        withdrawBalance.setText(String.valueOf(c.getBalance()));
        withdrawLevel.setText(c.getAccountLevel(c));
    }

    @FXML/* DOES account for error (withdraw more then balance in account) */
    private void withdrawButton(ActionEvent event) throws IOException {
        int withdraw = Integer.parseInt(withdrawAmount.getText());
        
        if(withdraw <= c.getBalance()){
            c.removeAmount(withdraw);//removes withdraw amount
            withdrawAmount.clear();
        }
        else{
            /* Withdraw fail */
            Parent root = FXMLLoader.load(getClass().getResource("withdrawFail.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    private void onlinePurchase(ActionEvent event) {
        //gets exactly which button was pressed
        Button sourceButton = (Button) event.getSource();
        
        //adds to the amount depending on what button was pressed
        if(sourceButton.equals(headphone)){
            amount += 2500;
        }
        else if(sourceButton.equals(pens)){
            amount += 5;
        }
        else if(sourceButton.equals(paper)){
            amount += 2;
        }
        else if(sourceButton.equals(shirt)){
            amount += 15;
        }
        else if(sourceButton.equals(shoes)){
            amount += 45;
        }
        else if(sourceButton.equals(laptop)){
            amount += 5000;
        }
        
        //new total cost after level is applied
        totalCost = amount + (Integer.parseInt(c.getTax(c)));
        
        //updates visual amounts on screen
        purchaceAmount.setText(String.valueOf(amount));
        totalAmount.setText(String.valueOf(totalCost));
    }

    @FXML/* DOES account for error (balance not enough for online purchase or less then 50) */
    private void purchaseButton(ActionEvent event) throws IOException, Exception {
        //total cost after checking if balance appropriate
        int total = c.onlinePurchase(c, c.getBalance(), totalCost);
        
        if(total > 0){
            c.removeAmount(total);//removes total cost from balance
        
            c.setAccountLevel(c);//resets level according to new balance

            //updates file with new balance
            File fileName = new File(c.getUsername() + ".txt");
            Scanner readFile = new Scanner(fileName);
            String un = readFile.next();
            String pw = readFile.next();
            String bal = readFile.next();
            String content = un + "\n" + pw + "\n" + String.valueOf(c.getBalance());
            FileWriter writer = new FileWriter(c.getUsername() + ".txt");
            writer.append(content);
            writer.flush();

            //resets online purchase
            amount = 0;
            totalCost = 0;

            onlineBalance.setText(String.valueOf(c.getBalance()));
            onlineLevel.setText(c.getAccountLevel(c));
            levelTax.setText(c.getTax(c));
            purchaceAmount.setText(String.valueOf(amount));
            totalAmount.setText(String.valueOf(totalCost));
        }
        else if(total == 0){
            /* Purchase Failed (balance is not enough) */
            Parent root = FXMLLoader.load(getClass().getResource("purchaseFail.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
            //resets online purchase
            amount = 0;
            totalCost = 0;
            purchaceAmount.setText(String.valueOf(amount));
            totalAmount.setText(String.valueOf(totalCost));
        }
        else if(total == -1){
            /* Total Cost Error (less then 50) */
            Parent root = FXMLLoader.load(getClass().getResource("totalCostFail.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
            //no need to reset online purchase so customer can continue to add
        }
    }
}
