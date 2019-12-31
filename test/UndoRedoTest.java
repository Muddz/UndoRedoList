import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UndoRedoTest {

    private static final String KEY_TEXT_COLOR = "KEY_TEXT_COLOR";
    private static final String KEY_BACKGROUND_COLOR = "KEY_BACKGROUND_COLOR";
    private UndoRedoList undoRedo;

    @BeforeEach
    public void setUp() {
        undoRedo = new UndoRedoList();
        undoRedo.add(KEY_TEXT_COLOR, Color.BLACK, Color.RED);
        undoRedo.add(KEY_TEXT_COLOR, Color.RED, Color.BLUE);
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.WHITE, Color.RED);
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.RED, Color.BLACK);
    }

    @Test
    public void testUndo() {
        Action action = undoRedo.undo();
        Assertions.assertEquals(KEY_BACKGROUND_COLOR, action.key);
        Assertions.assertEquals(Color.RED, action.value);

        action = undoRedo.undo();
        Assertions.assertEquals(KEY_BACKGROUND_COLOR, action.key);
        Assertions.assertEquals(Color.WHITE, action.value);

        action = undoRedo.undo();
        Assertions.assertEquals(KEY_TEXT_COLOR, action.key);
        Assertions.assertEquals(Color.RED, action.value);

        action = undoRedo.undo();
        Assertions.assertEquals(KEY_TEXT_COLOR, action.key);
        Assertions.assertEquals(Color.BLACK, action.value);
    }

    @Test
    public void testRedo() {
        while (undoRedo.canUndo()) {
            undoRedo.undo();
        }

        Action action = undoRedo.redo();
        Assertions.assertEquals(KEY_TEXT_COLOR, action.key);
        Assertions.assertEquals(Color.RED, action.value);

        action = undoRedo.redo();
        Assertions.assertEquals(KEY_TEXT_COLOR, action.key);
        Assertions.assertEquals(Color.BLUE, action.value);

        action = undoRedo.redo();
        Assertions.assertEquals(KEY_BACKGROUND_COLOR, action.key);
        Assertions.assertEquals(Color.RED, action.value);

        action = undoRedo.redo();
        Assertions.assertEquals(KEY_BACKGROUND_COLOR, action.key);
        Assertions.assertEquals(Color.BLACK, action.value);
    }

    @Test
    public void testCanUndo() {
        Assertions.assertTrue(undoRedo.canUndo());
    }

    @Test
    public void testCanNotRedo() {
        Assertions.assertFalse(undoRedo.canRedo());
    }

    @Test
    public void testClear() {
        undoRedo.clear();
        Assertions.assertEquals(0, undoRedo.getSize());
        Assertions.assertTrue(undoRedo.isEmpty());
        Assertions.assertFalse(undoRedo.canUndo());
        Assertions.assertFalse(undoRedo.canRedo());
    }

    @Test
    public void testGetCurrentValue() {
        Action action = undoRedo.getCurrent();
        Assertions.assertEquals(Color.BLACK, action.value);
    }
}