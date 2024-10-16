package backend.academy.labyrinth.generators;

import backend.academy.labyrinth.extraStructures.edge.Edge;
import backend.academy.labyrinth.extraStructures.point.Point;
import lombok.Getter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class KruskalGenerator implements Generator {

    int width;
    int height;
    Map<Point, Point> roots = new HashMap<>();
    List<Edge> edges = new ArrayList<>();
    @Getter
    List<Edge> maze = new ArrayList<>();

    @Override
    public String getShortInfo(){
        return "Kruskal Generator";
    }

    public KruskalGenerator(){
    }

    private Point findRoot(Point point){
        Point root = roots.get(point);
        if (root != point) {
            root = findRoot(root);
            roots.put(point, root);
        }
        return root;
    }

    private void unionSets(Point first, Point second){
        Point firstRoot = findRoot(first);
        Point secondRoot = findRoot(second);
        roots.put(firstRoot, secondRoot);
    }


    @Override
    public List<Edge> generate(int width, int height){
        this.width = width;
        this.height = height;

        for(int i = 0; i<height;i++){
            for(int j = 0; j<width;j++){
                Point point = new Point(i, j);
                roots.put(point, point);
                if(i < height-1){
                    edges.add(new Edge(point, new Point(i+1, j)));
                }
                if(j < width-1){
                    edges.add(new Edge(point, new Point(i, j+1)));
                }
            }
        }

        Random rnd = new Random();
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
