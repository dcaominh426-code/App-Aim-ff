package com.ultraaim.trainer;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.*;
import android.view.*;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.Random;

public class MainActivity extends Activity {
    @Override public void onCreate(Bundle b) { super.onCreate(b); getWindow().getDecorView().setSystemUiVisibility(5894 | 1024 | 512); setContentView(new Game(this)); }
    class Game extends View {
        Paint p = new Paint(3); Random rand = new Random(); SharedPreferences prefs;
        float w,h,tx,ty,vx=3,vy=2.4f,r=43,cx,cy,downX,downY; int score=0,hits=0,shots=0,level=1,best; long start=System.currentTimeMillis(); boolean dragging=false;
        Game(Context c){super(c);prefs=getSharedPreferences("aim",0);best=prefs.getInt("best",0);setLayerType(View.LAYER_TYPE_SOFTWARE,null);}
        void color(int c){p.setColor(c);p.setStyle(Paint.Style.FILL);p.setStrokeWidth(3);}
        void txt(Canvas c,String s,float x,float y,int size,int col){color(col);p.setTextSize(size);p.setTypeface(Typeface.create("sans-serif",Typeface.BOLD));c.drawText(s,x,y,p);}
        @Override protected void onSizeChanged(int a,int b,int oldw,int oldh){w=a;h=b;cx=w/2;cy=h*.7f;tx=w*.5f;ty=h*.35f;}
        @Override protected void onDraw(Canvas c){super.onDraw(c); c.drawColor(Color.rgb(12,17,31));
            color(0xff1c2944); for(int i=0;i<12;i++)c.drawLine(i*w/11,120,i*w/11,h-120,p);
            for(int j=0;j<12;j++)c.drawLine(0,120+j*(h-240)/11,w,120+j*(h-240)/11,p);
            tx+=vx;ty+=vy; if(tx<r||tx>w-r)vx=-vx;if(ty<175+r||ty>h*.67f)vy=-vy;
            color(0xffff5368);c.drawCircle(tx,ty,r+9,p);color(0xffffd6dc);c.drawCircle(tx,ty,r*.70f,p);color(0xffff5368);c.drawCircle(tx,ty,r*.35f,p);
            color(0xff77edff);p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(4);c.drawCircle(cx,cy,24,p);c.drawLine(cx-40,cy,cx-12,cy,p);c.drawLine(cx+12,cy,cx+40,cy,p);c.drawLine(cx,cy-40,cx,cy-12,p);c.drawLine(cx,cy+12,cx,cy+40,p);p.setStyle(Paint.Style.FILL);
            txt(c,"ULTRA AIM FF",28,65,38,0xff77edff);txt(c,"DIEM: "+score+"   KY LUC: "+best,28,112,25,Color.WHITE);
            txt(c,"TRUNG: "+hits+"/"+shots+"   CHINH XAC: "+(shots==0?0:(hits*100/shots))+"%",28,146,21,0xffcbd5e1);
            color(0xff263753);c.drawRoundRect(20,h-105,w*.48f,h-25,16,16,p);c.drawRoundRect(w*.52f,h-105,w-20,h-25,16,16,p);
            txt(c,"TOC DO -",36,h-56,24,Color.WHITE);txt(c,"TOC DO +",w*.55f,h-56,24,Color.WHITE);
            txt(c,"VUOT TÂM NGẮM VÀO MỤC TIÊU",28,h-136,19,0xffb9c7df);
            postInvalidateDelayed(16);
        }
        void attempt(){shots++;float dx=cx-tx,dy=cy-ty;if(dx*dx+dy*dy<(r+24)*(r+24)){hits++;score+=10*level;if(score>best){best=score;prefs.edit().putInt("best",best).apply();}tx=r+rand.nextFloat()*Math.max(1,w-2*r);ty=195+r+rand.nextFloat()*Math.max(1,h*.55f-195-2*r);}}
        @Override public boolean onTouchEvent(android.view.MotionEvent e){float x=e.getX(),y=e.getY();switch(e.getActionMasked()){
            case MotionEvent.ACTION_DOWN: if(y>h-115){if(x<w/2){level=Math.max(1,level-1);}else{level=Math.min(8,level+1);}float sp=2+level*1.25f;vx=Math.copySign(sp,vx);vy=Math.copySign(sp*.8f,vy);invalidate();return true;}dragging=true;downX=x;downY=y;return true;
            case MotionEvent.ACTION_MOVE:if(dragging){cx=Math.max(20,Math.min(w-20,cx+x-downX));cy=Math.max(170,Math.min(h-155,cy+y-downY));downX=x;downY=y;invalidate();}return true;
            case MotionEvent.ACTION_UP:if(dragging){attempt();dragging=false;invalidate();}return true;
        }return true;}
    }
          }
