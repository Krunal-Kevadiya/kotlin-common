package com.kotlinlibrary.utils.ktx

/**
 * Takes a number, a length unit, and a new unit and converts the number to the new unit.
 * Accepted units: inches, feet, yards, miles, millimeters, centimeters, meters, kilometers
 * Marc Kuniansky
 *
 * @param originalUnit must be a string
 * @param desiredUnit must be a string
 * @return the new double
 */
fun Double?.lengthConvert(originalUnit: String, desiredUnit: String): Double {
    val originalNum: Double = this@lengthConvert ?: 0.0
    //  Make two variable doubles, one the original double and one the new one
    var num2 = 0.0
    //  Store the units into new strings. I find this to be safer, as I can't override the originals this way.
    //  Also convert them to lower case
    val original = originalUnit.toLowerCase()
    val newU = desiredUnit.toLowerCase()
    //  Now there is a series of if statements to check which units are being converted from/to and
    //  to do the proper operation
    when (original) {
        "inches" -> { //  Begin converting from inches
            when (newU) {
                "inches" -> num2 = originalNum
                "feet" ->
                    //  12 inches in a foot, so divide by 12
                    num2 = originalNum / 12.0
                "yards" ->
                    //  36 inches in a yard, so divide by 36
                    num2 = originalNum / 36.0
                "miles" ->
                    //  63,360 inches in a mile
                    num2 = originalNum / 63360.0
                "millimeters" ->
                    //  There are 25.4 millimeters in an inch, so multiply inches by 25.4
                    num2 = originalNum * 25.4
                "centimeters" ->
                    //  2.54cm in an inch, multiply inches by 2.54
                    num2 = originalNum * 2.54
                "meters" ->
                    //  0.0254 meters to an inch, so multiply inches by 0.0254
                    num2 = originalNum * 0.0254
                "kilometers" ->
                    //  0.0000254km in an inch
                    num2 = originalNum * 0.0000254
            }
        } //  End converting from inches
        //  Next, converting from feet
        "feet" -> { //  Begin conversions from feet
            when (newU) {
                "inches" ->
                    //  12 inches in a foot, multiply by 12
                    num2 = originalNum * 12.0
                "feet" -> num2 = originalNum
                "yards" ->
                    //  3 feet in a yard
                    num2 = originalNum / 3.0
                "miles" ->
                    //  5,280 feet in a mile
                    num2 = originalNum / 5280.0
                "millimeters" ->
                    //  304.8 millimeters in a foot
                    num2 = originalNum * 304.8
                "centimeters" ->
                    //  30.48 Centimeters in a foot
                    num2 = originalNum * 30.48
                "meters" ->
                    //  0.3048 meters in a foot
                    num2 = originalNum * 0.3048
                "kilometers" ->
                    //  0.0003048 kilometers in a meter
                    num2 = originalNum * 0.0003048
            }
        } //  End conversions from feet
        //  Next, conversions from yards
        "yards" -> { //  Begin conversions from yards
            when (newU) {
                "inches" ->
                    //  36 inches in a yard
                    num2 = originalNum * 36.0
                "feet" ->
                    //  3 feet to a yard
                    num2 = originalNum * 3.0
                "yards" -> num2 = originalNum
                "miles" ->
                    //  1760 yards in a mile
                    num2 = originalNum / 1760.0
                "millimeters" ->
                    //  914.4 millimeters in a yard
                    num2 = originalNum * 914.4
                "centimeters" ->
                    //  91.44 centimeters in a yard
                    num2 = originalNum * 91.44
                "meters" -> num2 = originalNum * 0.9144
                "kilometers" ->
                    //  1,093.61 yards in a kilometer
                    num2 = originalNum / 1093.61
            }
        } //  End conversions from yards
        //  Next, convert from miles
        "miles" -> { //  Begin conversions from miles
            when (newU) {
                "inches" ->
                    //  6330 inches in a mile
                    num2 = originalNum * 6330.0
                "feet" ->
                    //  5280 feet in a mile
                    num2 = originalNum * 5280.0
                "yards" ->
                    //  1760 yards in a mile
                    num2 = originalNum * 1760.0
                "miles" -> num2 = originalNum
                "millimeters" ->
                    //  1,609,000 millimeters in a mile
                    num2 = originalNum * 1609340.0
                "centimeters" ->
                    //  16,0934 centimeters in a miles
                    num2 = originalNum * 160934.0
                "meters" ->
                    //  1609.34 meters in a mile
                    num2 = originalNum * 1609.34
                "kilometers" ->
                    //  1.60934 kilometers in a mile
                    num2 = originalNum * 1.60934
            }
        } //  End converting from miles
        //  Next, convert from millimeters
        "millimeters" -> { //  Begin converting from millimeters
            when (newU) {
                "inches" -> num2 = originalNum * 25.4
                "feet" -> num2 = originalNum / 304.8
                "yards" -> num2 = originalNum / 914.4
                "miles" -> num2 = originalNum / 1609000.0
                "millimeters" -> num2 = originalNum
                "centimeters" -> num2 = originalNum.metricConvert("milli", "centi")
                "meters" -> num2 = originalNum.metricConvert("milli", "unit")
                "kilometers" -> num2 = originalNum.metricConvert("milli", "kilo")
            }
        } //  End converting from millimeters
        //  Next, convert from centimeters
        "centimeters" -> { //  Begin converting from centimeters
            when (newU) {
                "inches" -> num2 = originalNum / 2.54
                "feet" -> num2 = originalNum / 30.48
                "yards" -> num2 = originalNum / 91.44
                "miles" -> num2 = originalNum / 160934.0
                "millimeters" -> num2 = originalNum.metricConvert("centi", "milli")
                "centimeters" -> num2 = originalNum
                "meters" -> num2 = originalNum.metricConvert("centi", "unit")
                "kilometers" -> num2 = originalNum.metricConvert("centi", "kilo")
            }
        } //  End converting from centimeters
        //  Next, convert from meters
        "meters" -> { //  Begin converting from meters
            when (newU) {
                "inches" -> num2 = originalNum * 39.3701
                "feet" -> num2 = originalNum * 3.28084
                "yards" -> num2 = originalNum * 1.09361
                "miles" -> num2 = originalNum / 1609.34
                "millimeters" -> num2 = originalNum.metricConvert("unit", "milli")
                "centimeters" -> num2 = originalNum.metricConvert("unit", "centi")
                "meters" -> num2 = originalNum
                "kilometers" -> num2 = originalNum.metricConvert("unit", "kilo")
            }
        } //  End converting from meters
        //  Finally, try converting from kilometers
        "kilometers" -> { //  Begin converting from kilometers
            when (newU) {
                "inches" -> num2 = originalNum * 39370.1
                "feet" -> num2 = originalNum * 3280.84
                "yards" -> num2 = originalNum * 1093.61
                "miles" -> num2 = originalNum / 1.60934
                "millimeters" -> num2 = originalNum.metricConvert("kilo", "milli")
                "centimeters" -> num2 = originalNum.metricConvert("kilo", "centi")
                "meters" -> num2 = originalNum.metricConvert("kilo", "unit")
                "kilometers" -> num2 = originalNum
            }
        } //  End converting from kilometers
    }
    //  num2 is the number we want; return it
    return num2
} //  End lengthConvert

