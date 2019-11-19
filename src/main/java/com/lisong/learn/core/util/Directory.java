package com.lisong.learn.core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.lisong.learn.core.util.Print.pFormat;
import static com.lisong.learn.core.util.Print.pprint;

public class Directory {
    private static final File[] emptyFile = new File[0];
    public static File[] local(File dir, String regex) {
        if(dir == null)
            return emptyFile;
        Pattern p = Pattern.compile(regex);
        return Optional.ofNullable(dir.listFiles((f,n)->p.matcher(n).matches())).orElse(emptyFile);
    }

    public static File[] local(String path, String regex) {
        return local(new File(path), regex);
    }

    public static TreeInfo walk(File start) {
        return recurseDirs(start, ".*");
    }

    public static TreeInfo walk(String start) {
        return recurseDirs(new File(start), ".*");
    }

    public static TreeInfo walk(File start, String regex) {
        return recurseDirs(start, regex);
    }

    public static TreeInfo walk(String start, String regex) {
        return recurseDirs(new File(start), regex);
    }

    private static TreeInfo recurseDirs(File file, String regex) {
        TreeInfo result = new TreeInfo();
        for(File f : Optional.ofNullable(file.listFiles()).orElse(emptyFile)) {
            if(f.isDirectory()) {
                result.dirs.add(f);
                result.addAll(recurseDirs(f, regex));
            }else {
                if(f.getName().matches(regex))
                    result.files.add(f);
            }
        }
        return result;
    }

    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<>();
        public List<File> dirs = new ArrayList<>();
        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }

        @Override
        public String toString() {
            return "dirs: " + pFormat(dirs) + "\n\nfiles: " + pFormat(files);
        }

        private void addAll(TreeInfo treeInfo) {
            files.addAll(treeInfo.files);
            dirs.addAll(treeInfo.dirs);
        }
    }

    public static void main(String[] args) {
        pprint(Directory.walk(new File("JavaCore/src/main/java/com/lisong/learn/core/containers"), ".*My.*").files);
    }
}