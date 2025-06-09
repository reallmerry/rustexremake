package optifine.xdelta;

import android.location.GpsSatellite;
import android.media.MediaCryptoException;
import android.text.style.ImageSpan;
import android.webkit.WebSettings;
import breeze.optimize.L2Regularization;
import breeze.optimize.proximal.Proximal;
import com.alibaba.druid.mock.MockDriver;
import com.alibaba.druid.sql.ast.statement.SQLAlterTableDropConstraint;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSubPartitionByValue;
import com.alibaba.druid.stat.JdbcSqlStatValue;
import com.sk89q.worldedit.MissingWorldException;
import com.sk89q.worldedit.blocks.Blocks;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.daporkchop.fp2.mode.voxel.server.gen.exact.VanillaVoxelGenerator;
import net.daporkchop.fp2.server.FP2Server;
import net.daporkchop.lib.binary.stream.netty.NonGrowingDirectByteBufOut;
import net.daporkchop.lib.primitive.collection.ShortIterator;
import net.daporkchop.lib.primitive.lambda.ByteIntByteConsumer;
import net.daporkchop.lib.primitive.lambda.CharLongCharConsumer;
import net.daporkchop.lib.primitive.lambda.FloatLongIntFunction;
import net.daporkchop.lib.primitive.lambda.IntBoolObjConsumer;
import net.daporkchop.lib.primitive.lambda.ObjDoubleConsumer;
import net.minecraft.advancements.CriterionProgress;
import net.minecraft.client.model.ModelIllager;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.fml.common.IFMLSidedHandler;
import net.minecraftforge.fml.common.discovery.asm.ModClassVisitor;
import net.minecraftforge.fml.common.event.FMLEvent;
import net.optifine.entity.model.ModelAdapterWolf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import oshi.SystemInfo;
import oshi.hardware.Processor;

public class RandomAccessFileSeekableSource {
   private static final Logger I = LogManager.getLogger();
   public static boolean J;
   public static boolean D;
   public static int h;
   public static int e;
   public static int i;
   public static int H;
   public static int synchronized;
   public static int F;
   public static int G;
   public static int g;
   public static int assert;
   private static RandomAccessFileSeekableSource.a default;
   public static boolean public;
   private static boolean while;
   private static boolean boolean;
   public static int transient;
   public static int finally;
   public static int package;
   public static int throws;
   private static boolean interface;
   public static int continue;
   public static int instanceof;
   public static int abstract;
   private static boolean throw;
   public static int implements;
   public static int protected;
   public static int volatile;
   public static int strictfp;
   public static int return;
   public static int import;
   public static int double;
   public static int extends;
   public static int switch;
   public static int native;
   public static int static;
   public static int private;
   public static int if;
   public static int try;
   public static int super;
   public static int enum;
   public static int byte;
   public static int void;
   public static int class;
   private static boolean true;
   public static boolean for;
   public static boolean char;
   public static boolean float;
   private static String break = "";
   private static String catch;
   public static boolean do;
   public static boolean else;
   private static boolean goto;
   public static int case;
   public static int short;
   public static float long = 0.0F;
   public static float new = 0.0F;
   public static boolean const;
   public static boolean false;
   public static int null;
   public static int int;
   public static final int final = 7;
   public static final int this = 4;

