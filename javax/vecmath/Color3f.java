package javax.vecmath;

import java.awt.Color;
import java.io.Serializable;

public class Color3f extends Tuple3f implements Serializable {
   public static final long null = -1861792981817493659L;

   public Color3f(float arg0, float arg1, float arg2) {
      super(arg0, arg1, arg2);
   }

   public Color3f(float[] arg0) {
      super(arg0);
   }

   public Color3f(Color3f arg0) {
      super((Tuple3f)arg0);
   }

   public Color3f(Tuple3f arg0) {
      super(arg0);
   }

   public Color3f(Tuple3d arg0) {
      super(arg0);
   }

   public Color3f(Color arg0) {
      super((float)arg0.getRed() / 255.0F, (float)arg0.getGreen() / 255.0F, (float)arg0.getBlue() / 255.0F);
   }

   public Color3f() {
   }

   public final void null(Color arg0) {
      this.int = (float)arg0.getRed() / 255.0F;
      this.final = (float)arg0.getGreen() / 255.0F;
      this.this = (float)arg0.getBlue() / 255.0F;
   }

   public final Color null() {
      int var1 = Math.round(this.int * 255.0F);
      int var2 = Math.round(this.final * 255.0F);
      int var3 = Math.round(this.this * 255.0F);
      return new Color(var1, var2, var3);
   }
}
