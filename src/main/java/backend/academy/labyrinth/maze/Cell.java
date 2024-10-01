package backend.academy.labyrinth.maze;

import backend.academy.labyrinth.extraStructures.point.Point;
import lombok.Builder;
import java.util.ArrayList;
import java.util.List;

@Builder
public class Cell {
    Point point;
    List<Cell> neighbours = new ArrayList<>();

    public Cell(Point p){
        point = p;
    }

    public void addNeighbour(Cell cell){

    }

    public void hasTopNeighbour(){

    }
}
