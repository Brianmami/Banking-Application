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
public class ManagerClass extends UserAb{
    
    public ManagerClass(String user, String pass, String role){ //manager constructor
    
    super(user, pass, role);
    }
    
    @Override
    public boolean Authenticater(){
        if(this.user.equals("manager") && this.pass.equals("manager") && this.role.equals("manager")){
            return true;
            
        } else{
            return false;
        }
    }
    
    public String add(String user, String pass, String role, double balance){
       
        String fileMessage = AccountCreator.createCustomerFile(user, pass, balance);
        return fileMessage;
    }
    
    public String delete(String user){
        
        String fileMessage = AccountCreator.deleteCustomer(user);
        return fileMessage;
    }
    
}
