package Game;
import AbstractClasses.*;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
public class Block extends AbstractBlock{
    int number;
    public String id;
    public Pane rect;
    public int getNumber()
    {
        return number;
    }
    public Block(GridPoint gridPoint, int number, Pane rec, String ID)
    {
        id = ID;
        point = new GridPoint(gridPoint.x(), gridPoint.y());
        this.number = number;
        rect = new Pane();
        rect.setStyle("-fx-background-color:"+Colorpicker.color[(int)(Math.log(number)/Math.log(2)) - 1]+";-fx-background-radius:15;");
        rect.setPrefHeight(130);
        rect.setPrefWidth(130);
        rect.setLayoutX(rec.getLayoutX());
        rect.setLayoutY(rec.getLayoutY());
        rect.setId(id);
        Label t = new Label(Integer.toString(number));
        if((int)Math.log10(number) == 0)
            t.setPadding(new Insets(30,0,0,50));
        else if ((int)Math.log10(number) == 1)
            t.setPadding(new Insets(30,0,0,38));
        else if ((int)Math.log10(number) == 2)
            t.setPadding(new Insets(30,0,0,26));
        else if ((int)Math.log10(number) == 3)
            t.setPadding(new Insets(30,0,0,13));
        else
            t.setPadding(new Insets(30,0,0,2));    
        t.setStyle("-fx-font-size:45;-fx-font-weight:Bold;-fx-text-fill:#ffffff;");
        rect.getChildren().add(t);
    }
    public void changePoint(GridPoint point)
    {
        this.point = point;
    }
    public GridPoint move(GridPoint from, Direction direction)
    {
        if(direction == Direction.UP)
            return from.up();
        return new GridPoint(2,2);
    } // it is better to use recursion and then return the final// GridPoint it ends up in
                                                                 
    public void animateMovement(GridPoint origin)
    {
        TranslateTransition  translate = new TranslateTransition();  
        //System.out.println(dest.getLayoutX() +"-"+ this.rect.getLayoutX()+" = "+(dest.getLayoutX() - this.rect.getLayoutX()));
        //System.out.println(dest.getLayoutY() +"-"+ this.rect.getLayoutY()+" = "+(dest.getLayoutY() - this.rect.getLayoutY()));
        // translate.setByX(dest.getLayoutX() - this.rect.getLayoutX());
        // translate.setByY(dest.getLayoutY() - this.rect.getLayoutY());
        translate.setByX(Main.base.game.rects[point.x()][point.y()].getLayoutX() - Main.base.game.rects[origin.x()][origin.y()].getLayoutX());
        translate.setByY(Main.base.game.rects[point.x()][point.y()].getLayoutY() - Main.base.game.rects[origin.x()][origin.y()].getLayoutY());
        translate.setDuration(Duration.millis(100));  
        translate.setNode(rect);
        translate.play();
    } //Best option is javafx.animation.TranslateTransition
    public void animateMovement2(Pane destPane)
    {
        TranslateTransition  translate = new TranslateTransition();  
        translate.setByX(0);
        translate.setByY(0);
        translate.setDuration(Duration.millis(80));  
        translate.setNode(rect);
        translate.play();
        translate.setOnFinished(e ->this.destroy());
    } //Best option is javafx.animation.TranslateTransition
    public void animateCreation()
    {
        ScaleTransition  translate = new ScaleTransition();  
        translate.setFromX(0);
        translate.setFromY(0);
        translate.setToX(1);
        translate.setToY(1);
        translate.setDuration(Duration.millis(250));  
        translate.setNode(rect);
        
        translate.play();
    }
    public void animateColapseCreation()
    {
        ScaleTransition  translate = new ScaleTransition();  
        translate.setFromX(1.2);
        translate.setFromY(1.2);
        translate.setToX(1);
        translate.setToY(1);
        translate.setDuration(Duration.millis(250));  
        translate.setNode(rect);
        
        translate.play();
    }
    public void animateColapse(GridPoint destBlock , AbstractBlock newBlock)
    {
        TranslateTransition  translate = new TranslateTransition();  
        translate.setByX(Main.base.game.rects[destBlock.x()][destBlock.y()].getLayoutX() - Main.base.game.rects[point.x()][point.y()].getLayoutX());
        translate.setByY(Main.base.game.rects[destBlock.x()][destBlock.y()].getLayoutY() - Main.base.game.rects[point.x()][point.y()].getLayoutY());
        translate.setDuration(Duration.millis(100));  
        translate.setNode(rect);
        //rect.setLayoutX(destPane.getLayoutX());
        //rect.setLayoutY(destPane.getLayoutY());
        translate.play();
        translate.setOnFinished(e -> {
            this.destroy();
            newBlock.animateColapseCreation();
            Main.base.game.pane.getChildren().add(((Block)newBlock).rect);
        });
    }
    public void destroy()
    {
        Main.base.game.pane.getChildren().remove(this.rect);
    }
}
