package com.pattern.strategy.enumStrategy;

public enum Calculator {
    ADD("+") {
        public int exec(int a, int b) {
            return a + b;
        }
    },
    SUB("-") {
        public int exec(int a, int b) {
            return a - b;
        }
    };

    private String mValue;

    private Calculator(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }

    public abstract int exec(int a, int b);
}
