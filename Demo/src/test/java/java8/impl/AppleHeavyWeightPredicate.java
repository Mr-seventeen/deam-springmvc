package java8.impl;

import java8.Apple;
import java8.ApplePredicate;

/**
 * @author: qixiujuan
 * @date: 2019/4/22
 */
public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
