package com.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class And extends Expression{
    private Expression expr1;
    private Expression expr2;
}
