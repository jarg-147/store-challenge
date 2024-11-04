import com.android.build.api.dsl.ApplicationExtension
import com.jarg.transport.porto.convention.*
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

            configureKotlinAndroid(
                commonExtension = this
            )

            configureBuildTypes(
                commonExtension = this
            )
        }
    }

}
