import scala.language.implicitConversions

val scala3Version = "3.6.4"

lazy val packageMod = taskKey[File]("Package as a starsector mod(zip).")

// Compile / compile := (Compile / compile).dependsOn(fetchStarsectorJars).value

lazy val root = project
  .in(file("."))
  .settings(
    name := "StarSectorScalaModTemplate",
    version := "0.1.0",
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test,
    libraryDependencies += "log4j" % "log4j" % "1.2.9",
    libraryDependencies += "com.thoughtworks.xstream" % "xstream" % "1.4.10",
    packageMod := {
      val jarFile = (Compile / packageBin).value
      val targetDir = (target).value / "dist"
      val zipFile = targetDir / s"${name.value}-${version.value}.zip"
      val confFiles = (baseDirectory.value / "conf").allPaths.filter(_.isFile)
      val modInfoJson = (baseDirectory.value / "mod_info.json")
      val scalaRuntimeJars = scalaInstance.value.libraryJars;

      IO.createDirectory(targetDir)

      val mappings: Seq[(File, String)] = Seq(
        jarFile -> s"lib/mod.jar"
      ) ++ confFiles.get.map(f => f -> s"conf/${f.getName}") ++
        scalaRuntimeJars
          .filter(f => f.getName.contains("scala3"))
          .map(f => f -> s"lib/${f.getName}") :+ (modInfoJson, "mod_info.json")

      IO.zip(mappings, zipFile)

      zipFile
    }
  )
