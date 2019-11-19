package com.lisong.learn.core.io;

import com.lisong.learn.core.util.ProcessFiles;

import java.io.File;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.lisong.learn.core.util.Print.pprint;

public class Exercise6 {

    public static void main(String[] args) {
        List<File> files = new ArrayList<>();
        long time = LocalDateTime.of(2019, Month.SEPTEMBER, 26, 0,0).atZone(
                ZoneId.systemDefault()).toInstant().toEpochMilli();
        new ProcessFiles(".*\\.java", f->{ if(f.lastModified() >= time)files.add(f); }).start(args);
        pprint(files);
    }
}