package io.tp.services.model;

import java.util.HashMap;
import java.util.Map;

public enum AccountType {
    CHECKINGS("checkings"), SAVINGS("savings");

    private final String value;
    private final static Map<String, AccountType> CONSTANTS = new HashMap<String, AccountType>();

    static {
        for (AccountType c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    private AccountType(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public String value() {
        return this.value;
    }

    public static AccountType fromValue(String value) {
        AccountType constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}
