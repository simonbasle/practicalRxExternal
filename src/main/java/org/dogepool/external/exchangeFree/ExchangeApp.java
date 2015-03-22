package org.dogepool.external.exchangeFree;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ExchangeApp {

    private boolean down = false;

    @RequestMapping("/down")
    private String down() {
        this.down = true;
        return "service is down";
    }

    @RequestMapping("/up")
    private String up() {
        this.down = false;
        return "service is up";
    }

    @RequestMapping(value = "/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    private Map<String, Object> exchangeRate(@PathVariable String from, @PathVariable String to) throws InterruptedException {
        long start = System.currentTimeMillis();
        if (down) {
            System.out.println("Down, sleeping for 5s");
            Thread.sleep(5000);
        }

        if (from == null || from.length() != 3 || !from.toUpperCase().equals(from)) {
            throw new IllegalArgumentException("Unknown currency " + from);
        }

        if (to == null || to.length() != 3 || !to.toUpperCase().equals(to)) {
            throw new IllegalArgumentException("Unknown currency " + to);
        }

        Random rng = new Random(System.currentTimeMillis());
        int sleepTime = rng.nextInt(3);
        Thread.sleep(sleepTime * 1000L);
        if (sleepTime > 0) {
            System.out.println("Failure, sleeping for " + sleepTime + "s");
        }

        Map<String, Object> result = new HashMap<String, Object>(4);
        Map<String, Object> metrics = new HashMap<String, Object>(1);
        result.put("metrics", metrics);
        result.put("currencyA", from);
        result.put("currencyB", to);

        if (from.equals(to)) {
            result.put("rate", 1d);
        } else if ("USD".equals(to)) {
            result.put("rate", 2d);
        } else {
            result.put("rate", 0.5);
        }
        metrics.put("executionTime", (System.currentTimeMillis() - start) + "ms");
        return result;
    }
}
