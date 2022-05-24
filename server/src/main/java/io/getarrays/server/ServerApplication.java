package io.getarrays.server;

import io.getarrays.server.enums.Status;
import io.getarrays.server.model.Server;
import io.getarrays.server.repositories.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	@Bean
	CommandLineRunner run(ServerRepository serverRepository){
		return args -> {
			serverRepository.save(new Server(null,"192.168.1.160","Ubuntu live","16GB","pc",
					"http://localhost:8000/server/image/server1.png", Status.SERVER_UP));
			serverRepository.save(new Server(null,"192.168.1.150","Ubuntu live 2","32GB","pc",
					"http://localhost:8000/server/image/server2.png", Status.SERVER_UP));
			serverRepository.save(new Server(null,"192.168.1.130","Ubuntu live 3","64GB","pc",
					"http://localhost:8000/server/image/server3.png", Status.SERVER_UP));
			serverRepository.save(new Server(null,"192.168.1.120","Ubuntu live 4","128GB","pc",
					"http://localhost:8000/server/image/server4.png", Status.SERVER_UP));
		};
	}

}
