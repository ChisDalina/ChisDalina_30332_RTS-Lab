public class Main {
    public static void main(String[] args) {
        FirstThread executionThread1 = new FirstThread(7, 2, 3);
        SecondThread executionThread21 = new SecondThread(3, 5, executionThread1);
        SecondThread executionThread22 = new SecondThread(4, 6, executionThread1);
        executionThread1.start();
        executionThread21.start();
        executionThread22.start();
    }

}