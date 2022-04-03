package app.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * This class represents the login manager for the system.
 *
 * @author Yossi Toohband
 *
 */

/**
 * This class represents the login manager for the system.
 * 
 * @author Yossi and Avi Toohband
 *
 */
@Service
public class LoginManager {

	@Autowired
	private ClientType clientType;

	/**
	 * This method returns a client facade.
	 *
	 * @param ct
	 * @return a clientFacade
	 */
	public ClientService login(ClientType.CType ct) {
		return clientType.getServiceType(ct);
	}
}
