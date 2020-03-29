package heuristics.valueSelection;

import informations.IndividualSudoku;
import informations.PointSudoku;

import java.util.Collections;

public class InOrderValue implements ValueSelection {

    private static final int EMPTY_SUDOKU_FIELD = 0;
    public int chooseValue(IndividualSudoku individualSudoku,  PointSudoku pointSudoku) {

        if (pointSudoku.getDomainValues().size() != 0) {
            Collections.sort(pointSudoku.getDomainValues());
            return pointSudoku.getDomainValues().get(0);
        } else {
            return EMPTY_SUDOKU_FIELD;
        }
    }
}
