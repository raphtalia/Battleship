package Data;

public class Matrix<T> {
    private int width;
    private int height;
    private Object data[][];

    public Matrix(int inputWidth, int inputHeight) {
        width = inputWidth;
        height = inputHeight;

        @SuppressWarnings("unchecked")
        T[][] newData = (T[][]) new Object[inputHeight][inputWidth];

        data = newData;
    }

    public Matrix(int inputWidth, int inputHeight, T value) {
        this(inputWidth, inputHeight);
        setAll(value);
    }

    public String toString() {
        // Primarily for debugging
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(data[x][y]);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public T get(int x, int y) {
        @SuppressWarnings("unchecked")
        T value = (T) data[y][x];

        return value;
    }

    public Matrix<T> set(int x, int y, T value) {
        data[y][x] = value;
        return this;
    }

    public Matrix<T> setAll(T value) {
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                data[x][y] = value;
            }
        }

        return this;
    }

    public Matrix<T> setFrom(int startX, int startY, int endX, int endY, T value) {
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                data[x][y] = value;
            }
        }

        return this;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}