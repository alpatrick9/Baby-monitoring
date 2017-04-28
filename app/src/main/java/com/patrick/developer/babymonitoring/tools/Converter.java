package com.patrick.developer.babymonitoring.tools;

import java.math.BigDecimal;

/**
 * Created by developer on 4/27/17.
 */

public class Converter {
    public static float doubleToFloat(Double d) {
        BigDecimal number = new BigDecimal(d);
        return number.floatValue();
    }
}
