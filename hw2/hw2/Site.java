package hw2;

public class Site {
    private boolean onTop;
    private boolean open;
    private boolean onBottom;
    private boolean full;
    private boolean bottomConnected;

    public Site(boolean ot, boolean op, boolean ob) {
        this.onTop = ot;
        this.open = op;
        this.onBottom = ob;
        this.full = false;
        this.bottomConnected = false;
    }

    public void open() {
        this.open = true;
        if (onTop) {
            this.full = true;
        }
        if (onBottom) {
            this.bottomConnected = true;
        }
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isFull() {
        return full;
    }

    public boolean isBottomConnected() {
        return bottomConnected;
    }

    public void setFull() {
        full = true;
    }

    public void setBottomConnected() {
        bottomConnected = true;
    }

    public void setOnTop() {
        onTop = true;
    }

    public void setOnBottom() {
        onBottom = true;
    }
}
