package io.tp.services.repositories;

import io.tp.services.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

    Account findById( String id );
    List<Account> findAll();
}