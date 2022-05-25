package com.genspark.backend.Security;

import com.genspark.backend.Entity.UserAccount;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import de.taimos.totp.TOTP;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;

public class TwoFactorAuth {
    // Requires User to have Google Authenticator App installed.
    // if user is flagged they want 2fa - we can have them generate a secret key.
    public static String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base32 base32 = new Base32();
        return base32.encodeToString(bytes);
    }
    // previous method goes into param for this method
    // 1 of 2 Google Auth entry, this one is via a time based (30 secs) 6 digit input.
    public static String getTOTPCode(String secretKey) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secretKey);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }
    // More User friendly which is just scanning a qr code being displayed on userAccount/userID/twofactorauthsettings
    // first method can also go to this param for 2nd Google Auth entry via QR.
    public static String getGoogleAuthenticatorBarCode(String secretKey, UserAccount account) {
        try {
            return "otpauth://totp/"
                    + URLEncoder.encode( ":" + account, "UTF-8").replace("+", "%20")
                    + "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }
    // Calling this method with the string returned by the method as the 1st argument will write a
    // PNG image to the specified path with the specified height and width
    public static void createQRCode(String barCodeData, String filePath, int height, int width)
            throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(barCodeData, BarcodeFormat.QR_CODE,
                width, height);
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            MatrixToImageWriter.writeToStream(matrix, "png", out);
        }
    }
}
