package apacheKafka;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

@ManagedBean
public class MessageListener {

	private static final Logger logger = Logger.getLogger(MessageListener.class);

	@Autowired
	private ReceiveIncidencesImpl receiveIncidences;
	
    @KafkaListener(topics = "incidences")
    public void listen(String data) {
        logger.info("New message received: \"" + data + "\"");

        receiveIncidences.receiveCurrentIncidences(data);
    }
}
