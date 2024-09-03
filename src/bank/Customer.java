/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author janni
 */
public class Customer extends BankAccount{
    
    private String username;
    private String password;
    private String role = "customer";
    protected BankAccount account;
    
    /**
     * Customer Login Constructor
     * @param username
     * @param password
     * @param balance 
     * 
     * when customers login saves the customers details
     */
    public Customer(String username, String password, int balance){
        File fileName = new File(username + ".txt");
        if(fileName.exists()){
            this.username = username;
            this.password = password;
            setAccountLevel(balance);//sets account level
            //sets balance in the account according to customers balance in file
            this.setBalance(balance);
        }
    }
    
    /**
     * Customer File Creation
     * @param username
     * @param password 
     * 
     * called when manager adds customer
     */
    public Customer(String username, String password){
        try{
            File fileName = new File(username + ".txt");
            if(fileName.createNewFile() == true){
                this.username = username;
                this.password = password;
                account = new Silver(); //by default customers start as silver
                FileWriter writeToFile = new FileWriter(username + ".txt");
                writeToFile.write(username + "\n");
                writeToFile.write(password + "\n");
                writeToFile.write(""+100);//default balance 100
                writeToFile.close();
            }
            else{
                /*adding customer failed (customer already exists)*/
                Parent root = FXMLLoader.load(getClass().getResource("addingCustomerError.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                return;
            }
        }
        catch(IOException e){
            System.out.println("IOException occured");
        }
    }
    
    /**
     * Set Account Level during code
     * @param c 
     * 
     * whenever code calls to reset account level
     */
    public void setAccountLevel(Customer c){
        if(c.getBalance() < 10000){
            c.account = new Silver();
            c.account.setBalance(balance);
        }
        if(c.getBalance() >= 10000 && c.getBalance() < 20000){
            c.account = new Gold();
            c.account.setBalance(balance);
        }       
        if(c.getBalance() >= 20000){
            c.account = new Platinum();
            c.account.setBalance(balance);
        }  
    }
    
    /**
     * Initial Set Account Level
     * @param bal 
     * 
     * when customers login account is set
     */
    public void setAccountLevel(int bal){
        if(bal < 10000){
            account = new Silver();
        }
        if(bal >= 10000 && bal < 20000){
            account = new Gold();
        }       
        if(bal >= 20000){
            account = new Platinum();
        } 
    }
    
    /**
     * Get Account Level
     * @param c
     * @return account level
     * 
     * From the override method of bank account level
     */
    public String getAccountLevel(Customer c){
        return c.account.getAccountLevel();
    }
    
    /**
     * Get Tax
     * @param c
     * @return account tax
     * 
     * From the override method of bank account level
     */
    public String getTax(Customer c){
        return c.account.getTax();
    }
    
    /**
     * Online Purchase
     * @param c
     * @param bal
     * @param amount
     * @return
     * @throws Exception 
     * goes to override method and confirms if balance is acceptable
     */
    public int onlinePurchase(Customer c, int bal, int amount) throws Exception{
        return c.account.onlinePurchase(bal, amount);
    }

    //returns customer username
    public String getUsername() {
        return username;
    } 
    
}
