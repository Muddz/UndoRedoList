import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UndoRedoAddTest {

    private static final String KEY_BACKGROUND_COLOR = "KEY_BACKGROUND_COLOR";
    private static final String KEY_TEXT_COLOR = "KEY_TEXT_COLOR";
    private UndoRedoList undoRedo;

    @BeforeEach
    void setUp() {
        undoRedo = new UndoRedoList();
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.WHITE, Color.RED);
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.RED, Color.BLUE);
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.BLUE, Color.YELLOW);
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.YELLOW, Color.MAGENTA);
    }


    @Test
    public void addSameKeyOnExistingHeadTest() {
        while (undoRedo.canUndo()) {
            undoRedo.undo();
        }
        undoRedo.add(KEY_BACKGROUND_COLOR, undoRedo.getCurrent().value, Color.BLACK);

        Assertions.assertFalse(undoRedo.canRedo());
        Assertions.assertNull(undoRedo.redo());
        Assertions.assertEquals(Color.BLACK, undoRedo.getCurrent().value);
        Assertions.assertEquals(Color.WHITE, undoRedo.getPrevious().value);
        Assertions.assertEquals(2, undoRedo.getSize());
    }

    @Test
    public void addSameKeyBetweenExistingElementsTest() {
        undoRedo.undo();
        undoRedo.undo();
        undoRedo.add(KEY_BACKGROUND_COLOR, undoRedo.getCurrent().value, Color.BLACK);

        Assertions.assertFalse(undoRedo.canRedo());
        Assertions.assertNull(undoRedo.redo());
        Assertions.assertTrue(undoRedo.canUndo());
        Assertions.assertEquals(Color.BLACK, undoRedo.getCurrent().value);
        Assertions.assertEquals(Color.BLUE, undoRedo.getPrevious().value);
        Assertions.assertEquals(4, undoRedo.getSize());
    }

    @Test
    public void addDifferentKeyBetweenExistingElementsTest() {
        undoRedo.undo();
        undoRedo.undo();
        undoRedo.add(KEY_TEXT_COLOR, Color.BLACK, Color.WHITE);

        Assertions.assertFalse(undoRedo.canRedo());
        Assertions.assertNull(undoRedo.redo());
        Assertions.assertTrue(undoRedo.canUndo());
        Assertions.assertEquals(Color.WHITE, undoRedo.getCurrent().value);
        Assertions.assertEquals(Color.BLACK, undoRedo.getPrevious().value);
        Assertions.assertEquals(5, undoRedo.getSize());
    }
}