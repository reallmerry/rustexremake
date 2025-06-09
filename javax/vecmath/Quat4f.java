package javax.vecmath;

import java.io.Serializable;

public class Quat4f extends Tuple4f implements Serializable {
   public static final long false = 2675933778405442383L;
   public static final double int = 1.0E-6D;
   public static final double final = 1.0E-30D;
   public static final double this = 1.57079632679D;

   public Quat4f(float arg0, float arg1, float arg2, float arg3) {
      float var5 = (float)(1.0D / Math.sqrt((double)(arg0 * arg0 + arg1 * arg1 + arg2 * arg2 + arg3 * arg3)));
      thisx.null = arg0 * var5;
      thisx.int = arg1 * var5;
      thisx.final = arg2 * var5;
      thisx.this = arg3 * var5;
   }

   public Quat4f(float[] arg0) {
      float var2 = (float)(1.0D / Math.sqrt((double)(arg0[0] * arg0[0] + arg0[1] * arg0[1] + arg0[2] * arg0[2] + arg0[3] * arg0[3])));
      thisx.null = arg0[0] * var2;
      thisx.int = arg0[1] * var2;
      thisx.final = arg0[2] * var2;
      thisx.this = arg0[3] * var2;
   }

   public Quat4f(Quat4f arg0) {
      super((Tuple4f)arg0);
   }

   public Quat4f(Quat4d arg0) {
      super((Tuple4d)arg0);
   }

   public Quat4f(Tuple4f arg0) {
      float var2 = (float)(1.0D / Math.sqrt((double)(arg0.null * arg0.null + arg0.int * arg0.int + arg0.final * arg0.final + arg0.this * arg0.this)));
      thisx.null = arg0.null * var2;
      thisx.int = arg0.int * var2;
      thisx.final = arg0.final * var2;
      thisx.this = arg0.this * var2;
   }

   public Quat4f(Tuple4d arg0) {
      double var2 = 1.0D / Math.sqrt(arg0.null * arg0.null + arg0.int * arg0.int + arg0.final * arg0.final + arg0.this * arg0.this);
      thisx.null = (float)(arg0.null * var2);
      thisx.int = (float)(arg0.int * var2);
      thisx.final = (float)(arg0.final * var2);
      thisx.this = (float)(arg0.this * var2);
   }

   public Quat4f() {
   }

   public final void true(Quat4f arg0) {
      thisx.null = -arg0.null;
      thisx.int = -arg0.int;
      thisx.final = -arg0.final;
      thisx.this = arg0.this;
   }

   public final void true() {
      thisx.null = -thisx.null;
      thisx.int = -thisx.int;
      thisx.final = -thisx.final;
   }

   public final void class(Quat4f arg0, Quat4f arg1) {
      if (thisx != arg0 && thisx != arg1) {
         thisx.this = arg0.this * arg1.this - arg0.null * arg1.null - arg0.int * arg1.int - arg0.final * arg1.final;
         thisx.null = arg0.this * arg1.null + arg1.this * arg0.null + arg0.int * arg1.final - arg0.final * arg1.int;
         thisx.int = arg0.this * arg1.int + arg1.this * arg0.int - arg0.null * arg1.final + arg0.final * arg1.null;
         thisx.final = arg0.this * arg1.final + arg1.this * arg0.final + arg0.null * arg1.int - arg0.int * arg1.null;
      } else {
         float var5 = arg0.this * arg1.this - arg0.null * arg1.null - arg0.int * arg1.int - arg0.final * arg1.final;
         float var3 = arg0.this * arg1.null + arg1.this * arg0.null + arg0.int * arg1.final - arg0.final * arg1.int;
         float var4 = arg0.this * arg1.int + arg1.this * arg0.int - arg0.null * arg1.final + arg0.final * arg1.null;
         thisx.final = arg0.this * arg1.final + arg1.this * arg0.final + arg0.null * arg1.int - arg0.int * arg1.null;
         thisx.this = var5;
         thisx.null = var3;
         thisx.int = var4;
      }

   }

   public final void const(Quat4f arg0) {
      float var4 = thisx.this * arg0.this - thisx.null * arg0.null - thisx.int * arg0.int - thisx.final * arg0.final;
      float var2 = thisx.this * arg0.null + arg0.this * thisx.null + thisx.int * arg0.final - thisx.final * arg0.int;
      float var3 = thisx.this * arg0.int + arg0.this * thisx.int - thisx.null * arg0.final + thisx.final * arg0.null;
      thisx.final = thisx.this * arg0.final + arg0.this * thisx.final + thisx.null * arg0.int - thisx.int * arg0.null;
      thisx.this = var4;
      thisx.null = var2;
      thisx.int = var3;
   }

