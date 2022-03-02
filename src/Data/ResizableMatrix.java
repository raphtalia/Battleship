package Data;

import java.util.ArrayList;

public class ResizableMatrix<T> {
    private ArrayList<ArrayList<T>> data = new ArrayList<ArrayList<T>>();

    public T get(int x, int y) {
        return data.get(x).get(y);
    }

    public ResizableMatrix<T> set(int x, int y, T value) {
        data.get(x).set(y, value);
        return this;
    }

    public int getWidth() {
        return data.size();
    }

    public int getHeight() {
        int height = 0;

        for (int x = 0; x < data.size(); x++) {
            height = Math.max(height, data.get(x).size());
        }

        return height;
    }

    public ResizableMatrix<T> setFrom(int startX, int startY, int endX, int endY, T value) {
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                data.get(x).set(y, value);
            }
        }

        return this;
    }
}
