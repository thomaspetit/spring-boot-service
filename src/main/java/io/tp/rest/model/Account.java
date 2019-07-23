
package io.tp.rest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "label",
    "type",
    "owners",
    "iban",
    "moneyAmount"
})
public class Account {

    @JsonProperty("label")
    private String label;
    @JsonProperty("type")
    private Account.Type type;
    @JsonProperty("owners")
    private List<String> owners = new ArrayList<String>();
    @JsonProperty("iban")
    private String iban;
    @JsonProperty("moneyAmount")
    private MoneyAmount moneyAmount;

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("type")
    public Account.Type getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Account.Type type) {
        this.type = type;
    }

    @JsonProperty("owners")
    public List<String> getOwners() {
        return owners;
    }

    @JsonProperty("owners")
    public void setOwners(List<String> owners) {
        this.owners = owners;
    }

    @JsonProperty("iban")
    public String getIban() {
        return iban;
    }

    @JsonProperty("iban")
    public void setIban(String iban) {
        this.iban = iban;
    }

    @JsonProperty("moneyAmount")
    public MoneyAmount getMoneyAmount() {
        return moneyAmount;
    }

    @JsonProperty("moneyAmount")
    public void setMoneyAmount(MoneyAmount moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public enum Type {

        CHECKINGS("checkings"),
        SAVINGS("savings");
        private final String value;
        private final static Map<String, Account.Type> CONSTANTS = new HashMap<String, Account.Type>();

        static {
            for (Account.Type c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static Account.Type fromValue(String value) {
            Account.Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
