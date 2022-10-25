

import java.io.Serializable;

/**
 * [Add your documentation here]
 *
 * @author Darius Tse and Alyssa Lyman
 * @version date
 */
final class ChatMessage implements Serializable {
    private static final long serialVersionUID = 6898543889087L;


    // Here is where you should implement the chat message object.
    // Variables, Constructors, Methods, etc.
    private String message;
    private int type;

    //type 0 = normal
    //type 1 = logout
    //type 2 = dm
    //type 3 = list


    public ChatMessage(String message, int type) {
        this.message = message;
        this.type = type;

    }

    public String getMessage() {
        return message;
    }

    public int getType() {
        return type;
    }


}
