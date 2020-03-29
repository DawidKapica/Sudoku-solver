package informations;

import java.util.ArrayList;
import java.util.List;

public class PointSudoku implements Comparable<PointSudoku>{
    public static final int MIN_SUDOKU_INDEX = 0;
    public static final int MAX_SUDOKU_INDEX = 8;

    private int verticalIndex;
    private int horizontalIndex;
    private int value = 0;

    ArrayList<Integer> domainValues = new ArrayList<Integer>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));


    public PointSudoku(int verticalIndex, int horizontalIndex) {
        if (verticalIndex >= MIN_SUDOKU_INDEX && verticalIndex <= MAX_SUDOKU_INDEX &&
                horizontalIndex >= MIN_SUDOKU_INDEX && horizontalIndex <= MAX_SUDOKU_INDEX) {
            this.verticalIndex = verticalIndex;
            this.horizontalIndex = horizontalIndex;
            value = 0;
        }
    }

    public PointSudoku(int verticalIndex, int horizontalIndex, int value) {
        if (verticalIndex >= MIN_SUDOKU_INDEX && verticalIndex <= MAX_SUDOKU_INDEX &&
                horizontalIndex >= MIN_SUDOKU_INDEX && horizontalIndex <= MAX_SUDOKU_INDEX) {
            this.verticalIndex = verticalIndex;
            this.horizontalIndex = horizontalIndex;
            this.value = value;
        }
    }

    public PointSudoku(PointSudoku pointSudoku) {
        this.horizontalIndex = pointSudoku.getHorizontalIndex();
        this.verticalIndex = pointSudoku.getVerticalIndex();
        this.value = pointSudoku.getValue();
        domainValues = new ArrayList<Integer>(pointSudoku.getDomainValues());
    }

    public int getVerticalIndex () {
        return verticalIndex;
    }

    public void setVerticalIndex (int verticalIndex) {
        if (verticalIndex >= MIN_SUDOKU_INDEX && verticalIndex <=MAX_SUDOKU_INDEX) {
            this.verticalIndex = verticalIndex;
        }
    }

    public int getHorizontalIndex () {
        return horizontalIndex;
    }

    public void setHorizontalIndex (int horizontalIndex) {
        if (horizontalIndex >= MIN_SUDOKU_INDEX && horizontalIndex <=MAX_SUDOKU_INDEX) {
            this.horizontalIndex = horizontalIndex;
        }
    }

    public void deleteValueDomain(int value) {
        System.out.println(value);
        domainValues.remove(Integer.valueOf(value));
    }

    public ArrayList<Integer> getDomainValues () {
        return domainValues;
    }

    public void setDomainValues (ArrayList<Integer> domainValues) {
        this.domainValues = domainValues;
    }

    public int getValue () {
        return value;
    }

    public void setValue (int value) {
        this.value = value;
    }

    @Override
    public int compareTo(PointSudoku pointSudoku) {
        if (pointSudoku.value < value) {
            return 1;
        } else if (pointSudoku.value == value) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString () {
        return ("v" + verticalIndex + "h" + horizontalIndex) + "v" + value;
    }
}
