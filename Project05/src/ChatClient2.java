import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * [Add your documentation here]
 *
 * @author your name and section
 * @version date
 */
final class ChatClient2 {
    private ObjectInputStream sInput;
    private ObjectOutputStream sOutput;
    private Socket socket;

    private final String server;
    private final String username;
    private final int port;

    private ChatClient2(String server, int port, String username) {
        this.server = server;
        this.port = port;
        this.username = username;
    }

    private ChatClient2(int port, String username) {
        server = "localhost";
        this.port = port;
        this.username = username;
    }

    private ChatClient2(String username) {
        server = "localhost";
        port = 1500;
        this.username = username;
    }

    private ChatClient2() {
        server = "localhost";
        port = 1500;
        username = "Anonymous";
    }

    /*
     * This starts the Chat Client
     */
    private boolean start() {
        // Create a socket
        try {
            socket = new Socket(server, port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create your input and output streams
        try {
            sInput = new ObjectInputStream(socket.getInputStream());
            sOutput = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // This thread will listen from the server for incoming messages

        Runnable r = new ListenFromServer();
        Thread t = new Thread(r);
        t.start();

        // After starting, send the clients username to the server.
        try {
            sOutput.writeObject(username);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }


    /*
     * This method is used to send a ChatMessage Objects to the server
     */
    private void sendMessage(ChatMessage msg) {
        try {
            sOutput.writeObject(msg);
            if (msg.getType() == 1) {
                sInput.close();
                sOutput.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
     * To start the Client use one of the following command
     * > java ChatClient2
     * > java ChatClient2 username
     * > java ChatClient2 username portNumber
     * > java ChatClient2 username portNumber serverAddress
     *
     * If the portNumber is not specified 1500 should be used
     * If the serverAddress is not specified "localHost" should be used
     * If the username is not specified "Anonymous" should be used
     */
    public static void main(String[] args) {
        // Get proper arguments and override defaults
        ChatClient2 client = new ChatClient2();
        if (args.length == 3) {
            client = new ChatClient2(args[2], Integer.parseInt(args[1]), args[0]);
        } else if (args.length == 2) {
            client = new ChatClient2("localhost", Integer.parseInt(args[1]), args[0]);
        } else if (args.length == 1) {
            client = new ChatClient2("localhost", 1500, args[0]);
        } else if (args.length == 0) {
            client = new ChatClient2("localhost", 1500, "Anonymous");
        }

        // Create your client and start it
        client.start();
        // Send an empty message to the server

        client.sendMessage(new ChatMessage("", 0));
        Scanner scan = new Scanner(System.in);

        String msg = scan.nextLine();

        while (true) {
            if (msg.equals("/logout")) {

                System.out.println("\nMessage: " + msg);
                client.sendMessage(new ChatMessage(msg, 1));

                break;

            } else {
                System.out.println("\nMessage: " + msg);
                client.sendMessage(new ChatMessage(msg, 0));
                msg = scan.nextLine();
            }

        }
        try {
            client.sInput.close();
            client.sOutput.close();
            client.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This is a private class inside of the ChatClient2
     * It will be responsible for listening for messages from the ChatServer.
     * ie: When other clients send messages, the server will relay it to the client.
     *
     * @author your name and section
     * @version date
     */
    private final class ListenFromServer implements Runnable {
        public void run() {

            try {
                while (true) {
                    String msg = (String) sInput.readObject();
                    System.out.print(msg);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("logout");
            }

        }
    }
}
