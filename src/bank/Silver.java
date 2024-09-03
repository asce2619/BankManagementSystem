/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

/**
 *
 * @author janni
 */
public class Silver extends BankAccount{
    
    public Silver(){
        super();//sets default balance 100
    }
    
    @Override
    public int onlinePurchase(int bal, int amount) throws Exception{
        //if amount is less then or equal balance and greater then or equal to 50 
        if(bal >= amount && amount >= 50){
            return amount;
        }
        else if (amount < 50){
            return -1;//if total cost is less then 100
        }
        return 0;//if balance is not enough
    }
    
    @Override
    public String getAccountLevel(){
          return "Silver";//returns level to customer controller
    }
    
    @Override
    public String getTax(){
        return "20";//applies the tax in customer controller
    }
}
