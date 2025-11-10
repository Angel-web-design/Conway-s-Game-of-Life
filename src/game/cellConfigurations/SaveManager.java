package game.cellConfigurations;

import game.scenes.gameScene.Cell;

import java.io.*;


public class SaveManager {
    public static void saveMatrix(Cell[][] matrix, String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(matrix);
            System.out.println("File saved correctly.");
        }
        catch (IOException e) {
            System.out.println("There was a problem when trying to save the position.");
            // e.printStackTrace();
        }
    }
    public static Cell[][] loadMatrix(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            System.out.println("File loaded correctly.");
            return (Cell[][]) in.readObject();

        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("That initial position doesn't exists or it's corrupted.");
            // e.printStackTrace();
            return null;
        }
    }
}
