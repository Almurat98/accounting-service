package com.cydeo.entity;

import com.cydeo.entity.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Where(clause = "is_deleted=false")
public class User extends BaseEntity {

    @Column(unique = true)
    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String phone;

    private boolean enabled;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}

