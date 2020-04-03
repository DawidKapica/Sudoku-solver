package heuristics.variableSelection;

import informations.IndividualSudoku;
import informations.PointSudoku;

import java.util.Random;


//NIE ZAIMPLEMENTOWANE
public class RandomVariable implements VariableSelection {

    public static final int MAX_SUDOKU_INDEX = 8;

    @Override
    public PointSudoku chooseVariable (IndividualSudoku individualSudoku) {
        PointSudoku pointSudoku = null;
        Random r = new Random();
        while (pointSudoku == null) {
            int i = r.nextInt(MAX_SUDOKU_INDEX + 1);
            int j = r.nextInt(MAX_SUDOKU_INDEX + 1);

            if (individualSudoku.getSingleElementValue(new PointSudoku(i, j)) == 0) {
                pointSudoku = new PointSudoku(i, j);
//                System.out.println("JEST");
//                System.out.println(pointSudoku);
//                System.out.println(individualSudoku);
                break;
            } else {
//                System.out.println("NIE MA");
            }
        }
        return pointSudoku;
    }
}