   public static void null() {
      SQLAlterTableDropConstraint.char();
      VanillaVoxelGenerator var0 = ModelIllager.getCapabilities();
      interface = var0.GL_ARB_multitexture && !var0.OpenGL13;
      throw = var0.GL_ARB_texture_env_combine && !var0.OpenGL13;
      const = var0.OpenGL31;
      if (const) {
         null = 36662;
         int = 36663;
      } else {
         null = 36662;
         int = 36663;
      }

      boolean var1 = const || var0.GL_ARB_copy_buffer;
      boolean var2 = var0.OpenGL14;
      false = var1 && var2;
      if (!false) {
         ArrayList var3 = new ArrayList();
         if (!var1) {
            var3.add(NonGrowingDirectByteBufOut.null("\u0007$-:\u000f\u0018hefgdt\t\u0006\n\u000b+;8-\u00176=2.1:"));
         }

         if (!var2) {
            var3.add(ShortIterator.null("\u0005}/c\rAj<d9"));
         }

         String var4 = NonGrowingDirectByteBufOut.null("\u001e6'\u0006-3!;&'h:' h'=$8;: -0dt%=;'!:/nh") + SQLAlterTableDropConstraint.null((List)var3);
         SQLAlterTableDropConstraint.do(var4);
         break = break + var4 + "\n";
      }

      if (interface) {
         break = break + ShortIterator.null("\u001f~#c--\u000b_\bR'x&y#y/u>x8hd\u0007");
         continue = 33984;
         instanceof = 33985;
         abstract = 33986;
      } else {
         break = break + NonGrowingDirectByteBufOut.null("\u001d'!:/t\u000f\u0018hefgh9=8<=<10 =&!:/zB");
         continue = 33984;
         instanceof = 33985;
         abstract = 33986;
      }

      if (throw) {
         break = break + ShortIterator.null("X9d$jjL\u0018O\u0015y/u>x8h\u0015h${\u0015n%`(d$hd\u0007");
         implements = 34160;
         protected = 34165;
         volatile = 34167;
         strictfp = 34166;
         return = 34168;
         import = 34161;
         double = 34176;
         extends = 34177;
         switch = 34178;
         native = 34192;
         static = 34193;
         private = 34194;
         if = 34162;
         try = 34184;
         super = 34185;
         enum = 34186;
         byte = 34200;
         void = 34201;
         class = 34202;
      } else {
         break = break + NonGrowingDirectByteBufOut.null("\u0001;=&3h\u0013\u0004tyz{t<10 =&-t+;%6!:-&;zB");
         implements = 34160;
         protected = 34165;
         volatile = 34167;
         strictfp = 34166;
         return = 34168;
         import = 34161;
         double = 34176;
         extends = 34177;
         switch = 34178;
         native = 34192;
         static = 34193;
         private = 34194;
         if = 34162;
         try = 34184;
         super = 34185;
         enum = 34186;
         byte = 34200;
         void = 34201;
         class = 34202;
      }

      for = var0.GL_EXT_blend_func_separate && !var0.OpenGL14;
      true = var0.OpenGL14 || var0.GL_EXT_blend_func_separate;
      public = true && (var0.GL_ARB_framebuffer_object || var0.GL_EXT_framebuffer_object || var0.OpenGL30);
      if (public) {
         break = break + ShortIterator.null("\u001f~#c--,\u007f+`/o?k,h8-%o h)y9-(h)l?~/-");
         if (var0.OpenGL30) {
            break = break + NonGrowingDirectByteBufOut.null("\u001b81&\u0013\u0004t{zxt!'h'=$8;: -0h5&0h'-$)&) -t*8-:,=&3h=;t;!8$'&<1,zB");
            default = RandomAccessFileSeekableSource.a.null;
            h = 36160;
            e = 36161;
            i = 36064;
            H = 36096;
            synchronized = 36053;
            F = 36054;
            G = 36055;
            g = 36059;
            assert = 36060;
         } else if (var0.GL_ARB_framebuffer_object) {
            break = break + ShortIterator.null("\u000b_\bR,\u007f+`/o?k,h8R%o h)yjd9-9x:}%\u007f>h.-+c.-9h:l8l>hjo&h$i#c--#~j~?}:b8y/id\u0007");
            default = RandomAccessFileSeekableSource.a.int;
            h = 36160;
            e = 36161;
            i = 36064;
            H = 36096;
            synchronized = 36053;
            G = 36055;
            F = 36054;
            g = 36059;
            assert = 36060;
         } else if (var0.GL_EXT_framebuffer_object) {
            break = break + NonGrowingDirectByteBufOut.null("\r\f\u001c\u000b.&)9-6=2.1:\u000b'6\"1+ h=;t;!8$'&<1,zB");
            default = RandomAccessFileSeekableSource.a.final;
            h = 36160;
            e = 36161;
            i = 36064;
            H = 36096;
            synchronized = 36053;
            G = 36055;
            F = 36054;
            g = 36059;
            assert = 36060;
         }
      } else {
         break = break + ShortIterator.null("\u0004b>-?~#c--,\u007f+`/o?k,h8-%o h)y9-(h)l?~/-");
         break = break + NonGrowingDirectByteBufOut.null("\u001b81&\u0013\u0004tyz|t!'h") + (var0.OpenGL14 ? "" : ShortIterator.null("$b>-")) + NonGrowingDirectByteBufOut.null(";!8$'&<1,xh");
         break = break + ShortIterator.null("H\u0012Y\u0015o&h$i\u0015k?c)R9h:l8l>hjd9-") + (var0.GL_EXT_blend_func_separate ? "" : NonGrowingDirectByteBufOut.null(":' h")) + ShortIterator.null("~?}:b8y/if-");
         break = break + NonGrowingDirectByteBufOut.null("\u001b81&\u0013\u0004t{zxt!'h") + (var0.OpenGL30 ? "" : ShortIterator.null("$b>-")) + NonGrowingDirectByteBufOut.null(";!8$'&<1,xh");
         break = break + ShortIterator.null("\u000b_\bR,\u007f+`/o?k,h8R%o h)yjd9-") + (var0.GL_ARB_framebuffer_object ? "" : NonGrowingDirectByteBufOut.null(":' h")) + ShortIterator.null("~?}:b8y/if-+c.-");
         break = break + NonGrowingDirectByteBufOut.null("\u0011\u0010\u0000\u00172:5%1*!.2-&\u0017;*>-7<t!'h") + (var0.GL_EXT_framebuffer_object ? "" : ShortIterator.null("$b>-")) + NonGrowingDirectByteBufOut.null(";!8$'&<1,zB");
      }

      char = var0.OpenGL21;
      while = char || var0.GL_ARB_vertex_shader && var0.GL_ARB_fragment_shader && var0.GL_ARB_shader_objects;
      break = break + ShortIterator.null("\u0019e+i/\u007f9-+\u007f/-") + (while ? "" : NonGrowingDirectByteBufOut.null(":' h")) + ShortIterator.null("+{+d&l(a/-(h)l?~/-");
      if (while) {
         if (var0.OpenGL21) {
            break = break + NonGrowingDirectByteBufOut.null("\u0007$-:\u000f\u0018hffeh=;t;!8$'&<1,zB");
            boolean = false;
            transient = 35714;
            finally = 35713;
            package = 35633;
            throws = 35632;
         } else {
            break = break + ShortIterator.null("\u000b_\bR9e+i/\u007f\u0015b(g/n>~f-\u000b_\bR<h8y/u\u0015~\"l.h8!jl$ijL\u0018O\u0015k8l-`/c>R9e+i/\u007fjl8hj~?}:b8y/id\u0007");
            boolean = true;
            transient = 35714;
            finally = 35713;
            package = 35633;
            throws = 35632;
         }
      } else {
         break = break + NonGrowingDirectByteBufOut.null("\u001b81&\u0013\u0004tzzyt!'h") + (var0.OpenGL21 ? "" : ShortIterator.null("$b>-")) + NonGrowingDirectByteBufOut.null(";!8$'&<1,xh");
         break = break + ShortIterator.null("\u000b_\bR9e+i/\u007f\u0015b(g/n>~jd9-") + (var0.GL_ARB_shader_objects ? "" : NonGrowingDirectByteBufOut.null(":' h")) + ShortIterator.null("~?}:b8y/if-");
         break = break + NonGrowingDirectByteBufOut.null("\t\u0006\n\u000b>1: -,\u0017' 5,1:t!'h") + (var0.GL_ARB_vertex_shader ? "" : ShortIterator.null("$b>-")) + NonGrowingDirectByteBufOut.null(";!8$'&<1,xh5&0h");
         break = break + ShortIterator.null("L\u0018O\u0015k8l-`/c>R9e+i/\u007fjd9-") + (var0.GL_ARB_fragment_shader ? "" : NonGrowingDirectByteBufOut.null(":' h")) + ShortIterator.null("~?}:b8y/id\u0007");
      }

      float = public && while;
      String var6 = IFMLSidedHandler.glGetString(7936).toLowerCase(Locale.ROOT);
      J = var6.contains(NonGrowingDirectByteBufOut.null(":>=,=)"));
      goto = !var0.OpenGL15 && var0.GL_ARB_vertex_buffer_object;
      do = var0.OpenGL15 || goto;
      break = break + ShortIterator.null("[\bB9-+\u007f/-") + (do ? "" : NonGrowingDirectByteBufOut.null(":' h")) + ShortIterator.null("+{+d&l(a/-(h)l?~/-");
      if (do) {
         if (goto) {
            break = break + NonGrowingDirectByteBufOut.null("\t\u0006\n\u000b>1: -,\u00176=2.1:\u000b'6\"1+ h=;t;!8$'&<1,zB");
            short = 35044;
            case = 34962;
         } else {
            break = break + ShortIterator.null("B:h$J\u0006-{#\u007f-#~j~?}:b8y/id\u0007");
            short = 35044;
            case = 34962;
         }
      }

      D = var6.contains(NonGrowingDirectByteBufOut.null(") !"));
      if (D) {
         if (do) {
            else = true;
         } else {
            Proximal.a.FA.null(16.0F);
         }
      }

      try {
         Processor[] var7 = (new SystemInfo()).getHardware().getProcessors();
         catch = String.format(ShortIterator.null("oi2-o~"), var7.length, var7[0]).replaceAll(NonGrowingDirectByteBufOut.null("\u0014'c"), " ");
      } catch (Throwable var5) {
      }

   }

