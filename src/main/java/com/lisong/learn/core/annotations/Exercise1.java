package com.lisong.learn.core.annotations;

import com.lisong.learn.core.annotations.db.*;

import java.time.Instant;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.lisong.learn.core.util.Print.print;

@DBTable(name="Member1")
public class Exercise1 {

    @SQLInteger(constraints = @Constraints(primaryKey = true))
    private long id;
    @SQLString(30)
    private String firstName;
    @SQLString(50)
    private String lastName;
    @SQLString(value = 10, constraints = @Constraints(unique = true))
    private String phone;
    @SQLInteger
    private Integer age;
    @SQLString(10)
    private String gender;
    @SQLDate
    private Date joinDate;
    @SQLFloat
    private float fee;
    @SQLTimestamp(constraints = @Constraints(allowNull = false))
    private Instant last_modify;

    public static void main(String[] args){
        if(args.length < 1) {
            print("Provide the table classes...");
            System.exit(0);
        }
        TableProcessor processor = new MysqlTableProcessor();
        Function<String,Class<?>> func = (n)->{
            try {
                return Class.forName(n);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        };
        processor.process(Stream.of(args).map(func).collect(Collectors.toList()));
    }
}