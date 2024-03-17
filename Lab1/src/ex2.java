public class ex2 {
    public static void main(String[] args) {
        int[][] matrix1={ {2,3,1},{7,1,6},{9,2,4}};
        int[][] matrix2={ {8,5,3},{3,9,2},{2,7,3}};
        int[][] sum=new int[3][3];
        int[][] product=new int[3][3];

        for (int i=0; i<matrix1.length;++i){
            for (int j=0; j<matrix2.length;++j){
                sum[i][j]=matrix1[i][j]+matrix2[i][j];
            }
        }

        for (int i=0; i<matrix1.length;++i) {
            for (int j = 0; j < matrix2.length; ++j) {
                for (int k = 0; k < matrix1.length; ++k) {
                    product[i][j] =product[i][j]+ matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        for (int i=0; i<matrix1.length;++i) {
            for (int j = 0; j < matrix2.length; ++j) {
                System.out.print(sum[i][j]+"  ");
                }
            System.out.print("\n");
            }

        System.out.println();

        for (int i=0; i<matrix1.length;++i) {
            for (int j = 0; j < matrix2.length; ++j) {
                System.out.print(product[i][j]+"  ");
            }
            System.out.print("\n");
        }

        }
}
