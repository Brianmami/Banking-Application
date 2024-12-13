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
public abstract class UserAb {
    
    protected String user, pass, role, tier;
    protected double balance;
    
    public UserAb(String user, String pass, String role){
        this.user = user;
        this.pass = pass;
        this.role = role;
    }
    
    public abstract boolean Authenticater();
    
    public String getUser() {
        
        return user;
    }

    public String getPass() {
        
        return pass;
    }

    public String getRole() {
        
        return role;
    }
    
}