/**
 * Takes a number, a temperature unit, and a new unit and converts the number to the new unit.
 * Accepted units: Fahrenheit, Kelvin, Celsius
 * Noah Getz
 *
 * @param originalUnit must be a string
 * @param desiredUnit must be a string
 * @return the new double
 */
fun Double?.tempConvert(originalUnit: String, desiredUnit: String): Double { //  Begin tempConvert
    val originalNum: Double = this@tempConvert ?: 0.0
    //  Make two variable doubles, one the original double and one the new one
    var num2 = 0.0
    val num3: Double
    //  Store the units into new strings. I find this to be safer, as I can't override the originals this way.
    //  Also convert them to lower case
    val original = originalUnit.toLowerCase()
    val newU = desiredUnit.toLowerCase()

    when (original) {
        //  Begin conversion table
        "celsius" -> { //  Begin converting from Celsius
            when (newU) {
                "celsius" -> num2 = originalNum
                "fahrenheit" -> num2 = originalNum * (9.0 / 5.0) + 32.0
                "kelvin" -> num2 = originalNum + 273.15
            }
        } //  end converting from Celsius
        "fahrenheit" -> { //  Begin converting from Fahrenheit
            when (newU) {
                "celsius" -> {
                    num3 = -originalNum - 32.0
                    num2 = num3 * (5.0 / 9.0)
                }
                "fahrenheit" -> num2 = originalNum
                "kelvin" -> num2 = (originalNum - 32.0) * (5.0 / 9.0) + 273.15
            }
        } //  End converting from Fahrenheit
        "kelvin" -> { //  Begin converting from Kelvin
            when (newU) {
                "celsius" -> num2 = originalNum - 273.15
                "fahrenheit" -> num2 = (originalNum - 273.15) * (9.0 / 5.0) + 32.0
                "kelvin" -> num2 = originalNum
            }
        }
    } //  End conversion table
    //  Return the final number, num2
    return num2
} //  End tempConvert

/**
 * Takes a number, a volume unit, and a new unit and converts the number to the new unit.
 * Accepted units: Milliliter, liter, kiloliter, pint, quart, gallon, cup.
 * Noah Getz
 *
 * @param originalUnit must be a string
 * @param desiredUnit must be a string
 * @return the new double
 */
