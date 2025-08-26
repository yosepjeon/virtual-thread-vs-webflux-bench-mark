package com.yosep.virtual_thread_vs_webflux_bench_mark_main.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "webclient")
public class WebClientProperties {

    private TimoutProperties external;

    public TimoutProperties getExternal() {
        return external;
    }

    public void setExternal(TimoutProperties external) {
        this.external = external;
    }

    public static class TimoutProperties {
        private int connectionTimeout;
        private int responseTimeout;
        private int readTimeout;
        private int writeTimeout;
        private int byteCnt;

        public int getConnectionTimeout() {
            return connectionTimeout;
        }

        public void setConnectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
        }

        public int getResponseTimeout() {
            return responseTimeout;
        }

        public void setResponseTimeout(int responseTimeout) {
            this.responseTimeout = responseTimeout;
        }

        public int getReadTimeout() {
            return readTimeout;
        }

        public void setReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
        }

        public int getWriteTimeout() {
            return writeTimeout;
        }

        public void setWriteTimeout(int writeTimeout) {
            this.writeTimeout = writeTimeout;
        }

        public int getByteCnt() {
            return byteCnt;
        }

        public void setByteCnt(int byteCnt) {
            this.byteCnt = byteCnt;
        }
    }
}