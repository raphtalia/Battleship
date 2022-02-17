package Data;

public class Vector2 {
    private float x;
    private float y;

    public Vector2(float inputX, float inputY) {
        x = inputX;
        y = inputY;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public float magnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(x + other.x, y + other.y);
    }

    public Vector2 subtract(Vector2 other) {
        return new Vector2(x - other.x, y - other.y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
