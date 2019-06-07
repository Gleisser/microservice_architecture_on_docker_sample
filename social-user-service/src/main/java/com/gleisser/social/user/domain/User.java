package com.gleisser.social.user.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

/**
 * A User object.
 */
@Document(collection = "user")
@Data
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    private String id;

    @NotNull
    @Field("username")
    private String username;

    @NotNull
    @Field("firstname")
    private String firstname;

    @NotNull
    @Field("lastname")
    private String lastname;

    @NotNull
    @Field("birthday")
    private String birthday;

    @NotNull
    @Field("password")
    private String password;

    @NotNull
    @Field("gender")
    private String gender;

	
}
