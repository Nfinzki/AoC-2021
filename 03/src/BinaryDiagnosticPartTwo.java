import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BinaryDiagnosticPartTwo {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

            List<String> input = new ArrayList<>();
            List<String> inputCopy = new ArrayList<>();
            String entry;

            //Reads the input and creates a copy
            while ((entry = reader.readLine()) != null) {
                input.add(entry);
                inputCopy.add(entry);
            }

            int i = 0;
            List<String> garbage = new ArrayList<>();
            //Calculates the result number from bit criteria
            while (input.size() != 1) {
                //Calculates the most frequent bit in position i
                int frequency = getFrequency(input, i);

                //Searches for entry to remove
                for (String bits : input) {
                    if ((bits.charAt(i) == '1' && frequency < 0) || (bits.charAt(i) == '0' && frequency >= 0)) //The conditions are for the most common bit
                        garbage.add(bits);
                }

                i++;
                //Removes the entries
                input.removeAll(garbage);
                garbage.clear();
            }
            //Converts the oxygen value from binary to decimal
            int oxygen = Integer.parseInt(input.get(0), 2);

            i = 0;
            //Calculates the result number from bit criteria
            while (inputCopy.size() != 1) {
                //Calculates the most frequent bit in position i
                int frequency = getFrequency(inputCopy, i);

                //Searches for entry to remove
                for (String bits : inputCopy) {
                    if ((bits.charAt(i) == '1' && frequency >= 0) || (bits.charAt(i) == '0' && frequency < 0)) //The conditions are for the least common bit
                        garbage.add(bits);
                }

                i++;
                //Removes the entries
                inputCopy.removeAll(garbage);
                garbage.clear();
            }
            //Converts the oxygen value from binary to decimal
            int CO2 = Integer.parseInt(inputCopy.get(0), 2);

            //Calculates the life support energy
            System.out.println(oxygen * CO2);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public static int getFrequency(List<String> input, int pos) {
        int frequency = 0;

        for (String bits : input) {
            if (bits.charAt(pos) == '1')
                frequency++;
            else
                frequency--;
        }

        return frequency;
    }
}