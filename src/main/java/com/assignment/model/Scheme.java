package com.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Scheme {
    private Expression expr;
    private double price;
}
