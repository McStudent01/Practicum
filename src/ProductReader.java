import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader
{

    public static void main(String[] args)
    {
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


            System.out.printf("%-15s %-20s %-30s %-8s\n", "Product ID", "Product Name", "Description", "Cost");
            System.out.println("-----------------------------------------------------------------------");
                // Read and format each record
            int line = 1;
            while ((rec = reader.readLine()) != null) {
                String[] fields = rec.split(",");
                System.out.printf("%-15s %-20s %-30s %8.2f\n", fields[0], fields[1], fields[2], Double.parseDouble(fields[3]));
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