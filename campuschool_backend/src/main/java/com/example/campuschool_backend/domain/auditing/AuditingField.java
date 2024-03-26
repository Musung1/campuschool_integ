package com.example.campuschool_backend.domain.auditing;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditingField {
    @CreatedDate
    LocalDateTime createdAt;
    @LastModifiedDate
    LocalDateTime modifiedAt;
    @CreatedBy
    Long createdBy;
}