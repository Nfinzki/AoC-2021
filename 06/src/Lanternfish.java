import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Lanternfish {
    public static void main (String []args) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("input.txt"));

            String input;
            //Reads the input
            if ((input = fileReader.readLine()) == null) {
                System.err.println("File is empty");
                System.exit(1);
            }

            //Puts the input in a list
            List<Integer> fish = new ArrayList<>();
            for (String s : input.split(","))
                fish.add(Integer.parseInt(s));

            //Sorts the list
            fish.sort(Comparator.naturalOrder());

            //Initializes the array who contain the fish
            long []fishCount = new long[9];
            Arrays.fill(fishCount, 0);

            //Puts the fish in the array
            for (int f : fish)
                fishCount[f]++;

            //Calculates the result day after day
            for (int k = 0; k < 256; k++) {
                //Saves the new fish to add
                long tmp = fishCount[0];

                //Decreases every fish by one
                for (int i = 0; i < fishCount.length-1; i++)
                    fishCount[i] = fishCount[i+1];

                //Resets fish arrived to zero
                fishCount[6] += tmp;
                //Adds new fish
                fishCount[8] = tmp;

                //Part One
                if (k == 79) {
                    long result = 0;
                    //Calculates the number of fish at day 80
                    for (int i = 0; i < fishCount.length; i++)
                        result += fishCount[i];

                    System.out.println("Day 80: " + result);
                }
            }

            //Part Two
            long result = 0;
            //Calculates the number of fish at day 256
            for (int i = 0; i < fishCount.length; i++)
                result += fishCount[i];

            //Prints the result
            System.out.println("Day 256: " + result);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }
}
