package com.example.demodddaccount.boundaries.account.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovementRequest {

    private BigDecimal movementValue;

}
