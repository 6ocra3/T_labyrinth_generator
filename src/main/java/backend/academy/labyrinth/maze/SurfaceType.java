package backend.academy.labyrinth.maze;

import lombok.Getter;

enum SurfaceType {
    BadSurface(3),
    DefaultSurface(2),
    GoodSurface(1);

    @Getter
    private final int value;

    SurfaceType(int value) {
        this.value = value;
    }
}
