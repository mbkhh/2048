import Controller.Game;
import Game.AudioPlayer;
import Game.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxmls/game.fxml"));
        
        AudioPlayer.audioPlayer = new AudioPlayer();
        AudioPlayer.audioPlayer.playBack();
        
        Parent root = loader.load();
        Game controller = loader.getController();
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("2048");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/icon/favicon.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
        Main.base = new Main();
        Main.base.game = controller;
        Main.base.scene = scene;
        Main.base.start();
    }
}