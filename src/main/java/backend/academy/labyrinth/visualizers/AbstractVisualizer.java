package backend.academy.labyrinth.visualizers;

import backend.academy.labyrinth.maze.Cell;
import backend.academy.labyrinth.maze.Maze;
import java.util.List;

public abstract class AbstractVisualizer {

    protected String CORNER = "○";
    protected String TOP_BOT = "───";
    protected String EMPTY_TOP_BOT = "   ";
    protected String EMPTY_LEFT_RIGHT = " ";
    protected String LEFT_RIGHT = "│";
    protected String DEFAULT_CELL = EMPTY_TOP_BOT;
    protected String START_CELL = " A ";
    protected String END_CELL = " B ";

    public abstract String getCellInMaze(Cell cell);

    public String visualizeMaze(Maze maze) {
        StringBuilder itog = new StringBuilder();

        for (int i = 0; i < maze.maze().size(); i++) {
            List<Cell> row = maze.maze().get(i);
            if (i == 0) {
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

    protected StringBuilder getTopPart(List<Cell> row) {
        StringBuilder topPart = new StringBuilder();
        for (Cell cell : row) {
            topPart.append(CORNER);
            topPart.append(cell.topNeighbour() != null ? EMPTY_TOP_BOT : TOP_BOT);
        }
        topPart.append(CORNER);
        return topPart;
    }

    protected StringBuilder getMiddlePart(List<Cell> row) {
        StringBuilder middlePart = new StringBuilder();
        for (Cell cell : row) {
            middlePart.append(cell.leftNeighbour() != null ? EMPTY_LEFT_RIGHT : LEFT_RIGHT);
            switch (cell.type()) {
                case END -> middlePart.append(END_CELL);
                case START -> middlePart.append(START_CELL);
                case DEFAULT -> middlePart.append(getCellInMaze(cell));
                default -> middlePart.append(DEFAULT_CELL);
            }
        }
        middlePart.append(LEFT_RIGHT);
        return middlePart;
    }

    protected StringBuilder getBottomPart(List<Cell> row) {
        StringBuilder bottomPart = new StringBuilder();
        for (Cell cell : row) {
            bottomPart.append(CORNER);
            bottomPart.append(cell.bottomNeighbour() != null ? EMPTY_TOP_BOT : TOP_BOT);
        }
        bottomPart.append(CORNER);
        return bottomPart;
    }
}
