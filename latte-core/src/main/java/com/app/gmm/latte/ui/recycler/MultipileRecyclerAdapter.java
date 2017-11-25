package com.app.gmm.latte.ui.recycler;

import android.support.v7.widget.GridLayoutManager;

import com.app.gmm.latte.R;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * Created by gmm on 2017/11/14.
 */

public class MultipileRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
    implements BaseQuickAdapter.SpanSizeLookup{

    protected MultipileRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    private void init() {
        // 添加不同的布局
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
    }

    public static MultipileRecyclerAdapter create(List<MultipleItemEntity> data) {
        return new MultipileRecyclerAdapter(data);
    }

    public static MultipileRecyclerAdapter create(DataConverter converter) {
        return new MultipileRecyclerAdapter(converter.convert());
    }

    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {



    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return 0;
    }
}
