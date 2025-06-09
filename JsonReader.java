package android.util;

import org.lwjgl.opengl.GL11;

public class JsonReader {
   public int drawStyle = 100012;
   public int orientation = 100020;
   public boolean textureFlag = false;
   public int normals = 100000;

   public void normal3f(float arg0, float arg1, float arg2) {
      float var4 = (float)Math.sqrt((double)(arg0 * arg0 + arg1 * arg1 + arg2 * arg2));
      if (var4 > 1.0E-5F) {
         arg0 /= var4;
         arg1 /= var4;
         arg2 /= var4;
      }

      GL11.glNormal3f(arg0, arg1, arg2);
   }

   public void setDrawStyle(int arg0) {
      this.drawStyle = arg0;
   }

   public void setNormals(int arg0) {
      this.normals = arg0;
   }

   public void setOrientation(int arg0) {
      this.orientation = arg0;
   }

   public void setTextureFlag(boolean arg0) {
      this.textureFlag = arg0;
   }

   public int getDrawStyle() {
      return this.drawStyle;
   }

   public int getNormals() {
      return this.normals;
   }

   public int getOrientation() {
      return this.orientation;
   }

   public boolean getTextureFlag() {
      return this.textureFlag;
   }

   public void TXTR_COORD(float arg0, float arg1) {
      if (this.textureFlag) {
         GL11.glTexCoord2f(arg0, arg1);
      }

   }

   public float sin(float arg0) {
      return (float)Math.sin((double)arg0);
   }

   public float cos(float arg0) {
      return (float)Math.cos((double)arg0);
   }
}