   public final void null(Quat4f arg0, Quat4f arg1) {
      Quat4f var3 = new Quat4f(arg1);
      var3.const();
      thisx.class(arg0, var3);
   }

   public final void long(Quat4f arg0) {
      Quat4f var2 = new Quat4f(arg0);
      var2.const();
      thisx.const(var2);
   }

   public final void class(Quat4f arg0) {
      float var2 = 1.0F / (arg0.this * arg0.this + arg0.null * arg0.null + arg0.int * arg0.int + arg0.final * arg0.final);
      thisx.this = var2 * arg0.this;
      thisx.null = -var2 * arg0.null;
      thisx.int = -var2 * arg0.int;
      thisx.final = -var2 * arg0.final;
   }

   public final void const() {
      float var1 = 1.0F / (thisx.this * thisx.this + thisx.null * thisx.null + thisx.int * thisx.int + thisx.final * thisx.final);
      thisx.this *= var1;
      thisx.null *= -var1;
      thisx.int *= -var1;
      thisx.final *= -var1;
   }

   public final void null(Quat4f arg0) {
      float var2 = arg0.null * arg0.null + arg0.int * arg0.int + arg0.final * arg0.final + arg0.this * arg0.this;
      if (var2 > 0.0F) {
         var2 = 1.0F / (float)Math.sqrt((double)var2);
         thisx.null = var2 * arg0.null;
         thisx.int = var2 * arg0.int;
         thisx.final = var2 * arg0.final;
         thisx.this = var2 * arg0.this;
      } else {
         thisx.null = 0.0F;
         thisx.int = 0.0F;
         thisx.final = 0.0F;
         thisx.this = 0.0F;
      }

   }

   public final void long() {
      float var1 = thisx.null * thisx.null + thisx.int * thisx.int + thisx.final * thisx.final + thisx.this * thisx.this;
      if (var1 > 0.0F) {
         var1 = 1.0F / (float)Math.sqrt((double)var1);
         thisx.null *= var1;
         thisx.int *= var1;
         thisx.final *= var1;
         thisx.this *= var1;
      } else {
         thisx.null = 0.0F;
         thisx.int = 0.0F;
         thisx.final = 0.0F;
         thisx.this = 0.0F;
      }

   }

   public final void null(Matrix4f arg0) {
      float var2 = 0.25F * (arg0.char + arg0.else + arg0.new + arg0.final);
      if (var2 >= 0.0F) {
         if ((double)var2 >= 1.0E-30D) {
            thisx.this = (float)Math.sqrt((double)var2);
            var2 = 0.25F / thisx.this;
            thisx.null = (arg0.long - arg0.goto) * var2;
            thisx.int = (arg0.break - arg0.short) * var2;
            thisx.final = (arg0.do - arg0.float) * var2;
         } else {
            thisx.this = 0.0F;
            var2 = -0.5F * (arg0.else + arg0.new);
            if (var2 >= 0.0F) {
               if ((double)var2 >= 1.0E-30D) {
                  thisx.null = (float)Math.sqrt((double)var2);
                  var2 = 1.0F / (2.0F * thisx.null);
                  thisx.int = arg0.do * var2;
                  thisx.final = arg0.short * var2;
               } else {
                  thisx.null = 0.0F;
                  var2 = 0.5F * (1.0F - arg0.new);
                  if ((double)var2 >= 1.0E-30D) {
                     thisx.int = (float)Math.sqrt((double)var2);
                     thisx.final = arg0.long / (2.0F * thisx.int);
                  } else {
                     thisx.int = 0.0F;
                     thisx.final = 1.0F;
                  }
               }
            } else {
               thisx.null = 0.0F;
               thisx.int = 0.0F;
               thisx.final = 1.0F;
            }
         }
      } else {
         thisx.this = 0.0F;
         thisx.null = 0.0F;
         thisx.int = 0.0F;
         thisx.final = 1.0F;
      }
   }

