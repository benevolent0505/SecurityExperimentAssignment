import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mikio on 2016/12/20.
 */
public class Matrix2DTest {

    private Matrix2D matrixA;
    private Matrix2D matrixB;

    @Before
    public void setup() {
        double[][] a = {
                {10, 20},
                {30, 40}
        };
        matrixA = Matrix2D.createMatrix(a);

        double[][] b = {
                {1, 2},
                {3, 4}
        };
        matrixB = Matrix2D.createMatrix(b);
    }

    @Test
    public void add() throws Exception {
        double[][] res = matrixA.add(matrixB).getData();
        double[][] exp = {
                {11, 22},
                {33, 44}
        };

        assertEquals(res, res);
    }

    @Test
    public void subtract() throws Exception {

    }

    @Test
    public void multiply() throws Exception {

    }

    @Test
    public void getInvMatrix() throws Exception {

    }

    @Test
    public void getSubMatrix2D() throws Exception {

    }

    @Test
    public void getData() throws Exception {

    }

}