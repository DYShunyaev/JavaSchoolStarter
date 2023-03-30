package com.digdes.school.services;

import java.util.Locale;

public class Service {
    /**
     @Service  класс, для выполнения логических операций и валидации ключей Map.
     */
    public String validValue(String value) {
        if (value.equalsIgnoreCase("id")) return "id";
        else if (value.equalsIgnoreCase("lastName")) return "lastName";
        else if (value.equalsIgnoreCase("age")) return "age";
        else if (value.equalsIgnoreCase("cost")) return "cost";
        else return "active";
    }

    /**
     * @comparisonOperatorService() Метод, позволяющий реализовать логические операции сравнения, в аргументе получает: ключ, оператор сравнения и значение.
     */
    public boolean comparisonOperatorService(Object key, String comparisonOperator, String value) {
        /*Большинство аргументов типа "String", поэтому большая часть логики - работа со строками.*/
        if (comparisonOperator
            .equals("=")) {
            return key.equals(value);
        }
        else if (comparisonOperator
            .equals("!=")) {
            return !key.equals(value);
        }
        else if (comparisonOperator
            .equalsIgnoreCase("like")) {
            String check;

            if (value.endsWith("%") && !value.startsWith("%")) {
                check = value.replaceAll("%", "");
                return String.valueOf(key).matches(check + "(.*)");
            } else if (value.startsWith("%") && !value.endsWith("%")) {
                check = value.replaceAll("%", "");
                return String.valueOf(key).matches("(.*)" + check);
            } else if (value.startsWith("%") && value.endsWith("%")) {
                check = value.replaceAll("%", "");
                return String.valueOf(key).matches("(.*)" + check + "(.*)");
            } else {
                return String.valueOf(key).matches(value);
            }

        }
        else if (comparisonOperator
            .equalsIgnoreCase("ilike")) {
            String check;

            if (value.endsWith("%") && !value.startsWith("%")) {
                check = value.replaceAll("%", "").toLowerCase(Locale.ROOT);
                return String.valueOf(key).toLowerCase(Locale.ROOT).matches(check + "(.*)");
            } else if (value.startsWith("%") && !value.endsWith("%")) {
                check = value.replaceAll("%", "").toLowerCase(Locale.ROOT);
                return String.valueOf(key).toLowerCase(Locale.ROOT).matches("(.*)" + check);
            } else if (value.startsWith("%") && value.endsWith("%")) {
                check = value.replaceAll("%", "").toLowerCase(Locale.ROOT);
                return String.valueOf(key).toLowerCase(Locale.ROOT).matches("(.*)" + check + "(.*)");
            } else {
                return String.valueOf(key).toLowerCase(Locale.ROOT).matches(value.toLowerCase(Locale.ROOT));
            }
        }
        else {
            /*Для логических операторов ">,<" строка парсится в Long или Double, для дальнейшего вычисления.*/
            Long number = null;
            Long number2 = null;
            Double num = null;
            Double num2 = null;
            if (String.valueOf(key).matches("\\d+.\\d+")) {
                num = Double.parseDouble((String) key);
            } else {
                number = Long.parseLong((String) key);
            }
            if (value.matches("\\d+.\\d+")) {
                num2 = Double.parseDouble(value);
            } else {
                number2 = Long.parseLong(value);
            }
            switch (comparisonOperator
            ) {
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
