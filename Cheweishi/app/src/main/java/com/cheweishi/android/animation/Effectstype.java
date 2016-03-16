package com.cheweishi.android.animation;

import com.cheweishi.android.animation.FadeIn;
import com.cheweishi.android.animation.Fall;
import com.cheweishi.android.animation.FlipH;
import com.cheweishi.android.animation.FlipV;
import com.cheweishi.android.animation.NewsPaper;
import com.cheweishi.android.animation.RotateBottom;
import com.cheweishi.android.animation.RotateLeft;
import com.cheweishi.android.animation.Shake;
import com.cheweishi.android.animation.SideFall;
import com.cheweishi.android.animation.SlideBottom;
import com.cheweishi.android.animation.SlideLeft;
import com.cheweishi.android.animation.SlideRight;
import com.cheweishi.android.animation.SlideTop;
import com.cheweishi.android.animation.Slit;

/**
  * 类名称:   Effectstype 
  * 创建人:    xhl 
  * 创建时间:  2014-12-24 下午2:16:20     
  * 版本:      v1.0  
  * 类描述:	NiftyDialogBuilder的动画效果枚举类
 */
public enum Effectstype {

    Fadein(FadeIn.class),
    Slideleft(SlideLeft.class),
    Slidetop(SlideTop.class),
    SlideBottom(SlideBottom.class),
    Slideright(SlideRight.class),
    Fall(Fall.class),
    Newspager(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class),
    RotateBottom(RotateBottom.class),
    RotateLeft(RotateLeft.class),
    Slit(Slit.class),
    Shake(Shake.class),
    Sidefill(SideFall.class);
    private Class<? extends BaseEffects> effectsClazz;

    private Effectstype(Class<? extends BaseEffects> mclass) {
        effectsClazz = mclass;
    }

    public BaseEffects getAnimator() {
        BaseEffects bEffects=null;
	try {
		bEffects = effectsClazz.newInstance();
	} catch (ClassCastException e) {
		throw new Error("Can not init animatorClazz instance");
	} catch (InstantiationException e) {
		throw new Error("Can not init animatorClazz instance");
	} catch (IllegalAccessException e) {
		throw new Error("Can not init animatorClazz instance");
	}
	return bEffects;
    }
}
