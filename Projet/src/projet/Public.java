/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

/**
 *
 * @author valfres
 */
public enum Public {
    enfant(3),
    adolescent(8),
    adulte(18);
    
    private int age=18;
    
    private Public(int age){
        this.age=age;
    }

    public int getAge(){
        return age;
    }
}