   public static boolean long() {
      return float;
   }

   public static String class() {
      return break;
   }

   public static int class(int arg0, int arg1) {
      return boolean ? CharLongCharConsumer.glGetObjectParameteriARB(arg0, arg1) : ModClassVisitor.glGetProgrami(arg0, arg1);
   }

   public static void true(int arg0, int arg1) {
      if (boolean) {
         CharLongCharConsumer.glAttachObjectARB(arg0, arg1);
      } else {
         ModClassVisitor.glAttachShader(arg0, arg1);
      }

   }

   public static void char(int arg0) {
      if (boolean) {
         CharLongCharConsumer.glDeleteObjectARB(arg0);
      } else {
         ModClassVisitor.glDeleteShader(arg0);
      }

   }

   public static int class(int arg0) {
      return boolean ? CharLongCharConsumer.glCreateShaderObjectARB(arg0) : ModClassVisitor.glCreateShader(arg0);
   }

   public static void null(int arg0, ByteBuffer arg1) {
      if (boolean) {
         CharLongCharConsumer.glShaderSourceARB(arg0, arg1);
      } else {
         ModClassVisitor.glShaderSource(arg0, arg1);
      }

   }

   public static void else(int arg0) {
      if (boolean) {
         CharLongCharConsumer.glCompileShaderARB(arg0);
      } else {
         ModClassVisitor.glCompileShader(arg0);
      }

   }

