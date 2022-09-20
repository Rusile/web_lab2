package com.rusile.web_lab2.utils;

import com.rusile.web_lab2.table.Coordinates;

import javax.ejb.Stateless;

@Stateless
public class HitChecker {
    public boolean isHit(Coordinates coordinates) {
        return checkCircle(coordinates) || checkSquare(coordinates) || checkTriangle(coordinates);
    }

    private boolean checkSquare(Coordinates coordinates) {
        return coordinates.getX() >= 0 &&
                coordinates.getY() >= 0 &&
                coordinates.getX() <= coordinates.getR() &&
                coordinates.getY() <= coordinates.getR() / 2;
    }

    private boolean checkTriangle(Coordinates coordinates) {
        return coordinates.getX() <= 0 &&
                coordinates.getY() >= 0 &&
                coordinates.getY() <= 2 * coordinates.getX() + coordinates.getR();
    }

    private boolean checkCircle(Coordinates coordinates) {
        return coordinates.getX() <= 0 &&
                coordinates.getY() <= 0 &&
                Math.pow(coordinates.getX(), 2) + Math.pow(coordinates.getY(), 2) <= Math.pow(coordinates.getR() / 2, 2);
    }
}
