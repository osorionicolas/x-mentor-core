package com.nosorio.xmentor.models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.nosorio.xmentor.models.converters.TopicConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.neo4j.core.schema.CompositeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Node
@Table(name = "course")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Course implements Serializable {

    @Id
    @org.springframework.data.neo4j.core.schema.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private UUID uuid;

    private String title;

    @Column(length = 8000)
    private String description;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    @CompositeProperty
    private Map<String, String> content;

    private String preview;

    @Convert(converter = TopicConverter.class)
    @Relationship(type = "HAS", direction = Relationship.Direction.INCOMING)
    @JsonUnwrapped
    private Topic topic;

    private int rating;

}
