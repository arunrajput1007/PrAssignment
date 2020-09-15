package com.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SKUnit extends Expression {
    private int unit;
    private char sku;
}
