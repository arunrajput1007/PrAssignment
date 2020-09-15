package com.assignment.model;

public abstract class Expression {
    public Expression add(SKUnit other){
        return new And(this, other);
    }
}
