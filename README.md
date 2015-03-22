This application simulates external APIs for the Practical Rx Workshop.

## Avatar API
 * by default, port `8071`
 * route `/avatar/{id}`, will return a PNG image.
 * route `/avatar/{id}/small` will return the same.

## Doge to $ Conversion API
 * by default, port `8072`
 * default route `/` will return the exchange rate of DOGE to DOLLAR (just a double).

## Unreliable Exchange Rate API
 * by default, port `8073`
 * route `{from}/{to}` will unreliably return the exchange rate.
 * currency codes must be 3 letters, uppercase (or an exception is thrown).
 * if service is marked as down, a delay of 5 seconds is minimally applied.
 * additionally, service will generate a response with a random delay of 0-3 seconds.
 * route `/down` and `/up` allow to mark the service as down/up.
 * response format is a JSON object like `{"currencyA":"USD","metrics":{"executionTime":"0ms"},"currencyB":"EUR","rate":0.5}`

## Reliable Exchange Rate API
 * by default, port `8074`
 * route `{from}/{to}` will reliably return the exchange rate, at a cost.
 * currency codes must be 3 letters, uppercase (or an exception is thrown).
 * response format is a JSON object like `{"from":"USD","to":"EUR","metrics":{"executionTime":"0ms","callCost":10.6,"callCostCurrency":"USD"},"exchangeRate":0.5}`
