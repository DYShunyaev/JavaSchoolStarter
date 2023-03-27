package com.digdes.school.commands;

import com.digdes.school.services.Service;

import java.util.*;

public class Select {
    private final Service service = new Service();

    public Select() {
    }

    public List<Map<String, Object>> commandSelect(String[] values, String[] condition, List<Map<String, Object>> dataBase) {
        if (values.length == 1 && values[0].equalsIgnoreCase("SELECT") && condition.length < 3) return dataBase;

        List<Map<String, Object>> select = new ArrayList<>();
        if (condition.length < 3) {
            for (Map<String, Object> map: dataBase) {
                select.add(putRow(values, map));
            }
            return select;
        }

        String key, bool, val;

        key = condition[condition.length-3];
        key = service.validValue(key);
        bool = condition[condition.length-2];
        val = condition[condition.length-1];
        if (condition.length > 4) {
            String key2, bool2, val2;

            key2 = condition[condition.length-7];
            key2 = service.validValue(key2);
            bool2 = condition[condition.length-6];
            val2 = condition[condition.length-5];

            if (condition[4].toUpperCase(Locale.ROOT).equals("AND")){
                for (Map<String, Object> map: dataBase) {
                    if (service.comparisonOperator(map.get(key),bool,val) && service.comparisonOperator(map.get(key2),bool2,val2)) {
                        select.add(putRow(values, map));
                    }
                }
            }
            else if (condition[4].toUpperCase(Locale.ROOT).equals("OR")){
                for (Map<String, Object> map: dataBase) {
                    if (service.comparisonOperator(map.get(key),bool,val) || service.comparisonOperator(map.get(key2),bool2,val2)) {
                        select.add(putRow(values, map));
                    }
                }
            }
        }

        else {
            for (Map<String, Object> map: dataBase) {
                if (service.comparisonOperator(map.get(key),bool,val)) {
                    select.add(putRow(values, map));
                }
            }
        }
        return select;
    }

    private static Map<String, Object> putRow(String[] values, Map<String, Object> map) {
        Map<String, Object> row = new HashMap<>();
        for (int i = 1; i < values.length; i++) {
            row.put(values[i],map.get(values[i]));
        }
        return row;
    }
}
