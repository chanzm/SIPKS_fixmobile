package id.ac.its.attendance.Response.UploadFoto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UploadWajahResponse {
    @SerializedName("uploadWajah")
    String foto;

    public String uploadWajah() {
        return foto;
    }

}

//public class ProfileResponse{
//    @SerializedName("profile")
//    private List<Profile> profile;
//
//    public List<Profile> getProfile(){
//        return profile;
//    }
//}