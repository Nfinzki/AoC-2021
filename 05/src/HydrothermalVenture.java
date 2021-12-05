import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HydrothermalVenture {
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

                //Removes the coordinates that don't have a matching x or y
                if (x1 != x2 && y1 != y2) continue;

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
                int base, bound;
                if (c.getX1() == c.getX2()) {
                    base = Math.min(c.getY1(), c.getY2());
                    bound = Math.max(c.getY1(), c.getY2());
                } else {
                    base = Math.min(c.getX1(), c.getX2());
                    bound = Math.max(c.getX1(), c.getX2());
                }

                for (int i = base; i <= bound; i++) {
                    if (c.getX1() == c.getX2()) {
                        diagram[i][c.getX1()]++;
                    } else {
                        diagram[c.getY1()][i]++;
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
