package optifine.json;

import breeze.linalg.diag;
import com.alibaba.druid.pool.xa.JtdsXAConnection;
import com.alibaba.druid.sql.dialect.odps.ast.OdpsAlterTableSetFileFormat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import net.daporkchop.lib.primitive.lambda.FloatIntShortFunction;
import net.daporkchop.lib.primitive.lambda.ShortBoolCharFunction;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.network.play.server.SPacketCamera;
import net.minecraft.world.IWorldEventListener;
import net.minecraftforge.event.entity.player.SleepingTimeCheckEvent;
import org.rocksdb.NativeComparatorWrapper;

public interface ParseException extends Iterable<ParseException> {
   ParseException null(IWorldEventListener var1);

   IWorldEventListener null();

   ParseException null(String var1);

   ParseException null(ParseException var1);

   String long();

   String class();

   String null();

   List<ParseException> null();

   ParseException null();

   public static class a implements JsonDeserializer<ParseException>, JsonSerializer<ParseException> {
      private static final Gson this;

      public ParseException null(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
         if (arg0.isJsonPrimitive()) {
            return new SleepingTimeCheckEvent(arg0.getAsString());
         } else if (!arg0.isJsonObject()) {
            if (arg0.isJsonArray()) {
               JsonArray var11 = arg0.getAsJsonArray();
               ParseException var16 = null;
               Iterator var14 = var11.iterator();

               while(var14.hasNext()) {
                  JsonElement var17 = (JsonElement)var14.next();
                  ParseException var18 = thisx.null((JsonElement)var17, (Type)var17.getClass(), (JsonDeserializationContext)arg2);
                  if (var16 == null) {
                     var16 = var18;
                  } else {
                     var16.null(var18);
                  }
               }

               return var16;
            } else {
               throw new JsonParseException(OdpsAlterTableSetFileFormat.null("T\u0007~OdH{\u0006\u007f\u001f0\u0000\u007f\u001f0\u001c\u007fHd\u001db\u00060") + arg0 + ShortBoolCharFunction.null("o\r!\u0010 D.D\f\u000b\"\u0014 \n*\n;"));
            }
         } else {
            JsonObject var4 = arg0.getAsJsonObject();
            Object var5;
            if (var4.has(OdpsAlterTableSetFileFormat.null("\u001cu\u0010d"))) {
               var5 = new SleepingTimeCheckEvent(var4.get(ShortBoolCharFunction.null("\u0010*\u001c;")).getAsString());
            } else if (var4.has("translate")) {
               String var12 = var4.get("translate").getAsString();
               if (var4.has(OdpsAlterTableSetFileFormat.null("\u001fy\u001cx"))) {
                  JsonArray var7 = var4.getAsJsonArray(ShortBoolCharFunction.null("\u0013&\u0010'"));
                  Object[] var8 = new Object[var7.size()];

                  for(int var9 = 0; var9 < var8.length; ++var9) {
                     var8[var9] = thisx.null(var7.get(var9), arg1, arg2);
                     if (var8[var9] instanceof SleepingTimeCheckEvent) {
                        SleepingTimeCheckEvent var10 = (SleepingTimeCheckEvent)var8[var9];
                        if (var10.null().null() && var10.null().isEmpty()) {
                           var8[var9] = var10.const();
                        }
                     }
                  }

                  var5 = new SPacketCamera(var12, var8);
               } else {
                  var5 = new SPacketCamera(var12, new Object[0]);
               }
            } else if (var4.has(OdpsAlterTableSetFileFormat.null("c\u000b\u007f\u001au"))) {
               JsonObject var6 = var4.getAsJsonObject(ShortBoolCharFunction.null("<\u0007 \u0016*"));
               if (!var6.has(OdpsAlterTableSetFileFormat.null("\u0006q\u0005u")) || !var6.has(ShortBoolCharFunction.null(" \u0006%\u0001,\u0010&\u0012*"))) {
                  throw new JsonParseException(OdpsAlterTableSetFileFormat.null("QHc\u000b\u007f\u001auHs\u0007}\u0018\u007f\u0006u\u0006dH~\ru\fcHqH|\rq\u001bdHqH~\t}\r0\t~\f0\t~H\u007f\nz\rs\u001cy\u001eu"));
               }

               var5 = new NativeComparatorWrapper(diag.null(var6, ShortBoolCharFunction.null("\n.\t*")), diag.null(var6, OdpsAlterTableSetFileFormat.null("\u007f\nz\rs\u001cy\u001eu")));
               if (var6.has(ShortBoolCharFunction.null("9\u0005#\u0011*"))) {
                  ((NativeComparatorWrapper)var5).null(diag.null(var6, OdpsAlterTableSetFileFormat.null("f\t|\u001du")));
               }
            } else if (var4.has(ShortBoolCharFunction.null("\u0017*\b*\u0007;\u000b="))) {
               var5 = new JtdsXAConnection(diag.null(var4, OdpsAlterTableSetFileFormat.null("\u001bu\u0004u\u000bd\u0007b")));
            } else {
               if (!var4.has(ShortBoolCharFunction.null("$\u00016\u0006&\n+"))) {
                  throw new JsonParseException(OdpsAlterTableSetFileFormat.null("T\u0007~OdH{\u0006\u007f\u001f0\u0000\u007f\u001f0\u001c\u007fHd\u001db\u00060") + arg0 + ShortBoolCharFunction.null("o\r!\u0010 D.D\f\u000b\"\u0014 \n*\n;"));
               }

               var5 = new EntityAIAttackRanged(diag.null(var4, OdpsAlterTableSetFileFormat.null("{\ri\ny\u0006t")));
            }

            if (var4.has(ShortBoolCharFunction.null("*\u001c;\u0016."))) {
               JsonArray var13 = var4.getAsJsonArray(OdpsAlterTableSetFileFormat.null("u\u0010d\u001aq"));
               if (var13.size() <= 0) {
                  throw new JsonParseException(ShortBoolCharFunction.null("1!\u00017\u0014*\u0007;\u0001+D*\t?\u00106D.\u0016=\u00056D \u0002o\u0007 \t?\u000b!\u0001!\u0010<"));
               }

               for(int var15 = 0; var15 < var13.size(); ++var15) {
                  ((ParseException)var5).null(thisx.null(var13.get(var15), arg1, arg2));
               }
            }

            ((ParseException)var5).null((IWorldEventListener)arg2.deserialize(arg0, IWorldEventListener.class));
            return (ParseException)var5;
         }
      }

