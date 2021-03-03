package com.epam.hplus.model.validators;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@RunWith(value = Parameterized.class)
public class UserValidatorTest {
    private final UserValidator validator = new UserValidator();
    private final String validUsername;
    private final String notValidUsername;
    private final String validPassword;
    private final String notValidPassword;
    private final Date validDate;
    private final Date notValidDate;
    private final String validName;
    private final String notValidName;

    public UserValidatorTest(String validUsername, String notValidUsername,
                             String validPassword, String notValidPassword,
                             Date validDate, Date notValidDate,
                             String validName, String notValidName) {
        this.validUsername = validUsername;
        this.notValidUsername = notValidUsername;
        this.validPassword = validPassword;
        this.notValidPassword = notValidPassword;
        this.validDate = validDate;
        this.notValidDate = notValidDate;
        this.validName = validName;
        this.notValidName = notValidName;
    }

    @Test
    public void isValidUserNamePositiveTest() {
        Assert.assertTrue(validator.isValidUsername(validUsername));
    }

    @Test
    public void isValidUserNameNegativeTest() {
        Assert.assertFalse(validator.isValidUsername(notValidUsername));
    }

    @Test
    public void isValidPasswordPositiveTest() {
        Assert.assertTrue(validator.isValidPassword(validPassword));
    }

    @Test
    public void isValidPasswordNegativeTest() {
        Assert.assertFalse(validator.isValidPassword(notValidPassword));
    }

    @Test
    public void isValidDatePositiveTest() {
        Assert.assertTrue(validator.isValidDate(validDate));
    }

    @Test
    public void isValidDateNegativeTest() {
        Assert.assertFalse(validator.isValidDate(notValidDate));
    }

    @Test
    public void isValidNamePositiveTest() {
        Assert.assertTrue(validator.isValidName(validName));
    }

    @Test
    public void isValidNameNegativeTest() {
        Assert.assertFalse(validator.isValidName(notValidName));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"username", "notValidUsername!",
                        "password", "notOk",
                        new Date(126229680000L), new Date(1262296800000L),
                        "Li", "@ntony"},
                {"UsErNaMe", "hn",
                        "д.а.ж.е.Str+ong_not=safe*!", "MoreThanFiftyCharactersIsTooLargeForPasswordReally",
                        new Date(229680000L), new Date(1262296800001L),
                        "Тен", "Li_"},
                {"USERName1234", "NameIsTooLongReallyLongForUserName",
                        "BVc7рублейW!!!", "!@!",
                        new Date(1269680000L), new Date(1452296800001L),
                        "Андрей", "Andrew#1"},
                {"1234", "QuestionMark?",
                        "always_is-ok!", "<>!!",
                        new Date(1262296800L), new Date(1262297337001L),
                        "Esmiralda", "My@name"},
                {"User_Name", "Comas,are,not,allowed",
                        "use/any!you|want", "still not whitespaces",
                        new Date(12229680000L), new Date(2262234500001L),
                        "McMillan", "My.Name"},
                {"dots.is.allowed", "no/any/slashes",
                        "Commercial@at@sign", "why123",
                        new Date(1262290000L), new Date(),
                        "Rymskiy-Korsakov", "Name!"},
                {".dot_and_underscore.", "no\\any\\slashes",
                        "КирилЛица", "limit",
                        new Date(0L), new Date(-4577929800000L),
                        "Alexander", "z"},
                {"abcd", "no|any|pipes",
                        "~tilda~is~strange~sign~", "1234567",
                        new Date(1L), new Date(-1577929803400L),
                        "Орлова-Ленских", "NoAnyNumbers1234"},
                {"a1b2", "no(brackets)",
                        "yes.no.i.dont_know", "SeV@n!+",
                        new Date(-1L), new Date(-1677929845000L),
                        "Herman", "NoMoreThanFiftyLettersNoMoreThanFiftyLettersNoMoreThanFiftyLetters"},
                {"_-_-", "no whitespaces",
                        "any\"Questions\"?", "five",
                        new Date(-177929800000L), new Date(-15477929867560L),
                        "Lancaster", "no whitespaces"},
                {"-.-_", "безКириллицы",
                        "punctuationIsOk!!", "real",
                        new Date(-157929800000L), new Date(-7845929567056L),
                        "SometimesSurnamesCanBeReallyLong", "no_underscores"}
        });
    }
}
