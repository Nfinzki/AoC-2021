import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HydrothermalVenturePartTwo {
    public static void main(String []args) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("input.txt"));

            List<Coordinates> lines = new ArrayList<>();
            String entry;
            int max = 0;

            //Reads the input
            while ((entry = fileReader.readLine()) != null) {
                int x1, y1, x2, y2;
                //Splits the two coordinates
                String []paris = entry.split(" -> ");

                //Splits x and y
                String []coordinates = paris[0].split(",");
                x1 = Integer.parseInt(coordinates[0]);
                y1 = Integer.parseInt(coordinates[1]);
                //Splits x and y
                coordinates = paris[1].split(",");
                x2 = Integer.parseInt(coordinates[0]);
                y2 = Integer.parseInt(coordinates[1]);

                //Calculates the diagram dimension
                max = Math.max(max, x1);
                max = Math.max(max, y1);
                max = Math.max(max, x2);
                max = Math.max(max, y2);

                lines.add(new Coordinates(x1, y1, x2, y2));
            }
            max++;

            //Initializes the diagram
            int [][]diagram = new int[max][max];
            for (int i = 0; i < max; i++)
                for (int j = 0; j < max; j++)
                    diagram[i][j] = 0;

            //Sets the clouds in the diagram
            for (Coordinates c : lines) {
                int x1 = c.getX1();
                int y1 = c.getY1();
                int x2 = c.getX2();
                int y2 = c.getY2();

                //Checks firstly the diagonals
                if (x1 < x2 && y1 < y2) {
                    while (x1 <= x2) {
                        diagram[y1][x1]++;
                        x1++;
                        y1++;
                    }
                    continue;
                }

                if (x1 < x2 && y1 > y2) {
                    while (x1 <= x2) {
                        diagram[y1][x1]++;
                        x1++;
                        y1--;
                    }
                    continue;
                }

                if (x1 > x2 && y1 > y2) {
                    while (x1 >= x2) {
                        diagram[y1][x1]++;
                        x1--;
                        y1--;
                    }
                    continue;
                }

                if (x1 > x2 && y1 < y2) {
                    while (x1 >= x2) {
                        diagram[y1][x1]++;
                        x1--;
                        y1++;
                    }
                    continue;
                }

                //Then checks rows and columns
                int base, bound;
                if (x1 == x2) {
                    base = Math.min(y1, y2);
                    bound = Math.max(y1, y2);
                } else {
                    base = Math.min(x1, x2);
                    bound = Math.max(x1, x2);
                }

                for (int i = base; i <= bound; i++) {
                    if (x1 == x2) {
                        diagram[i][x1]++;
                    } else {
                        diagram[y1][i]++;
                    }
                }
            }

            //Calculates the output
            int res = 0;
            for (int i = 0; i < max; i++)
                for (int j = 0; j < max; j++)
                    if (diagram[i][j] >= 2) res++;

            //Prints the output
            System.out.println(res);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }
}
