/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package lab5;

import lab5.models.Map;
import java.util.Arrays;
import lab5.models.Cell;
import lab5.models.Task;
import java.util.concurrent.CyclicBarrier;

public class App {
    public static Map updatedMap;
    public static void main(String[] args) {
        // String filePath = args[0];
        int num_of_iterations;

        int threadsNum = 4;

        String filePath = "/home/reyio/Programming/PWR/Programowanie/lab5/app/src/main/resources/input";
        InputParser parser = new InputParser(filePath);
        Map currentMap = parser.loadMap();
        num_of_iterations = parser.getNum_of_iterations();
        // currentMap.printMap();

        runSimulation(currentMap, num_of_iterations, threadsNum);
    }

    private static void runSimulation(Map currentMap, int num_of_iterations, int threadsNum) {
        int width = currentMap.getWidth();
        int height = currentMap.getHeight();
        Cell[][] cellArray = currentMap.getCellArray();

        updatedMap = currentMap; // This is where threads will update the map

        CyclicBarrier barrier = new CyclicBarrier(threadsNum, new Runnable() {
            @Override
            public void run() {
                updatedMap.printMap();
            }
        });

        for (int i = 0; i < threadsNum; i++) {
            int offset = i * (width / threadsNum);
            int end = (i + 1) * (width / threadsNum);
            if (i == threadsNum - 1) {
                end = width;
            }
            Thread thread = new Thread(new Task(i, offset, end, height, cellArray, barrier));
            thread.start();
        }
    }

    // for (int iteration = 0; i < num_of_iterations;i++){

    // }

    // }

    // private static void runSimulation(Map map, int num_of_iterations, int
    // threadsNum) {
    // int width = map.getWidth();
    // int height = map.getHeight();
    // Cell[][] cellArray = map.getCellArray();
    // for (int i = 0; i < num_of_iterations; i++) {
    // for (int j = 0; j < threadsNum; j++) {
    // int offset = j * (width / threadsNum);
    // int end = (j + 1) * (width / threadsNum);
    // if (j == threadsNum - 1) {
    // end = width;
    // }
    // Thread thread = new Thread(new MyThread(j, offset, end, height, cellArray));
    // thread.start();
    // try {
    // thread.join();
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    // for (int j = 0; j < threadsNum; j++) {
    // int offset = j * (width / threadsNum);
    // int end = (j + 1) * (width / threadsNum);
    // if (j == threadsNum - 1) {
    // end = width;
    // }
    // Thread thread = new Thread(new MyThread(j, offset, end, height, cellArray));
    // thread.start();
    // try {
    // thread.join();
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    // printMap(map);
    // }
    // }

}
