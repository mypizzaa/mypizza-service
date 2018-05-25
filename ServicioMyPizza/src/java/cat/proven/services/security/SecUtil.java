/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.services.security;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumne
 */
public class SecUtil {

    public static String getSHA256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();     
            String hex = String.format("%064x", new BigInteger(1, digest));
            return hex;
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }        
    }
}
