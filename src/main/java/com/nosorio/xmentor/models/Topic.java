package com.nosorio.xmentor.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Id;

import java.io.Serializable;

@Data
@Node
public class Topic implements Serializable {
    @Id
    @JsonProperty("topic")
    private String name;

    @JsonCreator
    public static Topic fromJson(String value){
        Topic topic = new Topic();
        topic.setName(value);
        return topic;
    }

}
