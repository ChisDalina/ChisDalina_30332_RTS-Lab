public class Main {
    static Integer monitor = new Integer(1);

    public static void main(String[] args) {
        new Execute(monitor,3,6,5).start();
        new Execute(monitor,4,7,3).start();
        new Execute(monitor,5,7,6).start();
    }


}