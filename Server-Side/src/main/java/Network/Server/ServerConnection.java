////package Network.Client;
////import javax.net.ssl.SSLSocket;
////import javax.net.ssl.SSLServerSocket;
////import javax.net.ssl.SSLServerSocketFactory;
////import java.io.*;
////
////
////public class ServerConnection {
////
////    private static final String[] protocols = new String[] {"TLSv1.3"};
////    private static final String[] cipher_suites = new String[] {"TLS_AES_128_GCM_SHA256"};
////
////    public static void main(String[] args) throws Exception{
////
////        SSLServerSocket serverSocket = null;
////
////        try {
////
////            SSLServerSocketFactory factory = (SSLServerSocketFactory)  SSLServerSocketFactory.getDefault();
////
////            serverSocket = (SSLServerSocket) factory.createServerSocket(8980);
////
////            serverSocket.setEnabledProtocols(protocols);
////            serverSocket.setEnabledCipherSuites(cipher_suites);
////
////            SSLSocket sslSocket = (SSLSocket) serverSocket.accept();
////
////            InputStream inputStream = sslSocket.getInputStream();
////            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
////
////            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
////            String request = null;
////            while((request = bufferedReader.readLine()) != null) {
////                System.out.println(request);
////                System.out.flush();
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            if(serverSocket != null) {
////                serverSocket.close();
////            }
////        }
////    }
////}
//
package Network.Server;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;

public class ServerConnection {

    private static final String[] protocols = new String[] {"TLSv1.3"};
    private static final String[] cipher_suites = new String[] {"TLS_AES_128_GCM_SHA256"};

    public static void main(String[] args) throws Exception {
        SSLServerSocket serverSocket = null;

        try {
            SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

            serverSocket = (SSLServerSocket) factory.createServerSocket(8980);

            serverSocket.setEnabledProtocols(protocols);
            serverSocket.setEnabledCipherSuites(cipher_suites);

            boolean isRunning = true;

            while (isRunning) {
                SSLSocket sslSocket = (SSLSocket) serverSocket.accept();

                handleClient(sslSocket);

                // Close the connection
                sslSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }

    private static void handleClient(SSLSocket sslSocket) {
        try {
            InputStream inputStream = sslSocket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // Read the client's message
            String request;
            while ((request = bufferedReader.readLine()) != null) {
                System.out.println("Client: " + request);
                System.out.flush();
            }

            // Respond to the client with a custom message
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream())));
            out.println("Server: Hi there, Client!");
            out.println();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

