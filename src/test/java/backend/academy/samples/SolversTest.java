package backend.academy.samples;

import backend.academy.labyrinth.extraStructures.edge.Edge;
import backend.academy.labyrinth.extraStructures.point.Point;
import backend.academy.labyrinth.generators.HuntAndKillGenerator;
import backend.academy.labyrinth.generators.KruskalGenerator;
import backend.academy.labyrinth.maze.Cell;
import backend.academy.labyrinth.maze.Maze;
import backend.academy.labyrinth.solvers.BFSSolver;
import backend.academy.labyrinth.solvers.DFSSolver;
import backend.academy.labyrinth.solvers.Solver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolversTest {

    List<Solver> solvers;
    Maze maze;

    @BeforeEach
    public void setUp() {
        solvers = Arrays.asList(new BFSSolver(), new DFSSolver());
        maze = createSimpleMaze();
    }

    @Test
    public void testSolveReturnsCorrectPath() {
        for(Solver solver : solvers){
            // Arrange
            Set<Cell> visitedBySearch = new HashSet<>();

            // Act
            Maze solvedMaze = solver.solve(maze);

            // Assert
            Cell start = solvedMaze.start();
            Cell end = solvedMaze.end();

            assertTrue(start.visited(), "Start must be visited: " + solver.getShortInfo());
            assertTrue(end.visited(), "End must be visited: " + solver.getShortInfo());

            Cell current = end;
            visitedBySearch.add(end);
            while (current != start && current != null) {
                assertTrue(current.visited(), "Backtrack path failed: " + solver.getShortInfo());
                current = getPreviousCellOnPath(current, visitedBySearch);
                visitedBySearch.add(current);
            }
            assertEquals(current, start);
        }

    }

    private Cell getPreviousCellOnPath(Cell cell, Set<Cell> visitedBySearch){
        for(Cell next : cell.neighbours()){
            if(next.visited() && !visitedBySearch.contains(next)) return next;
        }
        return null;
    }

    private Maze createSimpleMaze() {
        List<Edge> edges = new ArrayList<>();

        Point start = new Point(0, 0);
        Point end = new Point(2, 2);
        edges.add(new Edge(start, new Point(0, 1)));
        edges.add(new Edge(new Point(0, 1), new Point(0, 2)));

        edges.add(new Edge(new Point(1, 0), new Point(1, 1)));
        edges.add(new Edge(new Point(1, 1), new Point(1, 2)));
        edges.add(new Edge(new Point(0, 1), new Point(1, 1)));

        edges.add(new Edge(new Point(2, 0), new Point(2, 1)));
        edges.add(new Edge(new Point(2, 1), end));
        edges.add(new Edge(new Point(1, 1), new Point(2, 1)));

        return new Maze(edges, 3, 3, start, end);
    }
}
