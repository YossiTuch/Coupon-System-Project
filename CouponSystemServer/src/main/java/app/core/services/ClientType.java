package app.core.services;

import app.core.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This enum represents the facade types.
 *
 * @author Yossi and Avi Tuchband
 *
 */
@Service
public class ClientType {

	@Autowired
	private ServiceManager servicesManager;

	/**
	 * This method returns a client facade object by clientType.
	 *
	 * @param clientType
	 * @return returns ClientFacade Object
	 */
	public ClientService getServiceType(CType clientType) {
		switch (clientType) {
			case ADMIN: {
				return servicesManager.getAdminService();
			}
			case COMPANY: {
				return servicesManager.getCompanyService();
			}
			case CUSTOMER: {
				return servicesManager.getCustomerService();
			}
		}
		return null;
	}
	public enum CType {
		ADMIN, COMPANY, CUSTOMER
	}
}
