package Model;

import lombok.extern.log4j.Log4j2;

@Log4j2
/**
 * Class for checking the possibile steps for the snake and applying the moves.
 */
public class Step {
    private int row;
    private int col;
    private int snakeLength;

    public Step(int row, int col, int snakeLength) {
        this.row = row;
        this.col = col;
        this.snakeLength = snakeLength;
    }

    /**
     * Applies the move to the gamestate.
     * @param gameState current gamestate
     * @return modified gamestate or null
     */
    public int[][] applyMove (int[][] gameState){
        if(canMakeStep(gameState)){
            gameState[row][col]=snakeLength;
            return gameState;
        }
        else{
            return null;
        }
    }

    /**
     * Checks if move is appliable, returns {@code true} if appliable and {@code false} otherwise.
     * @param gameState current gamestate
     * @return whether move is appliable
     */
    public boolean canMakeStep(int[][] gameState){
        boolean canApplyMove=false;
        canApplyMove=checkIfCellNotOver();
        if(canApplyMove){
            canApplyMove=checkIfNotSelf(gameState);
        }
        return canApplyMove;
    }

    /**
     * Returns {@code true} if the cell is empty and has no food in it and returns {@code false} othetwise.
     * @param gameState current gamestate
     * @return if self or not
     */
    public boolean checkIfNotSelf(int[][] gameState){
        if(gameState[row][col]==0 || gameState[row][col]==-1){
            return true;
        }
        log.info("Game Over. Snake collapsed.");
        return false;
    }

    /**
     * Checks {@code true}if cell is over and {@code false} otherwise.
     * @return if cells are over or not
     */
    public boolean checkIfCellNotOver(){
        if(row>=0 && row<20 && col >=0 && col<26 ){
            return true;
        }
        log.info("Game Over.Cells over.");
        return false;
    }

}
