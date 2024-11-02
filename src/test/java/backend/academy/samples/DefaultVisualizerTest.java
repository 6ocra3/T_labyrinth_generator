package backend.academy.samples;

import backend.academy.labyrinth.extraStructures.edge.Edge;
import backend.academy.labyrinth.extraStructures.point.Point;
import backend.academy.labyrinth.maze.Maze;
import backend.academy.labyrinth.solvers.BFSSolver;
import backend.academy.labyrinth.solvers.DFSSolver;
import backend.academy.labyrinth.visualizers.AbstractVisualizer;
import backend.academy.labyrinth.visualizers.DefaultVisualizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultVisualizerTest {
    Maze maze;

    String visualizedMaze =
"""
○───○───○───○
│ A         │
○───○   ○───○
│           │
○───○   ○───○
│         B │
○───○───○───○
""";

    @BeforeEach
    public void setUp() {
        maze = createSimpleMaze();
    }

    @Test
    public void testVisualizer(){
        // Arrange
        AbstractVisualizer visualizer = new DefaultVisualizer();

        // Act
        String ans = visualizer.visualizeMaze(maze);

        //Assert
        assertEquals(visualizedMaze, ans);
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
