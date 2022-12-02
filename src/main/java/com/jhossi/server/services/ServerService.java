package com.jhossi.server.services;

import com.jhossi.server.model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;

public interface ServerService {

    Server create(Server server);

    Server ping(String ipAddress) throws IOException;

    Collection<Server> getAll(int range);

    Server get(Long serverId);

    Server update(Server server);

    Boolean  delete(Long id);

}
