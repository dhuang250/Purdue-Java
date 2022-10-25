import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * [Add your documentation here]
 *
 * @author Darius Tse and Alyssa Lyman
 * @version date
 */
final class ChatClient {
    private ObjectInputStream sInput;
    private ObjectOutputStream sOutput;
    private Socket socket;

    private final String server;
    private final String username;
    private final int port;

    private ChatClient(String server, int port, String username) {
        this.server = server;
        this.port = port;
        this.username = username;
    }

    private ChatClient(int port, String username) {
        server = "localhost";
        this.port = port;
        this.username = username;
    }

    private ChatClient(String username) {
        server = "localhost";
        port = 1500;
        this.username = username;
    }

    private ChatClient() {
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
     * > java ChatClient
     * > java ChatClient username
     * > java ChatClient username portNumber
     * > java ChatClient username portNumber serverAddress
     *
     * If the portNumber is not specified 1500 should be used
     * If the serverAddress is not specified "localHost" should be used
     * If the username is not specified "Anonymous" should be used
     */
    public static void main(String[] args) {
        // Get proper arguments and override defaults
        ChatClient client = new ChatClient();
        if (args.length == 3) {
            client = new ChatClient(args[2], Integer.parseInt(args[1]), args[0]);
        } else if (args.length == 2) {
            client = new ChatClient("localhost", Integer.parseInt(args[1]), args[0]);
        } else if (args.length == 1) {
            client = new ChatClient("localhost", 1500, args[0]);
        } else {
            client = new ChatClient("localhost", 1500, "Anonymous");
        }

        // Create your client and start it
        client.start();
        // Send an empty message to the server

        //first message
        client.sendMessage(new ChatMessage("has joined", 0));
        Scanner scan = new Scanner(System.in);

        String msg = scan.nextLine();

        while (true) {
            if (msg.equals("/logout")) {
                //logout

                client.sendMessage(new ChatMessage(msg, 1));

                break;

            } else if (msg.contains("/msg")) {
                //dm (/msg)

                client.sendMessage(new ChatMessage(msg, 2));

                //list
            } else if (msg.contains("/list")) {

                client.sendMessage(new ChatMessage(msg, 3));

            } else {
                //normal

                client.sendMessage(new ChatMessage(msg, 0));

            }
            msg = scan.nextLine();
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
     * This is a private class inside of the ChatClient
     * It will be responsible for listening for messages from the ChatServer.
     * ie: When other clients send messages, the server will relay it to the client.
     *
     * @author Darius Tse and Alyssa Lyman
     * @version date
     */
    private final class ListenFromServer implements Runnable {
        public void run() {

            try {
                while (true) {
                    if (!socket.isClosed()) {
                        String msg = (String) sInput.readObject();
                        System.out.print(msg);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("logout");
            }

        }
    }
}
