package tw.com.rex.main;

/**
 * @author Rex on 2017/05/12.
 */
public class Main {

    public static void main(String[] args) {
        String gg = "%s萬~%s萬";
        String g = "0.0";
        String format = String.format(gg, g, g);
        System.out.println(format);
    }

}
