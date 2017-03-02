/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package somegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author rhadas
 */
public class SomeGame {
    
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*Elf elf=new Elf();
        Orc orc=new Orc();
        String[] str={"human","elf"};
        
        List<Race> characterList = new ArrayList<Race>();
        characterList.add(elf);
        
        for (Race race : characterList)
  {
      System.out.println(race.speak());
  }*/
        
       GameBody game = new GameBody();
       game.generateAlliance();
       game.generateHorde();
       game.battle();
    
}
}
