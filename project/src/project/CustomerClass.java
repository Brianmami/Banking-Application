/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 *
 * @author bmami
 */


public class CustomerClass extends UserAb {
    
    //instance variable
    private String localUser = this.getUser();
    private Level level;
    private double balance;
    
    //constructor for customers
    public CustomerClass(String username, String password, String role) {
        
        //parent constructor called
        super(username, password, role);
    }
    
    @Override
    public boolean Authenticater() {
        
        //create file for customers and check if it exists or not
        File file = new File("Customer Accounts/" + localUser + ".txt");
        
        if(!file.exists()){
            
            System.out.println("This customer does not exist");
            return false;
        }
        
        else{
            
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                
                String line;
                
                while ((line = reader.readLine()) != null) {
                    
                    // if the line starts with the password key then take the password that comes after
                    if (line.startsWith("Password:")) {
                        
                        String storedPassword = line.substring("Password:".length()).trim();
                       
                        if (getPass().equals(storedPassword)) {
                            
                            return true; 
                        }
                    }
                }
            } 
            catch (IOException e) {
                
                System.out.println("Error retrieving customer information: " + e.getMessage());
            }
        }

        
        return false;
    }

    //reading balance
    public double readBalance() {
        
        File file = new File("Customer Accounts/" + localUser + ".txt");
        double bal = 0;
        
        try(BufferedReader read = new BufferedReader(new FileReader(file))){
            
           String line;
           
           //same check with password
           while ((line = read.readLine()) != null){
               
               if(line.startsWith("Balance:")){
                   
                   bal += Double.parseDouble(line.substring("Balance:".length()).trim());
                   return bal;
                   
               }
           }
        }
        catch(IOException e){
            
            System.out.println("Error retrieving customer information: " + e.getMessage());
        }
        return 0;
    }
     
    //deposit method
     public boolean deposit(double amount) {
         
        File file = new File("Customer Accounts/"+ localUser + ".txt");
        double oldBal = 0;
        double newBal;
        
        //same check as before
        if(!file.exists()){
            
            System.out.println("Error: Account file not found for user " + localUser);
        }
        
        try(BufferedReader read = new BufferedReader(new FileReader(file))){
            
           String line;
           StringBuilder content = new StringBuilder();
           
           while ((line = read.readLine()) != null){
               
               if(line.startsWith("Balance: ")){
                   
                   oldBal = Double.parseDouble(line.substring("Balance: ".length()).trim());
               }
               content.append(line).append("\n");
           }
           
           newBal = oldBal + amount;
           changeLevel();
           String update = content.toString().replaceAll("Balance: " + oldBal, "Balance: " + newBal);
           
           try(PrintWriter writer = new PrintWriter(new FileWriter(file))){
               
               writer.print(update);
               return true;
           }
        }
        
        catch(IOException e){
            
            System.out.println("Error retrieving customer information: " + e.getMessage());
            return false;
        }
    }
     
    // checking which level to use
    public Level getLevel() {
        double balance = readBalance();
        Level level;
        if (balance >= 20000) {
            level = new Platinum();
        } 
        else if (balance >= 10000) {
            level = new Gold();
        } 
        else {
            level = new Silver();
        }
        return level;
    }
    
    //fee getter
    public double getFee(){
        Level customerLevel = getLevel();
        return customerLevel.getFee();
    }

    //method to change levels
    public void changeLevel(){
        Level newLevel = getLevel();
        Level currentLevel = level;
        
        level = newLevel;
        
        // updating the string in the file for the new level
        File file = new File("Customer Accounts/" + localUser + ".txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    
                    if (line.startsWith("Level:")) {
                        
                        content.append("Level: ").append(newLevel).append("\n");
                    } 
                    else {
                        
                        content.append(line).append("\n");
                    }
                }
                // Write the updated content back to the file
                try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                    
                    writer.print(content);
                }
            } 
            catch (IOException e) {
                
                System.out.println("Error updating customer level in file: " + e.getMessage());
            }
        } 
        else {
            
            System.out.println("Error: Customer file not found.");
        }
    
    }
    
    // Method to withdraw money 
    public boolean withdraw(double amount) {
        
        File file = new File("Customer Accounts/"+ localUser + ".txt");
        double oldBal = 0;
        double newBal;
        
        if(!file.exists()){
            
            System.out.println("Error: Account file not found for user " + localUser);
        }
        
        try(BufferedReader read = new BufferedReader(new FileReader(file))){
            
           String line;
           StringBuilder content = new StringBuilder();
           
           while ((line = read.readLine()) != null){
               
               if(line.startsWith("Balance: ")){
                   
                   oldBal = Double.parseDouble(line.substring("Balance: ".length()).trim());
               }
               content.append(line).append("\n");
           }
           
           newBal = oldBal - amount;
           if(newBal < 0){
               
               System.out.println("Cannot withdraw this amount as your balance becomes negative");
               return false;
           }
           changeLevel();
           String update = content.toString().replaceAll("Balance: " + oldBal, "Balance: " + newBal);
           
           try(PrintWriter writer = new PrintWriter(new FileWriter(file))){
               
               writer.print(update);
               return true;
           }
        }
        
        catch(IOException e){
            System.out.println("Error retrieving customer information: " + e.getMessage());
            return false;
        }
    }
}
