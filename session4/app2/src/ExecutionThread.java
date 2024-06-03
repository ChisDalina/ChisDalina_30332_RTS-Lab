import java.util.concurrent.locks.Lock;

public class ExecutionThread extends Thread {
    Lock l1, l2;
    int sleep_min, sleep_max, activity1_min, activity1_max, activity2_min, activity2_max;
    public ExecutionThread(Lock l1, Lock l2, int sleep_min, int sleep_max, int activity1_min,
                           int activity1_max, int activity2_min, int activity2_max) {
        this.l1 = l1;
        this.l2 = l2;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.activity1_min = activity1_min;
        this.activity1_max = activity1_max;
        this.activity2_min = activity2_min;
        this.activity2_max = activity2_max;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        int j=(int) Math.round(Math.random()*(activity1_max - activity1_min) + activity1_min);
        for (int i=0;i<j*100000;i++) {
            i++;
            i--;
        }

        while (true) {
            if (l1.tryLock()) {
                System.out.println(this.getName() + " - STATE 2");
                int k=(int) Math.round(Math.random() * (activity2_max - activity2_min) + activity2_min);
                for (int i=0;i<k*100000;i++) {
                    i++;
                    i--;
                }

                if (l2.tryLock()) {
                    System.out.println(this.getName() + " - STATE 3");
                    try {
                        Thread.sleep(Math.round(Math.random() * (sleep_max - sleep_min) + sleep_min) * 500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        l1.unlock();
                        l2.unlock();
                    }
                    break;
                } else {
                    l1.unlock();
                }
            }
        }

        System.out.println(this.getName() + " - STATE 4");
    }
}