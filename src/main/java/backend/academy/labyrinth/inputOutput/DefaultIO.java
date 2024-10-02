package backend.academy.labyrinth.inputOutput;

import backend.academy.labyrinth.maze.Cell;
import backend.academy.labyrinth.maze.Maze;
import backend.academy.labyrinth.visualizers.DefaultVisualizer;
import java.util.List;

public class DefaultIO {
    public DefaultIO(){

    }

    public void visualizeMaze(Maze maze){
        System.out.println(DefaultVisualizer.visualizeMaze(maze));
    }



}
