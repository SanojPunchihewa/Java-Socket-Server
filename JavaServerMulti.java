// Author : Sanoj Punchihewa

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaServerMulti implements Runnable{

    ServerSocket serverSocket;
    Socket socket;

    public JavaServerMulti(int port) throws IOException{
        serverSocket = new ServerSocket(port);
    }

    private void loop_socket() throws IOException{
        while (true){
            socket = serverSocket.accept();
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run(){
        try {            
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
                e.printStackTrace();
            }finally {
                socket.close();
            }   
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException{
        int port = 1250;
        JavaServerMulti server = new JavaServerMulti(port);
        server.loop_socket();
    }
}
