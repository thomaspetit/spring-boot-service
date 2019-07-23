package io.tp.rest;

import io.tp.accountservice.api.AccountsApi;
import io.tp.rest.model.Account;
import io.tp.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


/**
 * No description
 * (Generated with springmvc-raml-parser v.0.9.1)
 *
 */
@RestController
public class AccountController implements AccountsApi {

    @Autowired
    private AccountService accountService;

    /**
     * Get list of accounts
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    /**
     * Create new accounts
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAccount(@Valid @RequestBody Account account) {
        final String id = accountService.create(account);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Retrieve account details
     */
    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccountById(@PathVariable String accountId) {
        return ResponseEntity.ok(accountService.get(accountId));
    }

    /**
     * Delete account
     */
    @RequestMapping(value = "/{accountId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAccountById(@PathVariable String accountId) {
        accountService.delete(accountId);
        return ResponseEntity.noContent().build();
    }
}