package backend.academy.labyrinth.solvers;

import backend.academy.labyrinth.maze.Cell;
import backend.academy.labyrinth.maze.Maze;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import lombok.Getter;
import org.apache.commons.lang3.SerializationUtils;


public class DFSSolver implements Solver {
    public DFSSolver() {

    }

    public String getShortInfo() {
        return "DFS Solver";
    }

    public boolean dfs(Cell cell, Cell end) {
        if (cell == end) {
            return true;
        }
        for (Cell neighbour : cell.neighbours()) {
            if (!neighbour.visited()) {
                neighbour.visited(true);
                if (dfs(neighbour, end)) {
                    return true;
                }
                neighbour.visited(false);
            }
        }
        return false;
    }

    @Override
    public Maze solve(Maze maze) {
        Maze solvedMaze = SerializationUtils.clone(maze);
        Cell start = solvedMaze.start();
        start.visited(true);
        Cell end = solvedMaze.end();
        dfs(start, end);
        return solvedMaze;
    }

    public Iterator<Maze> getIterator(Maze maze) {
        return new SolverIterator(maze);
    }

    public class SolverIterator implements Iterator {
        @Getter
        boolean isFinished = false;
        Maze solvedMaze = null;
        private Set<Cell> visited;
        Cell end;
        Deque<Cell> stack = new ArrayDeque<>();

        public SolverIterator(Maze maze) {
            solvedMaze = SerializationUtils.clone(maze);
            isFinished = false;
            Cell start = solvedMaze.start();
            end = solvedMaze.end();
            visited = new HashSet<>();
            stack.add(start);
        }

        @Override
        public boolean hasNext() {
            return !isFinished;
        }

        @Override
        public Maze next() {
            Cell last = stack.getLast();
            for (Cell cell : last.neighbours()) {
                if (!visited.contains(cell)) {
                    visited.add(cell);
                    stack.add(cell);
                    if (cell == end) {
                        isFinished = true;
                        return solvedMaze;
                    }
                    cell.visited(true);
                    return solvedMaze;
                }
            }
            last.visited(false);
            stack.removeLast();
            return solvedMaze;
        }
    }

}