   public static int null(int arg0, int arg1) {
      return boolean ? CharLongCharConsumer.glGetObjectParameteriARB(arg0, arg1) : ModClassVisitor.glGetShaderi(arg0, arg1);
   }

   public static String class(int arg0, int arg1) {
      return boolean ? CharLongCharConsumer.glGetInfoLogARB(arg0, arg1) : ModClassVisitor.glGetShaderInfoLog(arg0, arg1);
   }

   public static String null(int arg0, int arg1) {
      return boolean ? CharLongCharConsumer.glGetInfoLogARB(arg0, arg1) : ModClassVisitor.glGetProgramInfoLog(arg0, arg1);
   }

   public static void new(int arg0) {
      if (boolean) {
         CharLongCharConsumer.glUseProgramObjectARB(arg0);
      } else {
         ModClassVisitor.glUseProgram(arg0);
      }

   }

   public static int const() {
      return boolean ? CharLongCharConsumer.glCreateProgramObjectARB() : ModClassVisitor.glCreateProgram();
   }

   public static void this(int arg0) {
      if (boolean) {
         CharLongCharConsumer.glDeleteObjectARB(arg0);
      } else {
         ModClassVisitor.glDeleteProgram(arg0);
      }

   }

   public static void short(int arg0) {
      if (boolean) {
         CharLongCharConsumer.glLinkProgramARB(arg0);
      } else {
         ModClassVisitor.glLinkProgram(arg0);
      }

   }

