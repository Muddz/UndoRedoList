import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class UndoRedoTest {

    private static final String KEY_BACKGROUND_COLOR = "KEY_BACKGROUND_COLOR";
    private UndoRedoList undoRedo;

    @BeforeEach
    public void setUp() {
        undoRedo = new UndoRedoList();
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.RED);
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.GREEN);
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.BLUE);
    }


    @Test
    public void testAddOnHead() {
        while (undoRedo.canUndo()) {
            undoRedo.undo();
        }
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.BLACK);
        Assertions.assertEquals(2, undoRedo.getSize());

        Action action = undoRedo.getCurrent();
        Assertions.assertEquals(Color.BLACK, action.value);

        action = undoRedo.undo();
        Assertions.assertEquals(Color.RED, action.value);

        Assertions.assertFalse(undoRedo.canUndo());
        undoRedo.redo();
        Assertions.assertFalse(undoRedo.canRedo());
    }

    @Test
    public void testAddInBetween() {
        undoRedo.undo();
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.BLACK);
        Assertions.assertEquals(3, undoRedo.getSize());

        Action action = undoRedo.getCurrent();
        Assertions.assertEquals(Color.BLACK, action.value);

        Assertions.assertTrue(undoRedo.canUndo());
        Assertions.assertFalse(undoRedo.canRedo());

        action = undoRedo.undo();
        Assertions.assertEquals(Color.GREEN, action.value);

        action = undoRedo.undo();
        Assertions.assertEquals(Color.RED, action.value);
    }


    @Test
    public void testUndo() {
        Action action = undoRedo.undo();
        Assertions.assertEquals(KEY_BACKGROUND_COLOR, action.key);
        Assertions.assertEquals(Color.GREEN, action.value);

        action = undoRedo.undo();
        Assertions.assertEquals(KEY_BACKGROUND_COLOR, action.key);
        Assertions.assertEquals(Color.RED, action.value);
    }

    @Test
    public void testRedo() {
        while (undoRedo.canUndo()) {
            undoRedo.undo();
        }

        Action action = undoRedo.redo();
        Assertions.assertEquals(KEY_BACKGROUND_COLOR, action.key);
        Assertions.assertEquals(Color.GREEN, action.value);

        action = undoRedo.redo();
        Assertions.assertEquals(KEY_BACKGROUND_COLOR, action.key);
        Assertions.assertEquals(Color.BLUE, action.value);

    }

    @Test
    public void testSize() {
        Assertions.assertEquals(3, undoRedo.getSize());
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
        Assertions.assertEquals(Color.BLUE, action.value);
    }
}