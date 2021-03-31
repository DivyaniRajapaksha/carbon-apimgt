package org.wso2.carbon.apimgt.gatewaybridge.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Class for creating HTTP connection.
 */
public final class APIUtil {
    private static final Log log = LogFactory.getLog(APIUtil.class);
    private static CloseableHttpResponse httpResponse;

    /**
     * Returns a HttpClient instance
     * This method always returns immediately, whether or not the
     * HttpClient exists.
     *
     * @param port     the port of the client
     * @param protocol the Protocol use for communication
     * @return  a executable HttpClient
     */
    public static HttpClient getHttpClient(int port, String protocol) {
        String maxTotal = "100";
        String defaultMaxPerRoute = "50";

            PoolingHttpClientConnectionManager pool = getPoolingHttpClientConnectionManager(protocol);
            pool.setMaxTotal(Integer.parseInt(maxTotal));
            pool.setDefaultMaxPerRoute(Integer.parseInt(defaultMaxPerRoute));

            RequestConfig params = RequestConfig.custom().build();
            return HttpClients.custom().setConnectionManager(pool).setDefaultRequestConfig(params).build();


    }

    /**
     * Execute an HTTP request.
     *
     * @param method        the HttpRequest Type
     * @param httpClient    the HttpClient
     * @return  a HTTPResponse
     * @throws IOException  If there are any errors while executing the http request
     */
    public static CloseableHttpResponse executeHTTPRequest(HttpRequestBase method, HttpClient httpClient)
            throws Exception {

        int retryCount = 0;
        boolean retry;
        do {
            try {
                httpResponse = (CloseableHttpResponse) httpClient.execute(method);
                retry = false;
            } catch (IOException ex) {
                retryCount++;
                if (retryCount < 2) {
                    retry = true;
                    log.debug("Failed retrieving from remote endpoint: " + ex.getMessage()
                            + ". Retrying after " + 15 +
                            " seconds.");
                    try {
                        Thread.sleep(15 * 1000);
                    } catch (InterruptedException e) {
                        // Ignore
                    }
                } else {
                    throw ex;
                }
            }
        } while (retry);

        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            return httpResponse;
        } else {
            httpResponse.close();
            String errorMessage = EntityUtils.toString(httpResponse.getEntity(),
                    "UTF-8");
            throw new Exception(errorMessage + "Event-Hub status code is : "
                    + httpResponse.getStatusLine().getStatusCode());
        }
    }

    /**
     * Returns a PoolingHttpClientConnectionManager instance.
     *
     * @param protocol      the service endpoint protocol. It can be http/https
     * @return  a PoolManager instance
     */
    private static PoolingHttpClientConnectionManager getPoolingHttpClientConnectionManager(String protocol) {

        PoolingHttpClientConnectionManager poolManager;
        if ("https".equals(protocol)) {

            SSLConnectionSocketFactory socketFactory = createSocketFactory();
            assert socketFactory != null;
            org.apache.http.config.Registry<ConnectionSocketFactory> socketFactoryRegistry =
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("https", socketFactory).build();
            poolManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        } else {
            poolManager = new PoolingHttpClientConnectionManager();
        }
        return poolManager;
    }

    /**
     * Returns a SSLConnectionSocketFactory instance
     * Creates a socket and connects it to the specified remote host at the specified remote port.
     * This socket is configured using the socket options established for this factory.
     *
     * @return  a SSLConnectionSocketFactory instance
     */
    public static SSLConnectionSocketFactory createSocketFactory() {
//        SSLContext sslContext;
//
//        String keyStorePath = "/home/user/Desktop/New_Test/" +
//                "gateways/gateway-3/repository/resources/security/client-truststore.jks";
//        String keyStorePassword = "wso2carbon";
//        try {
//            KeyStore trustStore = KeyStore.getInstance("JKS");
//            trustStore.load(new FileInputStream(APIConstants.KEYPATH), keyStorePassword.toCharArray());
//            sslContext = SSLContexts.custom().loadTrustMaterial(trustStore).build();
//
//            log.debug(trustStore);
//            X509HostnameVerifier hostnameVerifier;
//            String hostnameVerifierOption = "AllowAll";
//
//
//            if ("AllowAll".equalsIgnoreCase(hostnameVerifierOption)) {
//                hostnameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
//            } else if ("Strict".equalsIgnoreCase(hostnameVerifierOption)) {
//                hostnameVerifier = SSLSocketFactory.STRICT_HOSTNAME_VERIFIER;
//            } else {
//                hostnameVerifier = SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
//            }
//            log.debug(sslContext + " " + hostnameVerifier);
//            return new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
//        } catch (KeyStoreException e) {
//            log.debug("Failed to read from Key Store");
//        } catch (IOException e) {
//            log.debug("Key Store not found in " + keyStorePath);
//        } catch (CertificateException e) {
//            log.debug("Failed to read Certificate");
//        } catch (NoSuchAlgorithmException e) {
//            log.debug("Failed to load Key Store from " + keyStorePath);
//        } catch (KeyManagementException e) {
//            log.debug("Failed to load key from" + keyStorePath);
//        }

        return null;
    }


}



