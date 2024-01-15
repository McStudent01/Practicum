import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonReader {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try {
            // Set initial directory for file chooser
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            // Let user select the file
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                // Read the file using NIO
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                // Print column headers
                System.out.printf("%-15s %-20s %-20s %-10s %-5s\n", "ID", "First Name", "Last Name", "Title", "YOB");
                System.out.println("----------------------------------------------------------------");

                // Read and format each record
                int line = 1;
                while ((rec = reader.readLine()) != null) {
                    String[] fields = rec.split(",");  // Assuming comma-separated values
                    System.out.printf("%-15s %-20s %-20s %-10s %-5s\n", fields[0], fields[1], fields[2], fields[3], fields[4]);
                    line++;
                }

                reader.close();
                System.out.println("\nData file read!");
            } else {
                System.out.println("No file selected. Exiting.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
