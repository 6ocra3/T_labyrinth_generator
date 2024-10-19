package backend.academy.labyrinth.maze;

import lombok.Getter;

@Getter
enum SurfaceType {
    BadSurface(3),
    DefaultSurface(2),
    GoodSurface(1);

    private final int value;

    int value(){
        return value;
    }

    SurfaceType(int value) {
        this.value = value;
    }
}