fun Double?.volumeConvert(originalUnit: String, desiredUnit: String): Double { //  Begin volumeConvert
    val originalNum: Double = this@volumeConvert ?: 0.0
    //  Make two variable doubles, one the original double and one the new one
    var num2 = 0.0
    //  Store the units into new strings. I find this to be safer, as I can't override the originals this way.
    //  Also convert them to lower case
    val original = originalUnit.toLowerCase()
    val newU = desiredUnit.toLowerCase()

    when (original) {
        //  Begin conversion table
        "milliliter" -> when (newU) {
            "milliliter" -> num2 = originalNum
            "liter" -> num2 = originalNum.metricConvert("milli", "unit")
            "kiloliter" -> num2 = originalNum.metricConvert("milli", "kilo")
            "pint" -> num2 = originalNum * .00211338
            "quart" -> num2 = originalNum * .00105669
            "gallon" -> num2 = originalNum * .000264172
            "cup" -> num2 = originalNum * .00422675
        }
        "liter" -> when (newU) {
            "milliliter" -> num2 = originalNum.metricConvert("unit", "milli")
            "liter" -> num2 = originalNum
            "kiloliter" -> num2 = originalNum.metricConvert("unit", "kilo")
            "pint" -> num2 = originalNum * 2.11338
            "quart" -> num2 = originalNum * 1.05669
            "gallon" -> num2 = originalNum * 0.264172
            "cup" -> num2 = originalNum * 4.22675
        }
        "kiloliter" -> when (newU) {
            "milliliter" -> num2 = originalNum.metricConvert("kilo", "milli")
            "liter" -> num2 = originalNum.metricConvert("kilo", "unit")
            "kiloliter" -> num2 = originalNum
            "pint" -> num2 = originalNum * 2113.38
            "quart" -> num2 = originalNum * 1056.69
            "gallon" -> num2 = originalNum * 264.172
            "cup" -> num2 = originalNum * 4226.75
        }
        "pint" -> when (newU) {
            "milliliter" -> num2 = originalNum * 473.176
            "liter" -> num2 = originalNum * 0.473176
            "kiloliter" -> num2 = originalNum * .00047176
            "pint" -> num2 = originalNum
            "quart" -> num2 = originalNum * .5
            "gallon" -> num2 = originalNum * .125
            "cup" -> num2 = originalNum * 2.0
        }
        "quart" -> when (newU) {
            "milliliter" -> num2 = originalNum * 946.353
            "liter" -> num2 = originalNum * 0.946353
            "kiloliter" -> num2 = originalNum * .000946353
            "pint" -> num2 = originalNum * 2.0
            "quart" -> num2 = originalNum
            "gallon" -> num2 = originalNum * .25
            "cup" -> num2 = originalNum * 4.0
        }
        "gallon" -> when (newU) {
            "milliliter" -> num2 = originalNum * 3785.41
            "liter" -> num2 = originalNum * 3.78541
            "kiloliter" -> num2 = originalNum * .00378541
            "pint" -> num2 = originalNum * 8.0
            "quart" -> num2 = originalNum * 4.0
            "gallon" -> num2 = originalNum
            "cup" -> num2 = originalNum * 16.0
        }
        "cup" -> when (newU) {
            "milliliter" -> num2 = originalNum * 236.588
            "liter" -> num2 = originalNum * 0.236588
            "kiloliter" -> num2 = originalNum * .000236588
            "pint" -> num2 = originalNum * .5
            "quart" -> num2 = originalNum * .25
            "gallon" -> num2 = originalNum * .0625
            "cup" -> num2 = originalNum
        }
    } //  End conversion table
    //  Return the final number, num2
    return num2
} //  End volume convert

/**
 * Takes a number, a mass unit, and a new unit and converts the number to the new unit.
 * Accepted units: pounds, kilograms, grams, milligrams
 * Noah Getz
 *
 * @param originalUnit must be a string
 * @param desiredUnit must be a string
 * @return the new double
 */
fun Double?.massConvert(originalUnit: String, desiredUnit: String): Double { //  Begin massConvert
    val originalNum: Double = this@massConvert ?: 0.0
    //  Make two variable doubles, one the original double and one the new one
    var num2 = 0.0
    //  Store the units into new strings. I find this to be safer, as I can't override the originals this way.
    //  Also convert them to lower case
    val original = originalUnit.toLowerCase()
    val newU = desiredUnit.toLowerCase()

    when (original) {
        //  Begin conversion table
        "pounds" -> when (newU) {
            "pounds" -> num2 = originalNum
            "kilograms" -> num2 = originalNum * 0.453592
            "grams" -> num2 = originalNum * 453.592
            "milligrams" -> num2 = originalNum * 453592.0
        }
        "kilograms" -> when (newU) {
            "pounds" -> num2 = originalNum * 2.20462
            "kilograms" -> num2 = originalNum
            "grams" -> num2 = originalNum.metricConvert("kilo", "unit")
            "milligrams" -> num2 = originalNum.metricConvert("kilo", "milli")
        }
        "grams" -> when (newU) {
            "pounds" -> num2 = originalNum * 2.20462
            "kilograms" -> num2 = originalNum.metricConvert("unit", "kilo")
            "grams" -> num2 = originalNum
            "milligrams" -> num2 = originalNum.metricConvert("unit", "milli")
        }
        "milligrams" -> when (newU) {
            "pounds" -> num2 = originalNum * .0000022046
            "kilograms" -> num2 = originalNum.metricConvert("milli", "kilo")
            "grams" -> num2 = originalNum.metricConvert("milli", "unit")
            "milligrams" -> num2 = originalNum
        }
    } //  End conversion table
    //  Return the final number
    return num2
} //  End massConvert

