package com.lisong.learn.core.exceptions;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 22, exercise 23, exercise 24.
 */
public class Exercise24 {

    public static void main(String[] args) {

        try {
            FailingConstructor fc = new FailingConstructor();
            try {
                fc.doSomething();
            }finally {
                fc.dispose();
            }
        }catch(ConstructorException e) {
            e.printStackTrace();
        }
    }
}

class ConstructorException extends Exception {}
class DisposeException extends Exception {}

class FailingConstructor {

    private Disposable d1;
    private Disposable d2;

    FailingConstructor() throws ConstructorException {
        print("Enter FailingConstructor.<init>");
        try {
            d1 = new Disposable();
            try {
                if(false)
                    throw new ConstructorException();
                d2 = new Disposable();
            }catch(Exception e) {
                try {
                    d1.dispose();
                }catch(DisposeException e1) {
                    print("Dispose d1 failure " + e1);
                }
                throw e;
            }
        }catch(Exception e) {
            ConstructorException conException =  new ConstructorException();
            conException.initCause(e);
            throw conException;
        }
        print("Constructed FailingConstructor");
    }

    void dispose() {
        print("Disposing FailingConstructor...");
        try {
            d2.dispose();
        }catch(DisposeException e) {
            print("Dispose d2 failure " + e);
        }

        try {
            d1.dispose();
        }catch(DisposeException e) {
            print("Dispose d1 failure " + e);
        }
    }

    void doSomething() { print("Do some thing!"); }
}

class Disposable {

    private static long count = 0;
    private final long id = count++;

    Disposable() {
        print("Enter Disposable.<init> " + id);
        if(id == 2)
            throw new IllegalStateException(""+id);
        print("Constructed Disposable " + id);
    }

    void dispose() throws DisposeException {
        if(id == 1)
            throw new DisposeException();
        print("Dispose " + id);
    }

    @Override
    public String toString() {
        return "Disposable " + id;
    }
}