package com.example.lurenjiaspring.aop.adviceuntil;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {
    public static void main(String[] args) throws InterruptedException {

        String url = "https://cat-match.easygame2021.com/sheep/v1/game/game_over?rank_score=1&rank_state=1&rank_time=21&rank_role=1&skin=1";
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.set("t", " eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTQ1MDk1OTIsIm5iZiI6MTY2Mz" +
                "QwNzM5MiwiaWF0IjoxNjYzNDA1NTkyLCJqdGkiOiJDTTpjYXRfbWF0Y2g6bHQxMjM0NTYiLCJvcGVuX2lkIjoiIiwidWlkIjo2NDkyMjk0OSwiZGVidWciOiIiLCJsYW5nIjoiIn0.6woLPw0SqdH-yp1FFWs0xN094MejbFCBzKbUjCCKCvw");
        HttpEntity httpEntity = new HttpEntity(null, multiValueMap);

        RestTemplate restTemplate = new RestTemplate();
        Thread thread = null;
        for (int i = 0; i < 100; i++) {
            try {
                thread  = new Thread(() -> {
                    while (true) {
                        restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
                    }
                });
                thread.start();
                //System.out.println(exchange.getBody());
                //if (exchange.getStatusCode() == HttpStatus.OK) {
                //    return;
                //}
            } catch (Exception e) {
                //System.out.println(e);
            }
        }
        thread.join();
    }
}
