package backend.academy.labyrinth.generators;

import backend.academy.labyrinth.extraStructures.edge.Edge;
import backend.academy.labyrinth.extraStructures.point.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class KraskalGenerator {

    int width;
    int height;
    Map<Point, Point> roots = new HashMap<>();
    List<Edge> edges = new ArrayList<>();
    List<Edge> maze = new ArrayList<>();


    public KraskalGenerator(){
        System.out.println("Kraskal генератор");
    }

    private Point findRoot(Point point){
        Point root = roots.get(point);
        if(root != point){
            return findRoot(root);
        }
        return point;
    }

    private void unionSets(Point first, Point second){
        Point firstRoot = findRoot(first);
        Point secondRoot = findRoot(second);
        roots.put(firstRoot, secondRoot);
    }

    public void generate(){
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

        Collections.shuffle(edges);

        for(Edge edge : edges){
            Point first = edge.first();
            Point second = edge.second();
            if(roots.get(first) != roots.get(second)){
                maze.add(edge);
                unionSets(first, second);
            }
        }

    }
}
