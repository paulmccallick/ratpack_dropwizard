package dropwizardtest.app;


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
                .module(DropwizardMetricsModule.class,
                    c -> c.console(con -> con.enable(true)
                        .reporterInterval(java.time.Duration.ofSeconds(3))))))
        .handlers(chain -> chain
            .get(ctx -> ctx.render("Hello World!"))));
  }
}
