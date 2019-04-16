package com.example.activity.conversions;

import android.content.Context;

public class UnitConversion {

    Context context;
    public UnitConversion(Context con){
        context = con;
    }


    public double convert(String value, String measure, String currentFrom, String currentTO){
        double result = 0;
        String resultToDeliver;
        if(measure.contains(ConversionActivity.lenValue)){
            result = convertLength(value, currentFrom, currentTO);
        }else if(measure.contains(ConversionActivity.capValue)){
            result = convertCapacity(value,currentFrom, currentTO);
        }else if(measure.contains(ConversionActivity.weiValue)){
            result = convertWeight(value, currentFrom, currentTO);
        }else if(measure.contains(ConversionActivity.stoValue)){
            result = convertStorage(value, currentFrom, currentTO);
        }else if(measure.contains(ConversionActivity.temValue)){
            result = convertTemperature(value, currentFrom, currentTO);
        }

        return result;
    }

    private double convertLength(String value, String currentFrom, String currentTO){
        double result = 0;
        double valueTyped = Double.valueOf(value);

        if(currentFrom.contains(context.getString(R.string.Kilometers))){
            //FROM KILOMETERS
            if(currentTO.contentEquals(context.getString(R.string.Kilometers))){
                result = valueTyped;
            }else if(currentTO.contentEquals(context.getString(R.string.Meters))){
                result = valueTyped*1000;
            }else if(currentTO.contentEquals(context.getString(R.string.Centimeters))){
                result = valueTyped*1000*100;
            }else if(currentTO.contentEquals(context.getString(R.string.Miles))){
                result = valueTyped*0.6214;
            }else if(currentTO.contentEquals(context.getString(R.string.Yards))){
                result = valueTyped*1094;
            }else if(currentTO.contentEquals(context.getString(R.string.Feet))){
                result = valueTyped*3281;
            }else if(currentTO.contentEquals(context.getString(R.string.Inches))){
                result = valueTyped*39370;
            }
        }else if(currentFrom.contains(context.getString(R.string.Meters))){
            //FROM METERS
            if(currentTO.contentEquals(context.getString(R.string.Kilometers))){
                result = valueTyped*0.001;
            }else if(currentTO.contentEquals(context.getString(R.string.Meters))){
                result = valueTyped;
            }else if(currentTO.contentEquals(context.getString(R.string.Centimeters))){
                result = valueTyped*100;
            }else if(currentTO.contentEquals(context.getString(R.string.Miles))){
                result = valueTyped*0.0006214;
            }else if(currentTO.contentEquals(context.getString(R.string.Yards))){
                result = valueTyped*1.094;
            }else if(currentTO.contentEquals(context.getString(R.string.Feet))){
                result = valueTyped*3.281;
            }else if(currentTO.contentEquals(context.getString(R.string.Inches))){
                result = valueTyped*39.37;
            }
        }else if(currentFrom.contains(context.getString(R.string.Centimeters))){
            //FROM CENTIMETERS
            if(currentTO.contentEquals(context.getString(R.string.Kilometers))){
                result = valueTyped*0.00001;
            }else if(currentTO.contentEquals(context.getString(R.string.Meters))){
                result = valueTyped*0.01;
            }else if(currentTO.contentEquals(context.getString(R.string.Centimeters))){
                result = valueTyped;
            }else if(currentTO.contentEquals(context.getString(R.string.Miles))){
                result = valueTyped*0.000006214;
            }else if(currentTO.contentEquals(context.getString(R.string.Yards))){
                result = valueTyped*0.01094;
            }else if(currentTO.contentEquals(context.getString(R.string.Feet))){
                result = valueTyped*0.03281;
            }else if(currentTO.contentEquals(context.getString(R.string.Inches))){
                result = valueTyped*0.3937;
            }
        }else if(currentFrom.contains(context.getString(R.string.Miles))){
            //FROM MILES
            if(currentTO.contentEquals(context.getString(R.string.Kilometers))){
                result = valueTyped*1.609;
            }else if(currentTO.contentEquals(context.getString(R.string.Meters))){
                result = valueTyped*1609;
            }else if(currentTO.contentEquals(context.getString(R.string.Centimeters))){
                result = valueTyped*160900;
            }else if(currentTO.contentEquals(context.getString(R.string.Miles))){
                result = valueTyped;
            }else if(currentTO.contentEquals(context.getString(R.string.Yards))){
                result = valueTyped*1760;
            }else if(currentTO.contentEquals(context.getString(R.string.Feet))){
                result = valueTyped*5280;
            }else if(currentTO.contentEquals(context.getString(R.string.Inches))){
                result = valueTyped*63360;
            }
        }else if(currentFrom.contains(context.getString(R.string.Yards))){
            //FROM YARDS
            if(currentTO.contentEquals(context.getString(R.string.Kilometers))){
                result = valueTyped*0.0009144;
            }else if(currentTO.contentEquals(context.getString(R.string.Meters))){
                result = valueTyped*0.9144;
            }else if(currentTO.contentEquals(context.getString(R.string.Centimeters))){
                result = valueTyped*91.44;
            }else if(currentTO.contentEquals(context.getString(R.string.Miles))){
                result = valueTyped*0.0005682;
            }else if(currentTO.contentEquals(context.getString(R.string.Yards))){
                result = valueTyped;
            }else if(currentTO.contentEquals(context.getString(R.string.Feet))){
                result = valueTyped*3;
            }else if(currentTO.contentEquals(context.getString(R.string.Inches))){
                result = valueTyped*36;
            }
        }else if(currentFrom.contains(context.getString(R.string.Feet))){
            //FROM FEET
            if(currentTO.contentEquals(context.getString(R.string.Kilometers))){
                result = valueTyped*0.0003048;
            }else if(currentTO.contentEquals(context.getString(R.string.Meters))){
                result = valueTyped*0.3048;
            }else if(currentTO.contentEquals(context.getString(R.string.Centimeters))){
                result = valueTyped*30.48;
            }else if(currentTO.contentEquals(context.getString(R.string.Miles))){
                result = valueTyped*0.0001894;
            }else if(currentTO.contentEquals(context.getString(R.string.Yards))){
                result = valueTyped*0.3333;
            }else if(currentTO.contentEquals(context.getString(R.string.Feet))){
                result = valueTyped;
            }else if(currentTO.contentEquals(context.getString(R.string.Inches))){
                result = valueTyped*12;
            }
        }else if(currentFrom.contains(context.getString(R.string.Inches))){
            //FROM Inches
            if(currentTO.contentEquals(context.getString(R.string.Kilometers))){
                result = valueTyped*0.0000254;
            }else if(currentTO.contentEquals(context.getString(R.string.Meters))){
                result = valueTyped*0.0254;
            }else if(currentTO.contentEquals(context.getString(R.string.Centimeters))){
                result = valueTyped*2.54;
            }else if(currentTO.contentEquals(context.getString(R.string.Miles))){
                result = valueTyped*0.00001578;
            }else if(currentTO.contentEquals(context.getString(R.string.Yards))){
                result = valueTyped*0.02778;
            }else if(currentTO.contentEquals(context.getString(R.string.Feet))){
                result = valueTyped*0.08333;
            }else if(currentTO.contentEquals(context.getString(R.string.Inches))){
                result = valueTyped;
            }
        }
        return result;
    }

