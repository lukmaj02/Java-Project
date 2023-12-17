package Bank.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum CurrencyType {

    PLN("PLN"),
    EURO("EUR"),
    US_DOLLAR("USD"),
    RUBLE("RUB"),
    NORWEGIAN_KRONE("NOK");

    private final String symbol;
}
