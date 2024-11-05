import com.android.build.api.dsl.LibraryExtension
import com.jargcode.storechallenge.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.run {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
        }

        extensions.configure<LibraryExtension> {
            configureAndroidCompose(
                commonExtension = this
            )
        }
    }

}
