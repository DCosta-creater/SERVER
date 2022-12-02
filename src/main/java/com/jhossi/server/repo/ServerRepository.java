package com.jhossi.server.repo;

import com.jhossi.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

    public Server findByServerIpAddress(String ipAddress);
    public List<Server> findByStatus(String ipAddress);
}
