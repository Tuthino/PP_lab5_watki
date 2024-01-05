package lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.checkerframework.checker.units.qual.m;

import lab5.models.Cell;
import lab5.models.Map;

public class InputParser {
    private Map settings;
    private String filePath;
    private int num_of_iterations;

    public InputParser(String filePath) {
        this.filePath = filePath;
    }

    public Map loadMap() {
        ArrayList<String> lines = getContent(filePath);
        return parseInput(lines);
    }

    public ArrayList<String> getContent(String filePath) {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;

    }

    public Map parseInput(ArrayList lines) {
        int width = Integer.parseInt(lines.get(0).toString());
        int height = Integer.parseInt(lines.get(1).toString());
        int num_alive_cells = Integer.parseInt(lines.get(3).toString());
        num_of_iterations = Integer.parseInt(lines.get(2).toString());

        validateFile(lines, width, height, num_of_iterations, num_alive_cells);

        Map map = new Map(width, height);
        Cell[][] cellArray = map.getCellArray();

        for (int i = 0; i < num_alive_cells; i++) {
            String[] temp = lines.get(i + 4).toString().split(" ");
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);

            // Setting alive state
            cellArray[x][y].setAliveState(true);

            // Setting neighbours
            map.setNeighboursState(cellArray, x, y, width, height,true);

        }

        map = map.setCellArray(cellArray);

        return map;
    }

    public int getNum_of_iterations() {
        return num_of_iterations;
    }

    private boolean validateFile(ArrayList lines, int width, int height, int num_of_iterations, int num_alive_cells) {
        if (lines.size() != num_alive_cells + 4) {
            throw new IllegalArgumentException("Invalid number of lines in file");
        }
        if (width < 0 || height < 0 || num_of_iterations < 0 || num_alive_cells < 0) {
            throw new IllegalArgumentException("Invalid number in file");
        }
        for (int i = 0; i < num_alive_cells; i++) {
            String[] temp = lines.get(i + 4).toString().split(" ");
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);
            if (x < 0 || x > width || y < 0 || y > height) {
                throw new IllegalArgumentException("Invalid coordinates in file");
            }
        }
        return true;
    }

}