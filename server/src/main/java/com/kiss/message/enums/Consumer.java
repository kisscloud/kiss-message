package com.kiss.message.enums;

public enum Consumer {

    MAIL(1),
    SMS(2),
    SINGLE(11),
    BATCH(12);

    public final Integer type;

    Consumer(int type) {
        this.type = type;
    }

}
