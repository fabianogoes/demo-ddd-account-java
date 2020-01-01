package com.example.demodddaccount.boundaries.account.domain;

import java.util.List;

public interface AccountRepository {

    void persisNewAccount(AccountVO accountVO);

    List<MovementVO> retrieveAllMovements(String accountNumber);

    AccountVO retrieveAccountByNumber(String accountNumber);

    void persistNewMovementAndUpdateAmount(AccountVO accountVO, MovementVO movementVO);

}
