package Exercitiu;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class ExecutionThread extends Thread {
    Semaphore s1;
    Integer monitor1;
    CyclicBarrier bariera;
    int threadIndex;
    int sleep_min, sleep_max, activity1_min, activity1_max;
    public ExecutionThread(CyclicBarrier bariera, Semaphore s1, Integer monitor1, int sleep_min, int
            sleep_max, int activity1_min, int activity1_max, int threadIndex) {
        this.bariera = bariera;
        this.s1 = s1;
        this.monitor1 = monitor1;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.activity1_min = activity1_min;
        this.activity1_max = activity1_max;
        this.threadIndex = threadIndex;
    }
    public void run() {
        while(true) {
            System.out.println(this.getName() + " - STATE 1");
            try {
                s1.acquire(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (monitor1) {
                monitor1.notify();
            }

            System.out.println(this.getName() + " - STATE 2");
            int aux = (int) Math.round(Math.random() * (activity1_max - activity1_min) + activity1_min);
            for (int i = 0; i < aux * 100000; i++) {
                i++;
                i--;
            }
            try {
                Thread.sleep(Math.round(Math.random() * 3) * 500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(this.getName() + " - STATE 3");

            s1.release(3);
            try {
                Thread.sleep(Math.round(Math.random() * (sleep_max - sleep_min) + sleep_min) * 500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(this.getName() + " - STATE 4");

            try {
                bariera.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


package Exercitiu;

        import java.util.concurrent.BrokenBarrierException;
        import java.util.concurrent.CyclicBarrier;
        import java.util.concurrent.Semaphore;

public class ExecutionThread2 extends Thread {
    Semaphore s1;
    Integer monitor1;
    CyclicBarrier bariera;
    int threadIndex;
    int sleep_min, sleep_max, activity1_min, activity1_max;
    public ExecutionThread2(CyclicBarrier bariera, Semaphore s1, Integer monitor1, int sleep_min, int
            sleep_max, int activity1_min, int activity1_max, int threadIndex) {
        this.bariera = bariera;
        this.s1 = s1;
        this.monitor1 = monitor1;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.activity1_min = activity1_min;
        this.activity1_max = activity1_max;
        this.threadIndex = threadIndex;
    }
    public void run() {
        while(true) {
            System.out.println(this.getName() + " - STATE 1");
            try {
                s1.acquire(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(this.getName() + " - STATE 2");
            int aux = (int) Math.round(Math.random() * (activity1_max - activity1_min) + activity1_min);
            for (int i = 0; i < aux * 100000; i++) {
                i++;
                i--;
            }

            System.out.println(this.getName() + " - STATE 3");

            synchronized (monitor1) {
                try {
                    monitor1.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            s1.release(2);
            try {
                Thread.sleep(Math.round(Math.random() * (sleep_max - sleep_min) + sleep_min) * 500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(this.getName() + " - STATE 4");

            try {
                bariera.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


package Exercitiu;

        import java.util.concurrent.CyclicBarrier;
        import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(3);
        Integer monitor1 = new Integer(1);

        CyclicBarrier bariera = new CyclicBarrier(2, new Runnable() {
            public void run() {
                System.out.println("Barrier Rutine");
            }
        });

        new ExecutionThread(bariera, s, monitor1, 4,6,3,5,1).start();
        new ExecutionThread2(bariera, s, monitor1, 5,7,2,4, 2).start();
    }
}

