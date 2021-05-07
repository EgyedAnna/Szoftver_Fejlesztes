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
