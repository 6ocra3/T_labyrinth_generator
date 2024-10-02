package backend.academy.labyrinth.solvers;

import backend.academy.labyrinth.maze.Cell;
import backend.academy.labyrinth.maze.Maze;
import org.apache.commons.lang3.SerializationUtils;

public class BFSSolvers {
    public BFSSolvers(){
        System.out.println("BFS солвер");
    }

    public static boolean bfs(Cell cell, Cell end){
        if(cell == end){
            return true;
        }
        for(Cell neighbour : cell.neighbours()){
            if(!neighbour.visited()){
                neighbour.visited(true);
                if(bfs(neighbour, end)){
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
        bfs(start, end);
        return solvedMaze;
    }
}
