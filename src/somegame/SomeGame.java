package somegame;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class SomeGame {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
       GameBody game = new GameBody();
       game.generateAlliance();
       game.generateHorde();
       game.battle();
    
}
}
