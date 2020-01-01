package com.example.demodddaccount.boundaries.account.presentation;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
class AccountResponse {

    private String person;
    private String number;
    private BigDecimal amount;
    private List<MovementResponse> movements;

}
