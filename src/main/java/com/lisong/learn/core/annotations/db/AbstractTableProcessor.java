package com.lisong.learn.core.annotations.db;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.lisong.learn.core.util.Print.print;

public abstract class AbstractTableProcessor implements TableProcessor {

    @Override
    public void process(List<Class<?>> tables) {
        for(Class<?> clazz : tables) {
            String ddl = generateDDL(clazz);
            print(ddl);
            execute(ddl);
        }
    }

    private String generateDDL(Class<?> clazz) {
        DBTable tn = clazz.getDeclaredAnnotation(DBTable.class);
        StringBuilder result = new StringBuilder();
        if(tn != null) {
            String tableName = tn.name().length() == 0 ? clazz.getSimpleName().toUpperCase() : tn.name();
            result.append("CREATE TABLE IF NOT EXISTS " + tableName + "(");
            List<String> fields = new ArrayList<>();
            for(Field field : clazz.getDeclaredFields()) {
                result.append("\n");
                result.append(buildField(field));
                result.append(",");
            }
            return result.substring(0,result.length() - 1) + ");";
        }
        print("Class " + clazz.getName() + " has no DBTable annotations.");
        return result.toString();
    }

    protected abstract String buildField(Field field);
    protected abstract void execute(String sql);
}