import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Scanner;

public class UrlContentDownloader {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter the path of the file containing URLs: ");
        String inputFilePath = scanner.nextLine();


        System.out.print("Enter the directory to save the downloaded content: ");
        String outputDirectory = scanner.nextLine();

        File inputFile = new File(inputFilePath);
        File outputDir = new File(outputDirectory);

        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String url;
            int fileCount = 1;

            while ((url = reader.readLine()) != null) {
                System.out.println("Downloading content from: " + url);

                try {
                    String content = downloadContentFromUrl(url);
                    String outputFilePath = outputDirectory + File.separator + "content" + fileCount + ".txt";

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                        writer.write(content);
                        System.out.println("Content saved to: " + outputFilePath);
                    }

                    fileCount++;
                } catch (IOException e) {
                    System.out.println("Failed to download content from " + url + ": " + e.getMessage());
                }
            }

            System.out.println("Download process completed.");

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading the input file: " + e.getMessage());
        }

        scanner.close();
    }

    private static String downloadContentFromUrl(String urlString) throws IOException {
        StringBuilder content = new StringBuilder();

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }

        return content.toString();
    }
}
