import com.android.build.api.dsl.LibraryExtension
import com.jarg.transport.porto.convention.configureFlavors
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryFlavorsConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        extensions.configure<LibraryExtension> {
            configureFlavors(
                commonExtension = this
            )
        }
    }

}