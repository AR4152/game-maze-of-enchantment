package comp2120.a3;

public class Enemy {
    private Double attack;
    private Double hitPoint;

    private boolean isBeated = false;

    private Integer X;

    private Integer Y;

    // Constructor
    public Enemy(Double attack, Double hitPoint, Integer X, Integer Y) {
        this.attack = attack;
        this.hitPoint = hitPoint;
        this.X = X;
        this.Y = Y;
    }

    public Double getAttack() {
        return attack;
    }

    public void setAttack(Double attack) {
        this.attack = attack;
    }

    public Double getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(Double hitPoint) {
        this.hitPoint = hitPoint;
    }

    public boolean isBeated() {
        return isBeated;
    }

    public void setBeated(boolean beated) {
        isBeated = beated;
    }

    public Integer getX(){return X;};
    public void setX(Integer x){X = x;}

    public Integer getY(){return Y;};
    public void setY(Integer y){Y = y;}

    @Override
    public String toString() {
        return "Enemy{" +
                "attack=" + attack +
                ", hitPoint=" + hitPoint +
                ", isBeated=" + isBeated +
                ", X=" + X +
                ", Y=" + Y +
                '}';
    }

}
