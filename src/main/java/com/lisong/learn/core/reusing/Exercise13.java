package com.lisong.learn.core.reusing;

import static com.lisong.learn.core.util.Print.print;

public class Exercise13 {

    public static void main(String[] args) {

        SubHomer sh = new SubHomer();
        sh.say(2.7d);
        sh.say("say something");
        sh.say(10);
        sh.say(true);
        sh.say('c');
        sh.say(123L);
        sh.say(new Integer(10));
    }
}

class Homer {

    void say(String s) { print("HomerS(" + s + ")"); }
    void say(int i) { print("HomerI(" + i + ")"); }
    void say(boolean b) { print("HomerB(" + b + ")"); }
}

class SubHomer extends Homer {

    void say(double d) { print("SubHomerD(" + d + ")"); }
}
