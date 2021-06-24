package com.nosorio.xmentor.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Id;

@Data
@Node
public class Topic {
    @Id
    @JsonProperty(value = "topic")
    private String name;
}
