package backend.academy.labyrinth.maze;

import backend.academy.labyrinth.extraStructures.point.Point;
import lombok.Builder;
import java.util.ArrayList;
import java.util.List;

@Builder
public class Cell {
    Point point;
    List<Cell> neighbours = new ArrayList<>();
    boolean hasTopNeighbour = false;
    boolean hasRightNeighbour = false;
    boolean hasBottomNeighbour = false;
    boolean hasLeftNeighbour = false;


    public Cell(Point p){
        point = p;
    }

    public void addNeighbour(Cell cell){
        switch (cell.point.y() - point.y()){
            case 1:
                hasBottomNeighbour = true;
            case -1:
                hasTopNeighbour = true;
        }
        switch (cell.point.x() - point.x()){
            case 1:
                hasLeftNeighbour = true;
            case -1:
                hasRightNeighbour = true;
        }
        neighbours.add(cell);
    }
}
