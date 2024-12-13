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
public abstract class Level {
    public abstract double getFee();
    
    public abstract void changeLevel(CustomerClass customer);
    
    public abstract String toString();
}
