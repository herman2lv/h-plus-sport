package com.epam.hplus.controller.commands.util;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class RequestProcessorTest {
    protected static final String INT_PARAM = "intParam";
    protected static final String BIG_DECIMAL_PARAM = "bigDecimalParam";
    private final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private final String intString;
    private final int expectedInt;
    private final String bigDecimalString;
    private final BigDecimal expectedBigDecimal;

    public RequestProcessorTest(String intString, int expectedInt,
                                String bigDecimalString, BigDecimal expectedBigDecimal) {
        this.intString = intString;
        this.expectedInt = expectedInt;
        this.bigDecimalString = bigDecimalString;
        this.expectedBigDecimal = expectedBigDecimal;
    }

    @Before
    public void setUp() throws Exception {
        Mockito.when(request.getParameter(INT_PARAM)).thenReturn(intString);
        Mockito.when(request.getParameter(BIG_DECIMAL_PARAM)).thenReturn(bigDecimalString);
    }

    @Test
    public void getIntFromRequestTest() {
        Assert.assertEquals(expectedInt, RequestProcessor.getIntFromRequest(request, INT_PARAM));
    }

    @Test
    public void getBigDecimalFromRequestTest() {
        Assert.assertEquals(expectedBigDecimal,
                RequestProcessor.getBigDecimalFromRequest(request, BIG_DECIMAL_PARAM));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"100", 100, "24.23", BigDecimal.valueOf(24.23)},
                {"-100", -100, "-24.23", BigDecimal.valueOf(-24.23)},
                {"1234", 1234, "1.974", BigDecimal.valueOf(1.974)},
                {"Camel", 0, "Winston", BigDecimal.ZERO},
                {"-0", 0, "-0.000", BigDecimal.valueOf(0.000)},
                {"9.0134", 0, "9.0134", BigDecimal.valueOf(9.0134)},
                {"null", 0, "null", BigDecimal.ZERO},
                {"-14", -14, "TEN", BigDecimal.ZERO},
                {"-0.234", 0, "123.123456789", BigDecimal.valueOf(123.123456789)},
                {"0.99", 0, "-0.001", BigDecimal.valueOf(-0.001)},
                {"1.99", 0, "+21.2", BigDecimal.valueOf(21.2)},
                {"27", 27, "1,155", BigDecimal.ZERO},
                {"-354", -354, "-4.34", BigDecimal.valueOf(-4.34)}
        });
    }
}
