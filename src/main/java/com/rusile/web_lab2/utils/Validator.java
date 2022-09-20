package com.rusile.web_lab2.utils;

import com.rusile.web_lab2.exception.ValidationException;
import com.rusile.web_lab2.table.Coordinates;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.util.*;

@Stateless
@NoArgsConstructor
public class Validator {
    @Getter
    @Setter
    private  Map<String, String> errors = new HashMap<>();

    @Getter
    @Setter
    private  Set<Float> rVals = new HashSet<>(Arrays.asList(1F, 1.5F, 2F, 2.5F, 3F));
    @Getter
    @Setter
    private  Set<Integer> xVals = new HashSet<>(Arrays.asList(-3, -2, -1, 0, 1, 2, 3, 4, 5));

    public Coordinates validateCoordinates(@NonNull String strX, @NonNull String strY, @NonNull String strR) {
        int x = checkX(strX);
        double y =checkY(strY);
        float r = checkR(strR);
        if (!errors.isEmpty()) {
            return null;
        }
        return new Coordinates(x, y, r);
    }

    private float checkR(String strR) {
        float r;
        try {
            r = Float.parseFloat(strR);
        } catch (NumberFormatException e) {
            errors.put("R", e.getMessage());
            return 0;
        }
        if (!rVals.contains(r)) {
            errors.put("R", "value must be in range {1, 1.5, 2, 2.5, 3}!");
        }
        return r;
    }


    private double checkY(String strY) {
        double y;
        try {
            y = Double.parseDouble(strY);
        } catch (NumberFormatException e) {
            errors.put("Y", e.getMessage());
            return 0;
        }
        if (y < -5 || y > 3) {
            errors.put("Y", "value must be in range (-5;3)!");
        }
        return y;
    }

    private int checkX(String strX) {
        int x;
        try {
            x = Integer.parseInt(strX);
        } catch (NumberFormatException e) {
            errors.put("X", e.getMessage());
            return 0;
        }
        if (!xVals.contains(x)) {
            errors.put("X", "value must be in range {-3, -2, -1, 0, 1, 2, 3, 4, 5}!");
        }
        return x;
    }

}