/**
 * Takes a number, a pressure unit, and a new unit and converts the number to the new unit.
 * Accepted units: torr, atm, mmHg, barr
 * Noah Getz
 *
 * @param originalUnit must be a string
 * @param desiredUnit must be a string
 * @return the new double
 */
fun Double?.pressureConvert(originalUnit: String, desiredUnit: String): Double {
    val originalNum: Double = this@pressureConvert ?: 0.0
    //  Make two variable doubles, one the original double and one the new one
    val num2: Double
    //  Store the units into new strings. I find this to be safer, as I can't override the originals this way.
    //  Also convert them to lower case
    val original = originalUnit.toLowerCase()
    val newU = desiredUnit.toLowerCase()

    when (original) {
        "torr" -> num2 = when (newU) {
            "torr" -> originalNum
            "atm" -> originalNum * 0.0013157893594
            "mmhg" -> originalNum * 0.99999984999
            else -> originalNum * 0.0013332237
        }
        "atm" -> num2 = when (newU) {
            "atm" -> originalNum
            "torr" -> originalNum * 760.00006601
            "mmhg" -> originalNum * 759.999952
            else -> originalNum * 1.0132501
        }
        "mmhg" -> num2 = when (newU) {
            "mmhg" -> originalNum
            "torr" -> originalNum * 1.00000015
            "atm" -> originalNum * 0.0013157895568
            else -> originalNum * 0.0013332239
        }
        else -> num2 = when (newU) {
            "bar" -> originalNum
            "torr" -> originalNum * 750.06167382
            "atm" -> originalNum * 0.98692316931
            else -> originalNum * 750.0615613
        }
    }
    return num2
}

/**
 * Converts a number from one unit of time to another
 * Units of time supported: seconds, minutes, hours, days, weeks, months, years
 * Marc Kuniansky
 *
 * @param originalUnit must be a valid String matching one of the supported units
 * @param newUnit must be a valid String matching one of the supported units
 * @return a double, the converted number
 */
fun Double?.timeConvert(originalUnit: String, newUnit: String): Double { //  Begin convertTime
    val originalNum: Double = this@timeConvert ?: 0.0
    //  Make two doubles, one that holds the original and one that will be redefined where needed
    var num2 = 0.0
    //  Make two strings, capturing the units fed to the method
    val originalU = originalUnit.toLowerCase()
    val newU = newUnit.toLowerCase()
    //  The series of if statements below figures out what unit to convert from/to, and does so.
    //  Convert from seconds
    when {
        originalU == "seconds" -> //  Begin converting from seconds
            //  Thanks Pam for the suggestion about switch statements- I realized far too late how easy they are to use and how much better they look.
            when (newU) {
                "seconds" -> num2 = originalNum
                "minutes" -> num2 = originalNum / 60.0
                "hours" -> num2 = originalNum / 3600.0
                "days" -> num2 = originalNum * 0.000011574
                "weeks" -> num2 = originalNum * 0.0000016534
                "months" -> num2 = originalNum * 0.00000038027
                "years" -> num2 = originalNum * 0.000000031689
            } //  End converting from seconds
        originalU.contains("minute") -> //  Begin converting from minutes
            when (newU) {
                "minutes" -> num2 = originalNum
                "seconds" -> num2 = originalNum * 60.0
                "hours" -> num2 = originalNum / 60.0
                "days" -> num2 = originalNum / 1440.0
                "weeks" -> num2 = originalNum / 10080.0
                "months" -> num2 = originalNum / 43829.1
                "years" -> num2 = originalNum / 525949.0
            } //  End converting from minutes
        originalU.contains("hour") -> //  Begin converting from hours
            when (newU) {
                "hours" -> num2 = originalNum
                "seconds" -> num2 = originalNum * 3600.0
                "minutes" -> num2 = originalNum * 60.0
                "days" -> num2 = originalNum / 24.0
                "weeks" -> num2 = originalNum / 168.0
                "months" -> num2 = originalNum / 730.484
                "years" -> num2 = originalNum / 8765.81
            } //  End converting from hours
        originalU.contains("day") -> //  Begin converting from days
            when (newU) {
                "days" -> num2 = originalNum
                "seconds" -> num2 = originalNum * 86400.0
                "minutes" -> num2 = originalNum * 1440.0
                "hours" -> num2 = originalNum * 24.0
                "weeks" -> num2 = originalNum / 7.0
                "months" -> num2 = originalNum / 30.4368
                "years" -> num2 = originalNum / 365.242
            } //  End converting from days
        originalU.contains("week") -> //  Begin converting from weeks
            when (newU) {
                "weeks" -> num2 = originalNum
                "seconds" -> num2 = originalNum * 604800.0
                "minutes" -> num2 = originalNum * 10080.0
                "hours" -> num2 = originalNum * 168.0
                "days" -> num2 = originalNum * 7.0
                "months" -> num2 = originalNum / 4.34812
                "years" -> num2 = originalNum / 52.1775
            } //  End converting from weeks
        originalU.contains("month") -> //  Begin converting from months
            when (newU) {
                "months" -> num2 = originalNum
                "seconds" -> num2 = originalNum * 2630000.0
                "minutes" -> num2 = originalNum * 43829.1
                "hours" -> num2 = originalNum * 730.484
                "days" -> num2 = originalNum * 30.4368
                "weeks" -> num2 = originalNum * 4.34812
                "years" -> num2 = originalNum / 12.0
            } //  End converting from months
        else -> //  Begin converting from years
            when (newU) {
                "years" -> num2 = originalNum
                "seconds" -> num2 = originalNum * 31560000.0
                "minutes" -> num2 = originalNum * 525949.0
                "hours" -> num2 = originalNum * 8765.81
                "days" -> num2 = originalNum * 365.242
                "weeks" -> num2 = originalNum * 52.1775
                "months" -> num2 = originalNum * 12.0
            }
    }
    //  Convert from years
    // Convert from months
    // Convert from days
    // Convert from hours
    // Convert from minutes
    // End converting from years
    return num2
} // End convertTime

