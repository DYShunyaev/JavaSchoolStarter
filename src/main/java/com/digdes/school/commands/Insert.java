package com.digdes.school.commands;

import java.util.HashMap;
import java.util.Map;

public class Insert {

    public Map<String, Object> commandInsert(String[] values) {
        Map<String, Object> row = new HashMap<>();
        row.put("id",null);
        row.put("lastName",null);
        row.put("age",null);
        row.put("cost", null);
        row.put("active",null);
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            for (int i = 0; i < values.length; i++) {
                if (values[i].equalsIgnoreCase(entry.getKey())) {
                    String val = values[i+1];
                    row.put(entry.getKey(),val);
                    break;
                }
            }
        }
        return row;
    }
}
