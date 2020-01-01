package com.example.demodddaccount.boundaries.account.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
class MovementResponse {

    private BigDecimal value;
    private String type;

}
