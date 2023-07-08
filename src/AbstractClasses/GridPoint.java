package AbstractClasses;
import java.util.ArrayList;
import java.util.List;

public record GridPoint(int x, int y) {
    public GridPoint up() {
        return new GridPoint(x-1, y); // todo
    }
    public GridPoint down() {
        return new GridPoint(x+1, y);
    }
    public GridPoint right() {
        return new GridPoint(x, y+1);
    }
    public GridPoint left() {
        return new GridPoint(x, y-1);
    }

    @Override
    public String toString(){
        return "(" +x + ","+y + ")"; // todo
    }
    @Override
    public boolean equals(Object o){
        // do not forget to check class type first
        if(!(o instanceof GridPoint))
            return false;
        else{
            if (((GridPoint)o).x == this.x && ((GridPoint)o).y == this.y)
                return true;
            else
                return false;
        }
    }

    public ArrayList<GridPoint> neighbors(){ // this one is implemented for you!
        GridPoint[] potential = new GridPoint[4];
        //Get all potential candidates for the neighbors of point
        potential[0] = up();
        potential[1] = down();
        potential[2] = left();
        potential[3] = right();

        //Remove all disallowed/illegal points; such as points with negative coordinates or with coordinates more than 3
        ArrayList<GridPoint> neighbors = new ArrayList<>(List.of(potential));
        neighbors.removeIf(GridPoint::isIllegal);
        return neighbors;
    }

    private static boolean isIllegal(GridPoint p) {
        return p.x < 0 || p.x > 3 || p.y < 0 || p.y > 3;
    }
}
