package com.example.demodddaccount.boundaries.account.infrastructure;

class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
