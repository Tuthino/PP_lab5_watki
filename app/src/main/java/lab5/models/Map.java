package lab5.models;
import java.util.ArrayList;


public class Map {
    int width;
    int height;
    Cell[][] cellArray;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.cellArray = initializeArray();
    } 

    private Cell[][] initializeArray() {
        Cell[][] cellArray = new Cell[this.width][this.height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cellArray[i][j] = new Cell(i, j, false, false);
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
        return cellArray[x][y];
    }

    public Map setCellArray(Cell[][] cellArray) {
        this.cellArray = cellArray;
        return this;
    }




    public int getNeighbours(Cell[][] cellArray, int x, int y, int width, int height) {
        int aliveNeighbours = 0;
        //left
        aliveNeighbours += this.getCell(((x - 1 + width) % width),y).setNeighbourState(state);

        //right
        aliveNeighbours += this.getCell(( (x + 1 + width) % width), y).setNeighbourState(state);
        //up
        aliveNeighbours += this.getCell(x,( (y - 1 + height) % height)).setNeighbourState(state);
        //down
        aliveNeighbours += this.getCell( x, ((y + 1 + height) % height)).setNeighbourState(state);
        //up left
        aliveNeighbours += this.getCell(( (x - 1 + width) % width), ((y - 1 + height) % height)).setNeighbourState(state);
        //up right
        aliveNeighbours += this.getCell(( (x + 1 + width) % width),((y - 1 + height) % height)).setNeighbourState(state);
        //down left
        aliveNeighbours += this.getCell(( (x - 1 + width) % width),((y + 1 + height) % height)).setNeighbourState(state);
        //down right
        aliveNeighbours += this.getCell(( (x + 1 + width) % width),((y + 1 + height) % height)).setNeighbourState(state);        
    
    }

    // ---- Evolution ----

    public boolean willBeAlive(Cell cell) {
        int aliveNeighbours = 0;
        if (cell.isNeighbour()) {
            aliveNeighbours++;
        }
        if (cell.isAlive()) {
            aliveNeighbours++;
        }
        if (aliveNeighbours == 3) {
            return true;
        } else if (aliveNeighbours == 2 && cell.isAlive()) {
            return true;
        } else {
            return false;
        }
    }


    // ---- END Evolution ----




    public void printMap() {
        Cell[][] cellArray = this.getCellArray();
        for (int i = 0; i < this.getHeight() ; i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                System.out.print(cellArray[i][j].getSymbol());
            }
            System.out.println();
        }
    }





    public void printTestMap() {
        Cell[][] cellArray = this.getCellArray();
        for (int i = 0; i < this.getHeight() ; i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                System.out.print(cellArray[i][j].getCharacter());
            }
            System.out.println();
        }
    }
}
