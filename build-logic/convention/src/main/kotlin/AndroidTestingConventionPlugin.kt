import com.jargcode.storechallenge.convention.library
import com.jargcode.storechallenge.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidTestingConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.run {
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
            apply(plugin = "store.hilt")
        }

        dependencies {
            // Modules
            add("implementation", project(":core:testing"))

            // Testing dependencies
            add("testImplementation", libs.library("junit"))
            add("testImplementation", libs.library("turbine"))
            add("testImplementation", libs.library("assertk"))
            add("testImplementation", libs.library("kotlinx.coroutines.test"))
            add("debugImplementation", libs.library("compose.ui.test.manifest"))
            add("androidTestImplementation", libs.library("kotlinx.coroutines.test"))
            add("androidTestImplementation", libs.library("compose.ui.test.junit4"))
        }
    }

}
