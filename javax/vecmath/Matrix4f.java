package javax.vecmath;

import android.location.GpsSatellite;
import breeze.linalg.diag;
import java.io.Serializable;

public class Matrix4f implements Serializable, Cloneable {
   public static final long for = -8405036035410109353L;
   public float char;
   public float float;
   public float break;
   public float catch;
   public float do;
   public float else;
   public float goto;
   public float case;
   public float short;
   public float long;
   public float new;
   public float const;
   public float false;
   public float null;
   public float int;
   public float final;
   private static final double this = 1.0E-8D;

   public Matrix4f(float arg0, float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8, float arg9, float arg10, float arg11, float arg12, float arg13, float arg14, float arg15) {
      thisx.char = arg0;
      thisx.float = arg1;
      thisx.break = arg2;
      thisx.catch = arg3;
      thisx.do = arg4;
      thisx.else = arg5;
      thisx.goto = arg6;
      thisx.case = arg7;
      thisx.short = arg8;
      thisx.long = arg9;
      thisx.new = arg10;
      thisx.const = arg11;
      thisx.false = arg12;
      thisx.null = arg13;
      thisx.int = arg14;
      thisx.final = arg15;
   }

   public Matrix4f(float[] arg0) {
      thisx.char = arg0[0];
      thisx.float = arg0[1];
      thisx.break = arg0[2];
      thisx.catch = arg0[3];
      thisx.do = arg0[4];
      thisx.else = arg0[5];
      thisx.goto = arg0[6];
      thisx.case = arg0[7];
      thisx.short = arg0[8];
      thisx.long = arg0[9];
      thisx.new = arg0[10];
      thisx.const = arg0[11];
      thisx.false = arg0[12];
      thisx.null = arg0[13];
      thisx.int = arg0[14];
      thisx.final = arg0[15];
   }

