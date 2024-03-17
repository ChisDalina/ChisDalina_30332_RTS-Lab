import java.util.Scanner;

public class Main {
    public static void menu(String[] options) {
        System.out.println("Choose the desired option:");
        for (String option : options) {
            System.out.println(option);
        }
    }
    public static void addComplexNumbers(int real1, int imaginary1, int real2, int imaginary2) {
        int realResult = real1 + real2;
        int imaginaryResult = imaginary1 + imaginary2;
        System.out.println("Result of addition: " + realResult + " + " + imaginaryResult + "i");
    }

    public static void subtractComplexNumbers(int real1, int imaginary1, int real2, int imaginary2) {
        int realResult = real1 - real2;
        int imaginaryResult = imaginary1 - imaginary2;
        System.out.println("Result of subtraction: " + realResult + " + " + imaginaryResult + "i");
    }


    public static void multiplyComplexNumbers(int real1, int imaginary1, int real2, int imaginary2) {
        int realResult = real1 * real2 - imaginary1 * imaginary2;
        int imaginaryResult = real1 * imaginary2 + imaginary1 * real2;
        System.out.println("Result of multiplication: " + realResult + " + " + imaginaryResult + "i");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] options = {"1-Addition", "2-Subtraction", "3-Multiplication"};
        menu(options);

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        System.out.println("Enter the real part of the first complex number:");
        int real1 = scanner.nextInt();
        System.out.println("Enter the imaginary part of the first complex number:");
        int imaginary1 = scanner.nextInt();

        System.out.println("Enter the real part of the second complex number:");
        int real2 = scanner.nextInt();
        System.out.println("Enter the imaginary part of the second complex number:");
        int imaginary2 = scanner.nextInt();

        switch (choice) {
            case 1:
                addComplexNumbers(real1, imaginary1, real2, imaginary2);
                break;
            case 2:
                subtractComplexNumbers(real1, imaginary1, real2, imaginary2);
                break;
            case 3:
                multiplyComplexNumbers(real1, imaginary1, real2, imaginary2);
                break;
        }

        scanner.close();
    }
}
