package ma.demo.devfest.profique.util;

import com.google.inject.servlet.RequestScoped;

/**
 * Tady mame ulozene informace o tom, kdo nas vola a proc.
 *
 */
@RequestScoped
public class CallContext {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
