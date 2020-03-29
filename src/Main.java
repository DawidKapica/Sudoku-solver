import algorithm.BacktrackingAlgotithmSudoku;
import heuristics.valueSelection.InOrderValue;
import heuristics.variableSelection.InOrderVariable;
import informations.IndividualSudoku;
import parser.ParserSudoku;
import parser.WriterToCsv;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        WriterToCsv writerToCsv = new WriterToCsv();
        ParserSudoku parserSudoku = new ParserSudoku();
        IndividualSudoku individualSudoku = parserSudoku.parse("Sudoku.csv", 43);

        InOrderValue inOrderValue = new InOrderValue();
        InOrderVariable inOrderVariable = new InOrderVariable();

        BacktrackingAlgotithmSudoku backtrackingAlgotithmSudoku = new BacktrackingAlgotithmSudoku(inOrderValue, inOrderVariable);
        ArrayList<IndividualSudoku> results = backtrackingAlgotithmSudoku.findSolutions(individualSudoku);

        System.out.println(results.size());
        if(results.size() == 0) {
            System.out.println("brak rozwiązań");
        } else {
            for (int i = 0; i < results.size(); i++) {
                writerToCsv.addTextToConvert(results.get(i).toString());
                System.out.println(results.get(i).toString());
            }
        }
        try {
            writerToCsv.createCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
