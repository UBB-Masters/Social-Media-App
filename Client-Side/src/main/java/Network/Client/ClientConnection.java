package Network.Client;
import java.io.*;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class ClientConnection {

    private static final String[] protocols = new String[] {"TLSv1.3"};
    private static final String[] cipher_suites = new String[] {"TLS_AES_128_GCM_SHA256"};

    public static void main(String[] args) throws Exception{
        SSLSocket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            SSLSocketFactory factory = (SSLSocketFactory)  SSLSocketFactory.getDefault();

            socket = (SSLSocket) factory.createSocket("google.com", 443);

            socket.setEnabledProtocols(protocols);
            socket.setEnabledCipherSuites(cipher_suites);

            socket.startHandshake();

            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            out.println("GET / HTTP/1.0");
            out.println();
            out.flush();

            out.println("Client: Hello, Server!");
            out.println();
            out.flush();


            if(out.checkError())
                System.out.println("SSLSocketClient:  java.io.PrintWriter error");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

//            String inputLine;
//            while((inputLine = in.readLine()) != null)
//                System.out.println(inputLine);

            String inputLine1;
            while ((inputLine1 = in.readLine()) != null)
                System.out.println("Server: " + inputLine1);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(socket != null) {
                socket.close();
            }
            if(out != null) {
                out.close();
            }
            if(in != null) {
                in.close();
            }
        }
    }
}