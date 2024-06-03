public class Execute extends Thread {

    Integer monitor;

    int sleep_time, activity_min, activity_max;

    public Execute(Integer monitor, int activity_min, int activity_max, int sleep_time) {
        this.monitor = monitor;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.sleep_time = sleep_time;
    }
    public void run() {
        while(true) {

            System.out.println(this.getName() + " - STATE 1");

            synchronized (monitor) {
                System.out.println(this.getName() + " - STATE 2");
                int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }

            }
            System.out.println(this.getName() + " - STATE 3");

            try {
                Thread.sleep(1000 * sleep_time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(this.getName() + " - STATE 4");
        }
    }
}