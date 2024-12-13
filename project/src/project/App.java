/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author bmami
 */
public class App extends Application {
    
    private Scene loginScene;
    
    @Override
    public void start(Stage primaryStage){
        //title of stage
        primaryStage.setTitle("Bank Login");
        
        LoginStage login = new LoginStage (primaryStage);
        loginScene = login.getScene();
        
        primaryStage.setScene(loginScene);
        primaryStage.show();
        
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
