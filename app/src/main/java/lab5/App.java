/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package lab5;

import lab5.models.Map;
import java.util.Arrays;
import lab5.models.Cell;
import lab5.models.Task;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class App {
    public static Map mainMap;

    public static void main(String[] args) {
        // String filePath = args[0];
        int num_of_iterations;

        int threadsNum = 4;

        String filePath = "/home/reyio/Programming/PWR/Programowanie/lab5/app/src/main/resources/input";
        InputParser parser = new InputParser(filePath);
        mainMap = parser.loadMap();
        num_of_iterations = parser.getNum_of_iterations();
        mainMap.printMap();

        runSimulation(mainMap, num_of_iterations, threadsNum);
    }

    private static void runSimulation(Map currentMap, int num_of_iterations, int threadsNum) {
        int width = currentMap.getWidth();
        int height = currentMap.getHeight();

        for (int iteration = 0; iteration < num_of_iterations; iteration++) {

            CyclicBarrier barrier = new CyclicBarrier(threadsNum, new Runnable() {
                @Override
                public void run() {
                    System.out.println("Test");
                }
            });

            runThreads(currentMap, threadsNum, width, height, barrier);
            mainMap.printMap();
            // mainMap.printTestMap();
            // System.out.println("Iteration finished");

        }

    }

    private static void runThreads(Map currentMap, int threadsNum, int width, int height, CyclicBarrier barrier) {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadsNum; i++) {
            int offset = i * (width / threadsNum);
            int end = (i + 1) * (width / threadsNum);
            if (i == threadsNum - 1) {
                end = width;
            }
            Map threadMap = new Map(currentMap);
            Thread thread = new Thread(new Task(i, offset, end, height, threadMap, barrier));
            thread.start();
            threads.add(thread);

        }
        for (Thread thread : threads) {
            try {
                thread.join();
                System.out.println(thread.getName() + " joined");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
