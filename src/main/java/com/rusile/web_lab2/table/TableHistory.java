package com.rusile.web_lab2.table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@ToString
@Stateful
@NoArgsConstructor
public class TableHistory {
    @Getter
    @Setter
    private List<Result> history = new ArrayList<>();
}