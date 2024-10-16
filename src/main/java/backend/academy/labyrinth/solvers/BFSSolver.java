package backend.academy.labyrinth.solvers;

import backend.academy.labyrinth.maze.Cell;
import backend.academy.labyrinth.maze.Maze;
import org.apache.commons.lang3.SerializationUtils;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BFSSolver {
    public BFSSolver(){

    }

    public static String getShortInfo(){
        return "BFS Solver";
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

    public class SolverIterator implements Iterator{

        List<Cell> prevStep = new ArrayList<>();
        Maze solvedMaze;
        boolean isFinished = false;
        Map<Cell, Cell> path = new HashMap<>();
        Set<Cell> visited = new HashSet<>();
        Cell end;


        public SolverIterator(Maze maze){
            solvedMaze = SerializationUtils.clone(maze);
            end = solvedMaze.end();
            solvedMaze.start().visited(true);
            prevStep.add(solvedMaze.start());
        }

        @Override
        public boolean hasNext() {
            return !isFinished;
        }

        @Override
        public Object next() {
            List<Cell> curStep = new ArrayList<>();
            for(Cell cell : prevStep){
                for(Cell neighbour : cell.neighbours()){
                    if(!neighbour.visited()){
                        visited.add(neighbour);
                        curStep.add(neighbour);
                        path.put(neighbour, cell);
                        neighbour.visited(true);
                        if(neighbour == end){
                            isFinished = true;
                            break;
                        }
                    }
                }
                if (isFinished) {
                    break;
                }
            }
            prevStep = curStep;
            if(!isFinished){
                return solvedMaze;
            }

            solvedMaze.setAllVisitedTo(false);
            Cell backPath = end;
            while (path.get(backPath) != null){
                backPath = path.get(backPath);
                backPath.visited(true);
            }
            return solvedMaze;

        }
    }

    public Iterator<Maze> getIterator(Maze maze){
        return new SolverIterator(maze);
    }

}
