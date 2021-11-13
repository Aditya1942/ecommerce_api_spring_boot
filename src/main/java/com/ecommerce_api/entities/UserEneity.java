package com.ecommerce_api.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "Users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@EntityListeners(AuditingEntityListener.class)
@Data
public class UserEneity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    @Schema(description ="enter a unique username ", type = "string", example = "name")
    @Column(name = "username",  unique = true)

    private String username;

    @Column(name = "password")
    @JsonIgnore
    private String password;
    @Column(name = "role")

    private String role;

}
