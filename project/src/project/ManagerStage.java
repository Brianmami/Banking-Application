/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
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
public class ManagerStage {
   
    //instance variables
    private Scene scene;
    private AccountCreator file;
    private String AccountUsername; 
    
    public static final String MANAGER_USER = "manager";
    public static final String MANAGER_PASS = "manager";
    public static final String MANAGER_ROLE = "manager";
    
    public ManagerStage(Stage primaryStage, AccountCreator file){
        
        //new instance of manager with username, pass, and role
        ManagerClass manager = new ManagerClass(MANAGER_USER, MANAGER_PASS, MANAGER_ROLE);
        
        primaryStage.setTitle("Welcome back, Manager");
        
        GridPane managerPane = new GridPane();
        managerPane.setAlignment(Pos.CENTER);
        managerPane.setHgap(10);
        managerPane.setVgap(5);
        managerPane.setPadding(new Insets(10));
        
        VBox logout = new VBox(10);
        logout.setAlignment(Pos.CENTER);
        logout.setPadding(new Insets(20));

        Button addCustomerButton = new Button("Add Customer");
        Button deleteCustomerButton = new Button("Delete Customer");
        Button logoutButton = new Button("Logout");
        
        addCustomerButton.setMinWidth(100);
        deleteCustomerButton.setMinWidth(100);

        logoutButton.setOnAction(e -> {
            primaryStage.setScene(new LoginStage(primaryStage).getScene());
            primaryStage.setTitle("Bank Application");
        });
        
        addCustomerButton.setOnAction(e -> {
            Stage addStage = new Stage();
            addStage.setTitle("Add Customer");
            
            GridPane addScreen = new GridPane();
            addScreen.setAlignment(Pos.CENTER);
            addScreen.setHgap(10);
            addScreen.setVgap(5);
            addScreen.setPadding(new Insets(10));
            
            TextField usernameField = new TextField();
            usernameField.setPromptText("Enter Customer Username");

            TextField passwordField = new TextField();
            passwordField.setPromptText("Enter Customer Password");

            TextField balanceField = new TextField();
            balanceField.setPromptText("Enter Customer Balance");

            TextField roleField = new TextField();
            roleField.setPromptText("Enter Customer Role");
            
            AccountUsername = usernameField.getText();
            
            Label usernameLabel = new Label("Username:");
            Label passwordLabel = new Label("Password:");
            Label balanceLabel = new Label("Balance:");
            Label roleLabel = new Label("Role:");
            
            Label result = new Label();

            Button addButton = new Button("Confirm");
            
            addButton.setOnAction(event -> {
                
                if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || roleField.getText().isEmpty() || balanceField.getText().isEmpty()){
                    result.setText("Necessary information is missing.\nPlease try again.");
                }
                
                else if(usernameField.getText().equals("admin")){
                    result.setText("Customer username cannot be admin.\nPlease try again.");
                }
                
                else if(Double.parseDouble(balanceField.getText()) < 100){
                    result.setText("Bank account balance must be a minimum of $100.");
                }
                else{
                    String username = usernameField.getText();
                    String password = passwordField.getText();
                    double balance = Double.parseDouble(balanceField.getText());
                    String role = roleField.getText();

                    // Call method from AccountFile to add customer
                    String message = manager.add(username, password, role, balance);
                    result.setText(message);
                    
                    usernameField.clear();
                    passwordField.clear();
                    balanceField.clear();
                    roleField.clear();
                }
            });
            
            addScreen.add(usernameLabel, 0, 0);
            addScreen.add(usernameField, 1, 0);
            addScreen.add(passwordLabel, 0, 1);
            addScreen.add(passwordField, 1, 1);
            addScreen.add(balanceLabel, 0, 2);
            addScreen.add(balanceField, 1, 2);
            addScreen.add(roleLabel, 0, 3);
            addScreen.add(roleField, 1, 3);
            addScreen.add(addButton, 1, 4);
            addScreen.add(result, 1, 5);

            Scene addCustomerScene = new Scene(addScreen, 400, 300);
            addStage.setScene(addCustomerScene);
            addStage.show();
        });
        
            deleteCustomerButton.setOnAction(e -> {
            Stage addStage = new Stage();
            addStage.setTitle("Delete Customer");
            
            GridPane delScreen = new GridPane();
            delScreen.setAlignment(Pos.CENTER);
            delScreen.setHgap(10);
            delScreen.setVgap(5);
            delScreen.setPadding(new Insets(10));
            
            TextField usernameField = new TextField();
            usernameField.setPromptText("Enter Customer Username");
            
            AccountUsername = usernameField.getText();
            
            Label usernameLabel = new Label("Username:");
            
            Label result = new Label();

            Button deleteButton = new Button("Confirm");
            
            deleteButton.setOnAction(event -> {
                String username = usernameField.getText();

                // Call method from AccountFile to add customer
                String message = manager.delete(username);
                result.setText(message);
                
                usernameField.clear();
            });
            
            delScreen.add(usernameLabel, 0, 0);
            delScreen.add(usernameField, 1, 0);
            delScreen.add(deleteButton, 1, 4);
            delScreen.add(result, 1, 6);

            Scene addCustomerScene = new Scene(delScreen, 400, 300);
            addStage.setScene(addCustomerScene);
            addStage.show();
        });
        
        managerPane.add(addCustomerButton, 0, 2);
        managerPane.add(deleteCustomerButton, 1, 2);
        
        logout.getChildren().addAll(logoutButton);
        
        managerPane.add(logout, 0, 3, 2, 1);

        this.scene = new Scene(managerPane, 500, 200);
    }
    
     public Scene getScene(){
        return scene;
    }
    
    public String getEnteredUsername() {
        return AccountUsername;
    }
}
