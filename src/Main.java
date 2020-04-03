import algorithm.BacktrackingAlgotithmSudoku;
import algorithm.ForwardCheckingAlgorithmSudoku;
import heuristics.valueSelection.InOrderValue;
import heuristics.valueSelection.RandomValue;
import heuristics.variableSelection.InOrderVariable;
import heuristics.variableSelection.MostRestrictiveVariable;
import heuristics.variableSelection.RandomVariable;
import informations.IndividualSudoku;
import parser.ParserSudoku;
import parser.WriterToCsv;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        WriterToCsv writerToCsv = new WriterToCsv("SUDOKU");
        ParserSudoku parserSudoku = new ParserSudoku();
        IndividualSudoku individualSudoku = parserSudoku.parse("Sudoku.csv", 46);

        InOrderValue inOrderValue = new InOrderValue();
        InOrderVariable inOrderVariable = new InOrderVariable();

        MostRestrictiveVariable mostRestrictiveVariable = new MostRestrictiveVariable();
        RandomValue randomValue = new RandomValue();
        RandomVariable randomVariable = new RandomVariable();

        BacktrackingAlgotithmSudoku backtrackingAlgotithmSudoku = new BacktrackingAlgotithmSudoku(inOrderValue, inOrderVariable);
//        ForwardCheckingAlgorithmSudoku forwardCheckingAlgorithmSudoku = new ForwardCheckingAlgorithmSudoku(randomValue, mostRestrictiveVariable);

        ArrayList<IndividualSudoku> results = backtrackingAlgotithmSudoku.findSolutions(individualSudoku);
//        ArrayList<IndividualSudoku> results = forwardCheckingAlgorithmSudoku.findSolutions(individualSudoku);


        System.out.println("Liczba odwiedzonych węzłów: " + backtrackingAlgotithmSudoku.getNumberOfNodes());
        System.out.println("Czas znalezienia rozwiązań: " + backtrackingAlgotithmSudoku.getFindAllSolutionsTime());
        System.out.println("Liczba nawrtotów: " + backtrackingAlgotithmSudoku.getNumberOfReccurence());
        System.out.println("Liczba odwiedzonych węzłów do pierwszego rozwiązania: " + backtrackingAlgotithmSudoku.getNumberOfNodesFirstSol());
        System.out.println("Czas znalezienia pierwszego rozwiązania: " + backtrackingAlgotithmSudoku.getFindFirstSolTime());
        System.out.println("Liczba nawrotów pierwszego rozwiązania: " + backtrackingAlgotithmSudoku.getNumberOfReccurenceFirstSol());


        System.out.println("number of solutions " + results.size());
        if(results.size() == 0) {
            System.out.println("brak rozwiązań");
            writerToCsv.addTextToConvert("brak rozwiązań");
        } else {
            for (int i = 0; i < results.size(); i++) {
                writerToCsv.addTextToConvert("number of solutions " + results.size());
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