   public static int class(int arg0, CharSequence arg1) {
      return boolean ? CharLongCharConsumer.glGetUniformLocationARB(arg0, arg1) : ModClassVisitor.glGetUniformLocation(arg0, arg1);
   }

   public static void const(int arg0, IntBuffer arg1) {
      if (boolean) {
         CharLongCharConsumer.glUniform1ARB(arg0, arg1);
      } else {
         ModClassVisitor.glUniform1(arg0, arg1);
      }

   }

   public static void const(int arg0, int arg1) {
      if (boolean) {
         CharLongCharConsumer.glUniform1iARB(arg0, arg1);
      } else {
         ModClassVisitor.glUniform1i(arg0, arg1);
      }

   }

   public static void const(int arg0, FloatBuffer arg1) {
      if (boolean) {
         CharLongCharConsumer.glUniform1ARB(arg0, arg1);
      } else {
         ModClassVisitor.glUniform1(arg0, arg1);
      }

   }

   public static void long(int arg0, IntBuffer arg1) {
      if (boolean) {
         CharLongCharConsumer.glUniform2ARB(arg0, arg1);
      } else {
         ModClassVisitor.glUniform2(arg0, arg1);
      }

   }

   public static void long(int arg0, FloatBuffer arg1) {
      if (boolean) {
         CharLongCharConsumer.glUniform2ARB(arg0, arg1);
      } else {
         ModClassVisitor.glUniform2(arg0, arg1);
      }

   }

   public static void class(int arg0, IntBuffer arg1) {
      if (boolean) {
         CharLongCharConsumer.glUniform3ARB(arg0, arg1);
      } else {
         ModClassVisitor.glUniform3(arg0, arg1);
      }

   }

   public static void class(int arg0, FloatBuffer arg1) {
      if (boolean) {
         CharLongCharConsumer.glUniform3ARB(arg0, arg1);
      } else {
         ModClassVisitor.glUniform3(arg0, arg1);
      }

   }

   public static void null(int arg0, IntBuffer arg1) {
      if (boolean) {
         CharLongCharConsumer.glUniform4ARB(arg0, arg1);
      } else {
         ModClassVisitor.glUniform4(arg0, arg1);
      }

   }

   public static void null(int arg0, FloatBuffer arg1) {
      if (boolean) {
         CharLongCharConsumer.glUniform4ARB(arg0, arg1);
      } else {
         ModClassVisitor.glUniform4(arg0, arg1);
      }

   }

   public static void long(int arg0, boolean arg1, FloatBuffer arg2) {
      if (boolean) {
         CharLongCharConsumer.glUniformMatrix2ARB(arg0, arg1, arg2);
      } else {
         ModClassVisitor.glUniformMatrix2(arg0, arg1, arg2);
      }

   }

   public static void class(int arg0, boolean arg1, FloatBuffer arg2) {
      if (boolean) {
         CharLongCharConsumer.glUniformMatrix3ARB(arg0, arg1, arg2);
      } else {
         ModClassVisitor.glUniformMatrix3(arg0, arg1, arg2);
      }

   }