    private double convertCapacity(String value, String currentFrom, String currentTO){
        double result = 0;
        double valueTyped = Double.valueOf(value);

        if(currentFrom.contains(context.getString(R.string.Liters))){
            //FROM Liters
            if(currentTO.contentEquals(context.getString(R.string.Liters))){
                result = valueTyped;
            }else if(currentTO.contentEquals(context.getString(R.string.Milliliters))){
                result = valueTyped*1000;
            }else if(currentTO.contentEquals(context.getString(R.string.Gallons))){
                result = valueTyped*0.2642;
            }else if(currentTO.contentEquals(context.getString(R.string.Quarts))){
                result = valueTyped*1.057;
            }else if(currentTO.contentEquals(context.getString(R.string.Fluid))){
                result = valueTyped*33.81;
            }
        }else if(currentFrom.contains(context.getString(R.string.Milliliters))){
            //FROM Milliliters
            if(currentTO.contentEquals(context.getString(R.string.Liters))){
                result = valueTyped*0.001;
            }else if(currentTO.contentEquals(context.getString(R.string.Milliliters))){
                result = valueTyped;
            }else if(currentTO.contentEquals(context.getString(R.string.Gallons))){
                result = valueTyped*0.0002642;
            }else if(currentTO.contentEquals(context.getString(R.string.Quarts))){
                result = valueTyped*0.001057;
            }else if(currentTO.contentEquals(context.getString(R.string.Fluid))){
                result = valueTyped*0.03381;
            }
        }else if(currentFrom.contains(context.getString(R.string.Gallons))){
            //FROM Gallons
            if(currentTO.contentEquals(context.getString(R.string.Liters))){
                result = valueTyped*3.785;
            }else if(currentTO.contentEquals(context.getString(R.string.Milliliters))){
                result = valueTyped*3785;
            }else if(currentTO.contentEquals(context.getString(R.string.Gallons))){
                result = valueTyped;
            }else if(currentTO.contentEquals(context.getString(R.string.Quarts))){
                result = valueTyped*4;
            }else if(currentTO.contentEquals(context.getString(R.string.Fluid))){
                result = valueTyped*128;
            }
        }else if(currentFrom.contains(context.getString(R.string.Quarts))){
            //FROM Quarts
            if(currentTO.contentEquals(context.getString(R.string.Liters))){
                result = valueTyped*0.9464;
            }else if(currentTO.contentEquals(context.getString(R.string.Milliliters))){
                result = valueTyped*946.4;
            }else if(currentTO.contentEquals(context.getString(R.string.Gallons))){
                result = valueTyped*0.25;
            }else if(currentTO.contentEquals(context.getString(R.string.Quarts))){
                result = valueTyped;
            }else if(currentTO.contentEquals(context.getString(R.string.Fluid))){
                result = valueTyped*32;
            }
        }else if(currentFrom.contains(context.getString(R.string.Fluid))){
            //FROM Fluid Oz
            if(currentTO.contentEquals(context.getString(R.string.Liters))){
                result = valueTyped*0.02957;
            }else if(currentTO.contentEquals(context.getString(R.string.Milliliters))){
                result = valueTyped*29.57;
            }else if(currentTO.contentEquals(context.getString(R.string.Gallons))){
                result = valueTyped*0.007813;
            }else if(currentTO.contentEquals(context.getString(R.string.Quarts))){
                result = valueTyped*0.03125;
            }else if(currentTO.contentEquals(context.getString(R.string.Fluid))){
                result = valueTyped;
            }
        }
        return result;
    }

