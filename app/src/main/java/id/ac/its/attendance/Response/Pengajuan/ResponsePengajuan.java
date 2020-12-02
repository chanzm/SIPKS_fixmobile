

package id.ac.its.attendance.Response.Pengajuan;

        import java.util.List;
        import com.google.gson.annotations.SerializedName;

public class ResponsePengajuan{

    @SerializedName("pengajuan")
    private List<Pengajuan> pengajuan;

    public List<Pengajuan> getPengajuan(){
        return pengajuan;
    }
}
