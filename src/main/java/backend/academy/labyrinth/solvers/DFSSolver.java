package backend.academy.labyrinth.solvers;

import backend.academy.labyrinth.maze.Cell;
import backend.academy.labyrinth.maze.Maze;
import lombok.Getter;
import org.apache.commons.lang3.SerializationUtils;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class DFSSolver {
    private Maze maze;
    private Maze solvedMaze;
    private Cell start;
    private Cell end;
    private Set<Cell> visited;
    Stack<Cell> stack = new Stack<>();
    @Getter
    boolean isFinished = false;

    public void maze(Maze maze1){
        this.maze = maze1;
        this.solvedMaze = SerializationUtils.clone(maze1);
        start = this.solvedMaze.start();
        end = this.solvedMaze.end();
    }

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

    public Maze solve(){
        Maze solvedMaze = SerializationUtils.clone(this.maze);
        Cell start = solvedMaze.start();
        Cell end = solvedMaze.end();
        dfs(start, end);
        return solvedMaze;
    }


    public void nextStep(){
        Cell last = stack.getLast();
        for(Cell cell : last.neighbours()){
            if(!visited.contains(cell)){
                visited.add(cell);
                stack.add(cell);
                if(cell == end){
                    isFinished = true;
                    return;
                }
                cell.visited(true);
                return;
            }
        }
        last.visited(false);
        stack.pop();
    }


    public Maze stepByStepSolve(){
        isFinished = false;
        visited = new HashSet<>();
        stack.add(start);
        return solvedMaze;
    }
}
