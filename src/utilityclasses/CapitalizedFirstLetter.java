package utilityclasses;

import java.util.StringTokenizer;

/**
 *
 * @author Dondell Batac
 */
public class CapitalizedFirstLetter {

    public static String capitalizeFirstLetter(String str) {
        final StringTokenizer st = new StringTokenizer(str, " ", true);
        final StringBuilder sb = new StringBuilder();

        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            Character ch = Character.valueOf(token.charAt(0));
            token = String.format("%s%s", Character.toUpperCase(token.charAt(0)), token.substring(1));
            sb.append(token);
        }
        return sb.toString();
    }

}
