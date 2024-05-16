buildscript {
    val agp_version by extra("7.4.1")
    dependencies {
        classpath("com.android.tools.build:gradle:$agp_version")
        classpath("com.google.gms:google-services:4.4.1")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
}