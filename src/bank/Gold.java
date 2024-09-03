/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

/**
 *
 * @author janni
 */
public class Gold extends BankAccount{
    
    public Gold(){
        super();//sets default balance of 100
    }
    
    @Override
    public int onlinePurchase(int bal, int amount) throws Exception{
        //if amount is less then or equal balance and greater then or equal to 50 
        if(bal >= amount && amount >= 50){
             return amount; 
        }
        else if(amount < 50){
            return -1;//if amount is less then 50
        }
        return 0;//if balance is not enough
    }
    
    @Override
    public String getAccountLevel(){
          return "Gold";//returns level to customer controller
    }
    
    @Override
    public String getTax(){
        return "10";//applies the tax in customer controller
    }
}
