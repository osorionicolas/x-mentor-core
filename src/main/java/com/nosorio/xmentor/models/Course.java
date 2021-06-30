package com.nosorio.xmentor.models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.nosorio.xmentor.models.converters.TopicConverter;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Node
@Table(name = "course")
public class Course {

    @Id
    @org.springframework.data.neo4j.core.schema.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private UUID uuid;

    private String title;

    @Column(length = 8000)
    private String description;

    private String content;

    private String preview;

    @Convert(converter = TopicConverter.class)
    @Relationship(type = "HAS", direction = Relationship.Direction.INCOMING)
    @JsonUnwrapped
    private Topic topic;

    private int rating;

}
