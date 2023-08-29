<template>
  <div>
    <input type="file" @change="handleFileChange" />
    <button v-if="file" @click="uploadFile">Upload</button>
    <p v-if="progress > 0">{{ progress }}%</p>
  </div>
</template>

<script>
import SparkMD5 from 'spark-md5'
import axios from 'axios'
import request from "@/untils/request";

// const CHUNK_SIZE = 2 * 1024 * 1024 // 2MB
const CHUNK_SIZE = 1 * 1024 * 1024 // 2MB

export default {
  data() {
    return {
      file: null,
      progress: 0
    }
  },
  methods: {
    handleFileChange(event) {
      this.file = event.target.files[0]
    },
    async uploadFile() {
      const chunks = await this.splitFile(this.file)
      const uploadUrls = await this.getUploadUrls(chunks.length)
      const promises = chunks.map((chunk, i) => {
        const url = uploadUrls[i]
        return this.uploadChunk(chunk, url, i)
      })
      await Promise.all(promises)
      await this.mergeChunks(chunks, this.file.name)
    },
    async splitFile(file) {
      const chunks = []
      let startPos = 0
      let endPos = CHUNK_SIZE
      while (startPos < file.size) {
        const chunk = file.slice(startPos, endPos)
        const reader = new FileReader()
        const promise = new Promise((resolve, reject) => {
          reader.onload = () => {
            const md5 = SparkMD5.ArrayBuffer.hash(reader.result)
            resolve({ chunk, md5 })
          }
          reader.onerror = reject
        })
        reader.readAsArrayBuffer(chunk)
        chunks.push(promise)
        startPos = endPos
        endPos = startPos + CHUNK_SIZE
      }
      return Promise.all(chunks)
    },
    async getUploadUrls(count) {
      debugger
      const params = new URLSearchParams()
      params.append('count', count)
      // const { data } = await axios.post(`/api/upload/urls?count=${count}`)
      let { data } ={}
        await request('/upload/urls?'+params.toString(), 'post').then((res)=>{data=res});
      return data.urls
    },
    async uploadChunk(chunk, url, i) {
      const formData = new FormData()
      formData.append('chunk', chunk.chunk)
      formData.append('md5', chunk.md5)
      formData.append('index', i)
      await axios.post("/api"+url, formData, {
        onUploadProgress: progressEvent => {
          this.progress = Math.round((progressEvent.loaded * 100) / progressEvent.total)
        }
      })
    },
    async mergeChunks(chunks, filename) {
      const md5s = chunks.map(chunk => chunk.md5)
      const { data } = await axios.post('/api/upload/merge', { md5s, filename })
      this.progress = 0
      this.file = null
    }
  }
}
</script>
