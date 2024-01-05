package lab5.models;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import lab5.App;


public class Task implements Runnable{
    private int threadId;
    private int offset;
    private int width;
    private int height;
    private Cell[][] cellArray;
    private CyclicBarrier barrier;


    public Task(int threadId, int offset, int width, int height, Cell[][] cellArray, CyclicBarrier barrier) {
        this.threadId = threadId;
        this.offset = offset;
        this.width = width;
        this.height = height;
        this.cellArray = cellArray;
        this.barrier = barrier;
    }

    @Override
    public void run() {
         // perform task
        for(int i = offset; i<width; i++){
            for(int j = 0; j<height; j++){
                App.updatedMap.getCell(i,j).setCharacter(Integer.toString(threadId));
                // System.out.println("Thread " + threadId + " is updating cell " + i + " " + j);
            }
        }
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }


    // public 
    // for(int i = offset; i<width; i++){
    //     for(int j = 0; j<height; j++){
    //         if(cellArray)
    //     }
    // }

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
