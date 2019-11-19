package com.lisong.learn.core.type;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static com.lisong.learn.core.util.Print.print;

/**
 * Combine exercise 22, exercise 23.
 */
public class Exercise23 {

    static class myHandler implements InvocationHandler {
        private IService realObj;
        myHandler(IService proxied) {
            this.realObj = proxied;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //print(proxy);
            if(!method.getName().contains("Else")) {
                return method.invoke(realObj, args);
            }
            print("Begin proxy method: " + method.getName());
            long start = System.currentTimeMillis();
            Object obj = method.invoke(realObj, args);
            print("End proxy method: " + method.getName());
            long end = System.currentTimeMillis();
            print("Time consumed during method: " + method.getName() + " is " + (end - start) + " 毫秒");
            return obj;
        }
    }

    public static void main(String[] args) {

        IService service = (IService) Proxy.newProxyInstance(Exercise23.class.getClassLoader(),
                new Class[]{IService.class}, new myHandler(new ServiceImpl()));
        service.doSomething();
        service.doSomethingElse("yes");
     }
}
