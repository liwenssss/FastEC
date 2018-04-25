package mall.online.com.latte.ec.main.index.message;

import android.annotation.SuppressLint;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import freemarker.template.utility.StringUtil;
import mall.online.com.latte.ec.R;
import mall.online.com.latte.ec.main.index.IndexRecyclerAdapter;
import mall.online.com.latte.ui.recycler.MultipleFields;
import mall.online.com.latte.ui.recycler.MultipleItemEntity;
import mall.online.com.latte.ui.recycler.MultipleViewHolder;
import mall.online.com.latte.utils.log.LogUtil;

/**
 * Created by liWensheng on 2018/4/25.
 */

public class MessageAdapter extends IndexRecyclerAdapter {
    protected MessageAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(MessageItemType.MESITEM, R.layout.item_message);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case MessageItemType.MESITEM:
                TextView ORDERID = holder.getView(R.id.mes_order_id);
                TextView TIME = holder.getView(R.id.mes_time);

                final String orderid = entity.getField(MultipleFields.OBJECTID);
                final String time = entity.getField(MultipleFields.TIME);

                ORDERID.setText(orderid);
                TIME.setText(time);
                break;
            default:
                break;
        }

    }
}
