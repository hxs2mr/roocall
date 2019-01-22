package com.gykj.rollcall.ui.analyse.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.gykj.mvvmlibrary.base.BaseFragment;
import com.gykj.mvvmlibrary.utils.KLog;
import com.gykj.rollcall.BR;
import com.gykj.rollcall.R;
import com.gykj.rollcall.databinding.FragmentEndBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * desc   : 已结束Fragment
 * author : josh.lu
 * e-mail : 1113799552@qq.com
 * date   : 2019/1/229:31
 * version: 1.0
 */
public class EndFragment extends BaseFragment<FragmentEndBinding,EndViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_end;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {

        final List<String> dateList = new ArrayList<>();
        dateList.add("2019-1-1");
        dateList.add("2019-1-2");
        dateList.add("2019-1-3");
        dateList.add("2019-1-4");
        dateList.add("2019-1-5");
        dateList.add("2019-1-6");

        Typeface tfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
        BarChart chart = binding.endTotalChart;
        chart.getDescription().setEnabled(false);

//        chart.setDrawBorders(true);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(true);

        chart.setDrawBarShadow(false);

        chart.setDrawGridBackground(false);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setTypeface(tfLight);
        l.setYOffset(0f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        XAxis xAxis = chart.getXAxis();
        xAxis.setTypeface(tfLight);
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new LargeValueFormatter(){
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                KLog.d("yxxs","value="+value);
                if(value < 0 || value >=dateList.size()){
                    return "";
                }else {
                    return dateList.get((int) value);
                }
            }
        });

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(tfLight);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        chart.getAxisRight().setEnabled(false);

        ArrayList<BarEntry> values1 = new ArrayList<>();
        ArrayList<BarEntry> values2 = new ArrayList<>();

        for (int i = 0; i < dateList.size(); i++) {
            values1.add(new BarEntry(i, (float) (Math.random() * 1000)));
            values2.add(new BarEntry(i, (float) (Math.random() * 1000)));
        }

        BarDataSet set1, set2;

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {

            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) chart.getData().getDataSetByIndex(1);
            set1.setValues(values1);
            set2.setValues(values2);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();

        } else {
            set1 = new BarDataSet(values1, "已到");
            set1.setColor(Color.rgb(82, 152, 252));
            set2 = new BarDataSet(values2, "未到");
            set2.setColor(Color.rgb(46, 244, 197));

            BarData data = new BarData(set1, set2);
            data.setValueFormatter(new LargeValueFormatter());
            data.setValueTypeface(tfLight);

            chart.setData(data);
        }

        float groupSpace = 0.08f;
        float barSpace = 0.16f; // x4 DataSet
        float barWidth = 0.3f; // x4 DataSet
        // specify the width each bar should have
        chart.getBarData().setBarWidth(barWidth);

        // restrict the x-axis range
        chart.getXAxis().setAxisMinimum(0);
        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * dateList.size());
        chart.groupBars(0, groupSpace, barSpace);

        chart.invalidate();
    }


}
