/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

/**
 *
 * @author janni
 */
public class Platinum extends BankAccount{
    
    public Platinum(){
        super();//sets default balance of 100
    }
    
    @Override
    public int onlinePurchase(int bal, int amount) throws Exception{
        //if amount is less then or equal balance and greater then or equal to 50 
        if(bal >= amount  && amount >= 50){
            return (amount);
        }
        
        else if(amount < 50){
            return -1;//if amount less then 50
        }
        return 0;//if balance not enough
    }
    
    @Override
    public String getAccountLevel(){
          return "Platinum";//returns level to customer controller
    }
    
    @Override
    public String getTax(){
        return "0";//applies the tax in customer controller
    }
}
