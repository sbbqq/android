package com.example.moduleview.developerandroid.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wqq on 18-6-14.
 */

public class CustomCalendar extends ViewGroup {
   private ArrayList<DateShow> buttonstest;
   private ArrayList<TextView> textViewsdayweek;
   LayoutParams layoutParams;
   int width,height;
   int Topmargin=250;
   int Topmargindayweek=100;
   int Leftmargin=200;
   int lineunit=40;
   int linejianju=20;
   int lineHeight=80;
   MyDay myDayToday,otherDay;

    public CustomCalendar(Context context) {
        super(context);
        ini();
    }

    public CustomCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        ini();
    }

    public CustomCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ini();
    }


     public void ini(){
        getCurrentDay();
        testDate();
        layoutParams=new LayoutParams(120,120);
        buttonstest=new ArrayList<>();



         iniDateData(getCurrentDay());
        textViewsdayweek=new ArrayList<>();
         String weekname[]={"周日","周一","周二","周三","周四","周五","周六"};
         for(int i=0;i<7;i++){
             TextView txdayofweek=new TextView(getContext());
             txdayofweek.setText(weekname[i]);
             txdayofweek.setGravity(Gravity.CENTER);
             txdayofweek.setBackgroundColor(Color.parseColor("#80000000"));
             txdayofweek.setLayoutParams(layoutParams);
             this.addView(txdayofweek);

         }
         iniHeadDate(getCurrentDay());
     }

     public void iniHeadDate(MyDay myDay){
         //添加四个按钮和日期
         for(int i=0;i<6;i++) {
             if(i!=1&&i!=4) {
                 CustomButton customButton = new CustomButton(getContext());
                 customButton.setLayoutParams(new LayoutParams(80, 80));
                 customButton.setState(0);
                 customButton.setOnClickListener(new OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Log.e("shit","*************");
                     }
                 });
                 this.addView(customButton);
             }
             else{
                 TextView txdayofweek=new TextView(getContext());
                 if(i==1)
                 txdayofweek.setText(myDay.getY()+"");
                 else
                     txdayofweek.setText((myDay.getM()+1)+"");
                 txdayofweek.setGravity(Gravity.CENTER);
                 txdayofweek.setBackgroundColor(Color.parseColor("#80000000"));
                 txdayofweek.setLayoutParams(new LayoutParams(150,80));
                 this.addView(txdayofweek);
             }

         }

     }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for(int i=0;i<buttonstest.size();i++){
            int top=(i/7)*(lineHeight+linejianju)+Topmargin;
            int left=(i%7)*(getChildAt(i).getMeasuredWidth()+lineunit)+Leftmargin;
            int bottom=top+getChildAt(i).getMeasuredHeight();
            int right=left+getChildAt(i).getMeasuredWidth();
            getChildAt(i).layout(left,top,right,bottom);
        }
        int countorder=0;
        for(int i=buttonstest.size();i<getChildCount();i++){
            View  view=getChildAt(i);
            int top=Topmargindayweek;
            int left=countorder*(lineunit+view.getMeasuredWidth())+Leftmargin;
            int bottom=top+view.getMeasuredHeight();
            int right=left+view.getMeasuredWidth();
            view.layout(left,top,right,bottom);
            countorder++;
        }


          int counter2=0;
        for(int i=buttonstest.size()+7;i<getChildCount();i++){
            View view=getChildAt(i);
            int top=0,left,right,bottom;
            if(counter2==0){
                left=300;
                right=left+view.getMeasuredWidth();
                bottom=top+view.getMeasuredHeight();
                view.layout(left,top,right,bottom);
            } else
            if(counter2==1){
                left=400;
                right=left+view.getMeasuredWidth();
                bottom=top+view.getMeasuredHeight();
                view.layout(left,top,right,bottom);
            }
             else
            if(counter2==2){
                left=600;
                right=left+view.getMeasuredWidth();
                bottom=top+view.getMeasuredHeight();
                view.layout(left,top,right,bottom);
            }
            else
            if(counter2==3){
                left=700;
                right=left+view.getMeasuredWidth();
                bottom=top+view.getMeasuredHeight();
                view.layout(left,top,right,bottom);
            }
            else
            if(counter2==4){
                left=800;
                right=left+view.getMeasuredWidth();
                bottom=top+view.getMeasuredHeight();
                view.layout(left,top,right,bottom);
            }
            else
            if(counter2==5){
                left=950;
                right=left+view.getMeasuredWidth();
                bottom=top+view.getMeasuredHeight();
                view.layout(left,top,right,bottom);
            }
            counter2++;



        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for(int i=0;i<getChildCount();i++){
            measureChild(getChildAt(i),widthMeasureSpec,heightMeasureSpec);
        }
        width=getMeasuredWidth();
        height=getMeasuredHeight();
       // Log.e("width",widthMeasureSpec+"");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void initDateHandle(){


    }
    public class  DateTool{
        /**
         * 获取当月的 天数
         * */
        public  int getCurrentMonthDay() {

            Calendar a = Calendar.getInstance();
            a.set(Calendar.DATE, 1);
            a.roll(Calendar.DATE, -1);
            int maxDate = a.get(Calendar.DATE);
            return maxDate;
        }

        /**
         * 根据年 月 获取对应的月份 天数
         * */
        public  int getDaysByYearMonth(int year, int month) {

            Calendar a = Calendar.getInstance();
            a.set(Calendar.YEAR, year);
            a.set(Calendar.MONTH, month - 1);
            a.set(Calendar.DATE, 1);
            a.roll(Calendar.DATE, -1);
            int maxDate = a.get(Calendar.DATE);
            return maxDate;
        }





        public  int getDaysOfMonth(int Y,int M,int day) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                calendar.setTime(sdf.parse(""+Y+"-"+M+"-"+day));
            } catch (ParseException e) {
                e.printStackTrace();
            };
            return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        public  int getDaysOfWeek(int Y,int M,int day) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                calendar.setTime(sdf.parse(""+Y+"-"+M+"-"+day));
            } catch (ParseException e) {
                e.printStackTrace();
            };
            return calendar.get(Calendar.DAY_OF_WEEK);
        }


        public  MyDay getDayOfMonth(int Y, int M, int day, int days) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Y,M,day);
            calendar.add(Calendar.DATE,days);
            return  new MyDay(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        }



        /**
         * 根据日期 找到对应日期的 星期
         */
        public  String getDayOfWeekByDate(String date) {
            String dayOfweek = "-1";
            try {
                SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date myDate = myFormatter.parse(date);
                SimpleDateFormat formatter = new SimpleDateFormat("E");
                String str = formatter.format(myDate);
                dayOfweek = str;

            } catch (Exception e) {
                System.out.println("错误!");
            }
            return dayOfweek;
        }


    }


    /**
     * 获取今天的日期
     */
    private  MyDay getCurrentDay(){
        Calendar calendar=Calendar.getInstance();
        Log.e("Today","Y:"+calendar.get(Calendar.YEAR)+"M:"+calendar.get(Calendar.MONTH)+"Day:"+calendar.get(Calendar.DAY_OF_MONTH));
        MyDay myDay=new MyDay(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        this.setMyDayToday(myDay);
        return  myDay;
    }


    private void iniDateData(MyDay myDay){

        int daysOfMon=new DateTool().getDaysByYearMonth(myDay.Y,myDay.M+1);
        //获取这月１号是week de第几天，１～７（周天开始第一天）
        int dayofWeek=new DateTool().getDaysOfWeek(myDay.Y,myDay.M+1,1);
        for(int i=dayofWeek-1;i>=1;i--){
            DateShow button=new DateShow(getContext());

            button.setStyle(0,0);
            button.setId(i);
            button.setLayoutParams(layoutParams);
            button.setFocusable(true);;
            MyDay myDay1=new DateTool().getDayOfMonth(myDay.Y,myDay.M,1,-(i));
            button.setMyDay(myDay1);
            button.setContent(button.getMyDay().getD()+"");
            button.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    Log.e("btnid",v.getId()+"");
                    if(hasFocus){
                        //v.setBackgroundColor(Color.RED);
                        ( (DateShow)v).setStateBig(1);
                    }
                    else{
                        //v.setBackgroundColor(Color.GREEN);
                        ( (DateShow)v).setStateBig(0);
                    }
                }
            });
            this.addView(button);
            buttonstest.add(button);

        }
        for(int i=1;i<=daysOfMon;i++){
            DateShow button=new DateShow(getContext());
            button.setContent(i+"");
            if(i==getMyDayToday().getD())
            button.setStateToday(1);
            else
                button.setStateToday(0);
            button.setId(i);
            button.setLayoutParams(layoutParams);
            button.setFocusable(true);;
            MyDay myDay1=new MyDay(myDay.Y,myDay.M,i);
            button.setMyDay(myDay1);
            button.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    Log.e("btnid",v.getId()+"");
                    if(hasFocus){
                        //v.setBackgroundColor(Color.RED);
                        ( (DateShow)v).setStateBig(1);
                    }
                    else{
                        //v.setBackgroundColor(Color.GREEN);
                        ( (DateShow)v).setStateBig(0);
                    }
                }
            });
            this.addView(button);
            buttonstest.add(button);
        }


    }

    private void testDate(){
        Log.e("根据日期找星期",new DateTool().getDayOfWeekByDate("2018-6-14"));
        Log.e("根据日期找星期",new DateTool().getDaysOfWeek(2018,6,1)+"");
        Log.e("根据日期找天数",new DateTool().getDaysByYearMonth(2016,10)+"");
        Log.e("根据日期找天数",new DateTool().getDaysOfMonth(2016,10,2)+"");
       // DateTool.getCurrentDay();

    }
    public class  MyDay{
        private int Y;
        private int M;
        private int D;

        public int getY() {
            return Y;
        }

        public void setY(int y) {
            Y = y;
        }

        public int getM() {
            return M;
        }

        public void setM(int m) {
            M = m;
        }

        public int getD() {
            return D;
        }

        public void setD(int d) {
            D = d;
        }

        public MyDay(int y, int m, int d) {
            Y = y;
            M = m;
            D = d;
        }
    }

    public MyDay getMyDayToday() {
        return myDayToday;
    }

    public void setMyDayToday(MyDay myDayToday) {
        this.myDayToday = myDayToday;
    }

    public MyDay getOtherDay() {
        return otherDay;
    }

    public void setOtherDay(MyDay otherDay) {
        this.otherDay = otherDay;
    }
}
