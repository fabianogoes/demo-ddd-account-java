package com.example.demodddaccount.boundaries.account.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class Account {

    private static Integer accountNumbers = 0;

    public enum MovementType {
        CREDIT, DEBIT
    }

    private final String personName;
    private final String number;
    private BigDecimal amount;
    private AccountValidator validator;

    private Account(String personName, BigDecimal amount) {
        this.personName = personName;
        this.amount = amount;
        this.number = generateNewAccountNumber();
        this.validator = new AccountValidator(this);
    }

    private Account(AccountVO accountVO) {
        this.number = accountVO.getNumber();
        this.personName = accountVO.getPersonName();
        this.amount = accountVO.getAmount();
        this.validator = new AccountValidator(this);
    }

    public static Account of(String personName, BigDecimal amount) {
        return new Account(personName, amount);
    }
    public static Account of(AccountVO accountVO) {
        return new Account(accountVO);
    }

    public AccountVO createNewAccount(AccountRepository repository) {
        isNewAccountValid();
        AccountVO accountVO = DomainMapper.toAccountVo(this);
        repository.persisNewAccount(accountVO);
        List<MovementVO> movements = repository.retrieveAllMovements(this.number);
        accountVO.setMovements(movements);
        return accountVO;
    }

    public void createNewMovementOfCreditAndUpdateAmountOfAccount(AccountRepository repository, BigDecimal movementValue) {
        isNewMovementOfCreditValid(movementValue);
        updateCreditAmountOfAccount(movementValue);

        AccountVO accountVO = DomainMapper.toAccountVo(this);
        MovementVO movementVO = DomainMapper.toMovementOfCredit(movementValue);

        repository.persistNewMovementAndUpdateAmount(accountVO, movementVO);
    }

    public void createNewMovementOfDebitAndUpdateAmountOfAccount(AccountRepository repository, BigDecimal movementValue) {
        isNewMovementOfDebitValid(movementValue);
        updateDebitAmountOfAccount(movementValue);

        AccountVO accountVO = DomainMapper.toAccountVo(this);
        MovementVO movementVO = DomainMapper.toMovementOfDebit(movementValue);

        repository.persistNewMovementAndUpdateAmount(accountVO, movementVO);
    }

    private void updateCreditAmountOfAccount(BigDecimal movementValue) {
        this.amount = this.amount.add(movementValue);
    }

    private void updateDebitAmountOfAccount(BigDecimal movementValue) {
        this.amount = this.amount.subtract(movementValue);
    }

    private void isNewAccountValid() {
        this.validator.validateRulesOfPerson();
        this.validator.validateRulesOfAmount();
    }

    private void isNewMovementOfCreditValid(BigDecimal movementValue) {
        this.validator.validateRulesOfValueToNewMovement(movementValue);
    }

    private void isNewMovementOfDebitValid(BigDecimal movementValue) {
        this.validator.validateRulesOfValueToNewMovement(movementValue);
        this.validator.validateRulesOfSufficientAmountToNewMovementOfDebit(movementValue);
    }

    private String generateNewAccountNumber() {
        Integer nextNumber = accountNumbers + 1;
        Integer lengthNumber = 10;
        String nextNumberStr = String.format("%1$" + lengthNumber + "s", nextNumber).replace(' ', '0');
        return nextNumberStr;
    }

}
