import com.jargcode.storechallenge.convention.library
import com.jargcode.storechallenge.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.run {
            apply("com.google.devtools.ksp")
            apply("dagger.hilt.android.plugin")
        }

        dependencies {
            add("implementation", libs.library("hilt-android"))
            add("implementation", libs.library("hilt-navigation-compose"))
            add("ksp", libs.library("hilt-compiler"))
        }
    }

}
