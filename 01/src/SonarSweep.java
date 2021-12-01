import java.io.*;

public class SonarSweep {
    public static void main(String []args) {
        try {
            BufferedReader fileStream = new BufferedReader(new FileReader("input-Part1.txt"));

            //Reads the first measurement who has no previous measurement
            int prev = Integer.parseInt(fileStream.readLine());

            String currentStr;
            int largerMeasurements = 0;

            //Reads one line at time the file entry
            while ((currentStr = fileStream.readLine()) != null) {
                int current = Integer.parseInt(currentStr); //Parse to int

                //Checks if the measurement is larger than previous
                if (current > prev) largerMeasurements++;

                //Updates the previous measurement
                prev = current;
            }

            //Prints the output
            System.out.println(largerMeasurements);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }

    }
}
