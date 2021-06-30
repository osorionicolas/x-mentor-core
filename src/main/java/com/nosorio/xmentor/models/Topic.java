package com.nosorio.xmentor.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Id;

@Data
@Node
public class Topic {
    @Id
    @JsonAlias("topic")
    private String name;

    @JsonCreator
    public static Topic fromJson(String value){
        Topic topic = new Topic();
        topic.setName(value);
        return topic;
    }

}
