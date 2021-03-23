package com.photoapp.config;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class TimeZoneConfig {

    public static final String TIMEZONE_WARSAW = "Europe/Warsaw";
    public static final String TIMEZONE_UTC = "UTC";

    public ZonedDateTime getCurrentDateTime(String zoneId) {
        return Instant.now().atZone(ZoneId.of(zoneId));
    }
}
