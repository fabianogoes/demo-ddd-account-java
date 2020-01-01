package com.example.demodddaccount.boundaries.account.presentation;

import com.example.demodddaccount.boundaries.account.application.AccountUseCase;
import com.example.demodddaccount.boundaries.account.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
class AccountResource {

    private final AccountService service;

    @Autowired
    public AccountResource(AccountRepository repository) {
        this.service = new AccountUseCase(repository);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AccountResponse create(@RequestBody AccountRequest request) {
        AccountVO accountVO = service.createNewAccount(request);
        return PresentationMapper.mapperToAccountResponse(accountVO);
    }

    @PatchMapping("/{accountNumber}/movement/credit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    AccountResponse createMovementOfCredit(@PathVariable String accountNumber, @RequestBody MovementRequest request) {
        AccountVO accountVO = service.createNewMovementOfCredit(accountNumber, request);
        return PresentationMapper.mapperToAccountResponse(accountVO);
    }

    @PatchMapping("/{accountNumber}/movement/debit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    AccountResponse createMovementOfDebit(@PathVariable String accountNumber, @RequestBody MovementRequest request) {
        AccountVO accountVO = service.createNewMovementOfDebit(accountNumber, request);
        return PresentationMapper.mapperToAccountResponse(accountVO);
    }

    @GetMapping("/{accountNumber}")
    AccountResponse createMovementOfDebit(@PathVariable String accountNumber) {
        AccountVO accountVO = service.retrieveAccountByNumber(accountNumber);
        return PresentationMapper.mapperToAccountResponse(accountVO);
    }

}
