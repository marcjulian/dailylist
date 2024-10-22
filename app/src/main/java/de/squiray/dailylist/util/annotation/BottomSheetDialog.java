package de.squiray.dailylist.util.annotation;

import android.support.design.widget.BottomSheetBehavior;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
public @interface BottomSheetDialog {
    int layout();

    int state() default BottomSheetBehavior.STATE_COLLAPSED;
}
