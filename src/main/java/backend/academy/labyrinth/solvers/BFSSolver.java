package backend.academy.labyrinth.solvers;

import backend.academy.labyrinth.maze.Cell;
import backend.academy.labyrinth.maze.Maze;
import org.apache.commons.lang3.SerializationUtils;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BFSSolver {
    public BFSSolver(){

    }

    public static void bfs(Cell start, Cell end, Queue<Cell> queue){
        Map<Cell, Cell> path = new HashMap<>();
        Set<Cell> visited = new HashSet<>();
        visited.add(start);
        queue.add(start);
        while(!queue.isEmpty()){
            Cell cur = queue.poll();
            if(cur == end){
                break;
            }
            for(Cell neighbour : cur.neighbours()){
                if(!visited.contains(neighbour)){
                    visited.add(neighbour);
                    path.put(neighbour, cur);
                    queue.add(neighbour);
                }
            }
        }
        Cell backPath = end;
        while (path.get(backPath) != null){
            backPath = path.get(backPath);
            backPath.visited(true);
        }
    }

    public static Maze solve(Maze maze){
        Maze solvedMaze = SerializationUtils.clone(maze);
        Cell start = solvedMaze.start();
        Cell end = solvedMaze.end();
        Queue<Cell> queue = new ArrayDeque<>();
        bfs(start, end, queue);
        return solvedMaze;
    }
}
