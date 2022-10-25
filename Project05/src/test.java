import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        String x = "i have ";
        String[] y = x.split(" ", 3);
        System.out.printf("%s %s %s", y[0], y[1], y[2]);
    }
}
