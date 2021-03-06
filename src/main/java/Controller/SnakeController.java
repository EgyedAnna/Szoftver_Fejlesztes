package Controller;

import Dao.HighScoreDao;
import Dao.Score;
import Model.GameState;
import Model.Direction;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SnakeController extends Controller {

    @FXML
    private Label scoreLabel;

    @FXML
    private Pane grid;

    @FXML
    private Label gameOver;

    private GridPane gridPane;
    private GameState gameState;
    private Direction direction;
    private Timeline timeline;

    @FXML
    public void initialize() {

        generateGridPane();

    }

    public void startGame() throws InterruptedException {
        clearSnakeSpeed();
        this.gameState = new GameState();
        gameOver.setVisible(false);
        renderSnake();
        SnakeMoveManager();
        direction = new Direction("D", "A");
        log.info("Start button is clicked.");
    }

    private void SnakeMoveManager() {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(130), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (gameState.isOver() == false) {
                    gameState.moveSnake(direction.getDirection());
                    renderSnake();
                }
            }
        }));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
    }

    private void clearSnakeSpeed() {
        if (this.timeline != null) {
            this.timeline.stop();
            this.timeline.getKeyFrames().clear();
            this.timeline = null;
        }
    }

    private void generateGridPane() {
        gridPane = new GridPane();
        gridPane.setPrefWidth(700);
        gridPane.setPrefHeight(500);
        gridPane.setGridLinesVisible(true);
        gridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THICK)));
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 26; j++) {
                Label label = new Label();
                label.setId(createID(i, j));
                label.setPrefHeight(500 / 20.0);
                label.setPrefWidth(700 / 26.0);
                gridPane.addRow(i, label);
            }
        }
        grid.getChildren().add(gridPane);
    }

    public String createID(int col, int row) {
        return String.valueOf(row) + "_" + String.valueOf(col);
    }

    private void renderSnake() {
        setScoreLabel();
        Node label;
        String id;
        int[][] state = gameState.getGameState();
        clearCells();
        try {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 26; j++) {
                    if (state[i][j] != 0) {
                        id = createID(i, j);
                        String finalId = id;
                        label = (Label) gridPane.getChildren().stream()
                                .filter(x -> x.getId() != null)
                                .filter(x -> x.getId().equals(finalId))
                                .findFirst()
                                .get();
                        if (state[i][j] != -1) label.setStyle("-fx-background-color: black;");
                        else label.setStyle("-fx-background-color: red;");
                    }
                }
            }
        } catch (Exception e) {
            gameOver.setVisible(true);
            Score newScore = new Score(name1, String.valueOf(gameState.getScore()));
            HighScoreDao highScoreDao = new HighScoreDao();
            highScoreDao.addScore(newScore);
            log.info("Highscore saved. {}",newScore);
        }
    }

    public void snakeMove(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        String opposite = null;
        String dir = code.toString();
        if (direction.getOpposite() != dir) {
            switch (dir) {
                case "W":
                    opposite = "S";
                    break;
                case "A":
                    opposite = "D";
                    break;
                case "S":
                    opposite = "W";
                    break;
                case "D":
                    opposite = "A";
                    break;
            }
            direction = new Direction(dir, opposite);
        }
    }

    public void clearCells() {
        for (Node label : gridPane.getChildren()) {
            label.setStyle("");
        }
    }

    public void backToRulePage(MouseEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/rules.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        log.info("Back button is clicked.");
    }

    public void setScoreLabel() {
        scoreLabel.setText(String.valueOf(gameState.getScore()));
    }

}
