package algorithm;

import heuristics.valueSelection.ValueSelection;
import heuristics.variableSelection.VariableSelection;
import informations.IndividualSudoku;
import informations.PointSudoku;
import structures.Node;
import structures.Tree;

import java.util.ArrayList;

public class BacktrackingAlgotithmSudoku {

    Tree<IndividualSudoku> tree;
    IndividualSudoku individualSudoku;
    ValueSelection valueSelection;
    VariableSelection variableSelection;

    ArrayList<IndividualSudoku> solveSudoku = new ArrayList<IndividualSudoku>();


    public BacktrackingAlgotithmSudoku(ValueSelection valueSelection, VariableSelection variableSelection) {
        this.valueSelection = valueSelection;
        this.variableSelection = variableSelection;
    }

    public ArrayList<IndividualSudoku> findSolutions(IndividualSudoku individualSudoku) {
        this.individualSudoku = individualSudoku;
        tree = new Tree<IndividualSudoku>(new Node<IndividualSudoku>(individualSudoku));
        
        makeTree(tree.getRootNode(), individualSudoku);

        return solveSudoku;
    }

    public Node<IndividualSudoku> makeTree(Node<IndividualSudoku> node, IndividualSudoku individualSudoku) {
        PointSudoku variable = variableSelection.chooseVariable(individualSudoku);
        if (variable != null) {
            PointSudoku pointSudoku = variableSelection.chooseVariable(individualSudoku);

            int value = valueSelection.chooseValue(individualSudoku, pointSudoku);
            pointSudoku.deleteValueDomain(value);

            if (value != 0) {

                IndividualSudoku individualSudokuChild = null;
                try {
                    individualSudokuChild = individualSudoku.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                individualSudokuChild.setSingleElement(variable, value);
                if (individualSudoku.checkSudoku()) {
                    return makeTree(new Node<IndividualSudoku>(individualSudokuChild), individualSudokuChild);
                }
            } else {
                return null;
            }
        } else {
            if(individualSudoku.isFull() && individualSudoku.checkSudoku()) {
                solveSudoku.add(individualSudoku);
                return null;
            } else {
                return null;
            }
        }
        return null;
    }
}
