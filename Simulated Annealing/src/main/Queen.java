package main;

public class Queen {
    private int row;
    private int column;

    public Queen(int r, int c) {
        row = r;
        column = c;
    }

    public boolean canAttack(Queen q) {
        boolean canAttack=false;

        //test rows and columns
        if(row == q.getRow() || column == q.getColumn()) {
            canAttack=true;
        }

        //test diagonal
        else if(Math.abs(column - q.getColumn()) == Math.abs(row - q.getRow())) {
            canAttack=true;
        }

        return canAttack;
    }

    public void moveDown(int spaces) {
        row += spaces;

        if (row > 7 && row % 7 !=0){
            row = (row % 7) - 1;
        }
        else if (row > 7 && row % 7 == 0){
            row=7;
        }
    }

    public void setRow(int r) {
        row = r;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int c) {
        column = c;
    }

    public int getColumn() {
        return column;
    }

    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
