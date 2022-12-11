package com.example.milkyway.calendarDecorator;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;

import com.example.milkyway.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.HashSet;

public class DayImageViewDecorator implements DayViewDecorator {

    private final Drawable drawable;
    private CalendarDay dates;

    public DayImageViewDecorator(CalendarDay date, Drawable drawable) {
        this.drawable = drawable;
        this.dates = date;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
//        Log.e("day", day.toString());
//        Log.e("day true?", String.valueOf(dates != null && dates.equals(day)));
        return dates != null && dates.equals(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        if (drawable != null) {
            Log.e("drawable", drawable.toString());
            Log.e("drawable은 null이아님", "dd");
//            ImageSpan imageSpan = new ImageSpan(drawable);
//            view.addSpan(imageSpan);
            view.setSelectionDrawable(drawable);
//            view.setBackgroundDrawable(drawable);
//            Drawable drawable =
//            view.setSelectionDrawable(R.drawable.ic_baseline_favorite_24);
//            view.setBackgroundDrawable(A);
//            view.addSpan(drawable);
        } else {
            Log.e("dayImageViewDecorator image null", null);
        }
//        view.addSpan(new ForegroundColorSpan(Color.RED){});
    }
}
