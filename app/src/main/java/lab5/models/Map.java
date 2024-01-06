package lab5.models;

import java.util.ArrayList;

public class Map {
    int width;
    int height;
    Cell[][] cellArray;

    public Map(Map map) {
        this.width = map.getWidth();
        this.height = map.getHeight();
        this.cellArray = new Cell[this.width][this.height];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.cellArray[j][i] = new Cell(map.getCell(i, j));
            }
        }
    }

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.cellArray = initializeArray();
    }

    private Cell[][] initializeArray() {
        Cell[][] cellArray = new Cell[this.width][this.height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cellArray[i][j] = new Cell(j, i, 0);
            }
        }
        return cellArray;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell[][] getCellArray() {
        return cellArray;
    }

    public Cell getCell(int x, int y) {
        return cellArray[y][x];
    }

    public Map setCellArray(Cell[][] cellArray) {
        this.cellArray = cellArray;
        return this;
    }

    public int getNeighbours(int x, int y, int width, int height) {
        int aliveNeighbours = 0;
        // left
        aliveNeighbours += this.getCell(((x - 1 + width) % width), y).isAlive();
        // right
        aliveNeighbours += this.getCell(((x + 1 + width) % width), y).isAlive();
        // up
        aliveNeighbours += this.getCell(x, ((y - 1 + height) % height)).isAlive();
        // down
        aliveNeighbours += this.getCell(x, ((y + 1 + height) % height)).isAlive();
        // up left
        aliveNeighbours += this.getCell(((x - 1 + width) % width), ((y - 1 + height) % height)).isAlive();

        // up right
        aliveNeighbours += this.getCell(((x + 1 + width) % width), ((y - 1 + height) % height)).isAlive();

        // down left
        aliveNeighbours += this.getCell(((x - 1 + width) % width), ((y + 1 + height) % height)).isAlive();
        // down right
        aliveNeighbours += this.getCell(((x + 1 + width) % width), ((y + 1 + height) % height)).isAlive();

        return aliveNeighbours;
    }

    // ---- Evolution ----

    public void setCellState(int x, int y, int state) {
        this.cellArray[y][x].setAliveState(state);
    }

    public int nextCellstate(Cell cell) {
        int aliveNeighbours = getNeighbours(cell.getX(), cell.getY(), this.getWidth(), this.getHeight());
        if (cell.isAlive() == 1) {
            if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (aliveNeighbours == 3) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    // ---- END Evolution ----

    public void printMap() {
        Cell[][] cellArray = this.getCellArray();
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                System.out.print(cellArray[i][j].getSymbol());
            }
            System.out.println();
        }
    }
}
