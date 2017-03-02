package somegame;

public abstract class Race {
   abstract int spell();
   abstract int melee();
   abstract int distant(); 
   abstract String getSide();
   abstract String speak();
   abstract int attack();
   abstract void updateHP(double damage);
   abstract double getHP();
   abstract boolean isAlive();
   abstract double isUnderBuff();
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
    
    void setCurse(boolean _curse){
        curse=_curse;
    }
    
     @Override
    double isUnderBuff(){
        if(buff==true){return 1.5;}
        else if(curse==true){return 0.5;}
        else return 1;
    }
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
            case("spell"):{return "Elf-mage"+id;}
            case("melee"):{return "Elf-melee"+id;}
            case("distance"):{return "Elf-distance"+id;}
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
        if(HP>0){return true;}
        else return false;
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
            case("spell"):{return "Human-mage"+id;}
            case("melee"):{return "Human-melee"+id;}
            case("distance"):{return "Human-distance"+id;}
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
        if(HP>0){return true;}
        else return false;
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
            case("spell"):{return "Orc-mage"+id;}
            case("melee"):{return "Orc-melee"+id;}
            case("distance"):{return "Orc-distance"+id;}
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
        if(HP>0){return true;}
        else return false;
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
            case("spell"):{return "Undead-mage"+id;}
            case("melee"):{return "Undead-melee"+id;}
            case("distance"):{return "Undead-distance"+id;}
        }
    return "";
}
}

