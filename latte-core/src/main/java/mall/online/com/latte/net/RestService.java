package mall.online.com.latte.net;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by liWensheng on 2018/1/23.
 * 网络框架接口
 */

public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap WeakHashMap<String, Object> parms);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap WeakHashMap<String, Object> parms);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap WeakHashMap<String, Object> parms);

    @DELETE
    Call<String> delete(@Url String url, @QueryMap WeakHashMap<String, Object> parms);

    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap WeakHashMap<String, Object> parms);

    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part parms);
}
