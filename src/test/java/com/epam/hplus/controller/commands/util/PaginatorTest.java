package com.epam.hplus.controller.commands.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

import static com.epam.hplus.util.constants.Context.REQUEST_PAGE;

@RunWith(value = Parameterized.class)
public class PaginatorTest {
    private final int page;
    private final int indexForPage;
    private final int numberOfItems;
    private final int numberOfPages;
    private final String requestParameter;
    private final Object sessionAttribute;
    private final Integer expectedInteger;
    private final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private final HttpSession session = Mockito.mock(HttpSession.class);

    public PaginatorTest(int page, int indexForPage, int numberOfItems, int numberOfPages,
                         String requestParameter, Object sessionAttribute,
                         Integer expectedInteger) {
        this.page = page;
        this.indexForPage = indexForPage;
        this.numberOfItems = numberOfItems;
        this.numberOfPages = numberOfPages;
        this.requestParameter = requestParameter;
        this.sessionAttribute = sessionAttribute;
        this.expectedInteger = expectedInteger;
    }

    @Before
    public void setUp() throws Exception {
        Mockito.when(request.getParameter(REQUEST_PAGE)).thenReturn(requestParameter);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(REQUEST_PAGE)).thenReturn(sessionAttribute);
    }

    @Test
    public void countCurrentIndexTest() {
        Assert.assertEquals(indexForPage, Paginator.countCurrentIndex(page));
    }

    @Test
    public void countNumberOfPagesTest() {
        Assert.assertEquals(numberOfPages, Paginator.countNumberOfPages(numberOfItems));
    }

    @Test
    public void getCurrentPageTest() {
        Assert.assertEquals(expectedInteger, Paginator.getCurrentPage(request));
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 0, 101, 21, "12", null, 12},
                {2, 5, 14, 3, "-18", 12, -18},
                {3, 10, 6, 2, "12.3", 8, 8},
                {4, 15, 5, 1, "TEN", -4, -4},
                {5, 20, 9, 2, "0", 24, 24},
                {10, 45, 11, 3, "text", "12", 1},
                {0, 0, 0, 0, "text", 12.22, 1},
                {-10, 0, -1, 0, null, 12, 12},
                {1000, 4995, 1, 1, "3", 1, 3},
                {-1234, 0, -1234, 0, null, null, 1}
        });
    }
}
