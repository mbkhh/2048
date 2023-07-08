package AbstractClasses;

public abstract class AbstractBlock {
    public GridPoint point; // don't forget this!
    public abstract void changePoint(GridPoint point);
    public abstract GridPoint move(GridPoint from, Direction direction); // it is better to use recursion and then return the final
                                                                // GridPoint it ends up in
    public abstract void animateMovement(GridPoint origin) ; //Best option is javafx.animation.TranslateTransition
    public abstract void animateColapseCreation();
    public abstract void animateColapse( GridPoint destBlock , AbstractBlock newBlock);
    public abstract void animateCreation(); //Best option is javafx.animation.ScaleTransition
    public abstract void destroy();
}
