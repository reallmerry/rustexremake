package net.minecraft.util;

import android.net.http.HttpResponseCache;
import android.widget.OverScroller;
import net.daporkchop.lib.noise.filter.math.SourceMulFilter;
import net.daporkchop.lib.primitive.lambda.ByteCharShortFunction;
import net.daporkchop.lib.primitive.lambda.CharShortLongFunction;
import net.daporkchop.lib.primitive.lambda.LongShortFloatFunction;
import net.minecraft.command.ICommand;
import org.postgresql.jdbc.ResourceLock;

public class JsonUtils implements SourceMulFilter {
   public boolean null(ResourceLock arg0, ByteCharShortFunction arg1) {
      int var3 = 0;
      ICommand var4 = ICommand.do;

      for(int var5 = 0; var5 < arg0.long(); ++var5) {
         ICommand var6 = arg0.class(var5);
         if (!var6.float()) {
            if (var6.null() == HttpResponseCache.synchronized) {
               if (!var4.float()) {
                  return false;
               }

               var4 = var6;
            } else {
               if (var6.null() != HttpResponseCache.H) {
                  return false;
               }

               ++var3;
            }
         }
      }

      return !var4.float() && var4.short() && var3 > 0;
   }

   public ICommand null(ResourceLock arg0) {
      int var2 = 0;
      ICommand var3 = ICommand.do;

      for(int var4 = 0; var4 < arg0.long(); ++var4) {
         ICommand var5 = arg0.class(var4);
         if (!var5.float()) {
            if (var5.null() == HttpResponseCache.synchronized) {
               if (!var3.float()) {
                  return ICommand.do;
               }

               var3 = var5;
            } else {
               if (var5.null() != HttpResponseCache.H) {
                  return ICommand.do;
               }

               ++var2;
            }
         }
      }

      if (!var3.float() && var3.short() && var2 >= 1 && LongShortFloatFunction.class(var3) < 2) {
         ICommand var6 = new ICommand(HttpResponseCache.synchronized, var2);
         var6.null(var3.null().null());
         var6.null().null(CharShortLongFunction.null("h^a^}Z{R`U"), LongShortFloatFunction.class(var3) + 1);
         if (var3.do()) {
            var6.null(var3.null());
         }

         return var6;
      } else {
         return ICommand.do;
      }
   }

   public ICommand null() {
      return ICommand.do;
   }

   public OverScroller<ICommand> null(ResourceLock arg0) {
      OverScroller var2 = OverScroller.null(arg0.long(), ICommand.do);

      for(int var3 = 0; var3 < var2.size(); ++var3) {
         ICommand var4 = arg0.class(var3);
         if (var4.null() instanceof LongShortFloatFunction) {
            ICommand var5 = var4.null();
            var5.long(1);
            var2.set(var3, var5);
            break;
         }
      }

      return var2;
   }

   public boolean null() {
      return true;
   }

   public boolean null(int arg0, int arg1) {
      return arg0 >= 3 && arg1 >= 3;
   }
}