/**
 * Converts a number from one unit of force to another.
 * Units accepted: Newtons, pound force
 * Marc Kuniansky
 *
 * @param originalUnit must be a valid String matching one of the supported units
 * @param newUnit must be a valid String matching one of the supported units
 * @return a double, the converted unit.
 */
fun Double?.forceConvert(originalUnit: String, newUnit: String): Double { // Begin convertForce
    val originalNum: Double = this@forceConvert ?: 0.0
    // Make two doubles, one that holds the original and one that will be redefined where needed
    var num2 = 0.0
    // Make two strings, capturing the units fed to the method
    val originalU = originalUnit.toLowerCase()
    val newU = newUnit.toLowerCase()
    // The series of switch statements below figures out what unit to convert from/to, and does so.
    when (originalU) {
        "pound force" -> when (newU) {
            "pound force" -> num2 = originalNum
            "newtons" -> num2 = originalNum * 4.448222
        }
        "newtons" -> when (newU) {
            "newtons" -> num2 = originalNum
            "pound force" -> num2 = originalNum / 4.448222
        }
    }
    return num2
} // End convertForce

/**
 * Converts a number from one unit of speed to another
 * Recognized speed units: miles per hour, feet per second, meters per second,
 * kilometers per second, kilometers per hour.
 * Marc Kuniansky
 *
 * @param originalUnit must be a valid String recognized by the method
 * @param newUnit must be a valid String recognized by the method
 * @return double, the converted unit.
 */
fun Double?.speedConvert(originalUnit: String, newUnit: String): Double { // Begin convertSpeed
    val originalNum: Double = this@speedConvert ?: 0.0
    // Make two doubles, one that holds the original and one that will be redefined where needed
    var num2 = 0.0
    // Make two strings, capturing the units fed to the method
    val originalU = originalUnit.toLowerCase()
    val newU = newUnit.toLowerCase()
    // The series of if statements below figures out what unit to convert from/to, and does so.
    when (originalU) {
        // Begin conversion table
        "miles per hour" -> when (newU) {
            // Begin converting from miles per hour
            "miles per hour" -> num2 = originalNum
            "feet per second" -> num2 = originalNum * 1.46667
            "kilometers per second" -> num2 = originalNum * 0.00044704
            "kilometers per hour" -> num2 = originalNum * 1.60934
            "meters per second" -> num2 = originalNum * 0.44704
        } // End converting from miles per hour
        "feet per second" -> when (newU) {
            // Begin converting from feet per second
            "miles per hour" -> num2 = originalNum * 0.681818
            "feet per second" -> num2 = originalNum
            "kilometers per second" -> num2 = originalNum * 0.0003048
            "kilometers per hour" -> num2 = originalNum * 1.09728
            "meters per second" -> num2 = originalNum * 0.3048
        } // End converting from feet per second
        "kilometers per second" -> when (newU) {
            // Begin converting from kilometers per second
            "miles per hour" -> num2 = originalNum * 2236.93629
            "feet per second" -> num2 = originalNum * 3280.8399
            "kilometers per second" -> num2 = originalNum
            "kilometers per hour" -> num2 = originalNum * 3600.0
            "meters per second" -> num2 = originalNum * 0.277778
        } // End converting from kilometers per second
        "kilometers per hour" -> when (newU) {
            // Begin converting from kilometers per hour
            "miles per hour" -> num2 = originalNum * 2.23694
            "feet per second" -> num2 = originalNum * 0.911344
            "kilometers per second" -> num2 = originalNum * 0.000277777778
            "kilometers per hour" -> num2 = originalNum
            "meters per second" -> num2 = originalNum * 0.277778
        } // end converting from kilometers per hour
        "meters per second" -> when (newU) {
            // Begin converting from meters per second
            "miles per hour" -> num2 = originalNum * 2.23694
            "feet per second" -> num2 = originalNum * 3.28084
            "kilometers per second" -> num2 = originalNum * 0.001
            "kilometers per hour" -> num2 = originalNum * 3.6
            "meters per second" -> num2 = originalNum
        } // End converting from meters per second
    } // End conversion table
    // Return the result
    return num2
} // End convertSpeed

