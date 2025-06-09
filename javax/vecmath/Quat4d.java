package javax.vecmath;

import java.io.Serializable;

public class Quat4d extends Tuple4d implements Serializable {
   public static final long false = 7577479888820201099L;
   public static final double new = 1.0E-12D;
   public static final double const = 1.0E-30D;
   public static final double false = 1.57079632679D;

   public Quat4d(double arg0, double arg1, double arg2, double arg3) {
      double var9 = 1.0D / Math.sqrt(arg0 * arg0 + arg1 * arg1 + arg2 * arg2 + arg3 * arg3);
      this.null = arg0 * var9;
      this.int = arg1 * var9;
      this.final = arg2 * var9;
      this.this = arg3 * var9;
   }

   public Quat4d(double[] arg0) {
      double var2 = 1.0D / Math.sqrt(arg0[0] * arg0[0] + arg0[1] * arg0[1] + arg0[2] * arg0[2] + arg0[3] * arg0[3]);
      this.null = arg0[0] * var2;
      this.int = arg0[1] * var2;
      this.final = arg0[2] * var2;
      this.this = arg0[3] * var2;
   }

   public Quat4d(Quat4d arg0) {
      super((Tuple4d)arg0);
   }

   public Quat4d(Quat4f arg0) {
      super((Tuple4f)arg0);
   }

   public Quat4d(Tuple4f arg0) {
      double var2 = 1.0D / Math.sqrt((double)(arg0.null * arg0.null + arg0.int * arg0.int + arg0.final * arg0.final + arg0.this * arg0.this));
      this.null = (double)arg0.null * var2;
      this.int = (double)arg0.int * var2;
      this.final = (double)arg0.final * var2;
      this.this = (double)arg0.this * var2;
   }

   public Quat4d(Tuple4d arg0) {
      double var2 = 1.0D / Math.sqrt(arg0.null * arg0.null + arg0.int * arg0.int + arg0.final * arg0.final + arg0.this * arg0.this);
      this.null = arg0.null * var2;
      this.int = arg0.int * var2;
      this.final = arg0.final * var2;
      this.this = arg0.this * var2;
   }

   public Quat4d() {
   }

   public final void true(Quat4d arg0) {
      this.null = -arg0.null;
      this.int = -arg0.int;
      this.final = -arg0.final;
      this.this = arg0.this;
   }

   public final void true() {
      this.null = -this.null;
      this.int = -this.int;
      this.final = -this.final;
   }

   public final void class(Quat4d arg0, Quat4d arg1) {
      if (this != arg0 && this != arg1) {
         this.this = arg0.this * arg1.this - arg0.null * arg1.null - arg0.int * arg1.int - arg0.final * arg1.final;
         this.null = arg0.this * arg1.null + arg1.this * arg0.null + arg0.int * arg1.final - arg0.final * arg1.int;
         this.int = arg0.this * arg1.int + arg1.this * arg0.int - arg0.null * arg1.final + arg0.final * arg1.null;
         this.final = arg0.this * arg1.final + arg1.this * arg0.final + arg0.null * arg1.int - arg0.int * arg1.null;
      } else {
         double var7 = arg0.this * arg1.this - arg0.null * arg1.null - arg0.int * arg1.int - arg0.final * arg1.final;
         double var3 = arg0.this * arg1.null + arg1.this * arg0.null + arg0.int * arg1.final - arg0.final * arg1.int;
         double var5 = arg0.this * arg1.int + arg1.this * arg0.int - arg0.null * arg1.final + arg0.final * arg1.null;
         this.final = arg0.this * arg1.final + arg1.this * arg0.final + arg0.null * arg1.int - arg0.int * arg1.null;
         this.this = var7;
         this.null = var3;
         this.int = var5;
      }

   }

