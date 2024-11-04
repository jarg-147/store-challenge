import com.jarg.transport.porto.convention.library
import com.jarg.transport.porto.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationFirebaseConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.run {
            apply("com.google.gms.google-services")
            apply("com.google.firebase.firebase-perf")
            apply("com.google.firebase.crashlytics")
        }

        dependencies {
            val bom = libs.library("firebase-bom")
            add("implementation", platform(bom))
            add("implementation", libs.library("firebase-crashlytics"))
            add("implementation", libs.library("firebase-performance"))
        }
    }

}
