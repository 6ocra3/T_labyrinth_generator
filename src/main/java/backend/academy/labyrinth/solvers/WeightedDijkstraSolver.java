package backend.academy.labyrinth.solvers;

import backend.academy.labyrinth.maze.Cell;
import backend.academy.labyrinth.maze.Maze;
import org.apache.commons.lang3.SerializationUtils;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class WeightedDijkstraSolver implements Solver {

    public WeightedDijkstraSolver() {}

    @Override
    public String getShortInfo() {
        return "Weighted Dijkstra solver ";
    }

    private void weightedDijkstra(Maze maze, Cell start, Cell end){
        Map<Cell, Integer> distances = new HashMap<>();
        for(int i = 0; i<maze.height();i++){
            for(int j = 0; j<maze.width();j++){
                maze.maze().get(i).get(j).pathCost(999);
            }
        }
        distances.put(start, 0);
        PriorityQueue<Path> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(path -> path.pathCost));

        while (!priorityQueue.isEmpty()){
            Path current = priorityQueue.poll();
            Cell curCell = current.cell;

            for(Cell neighbour : curCell.neighbours()){
                int newDist = current.pathCost + neighbour.getSurfaceModifier();

                if(newDist < neighbour.pathCost()){
                    neighbour.pathCost(newDist);
                    priorityQueue.add(new Path(neighbour, newDist));
                }
            }
        }
    }

    @Override
    public Maze solve(Maze maze) {
        Maze solvedMaze = SerializationUtils.clone(maze);
        Cell start = solvedMaze.start();
        start.visited(true);
        Cell end = solvedMaze.end();
        weightedDijkstra(solvedMaze, start, end);
        return solvedMaze;
    }

    @Override
    public Iterator<Maze> getIterator(Maze maze) {
        return null;
    }
    public record Path(Cell cell, int pathCost){}

}
