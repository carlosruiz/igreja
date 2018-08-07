package br.com.batistaserradinho.Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class Criptografia {

    public static String criptografar(String texto) {
        try {   	
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(texto.getBytes());
        
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
    
        return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String hexToString(String textoEmHex) throws DecoderException, UnsupportedEncodingException{
        byte[] bytes = Hex.decodeHex(textoEmHex.toCharArray());
        return new String(bytes, "UTF-8");
    }
    
    public static String stringToHex(String texto){
        return Hex.encodeHexString(texto.getBytes());
    }
}
