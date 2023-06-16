const express = require("express");
const app = express();
const jwt = require('jsonwebtoken');
const axios = require('axios');
const multer = require("multer");
const admin = require('firebase-admin');
const { Firestore } = require("@google-cloud/firestore");



const serviceAccount = require('./serviceAccountKey.json');
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  storageBucket: 'capstone-389205.appspot.com' // Firebase Storage bucket URL
});

app.use(express.json()); // Parse JSON bodies

// Set up multer storage configuration
const storage = multer.memoryStorage(); // Store file in memory

//storage configuration
const upload = multer({ storage: storage });

// Endpoint untuk mengambil Articles berdasarkan ID
app.get('/articles/:id', (req, res) => {
  const ArticlesId = req.params.id;

  // Mengakses Firestore dan mengambil Articles
  admin.firestore().collection('articles').doc(ArticlesId).get()
    .then((doc) => {
      if (doc.exists) {
        const ArticlesData = doc.data();
        const ArticlesLink = ArticlesData.link;

        // Mengirim link Articles sebagai respons
        res.json({ link: ArticlesLink });
      } else {
        // Jika Articles tidak ditemukan
        res.status(404).json({ error: 'Articles tidak ditemukan' });
      }
    })
    
    .catch((error) => {
      // Jika terjadi kesalahan saat mengambil Articles
      res.status(500).json({ error: 'Terjadi kesalahan saat mengambil artikel' });
    });
});

app.listen(8000, () => {
  console.log(`Server running on port 8000`);
});
