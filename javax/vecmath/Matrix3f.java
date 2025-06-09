package javax.vecmath;

import java.io.Serializable;
import net.daporkchop.lib.primitive.lambda.ByteByteShortConsumer;
import net.minecraftforge.client.model.b3d.B3DLoader;

public class Matrix3f implements Serializable, Cloneable {
   public static final long goto = 329697160112089834L;
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

   public Matrix3f(float arg0, float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8) {
      thisx.case = arg0;
      thisx.short = arg1;
      thisx.long = arg2;
      thisx.new = arg3;
      thisx.const = arg4;
      thisx.false = arg5;
      thisx.null = arg6;
      thisx.int = arg7;
      thisx.final = arg8;
   }

   public Matrix3f(float[] arg0) {
      thisx.case = arg0[0];
      thisx.short = arg0[1];
      thisx.long = arg0[2];
      thisx.new = arg0[3];
      thisx.const = arg0[4];
      thisx.false = arg0[5];
      thisx.null = arg0[6];
      thisx.int = arg0[7];
      thisx.final = arg0[8];
   }

   public Matrix3f(Matrix3d arg0) {
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

   public Matrix3f(Matrix3f arg0) {
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

   public Matrix3f() {
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
      return thisx.case + B3DLoader.null("\bR") + thisx.short + ByteByteShortConsumer.null("qx") + thisx.long + "\n" + thisx.new + B3DLoader.null("\bR") + thisx.const + ByteByteShortConsumer.null("qx") + thisx.false + "\n" + thisx.null + B3DLoader.null("\bR") + thisx.int + ByteByteShortConsumer.null("qx") + thisx.final + "\n";
   }

   public final void short() {
      thisx.case = 1.0F;
      thisx.short = 0.0F;
      thisx.long = 0.0F;
      thisx.new = 0.0F;
      thisx.const = 1.0F;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void if(float arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      thisx.null(var3, var2);
      thisx.case = (float)(var2[0] * (double)arg0);
      thisx.short = (float)(var2[1] * (double)arg0);
      thisx.long = (float)(var2[2] * (double)arg0);
      thisx.new = (float)(var2[3] * (double)arg0);
      thisx.const = (float)(var2[4] * (double)arg0);
      thisx.false = (float)(var2[5] * (double)arg0);
      thisx.null = (float)(var2[6] * (double)arg0);
      thisx.int = (float)(var2[7] * (double)arg0);
      thisx.final = (float)(var2[8] * (double)arg0);
   }

   public final void null(int arg0, int arg1, float arg2) {
      switch(arg0) {
      case 0:
         switch(arg1) {
         case 0:
            thisx.case = arg2;
            return;
         case 1:
            thisx.short = arg2;
            return;
         case 2:
            thisx.long = arg2;
            return;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(B3DLoader.null("?E\u0006V\u001b\\ABB")));
         }
      case 1:
         switch(arg1) {
         case 0:
            thisx.new = arg2;
            return;
         case 1:
            thisx.const = arg2;
            return;
         case 2:
            thisx.false = arg2;
            return;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(ByteByteShortConsumer.null("\u0015<,/1%k;h")));
         }
      case 2:
         switch(arg1) {
         case 0:
            thisx.null = arg2;
            return;
         case 1:
            thisx.int = arg2;
            return;
         case 2:
            thisx.final = arg2;
            return;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(B3DLoader.null("?E\u0006V\u001b\\ABB")));
         }
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(ByteByteShortConsumer.null("\u0015<,/1%k;h")));
      }
   }

   public final void const(int arg0, Vector3f arg1) {
      if (arg0 == 0) {
         arg1.int = thisx.case;
         arg1.final = thisx.short;
         arg1.this = thisx.long;
      } else if (arg0 == 1) {
         arg1.int = thisx.new;
         arg1.final = thisx.const;
         arg1.this = thisx.false;
      } else {
         if (arg0 != 2) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(B3DLoader.null("?E\u0006V\u001b\\ABC")));
         }

         arg1.int = thisx.null;
         arg1.final = thisx.int;
         arg1.this = thisx.final;
      }

   }

   public final void const(int arg0, float[] arg1) {
      if (arg0 == 0) {
         arg1[0] = thisx.case;
         arg1[1] = thisx.short;
         arg1[2] = thisx.long;
      } else if (arg0 == 1) {
         arg1[0] = thisx.new;
         arg1[1] = thisx.const;
         arg1[2] = thisx.false;
      } else {
         if (arg0 != 2) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(ByteByteShortConsumer.null("\u0015<,/1%k;i")));
         }

         arg1[0] = thisx.null;
         arg1[1] = thisx.int;
         arg1[2] = thisx.final;
      }

   }

   public final void long(int arg0, Vector3f arg1) {
      if (arg0 == 0) {
         arg1.int = thisx.case;
         arg1.final = thisx.new;
         arg1.this = thisx.null;
      } else if (arg0 == 1) {
         arg1.int = thisx.short;
         arg1.final = thisx.const;
         arg1.this = thisx.int;
      } else {
         if (arg0 != 2) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(B3DLoader.null("?E\u0006V\u001b\\ABA")));
         }

         arg1.int = thisx.long;
         arg1.final = thisx.false;
         arg1.this = thisx.final;
      }

   }

   public final void long(int arg0, float[] arg1) {
      if (arg0 == 0) {
         arg1[0] = thisx.case;
         arg1[1] = thisx.new;
         arg1[2] = thisx.null;
      } else if (arg0 == 1) {
         arg1[0] = thisx.short;
         arg1[1] = thisx.const;
         arg1[2] = thisx.int;
      } else {
         if (arg0 != 2) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(ByteByteShortConsumer.null("\u0015<,/1%k;k")));
         }

         arg1[0] = thisx.long;
         arg1[1] = thisx.false;
         arg1[2] = thisx.final;
      }

   }

   public final float null(int arg0, int arg1) {
      switch(arg0) {
      case 0:
         switch(arg1) {
         case 0:
            return thisx.case;
         case 1:
            return thisx.short;
         case 2:
            return thisx.long;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(B3DLoader.null("?E\u0006V\u001b\\ABG")));
         }
      case 1:
         switch(arg1) {
         case 0:
            return thisx.new;
         case 1:
            return thisx.const;
         case 2:
            return thisx.false;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(B3DLoader.null("?E\u0006V\u001b\\ABG")));
         }
      case 2:
         switch(arg1) {
         case 0:
            return thisx.null;
         case 1:
            return thisx.int;
         case 2:
            return thisx.final;
         }
      }

      throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(B3DLoader.null("?E\u0006V\u001b\\ABG")));
   }

   public final void class(int arg0, float arg1, float arg2, float arg3) {
      switch(arg0) {
      case 0:
         thisx.case = arg1;
         thisx.short = arg2;
         thisx.long = arg3;
         break;
      case 1:
         thisx.new = arg1;
         thisx.const = arg2;
         thisx.false = arg3;
         break;
      case 2:
         thisx.null = arg1;
         thisx.int = arg2;
         thisx.final = arg3;
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(ByteByteShortConsumer.null("\u0015<,/1%k;n")));
      }

   }

   public final void class(int arg0, Vector3f arg1) {
      switch(arg0) {
      case 0:
         thisx.case = arg1.int;
         thisx.short = arg1.final;
         thisx.long = arg1.this;
         break;
      case 1:
         thisx.new = arg1.int;
         thisx.const = arg1.final;
         thisx.false = arg1.this;
         break;
      case 2:
         thisx.null = arg1.int;
         thisx.int = arg1.final;
         thisx.final = arg1.this;
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(B3DLoader.null("?E\u0006V\u001b\\ABD")));
      }

   }

   public final void class(int arg0, float[] arg1) {
      switch(arg0) {
      case 0:
         thisx.case = arg1[0];
         thisx.short = arg1[1];
         thisx.long = arg1[2];
         break;
      case 1:
         thisx.new = arg1[0];
         thisx.const = arg1[1];
         thisx.false = arg1[2];
         break;
      case 2:
         thisx.null = arg1[0];
         thisx.int = arg1[1];
         thisx.final = arg1[2];
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(ByteByteShortConsumer.null("\u0015<,/1%k;n")));
      }

   }

   public final void null(int arg0, float arg1, float arg2, float arg3) {
      switch(arg0) {
      case 0:
         thisx.case = arg1;
         thisx.new = arg2;
         thisx.null = arg3;
         break;
      case 1:
         thisx.short = arg1;
         thisx.const = arg2;
         thisx.int = arg3;
         break;
      case 2:
         thisx.long = arg1;
         thisx.false = arg2;
         thisx.final = arg3;
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(B3DLoader.null("?E\u0006V\u001b\\ABK")));
      }

   }

   public final void null(int arg0, Vector3f arg1) {
      switch(arg0) {
      case 0:
         thisx.case = arg1.int;
         thisx.new = arg1.final;
         thisx.null = arg1.this;
         break;
      case 1:
         thisx.short = arg1.int;
         thisx.const = arg1.final;
         thisx.int = arg1.this;
         break;
      case 2:
         thisx.long = arg1.int;
         thisx.false = arg1.final;
         thisx.final = arg1.this;
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(ByteByteShortConsumer.null("\u0015<,/1%k;a")));
      }

   }

   public final void null(int arg0, float[] arg1) {
      switch(arg0) {
      case 0:
         thisx.case = arg1[0];
         thisx.new = arg1[1];
         thisx.null = arg1[2];
         break;
      case 1:
         thisx.short = arg1[0];
         thisx.const = arg1[1];
         thisx.int = arg1[2];
         break;
      case 2:
         thisx.long = arg1[0];
         thisx.false = arg1[1];
         thisx.final = arg1[2];
         break;
      default:
         throw new ArrayIndexOutOfBoundsException(VecMathI18N.null(B3DLoader.null("?E\u0006V\u001b\\ABK")));
      }

   }

   public final float char() {
      double[] var1 = new double[9];
      double[] var2 = new double[3];
      thisx.null(var2, var1);
      return (float)Matrix3d.null(var2);
   }

   public final void case(float arg0) {
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

   public final void class(float arg0, Matrix3f arg1) {
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

   public final void short(Matrix3f arg0, Matrix3f arg1) {
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

   public final void char(Matrix3f arg0) {
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

   public final void do(Matrix3f arg0, Matrix3f arg1) {
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

   public final void else(Matrix3f arg0) {
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

   public final void do() {
      float var1 = thisx.new;
      thisx.new = thisx.short;
      thisx.short = var1;
      var1 = thisx.null;
      thisx.null = thisx.long;
      thisx.long = var1;
      var1 = thisx.int;
      thisx.int = thisx.false;
      thisx.false = var1;
   }

   public final void new(Matrix3f arg0) {
      if (thisx != arg0) {
         thisx.case = arg0.case;
         thisx.short = arg0.new;
         thisx.long = arg0.null;
         thisx.new = arg0.short;
         thisx.const = arg0.const;
         thisx.false = arg0.int;
         thisx.null = arg0.long;
         thisx.int = arg0.false;
         thisx.final = arg0.final;
      } else {
         thisx.do();
      }

   }

   public final void null(Quat4f arg0) {
      thisx.case = 1.0F - 2.0F * arg0.int * arg0.int - 2.0F * arg0.final * arg0.final;
      thisx.new = 2.0F * (arg0.null * arg0.int + arg0.this * arg0.final);
      thisx.null = 2.0F * (arg0.null * arg0.final - arg0.this * arg0.int);
      thisx.short = 2.0F * (arg0.null * arg0.int - arg0.this * arg0.final);
      thisx.const = 1.0F - 2.0F * arg0.null * arg0.null - 2.0F * arg0.final * arg0.final;
      thisx.int = 2.0F * (arg0.int * arg0.final + arg0.this * arg0.null);
      thisx.long = 2.0F * (arg0.null * arg0.final + arg0.this * arg0.int);
      thisx.false = 2.0F * (arg0.int * arg0.final - arg0.this * arg0.null);
      thisx.final = 1.0F - 2.0F * arg0.null * arg0.null - 2.0F * arg0.int * arg0.int;
   }

   public final void null(AxisAngle4f arg0) {
      float var2 = (float)Math.sqrt((double)(arg0.false * arg0.false + arg0.null * arg0.null + arg0.int * arg0.int));
      if ((double)var2 < 1.0E-8D) {
         thisx.case = 1.0F;
         thisx.short = 0.0F;
         thisx.long = 0.0F;
         thisx.new = 0.0F;
         thisx.const = 1.0F;
         thisx.false = 0.0F;
         thisx.null = 0.0F;
         thisx.int = 0.0F;
         thisx.final = 1.0F;
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
         thisx.case = var8 * var3 * var3 + var7;
         thisx.short = var8 * var10 - var6 * var5;
         thisx.long = var8 * var9 + var6 * var4;
         thisx.new = var8 * var10 + var6 * var5;
         thisx.const = var8 * var4 * var4 + var7;
         thisx.false = var8 * var11 - var6 * var3;
         thisx.null = var8 * var9 - var6 * var4;
         thisx.int = var8 * var11 + var6 * var3;
         thisx.final = var8 * var5 * var5 + var7;
      }

   }

   public final void null(AxisAngle4d arg0) {
      double var2 = Math.sqrt(arg0.false * arg0.false + arg0.null * arg0.null + arg0.int * arg0.int);
      if (var2 < 1.0E-8D) {
         thisx.case = 1.0F;
         thisx.short = 0.0F;
         thisx.long = 0.0F;
         thisx.new = 0.0F;
         thisx.const = 1.0F;
         thisx.false = 0.0F;
         thisx.null = 0.0F;
         thisx.int = 0.0F;
         thisx.final = 1.0F;
      } else {
         var2 = 1.0D / var2;
         double var4 = arg0.false * var2;
         double var6 = arg0.null * var2;
         double var8 = arg0.int * var2;
         double var10 = Math.sin(arg0.final);
         double var12 = Math.cos(arg0.final);
         double var14 = 1.0D - var12;
         double var16 = var4 * var8;
         double var18 = var4 * var6;
         double var20 = var6 * var8;
         thisx.case = (float)(var14 * var4 * var4 + var12);
         thisx.short = (float)(var14 * var18 - var10 * var8);
         thisx.long = (float)(var14 * var16 + var10 * var6);
         thisx.new = (float)(var14 * var18 + var10 * var8);
         thisx.const = (float)(var14 * var6 * var6 + var12);
         thisx.false = (float)(var14 * var20 - var10 * var4);
         thisx.null = (float)(var14 * var16 - var10 * var6);
         thisx.int = (float)(var14 * var20 + var10 * var4);
         thisx.final = (float)(var14 * var8 * var8 + var12);
      }

   }

   public final void null(Quat4d arg0) {
      thisx.case = (float)(1.0D - 2.0D * arg0.int * arg0.int - 2.0D * arg0.final * arg0.final);
      thisx.new = (float)(2.0D * (arg0.null * arg0.int + arg0.this * arg0.final));
      thisx.null = (float)(2.0D * (arg0.null * arg0.final - arg0.this * arg0.int));
      thisx.short = (float)(2.0D * (arg0.null * arg0.int - arg0.this * arg0.final));
      thisx.const = (float)(1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.final * arg0.final);
      thisx.int = (float)(2.0D * (arg0.int * arg0.final + arg0.this * arg0.null));
      thisx.long = (float)(2.0D * (arg0.null * arg0.final + arg0.this * arg0.int));
      thisx.false = (float)(2.0D * (arg0.int * arg0.final - arg0.this * arg0.null));
      thisx.final = (float)(1.0D - 2.0D * arg0.null * arg0.null - 2.0D * arg0.int * arg0.int);
   }

   public final void null(float[] arg0) {
      thisx.case = arg0[0];
      thisx.short = arg0[1];
      thisx.long = arg0[2];
      thisx.new = arg0[3];
      thisx.const = arg0[4];
      thisx.false = arg0[5];
      thisx.null = arg0[6];
      thisx.int = arg0[7];
      thisx.final = arg0[8];
   }

   public final void this(Matrix3f arg0) {
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

   public final void null(Matrix3d arg0) {
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

   public final void short(Matrix3f arg0) {
      thisx.do(arg0);
   }

   public final void true() {
      thisx.do(thisx);
   }

   private final void do(Matrix3f arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[9];
      int[] var4 = new int[3];
      var2[0] = (double)arg0.case;
      var2[1] = (double)arg0.short;
      var2[2] = (double)arg0.long;
      var2[3] = (double)arg0.new;
      var2[4] = (double)arg0.const;
      var2[5] = (double)arg0.false;
      var2[6] = (double)arg0.null;
      var2[7] = (double)arg0.int;
      var2[8] = (double)arg0.final;
      if (!null(var2, var4)) {
         throw new SingularMatrixException(VecMathI18N.null(ByteByteShortConsumer.null("\u00109)*4 n>lj")));
      } else {
         for(int var5 = 0; var5 < 9; ++var5) {
            var3[var5] = 0.0D;
         }

         var3[0] = 1.0D;
         var3[4] = 1.0D;
         var3[8] = 1.0D;
         null(var2, var4, var3);
         thisx.case = (float)var3[0];
         thisx.short = (float)var3[1];
         thisx.long = (float)var3[2];
         thisx.new = (float)var3[3];
         thisx.const = (float)var3[4];
         thisx.false = (float)var3[5];
         thisx.null = (float)var3[6];
         thisx.int = (float)var3[7];
         thisx.final = (float)var3[8];
      }
   }

   public static boolean null(double[] arg0, int[] arg1) {
      double[] var2 = new double[3];
      int var5 = 0;
      int var6 = 0;

      int var3;
      double var7;
      for(var3 = 3; var3-- != 0; var2[var6++] = 1.0D / var7) {
         var7 = 0.0D;
         int var4 = 3;

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

      for(var3 = 0; var3 < 3; ++var3) {
         int var8;
         int var10;
         double var11;
         int var18;
         int var19;
         for(var5 = 0; var5 < var3; ++var5) {
            var8 = var17 + 3 * var5 + var3;
            var11 = arg0[var8];
            var18 = var5;
            var19 = var17 + 3 * var5;

            for(var10 = var17 + var3; var18-- != 0; var10 += 3) {
               var11 -= arg0[var19] * arg0[var10];
               ++var19;
            }

            arg0[var8] = var11;
         }

         double var13 = 0.0D;
         var6 = -1;

         double var15;
         for(var5 = var3; var5 < 3; ++var5) {
            var8 = var17 + 3 * var5 + var3;
            var11 = arg0[var8];
            var18 = var3;
            var19 = var17 + 3 * var5;

            for(var10 = var17 + var3; var18-- != 0; var10 += 3) {
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
            throw new RuntimeException(VecMathI18N.null(B3DLoader.null("i\u0013P\u0000M\n\u0017\u0014\u0015A")));
         }

         if (var3 != var6) {
            var18 = 3;
            var19 = var17 + 3 * var6;

            for(var10 = var17 + 3 * var3; var18-- != 0; arg0[var10++] = var15) {
               var15 = arg0[var19];
               arg0[var19++] = arg0[var10];
            }

            var2[var6] = var2[var3];
         }

         arg1[var3] = var6;
         if (arg0[var17 + 3 * var3 + var3] == 0.0D) {
            return false;
         }

         if (var3 != 2) {
            var15 = 1.0D / arg0[var17 + 3 * var3 + var3];
            var8 = var17 + 3 * (var3 + 1) + var3;

            for(var5 = 2 - var3; var5-- != 0; var8 += 3) {
               arg0[var8] *= var15;
            }
         }
      }

      return true;
   }

   public static void null(double[] arg0, int[] arg1, double[] arg2) {
      byte var8 = 0;

      for(int var7 = 0; var7 < 3; ++var7) {
         int var9 = var7;
         int var4 = -1;

         int var10;
         for(int var3 = 0; var3 < 3; ++var3) {
            int var5 = arg1[var8 + var3];
            double var11 = arg2[var9 + 3 * var5];
            arg2[var9 + 3 * var5] = arg2[var9 + 3 * var3];
            if (var4 >= 0) {
               var10 = var3 * 3;

               for(int var6 = var4; var6 <= var3 - 1; ++var6) {
                  var11 -= arg0[var10 + var6] * arg2[var9 + 3 * var6];
               }
            } else if (var11 != 0.0D) {
               var4 = var3;
            }

            arg2[var9 + 3 * var3] = var11;
         }

         byte var13 = 6;
         arg2[var9 + 6] /= arg0[var13 + 2];
         var10 = var13 - 3;
         arg2[var9 + 3] = (arg2[var9 + 3] - arg0[var10 + 2] * arg2[var9 + 6]) / arg0[var10 + 1];
         var10 -= 3;
         arg2[var9 + 0] = (arg2[var9 + 0] - arg0[var10 + 1] * arg2[var9 + 3] - arg0[var10 + 2] * arg2[var9 + 6]) / arg0[var10 + 0];
      }

   }

   public final float else() {
      float var1 = thisx.case * (thisx.const * thisx.final - thisx.false * thisx.int) + thisx.short * (thisx.false * thisx.null - thisx.new * thisx.final) + thisx.long * (thisx.new * thisx.int - thisx.const * thisx.null);
      return var1;
   }

   public final void for(float arg0) {
      thisx.case = arg0;
      thisx.short = 0.0F;
      thisx.long = 0.0F;
      thisx.new = 0.0F;
      thisx.const = arg0;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = arg0;
   }

   public final void false(float arg0) {
      float var2 = (float)Math.sin((double)arg0);
      float var3 = (float)Math.cos((double)arg0);
      thisx.case = 1.0F;
      thisx.short = 0.0F;
      thisx.long = 0.0F;
      thisx.new = 0.0F;
      thisx.const = var3;
      thisx.false = -var2;
      thisx.null = 0.0F;
      thisx.int = var2;
      thisx.final = var3;
   }

   public final void float(float arg0) {
      float var2 = (float)Math.sin((double)arg0);
      float var3 = (float)Math.cos((double)arg0);
      thisx.case = var3;
      thisx.short = 0.0F;
      thisx.long = var2;
      thisx.new = 0.0F;
      thisx.const = 1.0F;
      thisx.false = 0.0F;
      thisx.null = -var2;
      thisx.int = 0.0F;
      thisx.final = var3;
   }

   public final void char(float arg0) {
      float var2 = (float)Math.sin((double)arg0);
      float var3 = (float)Math.cos((double)arg0);
      thisx.case = var3;
      thisx.short = -var2;
      thisx.long = 0.0F;
      thisx.new = var2;
      thisx.const = var3;
      thisx.false = 0.0F;
      thisx.null = 0.0F;
      thisx.int = 0.0F;
      thisx.final = 1.0F;
   }

   public final void else(float arg0) {
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

   public final void null(float arg0, Matrix3f arg1) {
      thisx.case = arg0 * arg1.case;
      thisx.short = arg0 * arg1.short;
      thisx.long = arg0 * arg1.long;
      thisx.new = arg0 * arg1.new;
      thisx.const = arg0 * arg1.const;
      thisx.false = arg0 * arg1.false;
      thisx.null = arg0 * arg1.null;
      thisx.int = arg0 * arg1.int;
      thisx.final = arg0 * arg1.final;
   }

   public final void true(Matrix3f arg0) {
      float var2 = thisx.case * arg0.case + thisx.short * arg0.new + thisx.long * arg0.null;
      float var3 = thisx.case * arg0.short + thisx.short * arg0.const + thisx.long * arg0.int;
      float var4 = thisx.case * arg0.long + thisx.short * arg0.false + thisx.long * arg0.final;
      float var5 = thisx.new * arg0.case + thisx.const * arg0.new + thisx.false * arg0.null;
      float var6 = thisx.new * arg0.short + thisx.const * arg0.const + thisx.false * arg0.int;
      float var7 = thisx.new * arg0.long + thisx.const * arg0.false + thisx.false * arg0.final;
      float var8 = thisx.null * arg0.case + thisx.int * arg0.new + thisx.final * arg0.null;
      float var9 = thisx.null * arg0.short + thisx.int * arg0.const + thisx.final * arg0.int;
      float var10 = thisx.null * arg0.long + thisx.int * arg0.false + thisx.final * arg0.final;
      thisx.case = var2;
      thisx.short = var3;
      thisx.long = var4;
      thisx.new = var5;
      thisx.const = var6;
      thisx.false = var7;
      thisx.null = var8;
      thisx.int = var9;
      thisx.final = var10;
   }

   public final void true(Matrix3f arg0, Matrix3f arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.case = arg0.case * arg1.case + arg0.short * arg1.new + arg0.long * arg1.null;
         thisx.short = arg0.case * arg1.short + arg0.short * arg1.const + arg0.long * arg1.int;
         thisx.long = arg0.case * arg1.long + arg0.short * arg1.false + arg0.long * arg1.final;
         thisx.new = arg0.new * arg1.case + arg0.const * arg1.new + arg0.false * arg1.null;
         thisx.const = arg0.new * arg1.short + arg0.const * arg1.const + arg0.false * arg1.int;
         thisx.false = arg0.new * arg1.long + arg0.const * arg1.false + arg0.false * arg1.final;
         thisx.null = arg0.null * arg1.case + arg0.int * arg1.new + arg0.final * arg1.null;
         thisx.int = arg0.null * arg1.short + arg0.int * arg1.const + arg0.final * arg1.int;
         thisx.final = arg0.null * arg1.long + arg0.int * arg1.false + arg0.final * arg1.final;
      } else {
         float var3 = arg0.case * arg1.case + arg0.short * arg1.new + arg0.long * arg1.null;
         float var4 = arg0.case * arg1.short + arg0.short * arg1.const + arg0.long * arg1.int;
         float var5 = arg0.case * arg1.long + arg0.short * arg1.false + arg0.long * arg1.final;
         float var6 = arg0.new * arg1.case + arg0.const * arg1.new + arg0.false * arg1.null;
         float var7 = arg0.new * arg1.short + arg0.const * arg1.const + arg0.false * arg1.int;
         float var8 = arg0.new * arg1.long + arg0.const * arg1.false + arg0.false * arg1.final;
         float var9 = arg0.null * arg1.case + arg0.int * arg1.new + arg0.final * arg1.null;
         float var10 = arg0.null * arg1.short + arg0.int * arg1.const + arg0.final * arg1.int;
         float var11 = arg0.null * arg1.long + arg0.int * arg1.false + arg0.final * arg1.final;
         thisx.case = var3;
         thisx.short = var4;
         thisx.long = var5;
         thisx.new = var6;
         thisx.const = var7;
         thisx.false = var8;
         thisx.null = var9;
         thisx.int = var10;
         thisx.final = var11;
      }

   }

   public final void const(Matrix3f arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[9];
      double[] var4 = new double[3];
      var2[0] = (double)(thisx.case * arg0.case + thisx.short * arg0.new + thisx.long * arg0.null);
      var2[1] = (double)(thisx.case * arg0.short + thisx.short * arg0.const + thisx.long * arg0.int);
      var2[2] = (double)(thisx.case * arg0.long + thisx.short * arg0.false + thisx.long * arg0.final);
      var2[3] = (double)(thisx.new * arg0.case + thisx.const * arg0.new + thisx.false * arg0.null);
      var2[4] = (double)(thisx.new * arg0.short + thisx.const * arg0.const + thisx.false * arg0.int);
      var2[5] = (double)(thisx.new * arg0.long + thisx.const * arg0.false + thisx.false * arg0.final);
      var2[6] = (double)(thisx.null * arg0.case + thisx.int * arg0.new + thisx.final * arg0.null);
      var2[7] = (double)(thisx.null * arg0.short + thisx.int * arg0.const + thisx.final * arg0.int);
      var2[8] = (double)(thisx.null * arg0.long + thisx.int * arg0.false + thisx.final * arg0.final);
      Matrix3d.class(var2, var4, var3);
      thisx.case = (float)var3[0];
      thisx.short = (float)var3[1];
      thisx.long = (float)var3[2];
      thisx.new = (float)var3[3];
      thisx.const = (float)var3[4];
      thisx.false = (float)var3[5];
      thisx.null = (float)var3[6];
      thisx.int = (float)var3[7];
      thisx.final = (float)var3[8];
   }

   public final void const(Matrix3f arg0, Matrix3f arg1) {
      double[] var3 = new double[9];
      double[] var4 = new double[9];
      double[] var5 = new double[3];
      var3[0] = (double)(arg0.case * arg1.case + arg0.short * arg1.new + arg0.long * arg1.null);
      var3[1] = (double)(arg0.case * arg1.short + arg0.short * arg1.const + arg0.long * arg1.int);
      var3[2] = (double)(arg0.case * arg1.long + arg0.short * arg1.false + arg0.long * arg1.final);
      var3[3] = (double)(arg0.new * arg1.case + arg0.const * arg1.new + arg0.false * arg1.null);
      var3[4] = (double)(arg0.new * arg1.short + arg0.const * arg1.const + arg0.false * arg1.int);
      var3[5] = (double)(arg0.new * arg1.long + arg0.const * arg1.false + arg0.false * arg1.final);
      var3[6] = (double)(arg0.null * arg1.case + arg0.int * arg1.new + arg0.final * arg1.null);
      var3[7] = (double)(arg0.null * arg1.short + arg0.int * arg1.const + arg0.final * arg1.int);
      var3[8] = (double)(arg0.null * arg1.long + arg0.int * arg1.false + arg0.final * arg1.final);
      Matrix3d.class(var3, var5, var4);
      thisx.case = (float)var4[0];
      thisx.short = (float)var4[1];
      thisx.long = (float)var4[2];
      thisx.new = (float)var4[3];
      thisx.const = (float)var4[4];
      thisx.false = (float)var4[5];
      thisx.null = (float)var4[6];
      thisx.int = (float)var4[7];
      thisx.final = (float)var4[8];
   }

   public final void long(Matrix3f arg0, Matrix3f arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.case = arg0.case * arg1.case + arg0.new * arg1.short + arg0.null * arg1.long;
         thisx.short = arg0.case * arg1.new + arg0.new * arg1.const + arg0.null * arg1.false;
         thisx.long = arg0.case * arg1.null + arg0.new * arg1.int + arg0.null * arg1.final;
         thisx.new = arg0.short * arg1.case + arg0.const * arg1.short + arg0.int * arg1.long;
         thisx.const = arg0.short * arg1.new + arg0.const * arg1.const + arg0.int * arg1.false;
         thisx.false = arg0.short * arg1.null + arg0.const * arg1.int + arg0.int * arg1.final;
         thisx.null = arg0.long * arg1.case + arg0.false * arg1.short + arg0.final * arg1.long;
         thisx.int = arg0.long * arg1.new + arg0.false * arg1.const + arg0.final * arg1.false;
         thisx.final = arg0.long * arg1.null + arg0.false * arg1.int + arg0.final * arg1.final;
      } else {
         float var3 = arg0.case * arg1.case + arg0.new * arg1.short + arg0.null * arg1.long;
         float var4 = arg0.case * arg1.new + arg0.new * arg1.const + arg0.null * arg1.false;
         float var5 = arg0.case * arg1.null + arg0.new * arg1.int + arg0.null * arg1.final;
         float var6 = arg0.short * arg1.case + arg0.const * arg1.short + arg0.int * arg1.long;
         float var7 = arg0.short * arg1.new + arg0.const * arg1.const + arg0.int * arg1.false;
         float var8 = arg0.short * arg1.null + arg0.const * arg1.int + arg0.int * arg1.final;
         float var9 = arg0.long * arg1.case + arg0.false * arg1.short + arg0.final * arg1.long;
         float var10 = arg0.long * arg1.new + arg0.false * arg1.const + arg0.final * arg1.false;
         float var11 = arg0.long * arg1.null + arg0.false * arg1.int + arg0.final * arg1.final;
         thisx.case = var3;
         thisx.short = var4;
         thisx.long = var5;
         thisx.new = var6;
         thisx.const = var7;
         thisx.false = var8;
         thisx.null = var9;
         thisx.int = var10;
         thisx.final = var11;
      }

   }

   public final void class(Matrix3f arg0, Matrix3f arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.case = arg0.case * arg1.case + arg0.short * arg1.short + arg0.long * arg1.long;
         thisx.short = arg0.case * arg1.new + arg0.short * arg1.const + arg0.long * arg1.false;
         thisx.long = arg0.case * arg1.null + arg0.short * arg1.int + arg0.long * arg1.final;
         thisx.new = arg0.new * arg1.case + arg0.const * arg1.short + arg0.false * arg1.long;
         thisx.const = arg0.new * arg1.new + arg0.const * arg1.const + arg0.false * arg1.false;
         thisx.false = arg0.new * arg1.null + arg0.const * arg1.int + arg0.false * arg1.final;
         thisx.null = arg0.null * arg1.case + arg0.int * arg1.short + arg0.final * arg1.long;
         thisx.int = arg0.null * arg1.new + arg0.int * arg1.const + arg0.final * arg1.false;
         thisx.final = arg0.null * arg1.null + arg0.int * arg1.int + arg0.final * arg1.final;
      } else {
         float var3 = arg0.case * arg1.case + arg0.short * arg1.short + arg0.long * arg1.long;
         float var4 = arg0.case * arg1.new + arg0.short * arg1.const + arg0.long * arg1.false;
         float var5 = arg0.case * arg1.null + arg0.short * arg1.int + arg0.long * arg1.final;
         float var6 = arg0.new * arg1.case + arg0.const * arg1.short + arg0.false * arg1.long;
         float var7 = arg0.new * arg1.new + arg0.const * arg1.const + arg0.false * arg1.false;
         float var8 = arg0.new * arg1.null + arg0.const * arg1.int + arg0.false * arg1.final;
         float var9 = arg0.null * arg1.case + arg0.int * arg1.short + arg0.final * arg1.long;
         float var10 = arg0.null * arg1.new + arg0.int * arg1.const + arg0.final * arg1.false;
         float var11 = arg0.null * arg1.null + arg0.int * arg1.int + arg0.final * arg1.final;
         thisx.case = var3;
         thisx.short = var4;
         thisx.long = var5;
         thisx.new = var6;
         thisx.const = var7;
         thisx.false = var8;
         thisx.null = var9;
         thisx.int = var10;
         thisx.final = var11;
      }

   }

   public final void null(Matrix3f arg0, Matrix3f arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.case = arg0.case * arg1.case + arg0.new * arg1.new + arg0.null * arg1.null;
         thisx.short = arg0.case * arg1.short + arg0.new * arg1.const + arg0.null * arg1.int;
         thisx.long = arg0.case * arg1.long + arg0.new * arg1.false + arg0.null * arg1.final;
         thisx.new = arg0.short * arg1.case + arg0.const * arg1.new + arg0.int * arg1.null;
         thisx.const = arg0.short * arg1.short + arg0.const * arg1.const + arg0.int * arg1.int;
         thisx.false = arg0.short * arg1.long + arg0.const * arg1.false + arg0.int * arg1.final;
         thisx.null = arg0.long * arg1.case + arg0.false * arg1.new + arg0.final * arg1.null;
         thisx.int = arg0.long * arg1.short + arg0.false * arg1.const + arg0.final * arg1.int;
         thisx.final = arg0.long * arg1.long + arg0.false * arg1.false + arg0.final * arg1.final;
      } else {
         float var3 = arg0.case * arg1.case + arg0.new * arg1.new + arg0.null * arg1.null;
         float var4 = arg0.case * arg1.short + arg0.new * arg1.const + arg0.null * arg1.int;
         float var5 = arg0.case * arg1.long + arg0.new * arg1.false + arg0.null * arg1.final;
         float var6 = arg0.short * arg1.case + arg0.const * arg1.new + arg0.int * arg1.null;
         float var7 = arg0.short * arg1.short + arg0.const * arg1.const + arg0.int * arg1.int;
         float var8 = arg0.short * arg1.long + arg0.const * arg1.false + arg0.int * arg1.final;
         float var9 = arg0.long * arg1.case + arg0.false * arg1.new + arg0.final * arg1.null;
         float var10 = arg0.long * arg1.short + arg0.false * arg1.const + arg0.final * arg1.int;
         float var11 = arg0.long * arg1.long + arg0.false * arg1.false + arg0.final * arg1.final;
         thisx.case = var3;
         thisx.short = var4;
         thisx.long = var5;
         thisx.new = var6;
         thisx.const = var7;
         thisx.false = var8;
         thisx.null = var9;
         thisx.int = var10;
         thisx.final = var11;
      }

   }

   public final void const() {
      double[] var1 = new double[9];
      double[] var2 = new double[3];
      thisx.null(var2, var1);
      thisx.case = (float)var1[0];
      thisx.short = (float)var1[1];
      thisx.long = (float)var1[2];
      thisx.new = (float)var1[3];
      thisx.const = (float)var1[4];
      thisx.false = (float)var1[5];
      thisx.null = (float)var1[6];
      thisx.int = (float)var1[7];
      thisx.final = (float)var1[8];
   }

   public final void long(Matrix3f arg0) {
      double[] var2 = new double[9];
      double[] var3 = new double[9];
      double[] var4 = new double[3];
      var2[0] = (double)arg0.case;
      var2[1] = (double)arg0.short;
      var2[2] = (double)arg0.long;
      var2[3] = (double)arg0.new;
      var2[4] = (double)arg0.const;
      var2[5] = (double)arg0.false;
      var2[6] = (double)arg0.null;
      var2[7] = (double)arg0.int;
      var2[8] = (double)arg0.final;
      Matrix3d.class(var2, var4, var3);
      thisx.case = (float)var3[0];
      thisx.short = (float)var3[1];
      thisx.long = (float)var3[2];
      thisx.new = (float)var3[3];
      thisx.const = (float)var3[4];
      thisx.false = (float)var3[5];
      thisx.null = (float)var3[6];
      thisx.int = (float)var3[7];
      thisx.final = (float)var3[8];
   }

   public final void long() {
      float var1 = 1.0F / (float)Math.sqrt((double)(thisx.case * thisx.case + thisx.new * thisx.new + thisx.null * thisx.null));
      thisx.case *= var1;
      thisx.new *= var1;
      thisx.null *= var1;
      var1 = 1.0F / (float)Math.sqrt((double)(thisx.short * thisx.short + thisx.const * thisx.const + thisx.int * thisx.int));
      thisx.short *= var1;
      thisx.const *= var1;
      thisx.int *= var1;
      thisx.long = thisx.new * thisx.int - thisx.const * thisx.null;
      thisx.false = thisx.short * thisx.null - thisx.case * thisx.int;
      thisx.final = thisx.case * thisx.const - thisx.short * thisx.new;
   }

   public final void class(Matrix3f arg0) {
      float var2 = 1.0F / (float)Math.sqrt((double)(arg0.case * arg0.case + arg0.new * arg0.new + arg0.null * arg0.null));
      thisx.case = arg0.case * var2;
      thisx.new = arg0.new * var2;
      thisx.null = arg0.null * var2;
      var2 = 1.0F / (float)Math.sqrt((double)(arg0.short * arg0.short + arg0.const * arg0.const + arg0.int * arg0.int));
      thisx.short = arg0.short * var2;
      thisx.const = arg0.const * var2;
      thisx.int = arg0.int * var2;
      thisx.long = thisx.new * thisx.int - thisx.const * thisx.null;
      thisx.false = thisx.short * thisx.null - thisx.case * thisx.int;
      thisx.final = thisx.case * thisx.const - thisx.short * thisx.new;
   }

   public boolean null(Matrix3f arg0) {
      try {
         return thisx.case == arg0.case && thisx.short == arg0.short && thisx.long == arg0.long && thisx.new == arg0.new && thisx.const == arg0.const && thisx.false == arg0.false && thisx.null == arg0.null && thisx.int == arg0.int && thisx.final == arg0.final;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public boolean equals(Object arg0) {
      try {
         Matrix3f var2 = (Matrix3f)arg0;
         return thisx.case == var2.case && thisx.short == var2.short && thisx.long == var2.long && thisx.new == var2.new && thisx.const == var2.const && thisx.false == var2.false && thisx.null == var2.null && thisx.int == var2.int && thisx.final == var2.final;
      } catch (ClassCastException var3) {
         return false;
      } catch (NullPointerException var4) {
         return false;
      }
   }

   public boolean null(Matrix3f arg0, float arg1) {
      boolean var3 = true;
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

   public final void class() {
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

   public final void null(Matrix3f arg0) {
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

   public final void null(Tuple3f arg0) {
      float var2 = thisx.case * arg0.int + thisx.short * arg0.final + thisx.long * arg0.this;
      float var3 = thisx.new * arg0.int + thisx.const * arg0.final + thisx.false * arg0.this;
      float var4 = thisx.null * arg0.int + thisx.int * arg0.final + thisx.final * arg0.this;
      arg0.null(var2, var3, var4);
   }

   public final void null(Tuple3f arg0, Tuple3f arg1) {
      float var3 = thisx.case * arg0.int + thisx.short * arg0.final + thisx.long * arg0.this;
      float var4 = thisx.new * arg0.int + thisx.const * arg0.final + thisx.false * arg0.this;
      arg1.this = thisx.null * arg0.int + thisx.int * arg0.final + thisx.final * arg0.this;
      arg1.int = var3;
      arg1.final = var4;
   }

   public void null(double[] arg0, double[] arg1) {
      double[] var3 = new double[]{(double)thisx.case, (double)thisx.short, (double)thisx.long, (double)thisx.new, (double)thisx.const, (double)thisx.false, (double)thisx.null, (double)thisx.int, (double)thisx.final};
      Matrix3d.class(var3, arg0, arg1);
   }

   public Object clone() {
      Matrix3f var1 = null;

      try {
         var1 = (Matrix3f)super.clone();
         return var1;
      } catch (CloneNotSupportedException var3) {
         throw new InternalError();
      }
   }

   public final float new() {
      return thisx.case;
   }

   public final void new(float arg0) {
      thisx.case = arg0;
   }

   public final float this() {
      return thisx.short;
   }

   public final void this(float arg0) {
      thisx.short = arg0;
   }

   public final float short() {
      return thisx.long;
   }

   public final void short(float arg0) {
      thisx.long = arg0;
   }

   public final float do() {
      return thisx.new;
   }

   public final void do(float arg0) {
      thisx.new = arg0;
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

   public static String null(String arg0) {
      int var10000 = 2 << 3 ^ 2 ^ 5;
      int var10001 = (2 ^ 5) << 4 ^ 1 << 1;
      int var10002 = (2 ^ 5) << 3 ^ 2;
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
