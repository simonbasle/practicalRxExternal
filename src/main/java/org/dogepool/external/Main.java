package org.dogepool.external;

import org.dogepool.external.avatar.AvatarApp;
import org.dogepool.external.doge2usd.DogeToUsdApp;
import org.dogepool.external.exchangeFree.ExchangeApp;
import org.dogepool.external.exchangePay.ReliableExchangeApp;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class Main {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AvatarApp.class)
                .showBanner(false)
                .properties("server.port=${avatar.port}")
                .run();

        new SpringApplicationBuilder(DogeToUsdApp.class)
                .showBanner(false)
                .properties("server.port=${doge.port}")
                .run();

        new SpringApplicationBuilder(ExchangeApp.class)
                .showBanner(false)
                .properties("server.port=${exchange.free.port}")
                .run();

        new SpringApplicationBuilder(ReliableExchangeApp.class)
                .showBanner(false)
                .properties("server.port=${exchange.reliable.port}")
                .run();
    }
}
