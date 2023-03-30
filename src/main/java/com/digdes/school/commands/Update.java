package com.digdes.school.commands;

import com.digdes.school.services.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Update {
    /**
     * @Update: Класс команды "Update"
     * **/
    private final Service service = new Service();

    public Update() {
    }

    public List<Map<String, Object>> commandUpdate(String[] values, String[] condition, List<Map<String, Object>> dataBase) {
        if (condition.length < 3) {
            for (Map<String, Object> map : dataBase) {
                updateRow(map, values);
            }
            return dataBase;
        }
        /*Переменные ключ, значение и оператор сравнения, которые принимаются после ключевого слова "WHERE".**/
        String key, value, comparisonOperator;

        key = condition[condition.length - 3];
        key = service.validValue(key);
        comparisonOperator = condition[condition.length - 2];
        value = condition[condition.length - 1];

        if (condition.length > 4) {
            String key2, value2, comparisonOperator2;

            /* В случае, если запрос состоит из 2х условий, объявляются дополнительные переменные.**/
            key2 = condition[condition.length - 7];
            key2 = service.validValue(key2);
            comparisonOperator2 = condition[condition.length - 6];
            value2 = condition[condition.length - 5];

            /*Проверка ключевых слов OR || AND, после чего выполняется обновление необходимых строк. */
            if (condition[4].toUpperCase(Locale.ROOT).equals("AND")) {
                for (Map<String, Object> map : dataBase) {
                    if (service.comparisonOperatorService(map.get(key), comparisonOperator, value)
                            && service.comparisonOperatorService(map.get(key2), comparisonOperator2, value2)) {
                        updateRow(map, values);
                    }
                }
            } else if (condition[4].toUpperCase(Locale.ROOT).equals("OR")) {
                for (Map<String, Object> map : dataBase) {
                    if (service.comparisonOperatorService(map.get(key), comparisonOperator, value)
                            || service.comparisonOperatorService(map.get(key2), comparisonOperator2, value2)) {
                        updateRow(map, values);
                    }
                }
            }
        } else {
            for (Map<String, Object> map : dataBase) {
                if (service.comparisonOperatorService(map.get(key), comparisonOperator, value)) {
                    updateRow(map, values);
                }
            }
        }
        return dataBase;
    }

    private static void updateRow(Map<String, Object> map, String[] values) {
        /*С помощью метода "entrySet" получается ключ Map, для сравнения с ключом значения,
          которое необходимо изменить.*/
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
