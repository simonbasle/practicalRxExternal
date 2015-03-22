package org.dogepool.external.doge2usd;

import java.util.Calendar;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DogeToUsdApp {

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public double rate() {
        Calendar cal = Calendar.getInstance();
        double rate = cal.get(Calendar.HOUR_OF_DAY) * 1000d
                + cal.get(Calendar.MINUTE) * 10d;
        return rate;
    }
}
