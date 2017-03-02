package somegame;

public abstract class Race {
   abstract int spell();            //this method returns the valuse of attack
   abstract int melee();            //this method returns the valuse of attack
   abstract int distant();          //this method returns the valuse of attack
   abstract String getSide();       //this method returns the side of characte - Alliance or Horde
   abstract String speak();         //this method returns who is this character
   abstract int attack();           //this method chose which attack would be accourding to the scecialization
   abstract void updateHP(double damage);  
   abstract double getHP();
   abstract boolean isAlive();
   abstract double isUnderBuff();   //check is character under positive buff
   abstract void setCurse(boolean _curse);
   abstract void setBuff(boolean _buff);
   
}

 class Elf extends Race {
     int id;
     String spec;
     String side = "Alliance";
     double HP=100; 
     boolean alive;
     boolean buff;
     boolean curse;

   
    Elf(String _str, int _id){
        spec=_str;
        id=_id;
        alive=true;
        buff=false;
        curse=false;
    };
     @Override
    void updateHP(double damage){
        HP=HP-damage;
    }
    
     @Override
    void setCurse(boolean _curse){
        curse=_curse;
    }
    
     @Override
    double isUnderBuff(){
        if(buff==true){return 1.5;}
        else if(curse==true){return 0.5;}
        else return 1;
    }
     @Override
    void setBuff(boolean _buff){
        buff=_buff;
    }
     @Override
    boolean isAlive(){
         return HP>0;
    }
    
     @Override
    double getHP(){
        return HP;
    }
    
     @Override
    int attack(){
        switch(spec){
            case("spell"):{return spell();}
            case("melee"):{return melee();}
            case("distance"):{return distant();}
        }
        return 0;
    }
     @Override
    public String getSide(){
        return side;
    }
    @Override
    int spell(){
        int choseattack;
        choseattack=0+(int) (Math.random()*2);
        switch(choseattack){
            case(0):{
                return 10; 
            }
            case(1):{
                return 1; 
            }
        }
        return 0;
    };
    @Override
    int melee(){
        return 15;
    };
    @Override
    int distant(){
        int choseattack;
        choseattack=0+(int) (Math.random()*2);
        switch(choseattack){
            case(0):{
                return 3; 
            }
            case(1):{
                return 7; 
            }
        }
        return 0;
    };
    
    @Override
    public String speak(){ 
        switch(spec){
            case("spell"):{return "Elf-mage";}
            case("melee"):{return "Elf-warrior"+(id-3);}
            case("distance"):{return "Elf-archer"+id;}
        }
    return "";
}
}

 class Human extends Race {
    int id;
    String spec;
    String side = "Alliance";
    double HP=100; 
    boolean alive;
    boolean buff;
    boolean curse;

    Human(String _str,int _id){
        spec=_str;
        id=_id;
        alive=true;
        buff=false;
        curse=false;
    };
    
    @Override
    boolean isAlive(){
        return HP>0;
    }
    
    @Override
     void updateHP(double damage){
        HP=HP-damage;
    }
     
       @Override
    double isUnderBuff(){
        if(buff==true){return 1.5;}
        else if(curse==true){return 0.5;}
        else return 1;
    }
    @Override
    void setBuff(boolean _buff){
        buff=_buff;
    }
    
    @Override
     void setCurse(boolean _curse){
        curse=_curse;
    }
     
    @Override
    double getHP(){return HP;}
    @Override
    public String getSide(){return side;
    }
    @Override
     int attack(){
         switch(spec){
            case("spell"):{return spell();}
            case("melee"):{return melee();}
            case("distance"):{return distant();}
        }
        return 0;
     
     }
    @Override
    int spell(){
        int choseattack;
        choseattack=0+(int) (Math.random()*2);
        switch(choseattack){
            case(0):{
                return 4; 
            }
            case(1):{
                return 1; 
            }
        }
        return 0;
    };
    @Override
    int melee(){
        return 18;
    };
    @Override
    int distant(){
        int choseattack;
        choseattack=0+(int) (Math.random()*2);
        switch(choseattack){
            case(0):{
                return 3; 
            }
            case(1):{
                return 5; 
            }
        }
        return 0;
    };
    
      @Override
    public String speak(){ 
        switch(spec){
            case("spell"):{return "Human-mage";}
            case("melee"):{return "Human-warrior"+(id-3);}
            case("distance"):{return "Human-arbalester"+id;}
        }
    return "";
}
 }

