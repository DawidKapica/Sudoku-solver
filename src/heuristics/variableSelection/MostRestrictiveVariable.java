package heuristics.variableSelection;

import informations.IndividualSudoku;
import informations.PointSudoku;

//NIE ZAIMPLEMENTOWANE
public class MostRestrictiveVariable implements VariableSelection {

    public static final int MIN_SUDOKU_INDEX = 0;
    public static final int MAX_SUDOKU_INDEX = 8;


    @Override
    public PointSudoku chooseVariable (IndividualSudoku individualSudoku) {

        PointSudoku pointSudoku = null;
        int mostRestictiveDomainSize = Integer.MAX_VALUE;

        for (int i = MIN_SUDOKU_INDEX; i <= MAX_SUDOKU_INDEX; i++) {
            for (int j = MIN_SUDOKU_INDEX; j <= MAX_SUDOKU_INDEX; j++) {
                if (individualSudoku.getSingleElementValue(new PointSudoku(i, j)) == 0) {
                    if (individualSudoku.getSingleElement(new PointSudoku(i, j)).getDomainValues().size() < mostRestictiveDomainSize &&
                            individualSudoku.getSingleElement(new PointSudoku(i, j)).getDomainValues().size() != 0) {

                        pointSudoku = new PointSudoku(i, j);
                        mostRestictiveDomainSize = individualSudoku.getSingleElement(new PointSudoku(i, j)).getDomainValues().size();
                    }
                }
            }
        }
        return pointSudoku;
    }
}
