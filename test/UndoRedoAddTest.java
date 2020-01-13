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
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.RED, Color.GREEN);
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.GREEN, Color.BLUE);
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.BLUE, Color.YELLOW);
    }


    @Test
    public void addOnExistingHeadTest() {
        while (undoRedo.canUndo()) {
            undoRedo.undo();
        }
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.YELLOW, Color.BLACK);
        System.out.println(undoRedo.toString());
        Assertions.assertEquals(2, undoRedo.getSize());
    }

    @Test
    public void addOnExistingElementBehindHeadTest() {
        while (undoRedo.canUndo()) {
            undoRedo.undo();
        }
        undoRedo.redo();
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.YELLOW, Color.BLACK);
        System.out.println(undoRedo.toString());
        Assertions.assertEquals(3, undoRedo.getSize());
    }

    @Test
    public void addOnExistingTailTest() {
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.YELLOW, Color.BLACK);
        System.out.println(undoRedo.toString());
        Assertions.assertEquals(6, undoRedo.getSize());
    }


    @Test
    public void addNewKeyOnExistingHeadTest() {
        while (undoRedo.canUndo()) {
            undoRedo.undo();
        }
        undoRedo.add(KEY_TEXT_COLOR, Color.WHITE, Color.BLACK);
        System.out.println(undoRedo.toString());
        Assertions.assertEquals(2, undoRedo.getSize());
    }

    @Test
    public void addNewKeyOnExistingElementBehindHeadTest() {
        while (undoRedo.canUndo()) {
            undoRedo.undo();
        }
        undoRedo.redo();
        undoRedo.add(KEY_TEXT_COLOR, Color.WHITE, Color.BLACK);
        System.out.println(undoRedo.toString());
        Assertions.assertEquals(4, undoRedo.getSize());
    }


    @Test
    public void addNewKeyAtTailTest() {
        undoRedo.add(KEY_TEXT_COLOR, Color.WHITE, Color.BLACK);
        System.out.println(undoRedo.toString());
        Assertions.assertEquals(7, undoRedo.getSize());
    }

    @Test
    public void addNewKeyBetweenExistingElementsTest() {
        undoRedo.undo();
        undoRedo.undo();
        undoRedo.add(KEY_TEXT_COLOR, Color.WHITE, Color.BLACK);
        System.out.println(undoRedo.toString());
        Assertions.assertEquals(5, undoRedo.getSize());
    }

    @Test
    public void addNewKeyOnDifferentExistingKeyTest() {
        undoRedo.add(KEY_TEXT_COLOR, Color.WHITE, Color.BLACK);
        undoRedo.undo();
        undoRedo.add(KEY_BACKGROUND_COLOR, Color.YELLOW, Color.GREY);

        Action action = undoRedo.undo();
        Assertions.assertEquals(Color.YELLOW, action.value);

        action = undoRedo.undo();
        Assertions.assertEquals(Color.BLUE, action.value);
    }


    @Test
    public void addNewKeyBeforeDifferentExistingKeyTest() {
        undoRedo.add(KEY_TEXT_COLOR, Color.BLACK, Color.WHITE);
        undoRedo.undo();
        undoRedo.undo();
        undoRedo.add(KEY_TEXT_COLOR, Color.BLACK, Color.GREEN);
        System.out.println(undoRedo.toString());
        Assertions.assertEquals(6, undoRedo.getSize());
    }


}