package com.epam.hplus.controller.commands.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class PaginatorTest {
    private final int page;
    private final int indexForPage;
    private final int numberOfItems;
    private final int numberOfPages;

    public PaginatorTest(int page, int indexForPage, int numberOfItems, int numberOfPages) {
        this.page = page;
        this.indexForPage = indexForPage;
        this.numberOfItems = numberOfItems;
        this.numberOfPages = numberOfPages;
    }

    @Test
    public void countCurrentIndexTest() {
        Assert.assertEquals(indexForPage, Paginator.countCurrentIndex(page));
    }

    @Test
    public void countNumberOfPagesTest() {
        Assert.assertEquals(numberOfPages, Paginator.countNumberOfPages(numberOfItems));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 0, 101, 21},
                {2, 5, 14, 3},
                {3, 10, 6, 2},
                {4, 15, 5, 1},
                {5, 20, 9, 2},
                {10, 45, 11, 3},
                {0, 0, 0, 0},
                {-10, 0, -1, 0},
                {1000, 4995, 1, 1},
                {-1234, 0, -1234, 0}
        });
    }

}
