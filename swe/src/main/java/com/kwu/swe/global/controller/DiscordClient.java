package com.kwu.swe.global.controller;

import com.kwu.swe.global.config.DiscordFeignConfig;
import com.kwu.swe.global.dto.DiscordMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "discord-client",
        url = "${discord.webhook.url}",
        configuration = DiscordFeignConfig.class)
public interface DiscordClient {

    @PostMapping
    void sendAlarm(@RequestBody DiscordMessage message);

}
