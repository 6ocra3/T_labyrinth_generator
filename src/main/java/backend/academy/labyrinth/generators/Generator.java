package backend.academy.labyrinth.generators;

import backend.academy.labyrinth.extraStructures.BaseObject;
import backend.academy.labyrinth.extraStructures.edge.Edge;
import java.util.List;

public interface Generator extends BaseObject {
    List<Edge> generate(int width, int height);
}
