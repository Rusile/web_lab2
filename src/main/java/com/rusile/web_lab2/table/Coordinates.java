package com.rusile.web_lab2.table;

import lombok.*;

import java.io.Serializable;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates implements Serializable {
    @Getter
    @Setter
    private int x;
    @Getter
    @Setter
    private double y;
    @Getter
    @Setter
    private float r;
}
