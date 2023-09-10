package com.kapture.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "client_id")
    private int clientId;
    @Column(name = "emp_id")
    private int empId;
    @Column(name = "skuCode")
    private String skuCode;
    @Column(name = "name")
    private String name;
    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;
    @Column(name = "enabled")
    private int enabled;

    public Product(int clientId, int empId, String name, int enabled) {
        this.clientId = clientId;
        this.empId = empId;
        this.name = name;
        this.enabled = enabled;
    }

    public Product(int id, int clientId, int empId, String name, int enabled) {
        this.id=id;
        this.clientId=clientId;
        this.empId = empId;
        this.name = name;
        this.enabled = enabled;
    }
}
