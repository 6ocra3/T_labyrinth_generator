package backend.academy.labyrinth.visualizers;

import backend.academy.labyrinth.maze.Cell;
import backend.academy.labyrinth.maze.Maze;
import java.util.List;

public class DefaultVisualizer {

    static String CORNER = "○";
    static String TOP_BOT = "───";
    static String EMPTY_TOP_BOT = "   ";
    static String EMPTY_LEFT_RIGHT = " ";
    static String LEFT_RIGHT = "│";
    static String CELL = "   ";

    public DefaultVisualizer(){

    }

    public static String visualizeMaze(Maze maze){
        StringBuilder itog = new StringBuilder();

        for(int i = 0; i<maze.maze().size();i++){
            List<Cell> row = maze.maze().get(i);
            if(i == 0){
                String topPart = getTopPart(row).append("\n").toString();
                itog.append(topPart);
            }

            String middlePart = getMiddlePart(row).append("\n").toString();
            itog.append(middlePart);

            String botPart = getBottomPart(row).append("\n").toString();
            itog.append(botPart);
        }

        return itog.toString();
    }

    public static StringBuilder getTopPart(List<Cell> row){
        StringBuilder topPart = new StringBuilder();
        for (Cell cell : row) {
            topPart.append(CORNER);
            topPart.append(cell.topNeightbour() != null ? EMPTY_TOP_BOT : TOP_BOT);
        }
        topPart.append(CORNER);
        return topPart;
    }

    public static StringBuilder getMiddlePart(List<Cell> row){
        StringBuilder middlePart = new StringBuilder();
        for (Cell cell : row) {
            middlePart.append(cell.leftNeightbour() != null ? EMPTY_LEFT_RIGHT : LEFT_RIGHT);
            middlePart.append(CELL);
        }
        middlePart.append(LEFT_RIGHT);
        return middlePart;
    }

    public static StringBuilder getBottomPart(List<Cell> row){
        StringBuilder bottomPart = new StringBuilder();
        for (Cell cell : row) {
            bottomPart.append(CORNER);
            bottomPart.append(cell.bottomNeightbour() != null ? EMPTY_TOP_BOT : TOP_BOT);
        }
        bottomPart.append(CORNER);
        return bottomPart;
    }

}
