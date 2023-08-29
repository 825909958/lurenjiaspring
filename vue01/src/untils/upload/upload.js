import SparkMD5 from 'spark-md5'

const CHUNK_SIZE = 2 * 1024 * 1024 // 2MB

function splitFile(file) {
  const chunks = []
  let startPos = 0
  let endPos = CHUNK_SIZE
  while (startPos < file.size) {
    const chunk = file.slice(startPos, endPos)
    const reader = new FileReader()
    reader.readAsArrayBuffer(chunk)
    reader.onload = () => {
      const md5 = SparkMD5.ArrayBuffer.hash(reader.result)
      chunks.push({ chunk, md5 })
    }
    startPos = endPos
    endPos = startPos + CHUNK_SIZE
  }
  return chunks
}

function uploadChunk(chunk, url) {
  const formData = new FormData()
  formData.append('chunk', chunk.chunk)
  formData.append('md5', chunk.md5)
  formData.append('index', i)
  return axios.post(url, formData)
}
