package com.debugagent.threads.future;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpClient;
import java.nio.file.Files;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class PerformOnThread {
    public static void main(String[] argv) throws ExecutionException, InterruptedException {
        Executor executor = Executors.newSingleThreadExecutor();
        Supplier<String> r = () -> {
            try {
                return Files.readString(new File("pom.xml").toPath());
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        };
        var future = CompletableFuture.supplyAsync(r, executor);
        System.out.println(future.get());
    }
}
