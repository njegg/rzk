package rzk;

import javax.ejb.Remote;

@Remote
public interface RegisterBeanRemote {
	boolean register(String username, String password, String nickname);
}
