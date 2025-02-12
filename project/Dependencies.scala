package build.play.grpc

import sbt._
import sbt.Keys._

import akka.grpc.gen.{ BuildInfo => AkkaGrpcBuildInfo }

object Dependencies {

  object Versions {
    val scala212 = "2.12.18"
    val scala213 = "2.13.12"

    // Don't use AkkaGrpcBuildInfo.akkaHttpVersion or AkkaGrpcBuildInfo.akkaVersion and prioritize
    // aligning with versions transitively brought in via Play.
    val akka = "2.6.21"
    // bumps Akka HTTP version beyond play's 10.1.x
    val akkaHttp = "10.2.10"

    val akkaGrpc: String = AkkaGrpcBuildInfo.version
    val grpc: String     = AkkaGrpcBuildInfo.grpcVersion

    val play = "2.9.1"

    val scalaTest         = "3.2.17"
    val scalaTestPlusPlay = "6.0.1"

    val macwire = "2.5.9"
  }

  object Compile {
    val grpcStub = "io.grpc" % "grpc-stub" % Versions.grpc

    val akkaActorTyped           = "com.typesafe.akka" %% "akka-actor-typed"            % Versions.akka
    val akkaClusterShardingTyped = "com.typesafe.akka" %% "akka-cluster-sharding-typed" % Versions.akka
    val akkaStream               = "com.typesafe.akka" %% "akka-stream"                 % Versions.akka
    val akkaDiscovery            = "com.typesafe.akka" %% "akka-discovery"              % Versions.akka
    val akkaPersistenceTyped     = "com.typesafe.akka" %% "akka-persistence-typed"      % Versions.akka
    val akkaPersistenceQuery     = "com.typesafe.akka" %% "akka-persistence-query"      % Versions.akka
    val akkaSerializationJackson = "com.typesafe.akka" %% "akka-serialization-jackson"  % Versions.akka

    val akkaHttp          = "com.typesafe.akka" %% "akka-http"            % Versions.akkaHttp
    val akkaHttpSprayJson = "com.typesafe.akka" %% "akka-http-spray-json" % Versions.akkaHttp
    val akkaHttp2Support  = "com.typesafe.akka" %% "akka-http2-support"   % Versions.akkaHttp

    val akkaGrpcCodegen = "com.lightbend.akka.grpc" %% "akka-grpc-codegen" % Versions.akkaGrpc // Apache V2
    val akkaGrpcRuntime = "com.lightbend.akka.grpc" %% "akka-grpc-runtime" % Versions.akkaGrpc // Apache V2

    val play = ("com.typesafe.play" %% "play" % Versions.play)
      .exclude("javax.activation", "javax.activation-api") // Apache V2 (exclusion is "either GPL or CDDL")

    val playJava             = "com.typesafe.play" %% "play-java"               % Versions.play // Apache V2
    val playGuice            = "com.typesafe.play" %% "play-guice"              % Versions.play // Apache V2
    val playAkkaHttpServer   = "com.typesafe.play" %% "play-akka-http-server"   % Versions.play // Apache V2
    val playAkkaHttp2Support = "com.typesafe.play" %% "play-akka-http2-support" % Versions.play // Apache V2
    val playTest             = "com.typesafe.play" %% "play-test"               % Versions.play // Apache V2
    val playSpecs2           = "com.typesafe.play" %% "play-specs2"             % Versions.play // Apache V2

    val scalaTestPlusPlay = "org.scalatestplus.play" %% "scalatestplus-play" % Versions.scalaTestPlusPlay // Apache V2

    val macwire = "com.softwaremill.macwire" %% "macros" % Versions.macwire % "provided"
  }

  object Test {
    final val Test = sbt.Test

    val akkaActorTestkitTyped = "com.typesafe.akka" %% "akka-actor-testkit-typed" % Versions.akka
    val akkaStreamTestkit     = "com.typesafe.akka" %% "akka-stream-testkit"      % Versions.akka

    val junit             = "junit"                   % "junit"       % "4.13.2"           % Test // Common Public License 1.0
    val playAhcWs         = "com.typesafe.play"      %% "play-ahc-ws" % Versions.play      % Test // Apache V2
    val playSpecs2        = Compile.playSpecs2        % Test
    val scalaTest         = "org.scalatest"          %% "scalatest"   % Versions.scalaTest % Test // Apache V2
    val scalaTestPlusPlay = Compile.scalaTestPlusPlay % Test

    val junitInterface = "com.github.sbt" % "junit-interface" % "0.13.3" % "test"
    val logback        = "ch.qos.logback" % "logback-classic" % "1.4.14" % "test"
  }
}
