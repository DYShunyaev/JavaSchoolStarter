package com.digdes.school.commands;

import com.digdes.school.services.Service;

import java.util.*;

public class Select {
    /**
     * @Select: Класс команды "Select"
     * **/
    private final Service service = new Service();

    public Select() {
    }

    public List<Map<String, Object>> commandSelect(String[] values, String[] condition, List<Map<String, Object>> dataBase) {
        if (values.length == 1 && values[0].equalsIgnoreCase("SELECT") && condition.length < 3) return dataBase;

        List<Map<String, Object>> select = new ArrayList<>();
        if (condition.length < 3) {
            for (Map<String, Object> map : dataBase) {
                select.add(putRow(values, map));
            }
            return select;
        }

        String key, comparisonOperator, value;

        key = condition[condition.length - 3];
        key = service.validValue(key);
        comparisonOperator = condition[condition.length - 2];
        value = condition[condition.length - 1];
        if (condition.length > 4) {
            String key2, comparisonOperator2, value2;

            key2 = condition[condition.length - 7];
            key2 = service.validValue(key2);
            comparisonOperator2 = condition[condition.length - 6];
            value2 = condition[condition.length - 5];

            if (condition[4].toUpperCase(Locale.ROOT).equals("AND")) {
                for (Map<String, Object> map : dataBase) {
                    if (service.comparisonOperatorService(map.get(key), comparisonOperator, value)
                            && service.comparisonOperatorService(map.get(key2), comparisonOperator2, value2)) {
                        select.add(putRow(values, map));
                    }
                }
            } else if (condition[4].toUpperCase(Locale.ROOT).equals("OR")) {
                for (Map<String, Object> map : dataBase) {
                    if (service.comparisonOperatorService(map.get(key), comparisonOperator, value)
                            || service.comparisonOperatorService(map.get(key2), comparisonOperator2, value2)) {
                        select.add(putRow(values, map));
                    }
                }
            }
        } else {
            for (Map<String, Object> map : dataBase) {
                if (service.comparisonOperatorService(map.get(key), comparisonOperator, value)) {
                    select.add(putRow(values, map));
                }
            }
        }
        return select;
    }

    private static Map<String, Object> putRow(String[] values, Map<String, Object> map) {
        Map<String, Object> row = new HashMap<>();
        for (int i = 1; i < values.length; i++) {
            row.put(values[i], map.get(values[i]));
        }
        return row;
    }
}
