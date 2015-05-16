package org.dogepool.external.exchangePay;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ReliableExchangeApp {

    @RequestMapping(value = "/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> exchangeRate(@PathVariable String from, @PathVariable String to) {
        long start = System.currentTimeMillis();
        if (from == null || from.length() != 3 || !from.toUpperCase().equals(from)) {
            return new ResponseEntity<String>("Unknown currency " + from, HttpStatus.NOT_FOUND);
        }

        if (to == null || to.length() != 3 || !to.toUpperCase().equals(to)) {
            return new ResponseEntity<String>("Unknown currency " + to, HttpStatus.NOT_FOUND);
        }

        Map<String, Object> metrics = new HashMap<String, Object>(3);
        metrics.put("callCost", 10.6);
        metrics.put("callCostCurrency", "USD");

        Map<String, Object> result = new HashMap<String, Object>(4);
        result.put("from", from);
        result.put("to", to);
        result.put("metrics", metrics);

        if (from.equals(to)) {
            result.put("exchangeRate", 1d);
        } else if ("USD".equals(to)) {
            result.put("exchangeRate", 2d);
        } else {
            result.put("exchangeRate", 0.5);
        }
        metrics.put("executionTime", (System.currentTimeMillis() - start) + "ms");
        return ResponseEntity.ok(result);
    }
}
