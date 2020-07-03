import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.3.1.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    //id("org.mybatis.spring.boot") version "2.1.3"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
}

group = "com.demo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    maven{
        setUrl("http://maven.aliyun.com/nexus/content/groups/public/")
    }
    mavenCentral()
}

dependencies {
    // SpringBoot全家桶
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    // SpringBoot集成thymeleaf模板
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework:spring-context-support")
    runtimeOnly("org.springframework.boot:spring-boot-configuration-processor")
    // mybatis
    implementation(group = "org.mybatis.spring.boot", name = "mybatis-spring-boot-starter", version = "1.3.2")
    // thymeleaf模板引擎和shiro框架的整合
    implementation(group = "com.github.theborakompanioni", name = "thymeleaf-extras-shiro", version = "2.0.0")
    // 分页插件
    implementation(group = "com.github.pagehelper", name = "pagehelper-spring-boot-starter", version = "1.2.5")
    // json工具
    implementation(group = "com.alibaba", name = "fastjson", version = "1.2.68")
    // 数据库连接池
    implementation(group = "com.alibaba", name = "druid-spring-boot-starter", version = "1.1.14")
    // 浏览器信息
    implementation(group = "eu.bitwalker", name = "UserAgentUtils", version = "1.21")
    // 文件上传工具类
    implementation(group = "commons-fileupload", name = "commons-fileupload", version = "1.3.3")
    // IO工具类
    implementation(group = "commons-io", name = "commons-io", version = "2.5")
    // 常用String工具类
    implementation("org.apache.commons:commons-lang3")
    // shiro
    implementation(group = "org.apache.shiro", name = "shiro-core", version = "1.4.2")
    implementation(group = "org.apache.shiro", name = "shiro-spring", version = "1.4.2")
    implementation(group = "org.apache.shiro", name = "shiro-ehcache", version = "1.4.2")
    // kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    // 数据库驱动
    runtimeOnly("mysql:mysql-connector-java")
    // 测试类
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
