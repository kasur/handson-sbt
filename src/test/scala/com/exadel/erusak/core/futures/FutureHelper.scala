package com.exadel.erusak.core.futures

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

/**
 * @author erusak.
 */
object FutureHelper {
  import scala.language.implicitConversions
  implicit def tryToFuture[T](tryBlock: Try[T]): Future[T] =
    tryBlock match {
      case Success(success) => Future.successful(success)
      case Failure(ex) => Future.failed(ex)
    }
}
