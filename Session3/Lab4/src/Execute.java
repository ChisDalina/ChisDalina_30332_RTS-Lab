public class Execute extends Thread{
    Integer monitor1 = null, monitor2 = null;
    int sleep, activityMin, activityMax;

    public Execute(Integer monitor1, Integer monitor2, int activityMin, int activityMax, int sleep){
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.activityMax = activityMax;
        this.activityMin = activityMin;
        this.sleep = sleep;
    }

    public void work(){
        System.out.println(this.getName() + " - STATE 2 - gathered monitor");
        int k = (int) Math.round(Math.random()*(activityMax - activityMin) + activityMin);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        try{
            Thread.sleep(1000 * sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getName() + " - STATE 3 - released monitor");
    }
    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        if(monitor1 != null && monitor2 != null){
            synchronized (monitor1){
                synchronized (monitor2){
                    work();
                }
            }
            System.out.println(this.getName() + " - FINISHED");
        } else if(monitor1 != null){
            synchronized (monitor1){
                work();
            }
            System.out.println(this.getName() + " - FINISHED");
        } else {
            synchronized (monitor2){
                work();
            }
            System.out.println(this.getName() + " - FINISHED");
        }
    }
}