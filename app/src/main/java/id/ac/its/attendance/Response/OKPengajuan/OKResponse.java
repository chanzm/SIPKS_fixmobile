package id.ac.its.attendance.Response.OKPengajuan;

import com.google.gson.annotations.SerializedName;

public class OKResponse{

	@SerializedName("status")
	private String status;

	public String getStatus(){
		return status;
	}
}