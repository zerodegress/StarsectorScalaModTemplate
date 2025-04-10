lazy val root = project
  .in(file("."))
  .settings(
    libraryDependencies += "com.lihaoyi" %% "upickle" % "4.1.0"
  )
