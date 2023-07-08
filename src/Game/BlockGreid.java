package Game;

import java.util.ArrayList;
import java.util.Random;

import AbstractClasses.AbstractBlock;
import AbstractClasses.AbstractBlockgrid;
import AbstractClasses.GridPoint;

public class BlockGreid extends AbstractBlockgrid{
    AbstractBlock[][] blocks = new AbstractBlock[4][4];
    static int id_cunter = 1;
    ArrayList<String> avalibleId;
    ArrayList<String> wanted;
    BlockGreid()
    {
        avalibleId = new ArrayList<>();
        wanted = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) 
                blocks[i][j] = null;
    }
    public void reset()
    {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) 
                if(!isBlockEmpty(new GridPoint(i, j)))
                    getBlock(new GridPoint(i, j)).destroy();
    }
    public boolean checkEnd()
    {
        if(getRandomEmptyPoint() == null)//game has benn ended
        {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    ArrayList<GridPoint> neighbors = blocks[i][j].point.neighbors();
                    for (int k = 0; k < neighbors.size(); k++) {
                        if (((Block)blocks[i][j]).number == ((Block)blocks[neighbors.get(k).x()][neighbors.get(k).y()]).number)
                            return false;
                    }
                }
            }
            return true;
        }
        else
            return false;
    } //use GridPoint.neighbors here
    public boolean addNewBlock()
    {
        GridPoint point = getRandomEmptyPoint();
        if (point == null)
            return false;
        Random rand = new Random();
        int num = 1;
        num = (rand.nextInt(4) == 3) ? 4 : 2;
        Block block = new Block(point, num, Main.base.game.rects[point.x()][point.y()] , "block"+id_cunter);
        avalibleId.add("block"+id_cunter);
        wanted.add("block"+id_cunter);
        id_cunter++;
        Main.base.game.pane.getChildren().addAll(block.rect);
        setBlock(point, block);
        block.animateCreation();
        return true;
    } // return false if is full
    public void up(){ // example of how one might implement this function
        boolean changed = false;
        for (int row = 1; row < 4; row++)
            for (int column = 0; column < 4; column++) // the 'for' parameters might change for other directions
                if (!isBlockEmpty(row,column)) {
                    AbstractBlock block = getBlock(new GridPoint(row,column));
                    GridPoint origin = new GridPoint(row,column);
                    GridPoint dest = new GridPoint(row, column);
                    for (int i = row - 1; i >= 0 ; i--) {
                        if (isBlockEmpty(i, column)){
                            dest = new GridPoint(i, column);
                        }
                        else if (((Block)getBlock(new GridPoint(i, column))).number == ((Block)block).number)
                        {
                            dest = new GridPoint(i, column);
                        }
                        else
                            break;
                    }
                    if(!origin.equals(dest))
                    {
                        if (isBlockEmpty(dest))
                        {
                            block.changePoint(dest);
                            setBlock(block.point, block);
                            setBlock(origin, null);
                            block.animateMovement(origin );
                            AudioPlayer.audioPlayer.playTick();
                        }
                        else
                        {
                            avalibleId.remove(((Block)block).id);
                            avalibleId.remove(((Block)getBlock(dest)).id);
                            Main.base.score += ((Block)block).number + ((Block)getBlock(dest)).number;
                            Block newBlock = new Block(dest, ((Block)block).number + ((Block)getBlock(dest)).number, Main.base.game.rects[dest.x()][dest.y()], "block"+id_cunter);
                            avalibleId.add("block"+id_cunter);
                            wanted.add("block"+id_cunter);
                            id_cunter++;
                            ((Block)getBlock(dest)).animateMovement2(Main.base.game.rects[dest.x()][dest.y()]);
                            setBlock(dest, newBlock);
                            setBlock(origin, null);
                            block.animateColapse(dest , newBlock);
                            AudioPlayer.audioPlayer.playHit();
                        }
                        changed = true;
                    }                    
                }        
        if(changed)
            addNewBlock();
        else
            AudioPlayer.audioPlayer.playError();
        destroyUnwantedBlock();
    }
    public void down()
    {
        boolean changed = false;
        for (int row = 2; row >= 0; row--)
            for (int column = 0; column < 4; column++) // the 'for' parameters might change for other directions
                if (!isBlockEmpty(row,column)) {
                    AbstractBlock block = getBlock(new GridPoint(row,column));
                    GridPoint origin = new GridPoint(row,column);
                    GridPoint dest = new GridPoint(row, column);
                    for (int i = row + 1; i < 4 ; i++) {
                        if (isBlockEmpty(i , column)){
                            dest = new GridPoint(i , column);
                        }
                        else if (((Block)getBlock(new GridPoint(i , column))).number == ((Block)block).number)
                        {
                            dest = new GridPoint(i , column);
                        }
                        else
                            break;
                    }
                    if(!origin.equals(dest))
                    {
                        if (isBlockEmpty(dest))
                        {
                            block.changePoint(dest);
                            setBlock(block.point, block);
                            setBlock(origin, null);
                            block.animateMovement(origin);
                            AudioPlayer.audioPlayer.playTick();
                        }
                        else
                        {
                            avalibleId.remove(((Block)block).id);
                            avalibleId.remove(((Block)getBlock(dest)).id);
                            Main.base.score += ((Block)block).number + ((Block)getBlock(dest)).number;
                            Block newBlock = new Block(dest, ((Block)block).number + ((Block)getBlock(dest)).number, Main.base.game.rects[dest.x()][dest.y()], "block"+id_cunter);
                            avalibleId.add("block"+id_cunter);
                            wanted.add("block"+id_cunter);
                            id_cunter++;
                            ((Block)getBlock(dest)).animateMovement2(Main.base.game.rects[dest.x()][dest.y()]);
                            setBlock(dest, newBlock);
                            setBlock(origin, null);
                            block.animateColapse(dest , newBlock);
                            AudioPlayer.audioPlayer.playHit();
                        }
                        changed = true;
                    }                    
                }
        if(changed)
            addNewBlock();
        else
            AudioPlayer.audioPlayer.playError();
        // System.out.println(Main.base.game.pane.getChildren());
        // System.out.println(Main.base.game.pane.getChildren().size());
        // System.out.println(Main.base.game.pane.getChildren().get(16).getId());
        // System.out.println(avalibleId.toString());
        destroyUnwantedBlock();
    }
    public void right()
    {
        boolean changed = false;
        for (int row = 0; row < 4; row++)
            for (int column = 2; column >= 0; column--) // the 'for' parameters might change for other directions
                if (!isBlockEmpty(row,column)) {
                    AbstractBlock block = getBlock(new GridPoint(row,column));
                    GridPoint origin = new GridPoint(row,column);
                    GridPoint dest = new GridPoint(row, column);
                    for (int i = column + 1; i < 4 ; i++) {
                        if (isBlockEmpty(row , i)){
                            dest = new GridPoint(row , i);
                        }
                        else if (((Block)getBlock(new GridPoint(row , i))).number == ((Block)block).number)
                        {
                            dest = new GridPoint(row , i);
                        }
                        else
                            break;
                    }
                    if(!origin.equals(dest))
                    {
                        if (isBlockEmpty(dest))
                        {
                            block.changePoint(dest);
                            setBlock(block.point, block);
                            setBlock(origin, null);
                            block.animateMovement(origin);
                            AudioPlayer.audioPlayer.playTick();
                        }
                        else
                        {
                            avalibleId.remove(((Block)block).id);
                            avalibleId.remove(((Block)getBlock(dest)).id);
                            Main.base.score += ((Block)block).number + ((Block)getBlock(dest)).number;
                            Block newBlock = new Block(dest, ((Block)block).number + ((Block)getBlock(dest)).number, Main.base.game.rects[dest.x()][dest.y()], "block"+id_cunter);
                            avalibleId.add("block"+id_cunter);
                            wanted.add("block"+id_cunter);
                            id_cunter++;
                            ((Block)getBlock(dest)).animateMovement2(Main.base.game.rects[dest.x()][dest.y()]);
                            setBlock(dest, newBlock);
                            setBlock(origin, null);
                            block.animateColapse(dest , newBlock);
                            AudioPlayer.audioPlayer.playHit();
                        }
                        changed = true;
                    }                    
                }
        if(changed)
            addNewBlock();
        else
            AudioPlayer.audioPlayer.playError();
        destroyUnwantedBlock();
    }
    public void left()
    {
        boolean changed = false;
        for (int row = 0; row < 4; row++)
            for (int column = 1; column < 4; column++) // the 'for' parameters might change for other directions
                if (!isBlockEmpty(row,column)) {
                    AbstractBlock block = getBlock(new GridPoint(row,column));
                    GridPoint origin = new GridPoint(row,column);
                    GridPoint dest = new GridPoint(row, column);
                    for (int i = column - 1; i >= 0 ; i--) {
                        if (isBlockEmpty(row , i)){
                            dest = new GridPoint(row , i);
                        }
                        else if (((Block)getBlock(new GridPoint(row , i))).number == ((Block)block).number)
                        {
                            dest = new GridPoint(row , i);
                        }
                        else
                            break;
                    }
                    if(!origin.equals(dest))
                    {
                        if (isBlockEmpty(dest))
                        {
                            block.changePoint(dest);
                            setBlock(block.point, block);
                            setBlock(origin, null);
                            block.animateMovement(origin );
                            AudioPlayer.audioPlayer.playTick();
                        }
                        else
                        {
                            avalibleId.remove(((Block)block).id);
                            avalibleId.remove(((Block)getBlock(dest)).id);
                            Main.base.score += ((Block)block).number + ((Block)getBlock(dest)).number;
                            Block newBlock = new Block(dest, ((Block)block).number + ((Block)getBlock(dest)).number, Main.base.game.rects[dest.x()][dest.y()], "block"+id_cunter);
                            avalibleId.add("block"+id_cunter);
                            wanted.add("block"+id_cunter);
                            id_cunter++;
                            ((Block)getBlock(dest)).animateMovement2(Main.base.game.rects[dest.x()][dest.y()]);
                            setBlock(dest, newBlock);
                            setBlock(origin, null);
                            block.animateColapse(dest , newBlock);
                            AudioPlayer.audioPlayer.playHit();
                        }
                        changed = true;
                    }                    
                } 
        if(changed)
            addNewBlock();
        else
            AudioPlayer.audioPlayer.playError();
        destroyUnwantedBlock();
    }
    public void destroyUnwantedBlock()
    {
        // System.out.println(Main.base.game.pane.getChildren());
        // System.out.println(Main.base.game.pane.getChildren().size());
        // System.out.println(Main.base.game.pane.getChildren().get(16).getId());
        // System.out.println(avalibleId.toString());
        for (int i = Main.base.game.pane.getChildren().size() - 1 ; i > 15 ; i--) {
            if(!wanted.contains(Main.base.game.pane.getChildren().get(i).getId()))
            Main.base.game.pane.getChildren().remove(i);
        }
        wanted.clear();
        for (int i = 0; i < avalibleId.size(); i++) {
            wanted.add(avalibleId.get(i));
        }
    }
    public void setBlock(GridPoint point, AbstractBlock toBlock)
    {
        blocks[point.x()][point.y()] = toBlock;   
    } // in the end you might want to cast to
    public AbstractBlock getBlock(GridPoint point){
        return blocks[point.x()][point.y()];
    }
    public boolean isBlockEmpty(GridPoint point){
        return isBlockEmpty(point.x(), point.y());
    }
    public GridPoint getRandomEmptyPoint()
    {
        ArrayList<GridPoint> grids = new ArrayList<>();
        for (int i = 0; i < 16; i++)
            grids.add(new GridPoint((i/4), i % 4));
        J:
        for (int i = grids.size()-1; i >= 0; i--) {
            if(!isBlockEmpty(grids.get(i).x(), grids.get(i).y()))
            {
                grids.remove(i);
                continue J;
            }
        }
        Random rand = new Random();
        // System.out.println(grids.size());
        if(grids.size() > 0)
            return grids.get(rand.nextInt(grids.size()));
        else
            return null;
    }
    public boolean isBlockEmpty(int i, int j)
    {
        if(blocks[i][j] == null)
            return true;
        else
            return false;
    }
    public void debugMessage()
    {
        System.out.println("----------Debug----------");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[i][j] == null)
                    System.out.print("0     ");
                else
                    System.out.print(((Block)getBlock(new GridPoint(i,j))).getNumber()+"     ");
            }
            System.out.println();
        }
    }
}
