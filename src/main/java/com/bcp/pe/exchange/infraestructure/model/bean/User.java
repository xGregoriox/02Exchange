package com.bcp.pe.exchange.infraestructure.model.bean;

import lombok.Data;

@Data
public class User {
    private String userName;
    private String userPassword;
    private String token;
}
