import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Random;

/**
 * Created by benevolent0505 on 16/12/13.
 */
public class JuniorHighSchool {

    private double[][] gradeMatrix;
    private double[][] rndMatrix;

    private double[][] a1Matrix;
    private double[][] a2Matrix;

    public JuniorHighSchool(double[][] gradeMatrix){
        this.gradeMatrix = gradeMatrix;

        int subjects = gradeMatrix[0].length;
        this.rndMatrix = new double[subjects][subjects];

        Random rnd = new Random();
        for (int i = 0; i < subjects; i++) {
            for (int j = 0; j < subjects; j++) {
                rndMatrix[i][j] = rnd.nextInt(10);
            }
        }
    }

    public double[][] getRndMatrix() {
        return rndMatrix;
    }

    public double[][] getA1Matrix() {
        return a1Matrix;
    }

    public double[][] getA2Matrix() {
        return a2Matrix;
    }

    public void calcA1Matrix() {
        int sRow = 0;
        int eRow = rndMatrix.length - 1;
        int sCol = 0;
        int eCol = rndMatrix[0].length / 2 - 1;

        RealMatrix mLeft =
                MatrixUtils.createRealMatrix(rndMatrix).getSubMatrix(sRow, eRow, sCol, eCol);
        RealMatrix grade =
                MatrixUtils.createRealMatrix(gradeMatrix);

        this.a1Matrix = grade.multiply(mLeft).getData();
    }

    public void calcA2Matrix(double[][] b1Matrix) {
        int sRow = 0;
        int eRow = rndMatrix.length - 1;
        int sCol = rndMatrix[0].length / 2;
        int eCol = rndMatrix[0].length - 1;

        RealMatrix mRight =
                MatrixUtils.createRealMatrix(rndMatrix).getSubMatrix(sRow, eRow, sCol, eCol);
        RealMatrix grade =
                MatrixUtils.createRealMatrix(gradeMatrix);
        RealMatrix b1 =
                MatrixUtils.createRealMatrix(b1Matrix);

        this.a2Matrix = grade.multiply(mRight).multiply(b1).getData();
    }

    public String[][] getAdmissionResult(double[][] admissionMatrix) {
        int row = admissionMatrix.length;
        int col = admissionMatrix[0].length;

        String[][] resultMatrix = new String[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                resultMatrix[i][j] = admissionMatrix[i][j] == 1 ? "合" : "否";
            }
        }

        return resultMatrix;
    }
}
