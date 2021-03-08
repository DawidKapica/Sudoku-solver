package informations;

import java.util.ArrayList;
import java.util.List;

public class IndividualSudokuFillInfomration {



    ArrayList<Integer> freeVariablePlaceRow = new ArrayList<Integer>(List.of(9, 9, 9, 9, 9, 9, 9, 9, 9));
    ArrayList<Integer> freeVariablePlaceColumn = new ArrayList<Integer>(List.of(9, 9, 9, 9, 9, 9, 9, 9, 9));
    ArrayList<Integer> freeVariablePlaceGrid = new ArrayList<Integer>(List.of(9, 9, 9, 9, 9, 9, 9, 9, 9));


    public int chooseMostFillRow() {
        int mostFillIndex = Integer.MAX_VALUE;
        int mostfillValue = Integer.MAX_VALUE;
        for(int i = 0; i <= 8; i++) {
            if (freeVariablePlaceRow.get(i) < mostfillValue && freeVariablePlaceRow.get(i) != 0) {
                mostFillIndex = i;
                mostfillValue = freeVariablePlaceRow.get(i);
            }
        }

        return mostFillIndex;
    }

    public int chooseMostFillColumn() {
        int mostFillIndex = Integer.MAX_VALUE;
        int mostfillValue = Integer.MAX_VALUE;
        for(int i = 0; i <= 8; i++) {
            if (freeVariablePlaceColumn.get(i) < mostfillValue && freeVariablePlaceColumn.get(i) != 0) {
                mostFillIndex = i;
                mostfillValue = freeVariablePlaceRow.get(i);
            }
        }

        return mostFillIndex;
    }

    public int chooseMostFillGrid() {
        int mostFillIndex = Integer.MAX_VALUE;
        int mostfillValue = Integer.MAX_VALUE;
        for(int i = 0; i <= 8; i++) {
            if (freeVariablePlaceGrid.get(i) < mostfillValue && freeVariablePlaceGrid.get(i) != 0) {
                mostFillIndex = i;
                mostfillValue = freeVariablePlaceRow.get(i);
            }
        }

        return mostFillIndex;
    }

    public void decrementFreeVariableRow(int index) {
        freeVariablePlaceRow.set(index, (freeVariablePlaceRow.get(index)-1));
    }

    public ArrayList<Integer> getFreeVariablePlaceRow () {
        return freeVariablePlaceRow;
    }

    public void setFreeVariablePlaceRow (int index, int value) {
        freeVariablePlaceRow.set(index, value);
    }

    public ArrayList<Integer> getFreeVariablePlaceColumn () {
        return freeVariablePlaceColumn;
    }

    public void setFreeVariablePlaceColumn (ArrayList<Integer> freeVariablePlaceColumn) {
        this.freeVariablePlaceColumn = freeVariablePlaceColumn;
    }

    public ArrayList<Integer> getFreeVariablePlaceGrid () {
        return freeVariablePlaceGrid;
    }

    public void setFreeVariablePlaceGrid (ArrayList<Integer> freeVariablePlaceGrid) {
        this.freeVariablePlaceGrid = freeVariablePlaceGrid;
    }
}
