/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author bmami
 */
public class Silver extends Level{
    
    
    private final double fee = 20;
    
    public static void main(String[] args) {
        
        // Create an instance of Platinum
        Silver silver = new Silver();

        // Check the toString() method
        System.out.println("toString() result: " + silver.toString());

        // Check the repOk() method
        System.out.println("repOk() result: " + silver.repOk());
    }
    
 /**
 * Overview:
 * 
 * This class is a type of level for a customer, which is a child
 * class of level. It implements methods to handle level changes
 * based on the customer's balance and defines a fixed fee associated with
 * the Silver level. 
 * 
 * Instances of this class are mutable as they allow for the
 * modification of the level through the changeLevel() method.
 * 
 * The abstraction function is:
 *  every instance will return the level of that instance
 *
 * The Rep Invariant is:
 *   fee = 20;
 *   
 * 
 * 
 */
    
  
    
    

    /**
     * REQUIRES: None
     * MODIFIES: None
     * EFFECTS: gets the fee for the silver level
     */
    //getter for the fee
    public double getFee() {
        return this.fee;
    }
    
    /**
     * REQUIRES: the customer != null
     * MODIFIES: the level of the customer instance
     * EFFECTS: Changes the customer's level based on balance. 
     */
    //method to change level 
    //done like this for all level classes to lessen writing and if statements
    /*(Instead of checking the price in main code, it checks it in this class and then does the 
    proper level creation*/
    @Override
    public void changeLevel(CustomerClass customer) {
        
        if(customer.readBalance() >= 10000 && customer.readBalance() < 20000){
            
            Level level = new Gold();

        }
        else if(customer.readBalance() < 10000){
            
            Level level = new Silver();
        }
        
        else if(customer.readBalance() >= 20000){
            
            Level level = new Platinum();
        }
    }

    /**
     * REQUIRES: None
     * MODIFIES: None
     * EFFECTS: returns the string "Silver", the current level
     */
    //to string method to print out level
    @Override
    public String toString() {
        
        return "Silver";
    }
    
    /**
     * REQUIRES: None
     * MODIFIES: None
     * EFFECTS: returns true or false depending on if the repOk holds
     */
    public boolean repOk(){
        if (fee != 20){
            return false;
        }
        else{
            return true;
        }
    }
    
     
     
}