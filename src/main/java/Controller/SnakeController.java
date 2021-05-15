package Controller;

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


public class SnakeController extends Controller {

    @FXML
    private Label scoreLabel;

    @FXML
    private Label highScoreLabel;

    @FXML
    private Label userLabel;

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

    public String createID(int col, int row) {
        return String.valueOf(row) + "_" + String.valueOf(col);
    }

    public void backToRulePage(MouseEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/rules.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setScoreLabel() {
        scoreLabel.setText(String.valueOf(gameState.getScore()));
    }

}