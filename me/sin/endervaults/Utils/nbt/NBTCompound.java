/*     */ package me.sin.endervaults.Utils.nbt;
/*     */ 
/*     */

import org.bukkit.inventory.ItemStack;

import java.util.Set;

/*     */

/*     */
/*     */ 
/*     */ public class NBTCompound
/*     */ {
/*     */   private String compundname;
/*     */   private NBTCompound parent;
/*     */   
/*     */   protected NBTCompound(NBTCompound owner, String name)
/*     */   {
/*  14 */     this.compundname = name;
/*  15 */     this.parent = owner;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  19 */     return this.compundname;
/*     */   }
/*     */   
/*     */   protected Object getCompound() {
/*  23 */     return this.parent.getCompound();
/*     */   }
/*     */   
/*     */   protected void setCompound(Object comp) {
/*  27 */     this.parent.setCompound(comp);
/*     */   }
/*     */   
/*     */   public NBTCompound getParent() {
/*  31 */     return this.parent;
/*     */   }
/*     */   
/*     */   protected void setItem(ItemStack item) {
/*  35 */     this.parent.setItem(item);
/*     */   }
/*     */   
/*     */   public void setString(String key, String value) {
/*  39 */     NBTReflectionUtil.setString(this, key, value);
/*     */   }
/*     */   
/*     */   public String getString(String key) {
/*  43 */     return NBTReflectionUtil.getString(this, key);
/*     */   }
/*     */   
/*     */   protected String getContent(String key) {
/*  47 */     return NBTReflectionUtil.getContent(this, key);
/*     */   }
/*     */   
/*     */   public void setInteger(String key, Integer value)
/*     */   {
/*  52 */     NBTReflectionUtil.setInt(this, key, value);
/*     */   }
/*     */   
/*     */   public Integer getInteger(String key) {
/*  56 */     return NBTReflectionUtil.getInt(this, key);
/*     */   }
/*     */   
/*     */   public void setDouble(String key, Double value) {
/*  60 */     NBTReflectionUtil.setDouble(this, key, value);
/*     */   }
/*     */   
/*     */   public Double getDouble(String key) {
/*  64 */     return NBTReflectionUtil.getDouble(this, key);
/*     */   }
/*     */   
/*     */   public void setByte(String key, Byte value) {
/*  68 */     NBTReflectionUtil.setByte(this, key, value);
/*     */   }
/*     */   
/*     */   public Byte getByte(String key) {
/*  72 */     return NBTReflectionUtil.getByte(this, key);
/*     */   }
/*     */   
/*     */   public void setShort(String key, Short value) {
/*  76 */     NBTReflectionUtil.setShort(this, key, value);
/*     */   }
/*     */   
/*     */   public Short getShort(String key) {
/*  80 */     return NBTReflectionUtil.getShort(this, key);
/*     */   }
/*     */   
/*     */   public void setLong(String key, Long value) {
/*  84 */     NBTReflectionUtil.setLong(this, key, value);
/*     */   }
/*     */   
/*     */   public Long getLong(String key) {
/*  88 */     return NBTReflectionUtil.getLong(this, key);
/*     */   }
/*     */   
/*     */   public void setFloat(String key, Float value) {
/*  92 */     NBTReflectionUtil.setFloat(this, key, value);
/*     */   }
/*     */   
/*     */   public Float getFloat(String key) {
/*  96 */     return NBTReflectionUtil.getFloat(this, key);
/*     */   }
/*     */   
/*     */   public void setByteArray(String key, byte[] value) {
/* 100 */     NBTReflectionUtil.setByteArray(this, key, value);
/*     */   }
/*     */   
/*     */   public byte[] getByteArray(String key) {
/* 104 */     return NBTReflectionUtil.getByteArray(this, key);
/*     */   }
/*     */   
/*     */   public void setIntArray(String key, int[] value) {
/* 108 */     NBTReflectionUtil.setIntArray(this, key, value);
/*     */   }
/*     */   
/*     */   public int[] getIntArray(String key) {
/* 112 */     return NBTReflectionUtil.getIntArray(this, key);
/*     */   }
/*     */   
/*     */   public void setBoolean(String key, Boolean value) {
/* 116 */     NBTReflectionUtil.setBoolean(this, key, value);
/*     */   }
/*     */   
/*     */   protected void set(String key, Object val) {
/* 120 */     NBTReflectionUtil.set(this, key, val);
/*     */   }
/*     */   
/*     */   public Boolean getBoolean(String key) {
/* 124 */     return NBTReflectionUtil.getBoolean(this, key);
/*     */   }
/*     */   
/*     */   public void setObject(String key, Object value) {
/* 128 */     NBTReflectionUtil.setObject(this, key, value);
/*     */   }
/*     */   
/*     */   public <T> T getObject(String key, Class<T> type) {
/* 132 */     return (T)NBTReflectionUtil.getObject(this, key, type);
/*     */   }
/*     */   
/*     */   public Boolean hasKey(String key) {
/* 136 */     return NBTReflectionUtil.hasKey(this, key);
/*     */   }
/*     */   
/*     */   public void removeKey(String key) {
/* 140 */     NBTReflectionUtil.remove(this, key);
/*     */   }
/*     */   
/*     */   public Set<String> getKeys() {
/* 144 */     return NBTReflectionUtil.getKeys(this);
/*     */   }
/*     */   
/*     */   public NBTCompound addCompound(String name) {
/* 148 */     NBTReflectionUtil.addNBTTagCompound(this, name);
/* 149 */     return getCompound(name);
/*     */   }
/*     */   
/*     */   public NBTCompound getCompound(String name) {
/* 153 */     NBTCompound next = new NBTCompound(this, name);
/* 154 */     if (NBTReflectionUtil.valideCompound(next).booleanValue()) return next;
/* 155 */     return null;
/*     */   }
/*     */   
/*     */   public NBTList getList(String name, NBTType type) {
/* 159 */     return NBTReflectionUtil.getList(this, name, type);
/*     */   }
/*     */   
/*     */   public NBTType getType(String name) {
/* 163 */     if (MinecraftVersion.getVersion() == MinecraftVersion.MC1_7_R4) return NBTType.NBTTagEnd;
/* 164 */     return NBTType.valueOf(NBTReflectionUtil.getType(this, name));
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 169 */     String str = "";
/* 170 */     for (String k : getKeys()) {
/* 171 */       str = str + toString(k);
/*     */     }
/* 173 */     return str;
/*     */   }
/*     */   
/*     */   public String toString(String key) {
/* 177 */     String s = "";
/* 178 */     NBTCompound c = this;
/* 179 */     while (c.getParent() != null) {
/* 180 */       s = s + "   ";
/* 181 */       c = c.getParent();
/*     */     }
/* 183 */     if (getType(key) == NBTType.NBTTagCompound) {
/* 184 */       return getCompound(key).toString();
/*     */     }
/* 186 */     return s + "-" + key + ": " + getContent(key) + System.lineSeparator();
/*     */   }
/*     */ }


/* Location:              D:\JavaAPIS\Item-NBT-API.jar!\de\tr7zw\itemnbtapi\NBTCompound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */