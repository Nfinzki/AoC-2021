import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SonarSweepPart2 {
    public static void main(String []args) {
        try {
            BufferedReader fileStream = new BufferedReader(new FileReader("input-Part2.txt"));

            //Variables where will save the partial sums
            int firstColumn = 0;
            int secondColumn = 0;
            int thirdColumn = 0;
            int fourthColumn = 0;

            //Counters for every column
            int first = 1;
            int second = 0;
            int third = -1;
            int fourth = -2;

            int prev = Integer.MAX_VALUE;
            String currentStr;
            int largerMeasurements = 0;

            //Reads one line at time the file entry
            while ((currentStr = fileStream.readLine()) != null) {
                int current = Integer.parseInt(currentStr); //Parse to int

                if (first % 4 != 0)
                    firstColumn += current;
                else {
                    //Three measurements reached
                    if (firstColumn > prev) largerMeasurements++;
                    prev = firstColumn;
                    firstColumn = 0;
                }

                if (second % 4 != 0)
                    secondColumn += current;
                else {
                    //Three measurements reached
                    if (secondColumn > prev) largerMeasurements++;
                    prev = secondColumn;
                    secondColumn = 0;
                }

                if (third % 4 != 0 && third >= 0)
                    thirdColumn += current;
                else {
                    //Three measurements reached
                    if (thirdColumn > prev) largerMeasurements++;
                    prev = thirdColumn;
                    thirdColumn = 0;
                }

                if (fourth % 4 != 0 && fourth >= 0)
                    fourthColumn += current;
                else {
                    //Three measurements reached
                    if (fourthColumn > prev) largerMeasurements++;
                    prev = fourthColumn;
                    fourthColumn = 0;
                }

                //Increments counters
                first++;
                second++;
                third++;
                fourth++;
            }

            //Checks there is another measurement to count
            if (first % 4 == 0 && firstColumn > prev) largerMeasurements++;
            if (second % 4 == 0 && secondColumn > prev) largerMeasurements++;
            if (third % 4 == 0 && thirdColumn > prev) largerMeasurements++;
            if (fourth % 4 == 0 && fourthColumn > prev) largerMeasurements++;

            //Removes the first measurement calculated
            largerMeasurements--;

            //Prints the output
            System.out.println(largerMeasurements);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }

    }
}
