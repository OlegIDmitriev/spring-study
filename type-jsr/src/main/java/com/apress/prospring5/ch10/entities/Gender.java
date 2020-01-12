package com.apress.prospring5.ch10.entities;

import javax.swing.plaf.SpinnerUI;

public enum Gender {
    MALE("M"),
    FEMALE("F");

    private String code;

    Gender(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
