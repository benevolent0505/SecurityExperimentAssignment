import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by benevolent0505 on 16/12/13.
 */
public class Converter {

    /**
     * 表形式のファイル前提
     */
    public static double[][] convert(String filename) {
        File file = new File("res/" + filename);
        Scanner scanner;

        double[][] matrix;
        try {
            scanner = new Scanner(file);
            scanner.useDelimiter("\n");
            scanner.next(); // 1行目はheaderなので無視

            int row = 0;
            int col = 0;
            while (scanner.hasNext()) {
                String strs[] = scanner.next().split(",", 0);
                col = strs.length - 1;
                row += 1;
            }
            scanner.reset();
            scanner = new Scanner(file);
            scanner.next();

            int counter = 0;
            matrix = new double[row][col];
            while (scanner.hasNext()) {
                String strs[] = scanner.next().split(",", 0);
                for (int i = 0; i < strs.length - 1; i++) {
                    // +1は, 行のheaderを無視するため
                    matrix[counter][i] = Double.parseDouble(strs[i + 1]);
                }
                counter += 1;
            }

            return matrix;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
