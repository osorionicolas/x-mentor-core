package com.nosorio.xmentor.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@Node
@JsonPropertyOrder({ "email" })
public class User {
    @Id
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
