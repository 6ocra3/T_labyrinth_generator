package backend.academy.labyrinth.maze;

import backend.academy.labyrinth.extraStructures.point.Point;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Cell {
    Point point;
    List<Cell> neighbours = new ArrayList<>();
    @Setter
    CellType type = CellType.DEFAULT;
    private Cell topNeightbour = null;
    private Cell rightNeightbour = null;
    private Cell bottomNeightbour = null;
    private Cell leftNeightbour = null;

    public Cell(Point p){
        point = p;
    }

    public void addNeighbour(Cell cell){
        switch (cell.point.y() - point.y()){
            case 1:
                bottomNeightbour = cell;
                break;
            case -1:
                topNeightbour = cell;
                break;
        }
        switch (cell.point.x() - point.x()){
            case 1:
                rightNeightbour = cell;
                break;
            case -1:
                leftNeightbour = cell;
                break;
        }
        neighbours.add(cell);
    }
}
