package Data;

public class Matrix<T> {
    public int width;
    public int height;
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
}