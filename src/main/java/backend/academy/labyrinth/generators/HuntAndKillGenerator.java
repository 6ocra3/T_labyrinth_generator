package backend.academy.labyrinth.generators;

import backend.academy.labyrinth.extraStructures.edge.Edge;
import backend.academy.labyrinth.extraStructures.point.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;

public class HuntAndKillGenerator implements Generator {
    private int width;
    private int height;
    @Getter
    private List<Edge> maze = new ArrayList<>();
    private boolean[][] visited;
    @Setter
    private Random rnd = new Random();

    @Override
    public String getShortInfo() {
        return "Hunt And Kill Generator";
    }

    public HuntAndKillGenerator() {
    }

    public boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public List<Point> findNeighbours(Point p, boolean visitedValue) {
        List<Point> neighbours = new ArrayList<>();
        int[][] dxdy = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        for (int[] temp : dxdy) {
            int nx = p.x() + temp[0];
            int ny = p.y() + temp[1];
            if (inBounds(nx, ny) && visited[ny][nx] == visitedValue) {
                neighbours.add(new Point(ny, nx));
            }
        }
        return neighbours;
    }

    public void kill(Point p) {
        Point curPoint = p;
        visited[curPoint.y()][curPoint.x()] = true;

        while (true) {
            List<Point> neighbours = findNeighbours(curPoint, false);

            if (neighbours.isEmpty()) {
                break;
            }

            Point next = neighbours.get(rnd.nextInt(neighbours.size()));

            Edge edge = new Edge(curPoint, next);
            maze.add(edge);
            visited[next.y()][next.x()] = true;

            curPoint = next;
        }
    }

    public Point hunt() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!visited[y][x]) {
                    Point cur = new Point(y, x);
                    List<Point> neighbours = findNeighbours(cur, true);
                    if (!neighbours.isEmpty()) {
                        Point prev = neighbours.get(rnd.nextInt(neighbours.size()));
                        visited[y][x] = true;
                        maze.add(new Edge(prev, cur));
                        return cur;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<Edge> generate(int width, int height) {
        this.width = width;
        this.height = height;
        visited = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(visited[i], false);
        }

        int startX = rnd.nextInt(width);
        int startY = rnd.nextInt(height);

        kill(new Point(startY, startX));
        while (true) {
            Point p = hunt();
            if (p == null) {
                break;
            }
            kill(p);
        }
        return maze;
    }
}
