import com.jargcode.storechallenge.convention.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

class JvmLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.run {
            apply("org.jetbrains.kotlin.jvm")
        }

        configureKotlinJvm()
    }

}
