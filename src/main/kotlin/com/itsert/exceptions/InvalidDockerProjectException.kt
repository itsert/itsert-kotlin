package com.itsert.exceptions

import java.lang.RuntimeException

class InvalidDockerProjectException (message: String?) : RuntimeException(message)