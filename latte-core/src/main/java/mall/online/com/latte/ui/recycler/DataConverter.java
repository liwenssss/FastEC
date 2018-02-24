package mall.online.com.latte.ui.recycler;

import java.util.ArrayList;

/**
 * Created by liWensheng on 2018/2/23.
 */

public abstract class DataConverter {
    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData (String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("Data is Null!");
        }
        return mJsonData;
    }
}
