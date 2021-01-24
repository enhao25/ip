package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    Storage(String filePath) throws DukeException {
        this.filePath = filePath;
        if (!createFolder() || !createFile(filePath)) {
            throw new DukeException("Error during file creation");
        }
    }
    
    // Create the folder if it has not been created before
    private boolean createFolder() {
        File folder = new File("data");

        // Ensures that the data folder exists, if not create the folder
        if (!folder.exists()) {
            return folder.mkdirs();
        }
        return true;
    }

    // Create the file if it has not been created before
    private boolean createFile(String filePath) {
        File file = new File(filePath); // create a File for the given file path

        // Ensures that the file is created, if not create the file
        if (!file.exists() && !file.isFile()) {
            // Create a new file
            try {
                if (!file.createNewFile()) {
                    return false;
                }
            } catch (IOException ex) {
                return false;
            }   
        }
        return true;
    }

    // Rewrite the entire file from the file path based on the task list given
    public void writeToFile(TaskList taskList) throws FileNotFoundException {
        // Push the file content to the page
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(filePath);

            // Rewrite the file with the entire list of text
            for (int index = 0; index < taskList.size(); index++) {
                fileWriter.write(taskList.get(index).toString() + "\n");
            }
            
            fileWriter.close();
        } catch (Exception ex) {
            throw new FileNotFoundException();
        }
    }

    // Load and return the file content from the task file
    public ArrayList<String> loadFileContent() throws FileNotFoundException {
        
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        ArrayList<String> contents = new ArrayList<>();
        
        // Store each line as a separate value in the array
        while (sc.hasNextLine()) {
            contents.add(sc.nextLine());
        }

        sc.close();

        return contents;
    }
}
