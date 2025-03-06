<template>
  <div class="hello">
    <h1>Gemini Chat</h1>
    <div class="chat-container">
      <div v-for="(message, index) in messages" :key="index" :class="message.sender === 'user' ? 'user-message' : 'gemini-message'">
        <strong>{{ message.sender === 'user' ? 'You:' : 'Gemini:' }}</strong>
        <p>{{ message.text }}</p>
      </div>
    </div>
    <div class="input-area">
      <input type="text" v-model="prompt" placeholder="Nhập tin nhắn..." @keyup.enter="sendMessage">
      <button @click="sendMessage">Gửi</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'HelloWorld',
  data() {
    return {
      prompt: '',
      messages: [],
      apiUrl: 'http://localhost:8080/api/chat/generate' // Địa chỉ backend của bạn
    };
  },
  methods: {
    async sendMessage() {
      if (this.prompt.trim() === '') return;

      // Thêm tin nhắn của người dùng vào chat
      this.messages.push({ sender: 'user', text: this.prompt });

      try {
        const response = await axios.post(this.apiUrl, { prompt: this.prompt });
        // Thêm tin nhắn từ Gemini vào chat
        this.messages.push({ sender: 'gemini', text: response.data.response });
      } catch (error) {
        console.error("Lỗi khi gửi tin nhắn:", error);
        this.messages.push({ sender: 'gemini', text: "Lỗi: Không thể kết nối đến Gemini." });
      }

      this.prompt = ''; // Xóa input
    }
  }
}
</script>

<style scoped>
.hello {
  width: 80%;
  margin: 0 auto;
  text-align: center;
}

.chat-container {
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
  height: 300px;
  overflow-y: scroll;
}

.user-message {
  text-align: right;
  margin-bottom: 5px;
}

.gemini-message {
  text-align: left;
  margin-bottom: 5px;
}

.input-area {
  display: flex;
}

input[type="text"] {
  flex-grow: 1;
  padding: 5px;
}

button {
  padding: 5px 10px;
}
</style>