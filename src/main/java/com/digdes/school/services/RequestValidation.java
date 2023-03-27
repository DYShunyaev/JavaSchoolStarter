package com.digdes.school.services;

import java.util.Locale;

public class RequestValidation {

    public boolean requestValidation(String[] value, String[] condition) {

        boolean valid = false;
        if (value[0].toUpperCase(Locale.ROOT).startsWith("INSERT")) {
            valid = valuesValidation(value);
        }
        else if (value[0].toUpperCase(Locale.ROOT).startsWith("UPDATE")) {
            if (value.length == 1) return false;
            valid = valuesValidation(value);
            if (!valid) return false;
            valid = conditionValidation(condition);
            if (!valid) return false;
        }
        else if (value[0].toUpperCase(Locale.ROOT).startsWith("SELECT")
                || value[0].toUpperCase(Locale.ROOT).startsWith("DELETE")) {
            if (value.length > 1) {
                for (int i = 1; i < value.length; i++) {
                    valid = keyNameValidation(value[i]);
                    if (!valid) return false;
                }
            }
            if (condition.length > 1) {
                valid = conditionValidation(condition);
                if (!valid) return false;
            }
            if (value.length == 1 && condition.length <= 1) valid = true;
        }
        return valid;
    }

    private static boolean keyNameValidation(String key) {
        boolean valid = false;
        switch (key.toLowerCase(Locale.ROOT)) {
            case "id", "age", "lastname", "cost", "active" -> valid = true;
        }
        return valid;
    }

    private boolean valuesValidation(String[] req) {
        boolean valid = false;
        for (int i = 1; i < req.length; i+=2) {
            if (!keyNameValidation(req[i])) return false;
            switch (req[i].toLowerCase(Locale.ROOT)) {
                case "id" -> {
                    if (req[i+1].matches("\\d*")) valid = true;
                    else return false;
                }
                case "lastname" -> {
                    if (req[i+1].matches("[А-Яа-я]*") || req[i+1].equalsIgnoreCase("null")) valid = true;
                    else {
                        return false;
                    }
                }
                case "age" -> {
                    if (req[i+1].matches("\\d*") || req[i+1].equalsIgnoreCase("null")) valid = true;
                    else return false;
                }
                case "cost" -> {
                    if (req[i+1].matches("\\d*.\\d*|\\d*") || req[i+1].equalsIgnoreCase("null")) valid = true;
                    else return false;
                }
                case "active" -> {
                    if (req[i+1].equalsIgnoreCase("true") || req[i+1].equalsIgnoreCase("false")
                            || req[i+1].equalsIgnoreCase("null")) valid = true;
                    else return false;
                }
            }
        }
        return valid;
    }

    private static boolean conditionOperationValidation(String bool) {
        boolean valid = false;
        switch (bool.toLowerCase(Locale.ROOT)) {
            case "=", "!=", "like", "ilike", ">=", "<=", "<", ">" -> valid = true;
        }
        return valid;
    }

    private boolean conditionValidation(String[] condition) {
        boolean valid;
        String key, bool, val;
        key = condition[condition.length-3];
        bool = condition[condition.length-2];
        val = condition[condition.length-1];
        if (!keyNameValidation(key)) return false;
        valid = conditionValidation(key, bool, val);
        if (condition.length > 4) {
            String key2, bool2, val2, operator;
            key2 = condition[condition.length-7];
            bool2 = condition[condition.length-6];
            val2 = condition[condition.length-5];
            operator = condition[condition.length-4];
            if (!keyNameValidation(key2)) return false;
            if (!(operator.toLowerCase(Locale.ROOT).equals("and")
                    || operator.toLowerCase(Locale.ROOT).equals("or"))) return false;
            valid = conditionValidation(key2,bool2,val2);
        }
        return valid;
    }

    private boolean conditionValidation(String key, String bool, String val) {
        boolean valid = false;
        switch (key.toLowerCase(Locale.ROOT)) {
            case "id" -> {
                if (val.matches("\\d") && conditionOperationValidation(bool)) valid = true;
                else return false;
            }
            case "lastname" -> {
                if (val.matches("[А-Яа-я]*|[А-Яа-я]*(.*)|(.*)[А-Яа-я]*|(.*)[А-Яа-я]*(.*)")
                        && conditionOperationValidation(bool)) valid = true;
                else return false;
            }
            case "age" -> {
                if (val.matches("\\d*") && conditionOperationValidation(bool)) valid = true;
                else return false;
            }
            case "cost" -> {
                if (val.matches("\\d*.\\d*|\\d*") && conditionOperationValidation(bool)) valid = true;
                else return false;
            }
            case "active" -> {
                if (val.equalsIgnoreCase("true") && conditionOperationValidation(bool)
                        || val.equalsIgnoreCase("false") && conditionOperationValidation(bool)) valid = true;
                else return false;
            }
        }
        return valid;
    }
}