   public final void const(Quat4d arg0) {
      double var6 = this.this * arg0.this - this.null * arg0.null - this.int * arg0.int - this.final * arg0.final;
      double var2 = this.this * arg0.null + arg0.this * this.null + this.int * arg0.final - this.final * arg0.int;
      double var4 = this.this * arg0.int + arg0.this * this.int - this.null * arg0.final + this.final * arg0.null;
      this.final = this.this * arg0.final + arg0.this * this.final + this.null * arg0.int - this.int * arg0.null;
      this.this = var6;
      this.null = var2;
      this.int = var4;
   }

   public final void null(Quat4d arg0, Quat4d arg1) {
      Quat4d var3 = new Quat4d(arg1);
      var3.const();
      this.class(arg0, var3);
   }

   public final void long(Quat4d arg0) {
      Quat4d var2 = new Quat4d(arg0);
      var2.const();
      this.const(var2);
   }

   public final void class(Quat4d arg0) {
      double var2 = 1.0D / (arg0.this * arg0.this + arg0.null * arg0.null + arg0.int * arg0.int + arg0.final * arg0.final);
      this.this = var2 * arg0.this;
      this.null = -var2 * arg0.null;
      this.int = -var2 * arg0.int;
      this.final = -var2 * arg0.final;
   }

   public final void const() {
      double var1 = 1.0D / (this.this * this.this + this.null * this.null + this.int * this.int + this.final * this.final);
      this.this *= var1;
      this.null *= -var1;
      this.int *= -var1;
      this.final *= -var1;
   }

   public final void null(Quat4d arg0) {
      double var2 = arg0.null * arg0.null + arg0.int * arg0.int + arg0.final * arg0.final + arg0.this * arg0.this;
      if (var2 > 0.0D) {
         var2 = 1.0D / Math.sqrt(var2);
         this.null = var2 * arg0.null;
         this.int = var2 * arg0.int;
         this.final = var2 * arg0.final;
         this.this = var2 * arg0.this;
      } else {
         this.null = 0.0D;
         this.int = 0.0D;
         this.final = 0.0D;
         this.this = 0.0D;
      }

   }

   public final void long() {
      double var1 = this.null * this.null + this.int * this.int + this.final * this.final + this.this * this.this;
      if (var1 > 0.0D) {
         var1 = 1.0D / Math.sqrt(var1);
         this.null *= var1;
         this.int *= var1;
         this.final *= var1;
         this.this *= var1;
      } else {
         this.null = 0.0D;
         this.int = 0.0D;
         this.final = 0.0D;
         this.this = 0.0D;
      }

   }

   public final void null(Matrix4f arg0) {
      double var2 = 0.25D * (double)(arg0.char + arg0.else + arg0.new + arg0.final);
      if (var2 >= 0.0D) {
         if (var2 >= 1.0E-30D) {
            this.this = Math.sqrt(var2);
            var2 = 0.25D / this.this;
            this.null = (double)(arg0.long - arg0.goto) * var2;
            this.int = (double)(arg0.break - arg0.short) * var2;
            this.final = (double)(arg0.do - arg0.float) * var2;
         } else {
            this.this = 0.0D;
            var2 = -0.5D * (double)(arg0.else + arg0.new);
            if (var2 >= 0.0D) {
               if (var2 >= 1.0E-30D) {
                  this.null = Math.sqrt(var2);
                  var2 = 1.0D / (2.0D * this.null);
                  this.int = (double)arg0.do * var2;
                  this.final = (double)arg0.short * var2;
               } else {
                  this.null = 0.0D;
                  var2 = 0.5D * (1.0D - (double)arg0.new);
                  if (var2 >= 1.0E-30D) {
                     this.int = Math.sqrt(var2);
                     this.final = (double)arg0.long / (2.0D * this.int);
                  } else {
                     this.int = 0.0D;
                     this.final = 1.0D;
                  }
               }
            } else {
               this.null = 0.0D;
               this.int = 0.0D;
               this.final = 1.0D;
            }
         }
      } else {
         this.this = 0.0D;
         this.null = 0.0D;
         this.int = 0.0D;
         this.final = 1.0D;
      }
   }

