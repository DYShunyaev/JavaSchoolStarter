package com.digdes.school.commands;

import com.digdes.school.services.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Delete {
    private final Service service = new Service();

    public Delete() {
    }

    public List<Map<String, Object>> commandDelete(String[] values, String[] condition, List<Map<String, Object>> dataBase) {
        if (values.length == 1 && values[0].equalsIgnoreCase("DELETE") && condition.length < 3) {
            return new ArrayList<>();
        }
        else if (values.length > 1 && condition.length < 3) {
            for (Map<String, Object> map: dataBase) {
                deleteRow(map, values);
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
                for (int i = 0; i < dataBase.size(); i++) {
                    if (service.comparisonOperator(dataBase.get(i).get(key),bool,val)
                            && service.comparisonOperator(dataBase.get(i).get(key2),bool2,val2)) {
                        if (values.length == 1) {
                            dataBase.remove(i);
                        }
                        else deleteRow(dataBase.get(i), values);
                    }
                }
            }
            else if (condition[4].toUpperCase(Locale.ROOT).equals("OR")) {
                for (int i = 0; i < dataBase.size(); i++) {
                    if (service.comparisonOperator(dataBase.get(i).get(key),bool,val)
                            || service.comparisonOperator(dataBase.get(i).get(key2),bool2,val2)) {
                        if (values.length == 1) {
                            dataBase.remove(i);
                        }
                        else deleteRow(dataBase.get(i), values);
                    }
                }
            }
        }
        else {
            for (Map<String, Object> map: dataBase) {
                if (service.comparisonOperator(map.get(key), bool, val)) {
                    deleteRow(map, values);
                }
            }
        }
        return dataBase;
    }

    private static void deleteRow(Map<String, Object> map, String[] values) {
        for (Map.Entry<String, Object> entry: map.entrySet()) {
            for (int i = 1; i < values.length; i++) {
                if (values[i].equalsIgnoreCase(entry.getKey())) {
                    map.put(entry.getKey(),null);
                    break;
                }
            }
        }
    }
}
