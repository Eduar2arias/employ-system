# Etapa 1: Node para desarrollo
FROM node:20-alpine

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .

# Exponer puerto Vite
EXPOSE 5173

# Comando para dev server
CMD ["npm", "run", "dev", "--", "--host"]
