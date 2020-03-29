package heuristics.variableSelection;

import informations.IndividualSudoku;
import informations.PointSudoku;

public interface VariableSelection {

    PointSudoku chooseVariable (IndividualSudoku individualSudoku);
}
