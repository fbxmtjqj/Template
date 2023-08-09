package com.fbxmtjqj.template.bussiness.model.vo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserVo (
    val userId: String,
    val username: String,
)
