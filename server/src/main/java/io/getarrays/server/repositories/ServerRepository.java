package io.getarrays.server.repositories;

import io.getarrays.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server,Long> {
    Server findServerByIpAdress(String ipAdress);
}
