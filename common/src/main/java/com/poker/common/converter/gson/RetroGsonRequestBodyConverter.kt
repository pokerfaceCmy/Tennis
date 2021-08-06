package com.poker.common.converter.gson

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.Buffer
import retrofit2.Converter
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/9 11:01
 * @Desc: OkHttp转换器
 * @GitHub：https://github.com/pokerfaceCmy
 */
class RetroGsonRequestBodyConverter<T> constructor(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<T, RequestBody> {

    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        val writer = OutputStreamWriter(buffer.outputStream(), StandardCharsets.UTF_8)
        val jsonWriter = gson.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        return buffer.readByteString()
            .toRequestBody("application/json; charset=UTF-8".toMediaType())
    }

}