import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author crkimberley on 22/11/2016.
 */
public class ImprovedStackImplUsingLinkedListTest {
    ImprovedStack emptyStack, stack1Number, stackMillionNumbers, stackNames;

    @Before
    public void setUp() throws Exception {
        emptyStack = new ImprovedStackImpl(new LinkedList());
        stack1Number = new ImprovedStackImpl(new LinkedList());
        stackMillionNumbers = new ImprovedStackImpl(new LinkedList());
        stackNames = new ImprovedStackImpl(new LinkedList());
        stack1Number.push(13);
        for (int i=0; i<1000000; i++) {
            stackMillionNumbers.push(i);
        }
        stackNames.push("...");
        stackNames.push("amy");
        stackNames.push("bill");
        stackNames.push("claire");
        stackNames.push("derek");
        stackNames.push("enid");
    }

    @Test
    public void testReverse() {
        assertEquals("enid", stackNames.top().getReturnValue());
        assertEquals(6, stackNames.size());
        ImprovedStack reverseStack = stackNames.reverse();
        assertEquals(6, reverseStack.size());
        assertEquals("...", reverseStack.top().getReturnValue());
        assertEquals("...", reverseStack.pop().getReturnValue());
        assertEquals("amy", reverseStack.pop().getReturnValue());
        assertEquals("bill", reverseStack.pop().getReturnValue());
    }

    @Test
    public void testRemove() {
        assertEquals("enid", stackNames.top().getReturnValue());
        stackNames.remove("enid");
        assertEquals("derek", stackNames.top().getReturnValue());
        assertEquals(5, stackNames.size());

        stackNames.push("birkbeck");
        stackNames.push("birkbeck");
        stackNames.push("birkbeck");
        stackNames.push("birkbeck");
        stackNames.push("birkbeck");
        assertEquals(10, stackNames.size());
        stackNames.remove("birkbeck");
        assertEquals(5, stackNames.size());
        assertEquals("derek", stackNames.top().getReturnValue());

        ImprovedStack newStack = new ImprovedStackImpl(new LinkedList());
        newStack.push("a");
        newStack.push("b");
        newStack.push("b");
        newStack.push("c");
        newStack.push("d");
        newStack.push("d");
        newStack.push("d");
        newStack.push("e");
        newStack.push("e");
        newStack.push("f");
        newStack.push("g");
        newStack.push("g");
        newStack.push("g");
        newStack.push("g");
        newStack.push("g");
        newStack.push("g");
        assertEquals(16, newStack.size());
        newStack.remove("b");
        assertEquals(14, newStack.size());
        newStack.remove("g");
        assertEquals(8, newStack.size());
        assertEquals("f", newStack.pop().getReturnValue());
        assertEquals("e", newStack.pop().getReturnValue());
        assertEquals(6, newStack.size());
        newStack.remove("e");
        assertEquals(5, newStack.size());
        newStack.remove("d");
        assertEquals(2, newStack.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(emptyStack.isEmpty());
        emptyStack.push(317);
        assertFalse(emptyStack.isEmpty());
        emptyStack.pop();
        assertTrue(emptyStack.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, emptyStack.size());
        assertEquals(6, stackNames.size());
        assertEquals(1000000, stackMillionNumbers.size());
    }

    @Test
    public void testPush() {
        assertEquals(6, stackNames.size());
        stackNames.push("fiona");
        assertEquals(7, stackNames.size());
        assertEquals("fiona", stackNames.pop().getReturnValue());
    }

    @Test
    public void testTopForEmptyStackReturnsError() throws Exception {
        assertTrue(emptyStack.top().hasError());
        assertEquals(ErrorMessage.EMPTY_STRUCTURE, emptyStack.top().getError());
    }

    @Test
    public void testTopReturnsTopElementWithoutChangingStack() {
        assertEquals(1, stack1Number.size());
        assertEquals(13, stack1Number.top().getReturnValue());
        assertEquals(1, stack1Number.size());

        assertEquals(6, stackNames.size());
        assertEquals("enid", stackNames.top().getReturnValue());
        assertEquals(6, stackNames.size());
    }

    @Test
    public void testPopForEmptyStackReturnsError() {
        assertTrue(emptyStack.pop().hasError());
        assertEquals(ErrorMessage.EMPTY_STRUCTURE, emptyStack.pop().getError());
    }

    @Test
    public void testPopRemovesAndReturnsTopElement() {
        assertEquals(1, stack1Number.size());
        assertEquals(13, stack1Number.pop().getReturnValue());
        assertEquals(0, stack1Number.size());

        assertEquals(6, stackNames.size());
        assertEquals("enid", stackNames.pop().getReturnValue());
        assertEquals(5, stackNames.size());
        assertEquals("derek", stackNames.pop().getReturnValue());
        assertEquals(4, stackNames.size());
    }

    @Test
    public void testBigStack() {
        ImprovedStack bigStack = new ImprovedStackImpl(new LinkedList());
        for (int i=0; i<2000000; i++) {
            bigStack.push(i);
        }
        assertEquals(2000000, bigStack.size());
    }
}