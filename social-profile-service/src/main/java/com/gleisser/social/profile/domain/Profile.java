package com.gleisser.social.profile.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

/**
 * A Profile object.
 */
@Document(collection = "profile")
@Data
public class Profile {
	
	@Id
	private String id;

	@Field("user_id")
	private String userId;

	@Field("profile_id")
	private String profileId;

	@Field("hobbies_id")
	private String hobbiesId;

	@Field("education_id")
	private String educationId;

	@Field("name")
	private String name;

	@Field("surname")
	private String surname;

	@Field("city")
	private String city;

	@Field("state")
	private String state;

	@Field("profile_photo_url")
	private String profilePhotoUrl;

	@Field("banner_photo_url")
	private String bannerPhotoUrl;
}
