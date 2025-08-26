package com.yosep.virtual_thread_vs_webflux_bench_mark_main.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.LoggingCodecSupport;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    private final WebClientProperties webClientProperties;

    @Value("${external.server.url:http://localhost:20003}")
    private String externalServerUrl;

    public WebClientConfig(WebClientProperties webClientProperties) {
        this.webClientProperties = webClientProperties;
    }

    @Bean
    public WebClient externalClient() {
        ExchangeStrategies exchangeStrategies = getExchangeStrategies(webClientProperties.getExternal().getByteCnt());
        return getWebClient(exchangeStrategies, webClientProperties.getExternal());
    }

    private ExchangeStrategies getExchangeStrategies(int byteCnt) {
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(byteCnt))
                .build();

        exchangeStrategies.messageWriters().stream()
                .filter(writer -> writer instanceof LoggingCodecSupport)
                .forEach(writer -> ((LoggingCodecSupport) writer).setEnableLoggingRequestDetails(true));

        return exchangeStrategies;
    }

    private WebClient getWebClient(ExchangeStrategies exchangeStrategies, WebClientProperties.TimoutProperties timeoutProperties) {
        ConnectionProvider connectionProvider = ConnectionProvider.builder("custom")
                .maxConnections(10000)
                .pendingAcquireMaxCount(100000)
                .pendingAcquireTimeout(Duration.ofSeconds(30))
                .build();

        HttpClient httpClient = HttpClient.create(connectionProvider)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeoutProperties.getConnectionTimeout())
                .responseTimeout(Duration.ofMillis(timeoutProperties.getResponseTimeout()))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(timeoutProperties.getReadTimeout(), TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(timeoutProperties.getWriteTimeout(), TimeUnit.MILLISECONDS)));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(externalServerUrl)
                .exchangeStrategies(exchangeStrategies)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .build();
    }
}