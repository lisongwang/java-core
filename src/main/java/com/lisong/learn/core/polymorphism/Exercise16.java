package com.lisong.learn.core.polymorphism;

import static com.lisong.learn.core.util.Print.print;

public class Exercise16 {

    public static void main(String[] args) {

        StarShip ss = new StarShip();
        ss.showAlert();
        ss.promote();
        ss.showAlert();
        ss.promote();
        ss.showAlert();
        ss.demote();
        ss.showAlert();
    }
}

class StarShip {

    private AlertStatus alert = new Information();

    public void promote() {
        if(alert instanceof Warning)
            alert = new Emgerency();
        else if(alert instanceof Information)
            alert = new Warning();
    }
    public void demote() {
        if(alert instanceof Warning)
            alert = new Information();
        else if(alert instanceof Emgerency)
            alert = new Warning();
    }

    public void showAlert() {
        alert.alert();
    }
}

class AlertStatus {

    public void alert() {
        print("AlertStatus!");
    };
}

class Warning extends AlertStatus {

    @Override
    public void alert() {
        print("Warning!");
    }
}

class Emgerency extends AlertStatus {

    @Override
    public void alert() {
        print("Emgerency!");
    }
}

class Information extends AlertStatus {

    @Override
    public void alert() {
        print("Information!");
    }
}