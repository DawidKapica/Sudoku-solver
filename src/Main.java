import algorithm.BacktrackingAlgotithmSudoku;
import heuristics.valueSelection.InOrderValue;
import heuristics.variableSelection.InOrderVariable;
import informations.IndividualSudoku;
import parser.ParserSudoku;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ParserSudoku parserSudoku = new ParserSudoku();
        IndividualSudoku individualSudoku = parserSudoku.parse("Sudoku.csv", 1);

        System.out.println(individualSudoku);
        InOrderValue inOrderValue = new InOrderValue();
        InOrderVariable inOrderVariable = new InOrderVariable();

        BacktrackingAlgotithmSudoku backtrackingAlgotithmSudoku = new BacktrackingAlgotithmSudoku(inOrderValue, inOrderVariable);
        ArrayList<IndividualSudoku> x = backtrackingAlgotithmSudoku.findSolutions(individualSudoku);
        System.out.println(x.size());
    }
}
