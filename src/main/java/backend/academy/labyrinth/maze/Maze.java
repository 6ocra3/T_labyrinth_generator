package backend.academy.labyrinth.maze;

import backend.academy.labyrinth.extraStructures.edge.Edge;
import backend.academy.labyrinth.extraStructures.point.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Maze implements Serializable {

    int width;
    int height;
    @Getter
    Cell start;
    @Getter
    Cell end;
    @Getter
    List<List<Cell>> maze = new ArrayList<>();

    public Maze(List<Edge> labyrinth, int width, int height, Point start, Point end) {
        this.width = width;
        this.height = height;
        for (int i = 0; i < height; i++) {
            List<Cell> temp = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                temp.add(new Cell(new Point(i, j)));
            }
            this.maze.add(temp);
        }
        for (Edge edge : labyrinth) {
            Point point1 = edge.first();
            int y1 = point1.y();
            int x1 = point1.x();
            Point point2 = edge.second();
            int y2 = point2.y();
            int x2 = point2.x();
            Cell cell1 = maze.get(y1).get(x1);
            Cell cell2 = maze.get(y2).get(x2);
            cell1.addNeighbour(cell2);
            cell2.addNeighbour(cell1);
        }
        this.start = maze.get(start.y()).get(start.x());
        this.start.type(CellType.START);
        this.end = maze.get(end.y()).get(end.x());
        this.end.type(CellType.END);
    }

    public void setAllVisitedTo(boolean value) {
        for (List<Cell> row : maze) {
            for (Cell cell : row) {
                cell.visited(value);
            }
        }
    }
}
