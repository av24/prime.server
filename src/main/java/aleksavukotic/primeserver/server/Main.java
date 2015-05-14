package aleksavukotic.primeserver.server;

import aleksavukotic.primeserver.config.WebConfiguration;

public class Main {

    public static void main(String[] args) throws Exception {
        new JettyServerFactory(9000, WebConfiguration.class).createServer().start();
    }
}
