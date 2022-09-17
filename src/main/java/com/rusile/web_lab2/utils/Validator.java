package com.rusile.web_lab2.utils;

import lombok.ToString;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class Validator {

    public Validator(String strX, String strY, String strR) {
        this.strX = strX;
        this.strY = strY;
        this.strR = strR;
    }

    private final String strX;

    private final String strY;

    private final String strR;

    private Map<String, String> errors;

    private final Set<Float> rVals = new HashSet<>(Arrays.asList(1F, 1.5F, 2F, 2.5F, 3F));
    private final Set<Integer> xVals = new HashSet<>(Arrays.asList(-3, -2, -1, 0, 1, 2, 3, 4, 5));


    public void validateData() {
        throw new IllegalStateException("Pizda");

//        checkX(strX);
//        checkY(strY);
//        checkR(strR);
//        if (!errors.isEmpty()) {
//            writer.println("Caught");
//            throw new ValidationException(errors);
//        }
    }

    private void checkR(String strR) {
        float r;
        try {
            r = Float.parseFloat(strR);
        } catch (NumberFormatException e) {
            errors.put("R", e.getMessage());
            return;
        }
        if (rVals.contains(r)) {
            errors.put("R", "R value must be in range {1, 1.5, 2, 2.5, 3}!");
        }
    }


    private void checkY(String strY) {
        double y;
        try {
            y = Double.parseDouble(strY);
        } catch (NumberFormatException e) {
            errors.put("Y", e.getMessage());
            return;
        }
        if (y < -5 || y > 3) {
            errors.put("Y", "Y value must be in range (-5;3)!");
        }
    }

    private void checkX(String strX) {
        int x;
        try {
            x = Integer.parseInt(strX);
        } catch (NumberFormatException e) {
            errors.put("X", e.getMessage());
            return;
        }
        if (!xVals.contains(x)) {
            errors.put("X", "X value must be in range {-3, -2, -1, 0, 1, 2, 3, 4, 5}!");
        }
    }

}
