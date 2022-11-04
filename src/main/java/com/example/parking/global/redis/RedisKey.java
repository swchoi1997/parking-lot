package com.example.parking.global.redis;

import lombok.Getter;

@Getter
public enum RedisKey {
    REFRESH("REFRESH_"), ACCESS("Bearer_");

    private String key;

    RedisKey(String key) {
        this.key = key;
    }

}
