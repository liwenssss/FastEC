package mall.online.com.latte.ec.main.sort.content;

/**
 * Created by liWensheng on 2018/2/26.
 */

public class SectionContentItemEntity {
    private int mGoodsId = 0;
    private String mGoodsName = null;
    private String MgoodsThumb = null;


    public int getmGoodsId() {
        return mGoodsId;
    }

    public void setmGoodsId(int mGoodsId) {
        this.mGoodsId = mGoodsId;
    }

    public String getmGoodsName() {
        return mGoodsName;
    }

    public void setmGoodsName(String mGoodsName) {
        this.mGoodsName = mGoodsName;
    }

    public String getMgoodsThumb() {
        return MgoodsThumb;
    }

    public void setMgoodsThumb(String mgoodsThumb) {
        MgoodsThumb = mgoodsThumb;
    }
}
