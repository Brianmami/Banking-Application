/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author bmami
 */

//stage constructor
public class CustomerStage { 
    
    //instance variables
    private Scene scene;
    private String user;
    private CustomerClass customer;
    private AccountCreator file;
    
    public CustomerStage(Stage primaryStage, AccountCreator file, String user){
        
       
        this.file = file;
        this.user = user;
        String pass = file.getPassword(user);
        String role = file.getRole(user);
        Label level = new Label(); //
        this.customer = new CustomerClass(user, pass, role);
        
        primaryStage.setTitle("Hello, Welcome Back");
        
        GridPane customerPane = new GridPane();
        customerPane.setAlignment(Pos.CENTER);
        customerPane.setHgap(10);
        customerPane.setVgap(5);
        customerPane.setPadding(new Insets(10));
        customerPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null))); // Set light blue background

        
        
        VBox logout = new VBox(10);
        logout.setAlignment(Pos.CENTER);
        logout.setPadding(new Insets(20));

        
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button balanceButton = new Button("Balance");
        Button storeButton = new Button("Store");
        Button logoutButton = new Button("Logout");
        
        depositButton.setMinWidth(200);
        withdrawButton.setMinWidth(200);
        balanceButton.setMinWidth(200);
        storeButton.setMinWidth(200);

        logoutButton.setOnAction(e -> {
            
            primaryStage.setScene(new LoginStage(primaryStage).getScene());
            primaryStage.setTitle("Bank Application");
        });
        
        depositButton.setOnAction(e -> {
            
            Stage depStage = new Stage();
            depStage.setTitle("Deposit");
            
            GridPane depositPane = new GridPane();
            depositPane.setAlignment(Pos.CENTER_LEFT);
            depositPane.setHgap(10);
            depositPane.setVgap(5);
            depositPane.setPadding(new Insets(10));
            depositPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null))); // Set light blue background

            
            TextField amountField = new TextField();
            amountField.setPromptText("Deposit Amount");
            
            Label amountLabel = new Label("Amount:");
            
            Label result = new Label();

            Button confButton = new Button("Confirm");
            
            Label balance = new Label("Current Balance: " + customer.readBalance()); // Get balance from the customer object
            
            Label levelLabel = new Label("Level:");
            Label currentLevel = new Label("Current Level: " + customer.getLevel());
            
            confButton.setOnAction(event -> {
                
                try {
                    
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount > 0) {
                        
                        boolean success = customer.deposit(amount);
                        if (success){
                            
                            result.setText("Balance updated");
                            balance.setText("New Balance: $" + customer.readBalance()); // Get balance from the customer object
                            currentLevel.setText("New Level: " + customer.getLevel()); // Update new level
                        }
                        else{
                            
                            result.setText("Cannot deposit");
                            balance.setText("Balance: $" + customer.readBalance());
                            currentLevel.setText("New Level: " + customer.getLevel());
                        }
                        
                    } 
                    else {
                        
                        result.setText("Only positive amounts");
                        balance.setText("Balance: $" + customer.readBalance());
                        currentLevel.setText("New Level: " + customer.getLevel());

                    }
                } 
                catch (NumberFormatException ex) {
                    
                    result.setText("Invalid amount");
                    balance.setText("Balance: $" + customer.readBalance());
                    currentLevel.setText("New Level: " + customer.getLevel());

                }
            
        });
            depositPane.add(amountLabel, 0, 0);
            depositPane.add(amountField, 1, 0);
            depositPane.add(confButton, 1, 4);
            depositPane.add(result, 1, 6);
            depositPane.add(balance, 1, 7);
            depositPane.add(level, 0, 8);

            Scene addCustomerScene = new Scene(depositPane, 400, 300);
            depStage.setScene(addCustomerScene);
            depStage.show();
    });
        
        withdrawButton.setOnAction(e -> {
            
            Stage depStage = new Stage();
            depStage.setTitle("Withdraw Money to Balance");
            
            GridPane depScreen = new GridPane();
            depScreen.setAlignment(Pos.CENTER_LEFT);
            depScreen.setHgap(10);
            depScreen.setVgap(5);
            depScreen.setPadding(new Insets(10));
            depScreen.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null))); // Set light blue background

            
            TextField amountField = new TextField();
            amountField.setPromptText("Enter Amount to Withdraw");
            
            Label amountLabel = new Label("Amount:");
            
            Label result = new Label();

            Button confButton = new Button("Confirm");
            
            Label bal = new Label("Current Balance: " + customer.readBalance()); // Get balance from the customer object
            
            confButton.setOnAction(event -> {
                
                try {
                    
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount > 0) {
                        
                        boolean success = customer.withdraw(amount);
                        if (success){
                            
                            result.setText("Balance successfully updated");
                            bal.setText("New Balance: $" + customer.readBalance()); // Get balance from the customer object
                        }
                        else{
                            
                            result.setText("Error: Unable to deposit amount");
                            bal.setText("Balance: $" + customer.readBalance());
                        }
                        
                    } 
                    else {
                        result.setText("Error: Please enter a positive amount to deposit.");
                        bal.setText("Balance: $" + customer.readBalance());

                    }
                } 
                catch (NumberFormatException ex) {
                    
                    result.setText("Error: Invalid amount entered.");
                    bal.setText("Balance: $" + customer.readBalance());

                }
            
        });
            depScreen.add(amountLabel, 0, 0);
            depScreen.add(amountField, 1, 0);
            depScreen.add(confButton, 1, 4);
            depScreen.add(result, 1, 6);
            depScreen.add(bal, 1, 7);

            Scene addCustomerScene = new Scene(depScreen, 400, 300);
            depStage.setScene(addCustomerScene);
            depStage.show();
            
        });
        
        balanceButton.setOnAction(e -> {
            
            Stage balanceStage = new Stage();
            balanceStage.setTitle("Check Balance");

            GridPane balanceScreen = new GridPane();
            balanceScreen.setAlignment(Pos.CENTER_LEFT);
            balanceScreen.setHgap(10);
            balanceScreen.setVgap(5);
            balanceScreen.setPadding(new Insets(10));
            balanceScreen.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null))); // Set light blue background

            Label balanceLabel = new Label("Current Balance:");
            Label result = new Label("$" + customer.readBalance()); // Get balance from the customer object

            balanceScreen.add(balanceLabel, 0, 0);
            balanceScreen.add(result, 1, 0);

            Scene balanceScene = new Scene(balanceScreen, 300, 150);
            balanceStage.setScene(balanceScene);
            balanceStage.show();
        });
        
        storeButton.setOnAction(e -> {
            
            Stage storeStage = new Stage();
            storeStage.setTitle("Online Store");
            
            GridPane storeScreen = new GridPane();
            storeScreen.setAlignment(Pos.CENTER_LEFT);
            storeScreen.setHgap(10);
            storeScreen.setVgap(5);
            storeScreen.setPadding(new Insets(10));
            storeScreen.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null))); // Set light blue background

            
            Label welcome = new Label("Welcome to the Online Store");
            Label bal = new Label("Your Account Balance Is: $" + customer.readBalance());
            Label item = new Label("Enter Product Name:");
            Label price = new Label("Enter Product Price:");
            //
            Label fee = new Label();
            Label result = new Label();
            
            Button confButton = new Button("Confirm");
            
            level.setText("Level: " + customer.getLevel());
            fee.setText("Fee: $" + Double.toString(customer.getFee()));
            
            TextField itemName = new TextField();
            itemName.setPromptText("Enter Product Name");
            
            TextField itemPrice = new TextField();
            itemPrice.setPromptText("Enter Product Price");
            
            storeScreen.add(welcome, 0, 0);
            storeScreen.add(item, 0, 1);
            storeScreen.add(itemName, 1, 1);
            storeScreen.add(price, 0, 2);
            storeScreen.add(itemPrice, 1, 2);
            storeScreen.add(bal, 0, 3);
            storeScreen.add(result, 0, 4);
            storeScreen.add(level, 0, 5);
            storeScreen.add(fee, 0, 6);
            storeScreen.add(confButton, 1, 3);
            
            confButton.setOnAction(event ->{
                
                try {
                    
                    double amount = Double.parseDouble(itemPrice.getText());
                    if (amount > 0) {
                        
                        boolean success = customer.withdraw(amount + customer.getFee());
                        if (success){
                            
                            result.setText("Item bought");
                            bal.setText("New Balance: $" + customer.readBalance()); // Get balance from the customer object
                            customer.changeLevel();
                            level.setText("Level: " + customer.getLevel());
                            fee.setText("Fee: $" + Double.toString(customer.getFee()));
                        }
                        else{
                            
                            result.setText("Cannot buy");
                            bal.setText("Balance: $" + customer.readBalance());
                        }
                        
                    } 
                    else {
                        
                        result.setText("positive amounts only");
                        bal.setText("Balance: $" + customer.readBalance());

                    }
                } 
                catch (NumberFormatException ex) {
                    
                    result.setText("Wrong entry number");
                    bal.setText("Balance: $" + customer.readBalance());

                }
            
            });

            Scene balanceScene = new Scene(storeScreen, 500, 300);
            storeStage.setScene(balanceScene);
            storeStage.show();
            
        });
        
        customerPane.add(depositButton, 0, 2);
        customerPane.add(withdrawButton, 0, 3);
        customerPane.add(balanceButton, 0, 4);
        customerPane.add(storeButton, 0, 5);
        
        logout.getChildren().addAll(logoutButton);
        
        customerPane.add(logout, 0, 6, 2, 1);

        this.scene = new Scene(customerPane, 500, 200);
    }
    
    public Scene getScene(){
        
        return scene;
    }
    
    
}