/**
 * Converts a number from one unit of speed to another.
 * Accepted units: square inches, square feet, square yards, square miles, square meters, square kilometers, acres
 * Marc Kuniansky
 *
 * @param originalUnit must be a valid String matching one of the supported units
 * @param newUnit must be a valid String matching one of the supported units
 * @return a double, the converted unit.
 */
fun Double?.areaConvert(originalUnit: String, newUnit: String): Double { // Begin convertArea
    val originalNum: Double = this@areaConvert ?: 0.0
    // Make two doubles, one that holds the original and one that will be redefined where needed
    var num2 = 0.0
    // Make two strings, capturing the units fed to the method
    val originalU = originalUnit.toLowerCase()
    val newU = newUnit.toLowerCase()

    when (originalU) {
        // Begin unit conversions
        "square inches" -> when (newU) {
            // Begin converting from square inches
            "square inches" -> num2 = originalNum
            "square feet" -> num2 = originalNum / 144.0
            "square yards" -> num2 = originalNum / 1296.0
            "square miles" -> num2 = originalNum / 4014000000.0
            "acres" -> num2 = originalNum / 6273000.0
            "square kilometers" -> num2 = originalNum / 1550000000.0
            "square meters" -> num2 = originalNum / 1550.0
        } // End converting from square inches
        "square feet" -> when (newU) {
            // Begin converting from square feet
            "square inches" -> num2 = originalNum * 144.0
            "square feet" -> num2 = originalNum
            "square yards" -> num2 = originalNum / 9.0
            "square miles" -> num2 = originalNum / 27880000.0
            "acres" -> num2 = originalNum / 43560.0
            "square kilometers" -> num2 = originalNum / 10760000.0
            "square meters" -> num2 = originalNum / 10.7639
        } // End converting from square feet
        "square yards" -> when (newU) {
            // Begin converting from square yards
            "square inches" -> num2 = originalNum * 1296.0
            "square feet" -> num2 = originalNum * 9.0
            "square yards" -> num2 = originalNum
            "square miles" -> num2 = originalNum / 3098000.0
            "acres" -> num2 = originalNum / 4840.0
            "square kilometers" -> num2 = originalNum / 1196000.0
            "square meters" -> num2 = originalNum / 1.19599
        } // End converting from square yards
        "square miles" -> when (newU) {
            // Begin converting from square miles
            "square inches" -> num2 = originalNum * 4014000000.0
            "square feet" -> num2 = originalNum * 27880000.0
            "square yards" -> num2 = originalNum * 3098000.0
            "square miles" -> num2 = originalNum
            "acres" -> num2 = originalNum * 640.0
            "square kilometers" -> num2 = originalNum * 2.58999
            "square meters" -> num2 = originalNum * 2590000.0
        } // End converting from square miles
        "acres" -> when (newU) {
            // Begin converting from acres
            "square inches" -> num2 = originalNum * 6273000.0
            "square feet" -> num2 = originalNum * 43560.0
            "square yards" -> num2 = originalNum * 4840.0
            "square miles" -> num2 = originalNum / 640.0
            "square acres" -> num2 = originalNum
            "square kilometers" -> num2 = originalNum / 247.105
            "square meters" -> num2 = originalNum * 4046.86
        } // End converting from acres
        "kilo" -> when (newU) {
            // Begin converting from square kilometers
            "square inches" -> num2 = originalNum * 1550000000.0
            "square feet" -> num2 = originalNum * 10760000.0
            "square yards" -> num2 = originalNum * 1196000.0
            "square miles" -> num2 = originalNum / 2.58999
            "acres" -> num2 = originalNum
            "square kilometers" -> num2 = originalNum
            "square meters" -> num2 = originalNum * 1000000.0
        } // End converting from square kilometers
        "square meters" -> when (newU) {
            // Begin converting from square meters
            "square inches" -> num2 = originalNum * 1550.0
            "square feet" -> num2 = originalNum * 10.7639
            "square yards" -> num2 = originalNum * 1.19599
            "square miles" -> num2 = originalNum / 2590000.0
            "acres" -> num2 = originalNum / 4046.86
            "square kilometers" -> num2 = originalNum / 1000000.0
            "square meters" -> num2 = originalNum
        } // End converting from square meters
    } // End conversion table
    // Return the resulting number from the conversion table above
    return num2
} // End convertArea