   public final void null(Matrix4d arg0) {
      double var2 = 0.25D * (arg0.char + arg0.else + arg0.new + arg0.final);
      if (var2 >= 0.0D) {
         if (var2 >= 1.0E-30D) {
            this.this = Math.sqrt(var2);
            var2 = 0.25D / this.this;
            this.null = (arg0.long - arg0.goto) * var2;
            this.int = (arg0.break - arg0.short) * var2;
            this.final = (arg0.do - arg0.float) * var2;
         } else {
            this.this = 0.0D;
            var2 = -0.5D * (arg0.else + arg0.new);
            if (var2 >= 0.0D) {
               if (var2 >= 1.0E-30D) {
                  this.null = Math.sqrt(var2);
                  var2 = 0.5D / this.null;
                  this.int = arg0.do * var2;
                  this.final = arg0.short * var2;
               } else {
                  this.null = 0.0D;
                  var2 = 0.5D * (1.0D - arg0.new);
                  if (var2 >= 1.0E-30D) {
                     this.int = Math.sqrt(var2);
                     this.final = arg0.long / (2.0D * this.int);
                  } else {
                     this.int = 0.0D;
                     this.final = 1.0D;
                  }
               }
            } else {
               this.null = 0.0D;
               this.int = 0.0D;
               this.final = 1.0D;
            }
         }
      } else {
         this.this = 0.0D;
         this.null = 0.0D;
         this.int = 0.0D;
         this.final = 1.0D;
      }
   }

   public final void null(Matrix3f arg0) {
      double var2 = 0.25D * ((double)(arg0.case + arg0.const + arg0.final) + 1.0D);
      if (var2 >= 0.0D) {
         if (var2 >= 1.0E-30D) {
            this.this = Math.sqrt(var2);
            var2 = 0.25D / this.this;
            this.null = (double)(arg0.int - arg0.false) * var2;
            this.int = (double)(arg0.long - arg0.null) * var2;
            this.final = (double)(arg0.new - arg0.short) * var2;
         } else {
            this.this = 0.0D;
            var2 = -0.5D * (double)(arg0.const + arg0.final);
            if (var2 >= 0.0D) {
               if (var2 >= 1.0E-30D) {
                  this.null = Math.sqrt(var2);
                  var2 = 0.5D / this.null;
                  this.int = (double)arg0.new * var2;
                  this.final = (double)arg0.null * var2;
               } else {
                  this.null = 0.0D;
                  var2 = 0.5D * (1.0D - (double)arg0.final);
                  if (var2 >= 1.0E-30D) {
                     this.int = Math.sqrt(var2);
                     this.final = (double)arg0.int / (2.0D * this.int);
                  }

                  this.int = 0.0D;
                  this.final = 1.0D;
               }
            } else {
               this.null = 0.0D;
               this.int = 0.0D;
               this.final = 1.0D;
            }
         }
      } else {
         this.this = 0.0D;
         this.null = 0.0D;
         this.int = 0.0D;
         this.final = 1.0D;
      }
   }

   public final void null(Matrix3d arg0) {
      double var2 = 0.25D * (arg0.case + arg0.const + arg0.final + 1.0D);
      if (var2 >= 0.0D) {
         if (var2 >= 1.0E-30D) {
            this.this = Math.sqrt(var2);
            var2 = 0.25D / this.this;
            this.null = (arg0.int - arg0.false) * var2;
            this.int = (arg0.long - arg0.null) * var2;
            this.final = (arg0.new - arg0.short) * var2;
         } else {
            this.this = 0.0D;
            var2 = -0.5D * (arg0.const + arg0.final);
            if (var2 >= 0.0D) {
               if (var2 >= 1.0E-30D) {
                  this.null = Math.sqrt(var2);
                  var2 = 0.5D / this.null;
                  this.int = arg0.new * var2;
                  this.final = arg0.null * var2;
               } else {
                  this.null = 0.0D;
                  var2 = 0.5D * (1.0D - arg0.final);
                  if (var2 >= 1.0E-30D) {
                     this.int = Math.sqrt(var2);
                     this.final = arg0.int / (2.0D * this.int);
                  } else {
                     this.int = 0.0D;
                     this.final = 1.0D;
                  }
               }
            } else {
               this.null = 0.0D;
               this.int = 0.0D;
               this.final = 1.0D;
            }
         }
      } else {
         this.this = 0.0D;
         this.null = 0.0D;
         this.int = 0.0D;
         this.final = 1.0D;
      }
   }

