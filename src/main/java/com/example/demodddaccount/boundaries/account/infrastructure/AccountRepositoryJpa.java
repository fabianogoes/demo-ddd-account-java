package com.example.demodddaccount.boundaries.account.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface AccountRepositoryJpa extends JpaRepository<Account, Long> {

    Optional<Account> findByNumber(String number);

}
