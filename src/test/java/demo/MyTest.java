package demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

interface MyInterface {
    void doAThing();
    boolean returnAThing();
}

public class MyTest {
    @Test
    void test1() {
        MyInterface i = mock(MyInterface.class);
        verifyNoInteractions(i);
    }

    @Test
    void test2() {
        MyInterface i = mock(MyInterface.class);
        doThrow(new RuntimeException("Boom")).when(i).doAThing();
        try {
            i.doAThing();
            fail("Expected exception");
        } catch (RuntimeException e) {
            verify(i, times(1)).doAThing();
        }
        verifyNoMoreInteractions(i);
    }

    @Test
    void test3() {
        MyInterface i = mock(MyInterface.class);
        when(i.returnAThing()).thenReturn(true);
        assertEquals(true, i.returnAThing());
    }
}