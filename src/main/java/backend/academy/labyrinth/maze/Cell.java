package backend.academy.labyrinth.maze;

import backend.academy.labyrinth.extraStructures.point.Point;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Cell implements Serializable {
    Point point;
    Set<Cell> neighbours = new HashSet<>();
    @Setter
    CellType type = CellType.DEFAULT;
    private Cell topNeighbour = null;
    private Cell rightNeighbour = null;
    private Cell bottomNeighbour = null;
    private Cell leftNeighbour = null;
    @Getter @Setter
    boolean visited = false;

    public Cell(Point p){
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
        }
        switch (cell.point.x() - point.x()){
            case 1:
                rightNeighbour = cell;
                break;
            case -1:
                leftNeighbour = cell;
                break;
        }
        return true;
    }
}
