package GUI.infrastructuredesign;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


public class FileChooser {

    public static void main(String[] args) {
        // Specify the path to the directory to open
        String directoryPath = "C:";

        // Create a File object for the directory
        File directory = new File(directoryPath);

        // Open the directory in the default file explorer
        try {
            Desktop.getDesktop().open(directory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
