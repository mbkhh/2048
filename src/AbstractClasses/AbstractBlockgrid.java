package AbstractClasses;
public abstract class AbstractBlockgrid {

    public abstract boolean checkEnd(); //use GridPoint.neighbors here
    public abstract boolean addNewBlock(); // return false if is full

    public abstract void up();
    public abstract void down();
    public abstract void right();
    public abstract void left();
    public abstract AbstractBlock getBlock(GridPoint point);

    public abstract void setBlock(GridPoint point, AbstractBlock toBlock); // in the end you might want to cast to
                                                                    // the "Block" class you will implement
    public abstract GridPoint getRandomEmptyPoint();
    public abstract boolean isBlockEmpty(int i, int j);
    public abstract boolean isBlockEmpty(GridPoint point);
    public abstract void debugMessage();
}