   public final void null(Matrix4d arg0) {
      double var2 = 0.25D * (arg0.char + arg0.else + arg0.new + arg0.final);
      if (var2 >= 0.0D) {
         if (var2 >= 1.0E-30D) {
            thisx.this = (float)Math.sqrt(var2);
            var2 = 0.25D / (double)thisx.this;
            thisx.null = (float)((arg0.long - arg0.goto) * var2);
            thisx.int = (float)((arg0.break - arg0.short) * var2);
            thisx.final = (float)((arg0.do - arg0.float) * var2);
         } else {
            thisx.this = 0.0F;
            var2 = -0.5D * (arg0.else + arg0.new);
            if (var2 >= 0.0D) {
               if (var2 >= 1.0E-30D) {
                  thisx.null = (float)Math.sqrt(var2);
                  var2 = 0.5D / (double)thisx.null;
                  thisx.int = (float)(arg0.do * var2);
                  thisx.final = (float)(arg0.short * var2);
               } else {
                  thisx.null = 0.0F;
                  var2 = 0.5D * (1.0D - arg0.new);
                  if (var2 >= 1.0E-30D) {
                     thisx.int = (float)Math.sqrt(var2);
                     thisx.final = (float)(arg0.long / (2.0D * (double)thisx.int));
                  } else {
                     thisx.int = 0.0F;
                     thisx.final = 1.0F;
                  }
               }
            } else {
               thisx.null = 0.0F;
               thisx.int = 0.0F;
               thisx.final = 1.0F;
            }
         }
      } else {
         thisx.this = 0.0F;
         thisx.null = 0.0F;
         thisx.int = 0.0F;
         thisx.final = 1.0F;
      }
   }

   public final void null(Matrix3f arg0) {
      float var2 = 0.25F * (arg0.case + arg0.const + arg0.final + 1.0F);
      if (var2 >= 0.0F) {
         if ((double)var2 >= 1.0E-30D) {
            thisx.this = (float)Math.sqrt((double)var2);
            var2 = 0.25F / thisx.this;
            thisx.null = (arg0.int - arg0.false) * var2;
            thisx.int = (arg0.long - arg0.null) * var2;
            thisx.final = (arg0.new - arg0.short) * var2;
         } else {
            thisx.this = 0.0F;
            var2 = -0.5F * (arg0.const + arg0.final);
            if (var2 >= 0.0F) {
               if ((double)var2 >= 1.0E-30D) {
                  thisx.null = (float)Math.sqrt((double)var2);
                  var2 = 0.5F / thisx.null;
                  thisx.int = arg0.new * var2;
                  thisx.final = arg0.null * var2;
               } else {
                  thisx.null = 0.0F;
                  var2 = 0.5F * (1.0F - arg0.final);
                  if ((double)var2 >= 1.0E-30D) {
                     thisx.int = (float)Math.sqrt((double)var2);
                     thisx.final = arg0.int / (2.0F * thisx.int);
                  } else {
                     thisx.int = 0.0F;
                     thisx.final = 1.0F;
                  }
               }
            } else {
               thisx.null = 0.0F;
               thisx.int = 0.0F;
               thisx.final = 1.0F;
            }
         }
      } else {
         thisx.this = 0.0F;
         thisx.null = 0.0F;
         thisx.int = 0.0F;
         thisx.final = 1.0F;
      }
   }

   public final void null(Matrix3d arg0) {
      double var2 = 0.25D * (arg0.case + arg0.const + arg0.final + 1.0D);
      if (var2 >= 0.0D) {
         if (var2 >= 1.0E-30D) {
            thisx.this = (float)Math.sqrt(var2);
            var2 = 0.25D / (double)thisx.this;
            thisx.null = (float)((arg0.int - arg0.false) * var2);
            thisx.int = (float)((arg0.long - arg0.null) * var2);
            thisx.final = (float)((arg0.new - arg0.short) * var2);
         } else {
            thisx.this = 0.0F;
            var2 = -0.5D * (arg0.const + arg0.final);
            if (var2 >= 0.0D) {
               if (var2 >= 1.0E-30D) {
                  thisx.null = (float)Math.sqrt(var2);
                  var2 = 0.5D / (double)thisx.null;
                  thisx.int = (float)(arg0.new * var2);
                  thisx.final = (float)(arg0.null * var2);
               } else {
                  thisx.null = 0.0F;
                  var2 = 0.5D * (1.0D - arg0.final);
                  if (var2 >= 1.0E-30D) {
                     thisx.int = (float)Math.sqrt(var2);
                     thisx.final = (float)(arg0.int / (2.0D * (double)thisx.int));
                  } else {
                     thisx.int = 0.0F;
                     thisx.final = 1.0F;
                  }
               }
            } else {
               thisx.null = 0.0F;
               thisx.int = 0.0F;
               thisx.final = 1.0F;
            }
         }
      } else {
         thisx.this = 0.0F;
         thisx.null = 0.0F;
         thisx.int = 0.0F;
         thisx.final = 1.0F;
      }
   }

