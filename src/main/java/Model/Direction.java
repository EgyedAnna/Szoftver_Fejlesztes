package Model;

public class Direction {
    private String direction;
    private String opposite;

    public Direction(String direction, String opposite) {
        this.direction = direction;
        this.opposite = opposite;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getOpposite() {
        return opposite;
    }

    public void setOpposite(String opposite) {
        this.opposite = opposite;
    }
}
