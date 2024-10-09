package com.systex.ajaxwork.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "member")
public class MemberModel {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;

    @Transient
    private String confirmPassword;

    public MemberModel() {
    }

    public MemberModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
