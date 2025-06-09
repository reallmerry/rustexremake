package javax.vecmath;

import com.alibaba.druid.sql.dialect.postgresql.ast.expr.PGExpr;
import java.io.Serializable;
import net.daporkchop.lib.primitive.lambda.ShortBoolObjFunction;

public class AxisAngle4f implements Serializable, Cloneable {
   public static final long const = -163246355858070601L;
   public float false;
   public float null;
   public float int;
   public float final;
   public static final double this = 1.0E-6D;

   public AxisAngle4f(float arg0, float arg1, float arg2, float arg3) {
      thisx.false = arg0;
      thisx.null = arg1;
      thisx.int = arg2;
      thisx.final = arg3;
   }

   public AxisAngle4f(float[] arg0) {
      thisx.false = arg0[0];
      thisx.null = arg0[1];
      thisx.int = arg0[2];
      thisx.final = arg0[3];
   }

   public AxisAngle4f(AxisAngle4f arg0) {
      thisx.false = arg0.false;
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = arg0.final;
   }

   public AxisAngle4f(AxisAngle4d arg0) {
      thisx.false = (float)arg0.false;
      thisx.null = (float)arg0.null;
      thisx.int = (float)arg0.int;
      thisx.final = (float)arg0.final;
   }

   public AxisAngle4f(Vector3f arg0, float arg1) {
      thisx.false = arg0.int;
      thisx.null = arg0.final;
      thisx.int = arg0.this;
      thisx.final = arg1;
   }

   public AxisAngle4f() {
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 1.0F;
      thisx.final = 0.0F;
   }

   public final void null(float arg0, float arg1, float arg2, float arg3) {
      thisx.false = arg0;
      thisx.null = arg1;
      thisx.int = arg2;
      thisx.final = arg3;
   }

   public final void class(float[] arg0) {
      thisx.false = arg0[0];
      thisx.null = arg0[1];
      thisx.int = arg0[2];
      thisx.final = arg0[3];
   }

   public final void null(AxisAngle4f arg0) {
      thisx.false = arg0.false;
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = arg0.final;
   }

   public final void null(AxisAngle4d arg0) {
      thisx.false = (float)arg0.false;
      thisx.null = (float)arg0.null;
      thisx.int = (float)arg0.int;
      thisx.final = (float)arg0.final;
   }

   public final void null(Vector3f arg0, float arg1) {
      thisx.false = arg0.int;
      thisx.null = arg0.final;
      thisx.int = arg0.this;
      thisx.final = arg1;
   }

   public final void null(float[] arg0) {
      arg0[0] = thisx.false;
      arg0[1] = thisx.null;
      arg0[2] = thisx.int;
      arg0[3] = thisx.final;
   }

   public final void null(Quat4f arg0) {
      double var2 = (double)(arg0.null * arg0.null + arg0.int * arg0.int + arg0.final * arg0.final);
      if (var2 > 1.0E-6D) {
         var2 = Math.sqrt(var2);
         double var4 = 1.0D / var2;
         thisx.false = (float)((double)arg0.null * var4);
         thisx.null = (float)((double)arg0.int * var4);
         thisx.int = (float)((double)arg0.final * var4);
         thisx.final = (float)(2.0D * Math.atan2(var2, (double)arg0.this));
      } else {
         thisx.false = 0.0F;
         thisx.null = 1.0F;
         thisx.int = 0.0F;
         thisx.final = 0.0F;
      }

   }

   public final void null(Quat4d arg0) {
      double var2 = arg0.null * arg0.null + arg0.int * arg0.int + arg0.final * arg0.final;
      if (var2 > 1.0E-6D) {
         var2 = Math.sqrt(var2);
         double var4 = 1.0D / var2;
         thisx.false = (float)(arg0.null * var4);
         thisx.null = (float)(arg0.int * var4);
         thisx.int = (float)(arg0.final * var4);
         thisx.final = (float)(2.0D * Math.atan2(var2, arg0.this));
      } else {
         thisx.false = 0.0F;
         thisx.null = 1.0F;
         thisx.int = 0.0F;
         thisx.final = 0.0F;
      }

   }

   public final void null(Matrix4f arg0) {
      Matrix3f var2 = new Matrix3f();
      arg0.true(var2);
      thisx.false = var2.int - var2.false;
      thisx.null = var2.long - var2.null;
      thisx.int = var2.new - var2.short;
      double var3 = (double)(thisx.false * thisx.false + thisx.null * thisx.null + thisx.int * thisx.int);
      if (var3 > 1.0E-6D) {
         var3 = Math.sqrt(var3);
         double var5 = 0.5D * var3;
         double var7 = 0.5D * ((double)(var2.case + var2.const + var2.final) - 1.0D);
         thisx.final = (float)Math.atan2(var5, var7);
         double var9 = 1.0D / var3;
         thisx.false = (float)((double)thisx.false * var9);
         thisx.null = (float)((double)thisx.null * var9);
         thisx.int = (float)((double)thisx.int * var9);
      } else {
         thisx.false = 0.0F;
         thisx.null = 1.0F;
         thisx.int = 0.0F;
         thisx.final = 0.0F;
      }

   }

