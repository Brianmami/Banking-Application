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

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;




public class LoginStage{
    
    private final Scene scene;
    
    public LoginStage(Stage primaryStage) {
        
        primaryStage.setTitle("Bank Application");
        
        GridPane loginLayout = new GridPane();
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setHgap(10);
        loginLayout.setVgap(10);
        loginLayout.setPadding(new Insets(20));
        
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");
        
        Label usernameLbl = new Label("Username:");
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        
        Label passwordLbl = new Label("Password:");
        
        TextField roleField = new TextField();
        roleField.setPromptText("Enter Role");
        
        Label roleLbl = new Label("Role:");
        
        Button loginButton = new Button("Login");
        
        Label result = new Label();
        
        loginButton.setOnAction(e -> {
            // Add your authentication logic here
            String user = usernameField.getText();
            String pass = passwordField.getText();
            String role = roleField.getText();
            
            ManagerClass m = new ManagerClass(user, pass, role);
            CustomerClass c = new CustomerClass(user, pass, role);
            
            if(user.isEmpty() || pass.isEmpty()){
                result.setText("Invalid Credentials. Try Again.");
            }else {
                if (m.Authenticater() == true) {
                
                primaryStage.setScene(new ManagerStage(primaryStage, new AccountCreator()).getScene());
            } else if(c.Authenticater() == true){
                primaryStage.setScene(new CustomerStage(primaryStage, new AccountCreator(), c.getUser()).getScene());
            }
            else {
                result.setText("Wrong Login");
                
            }
            }
        });
        
        loginLayout.add(usernameLbl, 0, 0);
        loginLayout.add(usernameField, 1, 0);
        
        loginLayout.add(passwordLbl, 0, 1);
        loginLayout.add(passwordField, 1, 1);
        
        loginLayout.add(roleLbl, 0, 2);
        loginLayout.add(roleField, 1, 2);
        
        loginLayout.add(loginButton, 1, 3);
        
        loginLayout.add(result, 1, 4);
        
        this.scene = new Scene(loginLayout, 500, 200);
        
        

    }
            public Scene getScene() {
                 return scene;
            }
}