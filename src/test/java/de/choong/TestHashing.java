package de.choong;

import org.junit.Assert;
import org.junit.Test;

import de.choong.util.UserUtil;

public class TestHashing {

    @Test
    public void testSalting() {
        String salt = UserUtil.generateSalt();
        Assert.assertEquals(16, salt.length());
    }

    @Test
    public void testHashing() {
        String pw1 = "";
        String pw2 = "";
        String salt = "OK6nlvlNOCmRkerA";
        String hash1 = UserUtil.hash(pw1, salt);
        String hash2 = UserUtil.hash(pw2, salt);
        Assert.assertTrue(hash1.equals(hash2));
        System.out.println(hash1.length());
    }
}
