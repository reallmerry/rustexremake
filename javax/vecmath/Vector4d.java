package javax.vecmath;

import java.io.Serializable;

public class Vector4d extends Tuple4d implements Serializable {
   public static final long false = 3938123424117448700L;

   public Vector4d(double arg0, double arg1, double arg2, double arg3) {
      super(arg0, arg1, arg2, arg3);
   }

   public Vector4d(double[] arg0) {
      super(arg0);
   }

   public Vector4d(Vector4d arg0) {
      super((Tuple4d)arg0);
   }

   public Vector4d(Vector4f arg0) {
      super((Tuple4f)arg0);
   }

   public Vector4d(Tuple4f arg0) {
      super(arg0);
   }

   public Vector4d(Tuple4d arg0) {
      super(arg0);
   }

   public Vector4d(Tuple3d arg0) {
      super(arg0.int, arg0.final, arg0.this, 0.0D);
   }

   public Vector4d() {
   }

   public final void null(Tuple3d arg0) {
      this.null = arg0.int;
      this.int = arg0.final;
      this.final = arg0.this;
      this.this = 0.0D;
   }

   public final double do() {
      return Math.sqrt(this.null * this.null + this.int * this.int + this.final * this.final + this.this * this.this);
   }

   public final double true() {
      return this.null * this.null + this.int * this.int + this.final * this.final + this.this * this.this;
   }

   public final double class(Vector4d arg0) {
      return this.null * arg0.null + this.int * arg0.int + this.final * arg0.final + this.this * arg0.this;
   }

   public final void null(Vector4d arg0) {
      double var2 = 1.0D / Math.sqrt(arg0.null * arg0.null + arg0.int * arg0.int + arg0.final * arg0.final + arg0.this * arg0.this);
      this.null = arg0.null * var2;
      this.int = arg0.int * var2;
      this.final = arg0.final * var2;
      this.this = arg0.this * var2;
   }

   public final void long() {
      double var1 = 1.0D / Math.sqrt(this.null * this.null + this.int * this.int + this.final * this.final + this.this * this.this);
      this.null *= var1;
      this.int *= var1;
      this.final *= var1;
      this.this *= var1;
   }

   public final double null(Vector4d arg0) {
      double var2 = this.class(arg0) / (this.do() * arg0.do());
      if (var2 < -1.0D) {
         var2 = -1.0D;
      }

      if (var2 > 1.0D) {
         var2 = 1.0D;
      }

      return Math.acos(var2);
   }
}