      private void null(IWorldEventListener arg0, JsonObject arg1, JsonSerializationContext arg2) {
         JsonElement var4 = arg2.serialize(arg0);
         if (var4.isJsonObject()) {
            JsonObject var5 = (JsonObject)var4;
            Iterator var6 = var5.entrySet().iterator();

            while(var6.hasNext()) {
               Entry var7 = (Entry)var6.next();
               arg1.add((String)var7.getKey(), (JsonElement)var7.getValue());
            }
         }

      }

      public JsonElement null(ParseException arg0, Type arg1, JsonSerializationContext arg2) {
         JsonObject var4 = new JsonObject();
         if (!arg0.null().null()) {
            thisx.null(arg0.null(), var4, arg2);
         }

         if (!arg0.null().isEmpty()) {
            JsonArray var5 = new JsonArray();
            Iterator var6 = arg0.null().iterator();

            while(var6.hasNext()) {
               ParseException var7 = (ParseException)var6.next();
               var5.add(thisx.null((ParseException)var7, (Type)var7.getClass(), (JsonSerializationContext)arg2));
            }

            var4.add(OdpsAlterTableSetFileFormat.null("u\u0010d\u001aq"), var5);
         }

         if (arg0 instanceof SleepingTimeCheckEvent) {
            var4.addProperty(ShortBoolCharFunction.null("\u0010*\u001c;"), ((SleepingTimeCheckEvent)arg0).const());
         } else if (arg0 instanceof SPacketCamera) {
            SPacketCamera var11 = (SPacketCamera)arg0;
            var4.addProperty("translate", var11.const());
            if (var11.null() != null && var11.null().length > 0) {
               JsonArray var14 = new JsonArray();
               Object[] var17 = var11.null();
               int var8 = var17.length;

               for(int var9 = 0; var9 < var8; ++var9) {
                  Object var10 = var17[var9];
                  if (var10 instanceof ParseException) {
                     var14.add(thisx.null((ParseException)((ParseException)var10), (Type)var10.getClass(), (JsonSerializationContext)arg2));
                  } else {
                     var14.add(new JsonPrimitive(String.valueOf(var10)));
                  }
               }

               var4.add(OdpsAlterTableSetFileFormat.null("\u001fy\u001cx"), var14);
            }
         } else if (arg0 instanceof NativeComparatorWrapper) {
            NativeComparatorWrapper var12 = (NativeComparatorWrapper)arg0;
            JsonObject var16 = new JsonObject();
            var16.addProperty(ShortBoolCharFunction.null("\n.\t*"), var12.true());
            var16.addProperty(OdpsAlterTableSetFileFormat.null("\u007f\nz\rs\u001cy\u001eu"), var12.const());
            var16.addProperty(ShortBoolCharFunction.null("9\u0005#\u0011*"), var12.long());
            var4.add(OdpsAlterTableSetFileFormat.null("c\u000b\u007f\u001au"), var16);
         } else if (arg0 instanceof JtdsXAConnection) {
            JtdsXAConnection var13 = (JtdsXAConnection)arg0;
            var4.addProperty(ShortBoolCharFunction.null("\u0017*\b*\u0007;\u000b="), var13.const());
         } else {
            if (!(arg0 instanceof EntityAIAttackRanged)) {
               throw new IllegalArgumentException(OdpsAlterTableSetFileFormat.null(",\u007f\u00067\u001c0\u0003~\u0007gHx\u0007gHd\u00070\u001bu\u001ay\t|\u0001j\r0") + arg0 + ShortBoolCharFunction.null("o\u0005<D.D\f\u000b\"\u0014 \n*\n;"));
            }

            EntityAIAttackRanged var15 = (EntityAIAttackRanged)arg0;
            var4.addProperty(OdpsAlterTableSetFileFormat.null("{\ri\ny\u0006t"), var15.const());
         }

         return var4;
      }

      public static String null(ParseException arg0) {
         return this.toJson(arg0);
      }

      @Nullable
      public static ParseException class(String arg0) {
         return (ParseException)diag.null(this, arg0, ParseException.class, false);
      }

      @Nullable
      public static ParseException null(String arg0) {
         return (ParseException)diag.null(this, arg0, ParseException.class, true);
      }

      static {
         GsonBuilder var0 = new GsonBuilder();
         var0.registerTypeHierarchyAdapter(ParseException.class, new ParseException.a());
         var0.registerTypeHierarchyAdapter(IWorldEventListener.class, new IWorldEventListener.a());
         var0.registerTypeAdapterFactory(new FloatIntShortFunction());
         this = var0.create();
      }
   }
}
