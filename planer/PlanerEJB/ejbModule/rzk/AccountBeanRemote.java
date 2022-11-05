package rzk;

import javax.ejb.Remote;

@Remote
public interface AccountBeanRemote {
	boolean createAccount(String firstName, String lastName, String email, String password);
}
