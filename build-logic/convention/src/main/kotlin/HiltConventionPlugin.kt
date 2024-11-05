import com.jargcode.storechallenge.convention.library
import com.jargcode.storechallenge.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.run {
            apply("com.google.devtools.ksp")
        }

        dependencies {
            add("ksp", libs.library("hilt-compiler"))
        }

        when {
            pluginManager.hasPlugin("com.android.base") -> {
                // Hilt Android implementation
                pluginManager.run {
                    apply("dagger.hilt.android.plugin")
                }

                dependencies {
                    add("implementation", libs.library("hilt-android"))
                }
            }

            pluginManager.hasPlugin("org.jetbrains.kotlin.jvm") -> {
                // Hilt JVM implementation
                dependencies {
                    add("implementation", libs.library("hilt-core"))
                }
            }
        }
    }

}
