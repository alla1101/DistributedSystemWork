import java.io.*;
import java.net.*;
import java.util.*;

public class Client implements Runnable{

    Socket connectionSocket;

    int mode; // 0 for read, 1 for write

    public Client(Socket s, int mode){
        try {
            connectionSocket=s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mode=mode;
    }

    public void WriteToServer() throws Exception{
        
        Scanner reader = new Scanner(System.in);

        BufferedWriter writer=new BufferedWriter(
            new OutputStreamWriter(
                connectionSocket.getOutputStream()
            )
        );

        String message;

        while(true){
            message=reader.nextLine();
            writer.write(""+message+"\r\n");
            writer.flush();
        }
        
    }

    public void ReadFromServer() throws Exception{
        
        BufferedReader reader=new BufferedReader(
            new InputStreamReader(
                connectionSocket.getInputStream()
            )
        );

        String serverMSG;
        
        while((serverMSG=reader.readLine())!=null)
        {
            System.out.println("Client: "+serverMSG);
        }

    }

    public void run()
    {
        try{

            if(this.isModeRead())
            {
                this.ReadFromServer();
            }
            
            if(this.isModeWrite())
            {
                this.WriteToServer();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private boolean isModeRead(){
        return this.mode==0;
    }

    private boolean isModeWrite(){
        return this.mode==1;
    }

    public static void main(String argv[]){

        try {
            
            Socket socketClient=new Socket("server",1234);
            
            System.out.println("connected to server");

            Client allaClientReader=new Client(socketClient,0);
            Client allaClientWriter=new Client(socketClient,1);

            Thread readerThread=new Thread(allaClientReader);
            readerThread.start();
        
            Thread writerThread=new Thread(allaClientWriter);
            writerThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}