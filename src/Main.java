//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static int[][] defaultGrid = new int[9][9];
    public static void printSudoku(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
                if (j % 3 == 2 && j < 8) System.out.print("| ");
            }
            System.out.println();
            if (i % 3 == 2 && i < 8) System.out.println("— — — — — — — — — — —");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
    }
    public static boolean isValid(int[][] grid, int r, int c, int num) {
        for (int k = 0; k < 9; k++) {
            if (grid[r][k] == num) return false;
            if (grid[k][c] == num) return false;
            int tempRow = 3 * (r / 3) + k / 3;
            int tempColumn = 3 * (c / 3) + k % 3;
            if (grid[tempRow][tempColumn] == num) return false;
        }
        return true;
    }

    public static int[][] solveSudoku(int[][] grid, int i, int j) {
        if (defaultGrid[i][j] > 0) {
            if (i == 8 && j == 8) {
                return grid;
            } else if (j < 8) {
                return solveSudoku(grid, i, ++j);
            } else {
                return solveSudoku(grid, ++i, 0);
            }
        } else {
            int initialValue = grid[i][j];
            for (int k = initialValue; k <= 9; k++) {
                if (isValid(grid, i, j, k)) {
                    grid[i][j] = k;
                    printSudoku(grid);
                    break;
                }
            }
            if (grid[i][j] > initialValue) {
                if (i == 8 && j == 8) {
                    return grid;
                } else if (j < 8) {
                    return solveSudoku(grid, i, ++j);
                } else {
                    return solveSudoku(grid, ++i, 0);
                }
            } else {
                grid[i][j] = 0;
                int itemp = i;
                int jtemp = j;
                do {
                    if (jtemp == 0) {
                        jtemp = 8;
                        itemp--;
                    } else {
                        jtemp--;
                    }
                } while (defaultGrid[itemp][jtemp] > 0);
                return solveSudoku(grid, itemp, jtemp);
            }
        }
    }

    public static void main(String[] args) {
        /*int[][] grid = {{0,0,0,0,0,3,0,0,0},
                        {4,0,0,0,0,2,0,0,1},
                        {8,0,7,0,9,0,6,0,0},
                        {2,0,0,0,0,0,0,9,0},
                        {0,0,5,0,0,0,7,0,0},
                        {0,8,0,0,0,4,5,0,0},
                        {0,5,0,6,0,0,0,0,0},
                        {3,0,0,0,0,0,0,0,5},
                        {0,9,0,7,0,0,0,8,2}};

         */

        int[][] grid = {{7,0,0,0,0,0,0,0,5},
                        {0,1,5,0,2,0,0,0,4},
                        {0,6,0,0,7,0,0,1,2},
                        {0,0,0,8,1,2,0,0,0},
                        {2,8,4,7,0,9,0,0,0},
                        {3,0,0,0,5,4,0,7,8},
                        {6,0,3,0,0,0,8,4,1},
                        {0,0,0,0,6,0,5,0,0},
                        {0,2,0,0,0,8,9,0,0}};

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                defaultGrid[i][j] = grid[i][j];
            }
        }

        int[][] solvedGrid = solveSudoku(grid, 0, 0);
        printSudoku(grid);
    }
}
