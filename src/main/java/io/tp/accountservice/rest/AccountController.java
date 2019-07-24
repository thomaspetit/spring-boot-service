package io.tp.accountservice.rest;

import io.tp.accountservice.rest.model.Account;
import io.tp.accountservice.rest.sapi.AccountsApi;
import io.tp.accountservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class AccountController implements AccountsApi {

    @Autowired
    private AccountService accountService;

    @Override
    public ResponseEntity<Void> createAccounts(Account account, String xB3TraceId, String xB3SpanId) {
        final String id = accountService.create(account);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/accounts/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> deleteAccountById(String accountId, String xB3TraceId, String xB3SpanId) {
        accountService.delete(accountId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<Account>> listAccounts(String xB3TraceId, String xB3SpanId) {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @Override
    public ResponseEntity<Account> showAccountById(String accountId, String xB3TraceId, String xB3SpanId) {
        return ResponseEntity.ok(accountService.get(accountId));
    }
}