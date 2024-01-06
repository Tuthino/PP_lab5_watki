package lab5.models;

public class Cell {
    private int x;
    private int y;
    private int isAlive; // 0 - dead, 1 - alive
    private int nextCellstate;

    public Cell(int x, int y, int isAlive) {
        this.x = x;
        this.y = y;
        this.isAlive = isAlive;
    }

    // Creates deep copy of a
    public Cell(Cell cell) {
        this.x = cell.getX();
        this.y = cell.getY();
        this.isAlive = cell.isAlive();
    }

    public char getSymbol() {
        if (isAlive == 1) {
            return '@';
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

    public void setAliveState(int state) {
        this.isAlive = state;
    }

    public int getNextCellstate() {
        return nextCellstate;
    }

    public void setNextCellstate(int nextCellstate) {
        this.nextCellstate = nextCellstate;
    }

}
