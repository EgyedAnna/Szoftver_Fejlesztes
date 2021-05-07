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
}
