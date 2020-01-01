package com.example.demodddaccount.boundaries.account.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Builder
public class AccountVO {
    @Getter
    private final String personName;
    @Getter
    private final String number;
    @Getter
    private final BigDecimal amount;

    private List<MovementVO> movements;

    public void setMovements(final List<MovementVO> movements) {
        this.movements = Collections.unmodifiableList(movements);
    }

    public List<MovementVO> getMovements() {
        return Collections.unmodifiableList(movements);
    }
}
