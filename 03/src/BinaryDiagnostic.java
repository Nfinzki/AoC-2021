import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BinaryDiagnostic {
    public static void main(String []args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

            List<String> input = new ArrayList<>();
            String entry;

            //Reads the input
            while ((entry = reader.readLine()) != null) {
                input.add(entry);
            }

            //Initializes the frequency array
            int []frequency = new int[input.get(0).length()];

            //Calculates the frequency for each bit
            for (String bits : input) {
                for (int i = 0; i < bits.length(); i++) {
                    if (bits.charAt(i) == '1')
                        frequency[i]++;
                    else
                        frequency[i]--;
                }
            }

            int gammaRate = 0;
            int epsilonRate = 0;

            //Calculates the gamma and epsilon rate
            for (int i = 0; i < frequency.length; i++) {
                if (frequency[i] >= 0) //There's more 1
                    gammaRate += Math.pow(2, frequency.length - i-1);
                else //There's more 0
                    epsilonRate += Math.pow(2, frequency.length - i-1);
            }

            //Returns the final result
            System.out.println(gammaRate * epsilonRate);

        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }
}
