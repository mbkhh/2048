package Controller;

import Game.AudioPlayer;
import Game.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class Game {
    public Pane rec11,rec21,rec31,rec41,rec12,rec22,rec32,rec42,rec13,rec23,rec33,rec43,rec14,rec24,rec34,rec44;
    public Pane[][] rects = {{rec11 , rec12 , rec13 , rec14 },{rec21 , rec22 , rec23 , rec24},{rec31 , rec32 , rec33 , rec34},{rec41 , rec42 , rec43 , rec44}};
    public Button resetBtn,debugBtn;
    public Pane pane;
    public Slider musicSlider , SFXSlider;
    public Label scoreLbl;
    @FXML
    public void initialize() {
        System.out.println("hey!");
        rects[0][0] = rec11;
        rects[0][1] = rec12;
        rects[0][2] = rec13;
        rects[0][3] = rec14;

        rects[1][0] = rec21;
        rects[1][1] = rec22;
        rects[1][2] = rec23;
        rects[1][3] = rec24;

        rects[2][0] = rec31;
        rects[2][1] = rec32;
        rects[2][2] = rec33;
        rects[2][3] = rec34;

        rects[3][0] = rec41;
        rects[3][1] = rec42;
        rects[3][2] = rec43;
        rects[3][3] = rec44;
        musicSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            //System.out.println( (Double.toString(newValue.intValue())));
            AudioPlayer.audioPlayer.setMusicVolume(newValue.intValue());
        });
        SFXSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            AudioPlayer.audioPlayer.setSFXVolume(newValue.intValue());
        });
    }

    public void debug()
    {
        Main.base.debug();
    }
    public void reset()
    {
        Main.base.reset();
    }
    
}
