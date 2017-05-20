/*
 * Copyright (c) 2014 Jarek Sacha. All Rights Reserved.
 * Author's e-mail: jpsacha at gmail dot com
 */
package flycapture.examples.cpp

import flycapture.CheckMacro.check
import org.bytedeco.javacpp.FlyCapture2._

/**
 * The CameraPropertyInfoEx prints out property information from attached cameras.
 */
object CameraPropertyInfoEx extends App {

  def runSingleCamera(guid: PGRGuid) {

    // Connect to a camera
    val cam = new Camera()
    check(cam.Connect(guid))

    // Get the camera information
    val camInfo = new CameraInfo()
    check(cam.GetCameraInfo(camInfo))
    printCameraInfo(camInfo)

    printPropertyInfo(cam)

    // Disconnect the camera
    check(cam.Disconnect())
  }


  printBuildInfo()

  val busMgr = new BusManager()
  val numCameras = Array[Int](0)
  check(busMgr.GetNumOfCameras(numCameras))
  println("Number of cameras detected: " + numCameras(0))

  for (i <- 0 until numCameras(0)) {
    val guid = new PGRGuid()
    check(busMgr.GetCameraFromIndex(i, guid))

    runSingleCamera(guid)
  }

  println("Done!")
}
