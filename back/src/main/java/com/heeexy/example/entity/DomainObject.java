package com.heeexy.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Version
    @Column(name = "version", length = 11)
    private int version = 0;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name = "created_datetime")
    protected LocalDateTime createdDateTime = LocalDateTime.now();


    @Column(name = "last_modified_datetime")
    LocalDateTime lastModifiedDateTime;

    @PrePersist
    @PreUpdate
    public void updateLastModifiedDateTime() {
        lastModifiedDateTime = LocalDateTime.now();
    }

    protected DomainObject() {
    }

    public Long getId() {
        return id;
    }


    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
