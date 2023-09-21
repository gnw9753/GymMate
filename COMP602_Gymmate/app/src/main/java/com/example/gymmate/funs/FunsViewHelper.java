package com.example.gymmate.funs;


import static android.content.Context.MODE_PRIVATE;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gymmate.R;
import com.example.gymmate.data.DataBaseHelper;
import com.example.gymmate.data.ExerciseModel;
import com.example.gymmate.data.UserModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FunsViewHelper {

    public void init(LineChart mLineChar) {
        mLineChar.setDrawGridBackground(false);
        //设置描述文本
        mLineChar.getDescription().setEnabled(false);
        //设置支持触控手势
        mLineChar.setTouchEnabled(true);
        mLineChar.getXAxis().setDrawGridLines(false);// 是否绘制网格线，默认true
        mLineChar.getAxisRight().setEnabled(false);// 不绘制右侧的轴线
        mLineChar.getXAxis().setAvoidFirstLastClipping(false);
        mLineChar.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//去掉上面x
        // mLineChar.setBorderWidth(3f); //设置 chart 边界线的宽度，单位 dp。
        // //设置是否可以拖拽
        mLineChar.setDragEnabled(true);
        //是否缩放
        mLineChar.setScaleEnabled(false);
        //如果禁用,扩展可以在x轴和y轴分别完成
        mLineChar.setPinchZoom(true);
        mLineChar.setNoDataText("暂无数据");   // 没有数据时样式

        //这个三个属性是设置LineChar间距的，如果不设置 数据多的时候X轴标签文字会显示不全 ，切记 切记 切记！！！
        mLineChar.setExtraBottomOffset(20f);
        mLineChar.setExtraRightOffset(30f);
        mLineChar.setExtraLeftOffset(10f);//间距

        // mLineChar.setVisibleXRangeMaximum(4);
        Transformer trans = mLineChar.getTransformer(YAxis.AxisDependency.LEFT);
        mLineChar.setXAxisRenderer(new CustomXAxisRenderer(mLineChar.getViewPortHandler(),
                mLineChar.getXAxis(), trans));
        //自定义X轴标签位置

        // mLineChar.setMaxVisibleValueCount(0);  // 数据点上显示的标签，最大数量，默认100。
        // *************************轴****************************** //
        // 由四个元素组成：
        // 标签：即刻度值。也可以自定义，比如时间，距离等等，下面会说一下；
        // 轴线：坐标轴；
        // 网格线：垂直于轴线对应每个值画的轴线；
        // 限制线：最值等线。
        XAxis xAxis = mLineChar.getXAxis();    // 获取X轴
        YAxis yAxis = mLineChar.getAxisLeft(); // 获取Y轴,mLineChart.getAxis(YAxis.AxisDependency.LEFT);也可以获取Y轴

        //x坐标轴设置  下面几个属性很重要
        xAxis.setGranularity(1f);//设置最小间隔，防止当放大时，出现重复标签。
        xAxis.setLabelCount(5);//设置x轴显示的标签个数
        xAxis.setAxisLineWidth(1f);//设置x轴宽度, ...其他样式、
        // xAxis.setTextSize(20f);设置X轴字体大小
        xAxis.setCenterAxisLabels(false);//x轴居中显示
        //  xAxis.setDrawAxisLine(true);
        //y轴设置
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);//y轴标签绘制的位置
        yAxis.setDrawGridLines(false);//不绘制y轴格网线
        xAxis.setAxisMaximum(5); // 此轴能显示的最大值；
        xAxis.resetAxisMaximum();   // 撤销最大值；
        xAxis.setAxisMinimum(1);    // 此轴显示的最小值；
        xAxis.resetAxisMinimum();   // 撤销最小值；
        yAxis.setSpaceTop(10);   // 设置最大值到图标顶部的距离占所有数据范围的比例。默认10，y轴独有
        // 上面的右图是以下代码设置后的效果图
        yAxis.setStartAtZero(false);

        //  // 算法：比例 = （y轴最大值 - 数据最大值）/ (数据最大值 - 数据最小值) ；
        // 用途：可以通过设置该比例，使线最大最小值不会触碰到图标的边界。
        // 注意：设置一条线可能不太好看，mLineChart.getAxisRight().setSpaceTop(34)也设置比较好;同时，如果设置最小值，最大值，会影响该效果
        //  yAxis.setSpaceBottom(10);   // 同上，只不过是最小值距离底部比例。默认10，y轴独有
        // yAxis.setShowOnlyMinMax(true);   // 没找到。。。，true, 轴上只显示最大最小标签忽略指定的数量（setLabelCount，如果forced = false).
        //yAxis.setLabelCount(4, false); // 纵轴上标签显示的数量,数字不固定。如果force = true,将会画出明确数量，但是可能值导致不均匀，默认（6，false）
        //  yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);  // 标签绘制位置。默认再坐标轴外面
        // xAxis.setGranularity(1); // 设置X轴值之间最小距离。正常放大到一定地步，标签变为小数值，到一定地步，相邻标签都是一样的。这里是指定相邻标签间最小差，防止重复。
        //yAxis.setGranularity(1);    // 同上
        yAxis.setGranularityEnabled(false); // 是否禁用上面颗粒度限制。默认false
        // 轴颜色
        yAxis.setTypeface(null);    // 标签字体

        //yAxis.removeLimitLine(ll);  // 移除指定的限制线,还有其他的几个移除方法
        yAxis.setDrawLimitLinesBehindData(false); // 限制线在数据之后绘制。默认为false
        // X轴更多属性
        //  xAxis.setLabelRotationAngle(45);   // 标签倾斜
        // xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  // X轴绘制位置，默认是顶部

        // Y轴更多属性
        //set1.setAxisDependency(YAxis.AxisDependency.LEFT);  // 设置dataSet应绘制在Y轴的左轴还是右轴，默认LEFT
        yAxis.setDrawZeroLine(false);    // 绘制值为0的轴，默认false,其实比较有用的就是在柱形图，当有负数时，显示在0轴以下，其他的图这个可能会看到一些奇葩的效果
        yAxis.setZeroLineWidth(10);  // 0轴宽度
        yAxis.setZeroLineColor(Color.BLUE);   // 0轴颜色

        //图例设置
        Legend legend = mLineChar.getLegend();
        legend.setEnabled(false);//隐藏图列
        //legend.setWordWrapEnabled(true);//标签是否换行
        ArrayList<Entry> values = new ArrayList<Entry>();
        List<LineChartBean> mDataList = new ArrayList<>();
        mDataList.add(new LineChartBean("0901", 10));
        mDataList.add(new LineChartBean("0902", 11));
        mDataList.add(new LineChartBean("0903", 8));
        List<String> mTimeList = new ArrayList<>();
        {
            yAxis.setAxisMinValue(0);
            yAxis.setAxisMaxValue(20);
            if (mDataList != null && mDataList.size() > 0) {
                for (int i = 0; i < mDataList.size(); i++) {
                    values.add(new Entry(i, mDataList.get(i).getValue()));
                    mTimeList.add(mDataList.get(i).getDate());
                }
            }

            LimitLine lim2 = new LimitLine(10, "200ca"); // 创建限制线, 这个线还有一些相关的绘制属性方法，自行看一下就行，没多少东西。
            yAxis.addLimitLine(lim2);

        }

        //设置一页最大显示个数为6，超出部分就滑动
        float ratio = (float) values.size() / (float) 5;
        //显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
        mLineChar.zoom(ratio, 0f, 0, 0);
        if (mTimeList.size() > 0) {
            xAxis.setValueFormatter(new StringAxisValueFormatter(mTimeList));
        }

        //xAxis.setValueFormatter(new MyXFormatter(mDataTime));
        //
        //设置数据
        if (values.size() > 0) {
            setData(values, mLineChar);
        }
        //默认动画
        // mLineChar.animateX(2500);
        //刷新
        mLineChar.invalidate();
        //传递数据集
    }

    private void setData(ArrayList<Entry> values, LineChart mLineChar) {
        LineDataSet dataSets;
        if (mLineChar.getData() != null && mLineChar.getData().getDataSetCount() > 0) {
            dataSets = (LineDataSet) mLineChar.getData().getDataSetByIndex(0);
            dataSets.setValues(values);
            mLineChar.getData().notifyDataChanged();
            mLineChar.notifyDataSetChanged();
        } else {
            // 创建一个数据集,并给它一个类型
            dataSets = new LineDataSet(values, "asd");
            // 在这里设置线
            //  set1.enableDashedLine(10f, 5f, 0f);  虚线
            //  set1.enableDashedHighlightLine(10f, 5f, 0f);
            dataSets.setColor(Color.parseColor("#0AA4EC"));
            dataSets.setCircleColor(Color.parseColor("#0AA4EC"));
            dataSets.setLineWidth(1f);
            dataSets.setCircleRadius(3f);
            dataSets.setDrawCircleHole(false);
            //  set1.setValueTextSize(9f);
            dataSets.setDrawFilled(false);//折线图背景
            //   set1.setFormLineWidth(1f);
            // set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            // set1.setFormSize(15.f);
            if (Utils.getSDKInt() >= 18) {
                // 填充背景只支持18以上
                //Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_launcher);
                //set1.setFillDrawable(drawable);
                //set1.setFillColor(Color.YELLOW);
            } else {
                //  set1.setFillColor(Color.BLACK);
            }
            ArrayList<ILineDataSet> mDataSets = new ArrayList<ILineDataSet>();
            //添加数据集
            mDataSets.add(dataSets);

            //创建一个数据集的数据对象
            LineData data = new LineData(dataSets);
            // data.setDrawValues(false);
            //谁知数据
            mLineChar.setData(data);
        }
    }


    public void setUser(Context context) {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(context,
                androidx.appcompat.R.style.Base_Theme_AppCompat_Light_Dialog_Alert);
        RelativeLayout layout = (RelativeLayout) LayoutInflater.from(context)
                .inflate(R.layout.layout_set_user, null);


        String email=context.getSharedPreferences("user_prefs", MODE_PRIVATE).getString("userEmail","test@test.com");
        UserModel user = new DataBaseHelper(context).getUserByEmail(email);

        EditText editName = layout.findViewById(R.id.edit_name);
        EditText editEmail = layout.findViewById(R.id.edit_email);
        EditText editWeight = layout.findViewById(R.id.edit_weight);
        EditText editHeight = layout.findViewById(R.id.edit_height);

        editName.setText(user.getName());
        editEmail.setText(user.getEmail());
        editEmail.setEnabled(false);
        editWeight.setText(user.getWeight()+"");
        editHeight.setText(user.getHeight()+"");

        aBuilder.setView(layout);
        AlertDialog dialog = aBuilder.create();
        dialog.setCancelable(true);
        dialog.show();

        Button btnConfirm = layout.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DataBaseHelper(context).updateUserWeight(email,Integer.parseInt(editWeight.getText().toString()));
                dialog.hide();
            }
        });
    }

    public void setAlarm(Context context) {

        AlertDialog.Builder aBuilder = new AlertDialog.Builder(context,
                androidx.appcompat.R.style.Base_Theme_AppCompat_Light_Dialog_Alert);
        LinearLayout layout = (LinearLayout) LayoutInflater.from(context)
                .inflate(R.layout.layout_set_alarm, null);


        TimePicker timePicker = layout.findViewById(R.id.date_picker);
        Spinner spinerWorkout = layout.findViewById(R.id.spiner_workout);

        List<String> list1 = new ArrayList<String>();

        list1.add("yoga");
        list1.add("Pilates");
        list1.add("Push-up");
        list1.add("Sit-up");
        list1.add("Aerobics");
        list1.add("Rope skipping");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerWorkout.setAdapter(adapter1);


        aBuilder.setView(layout);
        AlertDialog dialog = aBuilder.create();
        dialog.setCancelable(true);
        dialog.show();

        Button btnConfirm = layout.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.hide();
                Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
                alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE, spinerWorkout.getSelectedItem().toString());
                alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, timePicker.getHour());
                alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, timePicker.getMinute());
                alarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI, false);
                alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(alarmIntent);
            }
        });
    }

    public void setWorkout(Context context)
    {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(context,
                androidx.appcompat.R.style.Base_Theme_AppCompat_Light_Dialog_Alert);
        LinearLayout layout = (LinearLayout) LayoutInflater.from(context)
                .inflate(R.layout.layout_change_workout, null);
        Spinner spinner1=layout.findViewById(R.id.spinner_date);
        List<String> list1 = new ArrayList<String>();
        list1.add("Monday");
        list1.add("Tuesday");
        list1.add("Wednesday");
        list1.add("ThursDay");
        list1.add("Friday");
        list1.add("Saturday");
        list1.add("Sunday");
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,list1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        List<String> list2 = new ArrayList<String>();
        list2.add("yoga");
        list2.add("Pilates");
        list2.add("Push-up");
        list2.add("Sit-up");
        list2.add("Aerobics");
        list2.add("Rope skipping");

        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,list2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner2=layout.findViewById(R.id.spinner_workout);
        spinner2.setAdapter(adapter2);




        aBuilder.setView(layout);
        AlertDialog dialog = aBuilder.create();
        dialog.setCancelable(true);
        dialog.show();

        String email=context.getSharedPreferences("user_prefs", MODE_PRIVATE).getString("userEmail","test@test.com");
        Button btnConfirm=layout.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date=spinner1.getSelectedItem().toString();
                String item=spinner2.getSelectedItem().toString();
                ExerciseModel model=new ExerciseModel(email,date,"",item,"","","","","");
                new DataBaseHelper(context).addOne(model);
                dialog.hide();
            }
        });

    }

    public void setDownload(Context context)
    {
        FileOutputStream fos = null;
        String msg = null;
        try { // 在操作文件的时候可能会报异常，需要进行捕获
            fos = context.openFileOutput("MyData.txt", Context.MODE_PRIVATE);
            String email=context.getSharedPreferences("user_prefs", MODE_PRIVATE).getString("userEmail","test@test.com");
            List<ExerciseModel> list=new DataBaseHelper(context).getExerciseListByEmail(email);
            StringBuffer sb=new StringBuffer();
            for(ExerciseModel item:list)
            {
                sb.append(item.getDay());
                sb.append("\n");
                sb.append(item.getName());
            }
            // getBytes()将字符串转换为字节流
            fos.write(sb.toString().getBytes());
            Toast.makeText(context,"Workout data has been wrote to MyData.txt",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fos.close(); // 流是系统中的稀缺资源，在使用完后要及时关闭
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


class CustomXAxisRenderer extends XAxisRenderer {
    public CustomXAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer trans) {
        super(viewPortHandler, xAxis, trans);
    }

    @Override
    protected void drawLabel(Canvas c, String formattedLabel, float x, float y, MPPointF anchor, float angleDegrees) {
        float labelHeight = mXAxis.getTextSize();
        float labelInterval = 5f;
        String[] labels = formattedLabel.split(" ");

        Paint mFirstLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFirstLinePaint.setColor(0xFF9b9b9b);
        mFirstLinePaint.setTextAlign(Paint.Align.LEFT);
        mFirstLinePaint.setTextSize(Utils.convertDpToPixel(10f));
        mFirstLinePaint.setTypeface(mXAxis.getTypeface());

        Paint mSecondLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondLinePaint.setColor(0xFF9b9b9b);
        mSecondLinePaint.setTextAlign(Paint.Align.LEFT);
        mSecondLinePaint.setTextSize(Utils.convertDpToPixel(10f));
        mSecondLinePaint.setTypeface(mXAxis.getTypeface());

        if (labels.length > 1) {
            Utils.drawXAxisValue(c, labels[0], x, y, mFirstLinePaint, anchor, angleDegrees);
            Utils.drawXAxisValue(c, labels[1], x, y + labelHeight + labelInterval, mSecondLinePaint, anchor, angleDegrees);
        } else {
            Utils.drawXAxisValue(c, formattedLabel, x, y, mFirstLinePaint, anchor, angleDegrees);
        }

    }


}

class StringAxisValueFormatter extends ValueFormatter {

    private List<String> mTimeList;

    public StringAxisValueFormatter(List<String> mTimeList) {
        this.mTimeList = mTimeList;
    }


    @Override
    public String getFormattedValue(float v, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        if (v < 0 || v > (mTimeList.size() - 1)) {//使得两侧柱子完全显示
            return "";
        }
        return mTimeList.get((int) v);
    }
}

