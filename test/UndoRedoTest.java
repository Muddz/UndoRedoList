import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UndoRedoTest {

    private int[] elements = {1, 2, 3, 4, 5};
    private UndoRedoList<Integer> undoRedo;

    @BeforeEach
    void setUp() {
        undoRedo = new UndoRedoList<>();
        for (int i : elements) {
            undoRedo.add(i);
        }
    }


    @Test
    void testAddBetweenElements() {
        int newElement = 6;
        while (undoRedo.canUndo()) {
            if (undoRedo.undo() == 2) {
                break;
            }
        }
        undoRedo.add(newElement);

        Assertions.assertFalse(undoRedo.canRedo());
        Assertions.assertEquals(6,undoRedo.getCurrent());
        Assertions.assertEquals(3,undoRedo.size());
        Assertions.assertEquals(2,undoRedo.undo());
        Assertions.assertEquals(1,undoRedo.undo());
    }

    @Test
    void testGetCurrent() {
        Assertions.assertEquals(5, undoRedo.getCurrent());
        undoRedo.undo();
        Assertions.assertEquals(4, undoRedo.getCurrent());
    }

    @Test
    void testRedo() {
        undoRedo.undo();
        undoRedo.undo();
        Assertions.assertEquals(4, undoRedo.redo());
        Assertions.assertEquals(5, undoRedo.redo());
    }

    @Test
    void testUndo() {
        Assertions.assertEquals(4, undoRedo.undo());
        Assertions.assertEquals(3, undoRedo.undo());
    }

    @Test
    void testCanRedo() {
        undoRedo.undo();
        Assertions.assertTrue(undoRedo.canRedo());
    }

    @Test
    void testCanUndo() {
        Assertions.assertTrue(undoRedo.canUndo());
    }

    @Test
    void testSizeCount() {
        Assertions.assertEquals(elements.length, undoRedo.size());
    }

    @Test
    void testIsNotEmpty() {
        Assertions.assertFalse(undoRedo.isEmpty());
    }

    @Test
    void testClear() {
        undoRedo.clear();
        Assertions.assertEquals(0, undoRedo.size());
        Assertions.assertTrue(undoRedo.isEmpty());
    }
}