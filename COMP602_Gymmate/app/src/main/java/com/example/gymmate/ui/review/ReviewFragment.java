package com.example.gymmate.ui.review;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gymmate.R;
import com.example.gymmate.data.FoodItem;
import com.example.gymmate.data.MyStorage;
import com.example.gymmate.data.UserInfo;
import com.example.gymmate.data.WorkoutItem;
import com.example.gymmate.databinding.FragmentNotificationsBinding;
import com.example.gymmate.dialog.DialogManager;
import com.example.gymmate.dialog.DialogView;
import com.example.gymmate.ui.workout.HomeFragment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReviewFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReviewViewModel notificationsViewModel =
                new ViewModelProvider(this).get(ReviewViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textNotifications;
        //notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        LineChart lineChart1=binding.lineChart1;
        LineChart lineChart2=binding.lineChart2;
        initLinartData(lineChart1,1);
        initLinartData(lineChart2,1);

        LinearLayout btnChange=binding.btnChangeWorkout;
        LinearLayout btnDownload=binding.btnDownloadWorkout;
        LinearLayout btnUserinfo=binding.btnUserinfo;

        btnUserinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogView dialogView= DialogManager.getInstance().initView(getContext(), R.layout.layout_userinfo, Gravity.BOTTOM);
                dialogView.setCanceledOnTouchOutside(true);

                EditText txt_Name=dialogView.findViewById(R.id.txt_name);
                EditText txt_weight=dialogView.findViewById(R.id.txt_weight);
                EditText txt_height=dialogView.findViewById(R.id.txt_height);
                EditText txt_goal=dialogView.findViewById(R.id.txt_goal);
                if(MyStorage.userInfo!=null)
                {
                    txt_Name.setText(MyStorage.userInfo.name);
                    txt_weight.setText(String.valueOf(MyStorage.userInfo.weight));
                    txt_height.setText(String.valueOf(MyStorage.userInfo.height));
                    txt_goal.setText(MyStorage.userInfo.goal);
                }
                Button btnConfirm=dialogView.findViewById(R.id.btn_confirm);
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(MyStorage.userInfo==null)
                            MyStorage.userInfo=new UserInfo();
                        MyStorage.userInfo.name=txt_Name.getText().toString();
                        MyStorage.userInfo.height=Float.valueOf(txt_weight.getText().toString());
                        MyStorage.userInfo.weight=Float.valueOf(txt_height.getText().toString());
                        MyStorage.userInfo.goal=txt_goal.getText().toString();

                        DialogManager.getInstance().hide(dialogView);
                    }
                });
                DialogManager.getInstance().show(dialogView);
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogView dialogView= DialogManager.getInstance().initView(getContext(), R.layout.layout_change_workout, Gravity.BOTTOM);
                dialogView.setCanceledOnTouchOutside(true);
                Spinner spinner1=dialogView.findViewById(R.id.spinner_date);
                List<String> list1 = new ArrayList<String>();
                list1.add("Monday");
                list1.add("Tuesday");
                list1.add("Wednesday");
                list1.add("ThursDay");
                list1.add("Friday");
                list1.add("Saturday");
                list1.add("Sunday");
                ArrayAdapter<String> adapter1=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,list1);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter1);

                List<String> list2 = new ArrayList<String>();
                list2.add("Upper");
                list2.add("Downer");
                list2.add("Rest");

                ArrayAdapter<String> adapter2=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,list2);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner spinner2=dialogView.findViewById(R.id.spinner_workout);
                spinner2.setAdapter(adapter2);
                DialogManager.getInstance().show(dialogView);

                Button btnConfirm=dialogView.findViewById(R.id.btn_confirm);
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String date=spinner1.getSelectedItem().toString();
                        String item=spinner2.getSelectedItem().toString();
                        MyStorage.addWorkout(date,item);
                        //((HomeFragment)(getActivity().getSupportFragmentManager().findFragmentById(R.id.navigation_home))).update();
                        DialogManager.getInstance().hide(dialogView);
                    }
                });

            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream fos = null;
                String msg = null;
                try { // 在操作文件的时候可能会报异常，需要进行捕获
                    fos = getContext().openFileOutput("MyData.txt", Context.MODE_PRIVATE);
                    StringBuffer sb=new StringBuffer();
                    for(WorkoutItem item:MyStorage.workoutItemList)
                    {
                        sb.append(item.getDate());
                        sb.append("\n");
                        for(String s:item.getContents())
                        {
                            sb.append(s);
                            sb.append("\n");
                        }
                    }
                    // getBytes()将字符串转换为字节流
                    fos.write(sb.toString().getBytes());
                    Toast.makeText(getContext(),"Workout data has been wrote to MyData.txt",Toast.LENGTH_LONG).show();
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
        });


        return root;
    }

    private void initLinartData(LineChart mLineChar, int type) {
        //后台绘制
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
        List<DataBean> mDataList=DataBean.getData();
        List<String> mTimeList=new ArrayList<>();
        if (type == 1) {//心率
            yAxis.setAxisMinValue(0);
            yAxis.setAxisMaxValue(20);
            // 限制线
            //initECgData(yAxis, 60f, 90f, "心率过速", "心率过缓");
            //设置数据数据
            if (mDataList!=null&&mDataList.size()>0){
                for (int i = 0; i < mDataList.size(); i++) {
                    values.add(new Entry(i, mDataList.get(i).value));
                    mTimeList.add(mDataList.get(i).date);
                }
            }

            LimitLine lim2 = new LimitLine(10, "200ca"); // 创建限制线, 这个线还有一些相关的绘制属性方法，自行看一下就行，没多少东西。
            yAxis.addLimitLine(lim2);

        }

        //设置一页最大显示个数为6，超出部分就滑动
        float ratio = (float) values.size() / (float) 5;
        //显示的时候是按照多大的比率缩放显示,1f表示不放大缩小
        mLineChar.zoom(ratio, 0f, 0, 0);
        if (mTimeList.size()>0){
            xAxis.setValueFormatter(new StringAxisValueFormatter(mTimeList));
        }

        //xAxis.setValueFormatter(new MyXFormatter(mDataTime));
        //
        //设置数据
        if (values.size()>0){
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
            dataSets = new LineDataSet(values, "血压测量");
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



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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

