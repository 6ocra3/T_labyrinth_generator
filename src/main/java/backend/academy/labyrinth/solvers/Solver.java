package backend.academy.labyrinth.solvers;

import backend.academy.labyrinth.extraStructures.BaseObject;
import backend.academy.labyrinth.maze.Maze;
import java.util.Iterator;

public interface Solver extends BaseObject {
    Maze solve(Maze maze);
    Iterator<Maze> getIterator(Maze maze);
}
