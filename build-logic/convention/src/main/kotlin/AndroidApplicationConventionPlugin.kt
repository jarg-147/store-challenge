import com.android.build.api.dsl.ApplicationExtension
import com.jargcode.storechallenge.convention.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.run {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
        }

        extensions.configure<ApplicationExtension> {
            defaultConfig {
                applicationId = libs.version("app-packageId")

                targetSdk = libs.version("app-targetSdk").toInt()

                versionCode = libs.version("app-versionCode").toInt()
                versionName = libs.version("app-versionName")
            }

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
