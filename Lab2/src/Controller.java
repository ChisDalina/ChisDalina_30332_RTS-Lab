import java.util.Observable;
public class Controller {
    private Fir fir;
    public Controller(Fir fir){
        this.fir=fir;
    }
    public void start(){
        fir.run();
    }
}
