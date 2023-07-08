package Game;

import java.util.Optional;

import Controller.Game;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Main {
    public Game game;
    public Scene scene;
    public static Main base;
    BlockGreid blockGreid;
    public int score;
    public void debug()
    {
        blockGreid.debugMessage();
    }
    public void start()
    {
        score = 0;
        blockGreid = new BlockGreid();
        blockGreid.addNewBlock();
        blockGreid.addNewBlock();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {              
                if(event.getCode() == KeyCode.W )
                    blockGreid.up();
                else if (event.getCode() == KeyCode.D)
                    blockGreid.right();
                else if (event.getCode() == KeyCode.A)
                    blockGreid.left();
                else if (event.getCode() == KeyCode.S)
                    blockGreid.down();
                updateScore();
                if(blockGreid.checkEnd())
                {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Loss");
                    alert.setHeaderText("You lost My friend");
                    alert.setContentText("You achived a score of "+score+".\nDo you wish to restart?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get() == null) {
                       Platform.exit();
                    } else if (option.get() == ButtonType.OK) {
                       reset();
                    } else if (option.get() == ButtonType.CANCEL) {
                        Platform.exit();
                    } else {
                        Platform.exit();
                    }
                }
                
            }
        });
    }
    public void updateScore()
    {
        game.scoreLbl.setText("Score: " + score);
    }
    public void reset()
    {
        score = 0;
        updateScore();
        blockGreid.reset();
        blockGreid = new BlockGreid();
        blockGreid.addNewBlock();
        blockGreid.addNewBlock();
    }
}
