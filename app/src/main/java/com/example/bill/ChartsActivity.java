package com.example.bill;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.ChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.LineChartView;

public class ChartsActivity extends Activity {

    private LineChartView mChart;
    private Map<String,Integer> table = new TreeMap<>();
    private LineChartData mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_view);
//        mData = new LineChartData();
        mChart = (LineChartView) findViewById(R.id.chart);
        List<CostBean> allDate = (List<CostBean>)getIntent().getSerializableExtra("cost_list");

        generateValues(allDate);
        generateData();
    }

    private void generateData() {
        List<Line> lines = new ArrayList<>();
        List<PointValue> values = new ArrayList<>();

        int indexX = 0;
        for (Integer value : table.values()){
            values.add(new PointValue(indexX,value));
            indexX++;
        }
        
        Line line = new Line(values);
        line.setColor(ChartUtils.COLORS[0]);//线颜色
        line.setShape(ValueShape.CIRCLE);//点形状
        line.setPointColor(ChartUtils.COLORS[1]);
        lines.add(line);
        mData = new LineChartData(lines);
        mData.setLines(lines);
        mChart.setLineChartData(mData);

//        Axis axisX = new Axis();//X轴
//        axisX.setHasTiltedLabels(true);
//        axisX.setTextColor(Color.GRAY);
//        axisX.setValues(mAxisXValues);
//        mData.setAxisXBottom(axisX);
//
//        Axis axisY = new Axis();//Y轴
//        axisY.setName("");
//
    }

    private void generateValues(List<CostBean> allDate) {
        if (allDate != null){
            for (int i = 0;i < allDate.size();i++){
                CostBean costBean = allDate.get(i);
                String costDate = costBean.costDate;
                int costMoney = Integer.parseInt(costBean.costMoney);
                if (!table.containsKey(costDate)){
                    table.put(costDate,costMoney);
                }
                else{
                    int originMoney = table.get(costDate);
                    table.put(costDate,originMoney + costMoney);
                }

            }
        }
    }
}
