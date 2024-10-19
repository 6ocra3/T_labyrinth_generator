package backend.academy.labyrinth.maze;

import backend.academy.labyrinth.extraStructures.point.Point;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Cell implements Serializable {
    private final Point point;
    private final Set<Cell> neighbours = new HashSet<>();
    @Setter
    private SurfaceType surface = SurfaceType.DefaultSurface;
    @Setter
    private CellType type = CellType.DEFAULT;
    private Cell topNeighbour = null;
    private Cell rightNeighbour = null;
    private Cell bottomNeighbour = null;
    private Cell leftNeighbour = null;
    @Setter
    private boolean visited = false;
    @Setter
    private int pathCost = 999;

    public int getSurfaceModifier(){
        return this.surface.value();
    }

    public Cell(Point p) {
        point = p;
    }

    public boolean addNeighbour(Cell cell){
        if(!neighbours.add(cell)){
            return false;
        }
        switch (cell.point.y() - point.y()){
            case 1:
                bottomNeighbour = cell;
                break;
            case -1:
                topNeighbour = cell;
                break;
            default:
                break;
        }
        switch (cell.point.x() - point.x()) {
            case 1:
                rightNeighbour = cell;
                break;
            case -1:
                leftNeighbour = cell;
                break;
            default:
                break;
        }
        return true;
    }
}
