import com.jargcode.storechallenge.convention.library
import com.jargcode.storechallenge.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.run {
            apply("store.android.library")
            apply("store.android.library.compose")
            apply("store.android.library.flavors")
            apply("store.hilt")
            apply("org.jetbrains.kotlin.plugin.serialization")
        }

        dependencies {
            // Modules
            add("implementation", project(":core:domain"))
            add("implementation", project(":core:designsystem"))
            add("implementation", project(":core:ui"))
            add("implementation", project(":core:testing"))

            // Dependencies
            add("implementation", libs.library("kotlinx.serialization.json"))
            add("implementation", libs.library("hilt.navigation.compose"))
            add("implementation", libs.library("androidx.lifecycle.runtime.compose"))
            add("implementation", libs.library("androidx.lifecycle.viewmodel.compose"))
            add("implementation", libs.library("androidx.navigation.compose"))
        }
    }

}
