package com.jhossi.server.model;

import com.jhossi.server.enumeration.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serverId;

    @NotBlank(message = "ServerName cannot be empty or null")
    private  String serverName;

    @Column(unique = true)
    @NotEmpty(message = "Ip Address cannot be empty or null")
    private  String serverIpAddress;
    private  String serverMemory;
    private  String serverType;
    private  String serverImageUrl;


    private Status status;


}