   public Matrix4f(Quat4f arg0, Vector3f arg1, float arg2) {
      thisx.char = (float)((double)arg2 * (1.0D - 2.0D * (double)arg0.int * (double)arg0.int - 2.0D * (double)arg0.final * (double)arg0.final));
      thisx.do = (float)((double)arg2 * 2.0D * (double)(arg0.null * arg0.int + arg0.this * arg0.final));
      thisx.short = (float)((double)arg2 * 2.0D * (double)(arg0.null * arg0.final - arg0.this * arg0.int));
      thisx.float = (float)((double)arg2 * 2.0D * (double)(arg0.null * arg0.int - arg0.this * arg0.final));
      thisx.else = (float)((double)arg2 * (1.0D - 2.0D * (double)arg0.null * (double)arg0.null - 2.0D * (double)arg0.final * (double)arg0.final));
      thisx.long = (float)((double)arg2 * 2.0D * (double)(arg0.int * arg0.final + arg0.this * arg0.null));
      thisx.break = (float)((double)arg2 * 2.0D * (double)(arg0.null * arg0.final + arg0.this * arg0.int));
      thisx.goto = (float)((double)arg2 * 2.0D * (double)(arg0.int * arg0.final - arg0.this * arg0.null));
      thisx.new = (float)((double)arg2 * (1.0D - 2.0D * (double)arg0.null * (double)arg0.null - 2.0D * (double)arg0.int * (double)arg0.int));
      thisx.catch = arg1.int;
      thisx.case = arg1.final;
      thisx.const = arg1.this;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public Matrix4f(Matrix4d arg0) {
      thisx.char = (float)arg0.char;
      thisx.float = (float)arg0.float;
      thisx.break = (float)arg0.break;
      thisx.catch = (float)arg0.catch;
      thisx.do = (float)arg0.do;
      thisx.else = (float)arg0.else;
      thisx.goto = (float)arg0.goto;
      thisx.case = (float)arg0.case;
      thisx.short = (float)arg0.short;
      thisx.long = (float)arg0.long;
      thisx.new = (float)arg0.new;
      thisx.const = (float)arg0.const;
      thisx.false = (float)arg0.false;
      thisx.null = (float)arg0.null;
      thisx.int = (float)arg0.int;
      thisx.final = (float)arg0.final;
   }

   public Matrix4f(Matrix4f arg0) {
      thisx.char = arg0.char;
      thisx.float = arg0.float;
      thisx.break = arg0.break;
      thisx.catch = arg0.catch;
      thisx.do = arg0.do;
      thisx.else = arg0.else;
      thisx.goto = arg0.goto;
      thisx.case = arg0.case;
      thisx.short = arg0.short;
      thisx.long = arg0.long;
      thisx.new = arg0.new;
      thisx.const = arg0.const;
      thisx.false = arg0.false;
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = arg0.final;
   }

   public Matrix4f(Matrix3f arg0, Vector3f arg1, float arg2) {
      thisx.char = arg0.case * arg2;
      thisx.float = arg0.short * arg2;
      thisx.break = arg0.long * arg2;
      thisx.catch = arg1.int;
      thisx.do = arg0.new * arg2;
      thisx.else = arg0.const * arg2;
      thisx.goto = arg0.false * arg2;
      thisx.case = arg1.final;
      thisx.short = arg0.null * arg2;
      thisx.long = arg0.int * arg2;
      thisx.new = arg0.final * arg2;
      thisx.const = arg1.this;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public Matrix4f() {
      thisx.char = 0.0F;
      thisx.float = 0.0F;
      thisx.break = 0.0F;
      thisx.catch = 0.0F;
      thisx.do = 0.0F;
      thisx.else = 0.0F;
      thisx.goto = 0.0F;
      thisx.case = 0.0F;
      thisx.short = 0.0F;
      thisx.long = 0.0F;
      thisx.new = 0.0F;
      thisx.const = 0.0F;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 0.0F;
   }

   public String toString() {
      return thisx.char + diag.null("r\u0018") + thisx.float + GpsSatellite.null("_$") + thisx.break + diag.null("r\u0018") + thisx.catch + "\n" + thisx.do + GpsSatellite.null("_$") + thisx.else + diag.null("r\u0018") + thisx.goto + GpsSatellite.null("_$") + thisx.case + "\n" + thisx.short + diag.null("r\u0018") + thisx.long + GpsSatellite.null("_$") + thisx.new + diag.null("r\u0018") + thisx.const + "\n" + thisx.false + GpsSatellite.null("_$") + thisx.null + diag.null("r\u0018") + thisx.int + GpsSatellite.null("_$") + thisx.final + "\n";
   }

   public final void true() {
      thisx.char = 1.0F;
      thisx.float = 0.0F;
      thisx.break = 0.0F;
      thisx.catch = 0.0F;
      thisx.do = 0.0F;
      thisx.else = 1.0F;
      thisx.goto = 0.0F;
      thisx.case = 0.0F;
      thisx.short = 0.0F;
      thisx.long = 0.0F;
      thisx.new = 1.0F;
      thisx.const = 0.0F;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void null(int arg0, int arg1, float arg2) {
      switch(arg0) {
      case 0:
         switch(arg1) {
         case 0:
            thisx.char = arg2;
            return;
         case 1:
            thisx.float = arg2;
            return;
         case 2:
            thisx.break = arg2;
            return;
         case 3:
            thisx.catch = arg2;
            return;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(diag.null("u?L,Q&\f8\b")));
         }
      case 1:
         switch(arg1) {
         case 0:
            thisx.do = arg2;
            return;
         case 1:
            thisx.else = arg2;
            return;
         case 2:
            thisx.goto = arg2;
            return;
         case 3:
            thisx.case = arg2;
            return;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(GpsSatellite.null("I\u0012p\u0001m\u000b0\u00154")));
         }
      case 2:
         switch(arg1) {
         case 0:
            thisx.short = arg2;
            return;
         case 1:
            thisx.long = arg2;
            return;
         case 2:
            thisx.new = arg2;
            return;
         case 3:
            thisx.const = arg2;
            return;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(diag.null("u?L,Q&\f8\b")));
         }
      case 3:
         switch(arg1) {
         case 0:
            thisx.false = arg2;
            return;
         case 1:
            thisx.null = arg2;
            return;
         case 2:
            thisx.int = arg2;
            return;
         case 3:
            thisx.final = arg2;
            return;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(GpsSatellite.null("I\u0012p\u0001m\u000b0\u00154")));
         }
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(diag.null("u?L,Q&\f8\b")));
      }
   }

   public final float null(int arg0, int arg1) {
      switch(arg0) {
      case 0:
         switch(arg1) {
         case 0:
            return thisx.char;
         case 1:
            return thisx.float;
         case 2:
            return thisx.break;
         case 3:
            return thisx.catch;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(GpsSatellite.null("I\u0012p\u0001m\u000b0\u00155")));
         }
      case 1:
         switch(arg1) {
         case 0:
            return thisx.do;
         case 1:
            return thisx.else;
         case 2:
            return thisx.goto;
         case 3:
            return thisx.case;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(GpsSatellite.null("I\u0012p\u0001m\u000b0\u00155")));
         }
      case 2:
         switch(arg1) {
         case 0:
            return thisx.short;
         case 1:
            return thisx.long;
         case 2:
            return thisx.new;
         case 3:
            return thisx.const;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(GpsSatellite.null("I\u0012p\u0001m\u000b0\u00155")));
         }
      case 3:
         switch(arg1) {
         case 0:
            return thisx.false;
         case 1:
            return thisx.null;
         case 2:
            return thisx.int;
         case 3:
            return thisx.final;
         }
      }

      throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(GpsSatellite.null("I\u0012p\u0001m\u000b0\u00155")));
   }

   public final void const(int arg0, Vector4f arg1) {
      if (arg0 == 0) {
         arg1.null = thisx.char;
         arg1.int = thisx.float;
         arg1.final = thisx.break;
         arg1.this = thisx.catch;
      } else if (arg0 == 1) {
         arg1.null = thisx.do;
         arg1.int = thisx.else;
         arg1.final = thisx.goto;
         arg1.this = thisx.case;
      } else if (arg0 == 2) {
         arg1.null = thisx.short;
         arg1.int = thisx.long;
         arg1.final = thisx.new;
         arg1.this = thisx.const;
      } else {
         if (arg0 != 3) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(diag.null("u?L,Q&\f8\n")));
         }

         arg1.null = thisx.false;
         arg1.int = thisx.null;
         arg1.final = thisx.int;
         arg1.this = thisx.final;
      }

   }

   public final void const(int arg0, float[] arg1) {
      if (arg0 == 0) {
         arg1[0] = thisx.char;
         arg1[1] = thisx.float;
         arg1[2] = thisx.break;
         arg1[3] = thisx.catch;
      } else if (arg0 == 1) {
         arg1[0] = thisx.do;
         arg1[1] = thisx.else;
         arg1[2] = thisx.goto;
         arg1[3] = thisx.case;
      } else if (arg0 == 2) {
         arg1[0] = thisx.short;
         arg1[1] = thisx.long;
         arg1[2] = thisx.new;
         arg1[3] = thisx.const;
      } else {
         if (arg0 != 3) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(GpsSatellite.null("I\u0012p\u0001m\u000b0\u00156")));
         }

         arg1[0] = thisx.false;
         arg1[1] = thisx.null;
         arg1[2] = thisx.int;
         arg1[3] = thisx.final;
      }

   }

   public final void long(int arg0, Vector4f arg1) {
      if (arg0 == 0) {
         arg1.null = thisx.char;
         arg1.int = thisx.do;
         arg1.final = thisx.short;
         arg1.this = thisx.false;
      } else if (arg0 == 1) {
         arg1.null = thisx.float;
         arg1.int = thisx.else;
         arg1.final = thisx.long;
         arg1.this = thisx.null;
      } else if (arg0 == 2) {
         arg1.null = thisx.break;
         arg1.int = thisx.goto;
         arg1.final = thisx.new;
         arg1.this = thisx.int;
      } else {
         if (arg0 != 3) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(diag.null("u?L,Q&\f8\f")));
         }

         arg1.null = thisx.catch;
         arg1.int = thisx.case;
         arg1.final = thisx.const;
         arg1.this = thisx.final;
      }

   }

   public final void long(int arg0, float[] arg1) {
      if (arg0 == 0) {
         arg1[0] = thisx.char;
         arg1[1] = thisx.do;
         arg1[2] = thisx.short;
         arg1[3] = thisx.false;
      } else if (arg0 == 1) {
         arg1[0] = thisx.float;
         arg1[1] = thisx.else;
         arg1[2] = thisx.long;
         arg1[3] = thisx.null;
      } else if (arg0 == 2) {
         arg1[0] = thisx.break;
         arg1[1] = thisx.goto;
         arg1[2] = thisx.new;
         arg1[3] = thisx.int;
      } else {
         if (arg0 != 3) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(GpsSatellite.null("I\u0012p\u0001m\u000b0\u00150")));
         }

         arg1[0] = thisx.catch;
         arg1[1] = thisx.case;
         arg1[2] = thisx.const;
         arg1[3] = thisx.final;
      }

   }

   public final void break(float arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      thisx.char = (float)(var2[0] * (double)arg0);
      thisx.float = (float)(var2[1] * (double)arg0);
      thisx.break = (float)(var2[2] * (double)arg0);
      thisx.do = (float)(var2[3] * (double)arg0);
      thisx.else = (float)(var2[4] * (double)arg0);
      thisx.goto = (float)(var2[5] * (double)arg0);
      thisx.short = (float)(var2[6] * (double)arg0);
      thisx.long = (float)(var2[7] * (double)arg0);
      thisx.new = (float)(var2[8] * (double)arg0);
   }

   public final void long(Matrix3d arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      arg0.case = var2[0];
      arg0.short = var2[1];
      arg0.long = var2[2];
      arg0.new = var2[3];
      arg0.const = var2[4];
      arg0.false = var2[5];
      arg0.null = var2[6];
      arg0.int = var2[7];
      arg0.final = var2[8];
   }

   public final void true(Matrix3f arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      arg0.case = (float)var2[0];
      arg0.short = (float)var2[1];
      arg0.long = (float)var2[2];
      arg0.new = (float)var2[3];
      arg0.const = (float)var2[4];
      arg0.false = (float)var2[5];
      arg0.null = (float)var2[6];
      arg0.int = (float)var2[7];
      arg0.final = (float)var2[8];
   }

   public final float null(Matrix3f arg0, Vector3f arg1) {
      double[] var3 = new double[9];
      double[] var4 = new double[3];
      thisx.null(var4, var3);
      arg0.case = (float)var3[0];
      arg0.short = (float)var3[1];
      arg0.long = (float)var3[2];
      arg0.new = (float)var3[3];
      arg0.const = (float)var3[4];
      arg0.false = (float)var3[5];
      arg0.null = (float)var3[6];
      arg0.int = (float)var3[7];
      arg0.final = (float)var3[8];
      arg1.int = thisx.catch;
      arg1.final = thisx.case;
      arg1.this = thisx.const;
      return (float)Matrix3d.null(var4);
   }

   public final void long(Quat4f arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      double var4 = 0.25D * (1.0D + var2[0] + var2[4] + var2[8]);
      if (!((var4 < 0.0D ? -var4 : var4) < 1.0E-30D)) {
         arg0.this = (float)Math.sqrt(var4);
         var4 = 0.25D / (double)arg0.this;
         arg0.null = (float)((var2[7] - var2[5]) * var4);
         arg0.int = (float)((var2[2] - var2[6]) * var4);
         arg0.final = (float)((var2[3] - var2[1]) * var4);
      } else {
         arg0.this = 0.0F;
         var4 = -0.5D * (var2[4] + var2[8]);
         if (!((var4 < 0.0D ? -var4 : var4) < 1.0E-30D)) {
            arg0.null = (float)Math.sqrt(var4);
            var4 = 0.5D / (double)arg0.null;
            arg0.int = (float)(var2[3] * var4);
            arg0.final = (float)(var2[6] * var4);
         } else {
            arg0.null = 0.0F;
            var4 = 0.5D * (1.0D - var2[8]);
            if (!((var4 < 0.0D ? -var4 : var4) < 1.0E-30D)) {
               arg0.int = (float)Math.sqrt(var4);
               arg0.final = (float)(var2[7] / (2.0D * (double)arg0.int));
            } else {
               arg0.int = 0.0F;
               arg0.final = 1.0F;
            }
         }
      }
   }

   public final void const(Vector3f arg0) {
      arg0.int = thisx.catch;
      arg0.final = thisx.case;
      arg0.this = thisx.const;
   }

   public final void const(Matrix3f arg0) {
      arg0.case = thisx.char;
      arg0.short = thisx.float;
      arg0.long = thisx.break;
      arg0.new = thisx.do;
      arg0.const = thisx.else;
      arg0.false = thisx.goto;
      arg0.null = thisx.short;
      arg0.int = thisx.long;
      arg0.final = thisx.new;
   }

   public final float enum() {
      double[] var1 = new double[9];
      double[] var2 = new double[3];
      thisx.null(var2, var1);
      return (float)Matrix3d.null(var2);
   }

   public final void long(Matrix3f arg0) {
      thisx.char = arg0.case;
      thisx.float = arg0.short;
      thisx.break = arg0.long;
      thisx.do = arg0.new;
      thisx.else = arg0.const;
      thisx.goto = arg0.false;
      thisx.short = arg0.null;
      thisx.long = arg0.int;
      thisx.new = arg0.final;
   }

   public final void class(int arg0, float arg1, float arg2, float arg3, float arg4) {
      switch(arg0) {
      case 0:
         thisx.char = arg1;
         thisx.float = arg2;
         thisx.break = arg3;
         thisx.catch = arg4;
         break;
      case 1:
         thisx.do = arg1;
         thisx.else = arg2;
         thisx.goto = arg3;
         thisx.case = arg4;
         break;
      case 2:
         thisx.short = arg1;
         thisx.long = arg2;
         thisx.new = arg3;
         thisx.const = arg4;
         break;
      case 3:
         thisx.false = arg1;
         thisx.null = arg2;
         thisx.int = arg3;
         thisx.final = arg4;
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(diag.null("u?L,Q&\f8\u000e")));
      }

   }

   public final void class(int arg0, Vector4f arg1) {
      switch(arg0) {
      case 0:
         thisx.char = arg1.null;
         thisx.float = arg1.int;
         thisx.break = arg1.final;
         thisx.catch = arg1.this;
         break;
      case 1:
         thisx.do = arg1.null;
         thisx.else = arg1.int;
         thisx.goto = arg1.final;
         thisx.case = arg1.this;
         break;
      case 2:
         thisx.short = arg1.null;
         thisx.long = arg1.int;
         thisx.new = arg1.final;
         thisx.const = arg1.this;
         break;
      case 3:
         thisx.false = arg1.null;
         thisx.null = arg1.int;
         thisx.int = arg1.final;
         thisx.final = arg1.this;
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(GpsSatellite.null("I\u0012p\u0001m\u000b0\u00152")));
      }

   }

   public final void class(int arg0, float[] arg1) {
      switch(arg0) {
      case 0:
         thisx.char = arg1[0];
         thisx.float = arg1[1];
         thisx.break = arg1[2];
         thisx.catch = arg1[3];
         break;
      case 1:
         thisx.do = arg1[0];
         thisx.else = arg1[1];
         thisx.goto = arg1[2];
         thisx.case = arg1[3];
         break;
      case 2:
         thisx.short = arg1[0];
         thisx.long = arg1[1];
         thisx.new = arg1[2];
         thisx.const = arg1[3];
         break;
      case 3:
         thisx.false = arg1[0];
         thisx.null = arg1[1];
         thisx.int = arg1[2];
         thisx.final = arg1[3];
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(diag.null("u?L,Q&\f8\u000e")));
      }

   }

   public final void null(int arg0, float arg1, float arg2, float arg3, float arg4) {
      switch(arg0) {
      case 0:
         thisx.char = arg1;
         thisx.do = arg2;
         thisx.short = arg3;
         thisx.false = arg4;
         break;
      case 1:
         thisx.float = arg1;
         thisx.else = arg2;
         thisx.long = arg3;
         thisx.null = arg4;
         break;
      case 2:
         thisx.break = arg1;
         thisx.goto = arg2;
         thisx.new = arg3;
         thisx.int = arg4;
         break;
      case 3:
         thisx.catch = arg1;
         thisx.case = arg2;
         thisx.const = arg3;
         thisx.final = arg4;
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(GpsSatellite.null("I\u0012p\u0001m\u000b0\u0015=")));
      }

   }

   public final void null(int arg0, Vector4f arg1) {
      switch(arg0) {
      case 0:
         thisx.char = arg1.null;
         thisx.do = arg1.int;
         thisx.short = arg1.final;
         thisx.false = arg1.this;
         break;
      case 1:
         thisx.float = arg1.null;
         thisx.else = arg1.int;
         thisx.long = arg1.final;
         thisx.null = arg1.this;
         break;
      case 2:
         thisx.break = arg1.null;
         thisx.goto = arg1.int;
         thisx.new = arg1.final;
         thisx.int = arg1.this;
         break;
      case 3:
         thisx.catch = arg1.null;
         thisx.case = arg1.int;
         thisx.const = arg1.final;
         thisx.final = arg1.this;
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(diag.null("u?L,Q&\f8\u0001")));
      }

   }

   public final void null(int arg0, float[] arg1) {
      switch(arg0) {
      case 0:
         thisx.char = arg1[0];
         thisx.do = arg1[1];
         thisx.short = arg1[2];
         thisx.false = arg1[3];
         break;
      case 1:
         thisx.float = arg1[0];
         thisx.else = arg1[1];
         thisx.long = arg1[2];
         thisx.null = arg1[3];
         break;
      case 2:
         thisx.break = arg1[0];
         thisx.goto = arg1[1];
         thisx.new = arg1[2];
         thisx.int = arg1[3];
         break;
      case 3:
         thisx.catch = arg1[0];
         thisx.case = arg1[1];
         thisx.const = arg1[2];
         thisx.final = arg1[3];
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(GpsSatellite.null("I\u0012p\u0001m\u000b0\u0015=")));
      }

   }

   public final void super(float arg0) {
      thisx.char += arg0;
      thisx.float += arg0;
      thisx.break += arg0;
      thisx.catch += arg0;
      thisx.do += arg0;
      thisx.else += arg0;
      thisx.goto += arg0;
      thisx.case += arg0;
      thisx.short += arg0;
      thisx.long += arg0;
      thisx.new += arg0;
      thisx.const += arg0;
      thisx.false += arg0;
      thisx.null += arg0;
      thisx.int += arg0;
      thisx.final += arg0;
   }

   public final void class(float arg0, Matrix4f arg1) {
      thisx.char = arg1.char + arg0;
      thisx.float = arg1.float + arg0;
      thisx.break = arg1.break + arg0;
      thisx.catch = arg1.catch + arg0;
      thisx.do = arg1.do + arg0;
      thisx.else = arg1.else + arg0;
      thisx.goto = arg1.goto + arg0;
      thisx.case = arg1.case + arg0;
      thisx.short = arg1.short + arg0;
      thisx.long = arg1.long + arg0;
      thisx.new = arg1.new + arg0;
      thisx.const = arg1.const + arg0;
      thisx.false = arg1.false + arg0;
      thisx.null = arg1.null + arg0;
      thisx.int = arg1.int + arg0;
      thisx.final = arg1.final + arg0;
   }

   public final void do(Matrix4f arg0, Matrix4f arg1) {
      thisx.char = arg0.char + arg1.char;
      thisx.float = arg0.float + arg1.float;
      thisx.break = arg0.break + arg1.break;
      thisx.catch = arg0.catch + arg1.catch;
      thisx.do = arg0.do + arg1.do;
      thisx.else = arg0.else + arg1.else;
      thisx.goto = arg0.goto + arg1.goto;
      thisx.case = arg0.case + arg1.case;
      thisx.short = arg0.short + arg1.short;
      thisx.long = arg0.long + arg1.long;
      thisx.new = arg0.new + arg1.new;
      thisx.const = arg0.const + arg1.const;
      thisx.false = arg0.false + arg1.false;
      thisx.null = arg0.null + arg1.null;
      thisx.int = arg0.int + arg1.int;
      thisx.final = arg0.final + arg1.final;
   }

   public final void this(Matrix4f arg0) {
      thisx.char += arg0.char;
      thisx.float += arg0.float;
      thisx.break += arg0.break;
      thisx.catch += arg0.catch;
      thisx.do += arg0.do;
      thisx.else += arg0.else;
      thisx.goto += arg0.goto;
      thisx.case += arg0.case;
      thisx.short += arg0.short;
      thisx.long += arg0.long;
      thisx.new += arg0.new;
      thisx.const += arg0.const;
      thisx.false += arg0.false;
      thisx.null += arg0.null;
      thisx.int += arg0.int;
      thisx.final += arg0.final;
   }

   public final void true(Matrix4f arg0, Matrix4f arg1) {
      thisx.char = arg0.char - arg1.char;
      thisx.float = arg0.float - arg1.float;
      thisx.break = arg0.break - arg1.break;
      thisx.catch = arg0.catch - arg1.catch;
      thisx.do = arg0.do - arg1.do;
      thisx.else = arg0.else - arg1.else;
      thisx.goto = arg0.goto - arg1.goto;
      thisx.case = arg0.case - arg1.case;
      thisx.short = arg0.short - arg1.short;
      thisx.long = arg0.long - arg1.long;
      thisx.new = arg0.new - arg1.new;
      thisx.const = arg0.const - arg1.const;
      thisx.false = arg0.false - arg1.false;
      thisx.null = arg0.null - arg1.null;
      thisx.int = arg0.int - arg1.int;
      thisx.final = arg0.final - arg1.final;
   }

   public final void short(Matrix4f arg0) {
      thisx.char -= arg0.char;
      thisx.float -= arg0.float;
      thisx.break -= arg0.break;
      thisx.catch -= arg0.catch;
      thisx.do -= arg0.do;
      thisx.else -= arg0.else;
      thisx.goto -= arg0.goto;
      thisx.case -= arg0.case;
      thisx.short -= arg0.short;
      thisx.long -= arg0.long;
      thisx.new -= arg0.new;
      thisx.const -= arg0.const;
      thisx.false -= arg0.false;
      thisx.null -= arg0.null;
      thisx.int -= arg0.int;
      thisx.final -= arg0.final;
   }

   public final void const() {
      float var1 = thisx.do;
      thisx.do = thisx.float;
      thisx.float = var1;
      var1 = thisx.short;
      thisx.short = thisx.break;
      thisx.break = var1;
      var1 = thisx.false;
      thisx.false = thisx.catch;
      thisx.catch = var1;
      var1 = thisx.long;
      thisx.long = thisx.goto;
      thisx.goto = var1;
      var1 = thisx.null;
      thisx.null = thisx.case;
      thisx.case = var1;
      var1 = thisx.int;
      thisx.int = thisx.const;
      thisx.const = var1;
   }

   public final void do(Matrix4f arg0) {
      if (thisx != arg0) {
         thisx.char = arg0.char;
         thisx.float = arg0.do;
         thisx.break = arg0.short;
         thisx.catch = arg0.false;
         thisx.do = arg0.float;
         thisx.else = arg0.else;
         thisx.goto = arg0.long;
         thisx.case = arg0.null;
         thisx.short = arg0.break;
         thisx.long = arg0.goto;
         thisx.new = arg0.new;
         thisx.const = arg0.int;
         thisx.false = arg0.catch;
         thisx.null = arg0.case;
         thisx.int = arg0.const;
         thisx.final = arg0.final;
      } else {
         thisx.const();
      }

   }

   public final void class(Quat4f arg0) {
      thisx.char = 1.0F - 2.0F * arg0.int * arg0.int - 2.0F * arg0.final * arg0.final;
      thisx.do = 2.0F * (arg0.null * arg0.int + arg0.this * arg0.final);
      thisx.short = 2.0F * (arg0.null * arg0.final - arg0.this * arg0.int);
      thisx.float = 2.0F * (arg0.null * arg0.int - arg0.this * arg0.final);
      thisx.else = 1.0F - 2.0F * arg0.null * arg0.null - 2.0F * arg0.final * arg0.final;
      thisx.long = 2.0F * (arg0.int * arg0.final + arg0.this * arg0.null);
      thisx.break = 2.0F * (arg0.null * arg0.final + arg0.this * arg0.int);
      thisx.goto = 2.0F * (arg0.int * arg0.final - arg0.this * arg0.null);
      thisx.new = 1.0F - 2.0F * arg0.null * arg0.null - 2.0F * arg0.int * arg0.int;
      thisx.catch = 0.0F;
      thisx.case = 0.0F;
      thisx.const = 0.0F;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void class(AxisAngle4f arg0) {
      float var2 = (float)Math.sqrt((double)(arg0.false * arg0.false + arg0.null * arg0.null + arg0.int * arg0.int));
      if ((double)var2 < 1.0E-8D) {
         thisx.char = 1.0F;
         thisx.float = 0.0F;
         thisx.break = 0.0F;
         thisx.do = 0.0F;
         thisx.else = 1.0F;
         thisx.goto = 0.0F;
         thisx.short = 0.0F;
         thisx.long = 0.0F;
         thisx.new = 1.0F;
      } else {
         var2 = 1.0F / var2;
         float var3 = arg0.false * var2;
         float var4 = arg0.null * var2;
         float var5 = arg0.int * var2;
         float var6 = (float)Math.sin((double)arg0.final);
         float var7 = (float)Math.cos((double)arg0.final);
         float var8 = 1.0F - var7;
         float var9 = var3 * var5;
         float var10 = var3 * var4;
         float var11 = var4 * var5;
         thisx.char = var8 * var3 * var3 + var7;
         thisx.float = var8 * var10 - var6 * var5;
         thisx.break = var8 * var9 + var6 * var4;
         thisx.do = var8 * var10 + var6 * var5;
         thisx.else = var8 * var4 * var4 + var7;
         thisx.goto = var8 * var11 - var6 * var3;
         thisx.short = var8 * var9 - var6 * var4;
         thisx.long = var8 * var11 + var6 * var3;
         thisx.new = var8 * var5 * var5 + var7;
      }

      thisx.catch = 0.0F;
      thisx.case = 0.0F;
      thisx.const = 0.0F;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void class(Quat4d arg0) {
      thisx.char = (float)(1.0D - 2.0D * arg0.int * arg0.int - 2.0D * arg0.final * arg0.final);
      thisx.do = (float)(2.0D * (arg0.null * arg0.int + arg0.this * arg0.final));
      thisx.short = (float)(2.0D * (arg0.null * arg0.final - arg0.this * arg0.int));
      thisx.float = (float)(2.0D * (arg0.null * arg0.int - arg0.this * arg0.final));
      thisx.else = (float)(1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.final * arg0.final);
      thisx.long = (float)(2.0D * (arg0.int * arg0.final + arg0.this * arg0.null));
      thisx.break = (float)(2.0D * (arg0.null * arg0.final + arg0.this * arg0.int));
      thisx.goto = (float)(2.0D * (arg0.int * arg0.final - arg0.this * arg0.null));
      thisx.new = (float)(1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.int * arg0.int);
      thisx.catch = 0.0F;
      thisx.case = 0.0F;
      thisx.const = 0.0F;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void null(AxisAngle4d arg0) {
      double var2 = Math.sqrt(arg0.false * arg0.false + arg0.null * arg0.null + arg0.int * arg0.int);
      if (var2 < 1.0E-8D) {
         thisx.char = 1.0F;
         thisx.float = 0.0F;
         thisx.break = 0.0F;
         thisx.do = 0.0F;
         thisx.else = 1.0F;
         thisx.goto = 0.0F;
         thisx.short = 0.0F;
         thisx.long = 0.0F;
         thisx.new = 1.0F;
      } else {
         var2 = 1.0D / var2;
         double var4 = arg0.false * var2;
         double var6 = arg0.null * var2;
         double var8 = arg0.int * var2;
         float var10 = (float)Math.sin(arg0.final);
         float var11 = (float)Math.cos(arg0.final);
         float var12 = 1.0F - var11;
         float var13 = (float)(var4 * var8);
         float var14 = (float)(var4 * var6);
         float var15 = (float)(var6 * var8);
         thisx.char = var12 * (float)(var4 * var4) + var11;
         thisx.float = var12 * var14 - var10 * (float)var8;
         thisx.break = var12 * var13 + var10 * (float)var6;
         thisx.do = var12 * var14 + var10 * (float)var8;
         thisx.else = var12 * (float)(var6 * var6) + var11;
         thisx.goto = var12 * var15 - var10 * (float)var4;
         thisx.short = var12 * var13 - var10 * (float)var6;
         thisx.long = var12 * var15 + var10 * (float)var4;
         thisx.new = var12 * (float)(var8 * var8) + var11;
      }

      thisx.catch = 0.0F;
      thisx.case = 0.0F;
      thisx.const = 0.0F;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void null(Quat4d arg0, Vector3d arg1, double arg2) {
      thisx.char = (float)(arg2 * (1.0D - 2.0D * arg0.int * arg0.int - 2.0D * arg0.final * arg0.final));
      thisx.do = (float)(arg2 * 2.0D * (arg0.null * arg0.int + arg0.this * arg0.final));
      thisx.short = (float)(arg2 * 2.0D * (arg0.null * arg0.final - arg0.this * arg0.int));
      thisx.float = (float)(arg2 * 2.0D * (arg0.null * arg0.int - arg0.this * arg0.final));
      thisx.else = (float)(arg2 * (1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.final * arg0.final));
      thisx.long = (float)(arg2 * 2.0D * (arg0.int * arg0.final + arg0.this * arg0.null));
      thisx.break = (float)(arg2 * 2.0D * (arg0.null * arg0.final + arg0.this * arg0.int));
      thisx.goto = (float)(arg2 * 2.0D * (arg0.int * arg0.final - arg0.this * arg0.null));
      thisx.new = (float)(arg2 * (1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.int * arg0.int));
      thisx.catch = (float)arg1.int;
      thisx.case = (float)arg1.final;
      thisx.const = (float)arg1.this;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void null(Quat4f arg0, Vector3f arg1, float arg2) {
      thisx.char = arg2 * (1.0F - 2.0F * arg0.int * arg0.int - 2.0F * arg0.final * arg0.final);
      thisx.do = arg2 * 2.0F * (arg0.null * arg0.int + arg0.this * arg0.final);
      thisx.short = arg2 * 2.0F * (arg0.null * arg0.final - arg0.this * arg0.int);
      thisx.float = arg2 * 2.0F * (arg0.null * arg0.int - arg0.this * arg0.final);
      thisx.else = arg2 * (1.0F - 2.0F * arg0.null * arg0.null - 2.0F * arg0.final * arg0.final);
      thisx.long = arg2 * 2.0F * (arg0.int * arg0.final + arg0.this * arg0.null);
      thisx.break = arg2 * 2.0F * (arg0.null * arg0.final + arg0.this * arg0.int);
      thisx.goto = arg2 * 2.0F * (arg0.int * arg0.final - arg0.this * arg0.null);
      thisx.new = arg2 * (1.0F - 2.0F * arg0.null * arg0.null - 2.0F * arg0.int * arg0.int);
      thisx.catch = arg1.int;
      thisx.case = arg1.final;
      thisx.const = arg1.this;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void null(Matrix4d arg0) {
      thisx.char = (float)arg0.char;
      thisx.float = (float)arg0.float;
      thisx.break = (float)arg0.break;
      thisx.catch = (float)arg0.catch;
      thisx.do = (float)arg0.do;
      thisx.else = (float)arg0.else;
      thisx.goto = (float)arg0.goto;
      thisx.case = (float)arg0.case;
      thisx.short = (float)arg0.short;
      thisx.long = (float)arg0.long;
      thisx.new = (float)arg0.new;
      thisx.const = (float)arg0.const;
      thisx.false = (float)arg0.false;
      thisx.null = (float)arg0.null;
      thisx.int = (float)arg0.int;
      thisx.final = (float)arg0.final;
   }

   public final void true(Matrix4f arg0) {
      thisx.char = arg0.char;
      thisx.float = arg0.float;
      thisx.break = arg0.break;
      thisx.catch = arg0.catch;
      thisx.do = arg0.do;
      thisx.else = arg0.else;
      thisx.goto = arg0.goto;
      thisx.case = arg0.case;
      thisx.short = arg0.short;
      thisx.long = arg0.long;
      thisx.new = arg0.new;
      thisx.const = arg0.const;
      thisx.false = arg0.false;
      thisx.null = arg0.null;
      thisx.int = arg0.int;
      thisx.final = arg0.final;
   }

   public final void const(Matrix4f arg0) {
      thisx.long(arg0);
   }

   public final void long() {
      thisx.long(thisx);
   }

   public final void long(Matrix4f arg0) {
      double[] var2 = new double[16];
      double[] var3 = new double[16];
      int[] var4 = new int[4];
      var2[0] = (double)arg0.char;
      var2[1] = (double)arg0.float;
      var2[2] = (double)arg0.break;
      var2[3] = (double)arg0.catch;
      var2[4] = (double)arg0.do;
      var2[5] = (double)arg0.else;
      var2[6] = (double)arg0.goto;
      var2[7] = (double)arg0.case;
      var2[8] = (double)arg0.short;
      var2[9] = (double)arg0.long;
      var2[10] = (double)arg0.new;
      var2[11] = (double)arg0.const;
      var2[12] = (double)arg0.false;
      var2[13] = (double)arg0.null;
      var2[14] = (double)arg0.int;
      var2[15] = (double)arg0.final;
      if (!null(var2, var4)) {
         throw new SingularMatrixException(VecMathI18N.null(diag.null("\u0013Y*J7@j^o\n")));
      } else {
         for(int var5 = 0; var5 < 16; ++var5) {
            var3[var5] = 0.0D;
         }

         var3[0] = 1.0D;
         var3[5] = 1.0D;
         var3[10] = 1.0D;
         var3[15] = 1.0D;
         null(var2, var4, var3);
         thisx.char = (float)var3[0];
         thisx.float = (float)var3[1];
         thisx.break = (float)var3[2];
         thisx.catch = (float)var3[3];
         thisx.do = (float)var3[4];
         thisx.else = (float)var3[5];
         thisx.goto = (float)var3[6];
         thisx.case = (float)var3[7];
         thisx.short = (float)var3[8];
         thisx.long = (float)var3[9];
         thisx.new = (float)var3[10];
         thisx.const = (float)var3[11];
         thisx.false = (float)var3[12];
         thisx.null = (float)var3[13];
         thisx.int = (float)var3[14];
         thisx.final = (float)var3[15];
      }
   }

   public static boolean null(double[] arg0, int[] arg1) {
      double[] var2 = new double[4];
      int var5 = 0;
      int var6 = 0;

      int var3;
      double var7;
      for(var3 = 4; var3-- != 0; var2[var6++] = 1.0D / var7) {
         var7 = 0.0D;
         int var4 = 4;

         while(var4-- != 0) {
            double var9 = arg0[var5++];
            var9 = Math.abs(var9);
            if (var9 > var7) {
               var7 = var9;
            }
         }

         if (var7 == 0.0D) {
            return false;
         }
      }

      byte var17 = 0;

      for(var3 = 0; var3 < 4; ++var3) {
         int var8;
         int var10;
         double var11;
         int var18;
         int var19;
         for(var5 = 0; var5 < var3; ++var5) {
            var8 = var17 + 4 * var5 + var3;
            var11 = arg0[var8];
            var18 = var5;
            var19 = var17 + 4 * var5;

            for(var10 = var17 + var3; var18-- != 0; var10 += 4) {
               var11 -= arg0[var19] * arg0[var10];
               ++var19;
            }

            arg0[var8] = var11;
         }

         double var13 = 0.0D;
         var6 = -1;

         double var15;
         for(var5 = var3; var5 < 4; ++var5) {
            var8 = var17 + 4 * var5 + var3;
            var11 = arg0[var8];
            var18 = var3;
            var19 = var17 + 4 * var5;

            for(var10 = var17 + var3; var18-- != 0; var10 += 4) {
               var11 -= arg0[var19] * arg0[var10];
               ++var19;
            }

            arg0[var8] = var11;
            if ((var15 = var2[var5] * Math.abs(var11)) >= var13) {
               var13 = var15;
               var6 = var5;
            }
         }

         if (var6 < 0) {
            throw new RuntimeException(VecMathI18N.null(GpsSatellite.null(">e\u0007v\u001a|GbB7")));
         }

         if (var3 != var6) {
            var18 = 4;
            var19 = var17 + 4 * var6;

            for(var10 = var17 + 4 * var3; var18-- != 0; arg0[var10++] = var15) {
               var15 = arg0[var19];
               arg0[var19++] = arg0[var10];
            }

            var2[var6] = var2[var3];
         }

         arg1[var3] = var6;
         if (arg0[var17 + 4 * var3 + var3] == 0.0D) {
            return false;
         }

         if (var3 != 3) {
            var15 = 1.0D / arg0[var17 + 4 * var3 + var3];
            var8 = var17 + 4 * (var3 + 1) + var3;

            for(var5 = 3 - var3; var5-- != 0; var8 += 4) {
               arg0[var8] *= var15;
            }
         }
      }

      return true;
   }

   public static void null(double[] arg0, int[] arg1, double[] arg2) {
      byte var8 = 0;

      for(int var7 = 0; var7 < 4; ++var7) {
         int var9 = var7;
         int var4 = -1;

         int var10;
         for(int var3 = 0; var3 < 4; ++var3) {
            int var5 = arg1[var8 + var3];
            double var11 = arg2[var9 + 4 * var5];
            arg2[var9 + 4 * var5] = arg2[var9 + 4 * var3];
            if (var4 >= 0) {
               var10 = var3 * 4;

               for(int var6 = var4; var6 <= var3 - 1; ++var6) {
                  var11 -= arg0[var10 + var6] * arg2[var9 + 4 * var6];
               }
            } else if (var11 != 0.0D) {
               var4 = var3;
            }

            arg2[var9 + 4 * var3] = var11;
         }

         byte var13 = 12;
         arg2[var9 + 12] /= arg0[var13 + 3];
         var10 = var13 - 4;
         arg2[var9 + 8] = (arg2[var9 + 8] - arg0[var10 + 3] * arg2[var9 + 12]) / arg0[var10 + 2];
         var10 -= 4;
         arg2[var9 + 4] = (arg2[var9 + 4] - arg0[var10 + 2] * arg2[var9 + 8] - arg0[var10 + 3] * arg2[var9 + 12]) / arg0[var10 + 1];
         var10 -= 4;
         arg2[var9 + 0] = (arg2[var9 + 0] - arg0[var10 + 1] * arg2[var9 + 4] - arg0[var10 + 2] * arg2[var9 + 8] - arg0[var10 + 3] * arg2[var9 + 12]) / arg0[var10 + 0];
      }

   }

   public final float final() {
      float var1 = thisx.char * (thisx.else * thisx.new * thisx.final + thisx.goto * thisx.const * thisx.null + thisx.case * thisx.long * thisx.int - thisx.case * thisx.new * thisx.null - thisx.else * thisx.const * thisx.int - thisx.goto * thisx.long * thisx.final);
      var1 -= thisx.float * (thisx.do * thisx.new * thisx.final + thisx.goto * thisx.const * thisx.false + thisx.case * thisx.short * thisx.int - thisx.case * thisx.new * thisx.false - thisx.do * thisx.const * thisx.int - thisx.goto * thisx.short * thisx.final);
      var1 += thisx.break * (thisx.do * thisx.long * thisx.final + thisx.else * thisx.const * thisx.false + thisx.case * thisx.short * thisx.null - thisx.case * thisx.long * thisx.false - thisx.do * thisx.const * thisx.null - thisx.else * thisx.short * thisx.final);
      var1 -= thisx.catch * (thisx.do * thisx.long * thisx.int + thisx.else * thisx.new * thisx.false + thisx.goto * thisx.short * thisx.null - thisx.goto * thisx.long * thisx.false - thisx.do * thisx.new * thisx.null - thisx.else * thisx.short * thisx.int);
      return var1;
   }

   public final void class(Matrix3f arg0) {
      thisx.char = arg0.case;
      thisx.float = arg0.short;
      thisx.break = arg0.long;
      thisx.catch = 0.0F;
      thisx.do = arg0.new;
      thisx.else = arg0.const;
      thisx.goto = arg0.false;
      thisx.case = 0.0F;
      thisx.short = arg0.null;
      thisx.long = arg0.int;
      thisx.new = arg0.final;
      thisx.const = 0.0F;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void class(Matrix3d arg0) {
      thisx.char = (float)arg0.case;
      thisx.float = (float)arg0.short;
      thisx.break = (float)arg0.long;
      thisx.catch = 0.0F;
      thisx.do = (float)arg0.new;
      thisx.else = (float)arg0.const;
      thisx.goto = (float)arg0.false;
      thisx.case = 0.0F;
      thisx.short = (float)arg0.null;
      thisx.long = (float)arg0.int;
      thisx.new = (float)arg0.final;
      thisx.const = 0.0F;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void void(float arg0) {
      thisx.char = arg0;
      thisx.float = 0.0F;
      thisx.break = 0.0F;
      thisx.catch = 0.0F;
      thisx.do = 0.0F;
      thisx.else = arg0;
      thisx.goto = 0.0F;
      thisx.case = 0.0F;
      thisx.short = 0.0F;
      thisx.long = 0.0F;
      thisx.new = arg0;
      thisx.const = 0.0F;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void null(float[] arg0) {
      thisx.char = arg0[0];
      thisx.float = arg0[1];
      thisx.break = arg0[2];
      thisx.catch = arg0[3];
      thisx.do = arg0[4];
      thisx.else = arg0[5];
      thisx.goto = arg0[6];
      thisx.case = arg0[7];
      thisx.short = arg0[8];
      thisx.long = arg0[9];
      thisx.new = arg0[10];
      thisx.const = arg0[11];
      thisx.false = arg0[12];
      thisx.null = arg0[13];
      thisx.int = arg0[14];
      thisx.final = arg0[15];
   }

   public final void long(Vector3f arg0) {
      thisx.char = 1.0F;
      thisx.float = 0.0F;
      thisx.break = 0.0F;
      thisx.catch = arg0.int;
      thisx.do = 0.0F;
      thisx.else = 1.0F;
      thisx.goto = 0.0F;
      thisx.case = arg0.final;
      thisx.short = 0.0F;
      thisx.long = 0.0F;
      thisx.new = 1.0F;
      thisx.const = arg0.this;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void null(float arg0, Vector3f arg1) {
      thisx.char = arg0;
      thisx.float = 0.0F;
      thisx.break = 0.0F;
      thisx.catch = arg1.int;
      thisx.do = 0.0F;
      thisx.else = arg0;
      thisx.goto = 0.0F;
      thisx.case = arg1.final;
      thisx.short = 0.0F;
      thisx.long = 0.0F;
      thisx.new = arg0;
      thisx.const = arg1.this;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void null(Vector3f arg0, float arg1) {
      thisx.char = arg1;
      thisx.float = 0.0F;
      thisx.break = 0.0F;
      thisx.catch = arg1 * arg0.int;
      thisx.do = 0.0F;
      thisx.else = arg1;
      thisx.goto = 0.0F;
      thisx.case = arg1 * arg0.final;
      thisx.short = 0.0F;
      thisx.long = 0.0F;
      thisx.new = arg1;
      thisx.const = arg1 * arg0.this;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void null(Matrix3f arg0, Vector3f arg1, float arg2) {
      thisx.char = arg0.case * arg2;
      thisx.float = arg0.short * arg2;
      thisx.break = arg0.long * arg2;
      thisx.catch = arg1.int;
      thisx.do = arg0.new * arg2;
      thisx.else = arg0.const * arg2;
      thisx.goto = arg0.false * arg2;
      thisx.case = arg1.final;
      thisx.short = arg0.null * arg2;
      thisx.long = arg0.int * arg2;
      thisx.new = arg0.final * arg2;
      thisx.const = arg1.this;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void null(Matrix3d arg0, Vector3d arg1, double arg2) {
      thisx.char = (float)(arg0.case * arg2);
      thisx.float = (float)(arg0.short * arg2);
      thisx.break = (float)(arg0.long * arg2);
      thisx.catch = (float)arg1.int;
      thisx.do = (float)(arg0.new * arg2);
      thisx.else = (float)(arg0.const * arg2);
      thisx.goto = (float)(arg0.false * arg2);
      thisx.case = (float)arg1.final;
      thisx.short = (float)(arg0.null * arg2);
      thisx.long = (float)(arg0.int * arg2);
      thisx.new = (float)(arg0.final * arg2);
      thisx.const = (float)arg1.this;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void class(Vector3f arg0) {
      thisx.catch = arg0.int;
      thisx.case = arg0.final;
      thisx.const = arg0.this;
   }

   public final void try(float arg0) {
      float var2 = (float)Math.sin((double)arg0);
      float var3 = (float)Math.cos((double)arg0);
      thisx.char = 1.0F;
      thisx.float = 0.0F;
      thisx.break = 0.0F;
      thisx.catch = 0.0F;
      thisx.do = 0.0F;
      thisx.else = var3;
      thisx.goto = -var2;
      thisx.case = 0.0F;
      thisx.short = 0.0F;
      thisx.long = var2;
      thisx.new = var3;
      thisx.const = 0.0F;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void int(float arg0) {
      float var2 = (float)Math.sin((double)arg0);
      float var3 = (float)Math.cos((double)arg0);
      thisx.char = var3;
      thisx.float = 0.0F;
      thisx.break = var2;
      thisx.catch = 0.0F;
      thisx.do = 0.0F;
      thisx.else = 1.0F;
      thisx.goto = 0.0F;
      thisx.case = 0.0F;
      thisx.short = -var2;
      thisx.long = 0.0F;
      thisx.new = var3;
      thisx.const = 0.0F;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void enum(float arg0) {
      float var2 = (float)Math.sin((double)arg0);
      float var3 = (float)Math.cos((double)arg0);
      thisx.char = var3;
      thisx.float = -var2;
      thisx.break = 0.0F;
      thisx.catch = 0.0F;
      thisx.do = var2;
      thisx.else = var3;
      thisx.goto = 0.0F;
      thisx.case = 0.0F;
      thisx.short = 0.0F;
      thisx.long = 0.0F;
      thisx.new = 1.0F;
      thisx.const = 0.0F;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void final(float arg0) {
      thisx.char *= arg0;
      thisx.float *= arg0;
      thisx.break *= arg0;
      thisx.catch *= arg0;
      thisx.do *= arg0;
      thisx.else *= arg0;
      thisx.goto *= arg0;
      thisx.case *= arg0;
      thisx.short *= arg0;
      thisx.long *= arg0;
      thisx.new *= arg0;
      thisx.const *= arg0;
      thisx.false *= arg0;
      thisx.null *= arg0;
      thisx.int *= arg0;
      thisx.final *= arg0;
   }

   public final void null(float arg0, Matrix4f arg1) {
      thisx.char = arg1.char * arg0;
      thisx.float = arg1.float * arg0;
      thisx.break = arg1.break * arg0;
      thisx.catch = arg1.catch * arg0;
      thisx.do = arg1.do * arg0;
      thisx.else = arg1.else * arg0;
      thisx.goto = arg1.goto * arg0;
      thisx.case = arg1.case * arg0;
      thisx.short = arg1.short * arg0;
      thisx.long = arg1.long * arg0;
      thisx.new = arg1.new * arg0;
      thisx.const = arg1.const * arg0;
      thisx.false = arg1.false * arg0;
      thisx.null = arg1.null * arg0;
      thisx.int = arg1.int * arg0;
      thisx.final = arg1.final * arg0;
   }

   public final void class(Matrix4f arg0) {
      float var2 = thisx.char * arg0.char + thisx.float * arg0.do + thisx.break * arg0.short + thisx.catch * arg0.false;
      float var3 = thisx.char * arg0.float + thisx.float * arg0.else + thisx.break * arg0.long + thisx.catch * arg0.null;
      float var4 = thisx.char * arg0.break + thisx.float * arg0.goto + thisx.break * arg0.new + thisx.catch * arg0.int;
      float var5 = thisx.char * arg0.catch + thisx.float * arg0.case + thisx.break * arg0.const + thisx.catch * arg0.final;
      float var6 = thisx.do * arg0.char + thisx.else * arg0.do + thisx.goto * arg0.short + thisx.case * arg0.false;
      float var7 = thisx.do * arg0.float + thisx.else * arg0.else + thisx.goto * arg0.long + thisx.case * arg0.null;
      float var8 = thisx.do * arg0.break + thisx.else * arg0.goto + thisx.goto * arg0.new + thisx.case * arg0.int;
      float var9 = thisx.do * arg0.catch + thisx.else * arg0.case + thisx.goto * arg0.const + thisx.case * arg0.final;
      float var10 = thisx.short * arg0.char + thisx.long * arg0.do + thisx.new * arg0.short + thisx.const * arg0.false;
      float var11 = thisx.short * arg0.float + thisx.long * arg0.else + thisx.new * arg0.long + thisx.const * arg0.null;
      float var12 = thisx.short * arg0.break + thisx.long * arg0.goto + thisx.new * arg0.new + thisx.const * arg0.int;
      float var13 = thisx.short * arg0.catch + thisx.long * arg0.case + thisx.new * arg0.const + thisx.const * arg0.final;
      float var14 = thisx.false * arg0.char + thisx.null * arg0.do + thisx.int * arg0.short + thisx.final * arg0.false;
      float var15 = thisx.false * arg0.float + thisx.null * arg0.else + thisx.int * arg0.long + thisx.final * arg0.null;
      float var16 = thisx.false * arg0.break + thisx.null * arg0.goto + thisx.int * arg0.new + thisx.final * arg0.int;
      float var17 = thisx.false * arg0.catch + thisx.null * arg0.case + thisx.int * arg0.const + thisx.final * arg0.final;
      thisx.char = var2;
      thisx.float = var3;
      thisx.break = var4;
      thisx.catch = var5;
      thisx.do = var6;
      thisx.else = var7;
      thisx.goto = var8;
      thisx.case = var9;
      thisx.short = var10;
      thisx.long = var11;
      thisx.new = var12;
      thisx.const = var13;
      thisx.false = var14;
      thisx.null = var15;
      thisx.int = var16;
      thisx.final = var17;
   }

   public final void const(Matrix4f arg0, Matrix4f arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.char = arg0.char * arg1.char + arg0.float * arg1.do + arg0.break * arg1.short + arg0.catch * arg1.false;
         thisx.float = arg0.char * arg1.float + arg0.float * arg1.else + arg0.break * arg1.long + arg0.catch * arg1.null;
         thisx.break = arg0.char * arg1.break + arg0.float * arg1.goto + arg0.break * arg1.new + arg0.catch * arg1.int;
         thisx.catch = arg0.char * arg1.catch + arg0.float * arg1.case + arg0.break * arg1.const + arg0.catch * arg1.final;
         thisx.do = arg0.do * arg1.char + arg0.else * arg1.do + arg0.goto * arg1.short + arg0.case * arg1.false;
         thisx.else = arg0.do * arg1.float + arg0.else * arg1.else + arg0.goto * arg1.long + arg0.case * arg1.null;
         thisx.goto = arg0.do * arg1.break + arg0.else * arg1.goto + arg0.goto * arg1.new + arg0.case * arg1.int;
         thisx.case = arg0.do * arg1.catch + arg0.else * arg1.case + arg0.goto * arg1.const + arg0.case * arg1.final;
         thisx.short = arg0.short * arg1.char + arg0.long * arg1.do + arg0.new * arg1.short + arg0.const * arg1.false;
         thisx.long = arg0.short * arg1.float + arg0.long * arg1.else + arg0.new * arg1.long + arg0.const * arg1.null;
         thisx.new = arg0.short * arg1.break + arg0.long * arg1.goto + arg0.new * arg1.new + arg0.const * arg1.int;
         thisx.const = arg0.short * arg1.catch + arg0.long * arg1.case + arg0.new * arg1.const + arg0.const * arg1.final;
         thisx.false = arg0.false * arg1.char + arg0.null * arg1.do + arg0.int * arg1.short + arg0.final * arg1.false;
         thisx.null = arg0.false * arg1.float + arg0.null * arg1.else + arg0.int * arg1.long + arg0.final * arg1.null;
         thisx.int = arg0.false * arg1.break + arg0.null * arg1.goto + arg0.int * arg1.new + arg0.final * arg1.int;
         thisx.final = arg0.false * arg1.catch + arg0.null * arg1.case + arg0.int * arg1.const + arg0.final * arg1.final;
      } else {
         float var3 = arg0.char * arg1.char + arg0.float * arg1.do + arg0.break * arg1.short + arg0.catch * arg1.false;
         float var4 = arg0.char * arg1.float + arg0.float * arg1.else + arg0.break * arg1.long + arg0.catch * arg1.null;
         float var5 = arg0.char * arg1.break + arg0.float * arg1.goto + arg0.break * arg1.new + arg0.catch * arg1.int;
         float var6 = arg0.char * arg1.catch + arg0.float * arg1.case + arg0.break * arg1.const + arg0.catch * arg1.final;
         float var7 = arg0.do * arg1.char + arg0.else * arg1.do + arg0.goto * arg1.short + arg0.case * arg1.false;
         float var8 = arg0.do * arg1.float + arg0.else * arg1.else + arg0.goto * arg1.long + arg0.case * arg1.null;
         float var9 = arg0.do * arg1.break + arg0.else * arg1.goto + arg0.goto * arg1.new + arg0.case * arg1.int;
         float var10 = arg0.do * arg1.catch + arg0.else * arg1.case + arg0.goto * arg1.const + arg0.case * arg1.final;
         float var11 = arg0.short * arg1.char + arg0.long * arg1.do + arg0.new * arg1.short + arg0.const * arg1.false;
         float var12 = arg0.short * arg1.float + arg0.long * arg1.else + arg0.new * arg1.long + arg0.const * arg1.null;
         float var13 = arg0.short * arg1.break + arg0.long * arg1.goto + arg0.new * arg1.new + arg0.const * arg1.int;
         float var14 = arg0.short * arg1.catch + arg0.long * arg1.case + arg0.new * arg1.const + arg0.const * arg1.final;
         float var15 = arg0.false * arg1.char + arg0.null * arg1.do + arg0.int * arg1.short + arg0.final * arg1.false;
         float var16 = arg0.false * arg1.float + arg0.null * arg1.else + arg0.int * arg1.long + arg0.final * arg1.null;
         float var17 = arg0.false * arg1.break + arg0.null * arg1.goto + arg0.int * arg1.new + arg0.final * arg1.int;
         float var18 = arg0.false * arg1.catch + arg0.null * arg1.case + arg0.int * arg1.const + arg0.final * arg1.final;
         thisx.char = var3;
         thisx.float = var4;
         thisx.break = var5;
         thisx.catch = var6;
         thisx.do = var7;
         thisx.else = var8;
         thisx.goto = var9;
         thisx.case = var10;
         thisx.short = var11;
         thisx.long = var12;
         thisx.new = var13;
         thisx.const = var14;
         thisx.false = var15;
         thisx.null = var16;
         thisx.int = var17;
         thisx.final = var18;
      }

   }

   public final void long(Matrix4f arg0, Matrix4f arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.char = arg0.char * arg1.char + arg0.do * arg1.float + arg0.short * arg1.break + arg0.false * arg1.catch;
         thisx.float = arg0.char * arg1.do + arg0.do * arg1.else + arg0.short * arg1.goto + arg0.false * arg1.case;
         thisx.break = arg0.char * arg1.short + arg0.do * arg1.long + arg0.short * arg1.new + arg0.false * arg1.const;
         thisx.catch = arg0.char * arg1.false + arg0.do * arg1.null + arg0.short * arg1.int + arg0.false * arg1.final;
         thisx.do = arg0.float * arg1.char + arg0.else * arg1.float + arg0.long * arg1.break + arg0.null * arg1.catch;
         thisx.else = arg0.float * arg1.do + arg0.else * arg1.else + arg0.long * arg1.goto + arg0.null * arg1.case;
         thisx.goto = arg0.float * arg1.short + arg0.else * arg1.long + arg0.long * arg1.new + arg0.null * arg1.const;
         thisx.case = arg0.float * arg1.false + arg0.else * arg1.null + arg0.long * arg1.int + arg0.null * arg1.final;
         thisx.short = arg0.break * arg1.char + arg0.goto * arg1.float + arg0.new * arg1.break + arg0.int * arg1.catch;
         thisx.long = arg0.break * arg1.do + arg0.goto * arg1.else + arg0.new * arg1.goto + arg0.int * arg1.case;
         thisx.new = arg0.break * arg1.short + arg0.goto * arg1.long + arg0.new * arg1.new + arg0.int * arg1.const;
         thisx.const = arg0.break * arg1.false + arg0.goto * arg1.null + arg0.new * arg1.int + arg0.int * arg1.final;
         thisx.false = arg0.catch * arg1.char + arg0.case * arg1.float + arg0.const * arg1.break + arg0.final * arg1.catch;
         thisx.null = arg0.catch * arg1.do + arg0.case * arg1.else + arg0.const * arg1.goto + arg0.final * arg1.case;
         thisx.int = arg0.catch * arg1.short + arg0.case * arg1.long + arg0.const * arg1.new + arg0.final * arg1.const;
         thisx.final = arg0.catch * arg1.false + arg0.case * arg1.null + arg0.const * arg1.int + arg0.final * arg1.final;
      } else {
         float var3 = arg0.char * arg1.char + arg0.do * arg1.float + arg0.short * arg1.break + arg0.false * arg1.catch;
         float var4 = arg0.char * arg1.do + arg0.do * arg1.else + arg0.short * arg1.goto + arg0.false * arg1.case;
         float var5 = arg0.char * arg1.short + arg0.do * arg1.long + arg0.short * arg1.new + arg0.false * arg1.const;
         float var6 = arg0.char * arg1.false + arg0.do * arg1.null + arg0.short * arg1.int + arg0.false * arg1.final;
         float var7 = arg0.float * arg1.char + arg0.else * arg1.float + arg0.long * arg1.break + arg0.null * arg1.catch;
         float var8 = arg0.float * arg1.do + arg0.else * arg1.else + arg0.long * arg1.goto + arg0.null * arg1.case;
         float var9 = arg0.float * arg1.short + arg0.else * arg1.long + arg0.long * arg1.new + arg0.null * arg1.const;
         float var10 = arg0.float * arg1.false + arg0.else * arg1.null + arg0.long * arg1.int + arg0.null * arg1.final;
         float var11 = arg0.break * arg1.char + arg0.goto * arg1.float + arg0.new * arg1.break + arg0.int * arg1.catch;
         float var12 = arg0.break * arg1.do + arg0.goto * arg1.else + arg0.new * arg1.goto + arg0.int * arg1.case;
         float var13 = arg0.break * arg1.short + arg0.goto * arg1.long + arg0.new * arg1.new + arg0.int * arg1.const;
         float var14 = arg0.break * arg1.false + arg0.goto * arg1.null + arg0.new * arg1.int + arg0.int * arg1.final;
         float var15 = arg0.catch * arg1.char + arg0.case * arg1.float + arg0.const * arg1.break + arg0.final * arg1.catch;
         float var16 = arg0.catch * arg1.do + arg0.case * arg1.else + arg0.const * arg1.goto + arg0.final * arg1.case;
         float var17 = arg0.catch * arg1.short + arg0.case * arg1.long + arg0.const * arg1.new + arg0.final * arg1.const;
         float var18 = arg0.catch * arg1.false + arg0.case * arg1.null + arg0.const * arg1.int + arg0.final * arg1.final;
         thisx.char = var3;
         thisx.float = var4;
         thisx.break = var5;
         thisx.catch = var6;
         thisx.do = var7;
         thisx.else = var8;
         thisx.goto = var9;
         thisx.case = var10;
         thisx.short = var11;
         thisx.long = var12;
         thisx.new = var13;
         thisx.const = var14;
         thisx.false = var15;
         thisx.null = var16;
         thisx.int = var17;
         thisx.final = var18;
      }

   }

   public final void class(Matrix4f arg0, Matrix4f arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.char = arg0.char * arg1.char + arg0.float * arg1.float + arg0.break * arg1.break + arg0.catch * arg1.catch;
         thisx.float = arg0.char * arg1.do + arg0.float * arg1.else + arg0.break * arg1.goto + arg0.catch * arg1.case;
         thisx.break = arg0.char * arg1.short + arg0.float * arg1.long + arg0.break * arg1.new + arg0.catch * arg1.const;
         thisx.catch = arg0.char * arg1.false + arg0.float * arg1.null + arg0.break * arg1.int + arg0.catch * arg1.final;
         thisx.do = arg0.do * arg1.char + arg0.else * arg1.float + arg0.goto * arg1.break + arg0.case * arg1.catch;
         thisx.else = arg0.do * arg1.do + arg0.else * arg1.else + arg0.goto * arg1.goto + arg0.case * arg1.case;
         thisx.goto = arg0.do * arg1.short + arg0.else * arg1.long + arg0.goto * arg1.new + arg0.case * arg1.const;
         thisx.case = arg0.do * arg1.false + arg0.else * arg1.null + arg0.goto * arg1.int + arg0.case * arg1.final;
         thisx.short = arg0.short * arg1.char + arg0.long * arg1.float + arg0.new * arg1.break + arg0.const * arg1.catch;
         thisx.long = arg0.short * arg1.do + arg0.long * arg1.else + arg0.new * arg1.goto + arg0.const * arg1.case;
         thisx.new = arg0.short * arg1.short + arg0.long * arg1.long + arg0.new * arg1.new + arg0.const * arg1.const;
         thisx.const = arg0.short * arg1.false + arg0.long * arg1.null + arg0.new * arg1.int + arg0.const * arg1.final;
         thisx.false = arg0.false * arg1.char + arg0.null * arg1.float + arg0.int * arg1.break + arg0.final * arg1.catch;
         thisx.null = arg0.false * arg1.do + arg0.null * arg1.else + arg0.int * arg1.goto + arg0.final * arg1.case;
         thisx.int = arg0.false * arg1.short + arg0.null * arg1.long + arg0.int * arg1.new + arg0.final * arg1.const;
         thisx.final = arg0.false * arg1.false + arg0.null * arg1.null + arg0.int * arg1.int + arg0.final * arg1.final;
      } else {
         float var3 = arg0.char * arg1.char + arg0.float * arg1.float + arg0.break * arg1.break + arg0.catch * arg1.catch;
         float var4 = arg0.char * arg1.do + arg0.float * arg1.else + arg0.break * arg1.goto + arg0.catch * arg1.case;
         float var5 = arg0.char * arg1.short + arg0.float * arg1.long + arg0.break * arg1.new + arg0.catch * arg1.const;
         float var6 = arg0.char * arg1.false + arg0.float * arg1.null + arg0.break * arg1.int + arg0.catch * arg1.final;
         float var7 = arg0.do * arg1.char + arg0.else * arg1.float + arg0.goto * arg1.break + arg0.case * arg1.catch;
         float var8 = arg0.do * arg1.do + arg0.else * arg1.else + arg0.goto * arg1.goto + arg0.case * arg1.case;
         float var9 = arg0.do * arg1.short + arg0.else * arg1.long + arg0.goto * arg1.new + arg0.case * arg1.const;
         float var10 = arg0.do * arg1.false + arg0.else * arg1.null + arg0.goto * arg1.int + arg0.case * arg1.final;
         float var11 = arg0.short * arg1.char + arg0.long * arg1.float + arg0.new * arg1.break + arg0.const * arg1.catch;
         float var12 = arg0.short * arg1.do + arg0.long * arg1.else + arg0.new * arg1.goto + arg0.const * arg1.case;
         float var13 = arg0.short * arg1.short + arg0.long * arg1.long + arg0.new * arg1.new + arg0.const * arg1.const;
         float var14 = arg0.short * arg1.false + arg0.long * arg1.null + arg0.new * arg1.int + arg0.const * arg1.final;
         float var15 = arg0.false * arg1.char + arg0.null * arg1.float + arg0.int * arg1.break + arg0.final * arg1.catch;
         float var16 = arg0.false * arg1.do + arg0.null * arg1.else + arg0.int * arg1.goto + arg0.final * arg1.case;
         float var17 = arg0.false * arg1.short + arg0.null * arg1.long + arg0.int * arg1.new + arg0.final * arg1.const;
         float var18 = arg0.false * arg1.false + arg0.null * arg1.null + arg0.int * arg1.int + arg0.final * arg1.final;
         thisx.char = var3;
         thisx.float = var4;
         thisx.break = var5;
         thisx.catch = var6;
         thisx.do = var7;
         thisx.else = var8;
         thisx.goto = var9;
         thisx.case = var10;
         thisx.short = var11;
         thisx.long = var12;
         thisx.new = var13;
         thisx.const = var14;
         thisx.false = var15;
         thisx.null = var16;
         thisx.int = var17;
         thisx.final = var18;
      }

   }

   public final void null(Matrix4f arg0, Matrix4f arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.char = arg0.char * arg1.char + arg0.do * arg1.do + arg0.short * arg1.short + arg0.false * arg1.false;
         thisx.float = arg0.char * arg1.float + arg0.do * arg1.else + arg0.short * arg1.long + arg0.false * arg1.null;
         thisx.break = arg0.char * arg1.break + arg0.do * arg1.goto + arg0.short * arg1.new + arg0.false * arg1.int;
         thisx.catch = arg0.char * arg1.catch + arg0.do * arg1.case + arg0.short * arg1.const + arg0.false * arg1.final;
         thisx.do = arg0.float * arg1.char + arg0.else * arg1.do + arg0.long * arg1.short + arg0.null * arg1.false;
         thisx.else = arg0.float * arg1.float + arg0.else * arg1.else + arg0.long * arg1.long + arg0.null * arg1.null;
         thisx.goto = arg0.float * arg1.break + arg0.else * arg1.goto + arg0.long * arg1.new + arg0.null * arg1.int;
         thisx.case = arg0.float * arg1.catch + arg0.else * arg1.case + arg0.long * arg1.const + arg0.null * arg1.final;
         thisx.short = arg0.break * arg1.char + arg0.goto * arg1.do + arg0.new * arg1.short + arg0.int * arg1.false;
         thisx.long = arg0.break * arg1.float + arg0.goto * arg1.else + arg0.new * arg1.long + arg0.int * arg1.null;
         thisx.new = arg0.break * arg1.break + arg0.goto * arg1.goto + arg0.new * arg1.new + arg0.int * arg1.int;
         thisx.const = arg0.break * arg1.catch + arg0.goto * arg1.case + arg0.new * arg1.const + arg0.int * arg1.final;
         thisx.false = arg0.catch * arg1.char + arg0.case * arg1.do + arg0.const * arg1.short + arg0.final * arg1.false;
         thisx.null = arg0.catch * arg1.float + arg0.case * arg1.else + arg0.const * arg1.long + arg0.final * arg1.null;
         thisx.int = arg0.catch * arg1.break + arg0.case * arg1.goto + arg0.const * arg1.new + arg0.final * arg1.int;
         thisx.final = arg0.catch * arg1.catch + arg0.case * arg1.case + arg0.const * arg1.const + arg0.final * arg1.final;
      } else {
         float var3 = arg0.char * arg1.char + arg0.do * arg1.do + arg0.short * arg1.short + arg0.false * arg1.false;
         float var4 = arg0.char * arg1.float + arg0.do * arg1.else + arg0.short * arg1.long + arg0.false * arg1.null;
         float var5 = arg0.char * arg1.break + arg0.do * arg1.goto + arg0.short * arg1.new + arg0.false * arg1.int;
         float var6 = arg0.char * arg1.catch + arg0.do * arg1.case + arg0.short * arg1.const + arg0.false * arg1.final;
         float var7 = arg0.float * arg1.char + arg0.else * arg1.do + arg0.long * arg1.short + arg0.null * arg1.false;
         float var8 = arg0.float * arg1.float + arg0.else * arg1.else + arg0.long * arg1.long + arg0.null * arg1.null;
         float var9 = arg0.float * arg1.break + arg0.else * arg1.goto + arg0.long * arg1.new + arg0.null * arg1.int;
         float var10 = arg0.float * arg1.catch + arg0.else * arg1.case + arg0.long * arg1.const + arg0.null * arg1.final;
         float var11 = arg0.break * arg1.char + arg0.goto * arg1.do + arg0.new * arg1.short + arg0.int * arg1.false;
         float var12 = arg0.break * arg1.float + arg0.goto * arg1.else + arg0.new * arg1.long + arg0.int * arg1.null;
         float var13 = arg0.break * arg1.break + arg0.goto * arg1.goto + arg0.new * arg1.new + arg0.int * arg1.int;
         float var14 = arg0.break * arg1.catch + arg0.goto * arg1.case + arg0.new * arg1.const + arg0.int * arg1.final;
         float var15 = arg0.catch * arg1.char + arg0.case * arg1.do + arg0.const * arg1.short + arg0.final * arg1.false;
         float var16 = arg0.catch * arg1.float + arg0.case * arg1.else + arg0.const * arg1.long + arg0.final * arg1.null;
         float var17 = arg0.catch * arg1.break + arg0.case * arg1.goto + arg0.const * arg1.new + arg0.final * arg1.int;
         float var18 = arg0.catch * arg1.catch + arg0.case * arg1.case + arg0.const * arg1.const + arg0.final * arg1.final;
         thisx.char = var3;
         thisx.float = var4;
         thisx.break = var5;
         thisx.catch = var6;
         thisx.do = var7;
         thisx.else = var8;
         thisx.goto = var9;
         thisx.case = var10;
         thisx.short = var11;
         thisx.long = var12;
         thisx.new = var13;
         thisx.const = var14;
         thisx.false = var15;
         thisx.null = var16;
         thisx.int = var17;
         thisx.final = var18;
      }

   }

   public boolean null(Matrix4f arg0) {
      try {
         return thisx.char == arg0.char && thisx.float == arg0.float && thisx.break == arg0.break && thisx.catch == arg0.catch && thisx.do == arg0.do && thisx.else == arg0.else && thisx.goto == arg0.goto && thisx.case == arg0.case && thisx.short == arg0.short && thisx.long == arg0.long && thisx.new == arg0.new && thisx.const == arg0.const && thisx.false == arg0.false && thisx.null == arg0.null && thisx.int == arg0.int && thisx.final == arg0.final;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public boolean equals(Object arg0) {
      try {
         Matrix4f var2 = (Matrix4f)arg0;
         return thisx.char == var2.char && thisx.float == var2.float && thisx.break == var2.break && thisx.catch == var2.catch && thisx.do == var2.do && thisx.else == var2.else && thisx.goto == var2.goto && thisx.case == var2.case && thisx.short == var2.short && thisx.long == var2.long && thisx.new == var2.new && thisx.const == var2.const && thisx.false == var2.false && thisx.null == var2.null && thisx.int == var2.int && thisx.final == var2.final;
      } catch (ClassCastException var3) {
         return false;
      } catch (NullPointerException var4) {
         return false;
      }
   }

   public boolean null(Matrix4f arg0, float arg1) {
      boolean var3 = true;
      if (Math.abs(thisx.char - arg0.char) > arg1) {
         var3 = false;
      }

      if (Math.abs(thisx.float - arg0.float) > arg1) {
         var3 = false;
      }

      if (Math.abs(thisx.break - arg0.break) > arg1) {
         var3 = false;
      }

      if (Math.abs(thisx.catch - arg0.catch) > arg1) {
         var3 = false;
      }

      if (Math.abs(thisx.do - arg0.do) > arg1) {
         var3 = false;
      }

      if (Math.abs(thisx.else - arg0.else) > arg1) {
         var3 = false;
      }

      if (Math.abs(thisx.goto - arg0.goto) > arg1) {
         var3 = false;
      }

      if (Math.abs(thisx.case - arg0.case) > arg1) {
         var3 = false;
      }

      if (Math.abs(thisx.short - arg0.short) > arg1) {
         var3 = false;
      }

      if (Math.abs(thisx.long - arg0.long) > arg1) {
         var3 = false;
      }

      if (Math.abs(thisx.new - arg0.new) > arg1) {
         var3 = false;
      }

      if (Math.abs(thisx.const - arg0.const) > arg1) {
         var3 = false;
      }

      if (Math.abs(thisx.false - arg0.false) > arg1) {
         var3 = false;
      }

      if (Math.abs(thisx.null - arg0.null) > arg1) {
         var3 = false;
      }

      if (Math.abs(thisx.int - arg0.int) > arg1) {
         var3 = false;
      }

      if (Math.abs(thisx.final - arg0.final) > arg1) {
         var3 = false;
      }

      return var3;
   }

   public int hashCode() {
      long var1 = 1L;
      var1 = VecMathUtil.null(var1, thisx.char);
      var1 = VecMathUtil.null(var1, thisx.float);
      var1 = VecMathUtil.null(var1, thisx.break);
      var1 = VecMathUtil.null(var1, thisx.catch);
      var1 = VecMathUtil.null(var1, thisx.do);
      var1 = VecMathUtil.null(var1, thisx.else);
      var1 = VecMathUtil.null(var1, thisx.goto);
      var1 = VecMathUtil.null(var1, thisx.case);
      var1 = VecMathUtil.null(var1, thisx.short);
      var1 = VecMathUtil.null(var1, thisx.long);
      var1 = VecMathUtil.null(var1, thisx.new);
      var1 = VecMathUtil.null(var1, thisx.const);
      var1 = VecMathUtil.null(var1, thisx.false);
      var1 = VecMathUtil.null(var1, thisx.null);
      var1 = VecMathUtil.null(var1, thisx.int);
      var1 = VecMathUtil.null(var1, thisx.final);
      return VecMathUtil.null(var1);
   }

   public final void null(Tuple4f arg0, Tuple4f arg1) {
      float var3 = thisx.char * arg0.null + thisx.float * arg0.int + thisx.break * arg0.final + thisx.catch * arg0.this;
      float var4 = thisx.do * arg0.null + thisx.else * arg0.int + thisx.goto * arg0.final + thisx.case * arg0.this;
      float var5 = thisx.short * arg0.null + thisx.long * arg0.int + thisx.new * arg0.final + thisx.const * arg0.this;
      arg1.this = thisx.false * arg0.null + thisx.null * arg0.int + thisx.int * arg0.final + thisx.final * arg0.this;
      arg1.null = var3;
      arg1.int = var4;
      arg1.final = var5;
   }

   public final void null(Tuple4f arg0) {
      float var2 = thisx.char * arg0.null + thisx.float * arg0.int + thisx.break * arg0.final + thisx.catch * arg0.this;
      float var3 = thisx.do * arg0.null + thisx.else * arg0.int + thisx.goto * arg0.final + thisx.case * arg0.this;
      float var4 = thisx.short * arg0.null + thisx.long * arg0.int + thisx.new * arg0.final + thisx.const * arg0.this;
      arg0.this = thisx.false * arg0.null + thisx.null * arg0.int + thisx.int * arg0.final + thisx.final * arg0.this;
      arg0.null = var2;
      arg0.int = var3;
      arg0.final = var4;
   }

   public final void null(Point3f arg0, Point3f arg1) {
      float var3 = thisx.char * arg0.int + thisx.float * arg0.final + thisx.break * arg0.this + thisx.catch;
      float var4 = thisx.do * arg0.int + thisx.else * arg0.final + thisx.goto * arg0.this + thisx.case;
      arg1.this = thisx.short * arg0.int + thisx.long * arg0.final + thisx.new * arg0.this + thisx.const;
      arg1.int = var3;
      arg1.final = var4;
   }

   public final void null(Point3f arg0) {
      float var2 = thisx.char * arg0.int + thisx.float * arg0.final + thisx.break * arg0.this + thisx.catch;
      float var3 = thisx.do * arg0.int + thisx.else * arg0.final + thisx.goto * arg0.this + thisx.case;
      arg0.this = thisx.short * arg0.int + thisx.long * arg0.final + thisx.new * arg0.this + thisx.const;
      arg0.int = var2;
      arg0.final = var3;
   }

   public final void null(Vector3f arg0, Vector3f arg1) {
      float var3 = thisx.char * arg0.int + thisx.float * arg0.final + thisx.break * arg0.this;
      float var4 = thisx.do * arg0.int + thisx.else * arg0.final + thisx.goto * arg0.this;
      arg1.this = thisx.short * arg0.int + thisx.long * arg0.final + thisx.new * arg0.this;
      arg1.int = var3;
      arg1.final = var4;
   }

   public final void null(Vector3f arg0) {
      float var2 = thisx.char * arg0.int + thisx.float * arg0.final + thisx.break * arg0.this;
      float var3 = thisx.do * arg0.int + thisx.else * arg0.final + thisx.goto * arg0.this;
      arg0.this = thisx.short * arg0.int + thisx.long * arg0.final + thisx.new * arg0.this;
      arg0.int = var2;
      arg0.final = var3;
   }

   public final void null(Matrix3d arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      thisx.char = (float)(arg0.case * var3[0]);
      thisx.float = (float)(arg0.short * var3[1]);
      thisx.break = (float)(arg0.long * var3[2]);
      thisx.do = (float)(arg0.new * var3[0]);
      thisx.else = (float)(arg0.const * var3[1]);
      thisx.goto = (float)(arg0.false * var3[2]);
      thisx.short = (float)(arg0.null * var3[0]);
      thisx.long = (float)(arg0.int * var3[1]);
      thisx.new = (float)(arg0.final * var3[2]);
   }

   public final void null(Matrix3f arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      thisx.char = (float)((double)arg0.case * var3[0]);
      thisx.float = (float)((double)arg0.short * var3[1]);
      thisx.break = (float)((double)arg0.long * var3[2]);
      thisx.do = (float)((double)arg0.new * var3[0]);
      thisx.else = (float)((double)arg0.const * var3[1]);
      thisx.goto = (float)((double)arg0.false * var3[2]);
      thisx.short = (float)((double)arg0.null * var3[0]);
      thisx.long = (float)((double)arg0.int * var3[1]);
      thisx.new = (float)((double)arg0.final * var3[2]);
   }

   public final void null(Quat4f arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      thisx.char = (float)((double)(1.0F - 2.0F * arg0.int * arg0.int - 2.0F * arg0.final * arg0.final) * var3[0]);
      thisx.do = (float)((double)(2.0F * (arg0.null * arg0.int + arg0.this * arg0.final)) * var3[0]);
      thisx.short = (float)((double)(2.0F * (arg0.null * arg0.final - arg0.this * arg0.int)) * var3[0]);
      thisx.float = (float)((double)(2.0F * (arg0.null * arg0.int - arg0.this * arg0.final)) * var3[1]);
      thisx.else = (float)((double)(1.0F - 2.0F * arg0.null * arg0.null - 2.0F * arg0.final * arg0.final) * var3[1]);
      thisx.long = (float)((double)(2.0F * (arg0.int * arg0.final + arg0.this * arg0.null)) * var3[1]);
      thisx.break = (float)((double)(2.0F * (arg0.null * arg0.final + arg0.this * arg0.int)) * var3[2]);
      thisx.goto = (float)((double)(2.0F * (arg0.int * arg0.final - arg0.this * arg0.null)) * var3[2]);
      thisx.new = (float)((double)(1.0F - 2.0F * arg0.null * arg0.null - 2.0F * arg0.int * arg0.int) * var3[2]);
   }

   public final void null(Quat4d arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      thisx.char = (float)((1.0D - 2.0D * arg0.int * arg0.int - 2.0D * arg0.final * arg0.final) * var3[0]);
      thisx.do = (float)(2.0D * (arg0.null * arg0.int + arg0.this * arg0.final) * var3[0]);
      thisx.short = (float)(2.0D * (arg0.null * arg0.final - arg0.this * arg0.int) * var3[0]);
      thisx.float = (float)(2.0D * (arg0.null * arg0.int - arg0.this * arg0.final) * var3[1]);
      thisx.else = (float)((1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.final * arg0.final) * var3[1]);
      thisx.long = (float)(2.0D * (arg0.int * arg0.final + arg0.this * arg0.null) * var3[1]);
      thisx.break = (float)(2.0D * (arg0.null * arg0.final + arg0.this * arg0.int) * var3[2]);
      thisx.goto = (float)(2.0D * (arg0.int * arg0.final - arg0.this * arg0.null) * var3[2]);
      thisx.new = (float)((1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.int * arg0.int) * var3[2]);
   }

   public final void null(AxisAngle4f arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      double var4 = Math.sqrt((double)(arg0.false * arg0.false + arg0.null * arg0.null + arg0.int * arg0.int));
      if (var4 < 1.0E-8D) {
         thisx.char = 1.0F;
         thisx.float = 0.0F;
         thisx.break = 0.0F;
         thisx.do = 0.0F;
         thisx.else = 1.0F;
         thisx.goto = 0.0F;
         thisx.short = 0.0F;
         thisx.long = 0.0F;
         thisx.new = 1.0F;
      } else {
         var4 = 1.0D / var4;
         double var6 = (double)arg0.false * var4;
         double var8 = (double)arg0.null * var4;
         double var10 = (double)arg0.int * var4;
         double var12 = Math.sin((double)arg0.final);
         double var14 = Math.cos((double)arg0.final);
         double var16 = 1.0D - var14;
         double var18 = (double)(arg0.false * arg0.int);
         double var20 = (double)(arg0.false * arg0.null);
         double var22 = (double)(arg0.null * arg0.int);
         thisx.char = (float)((var16 * var6 * var6 + var14) * var3[0]);
         thisx.float = (float)((var16 * var20 - var12 * var10) * var3[1]);
         thisx.break = (float)((var16 * var18 + var12 * var8) * var3[2]);
         thisx.do = (float)((var16 * var20 + var12 * var10) * var3[0]);
         thisx.else = (float)((var16 * var8 * var8 + var14) * var3[1]);
         thisx.goto = (float)((var16 * var22 - var12 * var6) * var3[2]);
         thisx.short = (float)((var16 * var18 - var12 * var8) * var3[0]);
         thisx.long = (float)((var16 * var22 + var12 * var6) * var3[1]);
         thisx.new = (float)((var16 * var10 * var10 + var14) * var3[2]);
      }

   }

   public final void class() {
      thisx.char = 0.0F;
      thisx.float = 0.0F;
      thisx.break = 0.0F;
      thisx.catch = 0.0F;
      thisx.do = 0.0F;
      thisx.else = 0.0F;
      thisx.goto = 0.0F;
      thisx.case = 0.0F;
      thisx.short = 0.0F;
      thisx.long = 0.0F;
      thisx.new = 0.0F;
      thisx.const = 0.0F;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 0.0F;
   }

   public final void null() {
      thisx.char = -thisx.char;
      thisx.float = -thisx.float;
      thisx.break = -thisx.break;
      thisx.catch = -thisx.catch;
      thisx.do = -thisx.do;
      thisx.else = -thisx.else;
      thisx.goto = -thisx.goto;
      thisx.case = -thisx.case;
      thisx.short = -thisx.short;
      thisx.long = -thisx.long;
      thisx.new = -thisx.new;
      thisx.const = -thisx.const;
      thisx.false = -thisx.false;
      thisx.null = -thisx.null;
      thisx.int = -thisx.int;
      thisx.final = -thisx.final;
   }

   public final void null(Matrix4f arg0) {
      thisx.char = -arg0.char;
      thisx.float = -arg0.float;
      thisx.break = -arg0.break;
      thisx.catch = -arg0.catch;
      thisx.do = -arg0.do;
      thisx.else = -arg0.else;
      thisx.goto = -arg0.goto;
      thisx.case = -arg0.case;
      thisx.short = -arg0.short;
      thisx.long = -arg0.long;
      thisx.new = -arg0.new;
      thisx.const = -arg0.const;
      thisx.false = -arg0.false;
      thisx.null = -arg0.null;
      thisx.int = -arg0.int;
      thisx.final = -arg0.final;
   }

   private final void null(double[] arg0, double[] arg1) {
      double[] var3 = new double[]{(double)thisx.char, (double)thisx.float, (double)thisx.break, (double)thisx.do, (double)thisx.else, (double)thisx.goto, (double)thisx.short, (double)thisx.long, (double)thisx.new};
      Matrix3d.class(var3, arg0, arg1);
   }

   public Object clone() {
      Matrix4f var1 = null;

      try {
         var1 = (Matrix4f)super.clone();
         return var1;
      } catch (CloneNotSupportedException var3) {
         throw new InternalError();
      }
   }

   public final float if() {
      return thisx.char;
   }

   public final void if(float arg0) {
      thisx.char = arg0;
   }

   public final float case() {
      return thisx.float;
   }

   public final void case(float arg0) {
      thisx.float = arg0;
   }

   public final float for() {
      return thisx.break;
   }

   public final void for(float arg0) {
      thisx.break = arg0;
   }

   public final float false() {
      return thisx.do;
   }

   public final void false(float arg0) {
      thisx.do = arg0;
   }

   public final float float() {
      return thisx.else;
   }

   public final void float(float arg0) {
      thisx.else = arg0;
   }

   public final float char() {
      return thisx.goto;
   }

   public final void char(float arg0) {
      thisx.goto = arg0;
   }

   public final float else() {
      return thisx.short;
   }

   public final void else(float arg0) {
      thisx.short = arg0;
   }

   public final float new() {
      return thisx.long;
   }

   public final void new(float arg0) {
      thisx.long = arg0;
   }

   public final float this() {
      return thisx.new;
   }

   public final void this(float arg0) {
      thisx.new = arg0;
   }

   public final float short() {
      return thisx.catch;
   }

   public final void short(float arg0) {
      thisx.catch = arg0;
   }

   public final float do() {
      return thisx.case;
   }

   public final void do(float arg0) {
      thisx.case = arg0;
   }

   public final float true() {
      return thisx.const;
   }

   public final void true(float arg0) {
      thisx.const = arg0;
   }

   public final float const() {
      return thisx.false;
   }

   public final void const(float arg0) {
      thisx.false = arg0;
   }

   public final float long() {
      return thisx.null;
   }

   public final void long(float arg0) {
      thisx.null = arg0;
   }

   public final float class() {
      return thisx.int;
   }

   public final void class(float arg0) {
      thisx.int = arg0;
   }

   public final float null() {
      return thisx.final;
   }

   public final void null(float arg0) {
      thisx.final = arg0;
   }
}
