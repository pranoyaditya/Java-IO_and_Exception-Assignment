import java.io.*;

public class LineSeparator {

    public static void main(String[] args) {
        String inputFilePath = "path_to_your_input_file.txt";
        String oddLinesFilePath = "odd_lines.txt";
        String evenLinesFilePath = "even_lines.txt";

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
                BufferedWriter oddWriter = new BufferedWriter(new FileWriter(oddLinesFilePath));
                BufferedWriter evenWriter = new BufferedWriter(new FileWriter(evenLinesFilePath))
        ) {
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                if (lineNumber % 2 == 0) {
                    evenWriter.write(line);
                    evenWriter.newLine();
                } else {
                    oddWriter.write(line);
                    oddWriter.newLine();
                }
                lineNumber++;
            }

            System.out.println("Lines have been successfully separated.");
            System.out.println("Odd lines written to: " + oddLinesFilePath);
            System.out.println("Even lines written to: " + evenLinesFilePath);

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while processing files: " + e.getMessage());
        }
    }
}
