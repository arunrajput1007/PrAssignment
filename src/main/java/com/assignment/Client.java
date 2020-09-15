package com.assignment;

import com.assignment.model.And;
import com.assignment.model.Expression;
import com.assignment.model.SKUnit;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Client {
    private static final Map<Character,Double> unitPrice = new HashMap<>();
    private static final Map<Expression,Integer> scheme = new HashMap<>();

    public static void main(String[] args) {
        unitPrice.put('A',50.0);
        unitPrice.put('B',30.0);
        unitPrice.put('C',20.0);
        unitPrice.put('D',15.0);

        scheme.put(new SKUnit(3,'A'),130);
        scheme.put(new SKUnit(2,'B'),45);
        scheme.put(new SKUnit(1,'C').add(new SKUnit(1,'D')),30);

        System.out.println(findTotalPrice(new SKUnit(1,'A').add(new SKUnit(1,'B')).add(new SKUnit(1,'C'))));
        System.out.println(findTotalPrice(new SKUnit(5,'A').add(new SKUnit(5,'B')).add(new SKUnit(1,'C'))));
    }

    public static double findTotalPrice(Expression expression){
        if(expression instanceof SKUnit){
            final SKUnit skUnitExpr = (SKUnit)expression;
            Optional<Map.Entry<Expression,Integer>> found = scheme.entrySet().stream()
                    .filter(entry -> entry.getKey() instanceof SKUnit)
                    .filter(entry -> ((SKUnit) entry.getKey()).getSku() == skUnitExpr.getSku() && skUnitExpr.getUnit() >= ((SKUnit) entry.getKey()).getUnit())
                    .findFirst();
            return found.map(entry -> entry.getValue() + findTotalPrice(new SKUnit(skUnitExpr.getUnit() - ((SKUnit) entry.getKey()).getUnit(), skUnitExpr.getSku())))
                    .orElseGet(() -> unitPrice.get(skUnitExpr.getSku()) * skUnitExpr.getUnit());
        } else if(expression instanceof And){
            And andExpr = (And)expression;
            return findTotalPrice(andExpr.getExpr1()) + findTotalPrice(andExpr.getExpr2());
        }
        return 0.0;
    }
}
