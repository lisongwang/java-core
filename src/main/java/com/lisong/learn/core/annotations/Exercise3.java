package com.lisong.learn.core.annotations;

import com.lisong.learn.core.annotations.db.*;

import java.time.Instant;
import java.util.Date;

@DBTable(name="Member2")
public class Exercise3 {

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
    private String emailAddress;

    public static void main(String[] args) {}
}