/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author janni
 */
public class Manager{
    public File fileName;
    public static int number = 0;
    public String curDir = System.getProperty("user.dir");
    
    public String num;
    public String storedUsername;
    public String storedPassword;
    public String storedBalance;
    public String storedLevel;
    
    private String username;
    private String password;
    private String role = "Manager";
    
    public Customer addCustomer(String username, String password) throws IOException{        
        return new Customer(username,password);//adds customer (creates their file)
    }
    
    public void deleteCustomer(String username){
        File file = new File(username + ".txt");
        file.delete();//delets customer (file deleted as well)
    }
    
    /* does NOT work (extra search feature) */
    public void search(String search) throws Exception{
        int found = 0;
        File dir = new File(curDir);
        
        for (File file: dir.listFiles()){
            String fName = file.getName();
            if(fName.endsWith((".txt")) && fName.equals("Bank"+search+".txt")){
                try(Scanner scan = new Scanner(file);) {
                    found = 1;
                    num = scan.nextLine();
                    storedUsername = scan.nextLine();
                    //if(search.equals(storedUsername)){
                        storedPassword = scan.nextLine();
                        storedBalance = scan.nextLine();
                        storedLevel = scan.nextLine();

                        System.out.println(num);
                        System.out.println(storedUsername);
                        System.out.println(storedPassword);

                        Parent root = FXMLLoader.load(getClass().getResource("customerFoundPage.fxml"));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                        break;
                    //}
                }
                catch (FileNotFoundException ex) {
                    System.out.println("File not found");
                }
            }
        } 
        
        if(found == 0){
            Parent root = FXMLLoader.load(getClass().getResource("customerNotFoundPage.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            return;
        }
    }
}