   public final void null(AxisAngle4f arg0) {
      double var4 = Math.sqrt((double)(arg0.false * arg0.false + arg0.null * arg0.null + arg0.int * arg0.int));
      if (var4 < 1.0E-12D) {
         this.this = 0.0D;
         this.null = 0.0D;
         this.int = 0.0D;
         this.final = 0.0D;
      } else {
         double var2 = Math.sin((double)arg0.final / 2.0D);
         var4 = 1.0D / var4;
         this.this = Math.cos((double)arg0.final / 2.0D);
         this.null = (double)arg0.false * var4 * var2;
         this.int = (double)arg0.null * var4 * var2;
         this.final = (double)arg0.int * var4 * var2;
      }

   }

   public final void null(AxisAngle4d arg0) {
      double var4 = Math.sqrt(arg0.false * arg0.false + arg0.null * arg0.null + arg0.int * arg0.int);
      if (var4 < 1.0E-12D) {
         this.this = 0.0D;
         this.null = 0.0D;
         this.int = 0.0D;
         this.final = 0.0D;
      } else {
         var4 = 1.0D / var4;
         double var2 = Math.sin(arg0.final / 2.0D);
         this.this = Math.cos(arg0.final / 2.0D);
         this.null = arg0.false * var4 * var2;
         this.int = arg0.null * var4 * var2;
         this.final = arg0.int * var4 * var2;
      }

   }

   public final void null(Quat4d arg0, double arg1) {
      double var4 = this.null * arg0.null + this.int * arg0.int + this.final * arg0.final + this.this * arg0.this;
      if (var4 < 0.0D) {
         arg0.null = -arg0.null;
         arg0.int = -arg0.int;
         arg0.final = -arg0.final;
         arg0.this = -arg0.this;
         var4 = -var4;
      }

      double var6;
      double var8;
      if (1.0D - var4 > 1.0E-12D) {
         double var10 = Math.acos(var4);
         double var12 = Math.sin(var10);
         var6 = Math.sin((1.0D - arg1) * var10) / var12;
         var8 = Math.sin(arg1 * var10) / var12;
      } else {
         var6 = 1.0D - arg1;
         var8 = arg1;
      }

      this.this = var6 * this.this + var8 * arg0.this;
      this.null = var6 * this.null + var8 * arg0.null;
      this.int = var6 * this.int + var8 * arg0.int;
      this.final = var6 * this.final + var8 * arg0.final;
   }

   public final void null(Quat4d arg0, Quat4d arg1, double arg2) {
      double var5 = arg1.null * arg0.null + arg1.int * arg0.int + arg1.final * arg0.final + arg1.this * arg0.this;
      if (var5 < 0.0D) {
         arg0.null = -arg0.null;
         arg0.int = -arg0.int;
         arg0.final = -arg0.final;
         arg0.this = -arg0.this;
         var5 = -var5;
      }

      double var7;
      double var9;
      if (1.0D - var5 > 1.0E-12D) {
         double var11 = Math.acos(var5);
         double var13 = Math.sin(var11);
         var7 = Math.sin((1.0D - arg2) * var11) / var13;
         var9 = Math.sin(arg2 * var11) / var13;
      } else {
         var7 = 1.0D - arg2;
         var9 = arg2;
      }

      this.this = var7 * arg0.this + var9 * arg1.this;
      this.null = var7 * arg0.null + var9 * arg1.null;
      this.int = var7 * arg0.int + var9 * arg1.int;
      this.final = var7 * arg0.final + var9 * arg1.final;
   }
}
