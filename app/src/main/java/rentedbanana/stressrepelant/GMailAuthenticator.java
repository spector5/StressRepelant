package rentedbanana.stressrepelant;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by Austin on 2/3/2018.
 */
class GMailAuthenticator extends Authenticator {
    String user;
    String pw;
    public GMailAuthenticator (String username, String password)
    {
        super();
        this.user = username;
        this.pw = password;
    }
    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(user, pw);
    }
}
