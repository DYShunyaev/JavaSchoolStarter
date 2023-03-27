package com.digdes.school.commands;

import com.digdes.school.services.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Update {
    private final Service service = new Service();

    public Update() {
    }

    public List<Map<String, Object>> commandUpdate(String[] values, String[] condition, List<Map<String, Object>> dataBase) {
        if (condition.length < 3) {
            for (Map<String, Object> map: dataBase) {
                updateRow(map, values);
            }
            return dataBase;
        }

        String key,val,bool;

        key = condition[condition.length-3];
        key = service.validValue(key);
        bool = condition[condition.length-2];
        val = condition[condition.length-1];

        if (condition.length > 4) {
            String key2, val2, bool2;

            key2 = condition[condition.length-7];
            key2 = service.validValue(key2);
            bool2 = condition[condition.length-6];
            val2 = condition[condition.length-5];

            if (condition[4].toUpperCase(Locale.ROOT).equals("AND")){
                for (Map<String, Object> map: dataBase) {
                    if (service.comparisonOperator(map.get(key),bool,val) && service.comparisonOperator(map.get(key2),bool2,val2)) {
                        updateRow(map, values);
                    }
                }
            }
            else if (condition[4].toUpperCase(Locale.ROOT).equals("OR")) {
                for (Map<String, Object> map: dataBase) {
                    if (service.comparisonOperator(map.get(key),bool,val) || service.comparisonOperator(map.get(key2),bool2,val2)) {
                        updateRow(map, values);
                    }
                }
            }
        }
        else {
            for (Map<String, Object> map: dataBase) {
                if (service.comparisonOperator(map.get(key),bool,val)) {
                    updateRow(map, values);
                }
            }
        }
        return dataBase;
    }

    private static void updateRow(Map<String, Object> map, String[] values) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            for (int i = 0; i < values.length; i++) {
                if (values[i].equalsIgnoreCase(entry.getKey())) {
                    String val1 = values[i + 1];
                    map.put(entry.getKey(), val1);
                    break;
                }
            }
        }
    }
}
