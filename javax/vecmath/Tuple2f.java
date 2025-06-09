package javax.vecmath;

import breeze.linalg.support.TensorPairs;
import java.io.Serializable;
import net.minecraftforge.fml.common.launcher.FMLServerTweaker;

public abstract class Tuple2f implements Serializable, Cloneable {
   public static final long int = 9011180388985266884L;
   public float final;
   public float this;

   public Tuple2f(float arg0, float arg1) {
      thisx.final = arg0;
      thisx.this = arg1;
   }

   public Tuple2f(float[] arg0) {
      thisx.final = arg0[0];
      thisx.this = arg0[1];
   }

   public Tuple2f(Tuple2f arg0) {
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public Tuple2f(Tuple2d arg0) {
      thisx.final = (float)arg0.final;
      thisx.this = (float)arg0.this;
   }

   public Tuple2f() {
      thisx.final = 0.0F;
      thisx.this = 0.0F;
   }

   public final void class(float arg0, float arg1) {
      thisx.final = arg0;
      thisx.this = arg1;
   }

   public final void class(float[] arg0) {
      thisx.final = arg0[0];
      thisx.this = arg0[1];
   }

   public final void true(Tuple2f arg0) {
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public final void null(Tuple2d arg0) {
      thisx.final = (float)arg0.final;
      thisx.this = (float)arg0.this;
   }

   public final void null(float[] arg0) {
      arg0[0] = thisx.final;
      arg0[1] = thisx.this;
   }

   public final void class(Tuple2f arg0, Tuple2f arg1) {
      thisx.final = arg0.final + arg1.final;
      thisx.this = arg0.this + arg1.this;
   }

   public final void const(Tuple2f arg0) {
      thisx.final += arg0.final;
      thisx.this += arg0.this;
   }

   public final void null(Tuple2f arg0, Tuple2f arg1) {
      thisx.final = arg0.final - arg1.final;
      thisx.this = arg0.this - arg1.this;
   }

   public final void long(Tuple2f arg0) {
      thisx.final -= arg0.final;
      thisx.this -= arg0.this;
   }

   public final void class(Tuple2f arg0) {
      thisx.final = -arg0.final;
      thisx.this = -arg0.this;
   }

   public final void class() {
      thisx.final = -thisx.final;
      thisx.this = -thisx.this;
   }

   public final void const(float arg0, Tuple2f arg1) {
      thisx.final = arg0 * arg1.final;
      thisx.this = arg0 * arg1.this;
   }

   public final void true(float arg0) {
      thisx.final *= arg0;
      thisx.this *= arg0;
   }

   public final void null(float arg0, Tuple2f arg1, Tuple2f arg2) {
      thisx.final = arg0 * arg1.final + arg2.final;
      thisx.this = arg0 * arg1.this + arg2.this;
   }

   public final void long(float arg0, Tuple2f arg1) {
      thisx.final = arg0 * thisx.final + arg1.final;
      thisx.this = arg0 * thisx.this + arg1.this;
   }

   public int hashCode() {
      long var1 = 1L;
      var1 = VecMathUtil.null(var1, thisx.final);
      var1 = VecMathUtil.null(var1, thisx.this);
      return VecMathUtil.null(var1);
   }

   public boolean null(Tuple2f arg0) {
      try {
         return thisx.final == arg0.final && thisx.this == arg0.this;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public boolean equals(Object arg0) {
      try {
         Tuple2f var2 = (Tuple2f)arg0;
         return thisx.final == var2.final && thisx.this == var2.this;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public boolean null(Tuple2f arg0, float arg1) {
      float var3 = thisx.final - arg0.final;
      if (Float.isNaN(var3)) {
         return false;
      } else if ((var3 < 0.0F ? -var3 : var3) > arg1) {
         return false;
      } else {
         var3 = thisx.this - arg0.this;
         if (Float.isNaN(var3)) {
            return false;
         } else {
            return !((var3 < 0.0F ? -var3 : var3) > arg1);
         }
      }
   }

   public String toString() {
      return FMLServerTweaker.null("G") + thisx.final + TensorPairs.null("\u000eK") + thisx.this + FMLServerTweaker.null("F");
   }

   public final void null(float arg0, float arg1, Tuple2f arg2) {
      if (arg2.final > arg1) {
         thisx.final = arg1;
      } else if (arg2.final < arg0) {
         thisx.final = arg0;
      } else {
         thisx.final = arg2.final;
      }

      if (arg2.this > arg1) {
         thisx.this = arg1;
      } else if (arg2.this < arg0) {
         thisx.this = arg0;
      } else {
         thisx.this = arg2.this;
      }

   }

   public final void class(float arg0, Tuple2f arg1) {
      if (arg1.final < arg0) {
         thisx.final = arg0;
      } else {
         thisx.final = arg1.final;
      }

      if (arg1.this < arg0) {
         thisx.this = arg0;
      } else {
         thisx.this = arg1.this;
      }

   }

   public final void null(float arg0, Tuple2f arg1) {
      if (arg1.final > arg0) {
         thisx.final = arg0;
      } else {
         thisx.final = arg1.final;
      }

      if (arg1.this > arg0) {
         thisx.this = arg0;
      } else {
         thisx.this = arg1.this;
      }

   }

   public final void null(Tuple2f arg0) {
      thisx.final = Math.abs(arg0.final);
      thisx.this = Math.abs(arg0.this);
   }

   public final void null(float arg0, float arg1) {
      if (thisx.final > arg1) {
         thisx.final = arg1;
      } else if (thisx.final < arg0) {
         thisx.final = arg0;
      }

      if (thisx.this > arg1) {
         thisx.this = arg1;
      } else if (thisx.this < arg0) {
         thisx.this = arg0;
      }

   }

   public final void const(float arg0) {
      if (thisx.final < arg0) {
         thisx.final = arg0;
      }

      if (thisx.this < arg0) {
         thisx.this = arg0;
      }

   }

   public final void long(float arg0) {
      if (thisx.final > arg0) {
         thisx.final = arg0;
      }

      if (thisx.this > arg0) {
         thisx.this = arg0;
      }

   }

   public final void null() {
      thisx.final = Math.abs(thisx.final);
      thisx.this = Math.abs(thisx.this);
   }

   public final void null(Tuple2f arg0, Tuple2f arg1, float arg2) {
      thisx.final = (1.0F - arg2) * arg0.final + arg2 * arg1.final;
      thisx.this = (1.0F - arg2) * arg0.this + arg2 * arg1.this;
   }

   public final void null(Tuple2f arg0, float arg1) {
      thisx.final = (1.0F - arg1) * thisx.final + arg1 * arg0.final;
      thisx.this = (1.0F - arg1) * thisx.this + arg1 * arg0.this;
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public final float class() {
      return thisx.final;
   }

   public final void class(float arg0) {
      thisx.final = arg0;
   }

   public final float null() {
      return thisx.this;
   }

   public final void null(float arg0) {
      thisx.this = arg0;
   }
}
