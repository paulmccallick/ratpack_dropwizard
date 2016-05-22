package dropwizardtest.app;


import java.net.InetSocketAddress;

import com.codahale.metrics.graphite.Graphite;

import ratpack.dropwizard.metrics.DropwizardMetricsModule;
import ratpack.guice.Guice;
import ratpack.server.RatpackServer;

public class Main {
  /**
   * Primary entry point for the application.
   k
   * @param args command line args for starting the app.
   */
  public static void main(String... args) throws Exception {
    RatpackServer
    .start(server -> server
        .registry(
            Guice.registry(r -> r
                .module(DropwizardMetricsModule.class, metrics -> metrics
                    .jvmMetrics(true)
                    .graphite(g -> g
                        .enable()
                        .reporterInterval(java.time.Duration.ofSeconds(1))
                        .sender(new Graphite(new InetSocketAddress("192.168.99.100", 2003)))
                        .prefix("dropwizardtest."))
                    .console(con -> con
                        .enable(true)
                        .reporterInterval(java.time.Duration.ofSeconds(3))))))
        .handlers(chain -> chain
            .get(ctx -> ctx.render("Hello World!"))));
  }
}