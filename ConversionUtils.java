package com.macco.unitconvertor;

public class ConversionUtils {
    // Method to handle unit conversions
    public static double convert(String sourceUnit, String destUnit, double value) {
        double result = 0.0;

        // Handle Length conversions
        if (sourceUnit.equals("inch") && destUnit.equals("cm")) {
            result = value * 2.54;
        } else if (sourceUnit.equals("foot") && destUnit.equals("cm")) {
            result = value * 30.48;
        } else if (sourceUnit.equals("yard") && destUnit.equals("cm")) {
            result = value * 91.44;
        } else if (sourceUnit.equals("mile") && destUnit.equals("km")) {
            result = value * 1.60934;
        }

        // Handle Weight conversions
        else if (sourceUnit.equals("pound") && destUnit.equals("kg")) {
            result = value * 0.453592;
        } else if (sourceUnit.equals("ounce") && destUnit.equals("g")) {
            result = value * 28.3495;
        } else if (sourceUnit.equals("ton") && destUnit.equals("kg")) {
            result = value * 907.185;
        }

        // Handle Temperature conversions
        else if (sourceUnit.equals("Celsius") && destUnit.equals("Fahrenheit")) {
            result = (value * 1.8) + 32;
        } else if (sourceUnit.equals("Fahrenheit") && destUnit.equals("Celsius")) {
            result = (value - 32) / 1.8;
        } else if (sourceUnit.equals("Celsius") && destUnit.equals("Kelvin")) {
            result = value + 273.15;
        } else if (sourceUnit.equals("Kelvin") && destUnit.equals("Celsius")) {
            result = value - 273.15;
        }

        // Return the result
        return result;
    }
}

