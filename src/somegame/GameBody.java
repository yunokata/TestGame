package somegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameBody {
    List<Race> characterList = new ArrayList<Race>();
    List<Race> characterListAlliance = new ArrayList<Race>();
    List<Race> characterListHorde = new ArrayList<Race>();
    List<Race> buffedCharacter = new ArrayList<Race>();
    int sumHPAlliance=0;
    int sumHPHorde=0;
    
    String giveSpec(int i){
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
    
    void attack(){
        
        String attacker="",victim="",type;
        int k,b=0;
        double attack_bonus=1;
        int attack;
        double attack_value;
        String RIP="";
        
        if(buffedCharacter.size()>0){
            System.out.print(" I AM BUFFED");
            k=0+(int) (Math.random()*buffedCharacter.size());
            attack_bonus=buffedCharacter.get(k).isUnderBuff();
            attack=buffedCharacter.get(k).attack();
            buffedCharacter.get(k).setBuff(false);
            attacker=buffedCharacter.get(k).speak();
            buffedCharacter.remove(k);  
        }

        else{
            k=0+(int) (Math.random()*characterList.size()); 
            attacker=characterList.get(k).speak();
            attack_bonus=characterList.get(k).isUnderBuff();    
            attack=characterList.get(k).attack();
        }
        
        attack_value=attack*attack_bonus;

        if (characterList.get(k).getSide()=="Alliance"){
            if(attack==1){
                b=0+(int) (Math.random()*characterListAlliance.size());
                characterListAlliance.get(b).setBuff(true);
                buffedCharacter.add(characterListAlliance.get(b));   
                victim=" buff " +characterListAlliance.get(b).speak();
            }
            else{
            b=0+(int) (Math.random()*characterListHorde.size());
            characterListHorde.get(b).updateHP(attack_value);
            sumHPHorde=checkHPHorde();
            victim=" attack " +characterListHorde.get(b).speak()+" by "+attack_value;

            if(characterListHorde.get(b).isAlive()==false){
                RIP=" WARNING "+characterListHorde.get(b).speak()+" died!";
                characterList.remove(b);
                characterListHorde.remove(b);}
            }
            characterList.get(k).setCurse(false);
        }
        else if (characterList.get(k).getSide()=="Horde"){
            
            if(attack==1){
                b=0+(int) (Math.random()*characterListHorde.size());
                characterListHorde.get(b).setBuff(true);
                buffedCharacter.add(characterListHorde.get(b)); 
                victim=" buff " +characterListHorde.get(b).speak();
            }
            else if(attack==-1){
                b=0+(int) (Math.random()*characterListAlliance.size());
                characterListAlliance.get(b).setCurse(true);
                victim=" curse " +characterListAlliance.get(b).speak();
            }
            else if(attack==0){
                buffedCharacter.clear();
                victim=" debaf Alliance ";
            }

            else{b=0+(int) (Math.random()*characterListAlliance.size());
            characterListAlliance.get(b).updateHP(attack_value);
            sumHPAlliance=checkHPAlliance();
            victim=" attack " +characterListAlliance.get(b).speak()+" by "+attack_value;

                if(characterListAlliance.get(b).isAlive()==false){
                    RIP=" WARNING "+characterListAlliance.get(b).speak()+" died!";
                    characterList.remove(b);
                    characterListAlliance.remove(b);
                }
            }
        }
        String rez=attacker+victim+RIP;
        System.out.println(rez);
    }
    
    void battle(){
        int i=1;
        
        do{
            System.out.print(i+") ");
            attack();  
            i++;
        }while ((characterListAlliance.size()>0)&&(characterListHorde.size()>0));
        System.out.println("Alliance"+sumHPAlliance);
        System.out.println("Horde"+sumHPHorde);
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