   public static void null(int arg0, boolean arg1, FloatBuffer arg2) {
      if (boolean) {
         CharLongCharConsumer.glUniformMatrix4ARB(arg0, arg1, arg2);
      } else {
         ModClassVisitor.glUniformMatrix4(arg0, arg1, arg2);
      }

   }

   public static int null(int arg0, CharSequence arg1) {
      return boolean ? ObjDoubleConsumer.glGetAttribLocationARB(arg0, arg1) : ModClassVisitor.glGetAttribLocation(arg0, arg1);
   }

   public static int long() {
      return goto ? WebSettings.glGenBuffersARB() : MockDriver.glGenBuffers();
   }

   public static void long(int arg0, int arg1) {
      if (goto) {
         WebSettings.glBindBufferARB(arg0, arg1);
      } else {
         MockDriver.glBindBuffer(arg0, arg1);
      }

   }

   public static void null(int arg0, ByteBuffer arg1, int arg2) {
      if (goto) {
         WebSettings.glBufferDataARB(arg0, arg1, arg2);
      } else {
         MockDriver.glBufferData(arg0, arg1, arg2);
      }

   }

   public static void do(int arg0) {
      if (goto) {
         WebSettings.glDeleteBuffersARB(arg0);
      } else {
         MockDriver.glDeleteBuffers(arg0);
      }

   }

   public static boolean class() {
      if (SQLAlterTableDropConstraint.throw()) {
         return false;
      } else if (SQLAlterTableDropConstraint.do() && !false) {
         return false;
      } else {
         return do && SetMetadata.null().P.ub;
      }
   }

   public static void class(int arg0, int arg1) {
      if (public) {
         switch(default) {
         case null:
            FloatLongIntFunction.glBindFramebuffer(arg0, arg1);
            break;
         case int:
            CriterionProgress.glBindFramebuffer(arg0, arg1);
            break;
         case final:
            Blocks.glBindFramebufferEXT(arg0, arg1);
         }
      }

   }

   public static void null(int arg0, int arg1) {
      if (public) {
         switch(default) {
         case null:
            FloatLongIntFunction.glBindRenderbuffer(arg0, arg1);
            break;
         case int:
            CriterionProgress.glBindRenderbuffer(arg0, arg1);
            break;
         case final:
            Blocks.glBindRenderbufferEXT(arg0, arg1);
         }
      }

   }

   public static void true(int arg0) {
      if (public) {
         switch(default) {
         case null:
            FloatLongIntFunction.glDeleteRenderbuffers(arg0);
            break;
         case int:
            CriterionProgress.glDeleteRenderbuffers(arg0);
            break;
         case final:
            Blocks.glDeleteRenderbuffersEXT(arg0);
         }
      }

   }

   public static void const(int arg0) {
      if (public) {
         switch(default) {
         case null:
            FloatLongIntFunction.glDeleteFramebuffers(arg0);
            break;
         case int:
            CriterionProgress.glDeleteFramebuffers(arg0);
            break;
         case final:
            Blocks.glDeleteFramebuffersEXT(arg0);
         }
      }

   }

   public static int class() {
      if (!public) {
         return -1;
      } else {
         switch(default) {
         case null:
            return FloatLongIntFunction.glGenFramebuffers();
         case int:
            return CriterionProgress.glGenFramebuffers();
         case final:
            return Blocks.glGenFramebuffersEXT();
         default:
            return -1;
         }
      }
   }

   public static int null() {
      if (!public) {
         return -1;
      } else {
         switch(default) {
         case null:
            return FloatLongIntFunction.glGenRenderbuffers();
         case int:
            return CriterionProgress.glGenRenderbuffers();
         case final:
            return Blocks.glGenRenderbuffersEXT();
         default:
            return -1;
         }
      }
   }

