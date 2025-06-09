package javax.vecmath;

import java.awt.Color;
import java.io.Serializable;

public class Color4f extends Tuple4f implements Serializable {
   public static final long false = 8577680141580006740L;

   public Color4f(float arg0, float arg1, float arg2, float arg3) {
      super(arg0, arg1, arg2, arg3);
   }

   public Color4f(float[] arg0) {
      super(arg0);
   }

   public Color4f(Color4f arg0) {
      super((Tuple4f)arg0);
   }

   public Color4f(Tuple4f arg0) {
      super(arg0);
   }

   public Color4f(Tuple4d arg0) {
      super(arg0);
   }

   public Color4f(Color arg0) {
      super((float)arg0.getRed() / 255.0F, (float)arg0.getGreen() / 255.0F, (float)arg0.getBlue() / 255.0F, (float)arg0.getAlpha() / 255.0F);
   }

   public Color4f() {
   }

   public final void null(Color arg0) {
      this.null = (float)arg0.getRed() / 255.0F;
      this.int = (float)arg0.getGreen() / 255.0F;
      this.final = (float)arg0.getBlue() / 255.0F;
      this.this = (float)arg0.getAlpha() / 255.0F;
   }

   public final Color null() {
      int var1 = Math.round(this.null * 255.0F);
      int var2 = Math.round(this.int * 255.0F);
      int var3 = Math.round(this.final * 255.0F);
      int var4 = Math.round(this.this * 255.0F);
      return new Color(var1, var2, var3, var4);
   }
}