   public final void null(Matrix4d arg0) {
      Matrix3d var2 = new Matrix3d();
      arg0.true(var2);
      thisx.false = (float)(var2.int - var2.false);
      thisx.null = (float)(var2.long - var2.null);
      thisx.int = (float)(var2.new - var2.short);
      double var3 = (double)(thisx.false * thisx.false + thisx.null * thisx.null + thisx.int * thisx.int);
      if (var3 > 1.0E-6D) {
         var3 = Math.sqrt(var3);
         double var5 = 0.5D * var3;
         double var7 = 0.5D * (var2.case + var2.const + var2.final - 1.0D);
         thisx.final = (float)Math.atan2(var5, var7);
         double var9 = 1.0D / var3;
         thisx.false = (float)((double)thisx.false * var9);
         thisx.null = (float)((double)thisx.null * var9);
         thisx.int = (float)((double)thisx.int * var9);
      } else {
         thisx.false = 0.0F;
         thisx.null = 1.0F;
         thisx.int = 0.0F;
         thisx.final = 0.0F;
      }

   }

   public final void null(Matrix3f arg0) {
      thisx.false = arg0.int - arg0.false;
      thisx.null = arg0.long - arg0.null;
      thisx.int = arg0.new - arg0.short;
      double var2 = (double)(thisx.false * thisx.false + thisx.null * thisx.null + thisx.int * thisx.int);
      if (var2 > 1.0E-6D) {
         var2 = Math.sqrt(var2);
         double var4 = 0.5D * var2;
         double var6 = 0.5D * ((double)(arg0.case + arg0.const + arg0.final) - 1.0D);
         thisx.final = (float)Math.atan2(var4, var6);
         double var8 = 1.0D / var2;
         thisx.false = (float)((double)thisx.false * var8);
         thisx.null = (float)((double)thisx.null * var8);
         thisx.int = (float)((double)thisx.int * var8);
      } else {
         thisx.false = 0.0F;
         thisx.null = 1.0F;
         thisx.int = 0.0F;
         thisx.final = 0.0F;
      }

   }

   public final void null(Matrix3d arg0) {
      thisx.false = (float)(arg0.int - arg0.false);
      thisx.null = (float)(arg0.long - arg0.null);
      thisx.int = (float)(arg0.new - arg0.short);
      double var2 = (double)(thisx.false * thisx.false + thisx.null * thisx.null + thisx.int * thisx.int);
      if (var2 > 1.0E-6D) {
         var2 = Math.sqrt(var2);
         double var4 = 0.5D * var2;
         double var6 = 0.5D * (arg0.case + arg0.const + arg0.final - 1.0D);
         thisx.final = (float)Math.atan2(var4, var6);
         double var8 = 1.0D / var2;
         thisx.false = (float)((double)thisx.false * var8);
         thisx.null = (float)((double)thisx.null * var8);
         thisx.int = (float)((double)thisx.int * var8);
      } else {
         thisx.false = 0.0F;
         thisx.null = 1.0F;
         thisx.int = 0.0F;
         thisx.final = 0.0F;
      }

   }

   public String toString() {
      return ShortBoolObjFunction.null("5") + thisx.false + PGExpr.null("2\n") + thisx.null + ShortBoolObjFunction.null("L=") + thisx.int + PGExpr.null("2\n") + thisx.final + ShortBoolObjFunction.null("4");
   }

   public boolean null(AxisAngle4f arg0) {
      try {
         return thisx.false == arg0.false && thisx.null == arg0.null && thisx.int == arg0.int && thisx.final == arg0.final;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public boolean equals(Object arg0) {
      try {
         AxisAngle4f var2 = (AxisAngle4f)arg0;
         return thisx.false == var2.false && thisx.null == var2.null && thisx.int == var2.int && thisx.final == var2.final;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public boolean null(AxisAngle4f arg0, float arg1) {
      float var3 = thisx.false - arg0.false;
      if ((var3 < 0.0F ? -var3 : var3) > arg1) {
         return false;
      } else {
         var3 = thisx.null - arg0.null;
         if ((var3 < 0.0F ? -var3 : var3) > arg1) {
            return false;
         } else {
            var3 = thisx.int - arg0.int;
            if ((var3 < 0.0F ? -var3 : var3) > arg1) {
               return false;
            } else {
               var3 = thisx.final - arg0.final;
               return !((var3 < 0.0F ? -var3 : var3) > arg1);
            }
         }
      }
   }

   public int hashCode() {
      long var1 = 1L;
      var1 = VecMathUtil.null(var1, thisx.false);
      var1 = VecMathUtil.null(var1, thisx.null);
      var1 = VecMathUtil.null(var1, thisx.int);
      var1 = VecMathUtil.null(var1, thisx.final);
      return VecMathUtil.null(var1);
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public final float const() {
      return thisx.final;
   }

   public final void const(float arg0) {
      thisx.final = arg0;
   }

   public final float long() {
      return thisx.false;
   }

   public final void long(float arg0) {
      thisx.false = arg0;
   }

   public final float class() {
      return thisx.null;
   }

   public final void class(float arg0) {
      thisx.null = arg0;
   }

   public final float null() {
      return thisx.int;
   }

   public final void null(float arg0) {
      thisx.int = arg0;
   }
}
