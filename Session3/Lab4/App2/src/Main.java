public class Main {
    static Integer monitor1 = new Integer(1);
    static Integer monitor2 = new Integer(1);

    public static void main(String[] args) {
        new Execute(monitor1,monitor2, 2, 4,3, 5,4).start();
        new Execute(monitor1,monitor2,3,5,5,7,5).start();
    }

}