   public static void long(int arg0, int arg1, int arg2, int arg3) {
      if (public) {
         switch(default) {
         case null:
            FloatLongIntFunction.glRenderbufferStorage(arg0, arg1, arg2, arg3);
            break;
         case int:
            CriterionProgress.glRenderbufferStorage(arg0, arg1, arg2, arg3);
            break;
         case final:
            Blocks.glRenderbufferStorageEXT(arg0, arg1, arg2, arg3);
         }
      }

   }

   public static void class(int arg0, int arg1, int arg2, int arg3) {
      if (public) {
         switch(default) {
         case null:
            FloatLongIntFunction.glFramebufferRenderbuffer(arg0, arg1, arg2, arg3);
            break;
         case int:
            CriterionProgress.glFramebufferRenderbuffer(arg0, arg1, arg2, arg3);
            break;
         case final:
            Blocks.glFramebufferRenderbufferEXT(arg0, arg1, arg2, arg3);
         }
      }

   }

   public static int null(int arg0) {
      if (!public) {
         return -1;
      } else {
         switch(default) {
         case null:
            return FloatLongIntFunction.glCheckFramebufferStatus(arg0);
         case int:
            return CriterionProgress.glCheckFramebufferStatus(arg0);
         case final:
            return Blocks.glCheckFramebufferStatusEXT(arg0);
         default:
            return -1;
         }
      }
   }

   public static void null(int arg0, int arg1, int arg2, int arg3, int arg4) {
      if (public) {
         switch(default) {
         case null:
            FloatLongIntFunction.glFramebufferTexture2D(arg0, arg1, arg2, arg3, arg4);
            break;
         case int:
            CriterionProgress.glFramebufferTexture2D(arg0, arg1, arg2, arg3, arg4);
            break;
         case final:
            Blocks.glFramebufferTexture2DEXT(arg0, arg1, arg2, arg3, arg4);
         }
      }

   }

   public static void long(int arg0) {
      if (interface) {
         FP2Server.glActiveTextureARB(arg0);
      } else {
         MediaCryptoException.glActiveTexture(arg0);
      }

   }

   public static void class(int arg0) {
      if (interface) {
         FP2Server.glClientActiveTextureARB(arg0);
      } else {
         MediaCryptoException.glClientActiveTexture(arg0);
      }

   }

   public static void null(int arg0, float arg1, float arg2) {
      if (interface) {
         FP2Server.glMultiTexCoord2fARB(arg0, arg1, arg2);
      } else {
         MediaCryptoException.glMultiTexCoord2f(arg0, arg1, arg2);
      }

      if (arg0 == instanceof) {
         long = arg1;
         new = arg2;
      }

   }

   public static void null(int arg0, int arg1, int arg2, int arg3) {
      if (true) {
         if (for) {
            IntBoolObjConsumer.glBlendFuncSeparateEXT(arg0, arg1, arg2, arg3);
         } else {
            MySqlSubPartitionByValue.glBlendFuncSeparate(arg0, arg1, arg2, arg3);
         }
      } else {
         IFMLSidedHandler.glBlendFunc(arg0, arg1);
      }

   }

   public static boolean null() {
      if (SQLAlterTableDropConstraint.super()) {
         return false;
      } else if (SQLAlterTableDropConstraint.return()) {
         return false;
      } else {
         return public && SetMetadata.null().P.HC;
      }
   }

   public static void null(int arg0, long arg1, int arg2) {
      if (goto) {
         WebSettings.glBufferDataARB(arg0, arg1, arg2);
      } else {
         MockDriver.glBufferData(arg0, arg1, arg2);
      }

   }

   public static void null(int arg0, long arg1, ByteBuffer arg2) {
      if (goto) {
         WebSettings.glBufferSubDataARB(arg0, arg1, arg2);
      } else {
         MockDriver.glBufferSubData(arg0, arg1, arg2);
      }

   }

   public static void null(int arg0, int arg1, long arg2, long arg3, long arg4) {
      if (const) {
         GpsSatellite.glCopyBufferSubData(arg0, arg1, arg2, arg3, arg4);
      } else {
         L2Regularization.glCopyBufferSubData(arg0, arg1, arg2, arg3, arg4);
      }

   }

