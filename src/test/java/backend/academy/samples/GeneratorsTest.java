package backend.academy.samples;

import backend.academy.labyrinth.extraStructures.edge.Edge;
import backend.academy.labyrinth.extraStructures.point.Point;
import backend.academy.labyrinth.generators.Generator;
import backend.academy.labyrinth.generators.HuntAndKillGenerator;
import backend.academy.labyrinth.generators.KruskalGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratorsTest {
    List<Generator> generators;

    @BeforeEach
    public void setUp() {
        generators = Arrays.asList(new HuntAndKillGenerator(), new KruskalGenerator());
    }

    @Test
    public void testGenerateMaze() {
        int width = 3;
        int height = 3;
        for (Generator generator : generators) {
            // Arrange
            Set<Point> visited = new HashSet<>();

            //Act
            List<Edge> edges = generator.generate(width, height);

            // Assert
            Point first = edges.getFirst().first();
            checkConnections(first, edges, visited);

            assertEquals(width * height, visited.size(), "Test failed for generator: " + generator.getShortInfo());
        }
    }

    private void checkConnections(Point point, List<Edge> edges, Set<Point> visited) {
        if (visited.contains(point)) {
            return;
        }
        visited.add(point);

        for (Edge edge : edges) {
            if (edge.first().equals(point)) {
                checkConnections(edge.second(), edges, visited);
            } else if (edge.second().equals(point)) {
                checkConnections(edge.first(), edges, visited);
            }
        }
    }

}
