package jewellerystore.com.example.jewellerystore;

/**
 * Created by Michael on 11/01/2016.
 */
public class Hash
{

        public static String getHash(String txt) {
            try {
                java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
                byte[] array = md.digest(txt.getBytes());
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < array.length; ++i) {
                    sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
                }
                return sb.toString();
            } catch (java.security.NoSuchAlgorithmException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }

        public static String md5(String txt) { return Hash.getHash(txt); }
}