   public static String null() {
      return catch == null ? ShortIterator.null("1?c!c%z$3") : catch;
   }

   public static void null(int arg0) {
      FMLEvent.case();
      FMLEvent.class(false);
      ImageSpan var1 = ImageSpan.null();
      MissingWorldException var2 = var1.null();
      IFMLSidedHandler.glLineWidth(4.0F);
      var2.null(1, ModelAdapterWolf.do);
      var2.null(0.0D, 0.0D, 0.0D).null(0, 0, 0, 255).true();
      var2.null((double)arg0, 0.0D, 0.0D).null(0, 0, 0, 255).true();
      var2.null(0.0D, 0.0D, 0.0D).null(0, 0, 0, 255).true();
      var2.null(0.0D, (double)arg0, 0.0D).null(0, 0, 0, 255).true();
      var2.null(0.0D, 0.0D, 0.0D).null(0, 0, 0, 255).true();
      var2.null(0.0D, 0.0D, (double)arg0).null(0, 0, 0, 255).true();
      var1.null();
      IFMLSidedHandler.glLineWidth(2.0F);
      var2.null(1, ModelAdapterWolf.do);
      var2.null(0.0D, 0.0D, 0.0D).null(255, 0, 0, 255).true();
      var2.null((double)arg0, 0.0D, 0.0D).null(255, 0, 0, 255).true();
      var2.null(0.0D, 0.0D, 0.0D).null(0, 255, 0, 255).true();
      var2.null(0.0D, (double)arg0, 0.0D).null(0, 255, 0, 255).true();
      var2.null(0.0D, 0.0D, 0.0D).null(127, 127, 255, 255).true();
      var2.null(0.0D, 0.0D, (double)arg0).null(127, 127, 255, 255).true();
      var1.null();
      IFMLSidedHandler.glLineWidth(1.0F);
      FMLEvent.class(true);
      FMLEvent.if();
   }

   public static void null(File arg0) {
      String var1 = arg0.getAbsolutePath();
      if (ByteIntByteConsumer.null() == ByteIntByteConsumer.a.int) {
         try {
            I.info(var1);
            Runtime.getRuntime().exec(new String[]{NonGrowingDirectByteBufOut.null("g!;&g6!:g;81&"), var1});
            return;
         } catch (IOException var7) {
            I.error(ShortIterator.null("\tb?a.cmyjb:h$-,d&h"), var7);
         }
      } else if (ByteIntByteConsumer.null() == ByteIntByteConsumer.a.null) {
         String var2 = String.format(NonGrowingDirectByteBufOut.null("+9,z-,-tg\u0017h'<5: hv\u0007$-:h2!8-vhvm'j"), var1);

         try {
            Runtime.getRuntime().exec(var2);
            return;
         } catch (IOException var6) {
            I.error(ShortIterator.null("\tb?a.cmyjb:h$-,d&h"), var6);
         }
      }

      boolean var8 = false;

      try {
         Class var3 = Class.forName(NonGrowingDirectByteBufOut.null(">)\")z)#<z\f1;?<;8"));
         Object var4 = var3.getMethod(ShortIterator.null("-h>I/~!y%}")).invoke((Object)null);
         var3.getMethod(NonGrowingDirectByteBufOut.null("6:;?'-"), URI.class).invoke(var4, arg0.toURI());
      } catch (Throwable var5) {
         I.error(ShortIterator.null("\tb?a.cmyjb:h$-&d$f"), var5);
         var8 = true;
      }

      if (var8) {
         I.info(NonGrowingDirectByteBufOut.null("\u0007$-:!:/t>=)t;-; -9h7$5;'i"));
         JdbcSqlStatValue.openURL(ShortIterator.null("k#a/7e\"") + var1);
      }

   }

   static enum a {
      null,
      int,
      final;
   }
}
