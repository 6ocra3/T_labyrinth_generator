package backend.academy.labyrinth.maze;

import lombok.Getter;

@Getter
enum SurfaceType {
    BadSurface(5),
    DefaultSurface(3),
    GoodSurface(1);

    private final int value;

    int value() {
        return value;
    }

    SurfaceType(int value) {
        this.value = value;
    }
}
