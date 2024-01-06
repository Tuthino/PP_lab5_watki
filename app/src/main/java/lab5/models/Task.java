package lab5.models;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import lab5.App;

public class Task implements Runnable {
    private int threadId;
    private int offset;
    private int width;
    private int height;
    private Map map;

    private CyclicBarrier barrier;

    public Task(int threadId, int offset, int width, int height, Map map, CyclicBarrier barrier) {
        this.threadId = threadId;
        this.offset = offset;
        this.width = width;
        this.height = height;
        this.map = map;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        // perform task
        for (int i = offset; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = map.getCell(i, j);
                cell.setNextCellstate(map.nextCellstate(cell));
                // App.mainMap.setCellState(cell.getX(), cell.getY(), nextCellstate);
                App.mainMap.getCell(cell.getX(), cell.getY()).setCharacter(Integer.toString(threadId));
                if(cell.getNextCellstate() == 1){
                    // System.out.println("Thread " + threadId + " changed cell " + cell.getX() + " " + cell.getY() + " to alive");
                }
            }
        }
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        for (int i = offset; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = map.getCell(i, j);
                cell.setAliveState(cell.getNextCellstate());
                App.mainMap.setCellState(cell.getX(), cell.getY(), cell.isAlive());
            }
        }

    }

    public Map getMap() {
        return map;
    }

    public int getThreadId() {
        return threadId;
    }

    public int getOffset() {
        return offset;
    }

    public int getWidth() {
        return width;
    }

}
