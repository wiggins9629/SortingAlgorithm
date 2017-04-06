package com.wiggins.sortingalgorithm;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wiggins.sortingalgorithm.base.BaseActivity;
import com.wiggins.sortingalgorithm.utils.SortingUtil;
import com.wiggins.sortingalgorithm.utils.UIUtils;
import com.wiggins.sortingalgorithm.widget.TitleView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TitleView titleView;
    private TextView tv_data;
    private TextView tv_insert;
    private TextView tv_hill;
    private TextView tv_simple;
    private TextView tv_heap;
    private TextView tv_bubble;
    private TextView tv_quick;
    private TextView tv_merge;
    private TextView tv_base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }

    private void initView() {
        titleView = (TitleView) findViewById(R.id.titleView);
        titleView.setAppTitle(UIUtils.getString(R.string.title));
        titleView.setLeftImageVisibility(View.GONE);
        tv_data = (TextView) findViewById(R.id.tv_data);
        tv_insert = (TextView) findViewById(R.id.tv_insert);
        tv_hill = (TextView) findViewById(R.id.tv_hill);
        tv_simple = (TextView) findViewById(R.id.tv_simple);
        tv_heap = (TextView) findViewById(R.id.tv_heap);
        tv_bubble = (TextView) findViewById(R.id.tv_bubble);
        tv_quick = (TextView) findViewById(R.id.tv_quick);
        tv_merge = (TextView) findViewById(R.id.tv_merge);
        tv_base = (TextView) findViewById(R.id.tv_base);
    }

    private void setListener() {
        tv_insert.setOnClickListener(this);
        tv_hill.setOnClickListener(this);
        tv_simple.setOnClickListener(this);
        tv_heap.setOnClickListener(this);
        tv_bubble.setOnClickListener(this);
        tv_quick.setOnClickListener(this);
        tv_merge.setOnClickListener(this);
        tv_base.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_insert://直接插入排序
                tv_data.setText(SortingUtil.insertSort());
                break;
            case R.id.tv_hill://希尔排序
                tv_data.setText(SortingUtil.shellSort());
                break;
            case R.id.tv_simple://简单选择排序
                tv_data.setText(SortingUtil.selectSort());
                break;
            case R.id.tv_heap://堆排序
                tv_data.setText(SortingUtil.heapSort());
                break;
            case R.id.tv_bubble://冒泡排序
                tv_data.setText(SortingUtil.bubbleSort());
                break;
            case R.id.tv_quick://快速排序
                tv_data.setText(SortingUtil.quickSort());
                break;
            case R.id.tv_merge://归并排序
                tv_data.setText(SortingUtil.mergingSort());
                break;
            case R.id.tv_base://基数排序
                tv_data.setText(SortingUtil.radixSort());
                break;
        }
    }
}
