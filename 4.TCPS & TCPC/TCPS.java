/* Using TCP/IP sockets, write a client – server program to make the 

client send the file name and to make the server send back the contents 

of the requested file if present*/


/*SERVER program*/

import java.io.*;
import java.net.*;

public class FileServer {

    public static void main(String[] args) {
        try {
            // Create server socket
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for client...");

            // Accept client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            // Input and Output streams
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Read file name from client
            String fileName = dis.readUTF();
            System.out.println("Requested file: " + fileName);

            File file = new File(fileName);

            if (file.exists()) {
                // Send file contents
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    dos.writeUTF(line);
                }
                dos.writeUTF("EOF");   // End of file marker
                br.close();
            } else {
                // File not found
                dos.writeUTF("File not found");
                dos.writeUTF("EOF");
            }

            // Close connections
            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}



/*first the server port should be established before it accepts any request from the client*/  
