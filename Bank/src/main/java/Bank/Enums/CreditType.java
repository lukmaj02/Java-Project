package Bank.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
@AllArgsConstructor
public enum CreditType {
    CONST(BigDecimal.valueOf(0.08));

    private final BigDecimal lendingRate;
    private static BigDecimal getLendingRateForCredit(CreditType creditType){
        return creditType.getLendingRate();
    }
}
