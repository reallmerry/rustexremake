package com.alibaba.druid.support.json;

import android.renderscript.Double3;
import breeze.linalg.support.TensorPairs;
import com.alibaba.druid.filter.logging.CommonsLogFilter;
import com.alibaba.druid.pool.ha.selector.RandomDataSourceSelector;
import com.alibaba.druid.sql.ast.statement.SQLAlterTableDropConstraint;
import com.sk89q.worldedit.antlr4.runtime.atn.ContextSensitivityInfo;
import com.sk89q.worldedit.util.formatting.text.BlockNbtComponentImpl;
import net.daporkchop.fp2.client.gl.OpenGL;
import net.daporkchop.lib.primitive.lambda.FloatCharShortConsumer;
import net.daporkchop.lib.primitive.lambda.IntByteFloatConsumer;
import net.minecraft.network.play.server.SPacketEntityTeleport;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.optifine.shaders.config.PropertyDefaultFastFancyOff;

public class JSONUtils extends TensorPairs {
   public JSONUtils() {
      super(BlockNbtComponentImpl.class, ContextSensitivityInfo.null("4\u00007\u001b"), 0.5F);
   }

   public CommonsLogFilter null() {
      return new FloatCharShortConsumer();
   }

   public PropertyDefaultFastFancyOff null(CommonsLogFilter arg0, String arg1) {
      if (!(arg0 instanceof FloatCharShortConsumer)) {
         return null;
      } else {
         FloatCharShortConsumer var3 = (FloatCharShortConsumer)arg0;
         if (arg1.equals(RandomDataSourceSelector.null("`1v*m3"))) {
            return var3.const[0];
         } else if (arg1.equals(ContextSensitivityInfo.null("4\u000e5\u0004"))) {
            return var3.const[1];
         } else if (arg1.equals(RandomDataSourceSelector.null("8p1l*"))) {
            return var3.const[2];
         } else if (arg1.equals(ContextSensitivityInfo.null("\u001d?\b>\u001b"))) {
            return var3.const[3];
         } else if (arg1.equals(RandomDataSourceSelector.null("n;d*"))) {
            return var3.const[4];
         } else if (arg1.equals(ContextSensitivityInfo.null("\u001f7\u000b2\u000330:\n0\u001b"))) {
            return var3.false[0];
         } else if (arg1.equals(RandomDataSourceSelector.null("r?f:n;],k9j*"))) {
            return var3.false[1];
         } else {
            return arg1.equals(ContextSensitivityInfo.null("\r9\u001b\"\u0000;08\u0000\t\u00187\u001b3\u001d")) ? var3.null : null;
         }
      }
   }

   public String[] null() {
      return new String[]{RandomDataSourceSelector.null("`1v*m3"), ContextSensitivityInfo.null("4\u000e5\u0004"), RandomDataSourceSelector.null("8p1l*"), ContextSensitivityInfo.null("\u001d?\b>\u001b"), RandomDataSourceSelector.null("n;d*"), ContextSensitivityInfo.null("\u001f7\u000b2\u000330:\n0\u001b"), RandomDataSourceSelector.null("r?f:n;],k9j*"), ContextSensitivityInfo.null("\r9\u001b\"\u0000;08\u0000\t\u00187\u001b3\u001d")};
   }

   public Double3 null(CommonsLogFilter arg0, float arg1) {
      SPacketEntityTeleport var3 = SetMetadata.null().null();
      IntByteFloatConsumer var4 = new IntByteFloatConsumer(var3);
      if (!OpenGL.implements.null()) {
         SQLAlterTableDropConstraint.true(RandomDataSourceSelector.null("\u0018k;n:\"0m*\"8m+l:8~P;l:g,@1c*,3m:g2@1c*"));
         return null;
      } else {
         OpenGL.null((Object)var4, (WorldGenShrub)OpenGL.implements, (Object)arg0);
         var4.false = arg1;
         return var4;
      }
   }
}
