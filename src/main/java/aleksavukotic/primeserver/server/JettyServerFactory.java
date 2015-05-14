package aleksavukotic.primeserver.server;

import aleksavukotic.primeserver.config.WebConfiguration;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.Jetty;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

public class JettyServerFactory {

    private final int port;
    private final Class[] configurationClasses;

    public JettyServerFactory(int port, Class... configurationClasses) {
        this.port = port;
        this.configurationClasses = configurationClasses;
    }

    public Server createServer() {
        final ThreadPool threadPool = threadPool();
        final Server server = new Server(threadPool);

        server.manage(threadPool);
        server.setDumpAfterStart(false);
        server.setDumpBeforeStop(false);
        try {
            server.addConnector(serverConnector(server));
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.setHandler(handlersContainer());
        server.setStopAtShutdown(true);
        return server;
    }

    private Handler handlersContainer() {
        HandlerCollection result = new HandlerCollection();
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");

        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(configurationClasses);
        handler.addServlet(new ServletHolder(new DispatcherServlet(webApplicationContext)), "/");

        result.setHandlers(new Handler[]{handler});
        return result;
    }

    private ServerConnector serverConnector(Server server) throws IOException {
        HttpConfiguration httpConfiguration = new HttpConfiguration();
        HttpConnectionFactory http = new HttpConnectionFactory(httpConfiguration);
        ServerConnector httpConnector = new ServerConnector(server, http);
        httpConnector.setPort(port());
        httpConnector.setHost("0.0.0.0");
        httpConnector.setIdleTimeout(10000);
        return httpConnector;
    }

    private ThreadPool threadPool() {
        QueuedThreadPool _threadPool = new QueuedThreadPool();
        _threadPool.setMinThreads(10);
        _threadPool.setMaxThreads(20);
        return _threadPool;
    }

    private int port() throws IOException {
        int portNum = port;
        if (portNum <= 0) {
            ServerSocket serverSocket = new ServerSocket(0);
            portNum = serverSocket.getLocalPort();
            serverSocket.close();
        }
        return portNum;
    }
}
