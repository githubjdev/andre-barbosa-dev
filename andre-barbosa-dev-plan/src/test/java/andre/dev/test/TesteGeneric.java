package andre.dev.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import lombok.Getter;

@Getter
public class TesteGeneric {

	@Autowired
	private WebApplicationContext wac;

	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc;

	@Value("${server.servlet.context-path}")
	private String context;

	@Value("${spring.web.url.hostname}")
	protected String hostname;

	protected String url(String endPoint) {
		return hostname + ":" + port + context + "/" + endPoint;
	}

}