class Orc extends Race {
    String spec;
    int id;
    double HP=100; 
    String side = "Horde";
    boolean alive;
    boolean buff;
    boolean curse;
    
    Orc(String _str, int _id){
        id=_id;
        alive=true;
        spec=_str;
        buff=false;
    };
    
    @Override
    boolean isAlive(){
        return HP>0;
    }
    
      @Override
    double isUnderBuff(){
        if(buff==true){return 1.5;}
        else return 1;
    }
    @Override
    void setBuff(boolean _buff){
        buff=_buff;
    }
    
    @Override
    double getHP(){return HP;}
    
    @Override
    void setCurse(boolean _curse){
        curse=_curse;
    }
    
    @Override
      void updateHP(double damage){
        HP=HP-damage;
    }
    
    @Override
    public String getSide(){return side;
    }
    @Override
    int attack(){
         switch(spec){
            case("spell"):{return spell();}
            case("melee"):{return melee();}
            case("distance"):{return distant();}
        }
        return 0;
     
     }
    @Override
    int spell(){
        int choseattack;
        choseattack=0+(int) (Math.random()*2);
        switch(choseattack){
            case(0):{
                return 0; 
            }
            case(1):{
                return 1; 
            }
        }
        return 0;
    };
    @Override
    int melee(){
        return 20;
    };
    @Override
    int distant(){
        int choseattack;
        choseattack=0+(int) (Math.random()*2);
        switch(choseattack){
            case(0):{
                return 2; 
            }
            case(1):{
                return 3; 
            }
        }
        return 0;
    };
    
      @Override
    public String speak(){ 
        switch(spec){
            case("spell"):{return "Orc-shaman";}
            case("melee"):{return "Orc-goblin"+(id-3);}
            case("distance"):{return "Orc-archer"+id;}
        }
    return "";
}
}
class Undead extends Race {
    String spec;
    int id;
    double HP=100; 
    String side = "Horde";
    boolean alive;
    boolean buff;
    boolean curse;

    Undead(String _str, int _id){
        id=_id;
        alive=true;
        spec=_str;
        buff=false;
    };
    @Override
    boolean isAlive(){
        return HP>0;
    }
      @Override
    double isUnderBuff(){
        if(buff==true){return 1.5;}
        else return 1;
    }
    @Override
    void setBuff(boolean _buff){
        buff=_buff;
    }
    
    @Override
    void setCurse(boolean _curse){
        curse=_curse;
    }
    
    @Override
    void updateHP(double damage){
        HP=HP-damage;
    }
    
    @Override
    double getHP(){return HP;}
          
    
    @Override
    public String getSide(){return side;
    }
    @Override
    int attack(){
         switch(spec){
            case("spell"):{return spell();}
            case("melee"):{return melee();}
            case("distance"):{return distant();}
        }
        return 0;
     
     }
    @Override
    int spell(){
        int choseattack;
        choseattack=0+(int) (Math.random()*2);
        switch(choseattack){
            case(0):{

                return 5; 
            }
            case(1):{
                return -1; 
            }
        }
        return 0;
    };
    @Override
    int melee(){
        return 18;
    };
    @Override
    int distant(){
        int choseattack;
        choseattack=0+(int) (Math.random()*2);
        switch(choseattack){
            case(0):{
                return 2; 
            }
            case(1):{
                return 4; 
            }
        }
        return 0;
    };
    
     @Override
    public String speak(){ 
        switch(spec){
            case("spell"):{return "Undead-necromancer";}
            case("melee"):{return "Undead-zombie"+(id-3);}
            case("distance"):{return "Undead-hunter"+id;}
        }
    return "";
}
}

