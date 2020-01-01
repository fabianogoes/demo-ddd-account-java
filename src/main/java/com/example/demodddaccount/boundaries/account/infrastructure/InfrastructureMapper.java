package com.example.demodddaccount.boundaries.account.infrastructure;

import com.example.demodddaccount.boundaries.account.domain.AccountVO;
import com.example.demodddaccount.boundaries.account.domain.MovementVO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class InfrastructureMapper {

    static AccountVO toAccountVO(Account account) {
        return AccountVO.builder()
                .number(account.getNumber())
                .amount(account.getAmount())
                .personName(account.getPerson().getName())
                .movements(toListOfMovementVO(account))
                .build();
    }

    static List<MovementVO> toListOfMovementVO(Account account) {
        return account.getMovementEntities()
                .stream()
                .map(InfrastructureMapper::toMovementVO)
                .collect(Collectors.toList());
    }

    private static MovementVO toMovementVO(Movement movement) {
        return new MovementVO(movement.getValue(), movement.getType().name());
    }

    static Movement toNewMovement(MovementVO movementVO, Account account) {
        return Movement.builder()
                .account(account)
                .type(MovementType.valueOf(movementVO.getType()))
                .value(movementVO.getValue())
                .build();
    }

    static Account toNewAccount(AccountVO accountVO) {
        Person person = Person.builder()
                .name(accountVO.getPersonName())
                .build();

        Account account = Account.builder()
                .person(person)
                .number(accountVO.getNumber())
                .amount(accountVO.getAmount())
                .build();

        Movement movement = Movement.builder()
                .account(account)
                .type(MovementType.CREDIT)
                .value(accountVO.getAmount())
                .build();

        account.setMovementEntities(Collections.singletonList(movement));
        return account;
    }

    static AccountNotFoundException toNotFoundException() {
        return new AccountNotFoundException("Account NotFound!");
    }

}
