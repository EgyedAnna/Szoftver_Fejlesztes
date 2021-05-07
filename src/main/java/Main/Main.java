package Main;

import Model.GameState;
import org.hibernate.internal.build.AllowSysOut;

public class Main {
    public static void main(String[] args){
        GameState gameState = new GameState();
        gameState.showState();

    }

}
