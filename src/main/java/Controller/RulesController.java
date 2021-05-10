package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;

public class RulesController extends Controller {

    @FXML
    private Label rulesLabel;

    @FXML
    private TextField nameField;

    @FXML
    private ImageView iconimage;

    private String rulesText = "This game is about a snake which has a liking in\n" +
            "  apples. Your goal is to feed the snake with as\n" +
            "  many apple as possible. Everytime your snake\n" +
            "  eats an apple its stomach gets bigger and\n" +
            "  bigger. Be careful because a long snake is\n" +
            "  harder to control than a smaller snake. You\n" +
            "  must avoid the snake to crash into its own body\n" +
            "  because then your snake gets tangled,\n" +
            "  consequently you can not continue to eat apples\n" +
            "  and the game is over. You should avoid crashing\n" +
            "  into the walls as well because then your snake\n" +
            "  is hurt and your game is over. A snake with a\n" +
            "  big belly is happier than a snake with a small\n" +
            "  belly. Try to be the one who gets its snake\n" +
            "  the longest and happiest.";


    @FXML
    private void initialize() {
        iconimage.setImage(new Image(RulesController.class.getResource("/images/iconsnake.png").toExternalForm()));
        rulesLabel.setText(rulesText);
    }

    public static void loadGame(MouseEvent mouseEvent, String name) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RulesController.class.getResource("/fxml/snake.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<Controller>getController().initdata(name);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}