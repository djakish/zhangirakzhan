package com.example.zhangirakzhan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "ADDRESSES")
public class  Address {
    @Column(name = "ID")
    @Id
    private Long id;

    @NotNull
    @NotEmpty
    private String country;

    @NotNull
    @NotEmpty
    private String street;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = true)
    private User user;
}
