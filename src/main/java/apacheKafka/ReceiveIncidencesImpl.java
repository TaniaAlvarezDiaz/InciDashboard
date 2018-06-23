package apacheKafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import model.Incidence;
import util.JSONAdapter;

@Component
public class ReceiveIncidencesImpl implements ReceiveIncidences {

	@Autowired
	private JSONAdapter jsonAdapter;
	
	@Autowired
    private SimpMessagingTemplate template;
	
	@Override
	public void receiveCurrentIncidences(String jsonIncidence) {
		Incidence incidence = jsonAdapter.generarIncidence(jsonIncidence);
		// Enviamos la incidencia a la vista, mediante web sockets
		this.template.convertAndSend("/topic/incidences", incidence);
	}

}
