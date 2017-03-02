package somegame;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameBody {
    List<Race> characterList = new ArrayList<>();           //list for all characters 
    List<Race> characterListAlliance = new ArrayList<>();   //list for elf/human characters
    List<Race> characterListHorde = new ArrayList<>();      //list for orc/undead characters
    List<Race> buffedCharacter = new ArrayList<>();         //list for character under buff
    int sumHPAlliance=0;                //total alliance side life
    int sumHPHorde=0;                   //total horde side life
    
    String giveSpec(int i){             // give randome specialization to character
        if(i==0) {return "spell"; }
        if((i>0)&&(i<4)){return "distance";}
        else {return "melee";}
        
    }
    
    int checkHPAlliance(){
        int sum=0,i=0;
        for (Race race : characterListAlliance){
            sum+=race.getHP();
        }
        return sum;
    }
    
    int checkHPHorde(){
        int sum=0,i=0;
        for (Race race : characterListHorde){
            sum+=race.getHP();
        }
        return sum;
    }
    
    String attack(){
        
        String attacker="",victim="",buffed="";
        int attacker_index,victim_index=0;
        double attack_bonus=1;
        int attack;
        double attack_value;
        String RIP="";
        
        if(buffedCharacter.size()>0){                                       //check if character in buff group
            buffed="BUFFED ";
            attacker_index=characterList.indexOf(buffedCharacter.get(0));   //get the main index in CharacterList
            attack_bonus=buffedCharacter.get(0).isUnderBuff();              //check if there any bonus  +1.5% or curse -0.5% to attack
            attack=buffedCharacter.get(0).attack();                         //get the value of character attack
            buffedCharacter.get(0).setBuff(false);                          // as buffed character turn is over he lost his buff
            attacker=buffedCharacter.get(0).speak();                        //write who attacked
            buffedCharacter.remove(0);                                      //remove this character from buff List
        }

        else{                                                               // chose random character as attacker
            attacker_index=0+(int) (Math.random()*characterList.size());    //get the main index in CharacterList
            attacker=characterList.get(attacker_index).speak();             //write who attacked
            attack_bonus=characterList.get(attacker_index).isUnderBuff();   //check if there any bonus  +1.5% or curse -0.5% to attack
            attack=characterList.get(attacker_index).attack();              //get the value of character attack
        }
        
        

        if ("Alliance".equals(characterList.get(attacker_index).getSide())){        //if attacker is from elf or human side
            if(attack==1){                                                          // 1 means that this is not attack, this is buff
                victim_index=0+(int) (Math.random()*characterListAlliance.size());  //from friendly side chose 1 random character
                characterListAlliance.get(victim_index).setBuff(true);              //set buff to this character
                buffedCharacter.add(characterListAlliance.get(victim_index));       //this character add to the buff list
                victim=" buff " +characterListAlliance.get(victim_index).speak();   //write whom did we buff
            }
            else{                                                                   //if we get another value that it is attack
            attack_value=attack*attack_bonus;                                       //according to the valuse of attack and bonus_attack we have our damage value
            victim_index=0+(int) (Math.random()*characterListHorde.size());         //from enemy side chose 1 random character
            characterListHorde.get(victim_index).updateHP(attack_value);            //attack this character by attack_value and update HP
            sumHPHorde=checkHPHorde();
            victim=" attack " +characterListHorde.get(victim_index).speak()+" by "+attack_value;    //write whom did we attack and value of damage

            if(characterListHorde.get(victim_index).isAlive()==false){              //check if victim is alive
                RIP=" WARNING "+characterListHorde.get(victim_index).speak()+" died!"; //write that this character died
                characterList.remove(victim_index);                                 //remove victim character from character list
                characterListHorde.remove(victim_index);}                           //remove victim from enemy character list
            }   
            characterList.get(attacker_index).setCurse(false);                      //in case if alliance character was cursed in the end of his turn he is free of curse
        }
        else if ("Horde".equals(characterList.get(attacker_index).getSide())){      //the same thing for another side
            
            switch (attack) {
                case 1:
                    victim_index=0+(int) (Math.random()*characterListHorde.size());
                    characterListHorde.get(victim_index).setBuff(true);
                    buffedCharacter.add(characterListHorde.get(victim_index));
                    victim=" buff " +characterListHorde.get(victim_index).speak();
                    break;
                case -1:
                    victim_index=0+(int) (Math.random()*characterListAlliance.size());
                    characterListAlliance.get(victim_index).setCurse(true);
                    victim=" curse " +characterListAlliance.get(victim_index).speak();
                    break;
                case 0:
                    buffedCharacter.clear();
                    victim=" debaf Alliance ";
                    break;
                default:
                    attack_value=attack*attack_bonus;                                   //according to the valuse of attack and bonus_attack we have our damage value
                    victim_index=0+(int) (Math.random()*characterListAlliance.size());
                    characterListAlliance.get(victim_index).updateHP(attack_value);
                    sumHPAlliance=checkHPAlliance();
                    victim=" attack " +characterListAlliance.get(victim_index).speak()+" by "+attack_value;
                    if(characterListAlliance.get(victim_index).isAlive()==false){
                        RIP=" WARNING "+characterListAlliance.get(victim_index).speak()+" died!";
                        characterList.remove(victim_index);
                        characterListAlliance.remove(victim_index);
                    }   
                    break;
            }
        }
        String rez=buffed+attacker+victim+RIP;      //form the final line that would be write to the LOG
        return rez;
    }
    
    void battle() throws FileNotFoundException, UnsupportedEncodingException{
        int i=1;
        String toOutWrite="";
        PrintWriter out = new PrintWriter  (new OutputStreamWriter  (new FileOutputStream ("D:/SomeGame/LOG.txt"), "windows-1251")); //path to the LOG file
        out.println("**Game START**");
        do{
            toOutWrite=i+") "+attack(); //number of turn
            out.println(toOutWrite);  
            i++;
        }while ((characterListAlliance.size()>0)&&(characterListHorde.size()>0)); // we do our battle until more that 1 character of both sides are alive
        out.println("**TOTAL SCORE**");  
        out.println("Alliance: "+sumHPAlliance+" Horde: "+sumHPHorde);
        if (sumHPAlliance>0){out.println("Alliance WIN!"); } 
        else {out.println("Horde WIN!");}
        
        out.close();
    }
    
     void generateAlliance(){
        String[] race ={"Human","Elf"};
        Random r = new Random();
        int iRand = r.nextInt(race.length);
        String sRand = race[iRand];
        
        switch(sRand){
            case("Human"):{
                Human[] allianceRace = new Human[8];
                for (int i=0;i<8;i++){
                    String str=giveSpec(i);
                    allianceRace[i]=new Human(str,i);
                    characterList.add(allianceRace[i]);
                    characterListAlliance.add(allianceRace[i]);     
                }
            break;
            }
            case("Elf"):{
                Elf[] allianceRace = new Elf[8];
                for (int i=0;i<8;i++){
                    String str=giveSpec(i);
                    allianceRace[i]=new Elf(str,i);
                    characterList.add(allianceRace[i]);
                    characterListAlliance.add(allianceRace[i]);
                }
            break;
            }
        }            
    }

     void generateHorde(){
        String[] race ={"Orc","Undead"};
        Random r = new Random();
        int iRand = r.nextInt(race.length);
        String sRand = race[iRand];
        
        switch(sRand){
            case("Orc"):{
                Orc[] hordeRace = new Orc[8];
                for (int i=0;i<8;i++){
                    String str=giveSpec(i);
                    hordeRace[i]=new Orc(str,i);
                    characterList.add(hordeRace[i]);
                    characterListHorde.add(hordeRace[i]);     
                }
            break;
            }
            case("Undead"):{
                Undead[] hordeRace = new Undead[8];
                for (int i=0;i<8;i++){
                    String str=giveSpec(i);
                    hordeRace[i]=new Undead(str,i);
                    characterList.add(hordeRace[i]);
                    characterListHorde.add(hordeRace[i]);
                }
            break;
            }
        }     
    }
}
