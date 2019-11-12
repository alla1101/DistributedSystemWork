import java.io.*;
import java.net.*;
import java.util.*;

public class Client{
    public void askServerForAddition(String message,Socket socketClient) throws Exception{
        
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

        writer.write(""+message+"\r\n");
        writer.flush();

        String serverMSG;
        
        while((serverMSG=reader.readLine())!=null){
            System.out.println("Client: "+serverMSG);
        }

    }

    public static void main(String argv[]){

        Scanner sc = new Scanner(System.in);
        Client x=new Client();

        try {
            Socket socketClient=new Socket("localhost",1234);

            System.out.println("connected to server");
            String message;

            while(true){
                try{
                    message=sc.nextLine();

                    x.askServerForAddition(
                        message,
                        socketClient
                    );
        
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}