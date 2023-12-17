package Bank.Service;

import Bank.Enums.CurrencyType;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

@Service
public class CurrencyTransferService {

    @Value("${api_key.currency}")
    private String API_KEY;
    @Autowired
    private HttpClient client;

    public BigDecimal convertCurrencyAmount(CurrencyType from, CurrencyType to, BigDecimal amount) {
        try{
            var response = client.send(sendGetConvertRequest(from.getSymbol(), to.getSymbol(), amount),HttpResponse.BodyHandlers.ofString());
            return getConvertedAmount(response.body()).setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (URISyntaxException |IOException | InterruptedException e) {
            throw new RuntimeException("API_TRANSFER_CURRENCY_FAILED");
        }

    }

    private BigDecimal getConvertedAmount(String response){
        return new JSONObject(response).getJSONObject("response").getBigDecimal("value");
    }
    private HttpRequest sendGetConvertRequest(String from, String to, BigDecimal amount) throws URISyntaxException {
        String convertUri = "https://api.currencybeacon.com/v1/convert?from="+from+"&to="+to+"&amount="+amount+"&api_key="+API_KEY;
            return HttpRequest.newBuilder()
                    .uri(new URI(convertUri))
                    .GET()
                    .build();
    }
}
