package com.excilys.android.formation.chatlite.drawing;

public class Circle {

    private float x;
    private float y;
    private float radius;

    public Circle(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public float distance(Circle c) {
        return (float) Math.sqrt(((this.x - c.getX()) * (this.x - c.getX())) + ((this.y - c.getY()) * (this.y - c.getY())));
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getRadius() {
        return this.radius;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}