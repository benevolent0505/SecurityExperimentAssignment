import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Created by benevolent0505 on 16/12/13.
 */
public class PrepSchool {

    private double[][] weightMatrix;
    private double[][] lowLineMatrix;

    private double[][] invRndMatrix;

    private double[][] b1Matrix;
    private double[][] b2Matrix;

    private double[][] aptitudeMatrix;
    private double[][] admissionMatrix;

    public PrepSchool(double[][] weightMatrix, double[][] lowLine) {
        this.weightMatrix = weightMatrix;
        this.lowLineMatrix = lowLine;
    }

    public void receiveRndMatrix(double[][] rndMatrix) {
        this.invRndMatrix = convertInversMatrix(rndMatrix);
    }

    private double[][] convertInversMatrix(double[][] matrix) {
        RealMatrix m = MatrixUtils.createRealMatrix(matrix);
        return MatrixUtils.blockInverse(m, 0).getData();
    }

    public double[][] getB1Matrix() {
        return b1Matrix;
    }

    public void calcB1Matrix() {
        int sRow = invRndMatrix.length / 2;
        int eRow = invRndMatrix.length - 1;
        int sCol = 0;
        int eCol = invRndMatrix[0].length - 1;

        RealMatrix mBottom =
                MatrixUtils.createRealMatrix(invRndMatrix).getSubMatrix(sRow, eRow, sCol, eCol);
        RealMatrix bMatrix = MatrixUtils.createRealMatrix(weightMatrix);

        this.b1Matrix = mBottom.multiply(bMatrix).getData();
    }

    public void calcB2Matrix(double[][] matrix) {
        int sRow = 0;
        int eRow = invRndMatrix.length / 2 - 1;
        int sCol = 0;
        int eCol = invRndMatrix[0].length - 1;

        RealMatrix mTop =
                MatrixUtils.createRealMatrix(invRndMatrix).getSubMatrix(sRow, eRow, sCol, eCol);
        RealMatrix aMatrix = MatrixUtils.createRealMatrix(matrix);
        RealMatrix bMatrix = MatrixUtils.createRealMatrix(weightMatrix);

        this.b2Matrix = aMatrix.multiply(mTop).multiply(bMatrix).getData();
    }

    public void setAptitudeMatrix(double[][] matrix) {
        RealMatrix a = MatrixUtils.createRealMatrix(matrix);
        RealMatrix b = MatrixUtils.createRealMatrix(this.b2Matrix);

        this.aptitudeMatrix = a.add(b).getData();
    }

    public void calcAdmissionMatrix() {
        RealMatrix aptitude = MatrixUtils.createRealMatrix(this.aptitudeMatrix);

        double[][] llMatrix = createLowLineMatrix(this.lowLineMatrix, aptitude.getRowDimension());
        RealMatrix lowline = MatrixUtils.createRealMatrix(llMatrix);

        double[][] result= aptitude.subtract(lowline).getData();

        int row = result.length;
        int col = result[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = result[i][j] >= 0 ? 1 : 0;
            }
        }

        this.admissionMatrix = result;
    }

    private double[][] createLowLineMatrix(double[][] matrix, int studentNum) {
        int row = studentNum;
        int col = matrix[0].length;
        double[][] lowLine = new double[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                lowLine[i][j] = matrix[0][j];
            }
        }

        return lowLine;
    }

    public double[][] getAdmissionMatrix() {
        return admissionMatrix;
    }
}