/**
 * Converts between metric prefixes. The type of unit is unimportant- the metric system operates on a base 10 system
 * and so converting between, say, millimeters and meters is exactly the same as converting between milliliters and liters.
 * Accepted prefixes: yotta, zeta, exa, peta, tera, giga, mega, kilo, hecto, deka, UNIT,
 * deci, centi, milli, micro, nano, pico, femto, atto, zepto, yocto
 * Marc Kuniansky
 *
 * @param originalUnit must be a valid String matching one of the supported units
 * @param newUnit must be a valid String matching one of the supported units
 * @return a double, the converted number
 */
fun Double?.metricConvert(originalUnit: String, newUnit: String): Double { // Begin metricConvert
    val originalNum: Double = this@metricConvert ?: 0.0
    // This can use a slightly different, and much easier, algorithm than the others.
    // Because metric is so well organized, it doesn't matter what number is input- the conversion factors are the same.
    // So if I take the original number and convert it to UNITS (which is x*10^0) then convert from UNITS to the new unit,
    // I can very easily do these conversions with very little work. I will heavily utilize the math class here, I need to
    // use exponents quite a bit to simplify life.
    // First, I will need four doubles: the original number, the UNIT number, the final number, and a variable with which to catch the
    // powers of 10.
    var unitNum = 0.0
    var finalNum = 0.0
    var tenP: Double
    // I like to grab the two strings to prevent accidental editing/deletion. I also send them to lower case.
    val originalUn = originalUnit.toLowerCase()
    val newUn = newUnit.toLowerCase()
    val originalU: String
    originalU = if (originalUn.contains(" ")) {
        originalUn.substring(0, originalUn.indexOf(" "))
    } else {
        originalUn
    }
    // If the string from the MetricActivity spinners is passed, there will be a space. Remove everything after it.
    val newU: String
    newU = if (newUn.contains(" ")) {
        newUn.substring(0, newUn.indexOf(" "))
    } else {
        newUn
    }
    // String newU = newUn.substring(0, newUn.indexOf(" "));
    // Next, I use the first of two switch statements. This converts the original number to UNITS, or x*10^0.
    when (originalU) {
        "yotta" -> {
            // Yotta is 10^24 units
            tenP = allExponents(10.0, 24.0)
            unitNum = tenP * originalNum
        }
        "zeta" -> {
            // Zeta is 10^21
            tenP = allExponents(10.0, 21.0)
            unitNum = tenP * originalNum
        }
        "exa" -> {
            // Exa is 10^18
            tenP = allExponents(10.0, 18.0)
            unitNum = tenP * originalNum
        }
        "peta" -> {
            // Peta is 10^15
            tenP = allExponents(10.0, 15.0)
            unitNum = tenP * originalNum
        }
        "tera" -> {
            // Tera is 10^12
            tenP = allExponents(10.0, 12.0)
            unitNum = tenP * originalNum
        }
        "giga" -> {
            // Giga is 10^9
            tenP = allExponents(10.0, 9.0)
            unitNum = tenP * originalNum
        }
        "mega" -> {
            // Mega is 10^6
            tenP = allExponents(10.0, 6.0)
            unitNum = tenP * originalNum
        }
        "kilo" -> {
            // Kilo is 10^3
            tenP = allExponents(10.0, 3.0)
            unitNum = tenP * originalNum
        }
        "hecto" -> {
            // Hecto is 10^2
            tenP = allExponents(10.0, 2.0)
            unitNum = tenP * originalNum
        }
        "deka" -> {
            // Deka is 10^1
            tenP = allExponents(10.0, 1.0)
            unitNum = tenP * originalNum
        }
        "unit" -> {
            // UNIT is the target, 10^0
            tenP = allExponents(10.0, 0.0)
            unitNum = tenP * originalNum
        }
        "deci" -> {
            // Deci is 10^-1
            tenP = allExponents(10.0, -1.0)
            unitNum = originalNum * tenP
        }
        "centi" -> {
            // Centi is 10^-2
            tenP = allExponents(10.0, -2.0)
            unitNum = originalNum * tenP
        }
        "milli" -> {
            // Milli is 10^-3
            tenP = allExponents(10.0, -3.0)
            unitNum = originalNum * tenP
        }
        "micro" -> {
            // Micro is 10^-6
            tenP = allExponents(10.0, -6.0)
            unitNum = originalNum * tenP
        }
        "nano" -> {
            // Nano is 10^-9
            tenP = allExponents(10.0, -9.0)
            unitNum = originalNum * tenP
        }
        "pico" -> {
            // Pico is 10^-12
            tenP = allExponents(10.0, -12.0)
            unitNum = originalNum * tenP
        }
        "femto" -> {
            // Femto is 10^-15
            tenP = allExponents(10.0, -15.0)
            unitNum = originalNum * tenP
        }
        "atto" -> {
            // Atto is 10^-18
            tenP = allExponents(10.0, -18.0)
            unitNum = originalNum * tenP
        }
        "zepto" -> {
            // Zepto is 10^-21
            tenP = allExponents(10.0, -21.0)
            unitNum = originalNum * tenP
        }
        "yocto" -> {
            // Yocto is 10^-24
            tenP = allExponents(10.0, -24.0)
            unitNum = originalNum * tenP
        }
    }
    // Next is a switch statement for all possible cases of the new unit. It takes
    // the number given by the first switch, unitNum, and converts it to the new unit
    // using math.
    when (newU) {
        // Begin converting from base units (10^0) to new units.
        "yotta" -> {
            // Yotta is 10^24 units
            tenP = allExponents(10.0, 24.0)
            finalNum = unitNum / tenP
        }
        "zeta" -> {
            // Zeta is 10^21
            tenP = allExponents(10.0, 21.0)
            finalNum = unitNum / tenP
        }
        "exa" -> {
            // Exa is 10^18
            tenP = allExponents(10.0, 18.0)
            finalNum = unitNum / tenP
        }
        "peta" -> {
            // Peta is 10^15
            tenP = allExponents(10.0, 15.0)
            finalNum = unitNum / tenP
        }
        "tera" -> {
            // Tera is 10^12
            tenP = allExponents(10.0, 12.0)
            finalNum = unitNum / tenP
        }
        "giga" -> {
            // Giga is 10^9
            tenP = allExponents(10.0, 9.0)
            finalNum = unitNum / tenP
        }
        "mega" -> {
            // Mega is 10^6
            tenP = allExponents(10.0, 6.0)
            finalNum = unitNum / tenP
        }
        "kilo" -> {
            // Kilo is 10^3
            tenP = allExponents(10.0, 3.0)
            finalNum = unitNum / tenP
        }
        "hecto" -> {
            // Hecto is 10^2
            tenP = allExponents(10.0, 2.0)
            finalNum = unitNum / tenP
        }
        "deka" -> {
            // Deka is 10^1
            tenP = allExponents(10.0, 1.0)
            finalNum = unitNum / tenP
        }
        "unit" -> {
            // UNIT is the target, 10^0
            tenP = allExponents(10.0, 0.0)
            finalNum = unitNum / tenP
        }
        "deci" -> {
            // Deci is 10^-1
            tenP = allExponents(10.0, -1.0)
            finalNum = unitNum / tenP
        }
        "centi" -> {
            // Centi is 10^-2
            tenP = allExponents(10.0, -2.0)
            finalNum = unitNum / tenP
        }
        "milli" -> {
            // Milli is 10^-3
            tenP = allExponents(10.0, -3.0)
            finalNum = unitNum / tenP
        }
        "micro" -> {
            // Micro is 10^-6
            tenP = allExponents(10.0, -6.0)
            finalNum = unitNum / tenP
        }
        "nano" -> {
            // Nano is 10^-9
            tenP = allExponents(10.0, -9.0)
            finalNum = unitNum / tenP
        }
        "pico" -> {
            // Pico is 10^-12
            tenP = allExponents(10.0, -12.0)
            finalNum = unitNum / tenP
        }
        "femto" -> {
            // Femto is 10^-15
            tenP = allExponents(10.0, -15.0)
            finalNum = unitNum / tenP
        }
        "atto" -> {
            // Atto is 10^-18
            tenP = allExponents(10.0, -18.0)
            finalNum = unitNum / tenP
        }
        "zepto" -> {
            // Zepto is 10^-21
            tenP = allExponents(10.0, -21.0)
            finalNum = unitNum / tenP
        }
        "yocto" -> {
            // Yocto is 10^-24
            tenP = allExponents(10.0, -24.0)
            finalNum = unitNum / tenP
        }
    } // End converting from base units (10^0) to new units.
    // Finally, return the final number
    return finalNum
} // End metricConvert

/**
 * Helping method which will allow an exponent to be either positive or negative, unlike the math pow() method.
 * Thanks to this page for the idea to do this, and for the skeleton code -
 * http:// stackoverflow.com/questions/4364634/calculate-the-power-of-any-exponent-negative-or-positive
 * Marc Kuniansky
 *
 * @param base must be a valid double
 * @param exponent must be a valid double
 * @return the resulting number
 */
private fun allExponents(base: Double, exponent: Double): Double { // Begin allExponents
    val e = Math.abs(exponent)
    val finalNum: Double
    finalNum = when {
        exponent > 0 -> Math.pow(base, e)
        exponent < 0 -> {
            val p = Math.pow(base, e)
            1 / p
        }
        else -> 1.0
    }

    return finalNum
} // End allExponents
