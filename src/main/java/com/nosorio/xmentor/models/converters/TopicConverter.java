package com.nosorio.xmentor.models.converters;

import com.nosorio.xmentor.models.Topic;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TopicConverter implements AttributeConverter<Topic, String> {

    @Override
    public String convertToDatabaseColumn(Topic topic) {
        if (topic == null) {
            return null;
        }

        return topic.getName();
    }

    @Override
    public Topic convertToEntityAttribute(String dbtopic) {
        if (dbtopic == null || dbtopic.isEmpty()) {
            return null;
        }

        Topic topic = new Topic();
        topic.setName(dbtopic);

        return topic;
    }
}