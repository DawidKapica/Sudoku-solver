package informations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IndividualSudoku implements Cloneable {

    ArrayList<ArrayList<PointSudoku>> sudokuBoard;
    public static final int MIN_SUDOKU_INDEX = 0;
    public static final int MAX_SUDOKU_INDEX = 8;
    public static final int MIN_SUDOKU_VALUE = 1;
    public static final int MAX_SUDOKU_VALUE = 9;
    public static final int EMPTY_FIELD_VALUE = 0;

    private boolean isCorrect = true;

    private boolean hasFieldNullDomain = false;

    public IndividualSudoku () {
        sudokuBoard = new ArrayList<ArrayList<PointSudoku>>();
        for (int i = MIN_SUDOKU_INDEX; i <= MAX_SUDOKU_INDEX; i++) {
            for (int j = MIN_SUDOKU_INDEX; j <= MAX_SUDOKU_INDEX; j++) {
                sudokuBoard.get(i).add(new PointSudoku(i, j, 0));
            }
        }
    }

    public IndividualSudoku (ArrayList<ArrayList<PointSudoku>> sudokuBoard) {
        this.sudokuBoard = new ArrayList<ArrayList<PointSudoku>>();
        for (int i = 0; i < sudokuBoard.size(); i++) {

            ArrayList<PointSudoku> row = new ArrayList<PointSudoku>();
            for (int j = 0; j < sudokuBoard.get(i).size(); j++) {
                PointSudoku pointSudoku = new PointSudoku(sudokuBoard.get(i).get(j));
                pointSudoku.setDomainValues(new ArrayList<>(sudokuBoard.get(i).get(j).getDomainValues()));
                row.add(pointSudoku);
            }
            this.sudokuBoard.add(row);
        }
    }

    public IndividualSudoku (IndividualSudoku individualSudoku) {
        this.sudokuBoard = new ArrayList<ArrayList<PointSudoku>>();
        for (int i = 0; i < individualSudoku.sudokuBoard.size(); i++) {
            ArrayList<PointSudoku> row = new ArrayList<PointSudoku>();
            for (int j = 0; j < individualSudoku.sudokuBoard.get(i).size(); j++) {
                PointSudoku pointSudoku = new PointSudoku(individualSudoku.sudokuBoard.get(i).get(j));
                pointSudoku.setDomainValues(new ArrayList<>(individualSudoku.sudokuBoard.get(i).get(j).getDomainValues()));
                row.add(pointSudoku);
            }
            this.sudokuBoard.add(row);
        }
    }


    public int getSingleElementValue (PointSudoku pointSudoku) {
        return sudokuBoard.get(pointSudoku.getVerticalIndex()).get(pointSudoku.getHorizontalIndex()).getValue();
    }

    public PointSudoku getSingleElement(PointSudoku pointSudoku) {
        return sudokuBoard.get(pointSudoku.getVerticalIndex()).get(pointSudoku.getHorizontalIndex());
    }

    public void setSingleElement(PointSudoku pointSudoku, int value) {
        if (value >= MIN_SUDOKU_VALUE && value <= MAX_SUDOKU_VALUE) {
            sudokuBoard.get(pointSudoku.getVerticalIndex()).get(pointSudoku.getHorizontalIndex()).setValue(value);
        }
    }

    public boolean checkColumn(int columnNumber) {
        if (columnNumber >= MIN_SUDOKU_INDEX && columnNumber <= MAX_SUDOKU_INDEX) {
            ArrayList<PointSudoku> columnToCheck = new ArrayList<PointSudoku>();
            for (int i = MIN_SUDOKU_INDEX; i <= MAX_SUDOKU_INDEX; i++) {
                columnToCheck.add(sudokuBoard.get(i).get(columnNumber));
            }
            Collections.sort(columnToCheck);
            for (int i = MIN_SUDOKU_INDEX; i < MAX_SUDOKU_INDEX; i++) {
                if (columnToCheck.get(i).getValue() != EMPTY_FIELD_VALUE && columnToCheck.get(i).getValue() == columnToCheck.get(i+1).getValue()) {
                    isCorrect = false;
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkLine (int lineNumber) {
        if (lineNumber >= MIN_SUDOKU_INDEX && lineNumber <= MAX_SUDOKU_INDEX) {
            ArrayList<PointSudoku> lineToCheck = new ArrayList<PointSudoku>(sudokuBoard.get(lineNumber));
            Collections.sort(lineToCheck);
            for (int i = MIN_SUDOKU_INDEX; i < MAX_SUDOKU_INDEX; i++) {
                if (lineToCheck.get(i).getValue() != EMPTY_FIELD_VALUE && lineToCheck.get(i).getValue() == lineToCheck.get(i+1).getValue()) {
                    isCorrect = false;
                    return false;
                }
            }
        } else {
            isCorrect = false;
            return false;
        }
        return true;
    }

    public boolean checkGrid(int horizontalFirstIndex, int verticalFirstIndex) {
        if (verticalFirstIndex >= MIN_SUDOKU_INDEX && verticalFirstIndex <= MAX_SUDOKU_INDEX &&
                horizontalFirstIndex >= MIN_SUDOKU_INDEX && horizontalFirstIndex <= MAX_SUDOKU_INDEX) {
            ArrayList<Integer> gridToCheck = new ArrayList<Integer>();

            for (int i = verticalFirstIndex; i <= verticalFirstIndex+2; i++) {
                for (int j = horizontalFirstIndex; j <= horizontalFirstIndex+2; j++) {
                    gridToCheck.add(sudokuBoard.get(i).get(j).getValue());
                }
            }

            Collections.sort(gridToCheck);
            for (int i = MIN_SUDOKU_INDEX; i < MAX_SUDOKU_INDEX; i++) {
                if (gridToCheck.get(i) != EMPTY_FIELD_VALUE && gridToCheck.get(i) == gridToCheck.get(i+1)) {
                    isCorrect = false;
                    return false;
                }
            }
        } else {
            isCorrect = false;
            return false;
        }
        return true;
    }

    public boolean checkSudoku () {
        //Sprawdzenie kolumn
        for (int i = MIN_SUDOKU_INDEX; i <= MAX_SUDOKU_INDEX; i++) {
            if(checkColumn(i) == false) {
                isCorrect = false;
                return false;
            }
        }
        //Sprawdzenie wierszy
        for (int i = MIN_SUDOKU_INDEX; i <= MAX_SUDOKU_INDEX; i++) {
            if(checkLine(i) == false) {
                isCorrect = false;
                return false;
            }
        }
        //Sprawdzenie kwadratów
        for (int i = MIN_SUDOKU_INDEX; i <= MAX_SUDOKU_INDEX; i = i + 3) {
            for (int j = MIN_SUDOKU_INDEX; j <= MAX_SUDOKU_INDEX; j = j + 3) {
                if(checkGrid(i, j) == false) {
                    isCorrect = false;
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isFull() {
        for (int i = 0; i <= MAX_SUDOKU_INDEX; i++) {
            for (int j = 0; j <= MAX_SUDOKU_INDEX; j++) {
                if (sudokuBoard.get(i).get(j).getValue() == EMPTY_FIELD_VALUE) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSolved() {
        if (checkSudoku() && isFull()) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<ArrayList<PointSudoku>> getSudokuBoard () {
        return sudokuBoard;
    }

    public void setSudokuBoard (ArrayList<ArrayList<PointSudoku>> sudokuBoard) {
        this.sudokuBoard = new ArrayList<ArrayList<PointSudoku>>(sudokuBoard);
    }

    @Override
    public IndividualSudoku clone() throws CloneNotSupportedException {
        IndividualSudoku coordinates = new IndividualSudoku(sudokuBoard);

        return coordinates;
    }

    public boolean isHasFieldNullDomain () {
        return hasFieldNullDomain;
    }

    public void setHasFieldNullDomain (boolean hasFieldNullDomain) {
        this.hasFieldNullDomain = hasFieldNullDomain;
    }

    @Override
    public String toString () {
        String sudokuString = "";
        for (int i = 0; i < sudokuBoard.size(); i++) {
            for (int j = 0; j < sudokuBoard.get(i).size(); j++) {
                sudokuString = sudokuString + String.format("%-3s", sudokuBoard.get(i).get(j).getValue());
//                sudokuString = sudokuString + String.format("%-19s", sudokuBoard.get(i).get(j).getDomainValues());
                if ((j+1)%3 == 0) {
                    sudokuString = sudokuString + String.format("%-3s", "|");
                }
            }
            sudokuString = sudokuString + "\n";
            if ((i+1)%3 == 0) {
                sudokuString = sudokuString + "----------------------------------\n";
            }
        }
        return sudokuString;
    }


    public void prepairDomain() {
        for (int i = 0; i <= MAX_SUDOKU_INDEX; i++) {
            for (int j = 0; j <= MAX_SUDOKU_INDEX; j++) {
                if(sudokuBoard.get(i).get(j).getValue() != 0) {
                    sudokuBoard.get(i).get(j).setDomainValues(new ArrayList<>(List.of(sudokuBoard.get(i).get(j).getValue())));
                }
                deleteDomainValuesRow(sudokuBoard.get(i).get(j), sudokuBoard.get(i).get(j).getValue());
                deleteDomainValuesColumn(sudokuBoard.get(i).get(j), sudokuBoard.get(i).get(j).getValue());
                deleteDomainValuesGrid(sudokuBoard.get(i).get(j), sudokuBoard.get(i).get(j).getValue());

            }
        }
    }

    public void deleteDomainValuesGrid(PointSudoku pointSudoku, int value) {
        int intHor = pointSudoku.getHorizontalIndex();
        int intVer = pointSudoku.getVerticalIndex();

        if (intHor == 1 || intHor == 4 || intHor == 7) {
            intHor = intHor - 1;
        }
        if (intVer == 1 || intVer == 4 || intVer == 7) {
            intVer = intVer - 1;
        }

        if (intHor == 2 || intHor == 5 || intHor == 8) {
            intHor = intHor - 2;
        }
        if (intVer == 2 || intVer == 5 || intVer == 8) {
            intVer = intVer - 2;
        }

        for (int i = intVer; i <= intVer + 2; i++) {
            for (int j = intHor; j <= intHor + 2; j++) {

                sudokuBoard.get(i).get(j).deleteValueDomain(value);
                if (sudokuBoard.get(i).get(j).getDomainValues().size() == 0 && sudokuBoard.get(i).get(j).getValue() == 0) {
                    hasFieldNullDomain = true;
                }
            }
        }
    }

    public void deleteDomainValuesRow(PointSudoku pointSudoku, int value) {
        for (int i = MIN_SUDOKU_INDEX; i <= MAX_SUDOKU_INDEX; i++) {
            if (sudokuBoard.get(pointSudoku.getVerticalIndex()).get(i).getValue() == 0) {
                sudokuBoard.get(pointSudoku.getVerticalIndex()).get(i).deleteValueDomain(value);
            }
            if (sudokuBoard.get(pointSudoku.getVerticalIndex()).get(i).getDomainValues().size() == 0 &&
                    sudokuBoard.get(pointSudoku.getVerticalIndex()).get(i).getValue() == 0) {
                hasFieldNullDomain = true;
            }
        }
    }

    public void deleteDomainValuesColumn(PointSudoku pointSudoku, int value) {
        for (int i = MIN_SUDOKU_INDEX; i <= MAX_SUDOKU_INDEX; i++) {
            sudokuBoard.get(i).get(pointSudoku.getHorizontalIndex()).deleteValueDomain(value);
            if (sudokuBoard.get(i).get(pointSudoku.getHorizontalIndex()).getDomainValues().size() == 0 &&
                    sudokuBoard.get(i).get(pointSudoku.getHorizontalIndex()).getValue() == 0) {
                hasFieldNullDomain = true;
            }
        }
    }
}
