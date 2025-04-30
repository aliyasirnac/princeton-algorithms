package com.aliyasirnac.algorithms;

import junit.framework.TestCase;

public class QuickUnionUFTest extends TestCase {
    private QuickUnionUF quickUnionUF;
    private final int size = 10;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        quickUnionUF = new QuickUnionUF(size);
    }

    public void testUnionAndConnectedBasic() {
        for (int i = 0; i < size - 1; i++) {
            assertFalse(quickUnionUF.connected(i, i + 1));
        }
        assertEquals(size, quickUnionUF.getCount());

        quickUnionUF.union(0, 1);
        quickUnionUF.union(2, 3);
        quickUnionUF.union(4, 5);

        assertTrue(quickUnionUF.connected(0, 1));
        assertTrue(quickUnionUF.connected(2, 3));
        assertTrue(quickUnionUF.connected(4, 5));

        assertFalse(quickUnionUF.connected(0, 2));
        assertFalse(quickUnionUF.connected(1, 3));
        assertFalse(quickUnionUF.connected(4, 6));

        assertEquals(size - 3, quickUnionUF.getCount());
    }

    public void testUnionChainAndConnected() {
        quickUnionUF.union(0, 1);
        quickUnionUF.union(1, 2);
        quickUnionUF.union(2, 3);

        assertTrue(quickUnionUF.connected(0, 3));
        assertTrue(quickUnionUF.connected(1, 3));
        assertTrue(quickUnionUF.connected(0, 2));

        assertFalse(quickUnionUF.connected(0, 4));
        assertFalse(quickUnionUF.connected(3, 5));

        assertEquals(size - 3, quickUnionUF.getCount());
    }

    public void testUnionAlreadyConnected() {
        quickUnionUF.union(0, 1);
        quickUnionUF.union(0, 2);

        assertTrue(quickUnionUF.connected(1, 2));

        quickUnionUF.union(0, 1);

        assertTrue(quickUnionUF.connected(0, 1));
        assertTrue(quickUnionUF.connected(0, 2));
        assertTrue(quickUnionUF.connected(1, 2));

        assertEquals(size - 2, quickUnionUF.getCount());
    }

    public void testUnionAllElements() {
        for (int i = 0; i < size - 1; i++) {
            quickUnionUF.union(i, i + 1);
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                assertTrue(quickUnionUF.connected(i, j));
            }
        }

        assertEquals(1, quickUnionUF.getCount());
    }

    public void testSmallSize() {
        quickUnionUF = new QuickUnionUF(1);
        assertEquals(1, quickUnionUF.getCount());

        quickUnionUF = new QuickUnionUF(2);
        assertEquals(2, quickUnionUF.getCount());
        assertFalse(quickUnionUF.connected(0, 1));
        quickUnionUF.union(0, 1);
        assertTrue(quickUnionUF.connected(0, 1));
        assertEquals(1, quickUnionUF.getCount());
    }

    public void testBoundaryConnections() {
        quickUnionUF.union(0, 5);
        quickUnionUF.union(size - 1, 3);

        assertTrue(quickUnionUF.connected(0, 5));
        assertTrue(quickUnionUF.connected(size - 1, 3));
        assertFalse(quickUnionUF.connected(0, size - 1));
    }

    public void testSequentialUnionsConnectsAll() {
        quickUnionUF.union(0, 1);
        quickUnionUF.union(1, 2);
        quickUnionUF.union(2, 3);
        quickUnionUF.union(3, 4);
        quickUnionUF.union(4, 5);
        quickUnionUF.union(5,6);
        quickUnionUF.union(6,7);
        quickUnionUF.union(7,8);
        quickUnionUF.union(8,9);

        for(int i = 0; i < size - 1; i++) {
            assertTrue(quickUnionUF.connected(i, i+1));
            assertTrue(quickUnionUF.connected(0, i+1));
        }
        assertEquals(1, quickUnionUF.getCount());
    }

    public void testFindCanoncial() {
        quickUnionUF.union(1,2);
        quickUnionUF.union(6, 9);
        quickUnionUF.union(1, 6);

        assertEquals(9, quickUnionUF.findCanoncial(1));
        assertEquals(9, quickUnionUF.findCanoncial(6));
        assertEquals(9, quickUnionUF.findCanoncial(2));
    }
}