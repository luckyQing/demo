package com.liyulin.skills.util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableFutureDemo {

    public static void main(String[] args) {
        List<Long> requests = new ArrayList<>(128);
        for (int i = 0; i < 100; i++) {
            requests.add(1L);
        }
        List<CompletableFuture<Long>> futureList = requests
                .stream()
                .map(request ->
                        CompletableFuture.supplyAsync(() -> {
                            System.out.println(Thread.currentThread().getName());
                            return request;
                        }))
                .collect(Collectors.toList());

        Long sum = futureList.stream().mapToLong(CompletableFuture::join).sum();
        System.out.println(sum);
    }

}
