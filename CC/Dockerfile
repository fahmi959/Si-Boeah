# Menggunakan Node.js sebagai base image
FROM node:14

# Membuat direktori kerja di dalam container
WORKDIR /app

# Menyalin package.json dan package-lock.json ke dalam container
COPY package*.json ./

# Menginstal dependensi
RUN npm install

# Menyalin kode aplikasi ke dalam container
COPY . .

# Mengexpose port 8000
EXPOSE 8000

# Menjalankan aplikasi saat container berjalan
CMD ["node", "server.js"]
