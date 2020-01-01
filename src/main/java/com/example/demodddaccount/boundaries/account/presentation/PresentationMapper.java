package com.example.demodddaccount.boundaries.account.presentation;

import com.example.demodddaccount.boundaries.account.domain.AccountVO;
import com.example.demodddaccount.boundaries.account.domain.MovementVO;

import java.util.List;
import java.util.stream.Collectors;

class PresentationMapper {

    static AccountResponse mapperToAccountResponse(AccountVO accountVO) {
        List<MovementResponse> movements = mapperToListOfMovementResponse(accountVO.getMovements());
        return AccountResponse.builder()
                .number(accountVO.getNumber())
                .amount(accountVO.getAmount())
                .person(accountVO.getPersonName())
                .movements(movements)
                .build();
    }

    private static List<MovementResponse> mapperToListOfMovementResponse(List<MovementVO> movementVOS) {
        return movementVOS.stream()
                .map(PresentationMapper::mapperToMovementResponse)
                .collect(Collectors.toList());
    }

    private static MovementResponse mapperToMovementResponse(MovementVO movementVO) {
        return new MovementResponse(movementVO.getValue(), movementVO.getType());
    }

}
