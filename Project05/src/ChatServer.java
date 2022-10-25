import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * [Add your documentation here]
 *
 * @author Darius Tse and Alyssa Lyman
 * @version date
 */


final class ChatServer {
    private static int uniqueId = 0;
    private final List<ClientThread> clients = new ArrayList<>();
    private final int port;
    private File wordsToFilter;

    public static final Object LOCK = new Object();


    private ChatServer(int port, File wordsToFilter) {
        this.port = port;
        this.wordsToFilter = wordsToFilter;

    }

    private ChatServer(int port) {
        this.port = port;
        wordsToFilter = new File("badwords.txt");
    }

    private ChatServer() {
        wordsToFilter = new File("badwords.txt");
        port = 1500;
    }

    /*
     * This is what starts the ChatServer.
     * Right now it just creates the socketServer and adds a new ClientThread to a list to be handled
     */
    private void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                Runnable r = new ClientThread(socket, uniqueId++);
                Thread t = new Thread(r);
                clients.add((ClientThread) r);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getwordsToFilter() {
        return wordsToFilter;
    }

    /*
     *  > java ChatServer
     *  > java ChatServer portNumber
     *  If the port number is not specified 1500 is used
     */
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        if (args.length == 2) {
            server = new ChatServer((Integer.parseInt(args[0])), new File(args[1]));
        } else if (args.length == 1) {
            server = new ChatServer((Integer.parseInt(args[0])), new File("badwords.txt"));
        } else {
            server = new ChatServer(1500, new File("badwords.txt"));
        }
        //ChatFilter badwords = new ChatFilter(server.getwordsToFilter().getName());

        server.start();
    }

    private void broadcast(String message) {
        String now = "";
        ChatFilter badwords = new ChatFilter(wordsToFilter.getName());
        try {
            message = badwords.filter(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (ClientThread i : clients) {
            synchronized (LOCK) {
                LocalTime time = LocalTime.now();
                now = time.getHour() + ":" + time.getMinute() + ":" + time.getSecond();
                i.writeMessage(message + " " + now + "\n");

            }
        }
        System.out.println(message + " " + now + "\n");
    }

    private void remove(int id) {
        //idk what this means but im pretty sure it means remove i if id is same

        clients.removeIf(i -> i.getId() == id);
    }

    private void directMessage(String message, String username) {
        //boolean foundUser = false;
        for (ClientThread i : clients) {
            if (i.getUsername().equals(username)) {
                LocalTime time = LocalTime.now();
                String now = time.getHour() + ":" + time.getMinute() + ":" + time.getSecond();
                i.writeMessage(message + " " + now + "\n");
                //foundUser = true;
            }
            //ALSO NEED TO HANDLE ERROR FOR WHEN RECIPIENT NOT FOUND
        }

    }

    private void list(String username) {
        String list = "Users connected to the server:";
        for (ClientThread i : clients) {
            if (!i.getUsername().equals(username)) {
                list += i.getUsername() + "\n";
            }
        }
        for (ClientThread i : clients) {
            if (i.getUsername().equals(username)) {
                i.writeMessage(list);
                break;
            }
        }
    }


    /**
     * This is a private class inside of the ChatServer
     * A new thread will be created to run this every time a new client connects.
     *
     * @author Darius Tse and Alyssa Lyman
     * @version date
     */
    private final class ClientThread implements Runnable {
        Socket socket;
        ObjectInputStream sInput;
        ObjectOutputStream sOutput;
        int id;
        String username;
        ChatMessage cm;

        private ClientThread(Socket socket, int id) {
            this.id = id;
            this.socket = socket;
            try {
                sOutput = new ObjectOutputStream(socket.getOutputStream());
                sInput = new ObjectInputStream(socket.getInputStream());
                username = (String) sInput.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        /*
         * This is what the client thread actually runs.
         */
        @Override
        public void run() {

            //System.out.println("hi");
            this.writeMessage("Welcome\n");
            // Read the username sent to you by client
            while (true) {
                try {
                    cm = (ChatMessage) sInput.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }


                //System.out.println(username + ": " + cm.getMessage());


                // Send message back to the client

                int type = cm.getType();
                if (type == 0) {
                    //normal
                    broadcast(username + ": " + cm.getMessage());
                } else if (type == 1) {
                    //logout
                    clients.remove(id);
                    //broadcast(username + ": " + cm.getMessage());
                    break;
                } else if (type == 2) {
                    //dm's

                    String[] msg = cm.getMessage().split(" ", 3);

                    String recipient = msg[1];

                    String message = username + ": " + msg[2];
                    if (!username.equals(recipient)) {
                        directMessage(message, recipient);
                        this.writeMessage("You to " + recipient + ": " + msg[2]);
                    } else {
                        this.writeMessage("ERROR: CANNOT DIRECT MSG SELF\n");
                    }

                } else if (type == 3) {
                    //list
                    broadcast(username + ": " + cm.getMessage());
                    list(username);

                }
            }
            close();
        }

        private boolean writeMessage(String message) {
            if (socket.isConnected()) {
                try {
                    sOutput.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
            return false;
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        private void close() {
            try {
                socket.close();
                sInput.close();
                sOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
