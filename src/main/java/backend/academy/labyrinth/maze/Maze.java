package backend.academy.labyrinth.maze;

import backend.academy.labyrinth.extraStructures.edge.Edge;
import backend.academy.labyrinth.extraStructures.point.Point;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class Maze {

    int width;
    int height;
    @Getter
    List<List<Cell>> maze = new ArrayList<>();

    public Maze(List<Edge> labyrinth, int width, int height){
        for(int i = 0; i<height;i++){
            List<Cell> temp = new ArrayList<>();
            for(int j = 0; j<width;j++){
                temp.add(new Cell(new Point(i, j)));
            }
            this.maze.add(temp);
        }
        for(Edge edge : labyrinth){
            Point point1 = edge.first();
            int y1 = point1.y(), x1 = point1.x();
            Point point2 = edge.second();
            int y2 = point2.y(), x2 = point2.x();
            Cell cell1 = maze.get(y1).get(x1);
            Cell cell2 = maze.get(y2).get(x2);
            cell1.addNeighbour(cell2);
            cell2.addNeighbour(cell1);
        }
    }
}
