/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author bmami
 */
class AccountCreator {

    //setting final variables for directors and name of directory
    private static final String CUSTOMER_ACCOUNTS_DIR = "Customer Accounts/";
    private static File directory = new File(CUSTOMER_ACCOUNTS_DIR);
    
    public static String createCustomerFile(String username, String password, double balance){
        
        if(directory.exists() == false){
            directory.mkdirs();
        }
   
        File file = new File(CUSTOMER_ACCOUNTS_DIR + username + ".txt");
        
        if(file.exists()){
            return "Error: A customer with username '" + username + "' already exists.";
        }
                
        try(PrintWriter writer = new PrintWriter(new FileWriter(file))){
            writer.println("Username: " + username);
            writer.println("Password: " + password);
            writer.println("Role: customer");
            writer.println("Balance: " + balance);
            if(balance < 10000){
                writer.println("Level: Silver");
            }
            else if (balance >= 10000 && balance < 20000){
                writer.println("Level: Gold");
            }
            else if(balance >= 20000){
                writer.println("Level: Platinum");
            }
            return "Customer file created for User: " + username;
        }
        catch(IOException e){
            return "Error creating customer file: " + e.getMessage();
        }
    }
    
    public static String deleteCustomer(String username){
        File file = new File(CUSTOMER_ACCOUNTS_DIR + username + ".txt");
        if(file.exists()){
            if(file.delete()){
                return "Customer file successfuly deleted for User: " + username;
            }
            else{
                return "Failed to delete customer file for User: " + username;
            }
        }
        else{
            return "Customer file does not exist for User: " + username;
        }
    }
    
    public static String getUsername(String username) {
        File file = new File(CUSTOMER_ACCOUNTS_DIR + username + ".txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Username:")) {
                        return line.substring("Username:".length()).trim();
                    }
                }
            } catch (IOException e) {
                System.out.println("Error retrieving username: " + e.getMessage());
            }
        }
        return null; // Return null if the username is not found
    }

    public static String getPassword(String username) {
        File file = new File(CUSTOMER_ACCOUNTS_DIR + username + ".txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Password:")) {
                        return line.substring("Password:".length()).trim();
                    }
                }
            } catch (IOException e) {
                System.out.println("Error retrieving password: " + e.getMessage());
            }
        }
        return null; 
    }

    public static String getRole(String username) {
        File file = new File(CUSTOMER_ACCOUNTS_DIR + username + ".txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Role:")) {
                        return line.substring("Role:".length()).trim();
                    }
                }
            } catch (IOException e) {
                System.out.println("Error retrieving Role: " + e.getMessage());
            }
        }
        return null; 
    }

    public static double getBalance(String username) {
        File file = new File(CUSTOMER_ACCOUNTS_DIR + username + ".txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Balance:")) {
                        String balanceString = line.substring("Balance:".length()).trim();
                        return Double.parseDouble(balanceString);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error retrieving username: " + e.getMessage());
            }
        }
        return 0; 
    }
    
}
