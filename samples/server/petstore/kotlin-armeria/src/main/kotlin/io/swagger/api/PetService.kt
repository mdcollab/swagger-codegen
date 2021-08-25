package io.swagger.api

import com.linecorp.armeria.common.HttpResponse
import com.linecorp.armeria.common.HttpStatus
import com.linecorp.armeria.server.annotation.Param
import com.linecorp.armeria.server.annotation.StatusCode

class PetService : PetApi {

    @StatusCode(404)
    override suspend fun getPetById(petId : kotlin.Long) : HttpResponse {
        return HttpResponse.of("It's here!")
    }
}