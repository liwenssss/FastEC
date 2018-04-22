package mall.online.com.latte.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.File;
import java.io.InputStream;

import mall.online.com.latte.app.Latte;
import mall.online.com.latte.net.callback.IRequest;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.utils.file.FileUtil;
import okhttp3.ResponseBody;

/**
 * Created by liWensheng on 2018/1/25.
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest request, ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... params) {

        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];
        final InputStream is = body.byteStream();

        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }

        if (extension == null || extension.equals("")) {
            extension = "";
        }

        if (name == null) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(),extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);

        if (SUCCESS != null) {
            SUCCESS.onSuceess(file.getPath());
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        autoInstallaPK(file);


    }

    private void autoInstallaPK(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }


    }
}
