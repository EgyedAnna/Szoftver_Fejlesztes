package Model;

import lombok.extern.log4j.Log4j2;

import java.util.Random;
@Log4j2
/**
 * Class representing the game state and the basic operations that is possible to execute within the game state.
 */
public class GameState {
    private int[][] gameState;
    private int snakeLength;
    private String direction;
    private int[] head;
    private int[] tail;
    private int[] foodPlace;
    private int score;
    boolean isOver;
    Random rand = new Random();

    public GameState() {
        this.score = 0;
        this.snakeLength = 3;
        this.direction = "right";
        this.gameState = new int[20][26];
        this.head = new int[2];
        this.tail = new int[2];
        this.foodPlace = new int[2];
        this.isOver = false;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 26; j++) {
                gameState[i][j] = 0;
            }
        }
        generateSnake();
        generateFood();
    }

    /**
     * Returns the game state in a form of a matrix, representing the generated snake and food in it.
     *
     * @return the game state
     */
    public int[][] getGameState() {
        return this.gameState;
    }

    /**
     * Checks whether the game is over.
     *
     * @return {@code true} if the game is over, {@code false} otherwise
     */
    public boolean isOver() {
        return this.isOver;
    }

    /**
     * Generates snake with the length of 3 on a random place.
     */
    public void generateSnake() {
        int randomI = rand.nextInt(17);
        int randomJ = rand.nextInt(20);
        saveHead(randomI, randomJ);
        gameState[randomI][randomJ] = 3;
        gameState[++randomI][randomJ] = 2;
        gameState[++randomI][randomJ] = 1;
    }

    /**
     * Saves the coordinates of the snake's head
     */
    public void saveHead(int headI, int headJ) {
        this.head[0] = headI;
        this.head[1] = headJ;
    }

    /**
     * Shows the game board in the terminal.
     */
    public void showState() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 26; j++) {
                System.out.print("[ " + gameState[i][j] + " ]");
                if (j == 25) {
                    System.out.println();
                }
            }
        }
    }

    /**
     * Generates food on a random place and turn the cell's value to -1.
     * Saves the food's coordinates.
     */
    public void generateFood() {
        boolean success = false;
        Random rand = new Random();
        int foodCellI;
        int foodCellJ;
        while (success == false) {
            foodCellI = rand.nextInt(20);
            foodCellJ = rand.nextInt(26);
            if (gameState[foodCellI][foodCellJ] == 0) {
                gameState[foodCellI][foodCellJ] = -1;
                success = true;
            }
            this.foodPlace[0] = foodCellI;
            this.foodPlace[1] = foodCellJ;
        }
    }

    /**
     * Moves the snake depending on the user's input.
     */
    public void moveSnake(String code) {
        switch (code) {
            case "W":
                this.direction = "up";
                makeStep();
                break;
            case "A":
                this.direction = "left";
                makeStep();
                break;
            case "S":
                this.direction = "down";
                makeStep();
                break;
            case "D":
                this.direction = "right";
                makeStep();
                break;
        }
    }

    /**
     * Checks whether the snake's head and the food in the same cell then increase the length of the snake.
     * Increases score and generate food.
     */
    public void makeStep() {
        if (this.foodPlace[0] == this.head[0] && this.foodPlace[1] == this.head[1]) {
            ++this.snakeLength;
            saveHead(this.foodPlace[0], this.foodPlace[1]);
            putHead();
            if (gameState != null) {
                this.score += 5;
                log.info("Apple eaten: {}  Score: {}",snakeLength-3,score);
                generateFood();
            } else {
                isOver = true;
            }
        } else {
            decreseCells();
            putHead();
            if (gameState == null) {
                isOver = true;
            }
        }

    }

    /**
     * Decreases cell's value where value is not 0 or -1 (food).
     */
    public void decreseCells() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 26; j++) {
                if (gameState[i][j] != 0 && gameState[i][j] != -1) {
                    gameState[i][j]--;
                }
            }
        }
    }

    /**
     * Moves the head in a direction and update the gamestate.
     */
    public void putHead() {
        Step step;
        switch (this.direction) {
            case "up":
                step = new Step(--head[0], head[1], snakeLength);
                gameState = step.applyMove(gameState);
                break;
            case "down":
                step = new Step(++head[0], head[1], snakeLength);
                gameState = step.applyMove(gameState);
                break;
            case "left":
                step = new Step(head[0], --head[1], snakeLength);
                gameState = step.applyMove(gameState);
                break;
            case "right":
                step = new Step(head[0], ++head[1], snakeLength);
                gameState = step.applyMove(gameState);
                break;
        }
    }
    /**
     * Returns the current score of the game.
     * @return the current score.
     */
    public int getScore() {
        return this.score;
    }
}