    private double convertWeight(String value, String currentFrom, String currentTO){
        double result = 0;
        double valueTyped = Double.valueOf(value);

        if(currentFrom.contains(context.getString(R.string.Kilogram))){
            //FROM Kilogram
            if(currentTO.contentEquals(context.getString(R.string.Kilogram))){
                result = valueTyped;
            }else if(currentTO.contentEquals(context.getString(R.string.Milligram))){
                result = valueTyped*1000000;
            }else if(currentTO.contentEquals(context.getString(R.string.Tons))){
                result = valueTyped*0.001102;
            }else if(currentTO.contentEquals(context.getString(R.string.Pounds))){
                result = valueTyped*2.205;
            }else if(currentTO.contentEquals(context.getString(R.string.Ounces))){
                result = valueTyped*35.27;
            }
        }else if(currentFrom.contains(context.getString(R.string.Milligram))){
            //FROM Milligram
            if(currentTO.contentEquals(context.getString(R.string.Kilogram))){
                result = valueTyped*0.000001;
            }else if(currentTO.contentEquals(context.getString(R.string.Milligram))){
                result = valueTyped;
            }else if(currentTO.contentEquals(context.getString(R.string.Tons))){
                result = valueTyped*0.000000001102;
            }else if(currentTO.contentEquals(context.getString(R.string.Pounds))){
                result = valueTyped*0.000002205;
            }else if(currentTO.contentEquals(context.getString(R.string.Ounces))){
                result = valueTyped*0.00003527;
            }
        }else if(currentFrom.contains(context.getString(R.string.Tons))){
            //FROM Tons
            if(currentTO.contentEquals(context.getString(R.string.Kilogram))){
                result = valueTyped*907.2;
            }else if(currentTO.contentEquals(context.getString(R.string.Milligram))){
                result = valueTyped*907200000;
            }else if(currentTO.contentEquals(context.getString(R.string.Tons))){
                result = valueTyped;
            }else if(currentTO.contentEquals(context.getString(R.string.Pounds))){
                result = valueTyped*2000;
            }else if(currentTO.contentEquals(context.getString(R.string.Ounces))){
                result = valueTyped*32000;
            }
        }else if(currentFrom.contains(context.getString(R.string.Pounds))){
            //FROM Pounds
            if(currentTO.contentEquals(context.getString(R.string.Kilogram))){
                result = valueTyped*0.4536;
            }else if(currentTO.contentEquals(context.getString(R.string.Milligram))){
                result = valueTyped*453600;
            }else if(currentTO.contentEquals(context.getString(R.string.Tons))){
                result = valueTyped*0.0005;
            }else if(currentTO.contentEquals(context.getString(R.string.Pounds))){
                result = valueTyped;
            }else if(currentTO.contentEquals(context.getString(R.string.Ounces))){
                result = valueTyped*16;
            }
        }else if(currentFrom.contains(context.getString(R.string.Ounces))){
            //FROM Ounces
            if(currentTO.contentEquals(context.getString(R.string.Kilogram))){
                result = valueTyped*0.02835;
            }else if(currentTO.contentEquals(context.getString(R.string.Milligram))){
                result = valueTyped*28350;
            }else if(currentTO.contentEquals(context.getString(R.string.Tons))){
                result = valueTyped*0.00003125;
            }else if(currentTO.contentEquals(context.getString(R.string.Pounds))){
                result = valueTyped*0.0625;
            }else if(currentTO.contentEquals(context.getString(R.string.Ounces))){
                result = valueTyped;
            }
        }

        return result;
    }

