package com.iyanuadelekan.kanary.app.data

import com.iyanuadelekan.kanary.app.ResponseEntity

/**
 * @author Iyanu Adelekan on 24/11/2018.
 */
@ResponseEntity("Generic API response value object.")
internal data class GenericResponse(val status: String, val message: String)