   public final void null(AxisAngle4f arg0) {
      float var3 = (float)Math.sqrt((double)(arg0.false * arg0.false + arg0.null * arg0.null + arg0.int * arg0.int));
      if ((double)var3 < 1.0E-6D) {
         thisx.this = 0.0F;
         thisx.null = 0.0F;
         thisx.int = 0.0F;
         thisx.final = 0.0F;
      } else {
         var3 = 1.0F / var3;
         float var2 = (float)Math.sin((double)arg0.final / 2.0D);
         thisx.this = (float)Math.cos((double)arg0.final / 2.0D);
         thisx.null = arg0.false * var3 * var2;
         thisx.int = arg0.null * var3 * var2;
         thisx.final = arg0.int * var3 * var2;
      }

   }

   public final void null(AxisAngle4d arg0) {
      float var3 = (float)(1.0D / Math.sqrt(arg0.false * arg0.false + arg0.null * arg0.null + arg0.int * arg0.int));
      if ((double)var3 < 1.0E-6D) {
         thisx.this = 0.0F;
         thisx.null = 0.0F;
         thisx.int = 0.0F;
         thisx.final = 0.0F;
      } else {
         var3 = 1.0F / var3;
         float var2 = (float)Math.sin(arg0.final / 2.0D);
         thisx.this = (float)Math.cos(arg0.final / 2.0D);
         thisx.null = (float)arg0.false * var3 * var2;
         thisx.int = (float)arg0.null * var3 * var2;
         thisx.final = (float)arg0.int * var3 * var2;
      }

   }

   public final void null(Quat4f arg0, float arg1) {
      double var3 = (double)(thisx.null * arg0.null + thisx.int * arg0.int + thisx.final * arg0.final + thisx.this * arg0.this);
      if (var3 < 0.0D) {
         arg0.null = -arg0.null;
         arg0.int = -arg0.int;
         arg0.final = -arg0.final;
         arg0.this = -arg0.this;
         var3 = -var3;
      }

      double var5;
      double var7;
      if (1.0D - var3 > 1.0E-6D) {
         double var9 = Math.acos(var3);
         double var11 = Math.sin(var9);
         var5 = Math.sin((1.0D - (double)arg1) * var9) / var11;
         var7 = Math.sin((double)arg1 * var9) / var11;
      } else {
         var5 = 1.0D - (double)arg1;
         var7 = (double)arg1;
      }

      thisx.this = (float)(var5 * (double)thisx.this + var7 * (double)arg0.this);
      thisx.null = (float)(var5 * (double)thisx.null + var7 * (double)arg0.null);
      thisx.int = (float)(var5 * (double)thisx.int + var7 * (double)arg0.int);
      thisx.final = (float)(var5 * (double)thisx.final + var7 * (double)arg0.final);
   }

   public final void null(Quat4f arg0, Quat4f arg1, float arg2) {
      double var4 = (double)(arg1.null * arg0.null + arg1.int * arg0.int + arg1.final * arg0.final + arg1.this * arg0.this);
      if (var4 < 0.0D) {
         arg0.null = -arg0.null;
         arg0.int = -arg0.int;
         arg0.final = -arg0.final;
         arg0.this = -arg0.this;
         var4 = -var4;
      }

      double var6;
      double var8;
      if (1.0D - var4 > 1.0E-6D) {
         double var10 = Math.acos(var4);
         double var12 = Math.sin(var10);
         var6 = Math.sin((1.0D - (double)arg2) * var10) / var12;
         var8 = Math.sin((double)arg2 * var10) / var12;
      } else {
         var6 = 1.0D - (double)arg2;
         var8 = (double)arg2;
      }

      thisx.this = (float)(var6 * (double)arg0.this + var8 * (double)arg1.this);
      thisx.null = (float)(var6 * (double)arg0.null + var8 * (double)arg1.null);
      thisx.int = (float)(var6 * (double)arg0.int + var8 * (double)arg1.int);
      thisx.final = (float)(var6 * (double)arg0.final + var8 * (double)arg1.final);
   }
}
