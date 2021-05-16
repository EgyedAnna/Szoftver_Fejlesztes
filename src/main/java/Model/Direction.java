package Model;

public class Direction {
    private String direction;
    private String opposite;

    public Direction(String direction, String opposite) {
        this.direction = direction;
        this.opposite = opposite;
    }

    /**
     * Returns the direction where the snake is heading.
     * @return the direction the snake is heading
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Returns the opposite direction where the snake is heading.
     * @return the opposite direction the snake is heading
     */
    public String getOpposite() {
        return opposite;
    }

}
