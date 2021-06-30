import org.openjdk.jol.info.ClassLayout;

/**
 * JolTest
 *
 * @author xgSama
 * @date 2021/6/28 18:01
 */
public class JolTest {
    public static void main(String[] args) {

        String str = "abc";
        System.out.println(ClassLayout.parseInstance(str).toPrintable());
    }
}
