package com.lisong.learn.core.annotations.atunit;

import com.lisong.learn.core.util.BinaryFile;
import com.lisong.learn.core.util.ProcessFiles;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.function.Consumer;

import static com.lisong.learn.core.util.Print.print;

/**
 * Remove all the field and method with test annotation
 */
public class AtUnitRemover implements Consumer<File> {
    private static boolean remove = false;
    @Override
    public void accept(File file) {
        boolean modified = false;
        try {
            String className = ClassNameFinder.thisClass(BinaryFile.read(file));
            if(className == null || !className.contains("."))
                return; //test class must be in some packages
            ClassPool pool = ClassPool.getDefault();
            CtClass ctClass = pool.get(className);
            //remove method
            for(CtMethod m : ctClass.getDeclaredMethods()) {
                AnnotationsAttribute attr = (AnnotationsAttribute)
                        m.getMethodInfo().getAttribute(AnnotationsAttribute.visibleTag);
                if(attr == null) continue;
                for(Annotation anno : attr.getAnnotations()) {
                    if(anno.getTypeName().startsWith("com.lisong.learn.core.annotations.atunit")) {
                        print(ctClass.getName() + " Method: " + m.getName() + " " + anno);
                        if(remove) {
                            ctClass.removeMethod(m);
                            modified = true;
                        }
                    }
                }
            }
            //remove field
            for(CtField f : ctClass.getDeclaredFields()) {
                AnnotationsAttribute attr = (AnnotationsAttribute)
                        f.getFieldInfo().getAttribute(AnnotationsAttribute.visibleTag);
                if(attr == null) continue;
                for(Annotation anno : attr.getAnnotations()) {
                    if(anno.getTypeName().startsWith("com.lisong.learn.core.annotations.atunit")) {
                        print(ctClass.getName() + " Field: " + f.getName() + " " + anno);
                        if(remove) {
                            ctClass.removeField(f);
                            modified = true;
                        }
                    }
                }
            }
            //override old class file
            if(modified)
                ctClass.toBytecode(new DataOutputStream(new FileOutputStream(file)));
            ctClass.detach();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("-r")) {
            String[] nargs = new String[args.length - 1];
            System.arraycopy(args, 1, nargs, 0, nargs.length);
            args = nargs;
            remove = true;
        }
        new ProcessFiles(".*\\.class", new AtUnitRemover()).start(args);
    }
}