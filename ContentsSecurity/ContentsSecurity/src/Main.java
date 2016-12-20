public class Main {

    public static void main(String[] args) {
        Converter converter = new Converter();

        double[][] weight = converter.convert("omomi.txt");
        double[][] grade = converter.convert("seiseki.txt");
        double[][] lowerLine = converter.convert("saiteiten.txt");

        JuniorHighSchool chugaku = new JuniorHighSchool(grade);
        PrepSchool yobiko = new PrepSchool(weight, lowerLine);

        // 1
        double[][] rndMatrix = chugaku.getRndMatrix();

        // 2
        yobiko.receiveRndMatrix(rndMatrix);

        // 5
        yobiko.calcB1Matrix();

        // 7
        chugaku.calcA1Matrix();
        double[][] a1Matrix = chugaku.getA1Matrix(); // 8

        double[][] b2Matrix = yobiko.getB1Matrix(); // 9

        // 10
        chugaku.calcA2Matrix(b2Matrix);

        // 11
        yobiko.calcB2Matrix(a1Matrix);

        double[][] a2Matrix = chugaku.getA2Matrix(); // 12

        // 13
        yobiko.setAptitudeMatrix(a2Matrix);

        // 14
        yobiko.calcAdmissionMatrix();

        double[][] admissionMatrix = yobiko.getAdmissionMatrix(); // 15

        String[][] result = chugaku.getAdmissionResult(admissionMatrix); // 16

        Matrix2D.printMatrix(result);
    }
}
