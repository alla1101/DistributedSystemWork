import java.io.*;
import java.net.*;
import java.util.*;

class Server implements Runnable{
    
    Socket connectionSocket;
    public static Vector<BufferedWriter> clients;
    
    public Server(Socket s){
        try {
            System.out.println("Client got Connected");
            connectionSocket=s;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run()
    {
        try{
            BufferedReader reader=new BufferedReader(
                new InputStreamReader(
                    connectionSocket.getInputStream()        
                )
            );

            BufferedWriter writer=new BufferedWriter(
                new OutputStreamWriter(
                    connectionSocket.getOutputStream()
                )
            );

            clients.add(writer);
            int ClientId = clients.size()-1;
            while(true){
                String data1 = reader.readLine().trim();
                System.out.println("Received : "+data1);

                for(int i=0; i<clients.size();i++){
                    if(i==ClientId){continue;}
                    try{
                        BufferedWriter bw=(BufferedWriter)clients.get(i);
                        bw.write(data1);
                        bw.write("\r\n");
                        bw.flush();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }

            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String argv[]) throws Exception{
        clients=new Vector<BufferedWriter>();
        System.out.println("Server is running, please start clients");

        ServerSocket serverSock=new ServerSocket(1234);
        while(true){
            Socket connectionSocket= serverSock.accept();
            Server allaServer=new Server(connectionSocket);

            Thread serverThread=new Thread(allaServer);
            serverThread.start();
        }
    }
}