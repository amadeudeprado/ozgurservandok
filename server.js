// Gerekli modüllerin yüklenmesi
const express = require('express');
const http = require('http');
const socketIo = require('socket.io');
const mongoose = require('mongoose');

// Uygulama ve sunucu oluşturulması
const app = express();
const server = http.createServer(app);
const io = socketIo(server);

// MongoDB bağlantısı (veritabanı adınızı buraya yazın)
mongoose.connect('mongodb://localhost/chatDB', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

// Mesaj şeması ve modeli
const messageSchema = new mongoose.Schema({ name: String, message: String });
const Message = mongoose.model('Message', messageSchema);

// Ana sayfa için rota
app.get('/', (req, res) => {
  res.sendFile(__dirname + '/index.html');
});

// Soket bağlantıları
io.on('connection', (socket) => {
  console.log('Bir kullanıcı bağlandı');
  
  // Varolan mesajları gönderme
  Message.find().then(messages => {
    socket.emit('init', messages);
  });

  socket.on('disconnect', () => {
    console.log('Bir kullanıcı ayrıldı');
  });

  // Yeni mesajları alma ve herkese yayınlama
  socket.on('chat message', (msg) => {
    const message = new Message({ name: msg.name, message: msg.message });
    message.save().then(() => {
      io.emit('chat message', msg);
    });
  });
});

// Sunucunun dinlenmesi
server.listen(3000, () => {
  console.log('Sunucu 3000 portu üzerinde çalışıyor');
});
