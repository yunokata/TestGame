/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package somegame;

/**
 *
 * @author rhadas
 */
public class Alliance {
    
    int hp=100;
    String race;
    
    Alliance(String _race){
        race=_race;
        String clas;
        String frakcia="alliance";
    
    }
    
    public void getPrint()
    {
         System.out.println(race);
    
    }
    
}
