import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractHashtagsFromCSV {

    public static void main(String[] args) {
        // Input CSV file path (local)
        String inputCsvFile = "./input/crypto-query-tweets.csv";

        // Output TXT file path inside the input folder
        String outputTxtFile = "./input/extracted_hashtags.txt";

        // Regex pattern to extract hashtags (# followed by letters, digits, or underscores)
        Pattern hashtagPattern = Pattern.compile("#[\\w_]+");

        try (BufferedReader br = new BufferedReader(new FileReader(inputCsvFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputTxtFile))) {

            String line;
            boolean firstLine = true;

            // Read CSV line by line (assuming first line is header)
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;  // skip header line if present
                    continue;
                }

                // Assuming CSV columns separated by commas, and tweet text is in a column named tweet_text
                // Simplification: if CSV format is simple and tweet text is the first column:
                // Otherwise, you can split by comma and get the correct column index
                String[] columns = line.split(",", -1);

                // Adjust this index according to your CSV format (tweet text column)
                // For example, if tweet_text is the first column:
                String tweetText = columns[0];

                Matcher matcher = hashtagPattern.matcher(tweetText.toLowerCase());
                while (matcher.find()) {
                    String hashtag = matcher.group();
                    bw.write(hashtag);
                    bw.newLine();
                }
            }

            System.out.println("Hashtag extraction complete. Output saved to: " + outputTxtFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
