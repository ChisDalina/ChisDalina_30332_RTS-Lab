public class Main {
    public static void main(String[] args) {
        Integer monitor1 = new Integer(1);
        Integer monitor2 = new Integer(1);
        new FirstThread(monitor1, 2, 4).start();
        new SecondThread(monitor1,monitor2, 3, 6).start();
        new ThirdThread(monitor2,2,5).start();
    }
}