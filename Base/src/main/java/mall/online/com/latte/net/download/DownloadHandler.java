package mall.online.com.latte.net.download;

import android.os.AsyncTask;

import java.util.WeakHashMap;

import mall.online.com.latte.net.RestCreator;
import mall.online.com.latte.net.callback.IError;
import mall.online.com.latte.net.callback.IFailure;
import mall.online.com.latte.net.callback.IRequest;
import mall.online.com.latte.net.callback.ISuccess;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liWensheng on 2018/1/25.
 */

public class DownloadHandler {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String DOWNLOAD_DIR;
    private final String EXTENSIONS;
    private final String NAME;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public DownloadHandler(String url, IRequest request,
                           String downloadDir, String extensions,
                           String name, ISuccess success,
                           IFailure failure, IError error) {
        this.URL = url;
        this.REQUEST = request;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSIONS = extensions;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    public final void handleDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    final ResponseBody responseBody = response.body();

                    final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                            DOWNLOAD_DIR, EXTENSIONS,responseBody, NAME);

                    if (task.isCancelled()) {
                        if (REQUEST != null) {
                            REQUEST.onRequestEnd();
                        }
                    }
                } else {
                    if (ERROR != null) {
                        ERROR.onError(response.code(), response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (FAILURE != null) {
                    FAILURE.onFailure();
                }
            }
        });
    }
}
