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
    }
}