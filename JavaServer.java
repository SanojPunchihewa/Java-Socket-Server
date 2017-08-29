// Author : Sanoj Punchihewa

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaServer {

    ServerSocket serverSocket;

    public JavaServer(int port) throws IOException{
        serverSocket = new ServerSocket(port);
    }

    private void loop_socket() throws IOException{
        Socket socket = serverSocket.accept();
        while(true){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                String line;
                while((line = reader.readLine()) != null){
                    System.out.println(line);
                    writer.write(line);
                    writer.flush();
                }
            }catch (IOException e){

            }finally {
                socket.close();
            }
        }
    }

    public static void main(String[] args) throws IOException{
        int port = 1250;
        JavaServer server = new JavaServer(port);
        server.loop_socket();
    }

}
