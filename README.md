  **若jdk7或8（据说jdk9会支持alpn）**
  jvm 需添加相关命令
  -Xbootclasspath/p:${maven_dir}/alpn-boot-xxxx.jar 
  
  **若要将此项目package成jar 需要在你的项目中引用**
  <dependency>
      <groupId>com.squareup.okhttp3</groupId>
      <artifactId>okhttp</artifactId>
      <version>3.6.0</version>
  </dependency>
