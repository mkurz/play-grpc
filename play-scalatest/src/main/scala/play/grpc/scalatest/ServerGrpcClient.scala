/*
 * Copyright (C) from 2022 The Play Framework Contributors <https://github.com/playframework>, 2011-2021 Lightbend Inc. <https://www.lightbend.com>
 */
package play.grpc.scalatest

import scala.reflect.ClassTag

import akka.grpc.scaladsl.AkkaGrpcClient
import org.scalatest.TestData
import org.scalatestplus.play.BaseOneServerPerTest
import play.api.test.DefaultTestServerFactory
import play.api.test.RunningServer
import play.api.Application
import play.grpc.internal.AkkaGrpcClientFactory
import play.grpc.testkit.AkkaGrpcClientHelpers

/**
 * Helpers to test gRPC clients with Play using ScalaTest.
 *
 * Mixes a method into [[AkkaGrpcClientHelpers]] that knows how to configure
 */
trait ServerGrpcClient extends AkkaGrpcClientHelpers { this: BaseOneServerPerTest =>

  /** Configure the factory by combining the current app and server information */
  implicit def configuredAkkaGrpcClientFactory[T <: AkkaGrpcClient: ClassTag](
      implicit running: RunningServer,
  ): AkkaGrpcClientFactory.Configured[T] = {
    AkkaGrpcClientHelpers.factoryForAppEndpoints(running.app, running.endpoints)
  }
}
