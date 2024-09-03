/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package bank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * COE528 Bank Project
 * @author Jannis Saini
 * @studentID 501168140
 * @section 14
 * @date 26/03/2024
 * 
 * Overview Clause written in BankAccount class
 * GUI built using Scene Builder
 */
public class Bank extends Application{
    
    @Override
    public void start(Stage stage) throws Exception {
        /* Login Page */
        Parent root = FXMLLoader.load(getClass().getResource("userInterface.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    } 
}
