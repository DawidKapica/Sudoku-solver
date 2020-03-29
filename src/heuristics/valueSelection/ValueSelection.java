package heuristics.valueSelection;

import informations.IndividualSudoku;
import informations.PointSudoku;

public interface ValueSelection {

    int chooseValue (IndividualSudoku individualSudoku, PointSudoku pointSudoku);
}
