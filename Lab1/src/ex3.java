import java.util.Random;
public class ex3 {
    public static void main(String[] args) {
        Random rand = new Random();
        int upperbound = 30;
        int[] arr = new int[10];

        for (int i = 0; i < 10; i++) {
            arr[i] = rand.nextInt(upperbound);
            System.out.print(arr[i] + " ");

        }
        System.out.println();
        int n = arr.length;
        int aux = 0;
        for (int i = 0; i<n-1; i++) {
            for (int j =i+1; j < n; j++) {
                if (arr[j]<arr[i]) {
                    aux=arr[j];
                    arr[j]=arr[i];
                    arr[i] = aux;
                }

            }


        }
        for(int i=0;i<n;i++) {
            System.out.print(arr[i] + " ");
        }
    }

}




