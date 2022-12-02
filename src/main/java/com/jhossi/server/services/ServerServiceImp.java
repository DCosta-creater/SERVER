package com.jhossi.server.services;

import com.jhossi.server.enumeration.Status;
import com.jhossi.server.model.Server;
import com.jhossi.server.repo.ServerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.jhossi.server.enumeration.Status.SERVER_DOWN;
import static com.jhossi.server.enumeration.Status.SERVER_UP;

//Lombok does de dependency injection for us via Constructor
@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImp implements ServerService{


    private ServerRepository serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Saving new server "+ server.getServerName());
        server.setServerImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }



    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server Ip: "+ ipAddress);
        Server server = serverRepo.findByServerIpAddress(ipAddress);

        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000)? SERVER_UP: SERVER_DOWN);

        return  serverRepo.save(server);
    }

    @Override
    public Collection<Server> getAll(int range) {
        log.info("Fetching all Servers: ");
        return serverRepo.findAll(PageRequest.of(0, range)).toList();
    }

    @Override
    public Server get(Long serverId) {
        log.info("Fetching server by Id: " + serverId);


        return serverRepo.findById(serverId).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Saving new server "+ server.getServerName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server "+ id);
        serverRepo.deleteById(id);
        return  Boolean.TRUE;
    }


    private String setServerImageUrl() {
        String[] imageNames = {"server1.png", "server2.png", "server3.png", "server4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/server/image"+imageNames[new Random().nextInt(4)]).toUriString();
    }
}
