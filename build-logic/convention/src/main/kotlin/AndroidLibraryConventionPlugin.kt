import com.android.build.gradle.LibraryExtension
import com.jarg.transport.porto.convention.configureBuildTypes
import com.jarg.transport.porto.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.run {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }

        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(
                commonExtension = this
            )
            
            configureBuildTypes(
                commonExtension = this
            )
        }
    }

}
