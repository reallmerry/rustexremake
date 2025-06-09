package javax.vecmath;

import android.text.style.DrawableMarginSpan;
import java.io.Serializable;
import net.minecraftforge.common.chunkio.ChunkIOExecutor;

public abstract class Tuple3d implements Serializable, Cloneable {
   public static final long null = 5542096614926168415L;
   public double int;
   public double final;
   public double this;

   public Tuple3d(double arg0, double arg1, double arg2) {
      thisx.int = arg0;
      thisx.final = arg1;
      thisx.this = arg2;
   }

   public Tuple3d(double[] arg0) {
      thisx.int = arg0[0];
      thisx.final = arg0[1];
      thisx.this = arg0[2];
   }

   public Tuple3d(Tuple3d arg0) {
      thisx.int = arg0.int;
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public Tuple3d(Tuple3f arg0) {
      thisx.int = (double)arg0.int;
      thisx.final = (double)arg0.final;
      thisx.this = (double)arg0.this;
   }

   public Tuple3d() {
      thisx.int = 0.0D;
      thisx.final = 0.0D;
      thisx.this = 0.0D;
   }

   public final void null(double arg0, double arg1, double arg2) {
      thisx.int = arg0;
      thisx.final = arg1;
      thisx.this = arg2;
   }

   public final void class(double[] arg0) {
      thisx.int = arg0[0];
      thisx.final = arg0[1];
      thisx.this = arg0[2];
   }

   public final void do(Tuple3d arg0) {
      thisx.int = arg0.int;
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public final void null(Tuple3f arg0) {
      thisx.int = (double)arg0.int;
      thisx.final = (double)arg0.final;
      thisx.this = (double)arg0.this;
   }

   public final void null(double[] arg0) {
      arg0[0] = thisx.int;
      arg0[1] = thisx.final;
      arg0[2] = thisx.this;
   }

   public final void true(Tuple3d arg0) {
      arg0.int = thisx.int;
      arg0.final = thisx.final;
      arg0.this = thisx.this;
   }

   public final void class(Tuple3d arg0, Tuple3d arg1) {
      thisx.int = arg0.int + arg1.int;
      thisx.final = arg0.final + arg1.final;
      thisx.this = arg0.this + arg1.this;
   }

   public final void const(Tuple3d arg0) {
      thisx.int += arg0.int;
      thisx.final += arg0.final;
      thisx.this += arg0.this;
   }

   public final void null(Tuple3d arg0, Tuple3d arg1) {
      thisx.int = arg0.int - arg1.int;
      thisx.final = arg0.final - arg1.final;
      thisx.this = arg0.this - arg1.this;
   }

   public final void long(Tuple3d arg0) {
      thisx.int -= arg0.int;
      thisx.final -= arg0.final;
      thisx.this -= arg0.this;
   }

   public final void class(Tuple3d arg0) {
      thisx.int = -arg0.int;
      thisx.final = -arg0.final;
      thisx.this = -arg0.this;
   }

   public final void class() {
      thisx.int = -thisx.int;
      thisx.final = -thisx.final;
      thisx.this = -thisx.this;
   }

   public final void const(double arg0, Tuple3d arg1) {
      thisx.int = arg0 * arg1.int;
      thisx.final = arg0 * arg1.final;
      thisx.this = arg0 * arg1.this;
   }

   public final void do(double arg0) {
      thisx.int *= arg0;
      thisx.final *= arg0;
      thisx.this *= arg0;
   }

   public final void null(double arg0, Tuple3d arg1, Tuple3d arg2) {
      thisx.int = arg0 * arg1.int + arg2.int;
      thisx.final = arg0 * arg1.final + arg2.final;
      thisx.this = arg0 * arg1.this + arg2.this;
   }

   /** @deprecated */
   public final void null(double arg0, Tuple3f arg1) {
      thisx.long(arg0, new Point3d(arg1));
   }

   public final void long(double arg0, Tuple3d arg1) {
      thisx.int = arg0 * thisx.int + arg1.int;
      thisx.final = arg0 * thisx.final + arg1.final;
      thisx.this = arg0 * thisx.this + arg1.this;
   }

   public String toString() {
      return DrawableMarginSpan.null("g") + thisx.int + ChunkIOExecutor.null("\u00167") + thisx.final + DrawableMarginSpan.null("\u0001o") + thisx.this + ChunkIOExecutor.null(">");
   }

   public int hashCode() {
      long var1 = 1L;
      var1 = VecMathUtil.null(var1, thisx.int);
      var1 = VecMathUtil.null(var1, thisx.final);
      var1 = VecMathUtil.null(var1, thisx.this);
      return VecMathUtil.null(var1);
   }

   public boolean null(Tuple3d arg0) {
      try {
         return thisx.int == arg0.int && thisx.final == arg0.final && thisx.this == arg0.this;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public boolean equals(Object arg0) {
      try {
         Tuple3d var2 = (Tuple3d)arg0;
         return thisx.int == var2.int && thisx.final == var2.final && thisx.this == var2.this;
      } catch (ClassCastException var3) {
         return false;
      } catch (NullPointerException var4) {
         return false;
      }
   }

   public boolean null(Tuple3d arg0, double arg1) {
      double var4 = thisx.int - arg0.int;
      if (Double.isNaN(var4)) {
         return false;
      } else if ((var4 < 0.0D ? -var4 : var4) > arg1) {
         return false;
      } else {
         var4 = thisx.final - arg0.final;
         if (Double.isNaN(var4)) {
            return false;
         } else if ((var4 < 0.0D ? -var4 : var4) > arg1) {
            return false;
         } else {
            var4 = thisx.this - arg0.this;
            if (Double.isNaN(var4)) {
               return false;
            } else {
               return !((var4 < 0.0D ? -var4 : var4) > arg1);
            }
         }
      }
   }

   /** @deprecated */
   public final void null(float arg0, float arg1, Tuple3d arg2) {
      thisx.null((double)arg0, (double)arg1, arg2);
   }

   public final void null(double arg0, double arg1, Tuple3d arg2) {
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

   /** @deprecated */
   public final void class(float arg0, Tuple3d arg1) {
      thisx.class((double)arg0, arg1);
   }

   public final void class(double arg0, Tuple3d arg1) {
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

   /** @deprecated */
   public final void null(float arg0, Tuple3d arg1) {
      thisx.null((double)arg0, arg1);
   }

   public final void null(double arg0, Tuple3d arg1) {
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

   public final void null(Tuple3d arg0) {
      thisx.int = Math.abs(arg0.int);
      thisx.final = Math.abs(arg0.final);
      thisx.this = Math.abs(arg0.this);
   }

   /** @deprecated */
   public final void null(float arg0, float arg1) {
      thisx.null((double)arg0, (double)arg1);
   }

   public final void null(double arg0, double arg1) {
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

   /** @deprecated */
   public final void class(float arg0) {
      thisx.true((double)arg0);
   }

   public final void true(double arg0) {
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

   /** @deprecated */
   public final void null(float arg0) {
      thisx.const((double)arg0);
   }

   public final void const(double arg0) {
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

   /** @deprecated */
   public final void null(Tuple3d arg0, Tuple3d arg1, float arg2) {
      thisx.null(arg0, arg1, (double)arg2);
   }

   public final void null(Tuple3d arg0, Tuple3d arg1, double arg2) {
      thisx.int = (1.0D - arg2) * arg0.int + arg2 * arg1.int;
      thisx.final = (1.0D - arg2) * arg0.final + arg2 * arg1.final;
      thisx.this = (1.0D - arg2) * arg0.this + arg2 * arg1.this;
   }

   /** @deprecated */
   public final void null(Tuple3d arg0, float arg1) {
      thisx.null(arg0, (double)arg1);
   }

   public final void null(Tuple3d arg0, double arg1) {
      thisx.int = (1.0D - arg1) * thisx.int + arg1 * arg0.int;
      thisx.final = (1.0D - arg1) * thisx.final + arg1 * arg0.final;
      thisx.this = (1.0D - arg1) * thisx.this + arg1 * arg0.this;
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public final double long() {
      return thisx.int;
   }

   public final void long(double arg0) {
      thisx.int = arg0;
   }

   public final double class() {
      return thisx.final;
   }

   public final void class(double arg0) {
      thisx.final = arg0;
   }

   public final double null() {
      return thisx.this;
   }

   public final void null(double arg0) {
      thisx.this = arg0;
   }

   public static String null(String arg0) {
      int var10000 = 4 << 4 ^ (2 ^ 5) << 1;
      int var10001 = 4 << 4 ^ 3;
      int var10002 = (2 ^ 5) << 4 ^ 1;
      int var10003 = arg0.length();
      char[] var10004 = new char[var10003];
      boolean var10006 = true;
      int var5 = var10003 - 1;
      var10003 = var10002;
      int var3;
      var10002 = var3 = var5;
      char[] var1 = var10004;
      int var4 = var10003;
      var10001 = var10000;
      var10000 = var10002;

      for(int var2 = var10001; var10000 >= 0; var10000 = var3) {
         var10001 = var3;
         char var6 = arg0.charAt(var3);
         --var3;
         var1[var10001] = (char)(var6 ^ var2);
         if (var3 < 0) {
            break;
         }

         var10002 = var3--;
         var1[var10002] = (char)(arg0.charAt(var10002) ^ var4);
      }

      return new String(var1);
   }
}
