package Model;

import java.util.Random;
import java.util.Scanner;

public class GameState {
    private int[][] gameState;
    private int snakeLength;
    private String direction;
    private int[] head;
    private int[] tail;
    private int[] foodPlace;
    private Scanner scanner = new Scanner(System.in);
    private int score;
    private int highScore;
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
    public void setGameState(int[][] gameState) {
        this.gameState = gameState;
    }

    public int[][] getGameState() {
        return this.gameState;
    }

    public boolean isOver() {
        return this.isOver;
    }

    public void generateSnake() {
        int randomI = rand.nextInt(17);
        int randomJ = rand.nextInt(20);
        saveHead(randomI, randomJ);
        gameState[randomI][randomJ] = 3;
        gameState[++randomI][randomJ] = 2;
        gameState[++randomI][randomJ] = 1;
    }

    public void saveHead(int headI, int headJ) {
        this.head[0] = headI;
        this.head[1] = headJ;
    }

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
}