package com.example.demodddaccount.boundaries.account.infrastructure;

import com.example.demodddaccount.boundaries.account.domain.AccountRepository;
import com.example.demodddaccount.boundaries.account.domain.AccountVO;
import com.example.demodddaccount.boundaries.account.domain.MovementVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
class AccountRepositoryHibernate implements AccountRepository {

    private final AccountRepositoryJpa accountRepositoryJpa;

    @Override
    public void persisNewAccount(AccountVO accountVO) {
        Account account = InfrastructureMapper.toNewAccount(accountVO);
        accountRepositoryJpa.save(account);
    }

    @Override
    public List<MovementVO> retrieveAllMovements(String accountNumber) {
        return accountRepositoryJpa.findByNumber(accountNumber)
                .map(InfrastructureMapper::toListOfMovementVO)
                .orElseThrow(InfrastructureMapper::toNotFoundException);
    }



    @Override
    public AccountVO retrieveAccountByNumber(String accountNumber) {
        return accountRepositoryJpa.findByNumber(accountNumber)
                .map(InfrastructureMapper::toAccountVO)
                .orElseThrow(InfrastructureMapper::toNotFoundException);
    }

    @Override
    public void persistNewMovementAndUpdateAmount(AccountVO accountVO, MovementVO movementVO) {
        Account account = accountRepositoryJpa.findByNumber(accountVO.getNumber())
                .orElseThrow(InfrastructureMapper::toNotFoundException);

        account.setAmount(accountVO.getAmount());
        Movement newMovement = InfrastructureMapper.toNewMovement(movementVO, account);
        account.getMovementEntities().add(newMovement);
        accountRepositoryJpa.save(account);
    }

}
