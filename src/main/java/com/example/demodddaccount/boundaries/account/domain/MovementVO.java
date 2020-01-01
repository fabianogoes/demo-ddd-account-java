package com.example.demodddaccount.boundaries.account.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class MovementVO {

    private final BigDecimal value;
    private final String type;

}
