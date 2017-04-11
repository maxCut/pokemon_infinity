
import java.io.InputStream;
/**
 * This Class makes all the photos used savable to a jar
 */
public final class Pictures
{
    public static InputStream load(String path)
    {
        InputStream input = Pictures.class.getResourceAsStream(path);
        if (input == null)
        {
            input = Pictures.class.getResourceAsStream("img/" + path);
        }
        return input;
    }
}
