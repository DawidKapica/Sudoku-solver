package heuristics.variableSelection;

import informations.IndividualSudoku;
import informations.PointSudoku;

public class InOrderVariable implements VariableSelection {

    public static final int MIN_SUDOKU_INDEX = 0;
    public static final int MAX_SUDOKU_INDEX = 8;



    public PointSudoku chooseVariable(IndividualSudoku individualSudoku) {

        for (int i = MIN_SUDOKU_INDEX; i <= MAX_SUDOKU_INDEX; i++) {
            for (int j = MIN_SUDOKU_INDEX; j <= MAX_SUDOKU_INDEX; j++) {
                if (individualSudoku.getSingleElementValue(new PointSudoku(i, j)) == 0) {
                    return new PointSudoku(i, j);
                }
            }
        }
        return null;
    }
}
