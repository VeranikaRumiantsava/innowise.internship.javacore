package javacore.customlinkedlist.customlinkedlist;

import org.innowise.internship.javacore.customlinkedlist.customlinkedlist.LinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    @Test
    public void sizeShouldBeCorrect() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");
        assertEquals(3, list.size());
    }

    @Test
    public void sizeOfEmptyListShouldBeZero() {
        LinkedList<String> list = new LinkedList<>();
        assertEquals(0, list.size());
    }

    @Test
    public void addFirstShouldAddFirstElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");
        list.addFirst("B");

        list.addFirst("-A");

        assertEquals(3, list.size());
        assertEquals("-A", list.get(0));
    }

    @Test
    public void addFirstShouldAddFirstElementInEmptyList() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");
        assertEquals(1, list.size());
        assertEquals("A", list.getFirst());
    }

    @Test
    public void addLastShouldAddLastElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");
        list.addFirst("B");
        list.addLast("D");

        assertEquals(3, list.size());
        assertEquals("D", list.getLast());
    }

    @Test
    public void addLastShouldAddLastElementInEmptyList() {
        LinkedList<String> list = new LinkedList<>();
        list.addLast("D");

        assertEquals(1, list.size());
        assertEquals("D", list.getLast());
    }

    @Test
    public void addShouldAddElementCorrectly() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");
        list.add(1, "imposter");

        assertEquals(4, list.size());
        assertEquals("imposter", list.get(1));
    }

    @Test
    public void addShouldAddElementCorrectlyInBegin() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");
        list.add(0, "imposter");

        assertEquals(4, list.size());
        assertEquals("imposter", list.getFirst());
    }

    @Test
    public void addShouldAddElementCorrectlyInEnd() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");
        list.add(3, "imposter");

        assertEquals(4, list.size());
        assertEquals("imposter", list.getLast());
    }

    @Test
    public void addShouldThrowIndexOutOfBoundsExceptionWithIncorrectIndex() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");

        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-8, "imposter"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(5, "imposter"));
    }

    @Test
    public void getFirstShouldGetFirstElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("0");

        assertEquals("0", list.getFirst());
    }

    @Test
    public void getFirstShouldGetNullInEmptyList() {
        LinkedList<String> list = new LinkedList<>();

        assertNull(list.getFirst());
    }

    @Test
    public void getLastShouldGetLastElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("B");
        list.addFirst("A");
        list.addLast("end");

        assertEquals("end", list.getLast());
    }

    @Test
    public void getLastShouldGetNullInEmptyList() {
        LinkedList<String> list = new LinkedList<>();

        assertNull(list.getLast());
    }

    @Test
    public void getShouldGetCorrectElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");

        assertEquals("B", list.get(1));
    }

    @Test
    public void getShouldThrowIndexOutOfBoundsExceptionWithIncorrectIndex() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");
        list.addFirst("B");

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-8));
    }

    @Test
    public void removeFirstShouldRemoveFirstElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");

        list.removeFirst();

        assertEquals(2, list.size());
        assertEquals("B", list.getFirst());
    }

    @Test
    public void removeFirstShouldMakeListIsEmptyInListWithOneElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");

        list.removeFirst();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void removeLastShouldRemoveLastElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");
        list.addFirst("B");

        list.removeLast();

        assertEquals(1, list.size());
        assertEquals("B", list.getLast());
    }

    @Test
    public void removeLastShouldMakeListIsEmptyInListWithOneElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");

        list.removeLast();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void removeShouldRemoveElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");
        list.addFirst("D");

        list.remove(2);

        assertEquals(3, list.size());
        assertEquals("A", list.get(2));
    }

    @Test
    public void removeShouldMakeListIsEmptyInListWithOneElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");

        list.remove(0);

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void removeShouldWorkCorrectlyWithIndexOfLastElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("3");
        list.addFirst("2");
        list.addFirst("1");

        list.remove(2);

        assertEquals(2, list.size());
        assertEquals("2", list.getLast());
    }

    @Test
    public void removeShouldThrowIndexOutOfBoundsExceptionWithIncorrectIndex() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("A");
        list.addFirst("B");

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(3));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-3));
    }
}
