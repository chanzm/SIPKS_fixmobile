package id.ac.its.attendance.Response.Profile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.ac.its.attendance.Response.Pengajuan.Pengajuan;

public class ProfileResponse{
	@SerializedName("profile")
	private List<Profile> profile;

	public List<Profile> getProfile(){
		return profile;
	}
}