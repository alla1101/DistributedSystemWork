import java.io.*;
import java.net.*;

class Server{

    public static void main(String argv[]) throws Exception{

        System.out.println("Server Code is Running");

        ServerSocket serverSock=new ServerSocket(1234);
        
        while(true){

            Socket connectionSocket= serverSock.accept();

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

            writer.write("***** a server who loves to add numbers ******\n");
            writer.write("**** enter the first number *****\n");
            int num1=Integer.parseInt(
                reader.readLine().trim()
            );

            writer.write("**** enter the Second number *****\n");
            int num2=Integer.parseInt(
                reader.readLine().trim()
            );

            int result=num1+num2;

            System.out.println("Addition Completed");

            writer.write("\r\n and the result is ....."+result);
            writer.flush();
            connectionSocket.close();
        }

    }
}