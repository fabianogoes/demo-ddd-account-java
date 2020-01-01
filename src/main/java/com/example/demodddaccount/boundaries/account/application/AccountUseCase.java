package com.example.demodddaccount.boundaries.account.application;


import com.example.demodddaccount.boundaries.account.domain.*;

public class AccountUseCase implements AccountService {

    final AccountRepository repository;

    public AccountUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccountVO createNewAccount(AccountRequest request) {
        Account account = Account.of(request.getPerson(), request.getAmount());
        return account.createNewAccount(repository);
    }

    @Override
    public AccountVO createNewMovementOfCredit(String accountNumber, MovementRequest request) {
        AccountVO accountVO = repository.retrieveAccountByNumber(accountNumber);
        Account account = Account.of(accountVO);
        account.createNewMovementOfCreditAndUpdateAmountOfAccount(repository, request.getMovementValue());
        return repository.retrieveAccountByNumber(accountNumber);
    }

    @Override
    public AccountVO createNewMovementOfDebit(String accountNumber, MovementRequest request) {
        AccountVO accountVO = repository.retrieveAccountByNumber(accountNumber);
        Account account = Account.of(accountVO);
        account.createNewMovementOfDebitAndUpdateAmountOfAccount(repository, request.getMovementValue());
        return repository.retrieveAccountByNumber(accountNumber);
    }

    @Override
    public AccountVO retrieveAccountByNumber(String accountNumber) {
        return repository.retrieveAccountByNumber(accountNumber);
    }

}
