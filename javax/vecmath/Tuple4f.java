package javax.vecmath;

import com.alibaba.druid.sql.dialect.presto.ast.stmt.PrestoSelectStatement;
import java.io.Serializable;
import net.minecraft.util.ServerRecipeBookHelper;

public abstract class Tuple4f implements Serializable, Cloneable {
   public static final long false = 7068460319248845763L;
   public float null;
   public float int;
   public float final;
   public float this;

   public Tuple4f(float arg0, float arg1, float arg2, float arg3) {
      thisx.null = arg0;
      thisx.int = arg1;
      thisx.final = arg2;
      thisx.this = arg3;
   }

   public Tuple4f(float[] arg0) {
      thisx.null = arg0[0];
      thisx.int = arg0[1];
      thisx.final = arg0[2];
      thisx.this = arg0[3];
   }

   public Tuple4f(Tuple4f arg0) {
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public Tuple4f(Tuple4d arg0) {
      thisx.null = (float)arg0.null;
      thisx.int = (float)arg0.int;
      thisx.final = (float)arg0.final;
      thisx.this = (float)arg0.this;
   }

   public Tuple4f() {
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 0.0F;
      thisx.this = 0.0F;
   }

   public final void null(float arg0, float arg1, float arg2, float arg3) {
      thisx.null = arg0;
      thisx.int = arg1;
      thisx.final = arg2;
      thisx.this = arg3;
   }

   public final void class(float[] arg0) {
      thisx.null = arg0[0];
      thisx.int = arg0[1];
      thisx.final = arg0[2];
      thisx.this = arg0[3];
   }

   public final void do(Tuple4f arg0) {
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = arg0.final;
      thisx.this = arg0.this;
   }

   public final void null(Tuple4d arg0) {
      thisx.null = (float)arg0.null;
      thisx.int = (float)arg0.int;
      thisx.final = (float)arg0.final;
      thisx.this = (float)arg0.this;
   }

   public final void null(float[] arg0) {
      arg0[0] = thisx.null;
      arg0[1] = thisx.int;
      arg0[2] = thisx.final;
      arg0[3] = thisx.this;
   }

   public final void true(Tuple4f arg0) {
      arg0.null = thisx.null;
      arg0.int = thisx.int;
      arg0.final = thisx.final;
      arg0.this = thisx.this;
   }

   public final void class(Tuple4f arg0, Tuple4f arg1) {
      thisx.null = arg0.null + arg1.null;
      thisx.int = arg0.int + arg1.int;
      thisx.final = arg0.final + arg1.final;
      thisx.this = arg0.this + arg1.this;
   }

   public final void const(Tuple4f arg0) {
      thisx.null += arg0.null;
      thisx.int += arg0.int;
      thisx.final += arg0.final;
      thisx.this += arg0.this;
   }

   public final void null(Tuple4f arg0, Tuple4f arg1) {
      thisx.null = arg0.null - arg1.null;
      thisx.int = arg0.int - arg1.int;
      thisx.final = arg0.final - arg1.final;
      thisx.this = arg0.this - arg1.this;
   }

   public final void long(Tuple4f arg0) {
      thisx.null -= arg0.null;
      thisx.int -= arg0.int;
      thisx.final -= arg0.final;
      thisx.this -= arg0.this;
   }

   public final void class(Tuple4f arg0) {
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

   public final void const(float arg0, Tuple4f arg1) {
      thisx.null = arg0 * arg1.null;
      thisx.int = arg0 * arg1.int;
      thisx.final = arg0 * arg1.final;
      thisx.this = arg0 * arg1.this;
   }

   public final void short(float arg0) {
      thisx.null *= arg0;
      thisx.int *= arg0;
      thisx.final *= arg0;
      thisx.this *= arg0;
   }

   public final void null(float arg0, Tuple4f arg1, Tuple4f arg2) {
      thisx.null = arg0 * arg1.null + arg2.null;
      thisx.int = arg0 * arg1.int + arg2.int;
      thisx.final = arg0 * arg1.final + arg2.final;
      thisx.this = arg0 * arg1.this + arg2.this;
   }

   public final void long(float arg0, Tuple4f arg1) {
      thisx.null = arg0 * thisx.null + arg1.null;
      thisx.int = arg0 * thisx.int + arg1.int;
      thisx.final = arg0 * thisx.final + arg1.final;
      thisx.this = arg0 * thisx.this + arg1.this;
   }

   public String toString() {
      return ServerRecipeBookHelper.null("a") + thisx.null + PrestoSelectStatement.null("x0") + thisx.int + ServerRecipeBookHelper.null("`i") + thisx.final + PrestoSelectStatement.null("x0") + thisx.this + ServerRecipeBookHelper.null("`");
   }

   public boolean null(Tuple4f arg0) {
      try {
         return thisx.null == arg0.null && thisx.int == arg0.int && thisx.final == arg0.final && thisx.this == arg0.this;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public boolean equals(Object arg0) {
      try {
         Tuple4f var2 = (Tuple4f)arg0;
         return thisx.null == var2.null && thisx.int == var2.int && thisx.final == var2.final && thisx.this == var2.this;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public boolean null(Tuple4f arg0, float arg1) {
      float var3 = thisx.null - arg0.null;
      if (Float.isNaN(var3)) {
         return false;
      } else if ((var3 < 0.0F ? -var3 : var3) > arg1) {
         return false;
      } else {
         var3 = thisx.int - arg0.int;
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
   }

   public int hashCode() {
      long var1 = 1L;
      var1 = VecMathUtil.null(var1, thisx.null);
      var1 = VecMathUtil.null(var1, thisx.int);
      var1 = VecMathUtil.null(var1, thisx.final);
      var1 = VecMathUtil.null(var1, thisx.this);
      return VecMathUtil.null(var1);
   }

   public final void null(float arg0, float arg1, Tuple4f arg2) {
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

   public final void class(float arg0, Tuple4f arg1) {
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

   public final void null(float arg0, Tuple4f arg1) {
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

   public final void null(Tuple4f arg0) {
      thisx.null = Math.abs(arg0.null);
      thisx.int = Math.abs(arg0.int);
      thisx.final = Math.abs(arg0.final);
      thisx.this = Math.abs(arg0.this);
   }

   public final void null(float arg0, float arg1) {
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

   public final void do(float arg0) {
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

   public final void true(float arg0) {
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

   public void null(Tuple4f arg0, Tuple4f arg1, float arg2) {
      thisx.null = (1.0F - arg2) * arg0.null + arg2 * arg1.null;
      thisx.int = (1.0F - arg2) * arg0.int + arg2 * arg1.int;
      thisx.final = (1.0F - arg2) * arg0.final + arg2 * arg1.final;
      thisx.this = (1.0F - arg2) * arg0.this + arg2 * arg1.this;
   }

   public void null(Tuple4f arg0, float arg1) {
      thisx.null = (1.0F - arg1) * thisx.null + arg1 * arg0.null;
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

   public final float const() {
      return thisx.null;
   }

   public final void const(float arg0) {
      thisx.null = arg0;
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
