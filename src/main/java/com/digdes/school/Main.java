package com.digdes.school;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Double start = (double) System.currentTimeMillis();
        JavaSchoolStarter starter = new JavaSchoolStarter();
        try {
            List<Map<String,Object>> result1 = starter
                    .execute("INSERT VALUES 'lastname' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true");
            System.out.println("-----------------------------------------------------");
            result1 = starter
                    .execute("INSERT VALUES 'lastName' = 'Петров' , 'id' = 5, 'age' = 40, 'active' = true");
            System.out.println("-----------------------------------------------------");
            for (Map<String, Object> stringObjectMap : result1) {
                System.out.println(Arrays.toString(new Map[]{stringObjectMap}));
            }
            result1 = starter.execute("SELECT 'id', 'lastName' WHERE 'id'=3");
            System.out.println("-----------------------------------------------------");
            for (Map<String, Object> stringObjectMap : result1) {
                System.out.println(Arrays.toString(new Map[]{stringObjectMap}));
            }
            List<Map<String,Object>> result2 = starter
                    .execute("UPDATE VALUES 'lastName'= 'Шуняев', 'cost'=10.1, 'age'=23 where 'id'=3 AND 'lastName' ilike %Д%");
            System.out.println("-----------------------------------------------------");
            for (Map<String, Object> stringObjectMap : result2) {
                System.out.println(Arrays.toString(new Map[]{stringObjectMap}));
            }
            result2 = starter
                    .execute("UPDATE VALUES 'lastName'= 'Подбельцев', 'cost'=4.1, 'age'=23 where 'id'=5 AND 'lastName' like %тро%");
            System.out.println("-----------------------------------------------------");
            for (Map<String, Object> stringObjectMap : result2) {
                System.out.println(Arrays.toString(new Map[]{stringObjectMap}));
            }
            result2 = starter
                    .execute("select 'lastName', 'cost' where 'id' >= 3 AND 'age' = 23");
            System.out.println("-----------------------------------------------------");
            for (Map<String, Object> stringObjectMap : result2) {
                System.out.println(Arrays.toString(new Map[]{stringObjectMap}));
            }
            result2 = starter
                    .execute("select");
            System.out.println("-----------------------------------------------------");
            for (Map<String, Object> stringObjectMap : result2) {
                System.out.println(Arrays.toString(new Map[]{stringObjectMap}));
            }
            result2 = starter
                    .execute("DELETE");
            System.out.println("-----------------------------------------------------");
            if (result2.isEmpty()) System.out.println("Data Base is empty.");
            else {
                for (Map<String, Object> stringObjectMap : result2) {
                    System.out.println(Arrays.toString(new Map[]{stringObjectMap}));
                }
            }
            Double end = (double) System.currentTimeMillis();
            System.out.println("\n" + (end-start));
        } catch (Exception e){
            System.out.println("Exception");
        }
    }
}
