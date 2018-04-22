package mall.online.com.latte.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by liWensheng on 2018/4/2.
 */

public interface GetRequest {
    @GET("sort_content")
    Call<ResponseBody> getThingByType(@Query("sortId") int id);
}
