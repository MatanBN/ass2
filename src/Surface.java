import biuoop.DrawSurface;

public class Surface {
    private Rectangle frame;
    private Ball balls[];

    public Surface(Rectangle frame, Ball[] balls) {
        this.frame = frame;
        this.balls = balls;
    }

    public Surface(Rectangle frame, Ball ball) {
        this.frame = frame;
        this.balls = new Ball[1];
        this.balls[0] = ball;
        
    }
    
    public void drawBalls(DrawSurface d) {
        for (int i = 0; i < balls.length; ++i) {
            balls[i].drawOn(d);
        }
    }
    
    public void drawFrame(DrawSurface d) {
        frame.drawOn(d);
    }

    public void moveBalls() throws NullPointerException{
        for (int i = 0; i < balls.length; ++i) {
            try {
                Velocity v = balls[i].getVelocity();
                // Check if the ball is in inside the x bounds.
                if (!checkX(balls[i])) {
                    v.setDx(-v.getDx());
                }
                // Check if the ball is in inside the y bounds.
                if (!checkY(balls[i])) {
                    v.setDy(-v.getDy());
                }
                Velocity savedV = new Velocity(v.getDx(),v.getDy());
                setBrakeSpeed(balls[i]);
                balls[i].moveOneStep();
                balls[i].setVelocity(savedV);
            }
            catch (NullPointerException e) {
                System.out.println("Ball #" + i + " Velcotiy isn't initialized");
            }
        }
    }

    /**
     * getBrakeVelocity checks if the velocity will move to ball to a point out of bounds and if so returns a
     * velocity that will get us exactly to the limit, if not it will return the normal velocity.
     * @return the appropriate ball's velocity.
     */
    public void setBrakeSpeed(Ball b) {
        double cX = b.getX();
        double cY = b.getY();
        Velocity v = b.getVelocity();
        int r = b.getSize();
        // Check if the x or y speed will get the ball out of bounds and if so modify the speed.
        if (v.getDx() + cX - r < frame.getX() && v.getDx() < 0) {
            v.setDx(frame.getX() - cX + r);
        } else if (v.getDx() + cX + r > frame.getMaxX() && v.getDx() > 0) {
            v.setDx(frame.getMaxX() - r - cX);
        }
        if (v.getDy() + cY - r < frame.getY() && v.getDy() < 0) {
            v.setDy(frame.getY() + r - cY);
        } else if (v.getDy() + cY + r > frame.getMaxY() && v.getDy() > 0) {
            v.setDy(frame.getMaxY() - r - cY);
        }
    }

    /**
     * checkX checks if the x coordinate is in the bounds of the DrawSurface.
     * @return true if it is in the bounds, otherwise false..
     */
    public boolean checkX(Ball b) {
        double x = b.getX();
        if (x <= frame.getX() || x - b.getSize() <= frame.getX() || x >= frame.getMaxX()
                || x + b.getSize() >= frame.getMaxX()) {
            return false;
        }
        return true;
    }

    /**
     * checkY checks if the y coordinate is in the bounds of the DrawSurface.
     * @return true if it is in the bounds, otherwise false..
     */
    public boolean checkY(Ball b) {
        double y = b.getY();
        if (y <= frame.getY() || y - b.getSize() <= frame.getY() || y >= frame.getMaxY()
                || y + b.getSize() >= frame.getMaxY()) {
            return false;
        }
        return true;
    }
}
