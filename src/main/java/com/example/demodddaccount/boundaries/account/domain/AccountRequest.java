package com.example.demodddaccount.boundaries.account.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AccountRequest {

    private final String person;
    private final BigDecimal amount;

}
