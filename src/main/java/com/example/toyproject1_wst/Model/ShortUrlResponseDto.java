package com.example.toyproject1_wst.Model;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@RedisHash(value = "shortUrl", timeToLive = 60)
public class ShortUrlResponseDto implements Serializable {
    private static  final long serialVersion = -214490344996507077L;

    @Id
    private String orgUrl;

    private String shortUrl;

}