    private double convertTemperature(String value, String currentFrom, String currentTO) {
        double result = 0;
        double valueTyped = Double.valueOf(value);
        switch (currentFrom) {
            case "Celsius":
                if (currentTO.contentEquals("Celsius")) {
                    result = valueTyped;
                } else if (currentTO.contentEquals("Fahrenheit")) {
                    result = (valueTyped * 9/5) + 32;
                }
                break;
            case "Fahrenheit":
                if (currentTO.contentEquals("Celsius")) {
                    result = (valueTyped-32)* 5/9;
                } else if (currentTO.contentEquals("Fahrenheit")) {
                    result = valueTyped;
                }
                break;
        }
        return result;
    }

    private double convertStorage(String value, String currentFrom, String currentTO){
        double result = 0;
        double valueTyped = Double.valueOf(value);
        switch (currentFrom) {
            case "terabyte":
                if (currentTO.contentEquals("terabyte")) {
                    result = valueTyped;
                } else if (currentTO.contentEquals("gigabyte")) {
                    result = valueTyped*1024;
                }else if (currentTO.contentEquals("megabyte")) {
                    result = valueTyped*1049000;
                } else if (currentTO.contentEquals("kilobyte")) {
                    result = valueTyped*1074000000;
                }else if (currentTO.contentEquals("byte")) {
                    result = valueTyped*1100000000000.0;
                } else if (currentTO.contentEquals("kilobit")) {
                    result = valueTyped*8590000000.0;
                }else if (currentTO.contentEquals("megabit")) {
                    result = valueTyped*8389000;
                } else if (currentTO.contentEquals("gigabit")) {
                    result = valueTyped * 8192;
                }else if (currentTO.contentEquals("bit")) {
                    result = valueTyped * 8796000000000.0;
                }
                break;
            case "gigabyte":
                if (currentTO.contentEquals("terabyte")) {
                    result = valueTyped*0.0009766;
                } else if (currentTO.contentEquals("gigabyte")) {
                    result = valueTyped;
                }else if (currentTO.contentEquals("megabyte")) {
                    result = valueTyped*1024;
                } else if (currentTO.contentEquals("kilobyte")) {
                    result = valueTyped*1049000;
                }else if (currentTO.contentEquals("byte")) {
                    result = valueTyped*1074000000;
                } else if (currentTO.contentEquals("kilobit")) {
                    result = valueTyped*8389000;
                }else if (currentTO.contentEquals("megabit")) {
                    result = valueTyped*8192;
                } else if (currentTO.contentEquals("gigabit")) {
                    result = valueTyped * 8;
                }else if (currentTO.contentEquals("bit")) {
                    result = valueTyped * 8590000000.0;
                }
                break;
            case "megabyte":
                if (currentTO.contentEquals("terabyte")) {
                    result = valueTyped*0.0000009537;
                } else if (currentTO.contentEquals("gigabyte")) {
                    result = valueTyped*0.0009766;
                }else if (currentTO.contentEquals("megabyte")) {
                    result = valueTyped;
                } else if (currentTO.contentEquals("kilobyte")) {
                    result = valueTyped*1024;
                }else if (currentTO.contentEquals("byte")) {
                    result = valueTyped*1049000;
                } else if (currentTO.contentEquals("kilobit")) {
                    result = valueTyped*8192;
                }else if (currentTO.contentEquals("megabit")) {
                    result = valueTyped*8;
                } else if (currentTO.contentEquals("gigabit")) {
                    result = valueTyped * 0.007813;
                }else if (currentTO.contentEquals("bit")) {
                    result = valueTyped * 8389000;
                }
                break;
            case "kilobyte":
                if (currentTO.contentEquals("terabyte")) {
                    result = valueTyped*0.0000000009313;
                } else if (currentTO.contentEquals("gigabyte")) {
                    result = valueTyped*0.0000009537;
                }else if (currentTO.contentEquals("megabyte")) {
                    result = valueTyped*0.0009766;
                } else if (currentTO.contentEquals("kilobyte")) {
                    result = valueTyped*1;
                }else if (currentTO.contentEquals("byte")) {
                    result = valueTyped*1024;
                } else if (currentTO.contentEquals("kilobit")) {
                    result = valueTyped*8;
                }else if (currentTO.contentEquals("megabit")) {
                    result = valueTyped*0.007813;
                } else if (currentTO.contentEquals("gigabit")) {
                    result = valueTyped * 0.000007629;
                }else if (currentTO.contentEquals("bit")) {
                    result = valueTyped * 8192;
                }
                break;
            case "byte":
                if (currentTO.contentEquals("terabyte")) {
                    result = valueTyped*0.0000000000009095;
                } else if (currentTO.contentEquals("gigabyte")) {
                    result = valueTyped*0.0000000009313;
                }else if (currentTO.contentEquals("megabyte")) {
                    result = valueTyped*0.0000009537;
                } else if (currentTO.contentEquals("kilobyte")) {
                    result = valueTyped*0.0009766;
                }else if (currentTO.contentEquals("byte")) {
                    result = valueTyped;
                } else if (currentTO.contentEquals("kilobit")) {
                    result = valueTyped*0.007813;
                }else if (currentTO.contentEquals("megabit")) {
                    result = valueTyped*0.000007629;
                } else if (currentTO.contentEquals("gigabit")) {
                    result = valueTyped * 0.000000007451;
                }else if (currentTO.contentEquals("bit")) {
                    result = valueTyped * 8;
                }
                break;
            case "kilobit":
                if (currentTO.contentEquals("terabyte")) {
                    result = valueTyped*0.0000000001164;
                } else if (currentTO.contentEquals("gigabyte")) {
                    result = valueTyped*0.0000001192;
                }else if (currentTO.contentEquals("megabyte")) {
                    result = valueTyped*0.0001221;
                } else if (currentTO.contentEquals("kilobyte")) {
                    result = valueTyped*0.125;
                }else if (currentTO.contentEquals("byte")) {
                    result = valueTyped*128;
                } else if (currentTO.contentEquals("kilobit")) {
                    result = valueTyped*1;
                }else if (currentTO.contentEquals("megabit")) {
                    result = valueTyped*0.0009766;
                } else if (currentTO.contentEquals("gigabit")) {
                    result = valueTyped * 0.0000009537;
                }else if (currentTO.contentEquals("bit")) {
                    result = valueTyped * 1024;
                }
                break;
            case "megabit":
                if (currentTO.contentEquals("terabyte")) {
                    result = valueTyped*0.0000001192;
                } else if (currentTO.contentEquals("gigabyte")) {
                    result = valueTyped*0.0001221;
                }else if (currentTO.contentEquals("megabyte")) {
                    result = valueTyped*0.125;
                } else if (currentTO.contentEquals("kilobyte")) {
                    result = valueTyped*128;
                }else if (currentTO.contentEquals("byte")) {
                    result = valueTyped*131100;
                } else if (currentTO.contentEquals("kilobit")) {
                    result = valueTyped*1024;
                }else if (currentTO.contentEquals("megabit")) {
                    result = valueTyped;
                } else if (currentTO.contentEquals("gigabit")) {
                    result = valueTyped * 0.0009766;
                }else if (currentTO.contentEquals("bit")) {
                    result = valueTyped * 1049000;
                }
                break;
            case "gigabit":
                if (currentTO.contentEquals("terabyte")) {
                    result = valueTyped*0.0001221;
                } else if (currentTO.contentEquals("gigabyte")) {
                    result = valueTyped*0.125;
                }else if (currentTO.contentEquals("megabyte")) {
                    result = valueTyped*128;
                } else if (currentTO.contentEquals("kilobyte")) {
                    result = valueTyped*131100;
                }else if (currentTO.contentEquals("byte")) {
                    result = valueTyped*134200000;
                } else if (currentTO.contentEquals("kilobit")) {
                    result = valueTyped*1049000;
                }else if (currentTO.contentEquals("megabit")) {
                    result = valueTyped*1024;
                } else if (currentTO.contentEquals("gigabit")) {
                    result = valueTyped;
                }else if (currentTO.contentEquals("bit")) {
                    result = valueTyped * 1074000000;
                }
                break;
            case "bit":
                if (currentTO.contentEquals("terabyte")) {
                    result = valueTyped*0.0000000000001137;
                } else if (currentTO.contentEquals("gigabyte")) {
                    result = valueTyped*0.0000000001164;
                }else if (currentTO.contentEquals("megabyte")) {
                    result = valueTyped*0.0000001192;
                } else if (currentTO.contentEquals("kilobyte")) {
                    result = valueTyped*0.0001221;
                }else if (currentTO.contentEquals("byte")) {
                    result = valueTyped*0.125;
                } else if (currentTO.contentEquals("kilobit")) {
                    result = valueTyped*0.0009766;
                }else if (currentTO.contentEquals("megabit")) {
                    result = valueTyped*0.0000009537;
                } else if (currentTO.contentEquals("gigabit")) {
                    result = valueTyped * 0.0000000009313;
                }else if (currentTO.contentEquals("bit")) {
                    result = valueTyped;
                }
                break;
        }
        return result;
    }
}
