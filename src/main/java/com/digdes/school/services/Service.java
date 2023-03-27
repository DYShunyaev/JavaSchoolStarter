package com.digdes.school.services;

import java.util.Locale;

public class Service {
    public String validValue(String val) {
        if (val.equalsIgnoreCase("id")) return "id";
        else if (val.equalsIgnoreCase("lastName")) return "lastName";
        else if (val.equalsIgnoreCase("age")) return "age";
        else if (val.equalsIgnoreCase("cost")) return "cost";
        else return "active";
    }

    public boolean comparisonOperator(Object key, String bool, String val) {
        if (bool.equals("=")) {
            return key.equals(val);
        }
        else if (bool.equals("!=")) {
            return !key.equals(val);
        }
        else if (bool.equalsIgnoreCase("like")) {
            String check;

            if (val.endsWith("%") && !val.startsWith("%")) {
                check = val.replaceAll("%","");
                return String.valueOf(key).matches(check + "(.*)");
            }
            else if (val.startsWith("%") && !val.endsWith("%")) {
                check = val.replaceAll("%","");
                return String.valueOf(key).matches( "(.*)" + check);
            }
            else if (val.startsWith("%") && val.endsWith("%")) {
                check = val.replaceAll("%","");
                return String.valueOf(key).matches( "(.*)" + check + "(.*)");
            }
            else {
                return String.valueOf(key).matches(val);
            }
        }
        else if (bool.equalsIgnoreCase("ilike")) {
            String check;

            if (val.endsWith("%") && !val.startsWith("%")) {
                check = val.replaceAll("%","").toLowerCase(Locale.ROOT);
                return String.valueOf(key).toLowerCase(Locale.ROOT).matches(check + "(.*)");
            }
            else if (val.startsWith("%") && !val.endsWith("%")) {
                check = val.replaceAll("%","").toLowerCase(Locale.ROOT);
                return String.valueOf(key).toLowerCase(Locale.ROOT).matches( "(.*)" + check);
            }
            else if (val.startsWith("%") && val.endsWith("%")) {
                check = val.replaceAll("%","").toLowerCase(Locale.ROOT);
                return String.valueOf(key).toLowerCase(Locale.ROOT).matches( "(.*)" + check + "(.*)");
            }
            else {
                return String.valueOf(key).toLowerCase(Locale.ROOT).matches(val.toLowerCase(Locale.ROOT));
            }
        }
        else {
            Long number = null;
            Long number2 = null;
            Double num = null;
            Double num2 = null;
            if (String.valueOf(key).matches("\\d+.\\d+")){
                num = Double.parseDouble((String) key);
            }
            else {
                number = Long.parseLong((String) key);
            }
            if (val.matches("\\d+.\\d+")) {
                num2 = Double.parseDouble(val);
            }
            else {
                number2 = Long.parseLong(val);
            }
            switch (bool) {
                case ">=" -> {
                    if (num == null && num2 == null) return number >= number2;
                    if (number == null && number2 == null) return num >= num2;
                    if (number == null && num2 == null) return num >= number2;
                    if (num == null && number2 == null) return number >= num2;
                }
                case "<=" -> {
                    if (num == null && num2 == null) return number <= number2;
                    if (number == null && number2 == null) return num <= num2;
                    if (number == null && num2 == null) return num <= number2;
                    if (num == null && number2 == null) return number <= num2;
                }
                case "<" -> {
                    if (num == null && num2 == null) return number < number2;
                    if (number == null && number2 == null) return num < num2;
                    if (number == null && num2 == null) return num < number2;
                    if (num == null && number2 == null) return number < num2;
                }
                case ">" -> {
                    if (num == null && num2 == null) return number > number2;
                    if (number == null && number2 == null) return num > num2;
                    if (number == null && num2 == null) return num > number2;
                    if (num == null && number2 == null) return number > num2;
                }
            }
        }
        return false;
    }
}
