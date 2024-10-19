package backend.academy.labyrinth.visualizers;

import backend.academy.labyrinth.maze.Cell;
import backend.academy.labyrinth.maze.Maze;
import java.util.List;

public abstract class AbstractVisualizer {

    protected String corner = "○";
    protected String topBot = "───";
    protected String emptyTopBot = "   ";
    protected String emptyLeftRight = " ";
    protected String leftRight = "│";
    protected String defaultCell = emptyTopBot;
    protected String startCell = " A ";
    protected String endCell = " B ";

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
            topPart.append(corner);
            topPart.append(cell.topNeighbour() != null ? emptyTopBot : topBot);
        }
        topPart.append(corner);
        return topPart;
    }

    protected StringBuilder getMiddlePart(List<Cell> row) {
        StringBuilder middlePart = new StringBuilder();
        for (Cell cell : row) {
            middlePart.append(cell.leftNeighbour() != null ? emptyLeftRight : leftRight);
            switch (cell.type()) {
                case END -> middlePart.append(endCell);
                case START -> middlePart.append(startCell);
                case DEFAULT -> middlePart.append(getCellInMaze(cell));
                default -> middlePart.append(defaultCell);
            }
        }
        middlePart.append(leftRight);
        return middlePart;
    }

    protected StringBuilder getBottomPart(List<Cell> row) {
        StringBuilder bottomPart = new StringBuilder();
        for (Cell cell : row) {
            bottomPart.append(corner);
            bottomPart.append(cell.bottomNeighbour() != null ? emptyTopBot : topBot);
        }
        bottomPart.append(corner);
        return bottomPart;
    }
}
