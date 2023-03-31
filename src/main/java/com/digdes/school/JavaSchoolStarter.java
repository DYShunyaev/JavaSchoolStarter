package com.digdes.school;

import com.digdes.school.commands.Delete;
import com.digdes.school.commands.Insert;
import com.digdes.school.commands.Select;
import com.digdes.school.commands.Update;
import com.digdes.school.services.RequestValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaSchoolStarter {

    private final Update update = new Update();
    private final Insert insert = new Insert();
    private final Select select = new Select();
    private final Delete delete = new Delete();
    private final RequestValidation requestValidation = new RequestValidation();

    public JavaSchoolStarter() {
    }

    List<Map<String, Object>> dataBase = new ArrayList<>();
    /**
     * @execute(): Основной метод.
     * */
    public List<Map<String, Object>> execute(String request) throws Exception {

        String[] values = request
                .replaceAll("(where.+|WHERE.+)", "")
                .replaceAll("[\\s,=]", "")
                .replaceAll("'{2,}", " ")
                .replaceAll("[^\\w+\\s.=А-Яа-я]", " ").split(" ");

        String[] conditions = getCondition(request);

        if (!requestValidation.requestValidation(values, conditions)) {
            System.out.println("""

                    -----------
                    Bad request
                    -----------""");
            throw new NumberFormatException("Bad request");
        }

        if (request.toUpperCase(Locale.ROOT).startsWith("INSERT")) {
            Map<String, Object> string = insert.commandInsert(values);
            dataBase.add(string);
        } else if (request.toUpperCase(Locale.ROOT).startsWith("UPDATE")) {
            dataBase = update.commandUpdate(values, conditions, dataBase);
        } else if (request.toUpperCase(Locale.ROOT).startsWith("SELECT")) {
            return select.commandSelect(values, conditions, dataBase);
        } else if (request.toUpperCase(Locale.ROOT).startsWith("DELETE")) {
            dataBase = delete.commandDelete(values, conditions, dataBase);
        }

        return dataBase;
    }

    public String[] getCondition(String request) {
        Pattern pattern = Pattern.compile("(where.+|WHERE.+)");
        Matcher matcher = pattern.matcher(request);
        StringBuilder val = new StringBuilder();
        int start, end;
        while (matcher.find()) {
            start = matcher.start();
            end = matcher.end();
            for (int i = start; i < end; i++) {
                char sym = request.charAt(i);
                if (sym == '\'') sym = ' ';
                else if (sym == ' ') continue;
                else if (request.charAt(i - 1) >= '0' && request.charAt(i - 1) <= '9') {
                    val.append(sym);
                    continue;
                } else if (sym >= '0' && sym <= '9' || request.charAt(i - 2) >= '0'
                        && request.charAt(i - 2) <= '9' && i != start) {
                    val.append(" ").append(sym);
                    continue;
                } else if (sym == '%' && request.charAt(i - 2) == 'e' || sym == '%' && request.charAt(i - 2) == 'E') {
                    val.append(" ").append(sym);
                    continue;
                }
                val.append(sym);
            }
        }
        return val.toString().split(" ");
    }
}
