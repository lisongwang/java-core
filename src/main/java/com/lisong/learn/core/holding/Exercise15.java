package com.lisong.learn.core.holding;

import com.lisong.learn.core.util.Stack;

import static com.lisong.learn.core.util.Print.printnb;

public class Exercise15 {

    public static void main(String[] args) {

        String expression = "+U+n+c---+e+r+t---+a-+i-+n+t+y---+ -+r+u--+l+e+s---";
        Stack<Character> charStack = new Stack<>();
        char[] charArray = expression.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if(charArray[i] == '+') {
                charStack.push(charArray[i+1]);
            } else if (charArray[i] == '-') {
                printnb(charStack.pop());
            }
        }
    }
}
