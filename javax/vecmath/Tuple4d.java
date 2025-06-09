package javax.vecmath;

import java.io.Serializable;
import net.daporkchop.lib.primitive.lambda.IntBoolIntConsumer;
import net.minecraftforge.fml.common.launcher.FMLServerTweaker;

public abstract class Tuple4d implements Serializable, Cloneable {
   public static final long false = -4748953690425311052L;
   public double null;
   public double int;
   public double final;
   public double this;

   public Tuple4d(double arg0, double arg1, double arg2, double arg3) {
      thisx.null = arg0;
      thisx.int = arg1;
      thisx.final = arg2;
      thisx.this = arg3;
   }

   public Tuple4d(double[] arg0) {
      thisx.null = arg0[0];
      thisx.int = arg0[1];
      thisx.final = arg0[2];
      thisx.this = arg0[3];
   }

   public Tuple4d(Tuple4d arg0) {
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public Tuple4d(Tuple4f arg0) {
      thisx.null = (double)arg0.null;
      thisx.int = (double)arg0.int;
      thisx.final = (double)arg0.final;
      thisx.this = (double)arg0.this;
   }

   public Tuple4d() {
      thisx.null = 0.0D;
      thisx.int = 0.0D;
      thisx.final = 0.0D;
      thisx.this = 0.0D;
   }

   public final void null(double arg0, double arg1, double arg2, double arg3) {
      thisx.null = arg0;
      thisx.int = arg1;
      thisx.final = arg2;
      thisx.this = arg3;
   }

   public final void class(double[] arg0) {
      thisx.null = arg0[0];
      thisx.int = arg0[1];
      thisx.final = arg0[2];
      thisx.this = arg0[3];
   }

   public final void do(Tuple4d arg0) {
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public final void null(Tuple4f arg0) {
      thisx.null = (double)arg0.null;
      thisx.int = (double)arg0.int;
      thisx.final = (double)arg0.final;
      thisx.this = (double)arg0.this;
   }

   public final void null(double[] arg0) {
      arg0[0] = thisx.null;
      arg0[1] = thisx.int;
      arg0[2] = thisx.final;
      arg0[3] = thisx.this;
   }

   public final void true(Tuple4d arg0) {
      arg0.null = thisx.null;
      arg0.int = thisx.int;
      arg0.final = thisx.final;
      arg0.this = thisx.this;
   }

   public final void class(Tuple4d arg0, Tuple4d arg1) {
      thisx.null = arg0.null + arg1.null;
      thisx.int = arg0.int + arg1.int;
      thisx.final = arg0.final + arg1.final;
      thisx.this = arg0.this + arg1.this;
   }

   public final void const(Tuple4d arg0) {
      thisx.null += arg0.null;
      thisx.int += arg0.int;
      thisx.final += arg0.final;
      thisx.this += arg0.this;
   }

   public final void null(Tuple4d arg0, Tuple4d arg1) {
      thisx.null = arg0.null - arg1.null;
      thisx.int = arg0.int - arg1.int;
      thisx.final = arg0.final - arg1.final;
      thisx.this = arg0.this - arg1.this;
   }

   public final void long(Tuple4d arg0) {
      thisx.null -= arg0.null;
      thisx.int -= arg0.int;
      thisx.final -= arg0.final;
      thisx.this -= arg0.this;
   }

   public final void class(Tuple4d arg0) {
      thisx.null = -arg0.null;
      thisx.int = -arg0.int;
      thisx.final = -arg0.final;
      thisx.this = -arg0.this;
   }

   public final void class() {
      thisx.null = -thisx.null;
      thisx.int = -thisx.int;
      thisx.final = -thisx.final;
      thisx.this = -thisx.this;
   }

   public final void const(double arg0, Tuple4d arg1) {
      thisx.null = arg0 * arg1.null;
      thisx.int = arg0 * arg1.int;
      thisx.final = arg0 * arg1.final;
      thisx.this = arg0 * arg1.this;
   }

   public final void short(double arg0) {
      thisx.null *= arg0;
      thisx.int *= arg0;
      thisx.final *= arg0;
      thisx.this *= arg0;
   }

   public final void null(double arg0, Tuple4d arg1, Tuple4d arg2) {
      thisx.null = arg0 * arg1.null + arg2.null;
      thisx.int = arg0 * arg1.int + arg2.int;
      thisx.final = arg0 * arg1.final + arg2.final;
      thisx.this = arg0 * arg1.this + arg2.this;
   }

   /** @deprecated */
   public final void long(float arg0, Tuple4d arg1) {
      thisx.long((double)arg0, arg1);
   }

   public final void long(double arg0, Tuple4d arg1) {
      thisx.null = arg0 * thisx.null + arg1.null;
      thisx.int = arg0 * thisx.int + arg1.int;
      thisx.final = arg0 * thisx.final + arg1.final;
      thisx.this = arg0 * thisx.this + arg1.this;
   }

   public String toString() {
      return FMLServerTweaker.null("G") + thisx.null + IntBoolIntConsumer.null("]{") + thisx.int + FMLServerTweaker.null("=O") + thisx.final + IntBoolIntConsumer.null("]{") + thisx.this + FMLServerTweaker.null("F");
   }

   public boolean null(Tuple4d arg0) {
      try {
         return thisx.null == arg0.null && thisx.int == arg0.int && thisx.final == arg0.final && thisx.this == arg0.this;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public boolean equals(Object arg0) {
      try {
         Tuple4d var2 = (Tuple4d)arg0;
         return thisx.null == var2.null && thisx.int == var2.int && thisx.final == var2.final && thisx.this == var2.this;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public boolean null(Tuple4d arg0, double arg1) {
      double var4 = thisx.null - arg0.null;
      if (Double.isNaN(var4)) {
         return false;
      } else if ((var4 < 0.0D ? -var4 : var4) > arg1) {
         return false;
      } else {
         var4 = thisx.int - arg0.int;
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
   }

   public int hashCode() {
      long var1 = 1L;
      var1 = VecMathUtil.null(var1, thisx.null);
      var1 = VecMathUtil.null(var1, thisx.int);
      var1 = VecMathUtil.null(var1, thisx.final);
      var1 = VecMathUtil.null(var1, thisx.this);
      return VecMathUtil.null(var1);
   }

   /** @deprecated */
   public final void null(float arg0, float arg1, Tuple4d arg2) {
      thisx.null((double)arg0, (double)arg1, arg2);
   }

   public final void null(double arg0, double arg1, Tuple4d arg2) {
      if (arg2.null > arg1) {
         thisx.null = arg1;
      } else if (arg2.null < arg0) {
         thisx.null = arg0;
      } else {
         thisx.null = arg2.null;
      }

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
   public final void class(float arg0, Tuple4d arg1) {
      thisx.class((double)arg0, arg1);
   }

   public final void class(double arg0, Tuple4d arg1) {
      if (arg1.null < arg0) {
         thisx.null = arg0;
      } else {
         thisx.null = arg1.null;
      }

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
   public final void null(float arg0, Tuple4d arg1) {
      thisx.null((double)arg0, arg1);
   }

   public final void null(double arg0, Tuple4d arg1) {
      if (arg1.null > arg0) {
         thisx.null = arg0;
      } else {
         thisx.null = arg1.null;
      }

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
         thisx.this = arg1.final;
      }

   }

   public final void null(Tuple4d arg0) {
      thisx.null = Math.abs(arg0.null);
      thisx.int = Math.abs(arg0.int);
      thisx.final = Math.abs(arg0.final);
      thisx.this = Math.abs(arg0.this);
   }

   /** @deprecated */
   public final void null(float arg0, float arg1) {
      thisx.null((double)arg0, (double)arg1);
   }

   public final void null(double arg0, double arg1) {
      if (thisx.null > arg1) {
         thisx.null = arg1;
      } else if (thisx.null < arg0) {
         thisx.null = arg0;
      }

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
      thisx.do((double)arg0);
   }

   public final void do(double arg0) {
      if (thisx.null < arg0) {
         thisx.null = arg0;
      }

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
      thisx.true((double)arg0);
   }

   public final void true(double arg0) {
      if (thisx.null > arg0) {
         thisx.null = arg0;
      }

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
      thisx.null = Math.abs(thisx.null);
      thisx.int = Math.abs(thisx.int);
      thisx.final = Math.abs(thisx.final);
      thisx.this = Math.abs(thisx.this);
   }

   /** @deprecated */
   public void null(Tuple4d arg0, Tuple4d arg1, float arg2) {
      thisx.null(arg0, arg1, (double)arg2);
   }

   public void null(Tuple4d arg0, Tuple4d arg1, double arg2) {
      thisx.null = (1.0D - arg2) * arg0.null + arg2 * arg1.null;
      thisx.int = (1.0D - arg2) * arg0.int + arg2 * arg1.int;
      thisx.final = (1.0D - arg2) * arg0.final + arg2 * arg1.final;
      thisx.this = (1.0D - arg2) * arg0.this + arg2 * arg1.this;
   }

   /** @deprecated */
   public void null(Tuple4d arg0, float arg1) {
      thisx.null(arg0, (double)arg1);
   }

   public void null(Tuple4d arg0, double arg1) {
      thisx.null = (1.0D - arg1) * thisx.null + arg1 * arg0.null;
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

   public final double const() {
      return thisx.null;
   }

   public final void const(double arg0) {
      thisx.null = arg0;
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
      int var10000 = (3 ^ 5) << 3 ^ 3;
      int var10001 = 4 << 4 ^ 3;
      int var10002 = (3 ^ 5) << 3 ^ 3 ^ 5;
      int var10003 = arg0.length();
      char[] var10004 = new char[var10003];
      boolean var10006 = true;
      int var5 = var10003 - 1;
      var10003 = var10002;
      int var3;
      var10002 = var3 = var5;
      char[] var1 = var10004;
      int var4 = var10003;
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
