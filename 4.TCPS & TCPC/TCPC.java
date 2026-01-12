/*Client program*/

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileClient {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            // Connect to server
            Socket socket = new Socket("localhost", 5000);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Enter file name
            System.out.print("Enter file name: ");
            String fileName = sc.nextLine();

            // Send file name to server
            dos.writeUTF(fileName);

            // Receive file contents
            String data;
            System.out.println("\nFile contents:\n");

            while (!(data = dis.readUTF()).equals("EOF")) {
                System.out.println(data);
            }

            // Close connections
            socket.close();
            sc.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


       
/*First run the server program then in new terminal window run the client program and input the 
file name the server will return the file content if it exists

output:-
SERVER PROGRAM

javac TCPS.java
java TCPS
Server ready for connection
Connection Is successful and waiting for the client request

CLIENT program
in new terminal window
javac TCPC.java
 java TCPC
Enter the filename
text.txt
This the file client requested from the server....
*/
  
