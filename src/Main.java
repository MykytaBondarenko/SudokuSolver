//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {
    public static int[][] defaultGrid = new int[9][9];
    public static void printSudoku(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] > 0) {
                    System.out.print(grid[i][j] + " ");
                } else {
                    System.out.print(". ");
                }
                if (j % 3 == 2 && j < 8) System.out.print("| ");
            }
            System.out.println();
            if (i % 3 == 2 && i < 8) System.out.println("— — — — — — — — — — —");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
    }
    public static boolean isValid(int[][] grid, int r, int c, int num) {
        for (int k = 0; k < 9; k++) {
            if (grid[r][k] == num) return false; // row check
            if (grid[k][c] == num) return false; // column check
            int tempRow = 3 * (r / 3) + k / 3;                      //
            int tempColumn = 3 * (c / 3) + k % 3;                   // square check
            if (grid[tempRow][tempColumn] == num) return false;     //
        }
        return true;
    }

    public static int[][] solveSudoku(int[][] grid, int i, int j) {
        if (defaultGrid[i][j] > 0) { // check if value is assigned from the beginning
            if (i == 8 && j == 8) { // end of the grid
                return grid;
            }
            if (j < 8) {
                return solveSudoku(grid, i, j + 1);
            } // end of a row
            return solveSudoku(grid, i + 1, 0);
        }
        int initialValue = grid[i][j];
        for (int k = initialValue; k <= 9; k++) {
            if (isValid(grid, i, j, k)) {
                grid[i][j] = k;
                //printSudoku(grid);
                break;
            }
        }
        if (grid[i][j] > initialValue) { // if the cell value changes
            if (i == 8 && j == 8) {
                return grid;
            }
            if (j < 8) {
                return solveSudoku(grid, i, j + 1);
            }
            return solveSudoku(grid, i + 1, 0);
        }
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
        } while (defaultGrid[itemp][jtemp] > 0); // looking for a cell that wasn't assigned from the beginning
         return solveSudoku(grid, itemp, jtemp);
    }

    public static void main(String[] args) {

        int[][] grid = {{0,3,0,4,0,0,1,9,0},
                        {0,0,0,0,1,9,0,6,0},
                        {0,0,9,7,0,5,0,0,0},
                        {0,0,0,0,0,1,4,0,0},
                        {0,6,8,9,0,4,2,3,0},
                        {0,0,1,8,0,0,0,0,0},
                        {0,0,0,2,0,8,3,0,0},
                        {0,1,0,5,9,0,0,0,0},
                        {0,8,5,0,0,6,0,7,0}};



        System.out.println("Hi! This is my SudokuSolver! Press 'y' if you'd like to input your own sudoku! (or anything else if not)");
        Scanner sc = new Scanner(System.in);
        String reply = sc.nextLine();
        if (reply.toLowerCase().equals("y")) {
            System.out.println("Please enter your cell values one by one. The unassigned cells are denoted as '0'");
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print("g[" + i + "][" + j + "] = ");
                    int inputValue = sc.nextInt();
                    while(inputValue > 9 || inputValue < 0) {
                        System.out.println("Wrong input! The value should be in the range of [0,9]. Type your value again");
                        inputValue = sc.nextInt();
                    }
                    grid[i][j] = inputValue;
                }
            }
        }
        printSudoku(grid);
        System.out.println("Is this your Sudoku? (y/n)");
        reply = sc.next();
        if (reply.toLowerCase().equals("n")) {
            System.out.println("Input the cell index in 'i,j' format (separated by comma) and then your new value. For example, 1,2 and then 3. If you don't want to change anything, type 'y'");
            String input = sc.next();
            while (input.charAt(0) != 'y') {
                int i = Integer.parseInt(input.substring(0,1));
                int j = Integer.parseInt(input.substring(2,3));
                int value = sc.nextInt();
                grid[i][j] = value;
                printSudoku(grid); System.out.println("Input the cell index in 'i,j' format (separated by comma) and then your new value. For example, 1,2 and then 3. If you don't want to change anything, type 'y'");
                input = sc.next();
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                defaultGrid[i][j] = grid[i][j];
            }
        }

        int[][] solvedGrid = solveSudoku(grid, 0, 0);
        printSudoku(grid);
    }
}
