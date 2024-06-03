public class FirstThread extends Thread{

    Integer monitor;

    int activity_min, activity_max;

    public FirstThread(Integer monitor,  int activity_min, int activity_max) {

        this.monitor = monitor;

        this.activity_min = activity_min;
        this.activity_max = activity_max;
    }
    public void run() {

        System.out.println(this.getName() + " - STATE 1");
        synchronized (monitor) {
            System.out.println(this.getName() + " - STATE 2");
            int k = (int) Math.round(Math.random()*(activity_max - activity_min) + activity_min);
            for (int i = 0; i < k * 100000; i++) {
                i++; i--;
            }
            try {
                Thread.sleep(4*500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(this.getName() + " - STATE 3");
    }
}