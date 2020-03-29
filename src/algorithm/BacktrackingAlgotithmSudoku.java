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
    ValueSelection valueSelection;
    VariableSelection variableSelection;

    ArrayList<IndividualSudoku> solveSudoku = new ArrayList<IndividualSudoku>();


    public BacktrackingAlgotithmSudoku(ValueSelection valueSelection, VariableSelection variableSelection) {
        this.valueSelection = valueSelection;
        this.variableSelection = variableSelection;
    }

    public ArrayList<IndividualSudoku> findSolutions(IndividualSudoku individualSudoku) {
        tree = new Tree<IndividualSudoku>(new Node<IndividualSudoku>(individualSudoku));

        try {
            makeTree(tree.getRootNode());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return solveSudoku;
    }

//    private int iter = 0;

    public Node<IndividualSudoku> makeTree(Node<IndividualSudoku> node) throws CloneNotSupportedException {
        PointSudoku pointSudoku = variableSelection.chooseVariable(node.getData());

        IndividualSudoku individualSudoku = node.getData().clone();
        IndividualSudoku individualSudokuChild = individualSudoku.clone();

        if(pointSudoku != null) {
        while (individualSudoku.getSingleElement(pointSudoku).getDomainValues().size()!= 0) {
//            iter = iter + 1;
//            System.out.println(iter);

                int value = valueSelection.chooseValue(individualSudoku, pointSudoku);

                individualSudoku.getSingleElement(pointSudoku).deleteValueDomain(value);
                individualSudokuChild.setSingleElement(pointSudoku, value);

                if (individualSudokuChild.checkSudoku() && individualSudokuChild.isFull()) {
                    Node<IndividualSudoku> nodeChild = new Node<IndividualSudoku>(individualSudokuChild);
                    nodeChild.setParent(node);
                    solveSudoku.add(individualSudokuChild);

                    return nodeChild;
                } else if (individualSudokuChild.checkSudoku()) {
                    Node<IndividualSudoku> nodeChild = new Node<IndividualSudoku>(individualSudokuChild);
                    nodeChild.setParent(node);
                    node.addNodeChild(makeTree(nodeChild));

                } else {

                }

            }
        } else {
            return node;
        }
        return node;
    }

}
