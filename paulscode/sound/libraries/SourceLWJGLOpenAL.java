package paulscode.sound.libraries;

import breeze.math.Semiring;
import com.alibaba.druid.sql.ast.statement.SQLAlterTableReplaceColumn;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;
import javax.sound.sampled.AudioFormat;
import net.daporkchop.lib.primitive.collection.ShortIterator;
import net.minecraft.client.gui.spectator.ISpectatorMenuView;
import paulscode.sound.Channel;
import paulscode.sound.FilenameURL;
import paulscode.sound.SoundBuffer;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.Source;

public class SourceLWJGLOpenAL extends Source {
   private ChannelLWJGLOpenAL channelOpenAL;
   private IntBuffer myBuffer;
   private FloatBuffer listenerPosition;
   private FloatBuffer sourcePosition;
   private FloatBuffer sourceVelocity;

   public SourceLWJGLOpenAL(FloatBuffer arg0, IntBuffer arg1, boolean arg2, boolean arg3, boolean arg4, String arg5, FilenameURL arg6, SoundBuffer arg7, float arg8, float arg9, float arg10, int arg11, float arg12, boolean arg13) {
      super(arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13);
      this.channelOpenAL = (ChannelLWJGLOpenAL)this.channel;
      if (this.codec != null) {
         this.codec.reverseByteOrder(true);
      }

      this.listenerPosition = arg0;
      this.myBuffer = arg1;
      this.libraryType = LibraryLWJGLOpenAL.class;
      this.pitch = 1.0F;
      this.resetALInformation();
   }

   public SourceLWJGLOpenAL(FloatBuffer arg0, IntBuffer arg1, Source arg2, SoundBuffer arg3) {
      super(arg2, arg3);
      this.channelOpenAL = (ChannelLWJGLOpenAL)this.channel;
      if (this.codec != null) {
         this.codec.reverseByteOrder(true);
      }

      this.listenerPosition = arg0;
      this.myBuffer = arg1;
      this.libraryType = LibraryLWJGLOpenAL.class;
      this.pitch = 1.0F;
      this.resetALInformation();
   }

