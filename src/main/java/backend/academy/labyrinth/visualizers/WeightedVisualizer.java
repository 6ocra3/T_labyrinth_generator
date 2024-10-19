package backend.academy.labyrinth.visualizers;

import backend.academy.labyrinth.maze.Cell;

public class WeightedVisualizer extends AbstractVisualizer {

    public WeightedVisualizer() {
        this.corner = "○";
        this.topBot = "─────";
        this.emptyTopBot = "     ";
        this.defaultCell = this.emptyTopBot;
        this.startCell = "  A  ";
        this.endCell = "  B  ";
    }

    @Override
    public String getCellInMaze(Cell cell) {
        return String.format("%d %" + (defaultCell.length() - 2) + "d", cell.getSurfaceModifier(), cell.pathCost());
    }
}
