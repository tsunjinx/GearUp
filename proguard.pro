-injars ${dist.jar}
-outjars ${dist.dir}/${ant.project.name}-min.jar
-libraryjars <java.home>/lib/rt.jar

# Keep all Swing UI classes (forms & frames)
-keep public class * extends javax.swing.JFrame
-keep public class * extends javax.swing.JDialog

# Keep entry points
-keepclassmembers class * {
    public static void main(java.lang.String[]);
}

# Uncomment to enable obfuscation as well as shrinking
# -overloadaggressively
# -obfuscationdictionary proguard_dict.txt

-optimizationpasses 5
-dontwarn