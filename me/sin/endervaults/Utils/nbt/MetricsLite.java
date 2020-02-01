/*     */ package me.sin.endervaults.Utils.nbt;
/*     */ 
/*     */

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.logging.Level;
import java.util.zip.GZIPOutputStream;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MetricsLite
/*     */ {
/*     */   public static final int B_STATS_VERSION = 1;
/*     */   private static final String URL = "https://bStats.org/submitData/bukkit";
/*     */   private static boolean logFailedRequests;
/*     */   private static String serverUUID;
/*     */   private final JavaPlugin plugin;
/*     */   
/*     */   public MetricsLite(JavaPlugin plugin)
/*     */   {
/*  51 */     if (plugin == null) {
/*  52 */       throw new IllegalArgumentException("Plugin cannot be null!");
/*     */     }
/*  54 */     this.plugin = plugin;
/*     */     
/*     */ 
/*  57 */     File bStatsFolder = new File(plugin.getDataFolder().getParentFile(), "bStats");
/*  58 */     File configFile = new File(bStatsFolder, "config.yml");
/*  59 */     YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
/*     */     
/*     */ 
/*  62 */     if (!config.isSet("serverUuid"))
/*     */     {
/*     */ 
/*  65 */       config.addDefault("enabled", Boolean.valueOf(true));
/*     */       
/*  67 */       config.addDefault("serverUuid", UUID.randomUUID().toString());
/*     */       
/*  69 */       config.addDefault("logFailedRequests", Boolean.valueOf(false));
/*     */       
/*     */ 
/*  72 */       config.options().header(
/*  73 */         "bStats collects some data for plugin authors like how many servers are using their plugins.\nTo honor their work, you should not disable it.\nThis has nearly no effect on the server performance!\nCheck out https://bStats.org/ to learn more :)")
/*     */         
/*     */ 
/*     */ 
/*  77 */         .copyDefaults(true);
/*     */       try {
/*  79 */         config.save(configFile);
/*     */       }
/*     */       catch (IOException localIOException) {}
/*     */     }
/*     */     
/*  84 */     serverUUID = config.getString("serverUuid");
/*  85 */     logFailedRequests = config.getBoolean("logFailedRequests", false);
/*  86 */     if (config.getBoolean("enabled", true)) {
/*  87 */       boolean found = false;
/*     */       
/*  89 */       for (Class<?> service : Bukkit.getServicesManager().getKnownServices()) {
/*     */         try {
/*  91 */           service.getField("B_STATS_VERSION");
/*  92 */           found = true;
/*     */         }
/*     */         catch (NoSuchFieldException localNoSuchFieldException) {}
/*     */       }
/*     */       
/*  97 */       Bukkit.getServicesManager().register(MetricsLite.class, this, plugin, ServicePriority.Normal);
/*  98 */       if (!found)
/*     */       {
/* 100 */         startSubmitting();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void startSubmitting()
/*     */   {
/* 109 */     final Timer timer = new Timer(true);
/* 110 */     timer.scheduleAtFixedRate(new TimerTask()
/*     */     {
/*     */       public void run() {
/* 113 */         if (!MetricsLite.this.plugin.isEnabled()) {
/* 114 */           timer.cancel();
/* 115 */           return;
/*     */         }
/*     */         
/*     */ 
/* 119 */         Bukkit.getScheduler().runTask(MetricsLite.this.plugin, new Runnable()
/*     */         {
/*     */           public void run() {
/* 122 */             MetricsLite.this.submitData();
/*     */           }
/*     */         });
/*     */       }
/* 126 */     }, 300000L, 1800000L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JSONObject getPluginData()
/*     */   {
/* 139 */     JSONObject data = new JSONObject();
/*     */     
/* 141 */     String pluginName = this.plugin.getDescription().getName();
/* 142 */     String pluginVersion = this.plugin.getDescription().getVersion();
/*     */     
/* 144 */     data.put("pluginName", pluginName);
/* 145 */     data.put("pluginVersion", pluginVersion);
/* 146 */     JSONArray customCharts = new JSONArray();
/* 147 */     data.put("customCharts", customCharts);
/*     */     
/* 149 */     return data;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private JSONObject getServerData()
/*     */   {
/* 159 */     int playerAmount = Bukkit.getOnlinePlayers().size();
/* 160 */     int onlineMode = Bukkit.getOnlineMode() ? 1 : 0;
/* 161 */     String bukkitVersion = Bukkit.getVersion();
/* 162 */     bukkitVersion = bukkitVersion.substring(bukkitVersion.indexOf("MC: ") + 4, bukkitVersion.length() - 1);
/*     */     
/*     */ 
/* 165 */     String javaVersion = System.getProperty("java.version");
/* 166 */     String osName = System.getProperty("os.name");
/* 167 */     String osArch = System.getProperty("os.arch");
/* 168 */     String osVersion = System.getProperty("os.version");
/* 169 */     int coreCount = Runtime.getRuntime().availableProcessors();
/*     */     
/* 171 */     JSONObject data = new JSONObject();
/*     */     
/* 173 */     data.put("serverUUID", serverUUID);
/*     */     
/* 175 */     data.put("playerAmount", Integer.valueOf(playerAmount));
/* 176 */     data.put("onlineMode", Integer.valueOf(onlineMode));
/* 177 */     data.put("bukkitVersion", bukkitVersion);
/*     */     
/* 179 */     data.put("javaVersion", javaVersion);
/* 180 */     data.put("osName", osName);
/* 181 */     data.put("osArch", osArch);
/* 182 */     data.put("osVersion", osVersion);
/* 183 */     data.put("coreCount", Integer.valueOf(coreCount));
/*     */     
/* 185 */     return data;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void submitData()
/*     */   {
/* 192 */     final JSONObject data = getServerData();
/*     */     
/* 194 */     JSONArray pluginData = new JSONArray();
/*     */     
/* 196 */     for (Class<?> service : Bukkit.getServicesManager().getKnownServices()) {
/*     */       try {
/* 198 */         service.getField("B_STATS_VERSION");
/*     */       }
/*     */       catch (NoSuchFieldException ignored) {
/*     */         continue;
/*     */       }
/*     */       try {
/* 204 */         pluginData.add(service.getMethod("getPluginData", new Class[0]).invoke(Bukkit.getServicesManager().load(service), new Object[0]));
/*     */       }
/*     */       catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException localNoSuchMethodException) {}
/*     */     }
/* 208 */     data.put("plugins", pluginData);
/*     */     
/*     */ 
/* 211 */     new Thread(new Runnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*     */         try {
/* 216 */           MetricsLite.sendData(data);
/*     */         }
/*     */         catch (Exception e) {
/* 219 */           if (MetricsLite.logFailedRequests) {
/* 220 */             MetricsLite.this.plugin.getLogger().log(Level.WARNING, "Could not submit plugin stats of " + MetricsLite.this.plugin.getName(), e);
/*     */           }
/*     */         }
/*     */       }
/*     */     })
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 224 */       .start();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void sendData(JSONObject data)
/*     */     throws Exception
/*     */   {
/* 234 */     if (data == null) {
/* 235 */       throw new IllegalArgumentException("Data cannot be null!");
/*     */     }
/* 237 */     if (Bukkit.isPrimaryThread()) {
/* 238 */       throw new IllegalAccessException("This method must not be called from the main thread!");
/*     */     }
/* 240 */     HttpsURLConnection connection = (HttpsURLConnection)new URL("https://bStats.org/submitData/bukkit").openConnection();
/*     */     
/*     */ 
/* 243 */     byte[] compressedData = compress(data.toString());
/*     */     
/*     */ 
/* 246 */     connection.setRequestMethod("POST");
/* 247 */     connection.addRequestProperty("Accept", "application/json");
/* 248 */     connection.addRequestProperty("Connection", "close");
/* 249 */     connection.addRequestProperty("Content-Encoding", "gzip");
/* 250 */     connection.addRequestProperty("Content-Length", String.valueOf(compressedData.length));
/* 251 */     connection.setRequestProperty("Content-Type", "application/json");
/* 252 */     connection.setRequestProperty("User-Agent", "MC-Server/1");
/*     */     
/*     */ 
/* 255 */     connection.setDoOutput(true);
/* 256 */     DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
/* 257 */     outputStream.write(compressedData);
/* 258 */     outputStream.flush();
/* 259 */     outputStream.close();
/*     */     
/* 261 */     connection.getInputStream().close();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static byte[] compress(String str)
/*     */     throws IOException
/*     */   {
/* 272 */     if (str == null) {
/* 273 */       return null;
/*     */     }
/* 275 */     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 276 */     GZIPOutputStream gzip = new GZIPOutputStream(outputStream);
/* 277 */     gzip.write(str.getBytes("UTF-8"));
/* 278 */     gzip.close();
/* 279 */     return outputStream.toByteArray();
/*     */   }
/*     */ }


/* Location:              D:\JavaAPIS\Item-NBT-API.jar!\de\tr7zw\itemnbtapi\MetricsLite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */