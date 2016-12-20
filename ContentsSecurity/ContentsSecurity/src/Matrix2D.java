/**
 * Created by mikio on 2016/12/20.
 */
public class Matrix2D {

    private double[][] matrix;

    private Matrix2D(double[][] matrix) {
        this.matrix = matrix;
    }

    public static Matrix2D createMatrix(double[][] matrix) {
        return new Matrix2D(matrix);
    }

    public Matrix2D add(Matrix2D addMatrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        double[][] resMatrix = new double[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                resMatrix[i][j] = this.matrix[i][j] + addMatrix.getData()[i][j];
            }
        }

        return createMatrix(resMatrix);
    }

    public Matrix2D subtract(Matrix2D subMatrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        double[][] resMatrix = new double[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                resMatrix[i][j] = this.matrix[i][j] - subMatrix.getData()[i][j];
            }
        }

        return createMatrix(resMatrix);
    }

    public Matrix2D multiply(Matrix2D mulMatrix) {
        int row = matrix.length;
        int col = mulMatrix.getData()[0].length;

        double[][] resMatrix = new double[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < col; k++) {
                    resMatrix[i][j] += matrix[i][k] * mulMatrix.getData()[k][j];
                }
            }
        }

        return createMatrix(resMatrix);
    }

    public Matrix2D getInvMatrix() {
        if (matrix.length != matrix[0].length) return null;

        int size = matrix.length;

        double[][] iMatrix = new double[size][size];
        double[][] invMatrix = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                iMatrix[i][j] = i == j ? 1.0 : 0.0;
            }
        }

        // http://thira.plavox.info/blog/2008/06/_c.html
        for (int i = 0; i < size; i++) {
            double buffer = 1 / matrix[i][i];
            for (int j = 0; j < size; j++) {
                matrix[i][j] *= buffer;
                invMatrix[i][j] *= buffer;
            }
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    buffer = matrix[j][i];
                    for (int k = 0; k < size; k++) {
                        matrix[j][k] -= matrix[i][k] * buffer;
                        invMatrix[j][k] -= matrix[i][k] * buffer;
                    }
                }
            }
        }

        return createMatrix(invMatrix);
    }

    public Matrix2D getSubMatrix2D(int startRow, int endRow, int startColumn, int endColumn) {
        int row = endRow - startRow + 1;
        int col = endColumn - startColumn + 1;

        double[][] subMatrix = new double[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                subMatrix[i][j] = this.matrix[i][j];
            }
        }

        return createMatrix(subMatrix);
    }

    public double[][] getData() {
        return this.matrix;
    }

    public static void printMatrix2D(Matrix2D matrix) {
        printMatrix(matrix.getData());
    }

    public static void printMatrix(double[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        Double[][] m = new Double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                m[i][j] = matrix[i][j];
            }
        }

        printMatrix(m);
    }

    public static void printMatrix(Object[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
}
