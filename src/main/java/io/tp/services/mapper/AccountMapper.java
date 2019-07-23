package io.tp.services.mapper;

import io.tp.services.model.MoneyAmount;
import io.tp.services.model.Account;
import io.tp.services.model.AccountType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Component
public class AccountMapper {

    public AccountMapper() {
    }

    public List<io.tp.rest.model.Account> map(List<Account> accounts) {
        List<io.tp.rest.model.Account> resources = new ArrayList<>();

        for (Account account : accounts) {
            resources.add(map(account));
        }
        return resources;
    }

    public io.tp.rest.model.Account map(Account account) {
        io.tp.rest.model.Account resource = new io.tp.rest.model.Account();
        resource.setLabel(account.getLabel());
        resource.setType( io.tp.rest.model.Account.Type.fromValue( account.getType().name().toLowerCase() ) );
        resource.setOwners(account.getOwnerIds());
        resource.setIban(account.getIban());

        io.tp.rest.model.MoneyAmount moneyAmount = new io.tp.rest.model.MoneyAmount();
        moneyAmount.setAmount( account.getBalance().getAmount().floatValue() );
        moneyAmount.setCurrency( account.getBalance().getCurrency().toString() );
        resource.setMoneyAmount( moneyAmount );

        return resource;
    }

    public Account map(io.tp.rest.model.Account account) {
        AccountType accountType = AccountType.fromValue( account.getType().name().toLowerCase() );

        Account model = new Account();
        if (StringUtils.isEmpty(account.getLabel())) {
            model.generateLabel(accountType);
        } else {
            model.setLabel(account.getLabel());
        }
        model.setType( accountType );
        model.setOwnerIds(account.getOwners());
        model.setIban(account.getIban());

        MoneyAmount moneyAmount = new MoneyAmount();
        if ( account.getMoneyAmount() != null ) {
            moneyAmount.setAmount( BigDecimal.valueOf( account.getMoneyAmount().getAmount() ) );
            moneyAmount.setCurrency( Currency.getInstance( account.getMoneyAmount().getCurrency().toString() ) );
        } else {
            moneyAmount.setAmount(BigDecimal.ZERO);
            moneyAmount.setCurrency(Currency.getInstance("EUR"));
        }
        model.setBalance( moneyAmount );

        return model;
    }
}
