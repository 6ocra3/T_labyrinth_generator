package backend.academy.labyrinth.generators;

import backend.academy.labyrinth.extraStructures.edge.Edge;
import backend.academy.labyrinth.extraStructures.point.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;

public class KruskalGenerator implements Generator {

    private final Map<Point, Point> roots = new HashMap<>();
    private final List<Edge> edges = new ArrayList<>();
    @Setter
    private Random rnd = new Random();
    @Getter
    private List<Edge> maze = new ArrayList<>();

    @Override
    public String getShortInfo() {
        return "Kruskal Generator";
    }

    public KruskalGenerator() {
    }

    private Point findRoot(Point point) {
        Point root = roots.get(point);
        if (root != point) {
            root = findRoot(root);
            roots.put(point, root);
        }
        return root;
    }

    private void unionSets(Point first, Point second) {
        Point firstRoot = findRoot(first);
        Point secondRoot = findRoot(second);
        roots.put(firstRoot, secondRoot);
    }

    @Override
    public List<Edge> generate(int width, int height) {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Point point = new Point(i, j);
                roots.put(point, point);
                if (i < height - 1) {
                    edges.add(new Edge(point, new Point(i + 1, j)));
                }
                if (j < width - 1) {
                    edges.add(new Edge(point, new Point(i, j + 1)));
                }
            }
        }

        Collections.shuffle(edges, rnd);

        for (Edge edge : edges) {
            Point first = edge.first();
            Point second = edge.second();
            if (findRoot(first) != findRoot(second)) {
                maze.add(edge);
                unionSets(first, second);
            }
        }
        return maze;

    }
}
