package com.epam.hplus.controller.commands.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class PasswordEncryptorTest {
    private final String originalPassword;
    private final String encryptedPassword;

    public PasswordEncryptorTest(String originalPassword, String encryptedPassword) {
        this.originalPassword = originalPassword;
        this.encryptedPassword = encryptedPassword;
    }

    @Test
    public void encryptPasswordTest() {
        Assert.assertEquals(encryptedPassword,
                PasswordEncryptor.encryptPassword(originalPassword));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"admin", "d033e22ae348aeb5660fc2140aec35850c4da997"},
                {"aDmIn", "b63c3b79d63321b22cdd059f0a8404beb7aeb1dc"},
                {"ADMIN", "b521caa6e1db82e5a01c924a419870cb72b81635"},
                {"user", "12dea96fec20593566ab75692c9949596833adc9"},
                {"USER", "6eb0c61201a96afc99cbf180f1c8d93c0a9fd8c8"},
                {"UsEr", "fa2611cea6348857b80c714d03457dc235bb7ef1"},
                {"uSrR", "c67606aef236a602d19edf1135b2d7733213a973"},
                {"1234", "7110eda4d09e062aa5e4a390b0a572ac0d2c0220"},
                {"1243", "6fd599b68b363c01a1296645da4305d3313ebb3f"},
                {"1235", "ac1ab23d6288711be64a25bf13432baf1e60b2bd"},
                {"re234sdf", "8b295489dcdfd04da33f7a4d0d81d618af2c2339"},
                {"<>sd!!dsff>", "f84b526274b0b3904b77e2d597c527a20ff5b56e"},
                {"БВс7Ры3kj!.<", "d2e0ceda2191dfb912a1cd7cf753d5c27a3ed279"},
                {"Явгения1995", "57b50dce4a3018077bcdfe79a6326abbf3a92fba"},
                {"NiktoKromeNas", "f018c16431e6b6d47a3599bf561a14dc466e4cc6"},
                {"VivaLaViva", "861e6ddfbea25a1aaecc619d77f1fdea882fc1e3"},
                {"ZhyveBelarus", "e9e3a952e2aaeb3bbfa719bd159e1e9ae44b660a"}
        });
    }

}
