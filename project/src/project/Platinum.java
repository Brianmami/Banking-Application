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
public class Platinum extends Level{
    private final double fee = 0;

    public double getFee() {
        return this.fee;
    }

    
    public void changeLevel(CustomerClass c) {
        if(c.readBalance() >= 10000 && c.readBalance() < 20000){
            Gold l = new Gold();

        }
        else if(c.readBalance() < 10000){
            Level l = new Silver();
        }
        
        else if(c.readBalance() >= 20000){
            Level l = new Platinum();
        }
    }

    @Override
    public String toString() {
        return "Platinum";
    }
    
}
