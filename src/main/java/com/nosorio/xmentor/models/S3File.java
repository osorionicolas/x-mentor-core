package com.nosorio.xmentor.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@IdClass(S3File.class)
@Table(name = "s3_files")
@NoArgsConstructor
@AllArgsConstructor
public class S3File implements Serializable {
    @Id
    private String filename;
    @Id
    private String uuid;
}
