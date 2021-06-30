package com.nosorio.xmentor.models;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Data
@Node
@Getter
public class User {
    @Id
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @Relationship(type = "INTERESTED", direction = Relationship.Direction.OUTGOING)
    private List<Topic> interests;

    @Relationship(type = "STUDYING", direction = Relationship.Direction.OUTGOING)
    private List<Course> courses;
}
