package util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import model.Agent;
import model.Incidence;
import model.Incidence.Estado;
import model.IncidentManagementStaff;
import model.Property;
import repositories.AgentRepository;
import repositories.IncidentManagementStaffRepository;

@Service
@EnableJpaRepositories(basePackages = {"repositories"})
@EntityScan(basePackages = {"model"})
public class JSONAdapter {

	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private IncidentManagementStaffRepository imsRepository;
	
	/**
	 * Se encarga de parsear el String que le llega, convirtiéndolo a un objeto JSON, 
	 * y transformando dicho JSON en un objeto Incidence.
	 * 
	 * @param JSONString
	 * @return
	 * @throws BusinessException 
	 */
	public Incidence generarIncidence(String jsonIncidence) {
		JSONObject json = new JSONObject(jsonIncidence);
		List<String> names = Arrays.asList(JSONObject.getNames(json));
		
		String id = getString(names, json, "identifier");
		String login = getString(names, json, "login");
		String password = getString(names, json, "password");
		String kind = getString(names, json, "kind");
		String name = getString(names, json, "name");
		String description = getString(names, json, "description");
		
		String[] tagsArray = getStringArray(names, json, "tags");
		Set<String> tags = tagsArray != null ? 
				new HashSet<String>(Arrays.asList(tagsArray)) : 
					new HashSet<String>();
		
		Set<Property> properties =  getSetProperties(names, json) != null ? 
				 getSetProperties(names, json) : 
					new HashSet<Property>();
		
		String status = getString(names, json, "status");
		String imsIdentifier = getString(names, json, "operatorIdentifier");
		String expiration = getString(names, json, "expiration");
		
		Agent agent = agentRepository.find(login, password, kind);
		
		IncidentManagementStaff ims = imsRepository.findByIdentificador(imsIdentifier);
		
		Estado state = encontrarEstado(status);
		
		agent.setIncidences(new HashSet<Incidence>());
		
		Incidence incidence = new Incidence(name, description, state, agent, tags, properties, ims,"", expiration, false);
		incidence.setId(Long.parseLong(id));
				
		return incidence;
	}
	
	private String getString(List<String> names, JSONObject json, String key) {
		if(!names.contains(key))
			return null;
		return json.getString(key);
	}
	
	private String[] getStringArray(List<String> names, JSONObject json, String key) {
		if(!names.contains(key))
			return null;
		
		JSONArray jsonArray = json.getJSONArray(key);
		String[] strings = new String[jsonArray.length()];
		
		for (int i = 0; i < jsonArray.length(); i++)
			strings[i]= jsonArray.getString(i);
		
		return strings;
	}
	
	private Set<Property> getSetProperties(List<String> names, JSONObject json) {
		if(!names.contains("properties"))
			return null;
		
		JSONArray jsonArray = json.getJSONArray("properties");
		Set<Property> properties = new HashSet<Property>();
		
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonProperty = jsonArray.getJSONObject(i);
			
			String propertyName = JSONObject.getNames(jsonProperty)[0];
			String propertyValue = jsonProperty.getString(propertyName);
			
			Property property = new Property(propertyName, propertyValue);
			properties.add(property);
		}	
		
		return properties;
	}
	
	private Estado encontrarEstado (String status) {
		switch (status) {
		case "ABIERTA":
			return Estado.ABIERTA;
		case "ANULADA":
			return Estado.ANULADA;
		case "CERRADA":
			return Estado.CERRADA;
		case "ENPROCESO":
			return Estado.ENPROCESO;
		}
		return null;
	}
}
