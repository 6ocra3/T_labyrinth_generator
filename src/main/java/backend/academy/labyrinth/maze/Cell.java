package backend.academy.labyrinth.maze;

import backend.academy.labyrinth.extraStructures.point.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Cell implements Serializable {
    Point point;
    List<Cell> neighbours = new ArrayList<>();
    @Setter
    CellType type = CellType.DEFAULT;
    private Cell topNeighbour = null;
    private Cell rightNeighbour = null;
    private Cell bottomNeighbour = null;
    private Cell leftNeighbour = null;
    @Getter @Setter
    boolean visited = false;

    public Cell(Point p) {
        point = p;
    }

    public void addNeighbour(Cell cell) {
        switch (cell.point.y() - point.y()) {
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
        neighbours.add(cell);
    }
}
