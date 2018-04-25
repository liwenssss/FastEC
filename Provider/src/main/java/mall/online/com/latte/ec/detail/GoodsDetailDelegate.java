package mall.online.com.latte.ec.detail;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mall.online.com.latte.delegate.LatteDelegate;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.R2;
import mall.online.com.latte.net.RestClient;
import mall.online.com.latte.net.callback.ISuccess;
import mall.online.com.latte.ui.banner.HolderCreator;
import mall.online.com.latte.ui.widget.CircleTextView;
import mall.online.com.latte.utils.log.LogUtil;
import mall.online.com.latte.utils.storage.PreferenceUtils;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by liWensheng on 2018/2/25.
 */

public class GoodsDetailDelegate extends LatteDelegate implements AppBarLayout.OnOffsetChangedListener {

//    @BindView(R2.id.goods_detail_toolbar)
//    Toolbar  mToolbar = null;
    @BindView(R2.id.tab_layout)
    TabLayout mTabLayout = null;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager = null;
    @BindView(R2.id.detail_banner)
    ConvenientBanner<String> mBanner = null;
    @BindView(R2.id.collapsing_toolbar_detail)
    CollapsingToolbarLayout mCollapsingToolbarLayout = null;
    @BindView(R2.id.app_bar_detail)
    AppBarLayout mAppBar = null;

    @BindView(R2.id.icon_favor)
    IconTextView mIconFavor = null;
    @BindView(R2.id.rl_add_shop_cart)
    RelativeLayout mRlAddShopCart = null;


    private static  final String GOODS_ID = "GOODSID";
    private String mGoodsId = "";

    private String addParm = "";

    public static GoodsDetailDelegate create(String goodsId) {
        final Bundle args = new Bundle();
        args.putString(GOODS_ID, goodsId);
        final GoodsDetailDelegate delegate = new GoodsDetailDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mGoodsId = args.getString(GOODS_ID);
//            Toast.makeText(getContext(), mGoodsId, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mCollapsingToolbarLayout.setContentScrimColor(Color.WHITE);
        mAppBar.addOnOffsetChangedListener(this);
        initeData();
        initTabLayout();
    }

    private void initTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setSelectedTabIndicatorColor
                (ContextCompat.getColor(getContext(), R.color.wechat_black));
        mTabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
        mTabLayout.setBackgroundColor(Color.WHITE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initPager(JSONObject data) {
        final PagerAdapter adapter = new TabPagerAdapter(getFragmentManager(), data);
        mViewPager.setAdapter(adapter);
    }

    private void initeData() {
        RestClient.builder()
                .url("http://139.199.5.153:3000/ec/goods?goodsId="+mGoodsId)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        final JSONObject data = JSON.parseObject(response)
                                .getJSONObject("data");
                        initBanner(data);
                        initGoodsInfo(data);
                        initPager(data);
                        addParm ="phone="+
                                PreferenceUtils.getCustomAppProfile("current")+"&"+
                                "title="+getTitle(data)+"&"+
                                "desc="+getDesc(data)+"&"+
                                "goodsId="+getGoodsId(data)+"&"+
                                "count=1"+"&"+
                                "price="+getPrice(data)+"&"+
                                "thumb="+getThumb(data);

                    }
                })
                .build().get();
    }

    private void initGoodsInfo(JSONObject data) {
        final String goodsData = data.toJSONString();
        getSupportDelegate().
                loadRootFragment(R.id.frame_goods_info, GoodsInfoDelegate.create(goodsData));
    }

    private String getTitle(JSONObject data) {
        final String str = data.getString("name");
        return str;
    }

    private String getDesc(JSONObject data) {
        final String str = data.getString("description");
        return str;
    }

    private String getGoodsId(JSONObject data) {
        final String str = data.getString("id");
        LogUtil.i("cart_id", str);
        return str;
    }

    private String getPrice(JSONObject data) {
        final String str = data.getString("price");
        return str;
    }

    private String getThumb(JSONObject data) {
        final String str = data.getString("thumb");
        return str;
    }


    private void initBanner(JSONObject data) {
        final JSONArray array = data.getJSONArray("banners");
        final List<String> images = new ArrayList<>();
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            images.add(array.getString(i));
        }
        mBanner.setPages(new HolderCreator(), images)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }

    @OnClick(R2.id.rl_add_shop_cart)
    public void addToCart(){
        RestClient.builder()
                .url("http://139.199.5.153:3000/ec/cart?"+addParm)
                .success(new ISuccess() {
                    @Override
                    public void onSuceess(String response) {
                        Toast.makeText(getContext(), "已成功加入购物车", Toast.LENGTH_SHORT).show();
                    }
                })
                .build().post();

    }
}
