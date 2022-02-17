package Data;

public class Matrix<T> {
    public int width;
    public int height;
    private Object data[][];

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;

        @SuppressWarnings("unchecked")
        T[][] data = (T[][]) new Object[height][width];

        this.data = data;
    }

    public Matrix(int width, int height, T value) {
        this(width, height);
        this.setAll(value);
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
        for (int x = 0; x < this.height; x++) {
            for (int y = 0; y < this.width; y++) {
                this.data[x][y] = value;
            }
        }

        return this;
    }
}