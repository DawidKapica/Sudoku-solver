package algorithm;

import com.sun.jdi.Value;
import heuristics.valueSelection.ValueSelection;
import heuristics.variableSelection.VariableSelection;
import informations.IndividualSudoku;
import informations.PointSudoku;
import structures.Node;
import structures.Tree;

import java.util.ArrayList;

public class ForwardCheckingAlgorithmSudoku {

    Tree<IndividualSudoku> tree;

    ValueSelection valueSelection;
    VariableSelection variableSelection;

    ArrayList<IndividualSudoku> solveSudoku = new ArrayList<IndividualSudoku>();

    public ForwardCheckingAlgorithmSudoku(ValueSelection valueSelection, VariableSelection variableSelection) {
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
//                System.out.println(individualSudokuChild.toString());
                individualSudokuChild.setSingleElement(pointSudoku, value);


                if (individualSudokuChild.checkSudoku() && individualSudokuChild.isFull()) {
                    Node<IndividualSudoku> nodeChild = new Node<IndividualSudoku>(individualSudokuChild);
                    nodeChild.setParent(node);
                    solveSudoku.add(individualSudokuChild);

                    return nodeChild;
                } else if (individualSudokuChild.checkSudoku()) {
                    individualSudokuChild = deleteDomainValuesGrid(individualSudokuChild, pointSudoku, value);
                    System.out.println(individualSudoku.isHasFieldNullDomain());
                    if(individualSudokuChild.isHasFieldNullDomain() == false) {
                        Node<IndividualSudoku> nodeChild = new Node<IndividualSudoku>(individualSudokuChild);
                        nodeChild.setParent(node);
                        node.addNodeChild(makeTree(nodeChild));
//                        System.out.println(individualSudoku.toString());
//                        System.out.println(individualSudokuChild.isHasFieldNullDomain());
                    } else {
                        return node;
                    }
                }
            }
        } else {
            return node;
        }
        return node;
    }

    public IndividualSudoku deleteDomainValuesGrid(IndividualSudoku individualSudoku, PointSudoku pointSudoku, int value) {
        IndividualSudoku individualSudokuCopy = null;
        try {
            individualSudokuCopy = individualSudoku.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        for (int i = pointSudoku.getVerticalIndex(); i <= pointSudoku.getVerticalIndex() + 2; i++) {
            for (int j = pointSudoku.getHorizontalIndex(); j <= pointSudoku.getHorizontalIndex() + 2; j++) {
                individualSudokuCopy.getSingleElement(new PointSudoku(i, j)).deleteValueDomain(value);
                if (individualSudokuCopy.getSingleElement(new PointSudoku(i, j)).getDomainValues().size() == 0) {
                    individualSudokuCopy.setHasFieldNullDomain(true);
                    return individualSudoku;
                }
            }
        }
        return individualSudokuCopy;
    }

    public IndividualSudoku deleteDomainValuesRow(IndividualSudoku individualSudoku, PointSudoku pointSudoku, int value) {
        IndividualSudoku individualSudokuCopy = null;
        try {
            individualSudokuCopy = individualSudoku.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <=8; i++) {
            individualSudokuCopy.getSingleElement(new PointSudoku(pointSudoku.getVerticalIndex(), i)).deleteValueDomain(value);
            if (individualSudokuCopy.getSingleElement(new PointSudoku(pointSudoku.getVerticalIndex(), i)).getDomainValues().size() == 0) {
                individualSudokuCopy.setHasFieldNullDomain(true);
                return individualSudoku;
            }
        }
        return individualSudokuCopy;
    }

    public IndividualSudoku deleteDomainValuesColumn(IndividualSudoku individualSudoku, PointSudoku pointSudoku, int value) {
        IndividualSudoku individualSudokuCopy = null;
        try {
            individualSudokuCopy = individualSudoku.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <=8; i++) {
            individualSudokuCopy.getSingleElement(new PointSudoku(i, pointSudoku.getHorizontalIndex())).deleteValueDomain(value);
            if (individualSudokuCopy.getSingleElement(new PointSudoku(i, pointSudoku.getHorizontalIndex())).getDomainValues().size() == 0) {
                individualSudokuCopy.setHasFieldNullDomain(true);
                return individualSudoku;
            }
        }

        return individualSudokuCopy;
    }
}
