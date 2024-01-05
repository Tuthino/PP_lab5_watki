package lab5.models;

public class Cell {
    private int x;
    private int y;
    private int isAlive; //  0 - dead, 1 - alive
    private boolean isNeighbour;
    private String character = "|"; // for testing purposes

    public Cell(int x, int y, int isAlive, boolean isNeighbour){
        this.x = x;
        this.y = y;
        this.isAlive = isAlive;
        this.isNeighbour = isNeighbour;
    }

    public char getSymbol() {
        if (isAlive==1) {
            return '@';
        } else if (isNeighbour) {
            return 'X';
        } else {
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

    public void setAliveState(boolean state) {
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
