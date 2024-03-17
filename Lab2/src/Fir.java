import java.util.Observable;
public class Fir extends Observable implements Runnable {
    int id;
    Window win;
    int processorLoad;

    Fir(int id, Window win, int procLoad) {
        this.id = id;
        this.win = win;
        this.processorLoad = procLoad;

    }
    public int getId(){
        return id;
    }

    public void run() {
        int c = 0;
        while (c < 1000) {
            for (int j = 0; j < this.processorLoad; j++) {
                j++;
                j--;
            }
                c++;
            setChanged();
            notifyObservers(c);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.win.setProgressValue(id, c);
            }
        }
    }
