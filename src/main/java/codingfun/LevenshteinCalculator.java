package codingfun;

public class LevenshteinCalculator {
    private int[][] levMatrix;
    private char[] from;
    private char[] to;

    public int calculateDistance(String from, String to) {
        this.from = from.toCharArray();
        this.to = to.toCharArray();

        final int width = from.length() + 1;
        final int height = to.length() + 1;

        levMatrix = new int[width + 1][height + 1];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                calcFor(i, j);
            }
        }
        return levMatrix[width-1][height-1];
    }

    private void calcFor(int i, int j) {
        if (i == 0) {
            levMatrix[i][j] = j;
            return;
        }

        if (j == 0) {
            levMatrix[i][j] = i;
            return;
        }

        final int a = Math.min(levMatrix[i - 1][j] + 1, levMatrix[i][j - 1] + 1);
        final int b = from[i-1] == to[j-1] ? levMatrix[i - 1][j - 1] : levMatrix[i - 1][j - 1] + 1;
        levMatrix[i][j] =  Math.min(a, b);
    }
}
