package com.web.model;

import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employee_table_insertion")
@EntityListeners(AuditingEntityListener.class)
public class Employee {

    @Id  
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="salary")
    private double salary;
    @Column(name="addr")
    private String addr;
    
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;    
    
    @PreUpdate
    public void updateModifiedDate() {
        this.modifiedDate = LocalDateTime.now();
    }
}
