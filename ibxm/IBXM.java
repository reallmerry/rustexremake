package ibxm;

import android.graphics.PorterDuff;
import com.alibaba.druid.support.http.stat.WebAppStatManager;
import com.sk89q.jnbt.DoubleTag;
import java.io.Serializable;
import java.nio.FloatBuffer;
import net.minecraftforge.fml.repackage.com.nothome.delta.DiffWriter;

public class IBXM extends PorterDuff implements Serializable {
   private static final long serialVersionUID = 1L;
   public float m00;
   public float m01;
   public float m02;
   public float m03;
   public float m10;
   public float m11;
   public float m12;
   public float m13;
   public float m20;
   public float m21;
   public float m22;
   public float m23;
   public float m30;
   public float m31;
   public float m32;
   public float m33;

   public IBXM() {
      this.setIdentity();
   }

   public IBXM(IBXM arg0) {
      this.load(arg0);
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.m00).append(' ').append(this.m10).append(' ').append(this.m20).append(' ').append(this.m30).append('\n');
      var1.append(this.m01).append(' ').append(this.m11).append(' ').append(this.m21).append(' ').append(this.m31).append('\n');
      var1.append(this.m02).append(' ').append(this.m12).append(' ').append(this.m22).append(' ').append(this.m32).append('\n');
      var1.append(this.m03).append(' ').append(this.m13).append(' ').append(this.m23).append(' ').append(this.m33).append('\n');
      return var1.toString();
   }

   public PorterDuff setIdentity() {
      return setIdentity(this);
   }

   public static IBXM setIdentity(IBXM arg0) {
      arg0.m00 = 1.0F;
      arg0.m01 = 0.0F;
      arg0.m02 = 0.0F;
      arg0.m03 = 0.0F;
      arg0.m10 = 0.0F;
      arg0.m11 = 1.0F;
      arg0.m12 = 0.0F;
      arg0.m13 = 0.0F;
      arg0.m20 = 0.0F;
      arg0.m21 = 0.0F;
      arg0.m22 = 1.0F;
      arg0.m23 = 0.0F;
      arg0.m30 = 0.0F;
      arg0.m31 = 0.0F;
      arg0.m32 = 0.0F;
      arg0.m33 = 1.0F;
      return arg0;
   }

   public PorterDuff setZero() {
      return setZero(this);
   }

   public static IBXM setZero(IBXM arg0) {
      arg0.m00 = 0.0F;
      arg0.m01 = 0.0F;
      arg0.m02 = 0.0F;
      arg0.m03 = 0.0F;
      arg0.m10 = 0.0F;
      arg0.m11 = 0.0F;
      arg0.m12 = 0.0F;
      arg0.m13 = 0.0F;
      arg0.m20 = 0.0F;
      arg0.m21 = 0.0F;
      arg0.m22 = 0.0F;
      arg0.m23 = 0.0F;
      arg0.m30 = 0.0F;
      arg0.m31 = 0.0F;
      arg0.m32 = 0.0F;
      arg0.m33 = 0.0F;
      return arg0;
   }

   public IBXM load(IBXM arg0) {
      return load(arg0, this);
   }

   public static IBXM load(IBXM arg0, IBXM arg1) {
      if (arg1 == null) {
         arg1 = new IBXM();
      }

      arg1.m00 = arg0.m00;
      arg1.m01 = arg0.m01;
      arg1.m02 = arg0.m02;
      arg1.m03 = arg0.m03;
      arg1.m10 = arg0.m10;
      arg1.m11 = arg0.m11;
      arg1.m12 = arg0.m12;
      arg1.m13 = arg0.m13;
      arg1.m20 = arg0.m20;
      arg1.m21 = arg0.m21;
      arg1.m22 = arg0.m22;
      arg1.m23 = arg0.m23;
      arg1.m30 = arg0.m30;
      arg1.m31 = arg0.m31;
      arg1.m32 = arg0.m32;
      arg1.m33 = arg0.m33;
      return arg1;
   }

   public PorterDuff load(FloatBuffer arg0) {
      this.m00 = arg0.get();
      this.m01 = arg0.get();
      this.m02 = arg0.get();
      this.m03 = arg0.get();
      this.m10 = arg0.get();
      this.m11 = arg0.get();
      this.m12 = arg0.get();
      this.m13 = arg0.get();
      this.m20 = arg0.get();
      this.m21 = arg0.get();
      this.m22 = arg0.get();
      this.m23 = arg0.get();
      this.m30 = arg0.get();
      this.m31 = arg0.get();
      this.m32 = arg0.get();
      this.m33 = arg0.get();
      return this;
   }

   public PorterDuff loadTranspose(FloatBuffer arg0) {
      this.m00 = arg0.get();
      this.m10 = arg0.get();
      this.m20 = arg0.get();
      this.m30 = arg0.get();
      this.m01 = arg0.get();
      this.m11 = arg0.get();
      this.m21 = arg0.get();
      this.m31 = arg0.get();
      this.m02 = arg0.get();
      this.m12 = arg0.get();
      this.m22 = arg0.get();
      this.m32 = arg0.get();
      this.m03 = arg0.get();
      this.m13 = arg0.get();
      this.m23 = arg0.get();
      this.m33 = arg0.get();
      return this;
   }

   public PorterDuff store(FloatBuffer arg0) {
      arg0.put(this.m00);
      arg0.put(this.m01);
      arg0.put(this.m02);
      arg0.put(this.m03);
      arg0.put(this.m10);
      arg0.put(this.m11);
      arg0.put(this.m12);
      arg0.put(this.m13);
      arg0.put(this.m20);
      arg0.put(this.m21);
      arg0.put(this.m22);
      arg0.put(this.m23);
      arg0.put(this.m30);
      arg0.put(this.m31);
      arg0.put(this.m32);
      arg0.put(this.m33);
      return this;
   }

   public PorterDuff storeTranspose(FloatBuffer arg0) {
      arg0.put(this.m00);
      arg0.put(this.m10);
      arg0.put(this.m20);
      arg0.put(this.m30);
      arg0.put(this.m01);
      arg0.put(this.m11);
      arg0.put(this.m21);
      arg0.put(this.m31);
      arg0.put(this.m02);
      arg0.put(this.m12);
      arg0.put(this.m22);
      arg0.put(this.m32);
      arg0.put(this.m03);
      arg0.put(this.m13);
      arg0.put(this.m23);
      arg0.put(this.m33);
      return this;
   }

   public PorterDuff store3f(FloatBuffer arg0) {
      arg0.put(this.m00);
      arg0.put(this.m01);
      arg0.put(this.m02);
      arg0.put(this.m10);
      arg0.put(this.m11);
      arg0.put(this.m12);
      arg0.put(this.m20);
      arg0.put(this.m21);
      arg0.put(this.m22);
      return this;
   }

   public static IBXM add(IBXM arg0, IBXM arg1, IBXM arg2) {
      if (arg2 == null) {
         arg2 = new IBXM();
      }

      arg2.m00 = arg0.m00 + arg1.m00;
      arg2.m01 = arg0.m01 + arg1.m01;
      arg2.m02 = arg0.m02 + arg1.m02;
      arg2.m03 = arg0.m03 + arg1.m03;
      arg2.m10 = arg0.m10 + arg1.m10;
      arg2.m11 = arg0.m11 + arg1.m11;
      arg2.m12 = arg0.m12 + arg1.m12;
      arg2.m13 = arg0.m13 + arg1.m13;
      arg2.m20 = arg0.m20 + arg1.m20;
      arg2.m21 = arg0.m21 + arg1.m21;
      arg2.m22 = arg0.m22 + arg1.m22;
      arg2.m23 = arg0.m23 + arg1.m23;
      arg2.m30 = arg0.m30 + arg1.m30;
      arg2.m31 = arg0.m31 + arg1.m31;
      arg2.m32 = arg0.m32 + arg1.m32;
      arg2.m33 = arg0.m33 + arg1.m33;
      return arg2;
   }

   public static IBXM sub(IBXM arg0, IBXM arg1, IBXM arg2) {
      if (arg2 == null) {
         arg2 = new IBXM();
      }

      arg2.m00 = arg0.m00 - arg1.m00;
      arg2.m01 = arg0.m01 - arg1.m01;
      arg2.m02 = arg0.m02 - arg1.m02;
      arg2.m03 = arg0.m03 - arg1.m03;
      arg2.m10 = arg0.m10 - arg1.m10;
      arg2.m11 = arg0.m11 - arg1.m11;
      arg2.m12 = arg0.m12 - arg1.m12;
      arg2.m13 = arg0.m13 - arg1.m13;
      arg2.m20 = arg0.m20 - arg1.m20;
      arg2.m21 = arg0.m21 - arg1.m21;
      arg2.m22 = arg0.m22 - arg1.m22;
      arg2.m23 = arg0.m23 - arg1.m23;
      arg2.m30 = arg0.m30 - arg1.m30;
      arg2.m31 = arg0.m31 - arg1.m31;
      arg2.m32 = arg0.m32 - arg1.m32;
      arg2.m33 = arg0.m33 - arg1.m33;
      return arg2;
   }

   public static IBXM mul(IBXM arg0, IBXM arg1, IBXM arg2) {
      if (arg2 == null) {
         arg2 = new IBXM();
      }

      float var3 = arg0.m00 * arg1.m00 + arg0.m10 * arg1.m01 + arg0.m20 * arg1.m02 + arg0.m30 * arg1.m03;
      float var4 = arg0.m01 * arg1.m00 + arg0.m11 * arg1.m01 + arg0.m21 * arg1.m02 + arg0.m31 * arg1.m03;
      float var5 = arg0.m02 * arg1.m00 + arg0.m12 * arg1.m01 + arg0.m22 * arg1.m02 + arg0.m32 * arg1.m03;
      float var6 = arg0.m03 * arg1.m00 + arg0.m13 * arg1.m01 + arg0.m23 * arg1.m02 + arg0.m33 * arg1.m03;
      float var7 = arg0.m00 * arg1.m10 + arg0.m10 * arg1.m11 + arg0.m20 * arg1.m12 + arg0.m30 * arg1.m13;
      float var8 = arg0.m01 * arg1.m10 + arg0.m11 * arg1.m11 + arg0.m21 * arg1.m12 + arg0.m31 * arg1.m13;
      float var9 = arg0.m02 * arg1.m10 + arg0.m12 * arg1.m11 + arg0.m22 * arg1.m12 + arg0.m32 * arg1.m13;
      float var10 = arg0.m03 * arg1.m10 + arg0.m13 * arg1.m11 + arg0.m23 * arg1.m12 + arg0.m33 * arg1.m13;
      float var11 = arg0.m00 * arg1.m20 + arg0.m10 * arg1.m21 + arg0.m20 * arg1.m22 + arg0.m30 * arg1.m23;
      float var12 = arg0.m01 * arg1.m20 + arg0.m11 * arg1.m21 + arg0.m21 * arg1.m22 + arg0.m31 * arg1.m23;
      float var13 = arg0.m02 * arg1.m20 + arg0.m12 * arg1.m21 + arg0.m22 * arg1.m22 + arg0.m32 * arg1.m23;
      float var14 = arg0.m03 * arg1.m20 + arg0.m13 * arg1.m21 + arg0.m23 * arg1.m22 + arg0.m33 * arg1.m23;
      float var15 = arg0.m00 * arg1.m30 + arg0.m10 * arg1.m31 + arg0.m20 * arg1.m32 + arg0.m30 * arg1.m33;
      float var16 = arg0.m01 * arg1.m30 + arg0.m11 * arg1.m31 + arg0.m21 * arg1.m32 + arg0.m31 * arg1.m33;
      float var17 = arg0.m02 * arg1.m30 + arg0.m12 * arg1.m31 + arg0.m22 * arg1.m32 + arg0.m32 * arg1.m33;
      float var18 = arg0.m03 * arg1.m30 + arg0.m13 * arg1.m31 + arg0.m23 * arg1.m32 + arg0.m33 * arg1.m33;
      arg2.m00 = var3;
      arg2.m01 = var4;
      arg2.m02 = var5;
      arg2.m03 = var6;
      arg2.m10 = var7;
      arg2.m11 = var8;
      arg2.m12 = var9;
      arg2.m13 = var10;
      arg2.m20 = var11;
      arg2.m21 = var12;
      arg2.m22 = var13;
      arg2.m23 = var14;
      arg2.m30 = var15;
      arg2.m31 = var16;
      arg2.m32 = var17;
      arg2.m33 = var18;
      return arg2;
   }

   public static DoubleTag transform(IBXM arg0, DoubleTag arg1, DoubleTag arg2) {
      if (arg2 == null) {
         arg2 = new DoubleTag();
      }

      float var3 = arg0.m00 * arg1.x + arg0.m10 * arg1.y + arg0.m20 * arg1.z + arg0.m30 * arg1.w;
      float var4 = arg0.m01 * arg1.x + arg0.m11 * arg1.y + arg0.m21 * arg1.z + arg0.m31 * arg1.w;
      float var5 = arg0.m02 * arg1.x + arg0.m12 * arg1.y + arg0.m22 * arg1.z + arg0.m32 * arg1.w;
      float var6 = arg0.m03 * arg1.x + arg0.m13 * arg1.y + arg0.m23 * arg1.z + arg0.m33 * arg1.w;
      arg2.x = var3;
      arg2.y = var4;
      arg2.z = var5;
      arg2.w = var6;
      return arg2;
   }

   public PorterDuff transpose() {
      return this.transpose(this);
   }

   public IBXM translate(WebAppStatManager arg0) {
      return this.translate(arg0, this);
   }

   public IBXM translate(DiffWriter arg0) {
      return this.translate(arg0, this);
   }

   public IBXM scale(DiffWriter arg0) {
      return scale(arg0, this, this);
   }

   public static IBXM scale(DiffWriter arg0, IBXM arg1, IBXM arg2) {
      if (arg2 == null) {
         arg2 = new IBXM();
      }

      arg2.m00 = arg1.m00 * arg0.x;
      arg2.m01 = arg1.m01 * arg0.x;
      arg2.m02 = arg1.m02 * arg0.x;
      arg2.m03 = arg1.m03 * arg0.x;
      arg2.m10 = arg1.m10 * arg0.y;
      arg2.m11 = arg1.m11 * arg0.y;
      arg2.m12 = arg1.m12 * arg0.y;
      arg2.m13 = arg1.m13 * arg0.y;
      arg2.m20 = arg1.m20 * arg0.z;
      arg2.m21 = arg1.m21 * arg0.z;
      arg2.m22 = arg1.m22 * arg0.z;
      arg2.m23 = arg1.m23 * arg0.z;
      return arg2;
   }

   public IBXM rotate(float arg0, DiffWriter arg1) {
      return this.rotate(arg0, arg1, this);
   }

   public IBXM rotate(float arg0, DiffWriter arg1, IBXM arg2) {
      return rotate(arg0, arg1, this, arg2);
   }

   public static IBXM rotate(float arg0, DiffWriter arg1, IBXM arg2, IBXM arg3) {
      if (arg3 == null) {
         arg3 = new IBXM();
      }

      float var4 = (float)Math.cos((double)arg0);
      float var5 = (float)Math.sin((double)arg0);
      float var6 = 1.0F - var4;
      float var7 = arg1.x * arg1.y;
      float var8 = arg1.y * arg1.z;
      float var9 = arg1.x * arg1.z;
      float var10 = arg1.x * var5;
      float var11 = arg1.y * var5;
      float var12 = arg1.z * var5;
      float var13 = arg1.x * arg1.x * var6 + var4;
      float var14 = var7 * var6 + var12;
      float var15 = var9 * var6 - var11;
      float var16 = var7 * var6 - var12;
      float var17 = arg1.y * arg1.y * var6 + var4;
      float var18 = var8 * var6 + var10;
      float var19 = var9 * var6 + var11;
      float var20 = var8 * var6 - var10;
      float var21 = arg1.z * arg1.z * var6 + var4;
      float var22 = arg2.m00 * var13 + arg2.m10 * var14 + arg2.m20 * var15;
      float var23 = arg2.m01 * var13 + arg2.m11 * var14 + arg2.m21 * var15;
      float var24 = arg2.m02 * var13 + arg2.m12 * var14 + arg2.m22 * var15;
      float var25 = arg2.m03 * var13 + arg2.m13 * var14 + arg2.m23 * var15;
      float var26 = arg2.m00 * var16 + arg2.m10 * var17 + arg2.m20 * var18;
      float var27 = arg2.m01 * var16 + arg2.m11 * var17 + arg2.m21 * var18;
      float var28 = arg2.m02 * var16 + arg2.m12 * var17 + arg2.m22 * var18;
      float var29 = arg2.m03 * var16 + arg2.m13 * var17 + arg2.m23 * var18;
      arg3.m20 = arg2.m00 * var19 + arg2.m10 * var20 + arg2.m20 * var21;
      arg3.m21 = arg2.m01 * var19 + arg2.m11 * var20 + arg2.m21 * var21;
      arg3.m22 = arg2.m02 * var19 + arg2.m12 * var20 + arg2.m22 * var21;
      arg3.m23 = arg2.m03 * var19 + arg2.m13 * var20 + arg2.m23 * var21;
      arg3.m00 = var22;
      arg3.m01 = var23;
      arg3.m02 = var24;
      arg3.m03 = var25;
      arg3.m10 = var26;
      arg3.m11 = var27;
      arg3.m12 = var28;
      arg3.m13 = var29;
      return arg3;
   }

   public IBXM translate(DiffWriter arg0, IBXM arg1) {
      return translate(arg0, this, arg1);
   }

   public static IBXM translate(DiffWriter arg0, IBXM arg1, IBXM arg2) {
      if (arg2 == null) {
         arg2 = new IBXM();
      }

      arg2.m30 += arg1.m00 * arg0.x + arg1.m10 * arg0.y + arg1.m20 * arg0.z;
      arg2.m31 += arg1.m01 * arg0.x + arg1.m11 * arg0.y + arg1.m21 * arg0.z;
      arg2.m32 += arg1.m02 * arg0.x + arg1.m12 * arg0.y + arg1.m22 * arg0.z;
      arg2.m33 += arg1.m03 * arg0.x + arg1.m13 * arg0.y + arg1.m23 * arg0.z;
      return arg2;
   }

   public IBXM translate(WebAppStatManager arg0, IBXM arg1) {
      return translate(arg0, this, arg1);
   }

   public static IBXM translate(WebAppStatManager arg0, IBXM arg1, IBXM arg2) {
      if (arg2 == null) {
         arg2 = new IBXM();
      }

      arg2.m30 += arg1.m00 * arg0.x + arg1.m10 * arg0.y;
      arg2.m31 += arg1.m01 * arg0.x + arg1.m11 * arg0.y;
      arg2.m32 += arg1.m02 * arg0.x + arg1.m12 * arg0.y;
      arg2.m33 += arg1.m03 * arg0.x + arg1.m13 * arg0.y;
      return arg2;
   }

   public IBXM transpose(IBXM arg0) {
      return transpose(this, arg0);
   }

   public static IBXM transpose(IBXM arg0, IBXM arg1) {
      if (arg1 == null) {
         arg1 = new IBXM();
      }

      float var2 = arg0.m00;
      float var3 = arg0.m10;
      float var4 = arg0.m20;
      float var5 = arg0.m30;
      float var6 = arg0.m01;
      float var7 = arg0.m11;
      float var8 = arg0.m21;
      float var9 = arg0.m31;
      float var10 = arg0.m02;
      float var11 = arg0.m12;
      float var12 = arg0.m22;
      float var13 = arg0.m32;
      float var14 = arg0.m03;
      float var15 = arg0.m13;
      float var16 = arg0.m23;
      float var17 = arg0.m33;
      arg1.m00 = var2;
      arg1.m01 = var3;
      arg1.m02 = var4;
      arg1.m03 = var5;
      arg1.m10 = var6;
      arg1.m11 = var7;
      arg1.m12 = var8;
      arg1.m13 = var9;
      arg1.m20 = var10;
      arg1.m21 = var11;
      arg1.m22 = var12;
      arg1.m23 = var13;
      arg1.m30 = var14;
      arg1.m31 = var15;
      arg1.m32 = var16;
      arg1.m33 = var17;
      return arg1;
   }

   public float determinant() {
      float var1 = this.m00 * (this.m11 * this.m22 * this.m33 + this.m12 * this.m23 * this.m31 + this.m13 * this.m21 * this.m32 - this.m13 * this.m22 * this.m31 - this.m11 * this.m23 * this.m32 - this.m12 * this.m21 * this.m33);
      var1 -= this.m01 * (this.m10 * this.m22 * this.m33 + this.m12 * this.m23 * this.m30 + this.m13 * this.m20 * this.m32 - this.m13 * this.m22 * this.m30 - this.m10 * this.m23 * this.m32 - this.m12 * this.m20 * this.m33);
      var1 += this.m02 * (this.m10 * this.m21 * this.m33 + this.m11 * this.m23 * this.m30 + this.m13 * this.m20 * this.m31 - this.m13 * this.m21 * this.m30 - this.m10 * this.m23 * this.m31 - this.m11 * this.m20 * this.m33);
      var1 -= this.m03 * (this.m10 * this.m21 * this.m32 + this.m11 * this.m22 * this.m30 + this.m12 * this.m20 * this.m31 - this.m12 * this.m21 * this.m30 - this.m10 * this.m22 * this.m31 - this.m11 * this.m20 * this.m32);
      return var1;
   }

   private static float determinant3x3(float arg0, float arg1, float arg2, float arg3, float arg4, float arg5, float arg6, float arg7, float arg8) {
      return arg0 * (arg4 * arg8 - arg5 * arg7) + arg1 * (arg5 * arg6 - arg3 * arg8) + arg2 * (arg3 * arg7 - arg4 * arg6);
   }

   public PorterDuff invert() {
      return invert(this, this);
   }

   public static IBXM invert(IBXM arg0, IBXM arg1) {
      float var2 = arg0.determinant();
      if (var2 != 0.0F) {
         if (arg1 == null) {
            arg1 = new IBXM();
         }

         float var3 = 1.0F / var2;
         float var4 = determinant3x3(arg0.m11, arg0.m12, arg0.m13, arg0.m21, arg0.m22, arg0.m23, arg0.m31, arg0.m32, arg0.m33);
         float var5 = -determinant3x3(arg0.m10, arg0.m12, arg0.m13, arg0.m20, arg0.m22, arg0.m23, arg0.m30, arg0.m32, arg0.m33);
         float var6 = determinant3x3(arg0.m10, arg0.m11, arg0.m13, arg0.m20, arg0.m21, arg0.m23, arg0.m30, arg0.m31, arg0.m33);
         float var7 = -determinant3x3(arg0.m10, arg0.m11, arg0.m12, arg0.m20, arg0.m21, arg0.m22, arg0.m30, arg0.m31, arg0.m32);
         float var8 = -determinant3x3(arg0.m01, arg0.m02, arg0.m03, arg0.m21, arg0.m22, arg0.m23, arg0.m31, arg0.m32, arg0.m33);
         float var9 = determinant3x3(arg0.m00, arg0.m02, arg0.m03, arg0.m20, arg0.m22, arg0.m23, arg0.m30, arg0.m32, arg0.m33);
         float var10 = -determinant3x3(arg0.m00, arg0.m01, arg0.m03, arg0.m20, arg0.m21, arg0.m23, arg0.m30, arg0.m31, arg0.m33);
         float var11 = determinant3x3(arg0.m00, arg0.m01, arg0.m02, arg0.m20, arg0.m21, arg0.m22, arg0.m30, arg0.m31, arg0.m32);
         float var12 = determinant3x3(arg0.m01, arg0.m02, arg0.m03, arg0.m11, arg0.m12, arg0.m13, arg0.m31, arg0.m32, arg0.m33);
         float var13 = -determinant3x3(arg0.m00, arg0.m02, arg0.m03, arg0.m10, arg0.m12, arg0.m13, arg0.m30, arg0.m32, arg0.m33);
         float var14 = determinant3x3(arg0.m00, arg0.m01, arg0.m03, arg0.m10, arg0.m11, arg0.m13, arg0.m30, arg0.m31, arg0.m33);
         float var15 = -determinant3x3(arg0.m00, arg0.m01, arg0.m02, arg0.m10, arg0.m11, arg0.m12, arg0.m30, arg0.m31, arg0.m32);
         float var16 = -determinant3x3(arg0.m01, arg0.m02, arg0.m03, arg0.m11, arg0.m12, arg0.m13, arg0.m21, arg0.m22, arg0.m23);
         float var17 = determinant3x3(arg0.m00, arg0.m02, arg0.m03, arg0.m10, arg0.m12, arg0.m13, arg0.m20, arg0.m22, arg0.m23);
         float var18 = -determinant3x3(arg0.m00, arg0.m01, arg0.m03, arg0.m10, arg0.m11, arg0.m13, arg0.m20, arg0.m21, arg0.m23);
         float var19 = determinant3x3(arg0.m00, arg0.m01, arg0.m02, arg0.m10, arg0.m11, arg0.m12, arg0.m20, arg0.m21, arg0.m22);
         arg1.m00 = var4 * var3;
         arg1.m11 = var9 * var3;
         arg1.m22 = var14 * var3;
         arg1.m33 = var19 * var3;
         arg1.m01 = var8 * var3;
         arg1.m10 = var5 * var3;
         arg1.m20 = var6 * var3;
         arg1.m02 = var12 * var3;
         arg1.m12 = var13 * var3;
         arg1.m21 = var10 * var3;
         arg1.m03 = var16 * var3;
         arg1.m30 = var7 * var3;
         arg1.m13 = var17 * var3;
         arg1.m31 = var11 * var3;
         arg1.m32 = var15 * var3;
         arg1.m23 = var18 * var3;
         return arg1;
      } else {
         return null;
      }
   }

   public PorterDuff negate() {
      return this.negate(this);
   }

   public IBXM negate(IBXM arg0) {
      return negate(this, arg0);
   }

   public static IBXM negate(IBXM arg0, IBXM arg1) {
      if (arg1 == null) {
         arg1 = new IBXM();
      }

      arg1.m00 = -arg0.m00;
      arg1.m01 = -arg0.m01;
      arg1.m02 = -arg0.m02;
      arg1.m03 = -arg0.m03;
      arg1.m10 = -arg0.m10;
      arg1.m11 = -arg0.m11;
      arg1.m12 = -arg0.m12;
      arg1.m13 = -arg0.m13;
      arg1.m20 = -arg0.m20;
      arg1.m21 = -arg0.m21;
      arg1.m22 = -arg0.m22;
      arg1.m23 = -arg0.m23;
      arg1.m30 = -arg0.m30;
      arg1.m31 = -arg0.m31;
      arg1.m32 = -arg0.m32;
      arg1.m33 = -arg0.m33;
      return arg1;
   }
}
