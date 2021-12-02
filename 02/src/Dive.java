import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dive {
    public static void main (String []args) {
        try {
            BufferedReader file = new BufferedReader(new FileReader("input.txt"));

            int horizontalPosition = 0;
            int depth = 0;
            String fileEntry;

            //Reads one entry at time
            while ((fileEntry = file.readLine()) != null) {
                //Splits the line in 'command' and 'value'
                String []params = fileEntry.split(" ");
                int value = Integer.parseInt(params[1]);

                //Matches the command with the action to perform
                switch (params[0]) {
                    case "forward" : {
                        horizontalPosition += value;
                        break;
                    }
                    case "down" : {
                        depth += value;
                        break;
                    }
                    case "up" : {
                        depth -= value;
                        break;
                    }
                    case "" : break;
                }
            }

            //Prints the output
            System.out.println(horizontalPosition * depth);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }
}
