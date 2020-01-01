package com.example.demodddaccount.boundaries.account.domain;

public interface AccountService {

    AccountVO createNewAccount(AccountRequest request);

    AccountVO createNewMovementOfCredit(String accountNumber, MovementRequest request);

    AccountVO createNewMovementOfDebit(String accountNumber, MovementRequest request);

    AccountVO retrieveAccountByNumber(String accountNumber);
}
