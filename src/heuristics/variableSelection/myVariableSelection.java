package heuristics.variableSelection;

import informations.IndividualSudoku;
import informations.PointSudoku;

public class myVariableSelection implements VariableSelection{


    @Override
    public PointSudoku chooseVariable (IndividualSudoku individualSudoku) {

        int mostFillRowIndex = individualSudoku.getIndividualSudokuFillInfomration().chooseMostFillRow();
        int mostFillRowValue = individualSudoku.getIndividualSudokuFillInfomration().getFreeVariablePlaceRow().get(mostFillRowIndex);

        int mostFillColumnIndex = individualSudoku.getIndividualSudokuFillInfomration().chooseMostFillColumn();
        int mostFillColumnValue = individualSudoku.getIndividualSudokuFillInfomration().getFreeVariablePlaceColumn().get(mostFillColumnIndex);

        int mostFillGridIndex = individualSudoku.getIndividualSudokuFillInfomration().chooseMostFillGrid();
        int mostFillGridValue = individualSudoku.getIndividualSudokuFillInfomration().getFreeVariablePlaceGrid().get(mostFillGridIndex);

        if (mostFillRowValue <= mostFillColumnValue && mostFillRowValue <= mostFillColumnValue) {
            for (int i = 0; i < mostFillRowIndex; i++) {
                if (individualSudoku.getSingleElementValue(new PointSudoku(mostFillRowIndex, i)) == 0) {
                    return new PointSudoku(mostFillRowIndex, i);
                }
            }
        } else if (mostFillColumnValue <= mostFillRowValue && mostFillColumnValue <= mostFillGridValue) {
            for (int i = 0; i < mostFillColumnIndex; i++) {
                if (individualSudoku.getSingleElementValue(new PointSudoku(i, mostFillColumnIndex)) == 0) {
                    return new PointSudoku(i, mostFillColumnIndex);
                }
            }
        } else {
            //DOKONCZYC
            for (int i = 0; i < mostFillColumnIndex; i++) {
                if (individualSudoku.getSingleElementValue(new PointSudoku(i, mostFillColumnIndex)) == 0) {
                    return new PointSudoku(i, mostFillColumnIndex);
                }
            }
        }

        return null;


    }
}
