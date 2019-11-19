package com.lisong.learn.core.annotations.atunit;

import com.lisong.learn.core.util.BinaryFile;
import com.lisong.learn.core.util.ProcessFiles;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.lisong.learn.core.util.Print.print;
import static com.lisong.learn.core.util.Print.printnb;

public class AtUnit implements Consumer<File> {
    private static Class<?> testClass;
    private static List<String> failedTests = new ArrayList<>();
    private static long testsRun = 0;
    private static long failures = 0;
    @Override
    public void accept(File file) {
        //Find the class name from byte code
        try {
            String className = ClassNameFinder.thisClass(BinaryFile.read(file));
            if(className == null || !className.contains("."))
                return; //test class must be in some package
            testClass = Class.forName(className);
        } catch (Exception e) {
            print("Can not read class file by location: " + file.getAbsolutePath());
            System.exit(1);
        }
        //collect all the annotated method
        TestMethods methods = new TestMethods();
        Method creator = null;
        Method cleanup = null;
        try {
            for(Method m : testClass.getDeclaredMethods()) {
                methods.addIfTestMethod(m);
                if(creator == null)
                    creator = checkForCreatorMethod(m);
                if(cleanup == null)
                    cleanup = checkForCleanupMethod(m);
            }
        }catch (Exception e) {
            print(e.getMessage());
            System.exit(1); //we stop our test as soon as any invalid test method be detected.
        }

        //check if any test can be run on the test class
        if(methods.size() > 0) {
            if(creator == null) {
                try {
                    if(!Modifier.isPublic(testClass.getDeclaredConstructor().getModifiers())) {
                        print("Error: " + testClass + " default constructor must be public");
                        System.exit(1);
                    }
                } catch (NoSuchMethodException e) {
                    //you can not invoke non-public constructor even setAccessible
                }
            }
            print(testClass.getName());
        }
        //create a test object for each test method
        for(Method m : methods) {
            try {
                printnb("   ." + m.getName() + " ");
                boolean success = false;
                Object testObj = createTestObject(creator);
                //exception thrown from test method consider to be failure test
                try {
                    if(m.getReturnType() == boolean.class)
                        success = (boolean)m.invoke(testObj);
                    else {
                        m.invoke(testObj);
                        success = true;
                    }
                } catch (InvocationTargetException e) {
                    printnb(" " + e.getCause());
                }
                //display test note
                TestNote note = m.getAnnotation(TestNote.class);
                if(note == null)
                    print(success ? "" : " (failed)");
                else
                    print(success ? "  Note: " + note.value() : " (failed)  Note: " + note.value());
                if(!success) {
                    failures++;
                    failedTests.add(testClass.getName() + ": " + m.getName());
                }
                testsRun++;
                if(cleanup != null)
                    cleanup.invoke(null, testObj);
            }catch(Exception e) {
               throw new RuntimeException(e);
            }
        }
    }

    private static class TestMethods extends ArrayList<Method> {
        void addIfTestMethod(Method m) {
            if(m.getAnnotation(Test.class) != null) {
                if(m.getReturnType() != boolean.class
                    && m.getReturnType() != void.class) {
                    throw new RuntimeException("@Test method must return boolean or void: " + m);
                }
                if(m.getParameterCount() > 0)
                    throw new RuntimeException("@Test method must take zero argument: " + m);
                m.setAccessible(true);
                add(m);
            }
        }
    }

    private static Method checkForCreatorMethod(Method m) {
        if(m.getAnnotation(TestObjectCreate.class) != null) {
            if(!m.getReturnType().equals(testClass))
                throw new RuntimeException("@TestObjectCreate method must return instance of class to be tested: " + m);
            if(!Modifier.isStatic(m.getModifiers()))
                throw new RuntimeException("@TestObjectCreate method must be static: " + m);
            if(m.getParameterCount() > 0)
                throw new RuntimeException("@TestObjectCreate method must take zero argument: " + m);
            m.setAccessible(true);
            return m;
        }
        return null;
    }

    private static Method checkForCleanupMethod(Method m) {
        if(m.getAnnotation(TestObjectCleanup.class) != null) {
            if(!m.getReturnType().equals(void.class))
                throw new RuntimeException("@TestObjectCleanup method must return void: " + m);
            if(!Modifier.isStatic(m.getModifiers()))
                throw new RuntimeException("@TestObjectCleanup method must be static: " + m);
            if(m.getParameterCount() != 1 || m.getParameterTypes()[0] != testClass)
                throw new RuntimeException("@TestObjectCleanup method must take only one argument of the tested type: " + m);
            m.setAccessible(true);
            return m;
        }
        return null;
    }

    private static Object createTestObject(Method creator) {
        if(creator == null) {
            try {
                return testClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Can not create a test object. Try using a " +
                        "@TestObjectCreate method.");
            }
        }else {
            try {
                return creator.invoke(null);
            } catch (Exception e) {
                throw new RuntimeException("Couldn't run @TestObjectCreate method.");
            }
        }
    }

    public static void main(String[] args) {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
        new ProcessFiles(".*\\.class", new AtUnit()).start(args);
        if(failures == 0)
            print("OK (" + testsRun + " tests)");
        else {
            print("(" + testsRun + " tests)\n");
            print(">>> " + failures + " FAILURE" + (failures > 1 ? "S" : "") + " <<<");
            failedTests.forEach(s->print("   " + s));
        }
    }
}