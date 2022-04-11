package com.itsert.exceptions

import com.itsert.core.scriptengine.types.expression.Expression

class ReturnException(val value: Expression) : RuntimeException()