package goon.util;

public enum Resolution {
    MEDIUM(1024, 768),
    SMALL(800, 600);



    private final int width;
    private final int height;

    Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
