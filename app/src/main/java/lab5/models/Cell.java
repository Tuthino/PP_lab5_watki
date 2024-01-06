package lab5.models;

public class Cell {
    private int x;
    private int y;
    private int isAlive; //  0 - dead, 1 - alive
    private boolean isNeighbour = false;
    private String character = "|"; // for testing purposes

    public Cell(int x, int y, int isAlive){
        this.x = x;
        this.y = y;
        this.isAlive = isAlive;
    }

    // Creates deep copy of a Cell
    public Cell(Cell cell) {
        this.x = cell.getX();
        this.y = cell.getY();
        this.isAlive = cell.isAlive();
        this.isNeighbour = cell.isNeighbour();
        this.character = cell.getCharacter();
    }

    public char getSymbol() {
        if (isAlive==1) {
            return '@';
        }else if (isNeighbour) {
            return 'X';} 
        else {
            return '-';
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



    public int isAlive() {
        return isAlive;
    }

    public void setAliveState(int state) {
        this.isAlive = state;
    }

    public boolean isNeighbour() {
        return isNeighbour;
    }

    public void setNeighbourState(boolean state) {
        this.isNeighbour = state;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
    }


}
