public class Board {
    private final String[][] numbers;

    public Board(String[][] numbers) {
        this.numbers = numbers;
    }

    //Marks a cell in the board, if present
    //Returns true iif the board has a bingo
    public boolean markBoard(String number) {
        boolean result = false;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (numbers[i][j].equals(number)) {
                    numbers[i][j] = "-1";
                    result = checkWin(i, j); //Checks if the board contains a bingo
                }
        return result;
    }

    //Checks if the board has a bingo
    private boolean checkWin(int row, int column) {
        boolean win = true;
        //Checks the row where the last element was marked
        for (int j = 0; j < 5; j++)
            if (!numbers[row][j].equals("-1")) {
                win = false;
                break;
            }
        if (win) return true;

        //Checks the column where the last element was marked
        for (int i = 0; i < 5; i++) {
            if (!numbers[i][column].equals("-1"))
                return false;
        }

        return true;
    }

    //Sums all the unmarked elements
    public int sumUnmarked() {
        int sum = 0;

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (!numbers[i][j].equals("-1"))
                    sum += Integer.parseInt(numbers[i][j]);
        return sum;
    }
}