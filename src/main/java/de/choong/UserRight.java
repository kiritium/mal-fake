package de.choong;

/**
 * UserRights for the User Management. The visibility of pages or components are
 * set according to the "User-Right-Level" (first Element in Enum is 0, second
 * 1, ...).
 *
 */
public enum UserRight {

	ADMIN, // User Management
	MODERATOR, // can Add Animes
	USER, // Profile, Watched List

}
