import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GiantSquid {
    public static void main (String []args) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("input.txt"));
            //Reads the numbers to mark boards
            String numbersCalled = fileReader.readLine();
            String []numbers = numbersCalled.split(",");

            int i = 0;
            String [][]board = new String[5][5];
            String row;
            List<Board> boards = new ArrayList<>();

            //Reads all the boards
            while ((row = fileReader.readLine()) != null) {
                if (row.equals("")) continue;

                int j = 0;
                //Removes white spaces
                for (String s : row.split(" ")) {
                    if (s.equals("")) continue;
                    board[i % 5][j] = s;
                    j++;
                }

                i++;
                //Adds board to the list
                if (i % 5 == 0) {
                    boards.add(new Board(board));
                    board = new String[5][5];
                }
            }

            //Picks number in order until one board has bingo
            for (String number : numbers) {
                for (Board b : boards) {
                    if (b.markBoard(number)) { //Bingo
                        System.out.println(b.sumUnmarked() * Integer.parseInt(number));
                        return;
                    }
                }
            }

        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }
}