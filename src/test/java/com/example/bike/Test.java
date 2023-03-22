package com.example.bike;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class Test {

    @org.junit.jupiter.api.Test
    void test(){
        Instant now = Instant.now();
        OffsetDateTime now1 = OffsetDateTime.now(ZoneOffset.UTC);
        Instant instant = now1.toInstant();
        System.out.println(now);
        System.out.println(now1);
        System.out.println(instant);
        System.out.println(Clock.systemDefaultZone());
    }

}
