import algorithm.BacktrackingAlgotithmSudoku;
import heuristics.valueSelection.InOrderValue;
import heuristics.variableSelection.InOrderVariable;
import informations.IndividualSudoku;
import parser.ParserSudoku;

public class Main {

    public static void main(String[] args) {
        ParserSudoku parserSudoku = new ParserSudoku();
        IndividualSudoku individualSudoku = parserSudoku.parse("Sudoku.csv", 1);

        System.out.println(individualSudoku.toString());
        System.out.println("_____+++++");

        InOrderValue inOrderValue = new InOrderValue();
        InOrderVariable inOrderVariable = new InOrderVariable();

        BacktrackingAlgotithmSudoku backtrackingAlgotithmSudoku = new BacktrackingAlgotithmSudoku(inOrderValue, inOrderVariable);
        backtrackingAlgotithmSudoku.findSolutions(individualSudoku);
    }
}
