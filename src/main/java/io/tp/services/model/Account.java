package io.tp.services.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String label;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private String iban;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "BALANCE_EUR"))
    private MoneyAmount balance;

    @ElementCollection
    private List<String> ownerIds;

    public Account() {
    }

    public void generateLabel(AccountType accountType) {
        switch (accountType) {
            case CHECKINGS: this.label = "my current account"; break;
            case SAVINGS: this.label =  "my savings account"; break;
            default: this.label =  "my account"; break;
        }
    }
}
