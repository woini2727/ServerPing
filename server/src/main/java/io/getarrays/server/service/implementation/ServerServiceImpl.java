package io.getarrays.server.service.implementation;

import io.getarrays.server.enums.Status;
import io.getarrays.server.model.Server;
import io.getarrays.server.repositories.ServerRepository;
import io.getarrays.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {
    private final ServerRepository serverRepository;

    @Override
    public Server create(Server server) {
        log.info("Saving new Server: {}",server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    @Override
    public Server ping(String ipAdress) {
        log.info("Pinging Server IP: {}",ipAdress);
        Server server = serverRepository.findServerByIpAdress(ipAdress);
        InetAddress address = null;
        try {
            address = InetAddress.getByName(ipAdress);
            server.setStatus(address.isReachable(10000)? Status.SERVER_UP: Status.SERVER_DOWN);
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverRepository.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all Servers");
        return serverRepository.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching Server by Id: {}",id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating Server: {}",server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting Server bi Id: {}",id);
        serverRepository.deleteById(id);
        return Boolean.TRUE;
    }

    private String setServerImageUrl() {
        String[] imageNames = {"server1.png","server2.png","server3.png","server4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/server/image/"+imageNames[new Random().nextInt(4)]).toUriString();
    }
}
