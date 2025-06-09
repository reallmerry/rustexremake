package javax.vecmath;

import java.io.Serializable;
import net.daporkchop.lib.primitive.collection.ShortIterator;
import net.daporkchop.lib.primitive.lambda.LongBoolConsumer;

public abstract class Tuple3f implements Serializable, Cloneable {
   public static final long null = 5019834619484343712L;
   public float int;
   public float final;
   public float this;

   public Tuple3f(float arg0, float arg1, float arg2) {
      thisx.int = arg0;
      thisx.final = arg1;
      thisx.this = arg2;
   }

   public Tuple3f(float[] arg0) {
      thisx.int = arg0[0];
      thisx.final = arg0[1];
      thisx.this = arg0[2];
   }

   public Tuple3f(Tuple3f arg0) {
      thisx.int = arg0.int;
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public Tuple3f(Tuple3d arg0) {
      thisx.int = (float)arg0.int;
      thisx.final = (float)arg0.final;
      thisx.this = (float)arg0.this;
   }

   public Tuple3f() {
      thisx.int = 0.0F;
      thisx.final = 0.0F;
      thisx.this = 0.0F;
   }

   public String toString() {
      return LongBoolConsumer.null("Q") + thisx.int + ShortIterator.null("f-") + thisx.final + LongBoolConsumer.null("}Y") + thisx.this + ShortIterator.null("$");
   }

   public final void null(float arg0, float arg1, float arg2) {
      thisx.int = arg0;
      thisx.final = arg1;
      thisx.this = arg2;
   }

   public final void class(float[] arg0) {
      thisx.int = arg0[0];
      thisx.final = arg0[1];
      thisx.this = arg0[2];
   }

   public final void do(Tuple3f arg0) {
      thisx.int = arg0.int;
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public final void null(Tuple3d arg0) {
      thisx.int = (float)arg0.int;
      thisx.final = (float)arg0.final;
      thisx.this = (float)arg0.this;
   }

   public final void null(float[] arg0) {
      arg0[0] = thisx.int;
      arg0[1] = thisx.final;
      arg0[2] = thisx.this;
   }

   public final void true(Tuple3f arg0) {
      arg0.int = thisx.int;
      arg0.final = thisx.final;
      arg0.this = thisx.this;
   }

   public final void class(Tuple3f arg0, Tuple3f arg1) {
      thisx.int = arg0.int + arg1.int;
      thisx.final = arg0.final + arg1.final;
      thisx.this = arg0.this + arg1.this;
   }

   public final void const(Tuple3f arg0) {
      thisx.int += arg0.int;
      thisx.final += arg0.final;
      thisx.this += arg0.this;
   }

   public final void null(Tuple3f arg0, Tuple3f arg1) {
      thisx.int = arg0.int - arg1.int;
      thisx.final = arg0.final - arg1.final;
      thisx.this = arg0.this - arg1.this;
   }

   public final void long(Tuple3f arg0) {
      thisx.int -= arg0.int;
      thisx.final -= arg0.final;
      thisx.this -= arg0.this;
   }

   public final void class(Tuple3f arg0) {
      thisx.int = -arg0.int;
      thisx.final = -arg0.final;
      thisx.this = -arg0.this;
   }

   public final void class() {
      thisx.int = -thisx.int;
      thisx.final = -thisx.final;
      thisx.this = -thisx.this;
   }

   public final void const(float arg0, Tuple3f arg1) {
      thisx.int = arg0 * arg1.int;
      thisx.final = arg0 * arg1.final;
      thisx.this = arg0 * arg1.this;
   }

   public final void do(float arg0) {
      thisx.int *= arg0;
      thisx.final *= arg0;
      thisx.this *= arg0;
   }

   public final void null(float arg0, Tuple3f arg1, Tuple3f arg2) {
      thisx.int = arg0 * arg1.int + arg2.int;
      thisx.final = arg0 * arg1.final + arg2.final;
      thisx.this = arg0 * arg1.this + arg2.this;
   }

   public final void long(float arg0, Tuple3f arg1) {
      thisx.int = arg0 * thisx.int + arg1.int;
      thisx.final = arg0 * thisx.final + arg1.final;
      thisx.this = arg0 * thisx.this + arg1.this;
   }

   public boolean null(Tuple3f arg0) {
      try {
         return thisx.int == arg0.int && thisx.final == arg0.final && thisx.this == arg0.this;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public boolean equals(Object arg0) {
      try {
         Tuple3f var2 = (Tuple3f)arg0;
         return thisx.int == var2.int && thisx.final == var2.final && thisx.this == var2.this;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public boolean null(Tuple3f arg0, float arg1) {
      float var3 = thisx.int - arg0.int;
      if (Float.isNaN(var3)) {
         return false;
      } else if ((var3 < 0.0F ? -var3 : var3) > arg1) {
         return false;
      } else {
         var3 = thisx.final - arg0.final;
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
   }

   public int hashCode() {
      long var1 = 1L;
      var1 = VecMathUtil.null(var1, thisx.int);
      var1 = VecMathUtil.null(var1, thisx.final);
      var1 = VecMathUtil.null(var1, thisx.this);
      return VecMathUtil.null(var1);
   }

   public final void null(float arg0, float arg1, Tuple3f arg2) {
      if (arg2.int > arg1) {
         thisx.int = arg1;
      } else if (arg2.int < arg0) {
         thisx.int = arg0;
      } else {
         thisx.int = arg2.int;
      }

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

   public final void class(float arg0, Tuple3f arg1) {
      if (arg1.int < arg0) {
         thisx.int = arg0;
      } else {
         thisx.int = arg1.int;
      }

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

   public final void null(float arg0, Tuple3f arg1) {
      if (arg1.int > arg0) {
         thisx.int = arg0;
      } else {
         thisx.int = arg1.int;
      }

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

   public final void null(Tuple3f arg0) {
      thisx.int = Math.abs(arg0.int);
      thisx.final = Math.abs(arg0.final);
      thisx.this = Math.abs(arg0.this);
   }

   public final void null(float arg0, float arg1) {
      if (thisx.int > arg1) {
         thisx.int = arg1;
      } else if (thisx.int < arg0) {
         thisx.int = arg0;
      }

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

   public final void true(float arg0) {
      if (thisx.int < arg0) {
         thisx.int = arg0;
      }

      if (thisx.final < arg0) {
         thisx.final = arg0;
      }

      if (thisx.this < arg0) {
         thisx.this = arg0;
      }

   }

   public final void const(float arg0) {
      if (thisx.int > arg0) {
         thisx.int = arg0;
      }

      if (thisx.final > arg0) {
         thisx.final = arg0;
      }

      if (thisx.this > arg0) {
         thisx.this = arg0;
      }

   }

   public final void null() {
      thisx.int = Math.abs(thisx.int);
      thisx.final = Math.abs(thisx.final);
      thisx.this = Math.abs(thisx.this);
   }

   public final void null(Tuple3f arg0, Tuple3f arg1, float arg2) {
      thisx.int = (1.0F - arg2) * arg0.int + arg2 * arg1.int;
      thisx.final = (1.0F - arg2) * arg0.final + arg2 * arg1.final;
      thisx.this = (1.0F - arg2) * arg0.this + arg2 * arg1.this;
   }

   public final void null(Tuple3f arg0, float arg1) {
      thisx.int = (1.0F - arg1) * thisx.int + arg1 * arg0.int;
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

   public final float long() {
      return thisx.int;
   }

   public final void long(float arg0) {
      thisx.int = arg0;
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
