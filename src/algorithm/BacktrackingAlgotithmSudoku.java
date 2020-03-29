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
//        this.individualSudoku = individualSudoku;
        tree = new Tree<IndividualSudoku>(new Node<IndividualSudoku>(individualSudoku));

        try {
            makeTree(tree.getRootNode());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return solveSudoku;
    }



    public Node<IndividualSudoku> makeTree(Node<IndividualSudoku> node) throws CloneNotSupportedException {
//        PointSudoku variable = variableSelection.chooseVariable(individualSudoku);
        PointSudoku pointSudoku = variableSelection.chooseVariable(node.getData());


        IndividualSudoku individualSudoku = node.getData().clone();

        IndividualSudoku individualSudokuChild = individualSudoku.clone();


        while (individualSudoku.getSingleElement(pointSudoku).getDomainValues().size()!= 0) {
            int value = valueSelection.chooseValue(individualSudoku, pointSudoku);

            individualSudoku.getSingleElement(pointSudoku).deleteValueDomain(value);
            individualSudokuChild.setSingleElement(pointSudoku, value);
//            individualSudokuChild.getSingleElement(pointSudoku).setDomainValues(individualSudoku.getSingleElement(pointSudoku).getDomainValues());

            if (individualSudokuChild.checkSudoku() && individualSudokuChild.isFull()) {
                Node<IndividualSudoku> nodeChild = new Node<IndividualSudoku>(individualSudokuChild);
                nodeChild.setParent(node);
                solveSudoku.add(individualSudokuChild);
                return nodeChild;
            } else if (individualSudokuChild.checkSudoku()) {
                Node<IndividualSudoku> nodeChild = new Node<IndividualSudoku>(individualSudokuChild);
                nodeChild.setParent(node);
                node.addNodeChild(makeTree(nodeChild));
                return node;
            } else {

            }
        }
        return node;
    }

//    public Node<IndividualSudoku> makeTree(Node<IndividualSudoku> node, IndividualSudoku individualSudoku) {
//        PointSudoku variable = variableSelection.chooseVariable(individualSudoku);
//        if (variable != null) {
//            PointSudoku pointSudoku = variableSelection.chooseVariable(individualSudoku);
//            while (pointSudoku.getDomainValues().size() != 0) {
//                int value = valueSelection.chooseValue(individualSudoku, pointSudoku);
//                pointSudoku.deleteValueDomain(value);
//                if (value != 0) {
//
//                    IndividualSudoku individualSudokuChild = null;
//                    try {
//                        individualSudokuChild = individualSudoku.clone();
//                    } catch (CloneNotSupportedException e) {
//                        e.printStackTrace();
//                    }
//                    individualSudokuChild.setSingleElement(variable, value);
//                    if (individualSudokuChild.checkSudoku()) {
////                        node.addChild(individualSudokuChild);
//                        node.addNodeChild(makeTree(new Node<IndividualSudoku>(node), individualSudokuChild));
//                        System.out.println("sss");
////                        return makeTree(new Node<IndividualSudoku>(individualSudokuChild), individualSudokuChild);
//                    }
//
//                } else {
//                    return node;
//                }
//            }
//        } else {
//            if(individualSudoku.isFull() && individualSudoku.checkSudoku()) {
//                solveSudoku.add(individualSudoku);
//                return node;
//            }
//        }
//        return node;
//    }

//    public Node<IndividualSudoku> makeTree(Node<IndividualSudoku> node) {
//        PointSudoku pointSudoku = variableSelection.chooseVariable(individualSudoku);
//        for (int i = 1; i <= 9; i++) {
//
//        }
//    }


//    public Node<IndividualSudoku> makeTreeHelper(Node<IndividualSudoku> node) {
//        PointSudoku pointSudoku = variableSelection.chooseVariable(node.getData());
//        int value = valueSelection.chooseValue(node.getData(), pointSudoku);
//
//        pointSudoku.deleteValueDomain(value);
//        node.getData().getSingleElement(pointSudoku).deleteValueDomain(value);
//        while (node.getData().getSingleElement(pointSudoku).getDomainValues().size() != 0) {
//
//
//            IndividualSudoku individualSudoku = node.getData();
//            IndividualSudoku individualSudokuChild = null;
//            try {
//                individualSudokuChild = individualSudoku.clone();
//            } catch (CloneNotSupportedException e) {
//                e.printStackTrace();
//            }
//
//            individualSudokuChild.setSingleElement(pointSudoku, value);
//
//            if (individualSudokuChild.isFull() && individualSudokuChild.checkSudoku()) {
//                solveSudoku.add(individualSudokuChild);
//                return node;
//            } else if (individualSudokuChild.checkSudoku() == true) {
//
//
//////                return node;
////                node.addChild(individualSudokuChild);
////                System.out.println(individualSudoku.toString());
////                System.out.println("___");
//                node.addNodeChild(makeTreeHelper(new Node<IndividualSudoku>(node)));
//
//            } else {
//                System.out.println(individualSudoku.toString());
//                System.out.println("___");
//                System.out.println(node.getData().getSingleElement(pointSudoku).getDomainValues().toString());
//            }
//        }
//        return node;
//    }

}
