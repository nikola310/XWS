package xmlweb.projekat.soap;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xmlweb.projekat.model.dtos.UserDTO;
import xmlweb.projekat.service.interfaces.UserServiceInterface;
import xmlweb.projekat.soap.models.GetUserRequest;
import xmlweb.projekat.soap.models.GetUserResponse;
import xmlweb.projekat.soap.models.UserRequest;
import xmlweb.projekat.soap.models.UserSOAP;

@Endpoint
public class UserEndpoint {

	public static final String NAMESPACE_URI = "http://xmlweb/projekat/soap/models";

	private UserServiceInterface service;

	@Autowired
	public UserEndpoint(UserServiceInterface service) {
		this.service = service;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
	@ResponsePayload
	public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
		GetUserResponse response = new GetUserResponse();
		ArrayList<UserDTO> listaDTO = service.ReadAll();
		ArrayList<UserSOAP> retVal = new ArrayList<>();
		for (UserDTO dto : listaDTO) {
			UserSOAP sp = new UserSOAP();
			sp.setEntityVersion(dto.getVersion());
			sp.setFirstName(dto.getFirstName());
			sp.setLastName(dto.getLastName());
			sp.setPassword(dto.getPassword());
			sp.setPid(dto.getPid());
			sp.setUserId(dto.getId());
			sp.setUserName(dto.getUserName());
			sp.setUserType(dto.getUserType().toString());
			sp.setAgentAddress(dto.getAgentLocation());
			retVal.add(sp);
		}

		for (UserRequest req : request.getUser()) {
			Iterator<UserSOAP> listItr = retVal.iterator();
			while (listItr.hasNext()) {
				UserSOAP u = listItr.next();
				if (req.getEntityId() == u.getUserId() && req.getEntityVersion() == u.getEntityVersion()) {
					listItr.remove();
					break;
				}
			}
		}

		response.getUser().addAll(retVal);
		return response;
	}

}
