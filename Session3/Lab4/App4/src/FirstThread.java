public class FirstThread extends Thread{

    int sleep,activity_min, activity_max;

    public FirstThread( int sleep, int activity_min, int activity_max) {


        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
    }

    public synchronized void waitNotify(String name) throws InterruptedException{
        System.out.println(name + " waits");
        wait();
    }

    public void run() {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(this.getName() + " - STATE 1");

        int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        synchronized (this) {
            this.notify();
        }

        System.out.println( this.getName() + " - STATE 2");



    }
}
