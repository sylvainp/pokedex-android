package com.sylvainp.pokedex.domain.usecases

sealed class Usecase<P, T> {
    abstract suspend fun execute(params:P?=null): UsecaseResponse<T>
}