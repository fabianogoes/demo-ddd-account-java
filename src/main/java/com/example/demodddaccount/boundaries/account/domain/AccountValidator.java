package com.example.demodddaccount.boundaries.account.domain;

import org.springframework.util.Assert;

import java.math.BigDecimal;

class AccountValidator {

    private Account account;

    AccountValidator(Account account) {
        this.account = account;
    }

    void validateRulesOfPerson() {
        Assert.notNull(this.account.getPersonName(), "[PersonName] should not be Null!");
        Assert.hasText(this.account.getPersonName(), "[PersonName] should not be Black!");
        Assert.isTrue(this.account.getPersonName().length() >= 5, "[PersonName] should has greater than 4 words!");
    }

    void validateRulesOfAmount() {
        Assert.notNull(this.account.getAmount(), "[Amount] should not be Null!");
        Assert.isTrue(this.account.getAmount().doubleValue() >= 0, "[Amount] should be Less than ZERO!");
    }

    void validateRulesOfValueToNewMovement(BigDecimal movementValue) {
        Assert.notNull(this.account.getAmount(), "[Movement Value] should not be Null!");
        Assert.isTrue(this.account.getAmount().doubleValue() > 0, "[Movement Value] should be Greater than ZERO!");
    }

    void validateRulesOfSufficientAmountToNewMovementOfDebit(BigDecimal movementValue) {
        boolean hasSufficientAmount = account.getAmount().doubleValue() >= movementValue.doubleValue();
        Assert.isTrue(hasSufficientAmount, "[Movement Value] Account has not Sufficient Amount to Register this Debit");
    }

}
