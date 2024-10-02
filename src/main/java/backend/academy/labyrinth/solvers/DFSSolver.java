package backend.academy.labyrinth.solvers;

import backend.academy.labyrinth.maze.Cell;
import backend.academy.labyrinth.maze.Maze;
import org.apache.commons.lang3.SerializationUtils;

public class DFSSolver {
    public DFSSolver(){
        System.out.println("BFS солвер");
    }

    public static boolean dfs(Cell cell, Cell end){
        if(cell == end){
            return true;
        }
        for(Cell neighbour : cell.neighbours()){
            if(!neighbour.visited()){
                neighbour.visited(true);
                if(dfs(neighbour, end)){
                    return true;
                }
                neighbour.visited(false);
            }
        }
        return false;
    }

    public static Maze solve(Maze maze){
        Maze solvedMaze = SerializationUtils.clone(maze);
        Cell start = solvedMaze.start();
        Cell end = solvedMaze.end();
        dfs(start, end);
        return solvedMaze;
    }
}
