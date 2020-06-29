package com.opefago.exceptions

import java.lang.RuntimeException

class BuildFailedException (message: String?) : RuntimeException(message)