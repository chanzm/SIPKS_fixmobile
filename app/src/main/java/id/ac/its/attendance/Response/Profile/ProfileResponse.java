package id.ac.its.attendance.Response.Profile;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse{

	@SerializedName("profile")
	private Profile profile;

	public Profile getProfile(){
		return profile;
	}
}