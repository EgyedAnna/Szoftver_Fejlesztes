package Model;

public class Step {
    private int row;
    private int col;
    private int snakeLength;

    public Step(int row, int col, int snakeLength) {
        this.row = row;
        this.col = col;
        this.snakeLength = snakeLength;
    }
    public int[][] applyMove (int[][] gameState){
        if(canMakeStep(gameState)){
            gameState[row][col]=snakeLength;
            return gameState;
        }
        else{
            return null;
        }
    }
    
    public boolean canMakeStep(int[][] gameState){
        boolean canApplyMove=false;
        canApplyMove=checkIfCellNotOver();
        if(canApplyMove){
            canApplyMove=checkIfNotSelf(gameState);
        }
        return canApplyMove;
    }

    public boolean checkIfNotSelf(int[][] gameState){
        if(gameState[row][col]==0 || gameState[row][col]==-1){
            return true;
        }
        return false;
    }

    public boolean checkIfCellNotOver(){
        if(row>=0 && row<20 && col >=0 && col<26 ){
            return true;
        }
        return false;
    }

}
