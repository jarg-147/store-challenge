import com.android.build.gradle.LibraryExtension
import com.jargcode.storechallenge.convention.configureKotlinAndroid
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
            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    merges += "META-INF/LICENSE.md"
                    merges += "META-INF/LICENSE-notice.md"
                }
            }

            configureKotlinAndroid(
                commonExtension = this
            )
        }
    }

}
