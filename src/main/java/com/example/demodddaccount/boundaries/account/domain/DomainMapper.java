package com.example.demodddaccount.boundaries.account.domain;

import java.math.BigDecimal;

class DomainMapper {

    static AccountVO toAccountVo(Account account) {
        return AccountVO.builder()
                .personName(account.getPersonName())
                .number(account.getNumber())
                .amount(account.getAmount())
                .build();
    }

    static MovementVO toMovementOfDebit(BigDecimal movementValue) {
        return new MovementVO(movementValue, Account.MovementType.DEBIT.name());
    }

    static MovementVO toMovementOfCredit(BigDecimal movementValue) {
        return new MovementVO(movementValue, Account.MovementType.CREDIT.name());
    }

}
