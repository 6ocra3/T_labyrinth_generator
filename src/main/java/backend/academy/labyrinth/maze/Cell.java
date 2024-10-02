package backend.academy.labyrinth.maze;

import backend.academy.labyrinth.extraStructures.point.Point;
import lombok.Builder;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Cell {
    Point point;
    List<Cell> neighbours = new ArrayList<>();
    private boolean hasTopNeighbour = false;
    private boolean hasRightNeighbour = false;
    private boolean hasBottomNeighbour = false;
    private boolean hasLeftNeighbour = false;


    public Cell(Point p){
        point = p;
    }

    public void addNeighbour(Cell cell){
        switch (cell.point.y() - point.y()){
            case 1:
                hasBottomNeighbour = true;
                break;
            case -1:
                hasTopNeighbour = true;
                break;
        }
        switch (cell.point.x() - point.x()){
            case 1:
                hasRightNeighbour = true;
                break;
            case -1:
                hasLeftNeighbour = true;
                break;
        }
        neighbours.add(cell);
    }
}
