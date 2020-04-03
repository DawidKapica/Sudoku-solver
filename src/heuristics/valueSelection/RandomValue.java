package heuristics.valueSelection;

import informations.IndividualSudoku;
import informations.PointSudoku;

import java.util.Collections;
import java.util.Random;

public class RandomValue implements ValueSelection {

    private static final int EMPTY_SUDOKU_FIELD = 0;

    @Override
    public int chooseValue (IndividualSudoku individualSudoku, PointSudoku pointSudoku) {
        Random r = new Random();

        if (pointSudoku.getDomainValues().size() != 0) {
            return individualSudoku.getSingleElement(pointSudoku).getDomainValues().
                    get(r.nextInt((individualSudoku.getSingleElement(pointSudoku).getDomainValues().size())));
        } else {
            return EMPTY_SUDOKU_FIELD;
        }
    }
}
