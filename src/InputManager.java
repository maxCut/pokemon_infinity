import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class InputManager implements KeyListener {
    private static HashMap<Integer, Boolean> keys;
    public InputManager() {
        keys = new HashMap<Integer, Boolean>();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        keys.put(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys.put(e.getKeyCode(), false);
    }

    public boolean isKeyPressed(int code) {
        try {
            if (code == KeyEvent.VK_ENTER) {
                boolean status = keys.get(code);
                keys.put(code, false);
                return status;
            }
            return keys.get(code);
        } catch (Exception e) {
            return false;
        }
    }
}