   public SourceLWJGLOpenAL(FloatBuffer arg0, AudioFormat arg1, boolean arg2, String arg3, float arg4, float arg5, float arg6, int arg7, float arg8) {
      super(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
      this.channelOpenAL = (ChannelLWJGLOpenAL)this.channel;
      this.listenerPosition = arg0;
      this.libraryType = LibraryLWJGLOpenAL.class;
      this.pitch = 1.0F;
      this.resetALInformation();
   }

   public void cleanup() {
      super.cleanup();
   }

   public void changeSource(FloatBuffer arg0, IntBuffer arg1, boolean arg2, boolean arg3, boolean arg4, String arg5, FilenameURL arg6, SoundBuffer arg7, float arg8, float arg9, float arg10, int arg11, float arg12, boolean arg13) {
      super.changeSource(arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13);
      this.listenerPosition = arg0;
      this.myBuffer = arg1;
      this.pitch = 1.0F;
      this.resetALInformation();
   }

   public boolean incrementSoundSequence() {
      if (!this.toStream) {
         this.errorMessage(ShortIterator.null("@/y\"b.-md$n8h'h$y\u0019b?c.^/|?h$n/*j`+tjb$a3-(hjx9h.-,b8-9y8h+`#c--9b?\u007f)h9#"));
         return false;
      } else {
         synchronized(this.soundSequenceLock) {
            if (this.soundSequenceQueue != null && this.soundSequenceQueue.size() > 0) {
               this.filenameURL = (FilenameURL)this.soundSequenceQueue.remove(0);
               if (this.codec != null) {
                  this.codec.cleanup();
               }

               this.codec = SoundSystemConfig.getCodec(this.filenameURL.getFilename());
               if (this.codec != null) {
                  this.codec.reverseByteOrder(true);
                  if (this.codec.getAudioFormat() == null) {
                     this.codec.initialize(this.filenameURL.getURL());
                  }

                  AudioFormat var2 = this.codec.getAudioFormat();
                  if (var2 == null) {
                     this.errorMessage(Semiring.null(";#\u001e?\u0015v<9\b;\u001b\"Z8\u000f:\u0016v\u00138Z;\u001f\"\u00129\u001ev]?\u00145\b3\u00173\u0014\")9\u000f8\u001e\u0005\u001f'\u000f3\u00145\u001fq"));
                     return false;
                  }

                  boolean var3 = false;
                  short var6;
                  if (var2.getChannels() == 1) {
                     if (var2.getSampleSizeInBits() == 8) {
                        var6 = 4352;
                     } else {
                        if (var2.getSampleSizeInBits() != 16) {
                           this.errorMessage(ShortIterator.null("\u0003a&h-l&-9l'}&hj~#w/-#cj`/y\"b.-md$n8h'h$y\u0019b?c.^/|?h$n/*"));
                           return false;
                        }

                        var6 = 4353;
                     }
                  } else {
                     if (var2.getChannels() != 2) {
                        this.errorMessage(ShortIterator.null("L?i#bji+y+-$h#y\"h8-'b$bjc%\u007fj~>h8h%-#cj`/y\"b.-md$n8h'h$y\u0019b?c.^/|?h$n/*"));
                        return false;
                     }

                     if (var2.getSampleSizeInBits() == 8) {
                        var6 = 4354;
                     } else {
                        if (var2.getSampleSizeInBits() != 16) {
                           this.errorMessage(Semiring.null("3:\u00163\u001d7\u0016v\t7\u0017&\u00163Z%\u0013,\u001fv\u00138Z;\u001f\"\u00129\u001ev]?\u00145\b3\u00173\u0014\")9\u000f8\u001e\u0005\u001f'\u000f3\u00145\u001fq"));
                           return false;
                        }

                        var6 = 4355;
                     }
                  }

                  this.channelOpenAL.setFormat(var6, (int)var2.getSampleRate());
                  this.preLoad = true;
               }

               return true;
            } else {
               return false;
            }
         }
      }
   }

   public void listenerMoved() {
      this.positionChanged();
   }

   public void setPosition(float arg0, float arg1, float arg2) {
      super.setPosition(arg0, arg1, arg2);
      if (this.sourcePosition == null) {
         this.resetALInformation();
      } else {
         this.positionChanged();
      }

      this.sourcePosition.put(0, arg0);
      this.sourcePosition.put(1, arg1);
      this.sourcePosition.put(2, arg2);
      if (this.channel != null && this.channel.attachedSource == this && this.channelOpenAL != null && this.channelOpenAL.ALSource != null) {
         SQLAlterTableReplaceColumn.alSource(this.channelOpenAL.ALSource.get(0), 4100, this.sourcePosition);
         this.checkALError();
      }

   }

   public void positionChanged() {
      this.calculateDistance();
      this.calculateGain();
      if (this.channel != null && this.channel.attachedSource == this && this.channelOpenAL != null && this.channelOpenAL.ALSource != null) {
         SQLAlterTableReplaceColumn.alSourcef(this.channelOpenAL.ALSource.get(0), 4106, this.gain * this.sourceVolume * Math.abs(this.fadeOutGain) * this.fadeInGain);
         this.checkALError();
      }

      this.checkPitch();
   }

   private void checkPitch() {
      if (this.channel != null && this.channel.attachedSource == this && LibraryLWJGLOpenAL.alPitchSupported() && this.channelOpenAL != null && this.channelOpenAL.ALSource != null) {
         SQLAlterTableReplaceColumn.alSourcef(this.channelOpenAL.ALSource.get(0), 4099, this.pitch);
         this.checkALError();
      }

   }

   public void setLooping(boolean arg0) {
      super.setLooping(arg0);
      if (this.channel != null && this.channel.attachedSource == this && this.channelOpenAL != null && this.channelOpenAL.ALSource != null) {
         if (arg0) {
            SQLAlterTableReplaceColumn.alSourcei(this.channelOpenAL.ALSource.get(0), 4103, 1);
         } else {
            SQLAlterTableReplaceColumn.alSourcei(this.channelOpenAL.ALSource.get(0), 4103, 0);
         }

         this.checkALError();
      }

   }

   public void setAttenuation(int arg0) {
      super.setAttenuation(arg0);
      if (this.channel != null && this.channel.attachedSource == this && this.channelOpenAL != null && this.channelOpenAL.ALSource != null) {
         if (arg0 == 1) {
            SQLAlterTableReplaceColumn.alSourcef(this.channelOpenAL.ALSource.get(0), 4129, this.distOrRoll);
         } else {
            SQLAlterTableReplaceColumn.alSourcef(this.channelOpenAL.ALSource.get(0), 4129, 0.0F);
         }

         this.checkALError();
      }

   }

   public void setDistOrRoll(float arg0) {
      super.setDistOrRoll(arg0);
      if (this.channel != null && this.channel.attachedSource == this && this.channelOpenAL != null && this.channelOpenAL.ALSource != null) {
         if (this.attModel == 1) {
            SQLAlterTableReplaceColumn.alSourcef(this.channelOpenAL.ALSource.get(0), 4129, arg0);
         } else {
            SQLAlterTableReplaceColumn.alSourcef(this.channelOpenAL.ALSource.get(0), 4129, 0.0F);
         }

         this.checkALError();
      }

   }

   public void setVelocity(float arg0, float arg1, float arg2) {
      super.setVelocity(arg0, arg1, arg2);
      this.sourceVelocity = ISpectatorMenuView.createFloatBuffer(3).put(new float[]{arg0, arg1, arg2});
      this.sourceVelocity.flip();
      if (this.channel != null && this.channel.attachedSource == this && this.channelOpenAL != null && this.channelOpenAL.ALSource != null) {
         SQLAlterTableReplaceColumn.alSource(this.channelOpenAL.ALSource.get(0), 4102, this.sourceVelocity);
         this.checkALError();
      }

   }

   public void setPitch(float arg0) {
      super.setPitch(arg0);
      this.checkPitch();
   }

   public void play(Channel arg0) {
      if (!this.active()) {
         if (this.toLoop) {
            this.toPlay = true;
         }

      } else if (arg0 == null) {
         this.errorMessage(Semiring.null("\u0003\u00147\u0018:\u001fv\u000e9Z&\u00167\u0003v\t9\u000f$\u00193Vv\u00183\u00197\u000f%\u001fv\u0019>\u001b8\u00143\u0016v\r7\tv\u0014#\u0016:"));
      } else {
         boolean var2 = this.channel != arg0;
         if (this.channel != null && this.channel.attachedSource != this) {
            var2 = true;
         }

         boolean var3 = this.paused();
         super.play(arg0);
         this.channelOpenAL = (ChannelLWJGLOpenAL)this.channel;
         if (var2) {
            this.setPosition(this.position.x, this.position.y, this.position.z);
            this.checkPitch();
            if (this.channelOpenAL != null && this.channelOpenAL.ALSource != null) {
               if (LibraryLWJGLOpenAL.alPitchSupported()) {
                  SQLAlterTableReplaceColumn.alSourcef(this.channelOpenAL.ALSource.get(0), 4099, this.pitch);
                  this.checkALError();
               }

               SQLAlterTableReplaceColumn.alSource(this.channelOpenAL.ALSource.get(0), 4100, this.sourcePosition);
               this.checkALError();
               SQLAlterTableReplaceColumn.alSource(this.channelOpenAL.ALSource.get(0), 4102, this.sourceVelocity);
               this.checkALError();
               if (this.attModel == 1) {
                  SQLAlterTableReplaceColumn.alSourcef(this.channelOpenAL.ALSource.get(0), 4129, this.distOrRoll);
               } else {
                  SQLAlterTableReplaceColumn.alSourcef(this.channelOpenAL.ALSource.get(0), 4129, 0.0F);
               }

               this.checkALError();
               if (this.toLoop && !this.toStream) {
                  SQLAlterTableReplaceColumn.alSourcei(this.channelOpenAL.ALSource.get(0), 4103, 1);
               } else {
                  SQLAlterTableReplaceColumn.alSourcei(this.channelOpenAL.ALSource.get(0), 4103, 0);
               }

               this.checkALError();
            }

            if (!this.toStream) {
               if (this.myBuffer == null) {
                  this.errorMessage(ShortIterator.null("C%-9b?c.-(x,k/\u007fjy%-:a+t"));
                  return;
               }

               this.channelOpenAL.attachBuffer(this.myBuffer);
            }
         }

         if (!this.playing()) {
            if (this.toStream && !var3) {
               if (this.codec == null) {
                  this.errorMessage(Semiring.null("\u0012\u001f5\u00152\u001f$Z8\u000f:\u0016v\u00138Z;\u001f\"\u00129\u001ev]&\u00167\u0003q"));
                  return;
               }

               if (this.codec.getAudioFormat() == null) {
                  this.codec.initialize(this.filenameURL.getURL());
               }

               AudioFormat var4 = this.codec.getAudioFormat();
               if (var4 == null) {
                  this.errorMessage(ShortIterator.null("\u000bx.d%-\fb8`+yjc?a&-#cj`/y\"b.-m}&l3*"));
                  return;
               }

               boolean var5 = false;
               short var6;
               if (var4.getChannels() == 1) {
                  if (var4.getSampleSizeInBits() == 8) {
                     var6 = 4352;
                  } else {
                     if (var4.getSampleSizeInBits() != 16) {
                        this.errorMessage(Semiring.null("3:\u00163\u001d7\u0016v\t7\u0017&\u00163Z%\u0013,\u001fv\u00138Z;\u001f\"\u00129\u001ev]&\u00167\u0003q"));
                        return;
                     }

                     var6 = 4353;
                  }
               } else {
                  if (var4.getChannels() != 2) {
                     this.errorMessage(Semiring.null("\u0017\u000f2\u00139Z2\u001b\"\u001bv\u00143\u0013\"\u00123\bv\u00179\u00149Z8\u0015$Z%\u000e3\b3\u0015v\u00138Z;\u001f\"\u00129\u001ev]&\u00167\u0003q"));
                     return;
                  }

                  if (var4.getSampleSizeInBits() == 8) {
                     var6 = 4354;
                  } else {
                     if (var4.getSampleSizeInBits() != 16) {
                        this.errorMessage(ShortIterator.null("\u0003a&h-l&-9l'}&hj~#w/-#cj`/y\"b.-m}&l3*"));
                        return;
                     }

                     var6 = 4355;
                  }
               }

               this.channelOpenAL.setFormat(var6, (int)var4.getSampleRate());
               this.preLoad = true;
            }

            this.channel.play();
            if (this.pitch != 1.0F) {
               this.checkPitch();
            }
         }

      }
   }

   public boolean preLoad() {
      if (this.codec == null) {
         return false;
      } else {
         this.codec.initialize(this.filenameURL.getURL());
         LinkedList var1 = new LinkedList();

         for(int var2 = 0; var2 < SoundSystemConfig.getNumberStreamingBuffers(); ++var2) {
            this.soundBuffer = this.codec.read();
            if (this.soundBuffer == null || this.soundBuffer.audioData == null) {
               break;
            }

            var1.add(this.soundBuffer.audioData);
         }

         this.positionChanged();
         this.channel.preLoadBuffers(var1);
         this.preLoad = false;
         return true;
      }
   }

   private void resetALInformation() {
      this.sourcePosition = ISpectatorMenuView.createFloatBuffer(3).put(new float[]{this.position.x, this.position.y, this.position.z});
      this.sourceVelocity = ISpectatorMenuView.createFloatBuffer(3).put(new float[]{this.velocity.x, this.velocity.y, this.velocity.z});
      this.sourcePosition.flip();
      this.sourceVelocity.flip();
      this.positionChanged();
   }

   private void calculateDistance() {
      if (this.listenerPosition != null) {
         double var1 = (double)(this.position.x - this.listenerPosition.get(0));
         double var3 = (double)(this.position.y - this.listenerPosition.get(1));
         double var5 = (double)(this.position.z - this.listenerPosition.get(2));
         this.distanceFromListener = (float)Math.sqrt(var1 * var1 + var3 * var3 + var5 * var5);
      }

   }

   private void calculateGain() {
      if (this.attModel == 2) {
         if (this.distanceFromListener <= 0.0F) {
            this.gain = 1.0F;
         } else if (this.distanceFromListener >= this.distOrRoll) {
            this.gain = 0.0F;
         } else {
            this.gain = 1.0F - this.distanceFromListener / this.distOrRoll;
         }

         if (this.gain > 1.0F) {
            this.gain = 1.0F;
         }

         if (this.gain < 0.0F) {
            this.gain = 0.0F;
         }
      } else {
         this.gain = 1.0F;
      }

   }

   private boolean checkALError() {
      switch(SQLAlterTableReplaceColumn.alGetError()) {
      case 0:
         return false;
      case 40961:
         this.errorMessage(ShortIterator.null("D${+a#ijc+`/-:l8l'h>h8#"));
         return true;
      case 40962:
         this.errorMessage(Semiring.null("38\f7\u0016?\u001ev\n7\b7\u00173\u000e3\bx"));
         return true;
      case 40963:
         this.errorMessage(ShortIterator.null("D${+a#ijh$x'h8l>h.-:l8l'h>h8-<l&x/#"));
         return true;
      case 40964:
         this.errorMessage(Semiring.null("\u001f\u0016:\u001f1\u001b:Z5\u001b:\u0016x"));
         return true;
      case 40965:
         this.errorMessage(ShortIterator.null("\u001fc+o&hjy%-+a&b)l>hj`/`%\u007f3#"));
         return true;
      default:
         this.errorMessage(Semiring.null("\u0017\u0014v\u000f8\b3\u00199\u001d8\u0013,\u001f2Z3\b$\u0015$Z9\u00195\u000f$\b3\u001ex"));
         return true;
      }
   }
}
