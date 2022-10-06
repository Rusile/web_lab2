package com.rusile.web_lab2.table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class ResultRow {
    @Getter
    private final Coordinates coordinates;
    @Getter
    @Setter
    private String currentTime;
    @Getter
    @Setter
    private double scriptTime;
    @Getter
    @Setter
    private boolean isHit;
}
