package com.digdes.school.commands;

import java.util.HashMap;
import java.util.Map;
public class Insert {
    /**
     * @Insert: Класс команды "Insert"
     * **/

    public Map<String, Object> commandInsert(String[] values) {
        /*Создается объект типа Map, после чего заполняется заранее установленными значениями key,
           value остается пустым.*/
        Map<String, Object> row = new HashMap<>();
        row.put("id", null);
        row.put("lastName", null);
        row.put("age", null);
        row.put("cost", null);
        row.put("active", null);
        for (Map.Entry<String, Object> entry : row.entrySet()) {
            /*С помощью метода "entrySet" получается ключ Map, для сравнения с ключом значения,
             которму необходимо присвоить значение.*/
            for (int i = 1; i < values.length; i++) {
                if (values[i].equalsIgnoreCase(entry.getKey())) {
                    String val = values[i + 1];
                    row.put(entry.getKey(), val);
                    break;
                }
            }
        }
        return row;
    }
}
