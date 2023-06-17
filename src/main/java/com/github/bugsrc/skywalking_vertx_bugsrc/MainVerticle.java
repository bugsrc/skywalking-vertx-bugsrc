package com.github.bugsrc.skywalking_vertx_bugsrc;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {
        log.info("MainVerticle.start");
        vertx.createHttpServer().requestHandler(req -> {
            log.info("HTTP server received request");
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x!");
        }).listen(8888, http -> {
            if (http.succeeded()) {
                startPromise.complete();
                log.info("HTTP server started on port 8888");
            } else {
                startPromise.fail(http.cause());
            }
        });
    }
}
