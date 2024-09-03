/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

/**
 * COE528 Bank Project
 * @author Jannis SainiS
 * @studentID 501168140
 * @section 14
 * @date 26/03/2024
 * 
 * GUI built using Scene Builder
 * 
 * Overview: BankAccount is a mutable class. 
 *           it represents the amount of money the user has in balance
 *           balance an be greater then or equal to zero
 * 
 * Abstraction function is:
 * bank account c with a balance equal to c.balance
 * AF(c) = c.balance = c.balance
 * 
 * The rep invariant:
 * balance of bank account c is always greater than or equal to zero
 * RI(c) = true if c.balance >= 0
 *       = false if c.balance < 0
 */
public abstract class BankAccount {
    
    //rep
    protected int balance;
    
    /**
     * Bank Account
     * @EFFECTS: sets this.balance to 100 (default value)
     * @MODIFIES: nothing
     */
    public BankAccount(){
        this.balance = 100;//default balances
    }
    
    /**
     * Get Balance
     * @EFFECTS: returns this.balance
     * @MODIFIES: nothing
     */
    protected int getBalance(){
        return this.balance;
    }
    
    /**
     * Set Balance
     * @REQUIRES: newBalance not less then zero (newBalance >= 0)
     * @EFFECTS: changes this.balance to newBalance
     * @MODIFIES: balance
     */
    protected void setBalance(int newBalance){
        if(newBalance >= 0)
            this.balance = newBalance;//sets new balance
    }
    
    /**
     * Add Amount
     * @REQUIRES: amount >= 0
     * @EFFECTS: adds amount to this.balance
     * @MODIFIES: balance
     */
    protected void addAmount(int amount){
        this.balance += amount;//adds to amount
    }
    
    /**
     * Remove Amount
     * @REQUIRES: amount >= 0 && amount <= this.balance
     * @EFFECTS: subtracts amount from this.balance
     * @MODIFIES: balance
     */
    protected void removeAmount(int amount){
            this.balance -= amount;//subtracts from amount
    }
    
    /**
     * Online Purchase
     * @REQUIRES: bal >= 0 && amount >= 0 && amount >= 50 && bal >= amount
     * @EFFECTS: checks the bal and amount accordingly in subclasses and amount is returned; or -1 or 0 for error
     * @MODIFIES: nothing
     */    
    public int onlinePurchase(int bal, int amount) throws Exception{
        return amount;//amount returned 
        //(In Subclasses: only if amount less then or equal to balance and greater then or equal to 50...
        //...otherwise subclasses will return 0 for not enough balance...
        //...or return -1 if total cost less then 50. Error messages will pop up)
    } 
    
    /**
     * Get Account Level
     * @EFFECTS: returns state of the current accounts level
     * @MODIFIES: nothing
     */
    public  String getAccountLevel(){
        return getAccountLevel();//return account level
    }
    
    /**
     * Get Tax
     * @EFFECTS:returns the current accounts Tax from its state level 
     * @MODIFIES: nothing 
     */
    public String getTax(){
        return getTax();//return ta
    }
    
    /**
     * RepOK
     * @EFFECTS: returns true if this.balance >=0 otherwise returns false
     * @MODIFIES: nothing
     * 
     * implements the rep invariant
     */
    public boolean RepOK(){
        return this.balance >= 0;
    }
    
    //EFFECTS: returns abstraction function - that is returns balance of this
    /**
     * toString
     * @EFFECTS: returns the bank account balance
     * @MODIFIES: nothing
     * 
     * implements the abstraction function
     */
    @Override
    public String toString(){
        return "Balance is $" + this.balance;
    }
}
