package backend.academy.labyrinth.generators;

import backend.academy.labyrinth.extraStructures.BaseObject;

public interface Generator extends BaseObject {
    void generate(int width, int height);
}
