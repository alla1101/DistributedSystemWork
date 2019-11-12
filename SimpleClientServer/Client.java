import java.io.*;
import java.net.*;
import java.util.*;

public class Client{

    public void askServerForAddition(int x,int y) throws Exception{
        
        Socket socketClient=new Socket("localhost",1234);

        System.out.println("connected to server");

        BufferedReader reader=new BufferedReader(
            new InputStreamReader(
                socketClient.getInputStream()
            )
        );

        BufferedWriter writer=new BufferedWriter(
            new OutputStreamWriter(
                socketClient.getOutputStream()
            )
        );

        writer.write(""+x+"\r\n");
        writer.write(""+y+"\r\n");
        writer.flush();

        String serverMSG;
        
        while((serverMSG=reader.readLine())!=null){
            System.out.println("Client: "+serverMSG);
        }

        socketClient.close();
    }

    public static void main(String argv[]){

        Client x=new Client();

        try{
            x.askServerForAddition(
                Integer.parseInt(argv[0]), 
                Integer.parseInt(argv[1])
            );

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}