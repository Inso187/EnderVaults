/*    */ package me.sin.endervaults.Utils.nbt;
/*    */ 
/*    */

/*    */
/*    */ public class NBTListCompound
/*    */ {
/*    */   private NBTList owner;
/*    */   private Object compound;
/*    */   
/*    */   protected NBTListCompound(NBTList parent, Object obj)
/*    */   {
/* 12 */     this.owner = parent;
/* 13 */     this.compound = obj;
/*    */   }
/*    */   
/*    */   public void setString(String key, String val) {
/* 17 */     if (val == null) {
/* 18 */       remove(key);
/* 19 */       return;
/*    */     }
/*    */     try {
/* 22 */       this.compound.getClass().getMethod("setString", new Class[] { String.class, String.class }).invoke(this.compound, new Object[] { key, val });
/* 23 */       this.owner.save();
/*    */     } catch (Exception ex) {
/* 25 */       ex.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   public void setInteger(String key, int val) {
/*    */     try {
/* 31 */       this.compound.getClass().getMethod("setInt", new Class[] { String.class, Integer.TYPE }).invoke(this.compound, new Object[] { key, Integer.valueOf(val) });
/* 32 */       this.owner.save();
/*    */     } catch (Exception ex) {
/* 34 */       ex.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   public int getInteger(String key) {
/*    */     try {
/* 40 */       return ((Integer)this.compound.getClass().getMethod("getInt", new Class[] { String.class }).invoke(this.compound, new Object[] { key })).intValue();
/*    */     } catch (Exception ex) {
/* 42 */       ex.printStackTrace();
/*    */     }
/* 44 */     return 0;
/*    */   }
/*    */   
/*    */   public void setDouble(String key, double val) {
/*    */     try {
/* 49 */       this.compound.getClass().getMethod("setDouble", new Class[] { String.class, Double.TYPE }).invoke(this.compound, new Object[] { key, Double.valueOf(val) });
/* 50 */       this.owner.save();
/*    */     } catch (Exception ex) {
/* 52 */       ex.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   public double getDouble(String key) {
/*    */     try {
/* 58 */       return ((Double)this.compound.getClass().getMethod("getDouble", new Class[] { String.class }).invoke(this.compound, new Object[] { key })).doubleValue();
/*    */     } catch (Exception ex) {
/* 60 */       ex.printStackTrace();
/*    */     }
/* 62 */     return 0.0D;
/*    */   }
/*    */   
/*    */   public String getString(String key)
/*    */   {
/*    */     try {
/* 68 */       return (String)this.compound.getClass().getMethod("getString", new Class[] { String.class }).invoke(this.compound, new Object[] { key });
/*    */     } catch (Exception ex) {
/* 70 */       ex.printStackTrace();
/*    */     }
/* 72 */     return "";
/*    */   }
/*    */   
/*    */   public boolean hasKey(String key) {
/*    */     try {
/* 77 */       return ((Boolean)this.compound.getClass().getMethod("hasKey", new Class[] { String.class }).invoke(this.compound, new Object[] { key })).booleanValue();
/*    */     } catch (Exception ex) {
/* 79 */       ex.printStackTrace();
/*    */     }
/* 81 */     return false;
/*    */   }
/*    */   
/*    */   public java.util.Set<String> getKeys() {
/*    */     try {
/* 86 */       return (java.util.Set)this.compound.getClass().getMethod("c", new Class[0]).invoke(this.compound, new Object[0]);
/*    */     } catch (Exception ex) {
/* 88 */       ex.printStackTrace();
/*    */     }
/* 90 */     return new java.util.HashSet();
/*    */   }
/*    */   
/*    */   public void remove(String key) {
/*    */     try {
/* 95 */       this.compound.getClass().getMethod("remove", new Class[] { String.class }).invoke(this.compound, new Object[] { key });
/*    */     } catch (Exception ex) {
/* 97 */       ex.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\JavaAPIS\Item-NBT-API.jar!\de\tr7zw\itemnbtapi\NBTListCompound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */