import java.io.*;

/**
 * [Add your documentation here]
 *
 * @author Darius Tse and Alyssa Lyman
 * @version date
 */
public class ChatFilter {
    private String badWordsFileName;

    public ChatFilter(String badWordsFileName) {
        this.badWordsFileName = badWordsFileName;
    }

    public String filter(String msg) throws IOException {
        File f = new File(badWordsFileName);
        if (f.exists()) {
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            try {
                while (true) {
                    String line = bfr.readLine();
                    if (line == null) {
                        break;
                    }
                    if (msg.contains(line)) {
                        int length = line.length();
                        String replacement = "";
                        for (int i = 0; i < length; i++) {
                            replacement += "*";
                        }
                        msg = msg.replaceAll(line, replacement);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return msg;
    }
}
